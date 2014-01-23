/**
 * ServiceManagement.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.nrw.dipp.dippCore.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import org.apache.xmlbeans.XmlException;
import org.openarchives.oai.x20.oaiDc.DcDocument;
import org.openarchives.oai.x20.oaiDc.OaiDcType;

import de.nrw.dipp.definitions.admmetadata.AdmMdDocument;
import de.nrw.dipp.definitions.admmetadata.ElementType;
import de.nrw.dipp.definitions.admmetadata.Reference;
import de.nrw.dipp.definitions.extmetadata.Content;
import de.nrw.dipp.definitions.extmetadata.ExtMdDocument;
import de.nrw.dipp.dippCore.repository.contentmodel.CollectionModel;
import de.nrw.dipp.dippCore.repository.contentmodel.DippArticle;
import de.nrw.dipp.dippCore.repository.contentmodel.DippContainer;
import de.nrw.dipp.dippCore.repository.contentmodel.DippContent;
import de.nrw.dipp.dippCore.repository.contentmodel.DippJournal;
import de.nrw.dipp.dippCore.repository.contentmodel.OaiSet;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.repository.metadata.ObjectRelations;
import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreQualified;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RDF;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RIsearch;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.Fedora;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.FedoraCMA;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.OAI;
import de.nrw.dipp.dippCore.task.ConversionStatus;
import de.nrw.dipp.dippCore.task.Param;
import de.nrw.dipp.dippCore.task.TaskManager;
import de.nrw.dipp.dippCore.util.Config;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.util.URNGenerator;
import de.nrw.dipp.dippCore.webservice.AdministrativeMetadata;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import de.nrw.dipp.dippCore.www.definitions.InvalidParamMessage;

import de.nrw.dipp.dippCore.webservice.FieldSearchResult;
import de.nrw.dipp.dippCore.webservice.ConditionType;
import de.nrw.dipp.dippCore.webservice.ObjectFields;


/**
 * <p>Title: Class provides Methods for creating Fedora Objects 
 * according the DiPP Standards and writing them into a Fedora Repository</p>
 * <p>Description: Class obviously was used formerly for managing Fedora-Requests. 
 * Now it shall call DOManagement Methods for Fedora Repository interaction and 
 * class itself only should be used for creating Object representations according 
 * DiPP Content Models</p>
 * <p>
 *  <ul>
 *  	<li>modified 2006-03-30: public setArticleConvertedStatus</li>
 *  	<li>modified 2006-04-19: bugfix datastream-label, additional createArticle methods, added rdf datastreams -> not yet completed</li>
 *  	<li>modified 2006-04-25: removed methods public getDiPPAdministrativeMetaData </li>
 *  	<li>Qa: begin new modification 2009-04-02</li>
 *  	<li>Qa: 2009-07-07: added createNewDataObject method </li>
 *  
 *
 *  </ul>
 * </p>
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de, Andres Quast, quast@hbz-nrw.de
 * @version $Id: ServiceManagement.java,v 1.3 2007/01/05 11:33:56 dippadm Exp $
 */
public class ServiceManagement {
	
    // Get Logger for Class
	private static Logger log = Logger.getLogger(ServiceManagement.class);

	/**
	 * Constructor
	 */
	public ServiceManagement(){
	}

