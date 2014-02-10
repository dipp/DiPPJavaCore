/**
 * DOManagement.java - This file is part of the DiPP Project by hbz
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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.FedoraInstance;

/**
 * Class DOManagement.java
 * 
 * <p><em>Title: A connector to the Fedora Repository used by DiPP</em></p>
 * <b>Description: </b><br>
 * Qa: Class provides tools for management of fedora objects including
 * adding, updating, removing, changing, ...??? of fedora objects. It wraps the DiPP to 
 * fedora API-A and API-M Interfaces for fedora version 2<br>
 * JS: Introduced for Fedora V2, this class manages a digital object and 
 * makes the old dirty FedoraInterface class obsolet.</p>
 * <b>History:</b> 
 * <ul>
 * <li>created on 2006/01/09</li>
 * <li>last modification according to fedora2.2 api changes on 2007/05/16</li>
 * <li>last modification in retrieveDigitalObjects on 2006/12/15</li>
 * </ul>
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de, Andres Quast, quast@hbz-nrw.de
 * @version $Id: DOManagement.java,v 1.3 2007/01/05 11:29:23 dippadm Exp $
 */
public class DOManagement {

    // Get Logger for DOManagement
	private static Logger log = Logger.getLogger(DOManagement.class);

	// set runtime environment
	private int mFedoraVersion = Constant.cFedoraVersion; 
	private String mMetadataformat = Constant.cFedoraObjectFormatFoXML;
	
	// Declare new Object representation as XmlBeans for using strategy pattern
	// The following Factory call will generate this representation at runtime, 
	// which means that instance properties are variable and depending from runtime
	// environment
	private FedoraXMLObject fedoraXmlObj = null;
	

	/**
	 * Constructor gets required Fedora Version Information from
	 * Constant.cFedoraVersion
	 * ! This Constructor should be used in all Classes for reducing 
	 * forthcoming maintenance efforts
	 */
	public DOManagement(){
		mFedoraVersion = Constant.cFedoraVersion;
		mMetadataformat = FedoraVersion.Factory.newInstance().getFedoraXmlFormat(); 
		fedoraXmlObj = FedoraVersion.Factory.newInstance().getFedoraXmlObject();
	}
	
	/**
	 * Constructor that requires Parameter Fedora Version from calling Class. 
	 * Probably used for Testing classes?
	 * @param aFedoraVersion
	 * @deprecated 
	 */
	public DOManagement(int aFedoraVersion){

		mFedoraVersion = aFedoraVersion;
		// getting the Fedora Version from Method call means we 
		// not always get the correct Version
		// Due to that this Constructor may gives back different informations about 
		// version and MetadataFormat 
		mMetadataformat = FedoraVersion.Factory.newInstance().getFedoraXmlFormat(); 
		fedoraXmlObj = FedoraVersion.Factory.newInstance().getFedoraXmlObject();
		log.warn("Using deprecated Constructor, Please change code from calling method to call new Constructor!\n" 
				+ "Fedora Version from Parameter: " + mFedoraVersion + "\n"
				+ "Fedora Version used by Factory: " + Constant.cFedoraVersion);
	}
	

	/**
	 * Some APIM Web Service Operations accept only a Location (URL) for 
	 * adding Content to a Fedora Object. Therefore this Method calls an Upload 
	 * Object that uploads the Content as InputStream to the Server and returns a 
	 * Location URL. Method was formerly situated at each Fedora Adaptor and is moved 
	 * here for an easier implementation of new Fedora Adaptors
	 *     
	 * @param aInputStream
	 * @return
	 * @throws IOException
	 */
	private String getUploadLocation(InputStream aInputStream)throws IOException{
		Upload upload = new Upload(	Constant.cFedoraHost, 
				Constant.cFedoraPort,
				Constant.cFedoraUser, 
				Constant.cFedoraPassword);
		return upload.upload(aInputStream);		
	}
	
