/**
 * DippSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 * Implementation of methods by Andres Quast 06.05.2009
 * Added findObjects Method on 05.06.2009
 */

package de.nrw.dipp.dippCore.www.definitions;

import java.io.IOException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import org.apache.xmlbeans.XmlException;

import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;

public class DippSoapBindingImpl implements ContentModel{

    // Get Logger for DOManagement
	private static Logger log = Logger.getLogger(DippSoapBindingImpl.class);

	
	/* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setNewComplexDocument(java.lang.String, java.lang.String, de.nrw.dipp.webservice.QualifiedDublinCore)
     */
    public java.lang.String setNewComplexDocument(java.lang.String isPartOfPID, java.lang.String isVersionOfPID, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore qdc) throws java.rmi.RemoteException {
    	//TODO: Implement this Method
    	log.error("Method not implemented yet");
        return null;
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#copyObject(java.lang.String, java.lang.String)
     */
    public java.lang.String copyObject(java.lang.String objectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException {
    	ServiceManagement fi = new ServiceManagement();
    	return fi.copyObject(objectPID, destObjectPID);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#getArticleContentMetadata(java.lang.String)
     */
    public de.nrw.dipp.dippCore.webservice.ExtendedMetadata getArticleContentMetadata(java.lang.String in0) throws java.rmi.RemoteException {
    	if (in0 == null){
    		throw new RemoteException("getArticleContentMetadata parameter PID is not specified!");
    	}
    	return ServiceManagement.getDiPPExtendedMetadata(in0);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#getQualifiedDublinCore(java.lang.String)
     */
    public de.nrw.dipp.dippCore.webservice.QualifiedDublinCore getQualifiedDublinCore(java.lang.String in0) throws java.rmi.RemoteException {
    	if (in0 == null){
    		throw new RemoteException("getQualifiedDublinCore parameter PID is not specified!");
    	}
    	return Metadata.getQualifiedDublinCoreMetadata(in0);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setQualifiedDublinCore(java.lang.String, de.nrw.dipp.webservice.QualifiedDublinCore)
     */
    public void setQualifiedDublinCore(java.lang.String in0, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in1) throws java.rmi.RemoteException {
    	if (in0 == null){
    		throw new RemoteException("setQualifiedDublinCore parameter PID is not specified!");
    	}
    	if (in1 == null){
    		throw new RemoteException("setQualifiedDublinCore parameter QualifiedDublinCore is not specified!");
    	}
    	ServiceManagement fi = new ServiceManagement();
    	fi.setQualifiedDublinCoreMetadata(in0, in1);
    }

   /* (non-Javadoc)
    * @see de.nrw.dipp.dippCore.www.definitions.ContentModel#setNewJournal(de.nrw.dipp.dippCore.webservice.QualifiedDublinCore, java.lang.String, java.lang.String, java.lang.String)
 	*/
    public java.lang.String setNewJournal(de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in0, java.lang.String zopeInstance, java.lang.String oaiSetName, java.lang.String oaiSetSpec) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewJournal_fault {
    	if (in0 == null){
    		throw new InvalidParamMessage("setNewJournal parameter QualifiedDublinCore is not specified!");
    	}
    	ServiceManagement fi = new ServiceManagement();
    	String pid = fi.createNewJournal(in0, Constant.cPIDNamespaceDipp);
    	fi.addJournalEntryToConfig(pid, in0, zopeInstance, oaiSetName, oaiSetSpec);
       	return pid;
    }

    /**
     * <p><em>Title: </em></p>
     * <p>Description: </p>
     * @deprecated Version for compatibility to DIPP2
     */
    public java.lang.String setNewJournal(de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in0) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewJournal_fault {
    	if (in0 == null){
    		throw new InvalidParamMessage("setNewJournal parameter QualifiedDublinCore is not specified!");
    	}
    	ServiceManagement fi = new ServiceManagement();
       	return fi.createNewJournal(in0, Constant.cPIDNamespaceDipp);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setNewArticle(java.lang.String[], java.lang.String, de.nrw.dipp.webservice.QualifiedDublinCore, java.lang.String, java.lang.String, java.lang.String[])
     */
    public java.lang.String setNewArticle(java.lang.String[] in0, java.lang.String in1, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in2, java.lang.String nativeDocIdent, java.lang.String storageType, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewArticle_fault {
    	if (in0 == null){
    		throw new RemoteException("setNewArticle parameter Container is not specified!");
    	}else if (in1 == null){
    		throw new RemoteException("setNewArticle parameter journalPID is not specified!");
    	}else if (in2 == null){
    		throw new RemoteException("setNewArticle parameter QualifiedDublinCore is not specified!");
    	}else if (nativeDocIdent == null){
    		throw new RemoteException("setNewArticle parameter native document identifier is not specified!");
    	}else if (in2.getBibliographicCitation().length == 0){
    		throw new RemoteException("setNewArticle bibliographic citation must be set!");
    	}else if (storageType == null){
    		throw new RemoteException("setNewArticle parameter StorageType is not specified!");
    	}else if (targetFormat == null){
    		log.debug("No TargetFormat given");
    		//throw new RemoteException("setNewArticle parameter targetFormat is not specified!");    		
    	}
    	ServiceManagement fi = new ServiceManagement();
        return fi.createNewArticle(in0, in1, in2, nativeDocIdent, storageType, targetFormat);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setConvert(java.lang.String, java.lang.String, java.lang.String[])
     */
    public java.lang.String setConvert(java.lang.String articlePID, java.lang.String sourceURL, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetConvert_fault {
    	log.info("setConvert: articlePID: " + articlePID);
    	log.info("setConvert: sourceURL: " + sourceURL);
    	for (int i = 0; i < targetFormat.length; i++){
    		System.out.println("Targetformat: " + targetFormat[i]);
    	}
    	ServiceManagement fi = new ServiceManagement();
        return fi.convert(articlePID, sourceURL, targetFormat);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setNewContainer(java.lang.String, java.lang.String, java.lang.String)
     */
    public java.lang.String setNewContainer(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewContainer_fault {
    	//TODO: Implement this Method
    	log.error("Method not implemented yet");
    	return null;
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#moveObject(java.lang.String, java.lang.String, java.lang.String)
     */
    public java.lang.String moveObject(java.lang.String objectPID, java.lang.String sourceObjectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException {
    	ServiceManagement fi = new ServiceManagement();
    	log.info("objectID: " + objectPID);
    	log.info("sourceObjectID: " + sourceObjectPID);
    	log.info("destObjectID: " + destObjectPID);
    	return fi.moveObject(objectPID, sourceObjectPID, destObjectPID);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setPublishingState(java.lang.String, boolean, boolean)
     */
    public java.lang.String setPublishingState(java.lang.String articlePID, boolean state, boolean published) throws java.rmi.RemoteException {
    	ServiceManagement fi = new ServiceManagement();
    	String returnValue = "";
    	try{
    		returnValue = fi.setPublishingState(articlePID, state, published);
    		
    	}catch(IOException ioExc){
    		throw new RemoteException(ioExc.getMessage());
    		
    	}catch(XmlException xmlExc){
    		throw new RemoteException(xmlExc.getMessage());
    	}
        return returnValue;
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setNewDirectory(de.nrw.dipp.webservice.AdministrativeMetadata)
     */
    public java.lang.String setNewDirectory(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException {
    	log.info("absURL: " + admProperties.getAbsoluteURL());
    	log.info("chunkURL: " + admProperties.getChunkURL());
    	log.info("metaName: " + admProperties.getMetaName());
    	log.info("metaType: " + admProperties.getMetaType());
    	
    	/*String[] childs = admProperties.getChildList();
    	for (int i = 0; i < childs.length; i++){
        	log.info("child: " + childs[i]);
        }
    	log.info("Erstes Array");
    	String[] parents = admProperties.getParentList();
    	for (int i = 0; i < parents.length; i++){
        	log.info("parent: " + parents[i]);
        }
    	log.info("dippSoapBindingImpl: Zweites Array");*/
    	
    	ServiceManagement fi = new ServiceManagement();
    	return fi.createNewDirectory(admProperties);
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#setAdministrativeMetadata(de.nrw.dipp.webservice.AdministrativeMetadata)
     */
    public void setAdministrativeMetadata(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetAdministrativeMetadata_fault {
    	//TODO: Implement this Method
    	log.error("Method not implemented yet");
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.www.definitions.ContentModel#getAdministrativeMetadata(java.lang.String)
     */
    public de.nrw.dipp.dippCore.webservice.AdministrativeMetadata getAdministrativeMetadata(java.lang.String in0) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.GetAdministrativeMetadata_fault {
    	//TODO: Implement this Method
    	log.error("Method not implemented yet");
        return null;
    }

    /* (non-Javadoc)
     * @see de.nrw.dipp.dippCore.www.definitions.ContentModel#findObjects(java.lang.String[], org.apache.axis.types.NonNegativeInteger, de.nrw.dipp.dippCore.webservice.ConditionType[])
     */
    public de.nrw.dipp.dippCore.webservice.FieldSearchResult findObjects(java.lang.String[] resultFieldArray, org.apache.axis.types.NonNegativeInteger maxResult, de.nrw.dipp.dippCore.webservice.ConditionType[] searchConditionList) throws java.rmi.RemoteException {
    	ServiceManagement fi = new ServiceManagement();
    	return fi.findObjects(resultFieldArray, maxResult, searchConditionList);
    }

}