	/**
	 * <p><em>Title: Method prepares and ingests a new Object within Fedora</em></p>
	 * <p>Description: Method calls DOManagement methods to <em>prepare and create</em> 
	 * new Fedora Objects within the Fedora repository 
	 * (makes representations of digital objects persistent)</p>
	 * 
	 * @param aPIDNamespace <code>String</code> The namespace to which the PID of fedora Object 
	 * shall be bounded 
	 * @param aDigitalObject <code>DigitalObject</code> a representation of the Fedora Object 
	 * that will be generated
	 * @param aQDCmetadata <code>DublinCoreQualified</code> this object provides all qualified 
	 * DC Metadata required for the Fedora Object  
	 * @param isTest <code>String</code> ???
	 * @return <code>String</code> the PID of created Fedora Object
	 * @throws RemoteException 
	 */
	protected static synchronized String persistNewDigitalObject(String aPIDNamespace, DigitalObject aDigitalObject, DublinCoreQualified aQDCmetadata, boolean isTest)throws RemoteException{
		
		//TODO: evaluate which DiPP Objects use this Method and try to replace the if-statement by 
		// a strategy pattern. Introduce a strategy pattern for all types of DiPP Models.
		
		String pid = null;
		
		// make sure a label is set
		try{
		if (aDigitalObject.getLabel().length() == 0 ||
				aDigitalObject.getLabel().equals("")){
			throw new RemoteException("Label not set");
		}
		
		// create a DOManagement Instance for the new Fedora Object 
		DOManagement digObjMgt = new DOManagement();
		pid = digObjMgt.init(aPIDNamespace, aDigitalObject.getPid(), aDigitalObject.getLabel(), aDigitalObject.getCModel());		
		
		// Prepare a DigitalObject as a representation for the Fedora Object to be created
		// if ContentModel is DiPP:article add DiPPExt Datastream to DOManagement Instance
		if (aDigitalObject.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
			digObjMgt.addDatastreamToDigObj("DiPPExt", "DiPPExt record", "text/xml", "DiPPExt1.0", Metadata.createRepositoryExtendedMetadata(aDigitalObject),Constant.cFedoraMetadataClassTechnical);
			log.info("Pid: " + aDigitalObject.getPid() + " CM: " +  aDigitalObject.getCModel());
			//digObjMgt.addDatastreamToDigObj("DiPPExt", "DiPPExt record", "text/xml", "DiPPExt1.0", Metadata.createRepositoryExtendedMetadataContainer(aDigitalObject),Constant.cFedoraMetadataClassTechnical);
		}// Funktion für Artikel
		
		// if qualified DC Metadata are available add a them to DOManagement Instance and use this to 
		// add a DC Datastream
		if (aQDCmetadata != null){
			digObjMgt.addDatastreamToDigObj("QDC",    "DC Qualified record", "text/xml", "QDC1.0",    aQDCmetadata.getQualifiedDublinCore(), Constant.cFedoraMetadataClassDescriptive);
			digObjMgt.addDatastreamToDigObj("DC",     "DC record",           "text/xml", "DC1.0",     Metadata.createRepositoryDublinCoreSimplified(aDigitalObject, aQDCmetadata.getQualifiedDublinCore().getMetadata(), aQDCmetadata.getDCTypeList(), aQDCmetadata.getDdc(), isTest), Constant.cFedoraMetadataClassDescriptive);
		}
		
		// add DC Datastream but not a QDC Datastream
		else if(aPIDNamespace!= Constant.cPIDNamespaceOai){
			digObjMgt.addDatastreamToDigObj("DC", "DC record", "text/xml", "DC1.0", Metadata.createRepositoryDublinCoreSimplified(aDigitalObject, null, new Vector(), new Vector(), isTest), Constant.cFedoraMetadataClassDescriptive);
		}
		
		if(aPIDNamespace!= Constant.cPIDNamespaceOai){
			// add DiPPAdm Datastream to DOManagement Instance
			digObjMgt.addDatastreamToDigObj("DiPPAdm", "DiPPAdm record", "text/xml", "DiPPAdm1.0", Metadata.createRepositoryAdministrativeMetadata(aDigitalObject),Constant.cFedoraMetadataClassTechnical);
		}

		// new for Fedora2
		if (aDigitalObject.getRDF() != null){
			digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", aDigitalObject.getRDF().toXMLObject(), "");
		}

		// If a new Journal is introduced, a new OAI-Set is also required 
		// and will be generated in Fedora now
		if(aPIDNamespace == Constant.cPIDNamespaceOai){
			// add DiPPAdm Datastream to DOManagement Instance
			digObjMgt.addDatastreamToDigObj("SetInfo.xml", "OaiSetInformations", "text/xml", "SetInfo1.0", Metadata.createSetInfoObject(aDigitalObject), "");
			Metadata.createSetInfoObject(aDigitalObject);
		}// Funktion für OAI-Sets

		// and now... create this Object at Fedora Repository
		pid = digObjMgt.ingestFedoraObject("persist new digital object " + aDigitalObject.getMetaModel());
		if (!pid.equals(aDigitalObject.getPid())){
			throw new RemoteException("PID assignment exception");
		}
		
		//TODO: implement follower instances
		// ingest the formerly created Object in follower Repositories. followerRepositories should be a DiPP 
		// specific way to hold the same Object within different Fedora Repositories without care about the 
		// Fedora Repository Version  
		/*if(Constant.cConfiguration.getFollowerInstanceProperties()!= null){
			for(Properties followerProp : Constant.cConfiguration.getFollowerInstanceProperties()){
				//followerProp.
				//pid = digObjMgt.ingestFedoraObject("persist new digital object " + aDigitalObject.getMetaModel());
			}
		}*/

		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		log.info("PID: " + pid);
		return pid;
	}

	/**
	 * <p><em>Title: Create a Content Object within Fedora Repository</em></p>
	 * <p>Description: Method creates a new ContentObject in the Fedora Repository as 
	 * as Constituent of an Article Object.
	 * Method is used to assign Content Datastreams to an Article.</p>
	 * 
	 * <p><em>Hint:</em> This method was formerly situated in Metadata.java. 
	 * Due to its function it is placed here now. Unfortunately it is still called from a 
	 * method from Metadata.java. this should be corrected</p>
	 * 
	 * @param aLabel <code>String</code> the label (journal) the Object will be assigned to 
	 * @param aType <code>String</code><b>attention:</b> the Title (!) for the Content Object 
	 * which represents information what kind of Datastream(s) are included (html, xml, native,...)
	 * @param aParentObject <code>DigitalObject</code> the Article Object the Content Object is 
	 * assigned to. It is required for generate relations within DC-Datastream. 
	 * @return <code>String</code> the PID of Content Object
	 */
	public synchronized String createNewDataObject(String aLabel, String aType, DigitalObject aParentObject){
		//TODO: Change call from Metadata.java to call from ServiceManagement.java to make method private
		// and clarify mission of classes
		String pid = null;
		DOManagement digObjMgt = new DOManagement();
		
		try{
			// first create a DigitalObject as Representation of ContentObject and add default datastreams
			// to this representation
			pid = digObjMgt.init(Constant.cPIDNamespaceContent, null, aLabel, Constant.cContentModelData);		
			if (Constant.cFedoraVersion > 1){
				digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", Metadata.getRDF(pid, aParentObject), "");
			}
			digObjMgt.addDatastreamToDigObj("DC", "DC record", "text/xml", "DC1.0", Metadata.createDataObjectDC(aParentObject, aType), Constant.cFedoraMetadataClassDescriptive);
			
			// now create ContentObject in Fedora Repository
			digObjMgt.ingestFedoraObject("ingest of content object");

			// then add appropriate ContentModel to the newly created DigitalObject
			DigitalObject digObj = DOManagement.retrieveDigitalObject(pid);
			addRdfCM(digObj, Constant.cContentModelData);			
			byte[] dsContent = digObj.getRDF().toXMLObject().xmlText().getBytes("UTF-8");
			digObjMgt.modifyDatastream(pid, "RELS-EXT", "Relationships", "text/xml", dsContent, "added CM to Content Object");
			log.info("createDataObject: " + pid + ", of Type: " + aType + ", with ContentModel: " + Constant.cContentModelData);
			
		}catch(Exception remoteExc){
			remoteExc.printStackTrace();
		}
		return pid;
	}	
	

	/**
	   * <p>Checks, whether an article with a given source identifier already exists in the repository.</p>
	   *
	   * @return true, if an article already exists, false otherwise.
	   */
	public boolean existsArticle(String aSourceIdentifier){
		boolean exists = false;
		
		String[] resultFieldArray = {"pid"};
		
		SearchCondition sourceCondition = new SearchCondition();
		sourceCondition.setProperty("source");
		sourceCondition.setCondition(SearchCondition.cContains);
		sourceCondition.setValue(aSourceIdentifier);
		SearchCondition contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("cModel");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(Constant.cContentModelArticle);
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		conditions.add(sourceCondition);
		conditions.add(contentTypeCondition);
		try{
			DigitalObject[] objFields = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
			if (objFields.length == 1){
				exists = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return exists;
	}
	
	/**
	 * 
	 * @param aPID
	 * @param aLogMsg
	 */
	public String purgeObject(String aPID, String aLogMsg)throws RemoteException{
			// test aObjectPID on article or containertype
			String[] resultFieldArray = {"pid", "cModel", "label"};
			String returnMsg = "";
			
			SearchCondition contentTypeCondition = new SearchCondition();
			contentTypeCondition.setProperty("pid");
			contentTypeCondition.setCondition(SearchCondition.cContains);
			contentTypeCondition.setValue(aPID);
			List<SearchCondition> conditions = new Vector<SearchCondition>();
			conditions.add(contentTypeCondition);
			
			DigitalObject[] obj = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
			if (obj.length == 1){
				if (!(obj[0].getCModel().equals(Constant.cContentModelArticle) || 
						obj[0].getCModel().equals(Constant.cContentModelContainer) )){
					returnMsg = "purge operation for pid " + aPID + " not allowed";
					return returnMsg;
				}
			}else{
				returnMsg = "pid: " + aPID + " not found.\r\n";
				return returnMsg;
			}
			
			
			DigitalObject objDippAdm = getDiPPAdministrativeMetaData(aPID);
			if ( !objDippAdm.getChildList().isEmpty()){
				return "pid: " + aPID + " cannot deleted. It has " + objDippAdm.getChildList().size() + " childs";
			}
			
			Iterator<DigitalObject> itParent = objDippAdm.getParentList().iterator();
			while (itParent.hasNext()){
				DigitalObject parentAdm = (DigitalObject)itParent.next();
				 //getDiPPAdministrativeMetaData(parentID);
				objDippAdm.removeParentItem(parentAdm);
				parentAdm.removeChildItem(objDippAdm);
				try{
					updateDiPPAdministrativeMetaData(parentAdm);
				}catch(XmlException xmlExc){
					xmlExc.printStackTrace();
				}catch(IOException ioExc){
					ioExc.printStackTrace();
				}
			}
			
		try{
			returnMsg = FedoraManagement.getInstance().getFedoraServices().purgeObject(aPID, aLogMsg, false);
		}catch(RemoteException exc){
			exc.printStackTrace();
		}
		return returnMsg;
	}
	
	/**
	 * <p><em>Title: Returns the PID of the Journal</em></p>
	 * <p>Description: Method requests the Journal Pid via a given Label</p>
	 * 
	 * @param aLabelOfJournal <code>String</code> the Label of a Journal and its Articles
	 * @return <code>String</code> the Journal PID
	 */
	public String getPidOfJournal(String aLabelOfJournal){
		String pid = null;
		
		String[] resultFieldArray = {"pid", "cModel"};
		
		SearchCondition labelCondition = new SearchCondition();
		labelCondition.setProperty("label");
		labelCondition.setCondition(SearchCondition.cContains);
		labelCondition.setValue(aLabelOfJournal);
		SearchCondition contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("cModel");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(Constant.cContentModelJournal);
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		conditions.add(labelCondition);
		conditions.add(contentTypeCondition);
		try{
			DigitalObject[] objFields = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
			if (objFields.length == 1){
				DigitalObject objField = objFields[0];
				pid = objField.getPid();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pid;
	}
	
	/**
	 * 
	 * @param aDCDocument
	 * @param aQDCMetadata
	 */
	private void modifyDublinCoreSimplified(DcDocument aDCDocument, QualifiedDublinCore aQDCMetadata){
		OaiDcType dc = aDCDocument.getDc();
		for (int i = 0; i < dc.sizeOfSubjectArray(); i++){
			dc.removeSubject(i);
		}
		for ( int i = 0; i < aQDCMetadata.getDDC().length; i++){
			org.purl.dc.elements.x11.ElementType el = dc.addNewSubject();
			el.setStringValue("ddc:" + aQDCMetadata.getDDC(i));
		}
		for (int i = 0; i < dc.sizeOfTypeArray(); i++){
			dc.removeType(i);
		}
		for ( int i = 0; i < aQDCMetadata.getDocType().length; i++){
			org.purl.dc.elements.x11.ElementType el = dc.addNewType();
			el.setStringValue("doc-type:" + aQDCMetadata.getDocType(i));
		}
		for ( int i = 0; i < aQDCMetadata.getPubType().length; i++){
			org.purl.dc.elements.x11.ElementType el = dc.addNewType();
			el.setStringValue("pub-type:" + aQDCMetadata.getPubType(i));
		}
	}
	
	  /**
	   * <p>Sets whether the Document has been converted or not.</p>
	   *
	   * @param aPID A article PID.
	   * @param aConversionStatus conversion status.
	   * @throws IOException If the status cannot be set to the repository/database.
	   */
	public void setArticleConvertedStatus(String aPID, String aConversionStatus)throws IOException{
		try{
		DcDocument dcDoc = DcDocument.Factory.parse( getDatastream(aPID, "DC") );
		OaiDcType dc = dcDoc.getDc();
		if ( dc.getDescriptionArray() != null ){
			for (int i = 0; i < dc.getDescriptionArray().length; i++){
				dc.removeDescription(i);
			}
		}
		org.purl.dc.elements.x11.ElementType converted = dc.addNewDescription();
		converted.setStringValue(aConversionStatus);
		/*
		 * TODO marker for fedora-2.0
		 */
		String[] altIDs = null;
		boolean isVersionable = true;
		String mimeType = null;
		String formatURI = null;
		boolean force = false;
		FedoraManagement.getInstance().getFedoraServices().modifyDatastreamContent(aPID, "DC", altIDs, "DC record", isVersionable, mimeType, formatURI, dcDoc.xmlText().getBytes("UTF-8"), "A", "converted", "DEFAULT", null, force );
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
	}
	
/*	
	public boolean isPublished(String aArticlePID){
		
	}
*/	
	  /**
	   * <p>Sets the publishing state of the Document.</p>
	   *
	   * @param aArticlePID A article PID.
	   * @param aState active or inactive state of the document.
	   * @param aPublished publishing status.
	   * @return PID of changed Object or an empty string.
	   * @throws RemoteException, IOException If the status cannot be set to the repository/database.
	   * @throws XmlException If an error occurs in the xml-submission-format
	   * TODO set status in DiPP-Database, return value
	   */
	public String setPublishingState(String aArticlePID, boolean aState, boolean aPublished)
		throws RemoteException, IOException, XmlException{
		if (aState){
			
			
/*			
			// move this part into setPublishingState
			if (Constant.cFedoraVersion > 1 && 
					(aDigitalObject.getCModel().equals(Constant.cContentModelArticle) || aDigitalObject.getCModel().equals(Constant.cContentModelJournal))){
				digObjMgt.addDatastream(pid, "oai_dc", "oai_dc", "text/xml", Metadata.getDCXML(aQDCmetadata.getQualifiedDublinCore().getMetadata()), "add DS oai_dc", Constant.cFedoraDatastreamControlGroupInternal);
				digObjMgt.addDatastream(pid, "oai_epicur", "oai_epicur", "text/xml", Metadata.getEpicurXML(aQDCmetadata.getQualifiedDublinCore().getMetadata(), URNManagement.cEpicurUpdateStatus_URN_NEW), "add DS oai_epicur", Constant.cFedoraDatastreamControlGroupInternal);
			}
*/
			
			
				FedoraManagement.getInstance().getFedoraServices().modifyObjectState(aArticlePID, "A", null, "", "state changed");
				DcDocument dcDoc = DcDocument.Factory.parse( getDatastream(aArticlePID, "DC") );
				OaiDcType dc = dcDoc.getDc();
				if ( dc.getPublisherArray() != null ){
					if ( (aPublished && !dc.getPublisherArray(0).getStringValue().equals(Constant.cPublisherDippPublished)) ||
						 (!aPublished && !dc.getPublisherArray(0).getStringValue().equals(Constant.cPublisherDippUnPublished))){
						for (int i = 0; i < dc.getPublisherArray().length; i++){
							dc.removePublisher(i);
						}
					}
					else{
						return "";
					}
				}
				org.purl.dc.elements.x11.ElementType published = dc.addNewPublisher();
				if (aPublished){
					published.setStringValue(Constant.cPublisherDippPublished);						
				}else{
					published.setStringValue(Constant.cPublisherDippUnPublished);			
				}
				// Qa: DEFAULT and null for checksum arguments are required for Fedora Version 3. 
				// Do they work with Version 2 either ???
				// If not, it is maybe better to patch this in the Fedora 2 Adaptor then here?
				FedoraManagement.getInstance().getFedoraServices().modifyDatastreamContent(aArticlePID, "DC", null, "DC record", true, "text/xml", null, dcDoc.xmlText().getBytes("UTF-8"), "A", "published modified", "DEFAULT", null, false);
				if (Constant.cFedoraVersion == 2){
					DigitalObject digObj = DOManagement.retrieveDigitalObject(aArticlePID); 
					addRdfOAI(digObj, Metadata.getQualifiedDublinCoreMetadata(aArticlePID));
					try{
						ObjectRelations.persistModel(digObj, "added OAI-Item");
					}catch(Exception e){
						throw new RemoteException(e.getMessage());
					}
				}

		}else{
			log.info("State: Inactive for : " + aArticlePID);
			try{
				FedoraManagement.getInstance().getFedoraServices().modifyObjectState(aArticlePID, "I", null, "", "state changed");
			}catch(Exception e){
				e.printStackTrace();
				
			}			
		}
		
		return "";		
	}
	
	/**
	 * 
	 * @param aPID
	 * @return
	 */
	public String getArticleNativeDocumentFilename(String aPID){
		String filename = "";
		
			String[] resultFieldArray = {"identifier"};
			
			SearchCondition pidCondition = new SearchCondition();
			pidCondition.setProperty("pid");
			pidCondition.setCondition(SearchCondition.cContains);
			pidCondition.setValue(aPID);
			SearchCondition contentTypeCondition = new SearchCondition();
			contentTypeCondition.setProperty("cModel");
			contentTypeCondition.setCondition(SearchCondition.cContains);
			contentTypeCondition.setValue(Constant.cContentModelArticle);
			List<SearchCondition> conditions = new Vector<SearchCondition>();
			conditions.add(pidCondition);
			conditions.add(contentTypeCondition);
			try{
				DigitalObject[] objFields = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
				if (objFields.length == 1){
					String identifier = objFields[0].getIdentifier()[0]; 
					filename = identifier.substring(identifier.lastIndexOf("/") + 1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}		
			
		
		return filename;
	}
	
	
	/**
	 * updates RDFMetaData of referenced objects
	 */
	private void updateRDFRelationMetaData(DigitalObject aDigObj)throws RemoteException, Exception{
		ObjectRelations.mapRelation2RDF(aDigObj);
		Hashtable dsTable = FedoraManagement.getInstance().getFedoraServices().getDatastreamTable(aDigObj.getPid());
		DOManagement digObjMgt = new DOManagement();
		if (dsTable.containsKey("RELS-EXT")){
			digObjMgt.modifyDatastream(aDigObj.getPid(), "RELS-EXT", "Relationships", "text/xml", aDigObj.getRDF().toXMLObject().xmlText().getBytes("UTF-8") , "updated references");
		}else{
			digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", aDigObj.getRDF().toXMLObject(), "");
		}

	}
	
	/**
	 * updates AdmMetaData of referenced objects
	 */
	protected static void updateDiPPAdministrativeMetaData(DigitalObject aItem)throws IOException, XmlException{
		/*
		 * TODO method operates wrong 
		 */
		
		// get to Lists  of all referenced objects 
		Iterator<DigitalObject> itChild = aItem.getChildList().iterator();
		Iterator<DigitalObject> itParent = aItem.getParentList().iterator();

		// parse the DiPPAdm Datastream of the Object into XmlBeans representation
//		AdmMdDocument admDoc = AdmMdDocument.Factory.parse(FedoraManagement.getInstance().getFedoraServices().getDatastreamContent(aItem.getPid(), "DiPPAdm"));
		AdmMdDocument admDoc = AdmMdDocument.Factory.parse(FedoraAccess.getInstance().getFedoraServices().getDataStreamContent(aItem.getPid(), "DiPPAdm"));
		AdmMdDocument.AdmMd adm = admDoc.getAdmMd();
		
		// update child references
		// 1. remove <isParentOf/> Tag Array representation
		// this is may the problem of Jochens "method operates wrong" ?
		if (adm.isSetIsParentOf()){
			adm.unsetIsParentOf();
		}
		
		// 2. add new <isParentOf> Array to DiPPAdm representation
		if (!aItem.getChildList().isEmpty()){
			AdmMdDocument.AdmMd.IsParentOf child = adm.addNewIsParentOf();
			while (itChild.hasNext()){
				DigitalObject childItem = (DigitalObject)itChild.next();
//				System.out.println("updateDippAdm: aItemPID: add childID: " + childItem.getPID());
				Reference ref = child.addNewReference();
				ref.setItemID(childItem.getPid());
				if (childItem.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
					ref.setType(ElementType.Enum.forInt(ElementType.INT_CONTENT));			
				}else{
					ref.setType(ElementType.Enum.forInt(ElementType.INT_LOGIC));			
				}
			}
		}
		// update Parent references
		// 3. remove <isChildOf/> Tag Array representation
		// this is may the problem of Jochens "method operates wrong" ?
		if (adm.isSetIsChildOf()){
			adm.unsetIsChildOf();
		}

		// 4. add new <isChildOf> Array to DiPPAdm representation
		if (!aItem.getParentList().isEmpty()){
			AdmMdDocument.AdmMd.IsChildOf parent = adm.addNewIsChildOf();
			while (itParent.hasNext()){
				DigitalObject parentItem = (DigitalObject)itParent.next();
//				System.out.println("updateDippAdm: aItemPID: add parentID: " + parentItem.getPID());
				Reference ref = parent.addNewReference();
				ref.setItemID(parentItem.getPid());
				if (parentItem.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
					ref.setType(ElementType.Enum.forInt(ElementType.INT_CONTENT));			
				}else{
					ref.setType(ElementType.Enum.forInt(ElementType.INT_LOGIC));			
				}
			}
		}
		if (aItem.getMetaModel() instanceof CollectionModel) {
			CollectionModel cModel = (CollectionModel)aItem.getMetaModel() ;
			adm.setChunkURL(cModel.getChunkURL());
			adm.setAbsoluteURL(cModel.getAbsoluteURL());
		}
		
		//FedoraManagement.getInstance().getFedoraServices().modifyDatastreamContent(aItem.getPid(), "DiPPAdm", null, "DiPPAdm record", true, "text/xml", null, admDoc.xmlText().getBytes("UTF-8"), "A", "updated references", "", "", false);
		DOManagement domg = new DOManagement();
		try{
			domg.modifyDatastream(aItem.getPid(), "DiPPAdm", null, "text/xml", admDoc.xmlText().getBytes("UTF-8"), "updated references in DiPPAdm");
		}catch(Exception e){
			log.error(e);
		}
	}
	
	
	  /**
	   * <p>Adds a datastream to an object. The Method was created to take a Datastream identified 
	   * by a absolute Path and add this to a Fedora Object. Therefore a conversation from Path 
	   * to URL must have been implemented within the FedoraAdaptor. I'm not sure where and my 
	   * new Fedora 3 lacks this feature. Therefore this method implemetation is a workarround until
	   * all calling methods provide an url instead of path</p>
	   *
	   * @param aPID the pid of the object.
	   * @param aLocation external link or the name of the file to upload as datastream.
	   * @param aMimeType the mimetype of the file.
	   * @param aLabel a label of the new datastream.
	   * @return the datastream id.
	   * TODO should throw an exception
	   * 
	   * @deprecated use addDatastream Method from DOManagement
	   */
	public String addDataStream(String aPID, String aDSId, String aLocation, String aMimeType, String aLabel, String aControlgroup){
		/* Last implementation:
		String aLocationUrl = null;
		try{
			aLocationUrl = getUploadLocation(aLocation);
		}
		catch(Exception e){
			
		}
		log.info("aLocationURL: " + aLocationUrl);
		return FedoraManagement.getInstance().getFedoraServices().addDatastream(aPID, aDSId, aLocationUrl, aMimeType, aLabel, aControlgroup, "DEFAULT", null);
		*/
		log.error("method is out of use and does not work anymore");
		return null;
	}
	
	  /**
	   * <p>adds a datastream to an object.</p>
	   *
	   * @param aPID the pid of the object.
	   * @param aInputstream the content stream.
	   * @param aMimeType the mimeType of the data stream.
	   * @param aLabel a label of the new datastream.
	   * @return the datastream id.
	   * 
	   * @deprecated use addDatastream Method from DOManagement
	   */
	//TODO: Qa: clearify if this method is a duplicate from a DOManagement method  
	public String addDataStream(String aPID, String aDSId, InputStream aInputStream, String aMimeType, String aLabel, String aControlgroup){
		/*  Last implementation:
		//Qa: change this method to link it to that addDatastream Method that 
		// is directly linked to the addDatstream APIM Operation
		log.warn("This Method is deprecated, please implement addDatastream Method from DOManagement in your Code");
		DOManagement domg = new DOManagement();
		String aLocation = null;
		byte[] aDSContent = null;
		try{
			aDSContent = aInputStream.toString().getBytes("UTF-8");
			domg.addDatastream(aPID, aDSId, aLabel, aMimeType, aDSContent, "Added new DS", aControlgroup);
			return aPID;
		}catch(Exception e){
			log.error(e);
			return null;
		}*/
		log.error("method is out of use and does not work anymore");
		return null;
	}
	
	  /**
	   * <p>gets a datastream.</p>
	   *
	   * @param aPID the pid of the object.
	   * @param aDS the datastream ID.
	   * @return the datastreamcontent.
	   * @throws RemoteException, IOException If the status cannot be set to the repository/database.
	   */
	//TODO: Qa: clearify if this method is a duplicate from a DOManagement method  
	public InputStream getDatastream(String aPID, String aDS)
		throws RemoteException, IOException{
//		return FedoraManagement.getInstance().getFedoraServices().getDatastreamContent(aPID, aDS);
		return FedoraAccess.getInstance().getFedoraServices().getDataStreamContent(aPID, aDS);
	}
	
	/**
	 * <p>Title: </p>
	 * <p>Method returns a list of all datastream id's, associated with the Fedora Object 
	 * defined by given PID</p>
	 * 
	 * @param <code>String</code> aPID the pid of the object
	 * @return the datastream id list
	 * @throws RemoteException, IOException
	 */
	public Hashtable<String,DigitalObjectDatastream> getDatastreamTable(String aPID)
		throws RemoteException, IOException{
		return FedoraManagement.getInstance().getFedoraServices().getDatastreamTable(aPID);		
	}
	
	  /**
	   * <p>modifies a datastream content by value.</p>
	 * @param aPID the pid of the object.
	 * @param aDsID the datastream ID.
	 * @param aLabel the datastream Label.
	 * @param aLogMsg a Log Message.
	 * @param aDsContent the datastream content byte array.
	 * @param aDsState the datastream state.
	 * TODO should throw  RemoteException, IOException If the status cannot be set to the repository/database.
	 * TODO @param aMimeType 
	   */
/*	public void modifyDatastream(	String aPID, String aDsID, String aLabel, 
									String aLogMsg, byte[] aDsContent, String aDsState, String aMimeType){
		try{
			FedoraManagement.getInstance().getFedoraServices().modifyDatastreamContent(aPID, aDsID, null, aLabel, true, aMimeType, null, aDsContent, aDsState, aLogMsg, false );
		}catch(RemoteException remExc) {
			remExc.printStackTrace();
		}
		
	}
*/	
	  /**
	   * <p>modifies a datastream content by reference.</p>
	   *
	   * @param aPID the pid of the object.
	   * @param aDsID the datastream ID.
	   * @param aLabel the datastream Label.
	   * @param aLogMsg a Log Message.
	   * @param aDsContent the datastream content byte array.
	   * @param aDsState the datastream state.
	   * TODO should throw  RemoteException, IOException If the status cannot be set to the repository/database.
	   */
/*	public void modifyDatastreamByReference(String aPID, String aDsID, String aLabel, String aMimeType,
											String aLogMsg, byte[] aDsContent, String aDsState){
		try{
			FedoraManagement.getInstance().getFedoraServices().modifyDatastreamByReference(aPID, aDsID, null,aLabel, true, aMimeType, null, aDsContent, aDsState, aLogMsg, false);
		}catch(RemoteException remExc) {
			remExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		
	}
*/	
	  /**
	   * <p>modifies a datastream content by reference.</p>
	   *
	   * @param aPID the pid of the object.
	   * @param aDsID the datastream ID.
	   * @param aLabel the datastream Label.
	   * @param aLogMsg a Log Message.
	   * @param aDsContent the datastream content input stream.
	   * @param aDsState the datastream state.
	   * TODO should throw  RemoteException, IOException If the status cannot be set to the repository/database.
	   */
/*	public void modifyDatastreamByReference(String aPID, String aDsID, String aLabel, String aMimeType,
											String aLogMsg, InputStream aDsContent, String aDsState){
		try{
			FedoraManagement.getInstance().getFedoraServices().modifyDatastreamByReference( aPID, aDsID, null, aLabel, true, aMimeType, null, aDsContent, aDsState, aLogMsg, false);
		}catch(RemoteException remExc) {
			remExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		
	}
*/	
	
	  /**
	   * <p>sets the document metadata as qualified dublin core.</p>
	   *
	   * @param aArticlePID the pid of the object.
	   * @param aQDCMetadata the qualified dublin core metadata.
	   * TODO should throw  XMLException
	   */
	public void setQualifiedDublinCoreMetadata(String aArticlePID, QualifiedDublinCore aQDCMetadata){
		try{
			if (aQDCMetadata == null){
				throw new Exception("Parameter QDC ist null");
			}
			DublinCoreQualified dcQualified = new DublinCoreQualified();
			dcQualified.setQualifiedDublinCore(aQDCMetadata);
			try{
// JS, 23062006: why this ?:				getDatastream(aArticlePID, "QDC");
				DOManagement doManage = new DOManagement();
				doManage.modifyDatastream(aArticlePID, "QDC", "DC Qualified Record", "text/xml", dcQualified.getQualifiedDublinCore().xmlText().getBytes("UTF-8"), "modified");
//				modifyDatastream(aArticlePID, "QDC", "DC Qualified Record", "modified", dcQualified.getQualifiedDublinCore().xmlText().getBytes("UTF-8"), "A", "");
				DcDocument dcDoc = DcDocument.Factory.parse( getDatastream(aArticlePID, "DC") );
				modifyDublinCoreSimplified(dcDoc, aQDCMetadata);
				doManage.modifyDatastream(aArticlePID, "DC", "DC record", "text/xml", dcDoc.xmlText().getBytes("UTF-8"), "published modified");
//				modifyDatastream(aArticlePID, "DC", "DC record", "published modified", dcDoc.xmlText().getBytes("UTF-8"), "A", "");
			}catch(UnsupportedEncodingException exc){
				exc.printStackTrace();
			}
//	public void modifyDatastream(String aPid, String aDSId, String aLabel, String aMimeType, byte[] aDSContent, String aLogMessage)throws Exception{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * TODO should be private
	 * 27/04/2005: made synchronized cause creation of objects failed
	 */

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method requests the Fedora repository for an ArticleObject and 
	 * returns the informations provided by DiPPExt Datastream</p>
	 * 
	 * @param aArticlePID <code>String</code> representing an Article Object in Fedora 
	 * @return <code>ExtendedMetadata</code>
	 */
	public static synchronized ExtendedMetadata getDiPPExtendedMetadata(String aArticlePID){
		ExtendedMetadata metadata = null;
		
		try{
		Set<String[]> itemSet = FedoraAccess.getInstance().getFedoraServices().getItemIndex(aArticlePID);
		if (itemSet.contains("DiPPExt")){
			metadata = new ExtendedMetadata();

			//byte[] stream = FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(aArticlePID, "DiPPExt");
			// call DOManagement Method:	
			DOManagement domg = new DOManagement();
			byte[] stream = domg.getDatastream(aArticlePID, "DiPPExt");
			//				System.out.println(new String(stream, "UTF-8"));
				ExtMdDocument extMd = ExtMdDocument.Factory.parse(new String(stream, "UTF-8"));
				Content content = extMd.getExtMd().getContent();
				if (content != null){
					if (content.isSetHtml()){
						metadata.setObjectHTML(content.getHtml().getItemID());						
					}
					if (content.isSetMultimedia()){
						metadata.setObjectMultimedia(content.getMultimedia().getItemID());						
					}
					if (content.isSetOther()){
						metadata.setObjectOther(content.getOther().getItemID());						
					}
					if (content.isSetPdf()){
						metadata.setObjectPDF(content.getPdf().getItemID());						
					}
					if (content.isSetXml()){
						metadata.setObjectXML(content.getXml().getItemID());						
					}
					if (content.isSetNative()){
						metadata.setObjectNative(content.getNative().getItemID());						
					}
					if (content.isSetSupplementary()){
						metadata.setObjectSupplementary(content.getSupplementary().getItemID());
						/*
						 * TODO implement Content Supplementary
						 */
					}
				}
				}else{
					log.error("Item DiPPExt not found" );
				}
			}catch(RemoteException exc){
				exc.printStackTrace();
			}catch(UnsupportedEncodingException encodExc){
				encodExc.printStackTrace();
			}catch(XmlException xmlExc){
				xmlExc.printStackTrace();
			}
		return metadata;
	}
	
	//Qa: Method not in use?
	public int getSerialNumberOfJournal(String aPID)throws RemoteException{
		return ((DippJournal)getDiPPAdministrativeMetaData(aPID).getMetaModel() ).getSerialNumber();
	}
	
	/*
	 * TODO mv this method to Metadata class
	 */
	private DigitalObject getDiPPAdministrativeMetaData(String aPID)throws RemoteException{
		AdmMdDocument admDoc = null;
		try{
			log.info("getDippAdmMetadata for pid: " + aPID);
			admDoc = AdmMdDocument.Factory.parse(new String(FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(aPID, "DiPPAdm")));
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
		return getItem(admDoc.getAdmMd(), aPID);		
	}
	
	/*
	 * TODO mv this method to Metadata class or - better - replace with DigitalObect retrieve method
	 */
	private DigitalObject getItem(AdmMdDocument.AdmMd aAdm, String aPID) throws RemoteException{
		DigitalObject item = DOManagement.retrieveDigitalObject(aPID);
		
		if (aAdm.isSetIsChildOf()){
			Reference[] refArray = aAdm.getIsChildOf().getReferenceArray();
			for (int i = 0; i < refArray.length; i++){
				Reference ref = refArray[i];
				DigitalObject parent = new DigitalObject(ref.getItemID());
				if (ref.getType() == ElementType.Enum.forInt(ElementType.INT_LOGIC)){
					parent.setMetaModel(new DippContainer()); 
				}else{
					parent.setMetaModel(new DippArticle());
				}
				if (parent.getPid() == null || parent.getPid().equals("")){
					System.out.println("ServiceMng: error in getItem: parentItem for " + item.getPid() + " has no pid");
				}else{
					item.addParentItem(parent);
				}
			}			
		}
		if (aAdm.isSetIsParentOf()){
			Reference[] refArray = aAdm.getIsParentOf().getReferenceArray();
			for (int i = 0; i < refArray.length; i++){
				Reference ref = refArray[i];
				DigitalObject child = new DigitalObject(ref.getItemID());
				if (ref.getType() == ElementType.Enum.forInt(ElementType.INT_LOGIC)){
					child.setMetaModel(new DippContainer()); 
				}else{
					child.setMetaModel(new DippArticle());
				}
				if (child.getPid() == null || child.getPid().equals("")){
					System.out.println("ServiceMng: error in getItem: parentItem for " + item.getPid() + " has no pid");
				}else{
					item.addChildItem(child);
				}
			}
		}
		if (item.getMetaModel() instanceof CollectionModel) {
			CollectionModel cModel = (CollectionModel)item.getMetaModel() ;
			if (aAdm.isSetChunkURL()){
				cModel.setChunkURL(aAdm.getChunkURL());
			}
			if (aAdm.isSetAbsoluteURL()){
				cModel.setAbsoluteURL(aAdm.getAbsoluteURL());
			}
		}

		return item;		
	}
	
	/**
	 * <p><em>Title: create a new Journal entry in DiPPConfiguration.xml</em></p>
	 * <p>Description: </p>
	 * 
	 * @param zopeInstance
	 * @param setName
	 * @param setSpec 
	 */
	public void addJournalEntryToConfig(String pid, QualifiedDublinCore qdc, String zopeInstance, String setName, String setSpec){
		Properties journalProp = new Properties();
		DigitalObject digObj = null;
		
		try{
			digObj = DOManagement.retrieveDigitalObject(pid);
		}catch(Exception e){
			log.error(e);
		}
		
		journalProp.put("label", digObj.getLabel());
		journalProp.put("name", qdc.getTitle(0).getValue());
		journalProp.put("imageResize", "true");
		journalProp.put("oaiSetName", setName);
		journalProp.put("oaiSetSpec", setSpec);
		
		//Constant.cConfiguration
		//Config conf = new Config();
		Constant.cConfiguration.createZopeInstance(Constant.cFedoraName, zopeInstance, "blank");
		Constant.cConfiguration.createJournalEntry(Constant.cFedoraName, zopeInstance, journalProp);
		
		// generate a oai:setJournal Object for assign Journal articles to the appropriate OAI-Set 
		log.info("create an OaiSet for the Journal");
		createOaiSetObject(digObj);


	}
	
	/**
	 * <p><em>Title: Method creates a new Journal Object</em></p>
	 * 
	 * <p>Description: This Method prepare a representation of a new Journal Object. 
	 * Subsequently the new Journal Object will be stored in the Fedora Repository by 
	 * calling the persistNewDigitalObject Method</p>
	 * 
	 * <p>Hint: Method is called solely from the DiPP WS Implementation. 
	 * For testing this Method see 
	 * @link https://wiki.hbz-nrw.de:8443/display/DIP/Java+Testklassen+bei+DiPP</p>
	 * 
	 * @param aQDC <code>DublinCoreQualified</code> this object provides all qualified 
	 * DC Metadata required for the Fedora Object
	 * @param aPIDNameSpace <code>String</code> The namespace to which the PID of fedora Object 
	 * shall be assigned
	 * @return <code>String</code> the PID of new Journal
	 * @throws InvalidParamMessage
	 * @throws RemoteException 
	 */
	public synchronized String createNewJournal(QualifiedDublinCore aQDC, String aPIDNameSpace)throws InvalidParamMessage, RemoteException{
		DigitalObject digObj = null;
		

		// Create SearchCondition for objects with
		// ContentModel eJournal. Is needed for getting the label   
		List<SearchCondition> conditions = new Vector<SearchCondition>();

		// restrict search to CM: DiPP:eJournal
		SearchCondition condition = new SearchCondition();
		condition.setProperty("cModel");
		condition.setCondition(SearchCondition.cContains);
		condition.setValue(Constant.cContentModelJournal);
		conditions.add(condition);

		// Removed search for pid-namespace. 
		// If eJournals from different pid-namespaces exists 
		// a wrong label will be returned!
		// restrict search to the given namespace
		//condition = new SearchCondition();
		//condition.setProperty("pid");
		//condition.setCondition(SearchCondition.cContains);
		//condition.setValue(aPIDNameSpace + "*");
		//conditions.add(condition);
		
		DippJournal journal = new DippJournal();
		journal.setSerialNumber(DOManagement.retrieveDigitalObjects(conditions).length + 1);
		journal.setMetaName(journal.getSerialNumber() + "");
		journal.setMetaType(CollectionModel.cCollectionTypeJournal);
		log.info(journal.getSerialNumber());

		String[] title = new String[1];
		if (aQDC.getTitle() == null){
			throw new InvalidParamMessage("param title not set");
		}if (aQDC.getTitle().length == 0){			
			throw new InvalidParamMessage("param title not set");
		}else{
			title[0] = aQDC.getTitle()[0].getValue();
		}
		
		//create the new Journal within the correct Namespace
		try{
			String nextPID = null;
			boolean isTest = false;
			if (aPIDNameSpace.equals(Constant.cPIDNamespaceDipp)){
				if (title[0].equals(Constant.cTestJournalName)){				
					nextPID = DOManagement.getNextPID(Constant.cPIDNamespaceTest);
					isTest = true;
				}else{
					nextPID = DOManagement.getNextPID(Constant.cPIDNamespaceDipp);
				}				
			}else if (aPIDNameSpace.equals(Constant.cPIDNamespaceDipa) ||
					  aPIDNameSpace.equals(Constant.cPIDNamespaceDemo) ||
					  aPIDNameSpace.equals(Constant.cPIDNamespaceHBZ)){
				nextPID = DOManagement.getNextPID(aPIDNameSpace);
			}else{
				throw new InvalidParamMessage("pid namespace not supported");
			}

			// create a new representation as DigitalObject: 
			// TODO: Qa:
			// at this point we maybe better use the DigitalObject 
			// provided by fedoraXmlObject (if possible)? Nope: Digital Object 
			// here is not the same as in fedoraXmlObject :-( 
			digObj = new DigitalObject(nextPID);
			digObj.setLabel(journal.getSerialNumber() + "");
			digObj.setMetaModel(journal);
			digObj.setTitle(title);
			
			log.info("Create New Journal with PID: " + nextPID);
			log.info("Create New Journal with Label: " + journal.getSerialNumber());
			String urn = getURN(journal.getSerialNumber(), Integer.parseInt(nextPID.substring( (nextPID.lastIndexOf(":") + 1))));
			log.info("FedoraInterface createNewJournal for urn: " + urn);
			aQDC.setIdentifierURN(urn);
			DublinCoreQualified dcQualified = new DublinCoreQualified();
			dcQualified.setQualifiedDublinCore(aQDC);
			
			// Add RDF Statements for OAI-PMH and ContentModel (Fedora CMA)
			addRdfOAI(digObj, aQDC);
			addRdfCM(digObj, Constant.cContentModelJournal);

			// write new Journal Object into Fedora repository 
			// should be commented if method will be tested 
			persistNewDigitalObject(aPIDNameSpace, digObj, dcQualified, isTest);
			
		}catch(Exception exc){
			log.error(exc);
			exc.printStackTrace();
		}
		return digObj.getPid();
	}
	
	/**
	 * <p><em>Title: Create a RDF Statement that reflects the ContentModel</em></p>
	 * <p>Description: Method generates a RDF Statement for the ContentModel of Object</p>
	 * 
	 * @param <code>DigitalObject</code> aDigitalObject
	 * @param <code>String</code> the name of the ContentModel 
	 */
	private void addRdfCM(DigitalObject aDigitalObject, String cModelName){

		// Method works only for Fedora Version greater 3
		// TODO: Replace Constant.cFedoraVersion by something 
		// that can be changed by runtime 
		if(Constant.cFedoraVersion > 2){
			if (aDigitalObject.getRDF() == null){
				aDigitalObject.setRDF(new RDF(aDigitalObject.getPid()));
			}
			aDigitalObject.getRDF().addStatement(FedoraCMA.setContentModel, cModelName);
			log.debug("added cModel: " + cModelName + " to Object: " + aDigitalObject.getPid());
		}
	}

	/**
	 * <p><em>Title: Create RDF Statements for OAI-Sets</em></p>
	 * <p>Description: Method creates RDF Statements for oai:Set Objects. 
	 * The statements are required for exposing the Sets at OAI-PMH</p>
	 * 
	 * @param aDigitalObject 
	 */
	private void addRdfOaiSet(DigitalObject aDigitalObject){

		if (aDigitalObject.getRDF() == null){
			aDigitalObject.setRDF(new RDF(aDigitalObject.getPid()));
		}
		String[] setName = aDigitalObject.getTitle();
		String[] setSpec = aDigitalObject.getDescription();
		
		aDigitalObject.getRDF().addProperty(OAI.setName, setName[0]);
		aDigitalObject.getRDF().addProperty(OAI.setSpec, setSpec[0]);
	}

	/**
	 * <p><em>Title: Create RDF Statements for OAI-PMH</em></p>
	 * <p>Description: This Method creates RDF Statements that assigns article Objects to the 
	 * appropriate OAI-PMH Sets</p>
	 * 
	 * @param aDigitalObject
	 * @param aQDC 
	 */
	private void addRdfOAI(DigitalObject aDigitalObject, QualifiedDublinCore aQDC){
		String query = "* <http://www.openarchives.org/OAI/2.0/setSpec> *";
		RIsearch riSearch = new RIsearch(RIsearch.cType_Triples, RIsearch.cFormat_rdf_xml, RIsearch.cLang_Triples_spo, query);

		String[] ddc = aQDC.getDDC();
		String[] docType = aQDC.getDocType();
		String[] pubType = aQDC.getPubType();
		if (aDigitalObject.getRDF() == null){
			aDigitalObject.setRDF(new RDF(aDigitalObject.getPid()));
		}
		aDigitalObject.getRDF().addProperty(OAI.itemID, "oai:dipp.nrw.de:" + aDigitalObject.getPid());
		for (int i = 0; i < ddc.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, riSearch.getSubjectID(OAI.setSpec, "ddc:" + ddc[i]));
		}
		for (int i = 0; i < docType.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, riSearch.getSubjectID(OAI.setSpec, "doc-type:" + docType[i]));
		}
		for (int i = 0; i < pubType.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, riSearch.getSubjectID(OAI.setSpec, "pub-type:" + pubType[i]));
		}		
	}

	private void createOaiSetObject(DigitalObject digObj){
		String journalLabel = digObj.getLabel();
		
		// Update cConfiguration at runtime for adding new Journal-Informations into runtime system
		Constant.cConfiguration = new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName));
		String setName = Constant.cConfiguration.getOaiSetName(journalLabel);
		String setSpec = Constant.cConfiguration.getOaiSetSpec(journalLabel);
		log.info("SetName: " + setName);
		DigitalObject setObj = new DigitalObject("oai:SetJournal" + setName);
		String[] label = digObj.getTitle();
		setObj.setLabel("Journal " + label[0]);
		OaiSet oaiModel = new OaiSet();
		setObj.setMetaModel(oaiModel);
		String[] description = new String[]{setSpec};
		setObj.setDescription(description);
		String[] title = new String[]{setName};
		setObj.setTitle(title);

		// Create the neccesary RDF Statements for a Set Object
		addRdfOaiSet(setObj);
		addRdfCM(setObj, Constant.cContentModelOaiSet);
		try{
			persistNewDigitalObject("oai", setObj, null, true);
		}catch(Exception e){
			log.error(e);
		}
	}

	/*
	 * update of a document to convert
	 */
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aArticleID
	 * @param aConvertDocURL
	 * @param aTargetFormatArray
	 * @return
	 * @throws RemoteException 
	 */
	public String convert(String aArticleID, String aConvertDocURL, String[] aTargetFormatArray)throws RemoteException{
		/*
		 * TODO make a separate thread, cause download-stream could be big
		 */
		try{
			ConversionStatus convStatus = new ConversionStatus();
			FileUtil fileUtil = new FileUtil();
			fileUtil.create(aArticleID);
			Download dw = new Download(aConvertDocURL);
			dw.downloadToFile(fileUtil.getNativeFilename());
			fileUtil.setNativeFileMimeType(de.nrw.dipp.dippCore.util.request.MimeType.getInstance().getMimeType(fileUtil.getNativeFile()));
			ExtendedMetadata extMeta = getDiPPExtendedMetadata(aArticleID);
			String pid = null;
			if (fileUtil.getNativeFileMimeType().equals("application/rtf") || fileUtil.getNativeFileMimeType().equals("text/rtf")){
				// aktualisiere nativeDoc
				pid = extMeta.getObjectNative();
			}else if (fileUtil.getNativeFileMimeType().equals("text/xml")){
				pid = extMeta.getObjectXML();
			}else{
				throw new RemoteException("Dokumentformat wird für die Konvertierung nicht unterstützt");
			}
			String label = fileUtil.getNativeFilename().substring(fileUtil.getNativeFilename().lastIndexOf("/") + 1);
			
			DOManagement domg = new DOManagement();
			try{
				domg.addDatastream(pid, "DS1", fileUtil.getNativeFilename(),  fileUtil.getNativeFileMimeType(), label, Constant.cFedoraDatastreamControlGroupManaged);
				log.info("Added DS1 to PID: " + pid );
			}catch(Exception e){
				log.error(e);
			}

			DigitalObject articleObj = DOManagement.retrieveDigitalObject(aArticleID);

			Param task = new Param();
			task.setArticlePID(aArticleID);
			task.setJournalLabel(articleObj.getLabel());
			task.setDoModify(true);
			task.setContentObjectPID(pid);
			task.setContentObjectDS("DS1");
			task.setConversionStatus(convStatus);
			task.setExtMetadata(extMeta);
			task.setFileUtil(fileUtil);
			convStatus.addBit(ConversionStatus.cFlagConversion);
			TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskXML, task, true);
			convStatus.addBit(ConversionStatus.cFlagXML);
			TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskHTML, task, false);
			convStatus.addBit(ConversionStatus.cFlagHTML);
			TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskPlone, task, false);
		}catch(IOException ioExc){
			throw new RemoteException(ioExc.getMessage());
		}
		return "";
	}
	
	/**
	 * <p><em>Title: Method returns an URN for a newly created Object (Article/eJournal)</em></p>
	 * <p>Description: Method gives back an URN composed by the DiPP-Namespace, 
	 * the serial number (label) of the eJournal and the PID of the Object. It 
	 * additionally calls the URNGenerator Class for getting a checksum.</p>
	 * 
	 * @param aSerialNumberJournal
	 * @param <code>int</code> aPIDinteger the PID without its namespace as integer
	 * @return <code>String</code> an URN according DNB specifications 
	 */
	protected static synchronized String getURN(int aSerialNumberJournal, int aPIDinteger){
		String urn = Constant.cNamespaceURN + aSerialNumberJournal + "-" + aPIDinteger;
		URNGenerator urnGen = new URNGenerator();
		urnGen.setURN(urn);
		urn = urn + urnGen.getChecksum();
		return urn;
	}

	
	  /**
	   * <p>Creates a Article Object for institutional Repository.</p>
	   * <p>very beta - only for test purposes</p>
	   * @param aContainerIDArray IDs referencing container objects on which the article is a child of.
	   * @param aJournalID the PID of the journal.
	   * @param aQDC qualified dublin core metadata of the object.
	   * @param aNativeDocIdent a URI of the document, should contain an extension to determine the mimeType.
	   * @param aStorageType is obsolet.
	   * @param aTargetFormat should be empty, but not null.
	   * @return PID of the created object.
	   * @throws InvalidParamMessage, RemoteException
	   * TODO should return the article Object, more abstract
	   */
	private synchronized String _institutionalRep_newArticle(	java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			String aNativeDocIdent, String aStorageType,
			String[] aTargetFormat)
		throws InvalidParamMessage, RemoteException{
		String pid = null;
		try{
			FileUtil fileUtil = new FileUtil();
			fileUtil.create(null);
			Download dw = new Download(aNativeDocIdent);
			dw.downloadToFile(fileUtil.getNativeFilename());
			fileUtil.setNativeFileMimeType(de.nrw.dipp.dippCore.util.request.MimeType.getInstance().getMimeType(fileUtil.getNativeFile()));
			
//			String label = fileUtil.getNativeFilename().substring(fileUtil.getNativeFilename().lastIndexOf("/") + 1);
			String label = aNativeDocIdent.substring(aNativeDocIdent.lastIndexOf("/") + 1);
			
			DublinCoreQualified dcQualified = new DublinCoreQualified();
			
			DippArticle txt = new DippArticle();
			DippContent nativeContent = new DippContent();
			txt.addContentObject(Constant.cContentTypeNative, nativeContent);
			nativeContent.setMimeType(fileUtil.getNativeFileMimeType());
			txt.setIdentifierNativeDoc(aNativeDocIdent);
			
			if (aQDC.getBibliographicCitation() == null){

				throw new InvalidParamMessage("the bibliographic citation is missing");				
			}else{
/*				Citation bibCitation = aQDC.getBibliographicCitation()[0];
				System.out.println( "Date: " + bibCitation.getJournalIssueDate() + 
						" Number: " + bibCitation.getJournalIssueNumber() +
						" Title: " + bibCitation.getJournalTitle() + 
						" Volume: " + bibCitation.getJournalVolume());
*/				
			}
			
			if (aQDC.getLanguage() == null){
				throw new InvalidParamMessage("the language parameter is missing");
			}else if (aQDC.getLanguage().length == 0){
				throw new InvalidParamMessage("the language parameter is missing");				
			}
			
			if (aQDC.getIdentifier() != null){
				if (aQDC.getIdentifier().length > 0){
					txt.setIdentifierSource(aQDC.getIdentifier(0));	
				}
			}
			if (aQDC.getSource() != null){
				if (aQDC.getSource().length > 0){
					txt.setIdentifierSource(aQDC.getSource(0));	
				}
			}
			txt.setMetaType(CollectionModel.cCollectionTypeArticle);
			txt.setConversionStatus(Constant.cDecsriptionDippNoConversion);
			txt.setLocked(true);

			String[] title = new String[1];
			if (aQDC.getTitle() == null){
				throw new InvalidParamMessage("param title not set");
			}if (aQDC.getTitle().length == 0){			
				throw new InvalidParamMessage("param title not set");
			}else{
				txt.setMetaName(aQDC.getTitle()[0].getValue());
				title[0] = aQDC.getTitle()[0].getValue();
			}

			txt.setNew(true);
			String articlePID = DOManagement.getNextPID(Constant.cPIDNamespaceDipa);
			
			DigitalObject digObj = new DigitalObject(articlePID);
			digObj.setTitle(title);
			digObj.setMetaModel(txt);

			for (int i = 0; i  < aContainerIDArray.length; i++){
				DigitalObject parentDippAdm = getDiPPAdministrativeMetaData(aContainerIDArray[i]);
//				CollectionModel parent = new DippContainer();
//				parentDippAdm.setPid(aContainerIDArray[i]);
				digObj.addParentItem(parentDippAdm);
				parentDippAdm.addChildItem(digObj);
				updateDiPPAdministrativeMetaData(parentDippAdm);
			}
			//System.out.println("FedoraInterface: createNewArticle: dippAdm updated");

			DigitalObject journalObj = DOManagement.retrieveDigitalObject(aJournalID);
			boolean isTest = false;
			// preset of the pid
			digObj.setTitle(title);
			digObj.setMetaModel(txt);
			digObj.setLabel(journalObj.getLabel());
			//System.out.println("FedoraInterface: createNewArticle: got nextPID");

			dcQualified.setQualifiedDublinCore(aQDC);

			//System.out.println("FedoraInterface: createNewArticle: set qdc");
			pid = persistNewDigitalObject(aJournalID.substring(aJournalID.indexOf(":")), digObj, dcQualified, isTest);
			updateDiPPAdministrativeMetaData(digObj);

			//System.out.println("FedoraInterface: createNewArticle: dippAdm updated");
				
				ExtendedMetadata extMeta = getDiPPExtendedMetadata(pid);
				log.debug("FedoraInterface: " + fileUtil.getNativeFile().getAbsolutePath());
				log.debug("FedoraInterface: " + fileUtil.getNativeFilename());
				// Qa: I leave the next line untouched for the moment due to the "not in use state" of this hole method
				DOManagement domg = new DOManagement();
				try{
					domg.addDatastream(extMeta.getObjectNative(), "DS", fileUtil.getNativeFilename(),  txt.getContentObject(Constant.cContentTypeNative).getMimeType(), label, Constant.cFedoraDatastreamControlGroupManaged);
				}catch(Exception e){
					log.error(e);
				}
				//addDataStream(extMeta.getObjectNative(), "DS", fileUtil.getNativeFilename(), txt.getContentObject(Constant.cContentTypeNative).getMimeType(), label, Constant.cFedoraDatastreamControlGroupManaged);
				
				
			}catch(IOException ioExc){
				ioExc.printStackTrace();
			}catch(XmlException xmlExc){
				xmlExc.printStackTrace();
			}

		return pid;
	}
	
	/**
	 * <p><em>Title: Method delegates Article Object creation to different methods</em></p>
	 * <p>Description:  Method creates a new Article in Fedora by delegation to 
	 * other Method according the JournalID namespace.  
	 * It either calls the next createNewArticle Method (see below) 
	 * or the _institutionalRep_newArticle Method</p>
	 * <p><em>Hint: not clear for what purposes the _institutionalRep_newArticle Method was 
	 * created. Maybe delegations to this Method are obsolete now?</em></p>
	 * 
	 * @param aContainerIDArray <code>String[]</code> an Array of String providing all 
	 * IDs that reference container objects on which the article is a child of (eg. Volumes,...).
	 * @param aJournalID <code>String</code> the PID of the journal the article 
	 * is assigned to.
	 * @param aQDC <code>QualifiedDublinCore</code> the qualified dublin core metadata of the 
	 * object, represented by the autogenerated QualifiedDublinCore object and not 
	 * by a DublinCoreQuailified object.
	 * @param aNativeDocIdent
	 * @param aStorageType <code>String</code> defines what type of conversion 
	 * @param aTargetFormat <code>String[]</code> an Array of String defines the Formats 
	 * to which the native Document should be converted. 
	 * @return <code>String</code> the PID of the newly created Article Object
	 * @throws InvalidParamMessage
	 * @throws RemoteException 
	 */
	public synchronized String createNewArticle(	java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			String aNativeDocIdent, String aStorageType,
			String[] aTargetFormat)
	throws InvalidParamMessage, RemoteException{
				
		String pid = "";
		if (aJournalID.startsWith("dipa")){
			pid = _institutionalRep_newArticle(aContainerIDArray, aJournalID, aQDC, aNativeDocIdent, aStorageType, aTargetFormat);
		}else{
			String label = aNativeDocIdent.substring(aNativeDocIdent.lastIndexOf("/") + 1); // does "/" really exists ?
			pid = createNewArticle(aContainerIDArray, aJournalID, aQDC, aNativeDocIdent, aStorageType, aTargetFormat, label);		
			log.info("the pid returned: " + pid);
			log.info("Url as native Document Identifier: " + aNativeDocIdent);
			log.info("The Filename solely: " + label);
		}
		return pid;
	}
	
	/**
	 * <p><em>Title: Method calls the processNewArticleMethod to create a new Fedora Article Object</em></p>
	 * <p>Description: Method differs from the former createNewArticle Method in three ways. 
	 * It lacks the delegation to different method, instead it calls the processNewArticle Method
	 * directly. It additionally requires a Label as parameter. The NativeDocIdent (a Path to a File)
	 * will be converted into a FileUtil Object</p>
	 * 
	 * @param aContainerIDArray <code>String[]</code> an Array of String providing all 
	 * IDs that reference container objects on which the article is a child of (Volumes,...)
	 * @param aJournalID <code>String</code> the PID of the journal the article 
	 * is assigned to.
	 * @param aQDC <code>QualifiedDublinCore</code> the qualified dublin core metadata of the 
	 * object, represented by the autogenerated QualifiedDublinCore object and not 
	 * by a DublinCoreQuailified object.
	 * @param aNativeDocIdent
	 * @param aStorageType <code>String</code> defines what type of conversion 
	 * @param aTargetFormat <code>String[]</code> an Array of String defines the Formats 
	 * to which the native Document should be converted. 
	 * @param aLabel
	 * @return
	 * @throws InvalidParamMessage
	 * @throws RemoteException 
	 */
	public synchronized String createNewArticle(	java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			String aNativeDocIdent, String aStorageType,
			String[] aTargetFormat, String aLabel)
		throws InvalidParamMessage, RemoteException{
		
		String pid = "";		
		FileUtil fileUtil = new FileUtil();
		fileUtil.create(null);
		fileUtil.setNativeDocIdent(aNativeDocIdent);
		Download dw = new Download(aNativeDocIdent);
		
		try{
			dw.downloadToFile(fileUtil.getNativeFilename());
			pid = processNewArticle(aContainerIDArray, aJournalID, aQDC, fileUtil, aStorageType, aTargetFormat, aLabel);
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		return pid;
		//return "nexttest";
	}
	
	/**
	 * 
	 * create external referenced Article - with link(s) to document formats
	 * @param aContainerIDArray
	 * @param aJournalID
	 * @param aQDC
	 * @param aExternalLinks - key document format, value link
	 * @param aLabel
	 * @return
	 * @throws InvalidParamMessage
	 * @throws RemoteException
	 * 
	 * Method not in use?
	 */
	public synchronized String createNewArticle(	java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			Properties aExternalLinks, String aLabel)
		throws InvalidParamMessage, RemoteException{
		String pid = "";		
		try{
			pid = processNewExternalManagedArticle(aContainerIDArray, aJournalID, aQDC, aExternalLinks, aLabel);
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		return pid;
	}

	
	/**
	 * <p><em>Title: Method creates an Article Object as </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aContainerIDArray
	 * @param aJournalID
	 * @param aQDC
	 * @param aNativeDocInputStream
	 * @param aStorageType
	 * @param aTargetFormat
	 * @param aLabel
	 * @return
	 * @throws InvalidParamMessage
	 * @throws RemoteException
	 *  
	 * Method not in use?
	 */
	public synchronized String createNewArticle(	java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			InputStream aNativeDocInputStream, String aStorageType,
			String[] aTargetFormat, String aLabel)
		throws InvalidParamMessage, RemoteException{
		
		String pid = "";		
		FileUtil fileUtil = new FileUtil();
		fileUtil.create(null);
		fileUtil.setNativeDocIdent(Constant.cDefaultFilename);
		try{
			fileUtil.writeStream(aNativeDocInputStream);
			pid = processNewArticle(aContainerIDArray, aJournalID, aQDC, fileUtil, aStorageType, aTargetFormat, Constant.cDefaultFilename);
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		return pid;
	}
	
	protected static String getPIDNamespace(String aPID){
		String namespace = "";
		if (aPID.indexOf(":") != -1){
			namespace = aPID.substring(0, aPID.indexOf(":"));
		}
		return namespace;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aContainerIDArray
	 * @param aJournalID
	 * @param aQDC
	 * @param aExternalLinks
	 * @param aLabel
	 * @return
	 * @throws InvalidParamMessage
	 * @throws RemoteException 
	 */
	private synchronized String processNewExternalManagedArticle(
			java.lang.String[] aContainerIDArray, 
			java.lang.String aJournalID, 
			de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
			Properties aExternalLinks,
			String aLabel)
	throws InvalidParamMessage, RemoteException{
		
		String pid = null;
		ConversionStatus convStatus = new ConversionStatus();		
		DublinCoreQualified dcQualified = new DublinCoreQualified();
		DOManagement domg = new DOManagement();
		
		DippArticle txt = new DippArticle();
//		DippContent contentObject = new DippContent();
//		txt.addContentObject(Constant.cContentTypeNative, contentObject);
//		contentObject.setMimeType(mimeType); //fileUtil.getNativeFileMimeType());
//		txt.setIdentifierNativeDoc(aFileUtil.getNativeDocIdent());
		
		if (aQDC.getBibliographicCitation() == null){
			throw new InvalidParamMessage("the bibliographic citation is missing");				
		}else{
			Citation bibCitation = aQDC.getBibliographicCitation()[0];
			System.out.println( "Date: " + bibCitation.getJournalIssueDate() + 
					" Number: " + bibCitation.getJournalIssueNumber() +
					" Title: " + bibCitation.getJournalTitle() + 
					" Volume: " + bibCitation.getJournalVolume());
		}
		
		if (aQDC.getLanguage() == null){
			throw new InvalidParamMessage("the language parameter is missing");
		}else if (aQDC.getLanguage().length == 0){
			throw new InvalidParamMessage("the language parameter is missing");				
		}
		
		if (aQDC.getIdentifier() != null){
			if (aQDC.getIdentifier().length > 0){
				txt.setIdentifierSource(aQDC.getIdentifier(0));	
			}
		}
		if (aQDC.getSource() != null){
			if (aQDC.getSource().length > 0){
				txt.setIdentifierSource(aQDC.getSource(0));	
			}
		}
		txt.setMetaType(CollectionModel.cCollectionTypeArticle);
		txt.setConversionStatus(convStatus.getBitMask()); //Constant.cDecsriptionDippNoConversion);					
		txt.setLocked(true);

		String[] title = new String[1];
		if (aQDC.getTitle() == null){
			throw new InvalidParamMessage("param title not set");
		}if (aQDC.getTitle().length == 0){			
			throw new InvalidParamMessage("param title not set");
		}else{
			txt.setMetaName(aQDC.getTitle()[0].getValue());
			title[0] = aQDC.getTitle()[0].getValue();
		}

		txt.setNew(true);

		String articlePID = null;
		DigitalObject journalObj = DOManagement.retrieveDigitalObject(aJournalID);
		int serialNumber = Integer.parseInt(journalObj.getLabel());
		String titleOfJournal = journalObj.getTitle()[0];
		boolean isTest = false;
		String pidNamespace = getPIDNamespace(aJournalID);
		if (titleOfJournal.equals(Constant.cTestJournalName)){
			articlePID = DOManagement.getNextPID(Constant.cPIDNamespaceTest);
			isTest = true;
		}else{
			articlePID = DOManagement.getNextPID(pidNamespace);
		}
		DigitalObject digObj = new DigitalObject(articlePID);
		digObj.setTitle(title);
		digObj.setMetaModel(txt);
		try{
			for (int i = 0; i  < aContainerIDArray.length; i++){
				DigitalObject parentDippAdm = getDiPPAdministrativeMetaData(aContainerIDArray[i]);
				digObj.addParentItem(parentDippAdm);
				parentDippAdm.addChildItem(digObj);
				updateDiPPAdministrativeMetaData(parentDippAdm);
			}

			if (Constant.cConfiguration.hasWorkflowStep(serialNumber + "", Constant.cWfStepURN)){
				String urn = "";
				urn = getURN(serialNumber, Integer.parseInt(articlePID.substring( (articlePID.lastIndexOf(":") + 1))));					
				aQDC.setIdentifierURN(urn);
				System.out.println("FedoraInterface: createNewArticle: set URN: " + urn);					
			}else{
				System.out.println("FedoraInterface: createNewArticle: article gets no URN");
			}
				
			dcQualified.setQualifiedDublinCore(aQDC);

			// preset of the pid
			digObj.setLabel(journalObj.getLabel());
			if (Constant.cFedoraVersion > 1){
				try{
					ObjectRelations.mapRelation2RDF(digObj);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if (aExternalLinks.containsKey("html")){digObj.getContentTypeList().add("html");}
			if (aExternalLinks.containsKey("pdf")){digObj.getContentTypeList().add("pdf");}
			pid = persistNewDigitalObject(articlePID.substring(0,articlePID.indexOf(":")), digObj, dcQualified, isTest);
			updateDiPPAdministrativeMetaData(digObj);			
		}catch(IOException e){
			
		}catch(XmlException e){}


		ExtendedMetadata extMeta = getDiPPExtendedMetadata(pid);
		
		String fileName = null;
		if (aExternalLinks.containsKey("html")){
				fileName = aExternalLinks.getProperty("html");
		}
		if(aExternalLinks.containsKey("pdf")){
				fileName = aExternalLinks.getProperty("pdf");
		}
		try{
			domg.addDatastream(extMeta.getObjectNative(), null, fileName,  txt.getContentObject(Constant.cContentTypeNative).getMimeType(), "srcdoc_" + aLabel, Constant.cFedoraDatastreamControlGroupManaged);
			log.info("method addDatastream from DOManagement called and finished");
		}catch(Exception e){
			log.error(e);
		}

		/*String journalURL = Constant.cConfiguration.getIdentifier(serialNumber + "");
		if (aExternalLinks.containsKey("html")){
			addDataStream(extMeta.getObjectHTML(), "ExtLnk", aExternalLinks.getProperty("html"), "text/html", aLabel, Constant.cFedoraDatastreamControlGroupExternal);
		}
		if (aExternalLinks.containsKey("pdf")){
			addDataStream(extMeta.getObjectPDF(), "ExtLnk", aExternalLinks.getProperty("pdf"), "application/pdf", aLabel, Constant.cFedoraDatastreamControlGroupExternal);
		}*/
		
		return pid;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method managing and starts document converting process for Articles. 
	 * Subsequently it adds the converted documents into DiPP:Content Fedora Objects and 
	 * assign them to newly generated DiPP Artice Objects</p>
	 * 
	 * @param aContainerIDArray <code>String[]</code> an Array of String providing all 
	 * IDs that reference container objects on which the article is a child of (Volumes,...).
	 * @param aJournalID <code>String</code> the PID of the journal the article 
	 * is assigned to.
	 * @param aQDC <code>QualifiedDublinCore</code> the qualified dublin core metadata of the 
	 * object, represented by the autogenerated QualifiedDublinCore object and not 
	 * by a DublinCoreQuailified object.
	 * @param aFileUtil <code>FileUtil</code> a Wrapper for File Objects representing the Article Document
	 * in different ways.
	 * @param aStorageType <code>String</code> defines what type of conversion 
	 * @param aTargetFormat <code>String[]</code> an Array of String defines the Formats 
	 * to which the native Document should be converted. 
	 * Supports html and pdf actually?  
	 * @param aLabel <code>String</code> a label for the newly generated Content Object 
	 * can be either the original filename ("srcdoc_" will be added ) or?  
	 * @return PID <code>String</code> PID of the created object.
	 * @throws InvalidParamMessage
	 * @throws RemoteException 
	 */
	private synchronized String processNewArticle(	java.lang.String[] aContainerIDArray, 
									java.lang.String aJournalID, 
									de.nrw.dipp.dippCore.webservice.QualifiedDublinCore aQDC,
									FileUtil aFileUtil, String aStorageType,
									String[] aTargetFormat, String aLabel)
		throws InvalidParamMessage, RemoteException{
		//TODO:  This method is programmed in a very procedural manner, need an idea for making it better 
		
		String mimeType = null;
		String pid = null;
		boolean doConvert = false;
		ConversionStatus convStatus = new ConversionStatus();
		DOManagement domg = new DOManagement();
		DublinCoreQualified dcQualified = new DublinCoreQualified();

		log.debug("start creation of Article and its associated Objects in Fedora");
		
		// First step: get mimeType from native File
		mimeType = de.nrw.dipp.dippCore.util.request.MimeType.getInstance().getMimeType(aFileUtil.getNativeFile());

		// if file is a compressed file, unzip file and determine the main file
		if (mimeType.equals("application/zip")){
			try{
				aFileUtil.unzip();
			}catch(IOException IOExc){
				log.error(IOExc);
			}
			aFileUtil.declareMainFile();
			aFileUtil.setNativeFileMimeType(de.nrw.dipp.dippCore.util.request.MimeType.getInstance().getMimeType(aFileUtil.getNativeFile()));
		}else{
			aFileUtil.setNativeFileMimeType(mimeType);
		}
		
		// if a TargetFormat is given, evaluate if Format can be converted via Upcast and set
		// doConvert to true
		if (aTargetFormat != null ){
			if (aTargetFormat.length > 0){
				if (! (	aFileUtil.getNativeFileMimeType().equals("application/rtf") 	|| 
						aFileUtil.getNativeFileMimeType().equals("text/rtf") 		||
						aFileUtil.getNativeFileMimeType().equals("text/xml") ) ){
					throw new RemoteException("Das verwendete Dokumentenformat " + aFileUtil.getNativeFileMimeType() 
							+ " wird für die Konvertierung nicht unterstützt");
				}
				doConvert = true;						
			}
		}
		
		// Second step: create a 2 new ContentModel Objects (DippArticle, DippContent)
		// add ContentObject to DippArticleObject
		DippArticle dippArtObj = new DippArticle();
		DippContent contentObject = new DippContent();
		dippArtObj.addContentObject(Constant.cContentTypeNative, contentObject);
		contentObject.setMimeType(mimeType); //fileUtil.getNativeFileMimeType());
		dippArtObj.setIdentifierNativeDoc(aFileUtil.getNativeDocIdent());

		
		// Third step: add Metadata Informations from QDC to the DiPPArticle Object
		// Read some of them into DiPPArticle Object, validate others
		// TODO Qa: move this to Metadata.Class ?
		
		//read Metadata into DiPPAtricle Object
		if (aQDC.getIdentifier() != null){
			if (aQDC.getIdentifier().length > 0){
				dippArtObj.setIdentifierSource(aQDC.getIdentifier(0));	
			}
		}
		if (aQDC.getSource() != null){
			if (aQDC.getSource().length > 0){
				dippArtObj.setIdentifierSource(aQDC.getSource(0));	
			}
		}
		dippArtObj.setMetaType(CollectionModel.cCollectionTypeArticle);

		String[] title = new String[1];
		if ((aQDC.getTitle() != null) && (aQDC.getTitle().length != 0)){
			dippArtObj.setMetaName(aQDC.getTitle()[0].getValue());
			title[0] = aQDC.getTitle()[0].getValue();
		}else{
			throw new InvalidParamMessage("param title not set");
		}
		
		// determine the conversion state 
		if (!doConvert){
			dippArtObj.setConversionStatus(convStatus.getBitMask()); //Constant.cDecsriptionDippNoConversion);					
			log.debug("Conversion State: " + dippArtObj.getConversionStatus());
		}

		dippArtObj.setLocked(true);
		dippArtObj.setNew(true);
		
		// only for validation
		if ((aQDC.getLanguage() == null) || (aQDC.getLanguage().length == 0)){
			throw new InvalidParamMessage("the language parameter is missing");
		}
		if (aQDC.getBibliographicCitation() != null){
			log.info(" Reading Citation Data from QDC: " + 
					"Date: " + aQDC.getBibliographicCitation(0).getJournalIssueDate() + 
					" Number: " +  aQDC.getBibliographicCitation(0).getJournalIssueNumber() +
					" Title: " +  aQDC.getBibliographicCitation(0).getJournalTitle() + 
					" Volume: " +  aQDC.getBibliographicCitation(0).getJournalVolume());
		}else{
			throw new InvalidParamMessage("the bibliographic citation is missing");				
		}

		// Fourth step: get Metadata Informations from Journal
		DigitalObject journalObj = DOManagement.retrieveDigitalObject(aJournalID);
		int serialNumber = Integer.parseInt(journalObj.getLabel());
		String titleOfJournal = journalObj.getTitle()[0];

		
		// Fifth step: evaluate which kind of article shall be created (according PID Namespace) 
		// test article
		// temp article
		// dipp article
		// then request a new PID for an article Object from Fedora within the Namespace
		boolean isTest = false;
		String articlePID = null;
		String pidNamespace = getPIDNamespace(aJournalID);
		log.debug("PidNamespace: " + pidNamespace);
		
		if (titleOfJournal.equals(Constant.cTestJournalName)){
			if (aStorageType.equals(Constant.cStorageTypePermanent)){
				articlePID = DOManagement.getNextPID(Constant.cPIDNamespaceTest);// test:*
			}else{
				articlePID = DOManagement.getNextPID(Constant.cPIDNamespaceTemp);// temp:*
			}
			isTest = true;
		}else{
			if (aStorageType.equals(Constant.cStorageTypePermanent)){
				articlePID = DOManagement.getNextPID(pidNamespace);// dipp:*
			}else{
				articlePID = DOManagement.getNextPID(Constant.cPIDNamespaceTemp);// temp:*
			}
		}
		log.info("created new Article Pid: " + articlePID);

		
		// sixth step: create a DigitalObject for the Article Object
		// set Model from DippArticle Object dippArtObj
		DigitalObject digObj = new DigitalObject(articlePID);
		digObj.setTitle(title);
		digObj.setMetaModel(dippArtObj);
		digObj.setLabel(journalObj.getLabel());
		log.info("Label: " + journalObj.getLabel());

		// add all Parent Objects of this Article Object
		// and update all ParentObjects
		try{
			for (int i = 0; i  < aContainerIDArray.length; i++){
				DigitalObject parentDippAdm = getDiPPAdministrativeMetaData(aContainerIDArray[i]);
//				parentDippAdm.setPid(aContainerIDArray[i]);
				digObj.addParentItem(parentDippAdm);
				parentDippAdm.addChildItem(digObj);
				updateDiPPAdministrativeMetaData(parentDippAdm);
			}
		}catch(Exception e){
			log.error(e);
		}
				
		// Add RDF Statemants to digObj
		if (Constant.cFedoraVersion > 1){
			try{
				ObjectRelations.mapRelation2RDF(digObj);
				addRdfCM(digObj, digObj.getCModel());// add CM: DiPP:Article to RDF
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		// test if an URN shall be created
		if (Constant.cConfiguration.hasWorkflowStep(serialNumber + "", Constant.cWfStepURN)){
			String urn = "";
			if (aStorageType.equals(Constant.cStorageTypeTemporary)){
				// create dummy urn and language for a temporary Object
				aQDC.setLanguage(0, aQDC.getTitle()[0].getLang()); // workaround to set a language for temporary articles
				urn = getURN(0, Integer.parseInt(articlePID.substring( (articlePID.lastIndexOf(":") + 1))));
			}else{
				// create a real URN for the new Article
				urn = getURN(serialNumber, Integer.parseInt(articlePID.substring( (articlePID.lastIndexOf(":") + 1))));					
			}
			aQDC.setIdentifierURN(urn);
			log.info("createNewArticle: set URN: " + urn);					
		}else{
			log.warn("createNewArticle: article gets no URN");
		}
				
		dcQualified.setQualifiedDublinCore(aQDC);
		
					
		// !!!!!!!!!!!!!!!!!!!!!!!!!
		// ! now create the real Objects in Fedora Repository
		// ! all associated Content Objects will be created as dummies first
		// ! the article Object will be created then
		
		// first try: generate all available Content Objects and add them to digital Object:
		for(int i = 0; i < Constant.cContentTypes.size(); i++){
			try{
				digObj.addChildItem(DOManagement.retrieveDigitalObject(createNewDataObject(digObj.getLabel(),Constant.cContentTypes.get(i), digObj)));
			}catch(RemoteException remExc){
				log.error(remExc);
			}
		}
		try{
			pid = persistNewDigitalObject(articlePID.substring(0,articlePID.indexOf(":")), digObj, dcQualified, isTest);
					
		}catch(RemoteException RemExc){
			log.error(RemExc);
		}
		
		// after that update DiPPAdm
		try{
			updateDiPPAdministrativeMetaData(digObj);
		}catch(Exception Exc){
			log.error(Exc);
		}
		
		
		ExtendedMetadata extMeta = getDiPPExtendedMetadata(pid);
		log.info("FedoraInterface: " + aFileUtil.getNativeFile().getAbsolutePath());
		log.info("FedoraInterface: " + aFileUtil.getNativeFilename());

		String fileName = null;
		if (aFileUtil.isCompressedZip()){
			fileName= aFileUtil.getCompressedFileFilename();
		}else{
			fileName = aFileUtil.getNativeFilename();
		}
		try{
			// add the native File to the native ContentObject
			if(extMeta.getObjectNative()!= null){
				domg.addDatastream(extMeta.getObjectNative(), null, fileName,  dippArtObj.getContentObject(Constant.cContentTypeNative).getMimeType(), "srcdoc_" + aLabel, Constant.cFedoraDatastreamControlGroupManaged);
				log.info("processNewArticle calls addDatastream from DOManagement for PID: " + extMeta.getObjectNative());
			}
		}catch(Exception e){
			log.error(e);
		}
										
		Param task = new Param();
		task.setArticlePID(pid);
		task.setJournalLabel(digObj.getLabel());
		task.setDoNew(true);
		task.setFileUtil(aFileUtil);
		task.setExtMetadata(extMeta);
		task.setContentObjectPID(extMeta.getObjectNative());
		task.setContentObjectDS("DS1");
		task.setConversionStatus(convStatus);
		String journalURL = Constant.cConfiguration.getIdentifier(serialNumber + "");
		log.debug(Constant.cConfiguration.getIdentifier(serialNumber + ""));
		log.debug("die JournalUrl: " + journalURL);
					
		if (doConvert){
					
			convStatus.addBit(ConversionStatus.cFlagConversion);
			TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskXML, task, true);
			convStatus.addBit(ConversionStatus.cFlagXML);
			if (journalURL.length() > 0){
				// this is a workaround and can removed, if all instances have a journalURL assigned
				TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskPlone, task, false);
			}
			TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskPlone, task, false);
			for (int i = 0; i < aTargetFormat.length; i++){
				if (aTargetFormat[i].equals("html")){
					TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskHTML, task, false);
					convStatus.addBit(ConversionStatus.cFlagHTML);
				}else if (aTargetFormat[i].equals("pdf")){
					TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskPDF, task, false);
					convStatus.addBit(ConversionStatus.cFlagPDF);
				}	
			}
		}else{
			// TODO just write the file to the according container object as datastream
						
			// Qa: In case the native File is a PDF (no conversion takes place) store this as additional datastream
			if (aFileUtil.getNativeFileMimeType().equals("application/pdf")){
				// just add the same DS for NativeObject to the PDFObject -> here content-object is the same 
				try{
					domg.addDatastream(extMeta.getObjectPDF(), null, fileName,  dippArtObj.getContentObject(Constant.cContentTypeNative).getMimeType(), aLabel, Constant.cFedoraDatastreamControlGroupManaged);
					log.info("added native PDF Document to Content Object \"pdf\"");
				}catch(Exception e){
					log.error(e);
				}
			}
			if (journalURL.length() > 0){
				// this is a workaround and can removed, if all instances have a journalURL assigned
				TaskManager.getInstance().addTask(TaskManager.cRegisteredTaskPlone, task, true);							
			}
		}
		return pid;
	}

	/**
	 * <p><em>Title: Create a new Container </em></p>
	 * <p>Description: Method creates a new DiPP:container object in the Fedora repository</p>
	 * 
	 * @param aAdmMetadata
	 * @return
	 * @throws RemoteException 
	 */
	public synchronized String createNewDirectory(AdministrativeMetadata aAdmMetadata)throws RemoteException{
		/*
		 * Metadata.parsePubsystemAdministrativeMetadata(admProperties, Constant.cContentModelContainer));
		 */
		DippContainer modelContainer = new DippContainer();
		modelContainer.setMetaType(aAdmMetadata.getMetaType());
		modelContainer.setMetaName(aAdmMetadata.getMetaName());
		String[] title = new String[1];
		title[0] = aAdmMetadata.getMetaName();
		modelContainer.setAbsoluteURL(aAdmMetadata.getAbsoluteURL());
		modelContainer.setChunkURL(aAdmMetadata.getChunkURL());
		List<DigitalObject> parentList = new Vector<DigitalObject>();
		for (int i = 0; i < aAdmMetadata.getParentList().length; i++){
			DigitalObject parentObject = new DigitalObject(aAdmMetadata.getParentList()[i]);
			parentList.add(parentObject);
		}

		String nextPID = null;
		// how to make sure, that parentList is not empty ?
		DigitalObject parentObject = DOManagement.retrieveDigitalObject(((DigitalObject)parentList.get(0)).getPid());
		boolean isTest = false;
		if (parentObject.getLabel().length() == 0 ||
				parentObject.getLabel().equals("") ){
			throw new RemoteException("Label of parent object is not set");
		}
		String pidNamespace = getPIDNamespace(parentObject.getPid());
		if (parentObject.getPid().startsWith(Constant.cPIDNamespaceDipa)){
			nextPID = DOManagement.getNextPID(Constant.cPIDNamespaceDipa);

		}else if (parentObject.getLabel().equals(Constant.cLabelTestJournal)){
			nextPID = DOManagement.getNextPID(Constant.cPIDNamespaceTest);
			isTest = true;
		}else{
			nextPID = DOManagement.getNextPID(pidNamespace);
		}
		log.info("createNewDirectory : " + nextPID + " with pid-namespace: " + pidNamespace);
		DigitalObject dirObj = new DigitalObject(nextPID);
		dirObj.setTitle(title);
		dirObj.setLabel(parentObject.getLabel());
		dirObj.setMetaModel(modelContainer);
		dirObj.addParentItem(parentObject);
		log.info("Metamodel: " + dirObj.getCModel());
		try{
			ObjectRelations.mapRelation2RDF(dirObj);// sets RDF Triples but not for ContentModel
			//Qa: add ContentModel RDF:
			addRdfCM(dirObj, modelContainer.getCModel());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// now create Container Object:
		persistNewDigitalObject(nextPID.substring(nextPID.indexOf(":")), dirObj, null, isTest);
		
		parentObject = getDiPPAdministrativeMetaData(parentObject.getPid());
		parentObject.addChildItem(dirObj);
		try{
			updateDiPPAdministrativeMetaData(dirObj);
			updateDiPPAdministrativeMetaData(parentObject);
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		return dirObj.getPid();
	}
	
	/**
	 * 
	 * @param aPID
	 * @return
	 */
	public Properties getItemLabelProperties(String aPID)throws RemoteException{
		return FedoraAccess.getInstance().getFedoraServices().getItemLabelProps(aPID);
	}
	
	/**
	 * 
	 * @param aObjectPID
	 * @param aDestObjectPID
	 * @return message
	 * @throws RemoteException
	 */
	public String copyObject(String aObjectPID, String aDestObjectPID) throws RemoteException{
		// test aObjectPID on article or containertype
		String[] resultFieldArray = {"pid", "cModel", "label"};
		String returnMsg = "";
		
		SearchCondition contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("pid");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(aObjectPID);
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		conditions.add(contentTypeCondition);
		
		DigitalObject[] obj = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
		if (obj.length == 1){
			if (!(obj[0].getCModel().equals(Constant.cContentModelArticle) || 
					obj[0].getCModel().equals(Constant.cContentModelContainer) )){
				returnMsg = "copy operation for pid " + aObjectPID + " not allowed";
				return returnMsg;
			}
		}else{
			returnMsg = "pid: " + aObjectPID + " not found.\r\n";
			return returnMsg;
		}
		
		// test aDestObjectPID on journal or container type
		contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("pid");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(aDestObjectPID);
		conditions.clear();
		conditions.add(contentTypeCondition);
		
		DigitalObject[] destObj = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
		if (destObj.length == 1){
			if (! (destObj[0].getCModel().equals(Constant.cContentModelJournal) || 
					destObj[0].getCModel().equals(Constant.cContentModelContainer) )){
				returnMsg = "copy operation for pid " + aDestObjectPID + " not allowed: \r\n" +
							" content model is: " + destObj[0].getCModel();
				return returnMsg;
			}
		}else{
			returnMsg = "copy operation pid: " + aDestObjectPID + " not found.\r\n";
			return returnMsg;
		}
		
		if (! destObj[0].getLabel().equals(obj[0].getLabel())){
			returnMsg = "copy operation between different journals not possible.";
			return returnMsg;			
		}else{
			DigitalObject objDippAdm = getDiPPAdministrativeMetaData(aObjectPID);
			DigitalObject destDippAdm = getDiPPAdministrativeMetaData(aDestObjectPID);
			objDippAdm.addParentItem(destDippAdm);
			destDippAdm.addChildItem(objDippAdm);
			try{
				updateDiPPAdministrativeMetaData(objDippAdm);
				updateDiPPAdministrativeMetaData(destDippAdm);
			}catch(XmlException xmlExc){
				xmlExc.printStackTrace();
			}catch(IOException ioExc){
				ioExc.printStackTrace();
			}
			ObjectRelations.setRelation(objDippAdm, destDippAdm, true);
			try{
				ObjectRelations.persistModel(objDippAdm, "");
				ObjectRelations.persistModel(destDippAdm, "");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		

		return returnMsg;
	}
	
	/**
	 * <p><em>Title: Move fedora Object</em></p>
	 * <p>Description: Qa: method binds article or container object bound to  
	 * temporary container to an persistent fedora object  
	 * Assigns fedora object to journal or container object</p>
	 * 
	 * @param aObjectPID <code>String</code> the PID of the fedora object that should 
	 * be rebound 
	 * @param aSourceObjectPID <code>String</code> PID of the recent 
	 * (temporary) parent object
	 * @param aDestObjectPID <code>String</code> PID of the container or journal 
	 * object the fedora object should be assigned to 
	 * @return <code>String</code> a message with status informations
	 * @throws RemoteException 
	 */
	public String moveObject(String aObjectPID, String aSourceObjectPID, String aDestObjectPID)throws RemoteException{
		
		// test if aObjectPID points to article or container object
		String[] resultFieldArray = {"pid", "cModel", "label"};
		String returnMsg = "";
		
		SearchCondition contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("pid");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(aObjectPID);
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		conditions.add(contentTypeCondition);
		
		DigitalObject[] obj = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
		if (obj.length == 1){
			if (!(obj[0].getCModel().equals(Constant.cContentModelArticle) || 
					obj[0].getCModel().equals(Constant.cContentModelContainer) )){
				returnMsg = "move operation for pid " + aObjectPID + " is not allowed";
				return returnMsg;
			}
		}else{
			returnMsg = "pid: " + aObjectPID + " not found.\r\n";
			return returnMsg;
		}
		
		// test if aDestObjectPID points to a journal or container object
		contentTypeCondition = new SearchCondition();
		contentTypeCondition.setProperty("pid");
		contentTypeCondition.setCondition(SearchCondition.cContains);
		contentTypeCondition.setValue(aDestObjectPID);
		conditions.clear();
		conditions.add(contentTypeCondition);
		
		log.debug("starte 2. findObjects request");
		DigitalObject[] destObj = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
		if (destObj.length == 1){
			if (! (destObj[0].getCModel().equals(Constant.cContentModelJournal) || 
					destObj[0].getCModel().equals(Constant.cContentModelContainer) )){
				returnMsg = "move operation for pid " + aDestObjectPID + " not allowed";
				return returnMsg;
			}
		}else{
			returnMsg = "move operation pid: " + aDestObjectPID + " not found.\r\n";
			return returnMsg;
		}
		
		DigitalObject srcDippAdm = getDiPPAdministrativeMetaData(aSourceObjectPID);
		DigitalObject objDippAdm = getDiPPAdministrativeMetaData(aObjectPID);
		DigitalObject destDippAdm = getDiPPAdministrativeMetaData(aDestObjectPID);
		if (!srcDippAdm.isParentOf(objDippAdm)){
			return "move operation not possible: pid " + aObjectPID + " is not child of " + aSourceObjectPID;
		}
		
		if (! destObj[0].getLabel().equals(obj[0].getLabel())){
			returnMsg = "move operation between different journals not possible.";
			return returnMsg;			
		}else{
			objDippAdm.addParentItem(destDippAdm);
			objDippAdm.removeParentItem(srcDippAdm);
			if (objDippAdm.getMetaModel() instanceof CollectionModel) {
				CollectionModel cModel = (CollectionModel)objDippAdm.getMetaModel() ;
				cModel.setAbsoluteURL( ((CollectionModel)destDippAdm.getMetaModel()).getAbsoluteURL() + "/" + cModel.getChunkURL());
			}

			// TODO notify URN-Epicur
			srcDippAdm.removeChildItem(objDippAdm);
			destDippAdm.addChildItem(objDippAdm);
			try{
				updateDiPPAdministrativeMetaData(objDippAdm);
				updateDiPPAdministrativeMetaData(srcDippAdm);
				updateDiPPAdministrativeMetaData(destDippAdm);
			}catch(XmlException xmlExc){
				xmlExc.printStackTrace();
			}catch(IOException ioExc){
				ioExc.printStackTrace();
			}
			// RDF
			ObjectRelations.setRelation(objDippAdm, destDippAdm, true);
			ObjectRelations.setRelation(objDippAdm, srcDippAdm, false);
			try{
				ObjectRelations.persistModel(objDippAdm, "");
				ObjectRelations.persistModel(destDippAdm, "");
				ObjectRelations.persistModel(srcDippAdm, "");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return returnMsg;
	}
	
	/**
	 * <p><em>Title: Web Service Operation findObjects for access Fedora via ZSI</em></p>
	 * <p>Description: Method simulate a API A findObjects Operation call. 
	 * It is created for working with the Python ZSI framework which does not 
	 * correctly work with the original API-A</p>
	 * 
	 * @param resultFieldArray
	 * @param maxResult
	 * @param Condition
	 * @return 
	 */
	public FieldSearchResult findObjects(String[] resultFieldArray, org.apache.axis.types.NonNegativeInteger maxResult, ConditionType[] Condition){

		FieldSearchResult fsr = new FieldSearchResult();

		//Cast Types to FedoraServicesImpl Types
		int maxResults = maxResult.intValue();
		List<SearchCondition> srCnd = new Vector<SearchCondition>();
		for(int i=0; i<Condition.length; i++){
			SearchCondition sr = new SearchCondition();
			sr.setProperty(Condition[i].getProperty());
			sr.setValue(Condition[i].getValue());
			sr.setCondition(Condition[i].getOperator().getValue());

			srCnd.add(sr);
		}
		
		//List<ConditionType> searchConditionsList = new Vector<ConditionType>();
		//searchConditionsList.addAll(Arrays.asList(Condition));
		DigitalObject[] digObjs = null;

		//call findObjects Operation via WS Adaptor
		try{
			digObjs = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, maxResults, srCnd);
		}catch(RemoteException remExc){
			log.error(remExc);
			remExc.printStackTrace();
		}
		
		// call method to cast result to FieldSearchResult
		fsr.setObjectFields(parseDigitalObjectToObjectFields(digObjs));
		return fsr;
	}

	/**
	 * <p><em>Title: Type conversion for DigitalObjects to ObjectFields</em></p>
	 * <p>Description: method casts an Array of DigitalObjects into an 
	 * Array of ObjectFields. This is required to return the correct datatype 
	 * </p>
	 * 
	 * @param digObjs
	 * @return 
	 */
	private ObjectFields[] parseDigitalObjectToObjectFields(DigitalObject[] digObjs){

		ObjectFields[] obF = new ObjectFields[digObjs.length];
		for (int i = 0; i< digObjs.length; i++){
			obF[i] = new ObjectFields();
			obF[i].setPid(digObjs[i].getPid());
			obF[i].setLabel(digObjs[i].getLabel());
			obF[i].setContributor(digObjs[i].getContributor());
			obF[i].setCoverage(digObjs[i].getCoverage());
			obF[i].setCreator(digObjs[i].getCreator());
			obF[i].setDate(digObjs[i].getDate());
			obF[i].setDescription(digObjs[i].getDescription());
			obF[i].setFormat(digObjs[i].getFormat());
			obF[i].setIdentifier(digObjs[i].getIdentifier());
			obF[i].setLanguage(digObjs[i].getLanguage());
			obF[i].setPublisher(digObjs[i].getPublisher());
			obF[i].setRelation(digObjs[i].getRelation());
			obF[i].setRights(digObjs[i].getRights());
			obF[i].setSource(digObjs[i].getSource());
			obF[i].setState(digObjs[i].getState());
			obF[i].setSubject(digObjs[i].getSubject());
			obF[i].setTitle(digObjs[i].getTitle());
			obF[i].setType(digObjs[i].getType());
		}
		log.info("ObFObject: " + obF.toString());

		return obF;
	}
	
}