	/**
	 * Some APIM Web Service Operations accept only a Location (URL) for 
	 * adding Content to a Fedora Object. Therefore this Method calls an Upload 
	 * Object that uploads the Content from File to the Server and returns a 
	 * Location URL. Method was formerly situated at each Fedora adaptor and is moved 
	 * here for an easier implementation of new Fedora Adaptors
	 *
	 * @param aFileName
	 * @return
	 * @throws IOException
	 */
	private String getUploadLocation(String aFileName)throws IOException{
		Upload upload = new Upload(	Constant.cFedoraHost, 
				Constant.cFedoraPort,
				Constant.cFedoraUser, 
				Constant.cFedoraPassword);
		return upload.upload(new File(aFileName));		
	}

	/**
	 * <p><em>Title: Method calls the fedoraXmLObj to create a new XmlBeans representation
	 * of XMLDocument stub</em></p>
	 * <p>Description: </p>
	 * 
	 * @param aPIDNamespace  <code>String</code> The namespace to which the PID of fedora Object 
	 * shall be assigned
	 * @param aPID <code>String</code> since Fedora2: optional; if not set the system 
	 * assigns the next available id for this namespace
	 * @param aLabel 
	 * @param aCModel
	 * @return
	 * @throws RemoteException 
	 */
	public String init(String aPIDNamespace, String aPID, String aLabel, String aCModel) throws RemoteException{
				
		String nextPID = "";
		if (aPID != null){
			nextPID = aPID.trim();
		}
		if (nextPID.equals("")){
			nextPID = FedoraManagement.getInstance().getFedoraServices().getNextPID(aPIDNamespace);
		}
		
		// using the strategy design pattern and delegation via new Class:
		nextPID = fedoraXmlObj.createXMLDocument(aPIDNamespace, nextPID, aLabel, aCModel);
		return nextPID;
	}
	

	/**
	 * <p>Method returns the PID from a XmlBeans Object representing a Fedora Object</p>
	 * <p>uses the fedoraXmlObject interface to delegate the getPID method to the correct 
	 * XmlBeans Class for a Fedora Version</p>
	 * 
	 * @return 
	 */
	public String getPid(){
		String pid = "";		
		
		fedoraXmlObj.getPID();
		return pid;
	}
	
	/**
	 * <p>Method delegates a request to different methods that add a Datastream to an Instance 
	 * of DigitalObject. Does not write an Object to Fedora Repository. It is used to create add 
	 * all required Datastreams to a new DigitalObject. Question is if it is used for all kind 
	 * of Fedora Objects?</p>
	 * 
	 * This method now delegates any request to the appropriate method within the fedoraXmlObject</p> 
	 * 
	 * @param aDSId the fedora object pid
	 * @param aLabel the eJournal label ?
	 * @param aMimeType MimeType of datastream to add
	 * @param aVersionId Version Identifier
	 * @param aXMLObject the Datastream that should be added as XmlObject
	 * @param aDSType 
	 */
	public void addDatastreamToDigObj(String aDSId, String aLabel, String aMimeType, String aVersionId, XmlObject aXMLObject, String aDSType){

		fedoraXmlObj.addXMLDatastream(aDSId, aLabel, aMimeType, aVersionId, aXMLObject, aDSType);
	}
	
	 /**
	 * <p><em>Title:</em> Add datastream identified by Filename</p>
	 * <p>Method adds any Datastream to an existing Fedora Object, which is 
	 * identified by an absolute Path. For this Method uploads File to the DiPP 
	 * Server (via Upload Class) and uses the returned URL for adding the Datastream
	 * to Repository</p>
	 * <p>It can be used to add an external Datastreams from local maschine to a
	 * Fedora Object.</p>
	 * 
	 * @param aPID the pid of the object.
	 * @param aLocation external link or the name of the file to upload as datastream.
	 * @param aMimeType the mimetype of the file.
	 * @param aLabel a label of the new datastream.
	 * @return <code>String</code> the datastream id.
	 * TODO should throw an exception
	 */
	public String addDatastream(String aPID, String aDSId, String fileName, 
			String aMimeType, String aLabel, 
			String aControlgroup)throws Exception{

		String aLocation = null;
		try{
			aLocation = getUploadLocation(fileName);
		}catch(Exception e){
			log.error(e);
		}
		log.info("Filename: " + fileName);
		log.info("Upload-Url: " + aLocation);
		return FedoraManagement.getInstance().getFedoraServices().addDatastream(aPID, aDSId, aLocation, aMimeType, aLabel, aControlgroup, "DEFAULT", null);
	}
	
