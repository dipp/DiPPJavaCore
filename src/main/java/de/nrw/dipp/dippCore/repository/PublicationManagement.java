package de.nrw.dipp.dippCore.repository;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import de.nrw.dipp.dippCore.repository.contentmodel.CollectionModel;
import de.nrw.dipp.dippCore.repository.contentmodel.DippArticle;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactory;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactoryImpl;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.repository.metadata.ObjectRelations;
import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreQualified;
import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreSimplified;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RDF;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.Fedora;
import de.nrw.dipp.dippCore.repository.workflow.Workflow;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.Part;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import de.nrw.dipp.dippCore.www.definitions.InvalidParamMessage;

/**
 * @author SCHIRRWAGEN
 *
 */
public class PublicationManagement {

	/**
	 * 
	 * @param aIsPartOfPID the parentID (journal, container or article)
	 * @param aIsVersionOfPID optional - the parentID (article only)
	 * @param aQDC the qualified dublin core metadata
	 * @param aWorkflow optional - the repository workflow, the complex document is bound
	 * @return a newly created digital object pid
	 */
	public String createNewComplexDocument(String aIsPartOfPID, String aIsVersionOfPID, 
			QualifiedDublinCore aQDC, Workflow aWorkflow)
			throws PublicationException, RemoteException{
		String articlePID = null;
		
		if (aIsPartOfPID == null){
			throw new PublicationException("parentPID parameter missing");
		}else if (aQDC == null){
			throw new PublicationException("metadata parameter missing");
		}
		DublinCoreQualified dcQualified = new DublinCoreQualified();
		
		ModelFactory mf = new ModelFactoryImpl();
		DippArticle articleObject = (DippArticle)mf.createModel(Constant.cContentModelArticle);
		
		//DippContent contentObject = new DippContent();
		//articleObject.addContentObject(Constant.cContentTypeNative, contentObject);
		/*
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
		*/
		
		articleObject.setMetaType(CollectionModel.cCollectionTypeArticle);
		articleObject.setLocked(true);

		String[] title = new String[1];
		if (aQDC.getTitle() == null){
			throw new InvalidParamMessage("param title not set");
		}if (aQDC.getTitle().length == 0){			
			throw new InvalidParamMessage("param title not set");
		}else{
			articleObject.setMetaName(aQDC.getTitle()[0].getValue());
			title[0] = aQDC.getTitle()[0].getValue();
		}

		articleObject.setNew(true);

		
		de.nrw.dipp.dippCore.repository.DigitalObject parentObject = DOManagement.retrieveDigitalObject(aIsPartOfPID);
		int serialNumber = Integer.parseInt(parentObject.getLabel());
		
		boolean isTest = false;
		String pidNamespace = ServiceManagement.getPIDNamespace(parentObject.getPid());
		if (pidNamespace.equals(Constant.cPIDNamespaceTest)){
			articlePID = FedoraManagement.getInstance().getFedoraServices().getNextPID(Constant.cPIDNamespaceTest);						
			isTest = true;
		}else{
			articlePID = FedoraManagement.getInstance().getFedoraServices().getNextPID(pidNamespace);
		}
		de.nrw.dipp.dippCore.repository.DigitalObject digObj = new de.nrw.dipp.dippCore.repository.DigitalObject(articlePID);
		digObj.setTitle(title);
		digObj.setMetaModel(articleObject);
		
		Metadata.setHierarchyByDiPPAdministrativeMetaData(parentObject);
		digObj.addParentItem(parentObject);
		parentObject.addChildItem(digObj);
		
		try{
			ServiceManagement.updateDiPPAdministrativeMetaData(parentObject);			
		}catch(XmlException xmlExc){
				throw (PublicationException)
	        	new PublicationException().initCause(xmlExc);
		}catch(IOException ioExc){
			throw (PublicationException)
        	new PublicationException().initCause(ioExc);			
		}
		// comment, when test
		
		if (Constant.cConfiguration.hasWorkflowStep(serialNumber + "", Constant.cWfStepURN)){
			String urn = "";
			urn = ServiceManagement.getURN(serialNumber, Integer.parseInt(articlePID.substring( (articlePID.lastIndexOf(":") + 1))));
			aQDC.setIdentifierURN(urn);
			System.out.println("PublicationManagement: createNewComplexDocument: set URN: " + urn);
		}else{
			System.out.println("PublicationManagement: createNewComplexDocument: article gets no URN");
		}

		if (parentObject.getCModel().equals(Constant.cContentModelArticle)){
			// new article-object is part of parent article-object
			// write to qdc-part-element
			// write to rdf
			QualifiedDublinCore qdcParent = Metadata.getQualifiedDublinCoreMetadata(parentObject.getPid());
			
			Part[] parts = new Part[1];
			parts[0] = new Part();
			parts[0].setTitle(qdcParent.getTitle()[0].getValue());
			parts[0].setURN(qdcParent.getIdentifierURN());
			System.out.println("pubMan partsURN: " + parts[0].getURN());
			aQDC.setIsPartOf(parts);
			
			Part[] parentParts = qdcParent.getHasPart();
			ArrayList list = new ArrayList( Arrays.asList(parentParts) );
			Part part = new Part();
			part.setTitle(aQDC.getTitle()[0].getValue());
			part.setURN(aQDC.getIdentifierURN());
			list.add(part);
			System.out.println(list.size());
			qdcParent.setHasPart((Part[])list.toArray(new Part[0]));
			System.out.println(qdcParent.getHasPart().length);
			DublinCoreQualified dcQualifiedParent = new DublinCoreQualified();
			dcQualifiedParent.setQualifiedDublinCore(qdcParent);

			DOManagement doManage = new DOManagement(Constant.cFedoraVersion);
			try{
				doManage.modifyDatastream(parentObject.getPid(), "QDC", "DC Qualified Record", "text/xml", dcQualifiedParent.getQualifiedDublinCore().xmlText().getBytes("UTF-8"), "modified");
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		
		Part[] childParts = aQDC.getIsPartOf();
		if (childParts.length > 0){
			System.out.println("childParts: " + childParts[0].getURN());
		}
		dcQualified.setQualifiedDublinCore(aQDC);

		//System.out.println("FedoraInterface: createNewArticle: set qdc");
		// preset of the pid
		digObj.setLabel(parentObject.getLabel());
		if (Constant.cFedoraVersion > 1){
			try{
				ObjectRelations.mapRelation2RDF(digObj);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		articlePID = persistNewDigitalObject(articlePID.substring(0,articlePID.indexOf(":")), digObj, dcQualified, isTest);

		try{
			ServiceManagement.updateDiPPAdministrativeMetaData(digObj);
		}catch(XmlException xmlExc){
			throw (PublicationException)
        	new PublicationException().initCause(xmlExc);
		}catch(IOException ioExc){
			throw (PublicationException)
        	new PublicationException().initCause(ioExc);			
		}
		
			//System.out.println("FedoraInterface: createNewArticle: dippAdm updated");
		
		//ExtendedMetadata extMeta = ServiceManagement.getDiPPExtendedMetadata(articlePID);
			
		return articlePID;
	}
	
	public String createNewSimpleContent(String aIsConstituentOfPID, String aCategory) throws PublicationException{
		String contentPID = null;
		DOManagement digObjMgt = new DOManagement();
		try{
			de.nrw.dipp.dippCore.repository.DigitalObject digObj = digObjMgt.retrieveDigitalObject(aIsConstituentOfPID);
			// TODO check the ExtMetadata Format to get/set content
/*			ExtendedMetadata extMd = ServiceManagement.getDiPPExtendedMetadata(aIsConstituentOfPID);
			Content content = extMd. addNewContent();
			de.nrw.dipp.definitions.extmetadata.Reference ref = content.addNewHtml();
//			ref.setItemID(createDataObject(aTxtObject.getLabel(), "html", aTxtObject.getTitle()[0]));
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "html", aTxtObject));
			ref = content.addNewMultimedia();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "multimedia", aTxtObject));
			ref = content.addNewNative();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "native", aTxtObject));
			ref = content.addNewOther();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "other", aTxtObject));
			ref = content.addNewPdf();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "pdf", aTxtObject));
			ref = content.addNewXml();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "xml", aTxtObject));
			ref = content.addNewSupplementary();
			ref.setItemID(createDataObject(aTxtObject.getLabel(), "supplementary", aTxtObject));
*/
		}catch(RemoteException remExc){
			throw (PublicationException)
        	new PublicationException().initCause(remExc);			
		}
		return contentPID;
	}
	
	private static synchronized String createDataObject(String aLabel, String aType, de.nrw.dipp.dippCore.repository.DigitalObject aParentObject){
		String pid = null;
		DOManagement digObjMgt = new DOManagement();
		try{
			pid = digObjMgt.init(Constant.cPIDNamespaceContent, null, aLabel, Constant.cContentModelData);		
			if (Constant.cFedoraVersion == 2){
				digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", getRDF(pid, aParentObject), "");
			}
			digObjMgt.addDatastreamToDigObj("DC", "DC record", "text/xml", "DC1.0", createDataObjectDC(aParentObject, aType), Constant.cFedoraMetadataClassDescriptive);
			digObjMgt.ingestFedoraObject("ingest of content object");
			System.out.println("Metadata: createDataObject: " + pid);
		}catch(RemoteException remoteExc){
			remoteExc.printStackTrace();
		}
		return pid;
	}	
	private static XmlObject getRDF(String aPID, de.nrw.dipp.dippCore.repository.DigitalObject aParentObj){
		RDF rdf = new RDF(aPID);
		rdf.addStatement(Fedora.cIsConstituentof, aParentObj.getPid());
		aParentObj.getRDF().addStatement(Fedora.cHasConstituent, aPID);
		return rdf.toXMLObject();
	}
	
	private static XmlObject createDataObjectDC(de.nrw.dipp.dippCore.repository.DigitalObject aParentObject, String aType){
		DublinCoreSimplified dc = new DublinCoreSimplified();
		System.out.println("Metadata: createDataObjectDC: relation to " + aParentObject.getPid());
		dc.addRelation(aParentObject.getPid());
		dc.addTitle(aType);
		return dc.getSimplifiedDublinCore();		
	}	

	
	/**
	 * @param aPIDNamespace
	 * @param aDigitalObject
	 * @param aQDCmetadata
	 * @param isTest
	 * @return
	 * @throws RemoteException
	 */
	protected static synchronized String persistNewDigitalObject(String aPIDNamespace, de.nrw.dipp.dippCore.repository.DigitalObject aDigitalObject, DublinCoreQualified aQDCmetadata, boolean isTest)throws RemoteException{
		
		String pid = null;
		try{
		if (aDigitalObject.getLabel().length() == 0 ||
				aDigitalObject.getLabel().equals("")){
			throw new RemoteException("Label not set");
		}
		DOManagement digObjMgt = new DOManagement(Constant.cFedoraVersion);
		pid = digObjMgt.init(aPIDNamespace, aDigitalObject.getPid(), aDigitalObject.getLabel(), aDigitalObject.getCModel());		
		
		if (aDigitalObject.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
			digObjMgt.addDatastreamToDigObj("DiPPExt", "DiPPExt record", "text/xml", "DiPPExt1.0", Metadata.createRepositoryExtendedMetadataContainer(aDigitalObject),Constant.cFedoraMetadataClassTechnical);
		}
		
		if (aQDCmetadata != null){
			digObjMgt.addDatastreamToDigObj("QDC",    "DC Qualified record", "text/xml", "QDC1.0",    aQDCmetadata.getQualifiedDublinCore(),                                  Constant.cFedoraMetadataClassDescriptive);
			digObjMgt.addDatastreamToDigObj("DC",     "DC record",           "text/xml", "DC1.0",     Metadata.createRepositoryDublinCoreSimplified(aDigitalObject, aQDCmetadata.getQualifiedDublinCore().getMetadata(), aQDCmetadata.getDCTypeList(), aQDCmetadata.getDdc(), isTest), Constant.cFedoraMetadataClassDescriptive);
		}else{
			digObjMgt.addDatastreamToDigObj("DC", "DC record", "text/xml", "DC1.0", Metadata.createRepositoryDublinCoreSimplified(aDigitalObject, null, new Vector(), new Vector(), isTest), Constant.cFedoraMetadataClassDescriptive);
		}
		digObjMgt.addDatastreamToDigObj("DiPPAdm", "DiPPAdm record", "text/xml", "DiPPAdm1.0", Metadata.createRepositoryAdministrativeMetadata(aDigitalObject),Constant.cFedoraMetadataClassTechnical);

		// new for Fedora2
		if (aDigitalObject.getRDF() != null){
			digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", aDigitalObject.getRDF().toXMLObject(), "");
		}
		
		pid = digObjMgt.ingestFedoraObject("persist new digital object " + aDigitalObject.getMetaModel());
		if (!pid.equals(aDigitalObject.getPid())){
			throw new RemoteException("PID assignment exception");
		}
		

		}catch(Exception e){
			e.printStackTrace();
		}
		return pid;
	}

}