	 /**
	 * <p><em>Title:</em> Add datastream identified by Filename</p>
	 * <p>Method adds any Datastream to an existing Fedora Object, which is 
	 * identified by an Url.</p>
	 * <p>It can be used to add an external Datastream from local machine to a
	 * Fedora Object.</p>
	 * 
	 * @param aPID the pid of the object.
	 * @param url external link to import as datastream.
	 * @param aMimeType the mimetype of the file.
	 * @param aLabel a label of the new datastream.
	 * @return <code>String</code> the datastream id.
	 * TODO should throw an exception
	 */
	public String addDatastreamFromUrl(String aPID, String aDSId, String url, 
			String aMimeType, String aLabel, 
			String aControlgroup)throws Exception{

		log.info("Upload-Url: " + url);
		return FedoraManagement.getInstance().getFedoraServices().addDatastream(aPID, aDSId, url, aMimeType, aLabel, aControlgroup, "DEFAULT", null);
	}

	/**
	 * <p>Adds any Datastream to an existing Fedora Object.</p>
	 * <p>Changed this Method, so it now takes an Array of byte, for all
	 * Classes that will add a internal Datastream. Method uploads
	 * the array of byte to a File by method getUploadLocation on the Server 
	 * which then can be used from Web Service addDatastream Operation.    
	 * 
	 * @param aPid the fedora object pid
	 * @param aDSId a Datastream Identifier
	 * @param aLabel the eJournal label ?
	 * @param aMimeType MimeType of datastream to add
	 * @param aDSContent the Datastream?
	 * @param aLogMessage a Log Message for the added Datastream
	 * @param aControlgroup determines which type of Datastream will be added to the FOXML object. 
	 * "X" says an "Inline XML"-DS will be added: it has to be well formed XML  
	 */
	public void addDatastream(String aPid, String aDSId, String aLabel, String aMimeType, byte[] aDSContent, String aLogMessage, String aControlgroup)throws Exception{
		
		//TODO remove this if block
		if (mFedoraVersion == 1){
			throw new Exception("method not supported for fedora prior to version 2");
		}else{
			
			
			try{
				String aLocation = getUploadLocation(new ByteArrayInputStream(aDSContent)); 
				String id = FedoraManagement.getInstance().getFedoraServices().addDatastream(aPid, aDSId, aLocation, aMimeType, aLabel, aControlgroup, null, null);			
				log.info("die DatastreamID: " + id);
			}
			catch(IOException uploadExc){
					uploadExc.printStackTrace();
					}
			
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	
	/**
	 * Method modifies an internal Datastream of a FoXMl Object. Therefore it calls the 
	 * modifyDatastreamContent Method from FedoraServicesManagement Interface. In 
	 * difference to the modifyDatastreamByReference this and the underlying Web Service
	 * Operation take a Bytestream as modified datastream
	 *   
   	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be modified, if null is given 
	 * the repository system will create a new
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aDsContent <code>byte[]</code> the replacement datastream as Array of byte
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @throws Exception
	 */
	public void modifyDatastream(String aPid, String aDSId, String aLabel, String aMimeType, byte[] aDSContent, String aLogMessage)throws Exception{
		FedoraManagement.getInstance().getFedoraServices().modifyDatastreamContent(aPid, aDSId, new String[0], aLabel, true, aMimeType, "", aDSContent, "A", aLogMessage, "DEFAULT", null, true);		
	}

	/**
	 * Method modifies an external or managed Datastream. Therefore it first converts a 
	 * Datastream from its Array of bytes representation to a File with location, then 
	 * it calls the modifyDatastreamByReference Method from FedoraServicesManagement Interface.
	 * The underlying Web Service Operation is the modifyDatastreamByreference Operation
	 *    
   	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be modified
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aDsContent <code>byte[]</code> the replacement datastream as Array of byte
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @throws IOException
	 */
	public void modifyDatastreamByReference(String aPid, String aDSId, String aLabel, String aMimeType, InputStream aDSContent, String aLogMessage)throws IOException{
		FedoraManagement.getInstance().getFedoraServices().modifyDatastreamByReference(aPid, aDSId, new String[0], aLabel, aMimeType, "", getUploadLocation(aDSContent), "DEFAULT", null, aLogMessage, true);		
	}
	
	public void addOrModify(String aPID, String aDSId, String aLabel, String aMimeType, byte[] aDSContent, String aLogMessage, String aControlGroup)throws RemoteException, Exception{
		Set<String[]> itemSet = FedoraAccess.getInstance().getFedoraServices().getItemIndex(aPID);
		if (itemSet.contains(aDSId)){
			modifyDatastream(aPID, aDSId, aLabel, aMimeType, aDSContent, aLogMessage);
		}else{
			addDatastream(aPID, aDSId, aLabel, aMimeType, aDSContent, aLogMessage, aControlGroup);
		}
	}
	
	public byte[] getDatastream(String aPid, String aDSId) throws RemoteException{
		return FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(aPid, aDSId);
	}
	
	
	/**
	 * @param aPid
	 * @return Properties of Item-Label pairs
	 * @throws RemoteException
	 */
	public Properties getDatastreamList(String aPid) throws RemoteException{
		return FedoraAccess.getInstance().getFedoraServices().getItemLabelProps(aPid);
	}

	
	/**
	 * <p><em>Title: Method ingests the Object into Fedora Repository</em></p>
	 * <p>Description: Method calls the ingestObject method from WSAdaptor to submit 
	 * the new Fedora Object represented by fedoraXmlObject to the Fedora Repository. 
	 * Afterwards it sets the Objects State to I 
	 * (implemented for Fedora Version newer than 1 only).</p>
	 * 
	 * @param aLogMessage
	 * @return <code>String</code> PID of the Fedora Object newly created
	 * @throws RemoteException 
	 */
	public String ingestFedoraObject(String aLogMessage)throws RemoteException{
		String pid = "";
		
		try{
			
			log.debug(fedoraXmlObj.getXmlText());
			pid = FedoraManagement.getInstance().getFedoraServices().ingestObject(
					fedoraXmlObj.getXmlText().getBytes("UTF-8"), mMetadataformat, aLogMessage);
			FedoraManagement.getInstance().getFedoraServices().modifyObjectState(pid, "I", null, "", "set initial state to inactive");
			log.info("Ingested object into Fedora System: " + pid);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}

		return pid;
	}
	
	/**
	 * <p><em>Title: Method gets the next available PID for a Namespace</em></p>
	 * <p>Description: Method returns the next available PID for a given namespace. 
	 * Method only delegates requests to WSAdaptor</p>
	 * 
	 * @param pidNameSpace <code>String</code> a namespace for Fedora Objects
	 * @return <code>String</code> the next available PID within the namespace
	 */
	public static String getNextPID(String pidNameSpace){
		String nextPid = null; 
		try{
			nextPid = FedoraManagement.getInstance().getFedoraServices().getNextPID(pidNameSpace);
		}catch(RemoteException RemExc){
			log.error(RemExc);
		}
		return nextPid;
	}
	
	public String toXML(){
		// Frage: ist diese Methode überhaupt irgendwie genutzt? 
		// Anscheinend nicht: keine Aufrufe

		String xmlText = "";
		xmlText = fedoraXmlObj.getXmlText();
		
		return xmlText;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aPID
	 * @throws RemoteException 
	 */
	public void purgeObject(String aPID)throws RemoteException{
		FedoraManagement.getInstance().getFedoraServices().purgeObject(aPID, "delete it", false);
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aPID
	 * @param aDsID
	 * @param aEndDate
	 * @return
	 * @throws RemoteException 
	 */
	public String[] purgeDatastream(String aPID, String aDsID, String aEndDate) throws RemoteException{
		return FedoraManagement.getInstance().getFedoraServices().purgeDatastream(aPID,aDsID, aEndDate, "", false);
	}
	
	/*
	 * 07-12-2006, JS: added 'state' & 'publisher' to resultFieldArray
	 */
	public static de.nrw.dipp.dippCore.repository.DigitalObject retrieveDigitalObject(String aPID)throws RemoteException{
		de.nrw.dipp.dippCore.repository.DigitalObject digObj = null;
		String[] resultFieldArray = {"pid", "label", "title", "cDate", "mDate", "cModel", "state", "publisher"};
		
		SearchCondition condition = new SearchCondition();
		condition.setProperty("pid");
		condition.setCondition(SearchCondition.cContains);
		condition.setValue(aPID);
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		conditions.add(condition);
		de.nrw.dipp.dippCore.repository.DigitalObject[] objFields = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, conditions);
		if (objFields.length == 1){
			digObj = objFields[0];
		}
		Set<String[]> itemSet = FedoraAccess.getInstance().getFedoraServices().getItemIndex(aPID);
		if (itemSet.contains(Constant.cDSidRELS_Ext)){
			byte[] rdfDS = FedoraAccess.getInstance().getFedoraServices(). getDataStreamContentAsBytes(aPID, "RELS-EXT");
			digObj.getRDF().read(new ByteArrayInputStream(rdfDS));
		}			
		return digObj;
	}
	
	/**
	 * <p><em>Title: Method returns a List of Digital Objects that match to the given 
	 * Search conditions</em></p>
	 * <p>Description: Method uses (via WS Adaptor) the API-A findObject Operation for getting 
	 * a List of Fedora Objects that match to the given Search Conditions. 
	 * The WS Adaptor parses the results from the findObject Operation into a 
	 * List of DigitalObject Instances.</p>
	 * 
	 * @param aSearchConditions
	 * @return
	 * @throws RemoteException 
	 */
	public static de.nrw.dipp.dippCore.repository.DigitalObject[] retrieveDigitalObjects(List<SearchCondition> aSearchConditions)throws RemoteException{
		
		String[] resultFieldArray = {"pid", "label", "title", "cDate", "mDate", "cModel", "state", "publisher"};
		
		de.nrw.dipp.dippCore.repository.DigitalObject[] digObjects = FedoraAccess.getInstance().getFedoraServices().findObjects(resultFieldArray, 1, aSearchConditions);

		// Wofür?
		// Hier scheint u.a. das ContentModel in das entsprechende Modell geschrieben zu werden
		for (int i = 0; i < digObjects.length; i++){
			Set<String[]> itemSet = FedoraAccess.getInstance().getFedoraServices().getItemIndex(digObjects[i].getPid());
			if (itemSet.contains(Constant.cDSidRELS_Ext)){
				byte[] rdfDS = FedoraAccess.getInstance().getFedoraServices(). getDataStreamContentAsBytes(digObjects[i].getPid(), "RELS-EXT");
				digObjects[i].getRDF().read(new ByteArrayInputStream(rdfDS));
			}
		}

		return digObjects;
	}
	
	public static byte[] exportObject(String pid, String format, String context){
		byte[] exportStream = null;
		try{
			exportStream = FedoraManagement.getInstance().getFedoraServices().export(pid, format, context);
		}catch(Exception e){
			log.error(e);
		}
		return exportStream;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
/*		DOManagement management = new DOManagement(2);
		try{
			management.init("dipp", null, "1", Constant.cContentModelJournal);
			de.nrw.dipp.dippCore.repository.DigitalObject repDO = new de.nrw.dipp.dippCore.repository.DigitalObject();
			repDO.setMetaModel(new DippJournal());
//			repDO.setCollectionModel(new DippArticle());
			repDO.setLabel("100");
			String[] title = new String[1];
			title[0] = "abc";
			repDO.setTitle(title);
			management.addDatastream("DS", "Simple Dublin Core", "text/xml", "DS1.0", Metadata.createRepositoryDublinCoreSimplified(repDO, null, new Vector(), new Vector(), true), null);
			System.out.println(management.toXML());
		}catch(RemoteException exc){
			exc.printStackTrace();
		}
*/		
	}

}
