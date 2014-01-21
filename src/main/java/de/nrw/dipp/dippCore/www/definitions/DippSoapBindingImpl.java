/**
 * DippSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.www.definitions;

public class DippSoapBindingImpl implements de.nrw.dipp.dippCore.www.definitions.ContentModel{
    public de.nrw.dipp.dippCore.webservice.ExtendedMetadata getArticleContentMetadata(java.lang.String in0) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippCore.webservice.QualifiedDublinCore getQualifiedDublinCore(java.lang.String in0) throws java.rmi.RemoteException {
        return null;
    }

    public void setQualifiedDublinCore(java.lang.String in0, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in1) throws java.rmi.RemoteException {
    }

    public java.lang.String setNewJournal(de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in0, java.lang.String zopeInstance, java.lang.String oaiSetName, java.lang.String oaiSetSpec) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewJournal_fault {
        return null;
    }

    public java.lang.String setNewArticle(java.lang.String[] in0, java.lang.String in1, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in2, java.lang.String nativeDocIdent, java.lang.String storageType, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewArticle_fault {
        return null;
    }

    public java.lang.String setConvert(java.lang.String articlePID, java.lang.String sourceURL, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetConvert_fault {
        return null;
    }

    public java.lang.String setNewContainer(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewContainer_fault {
        return null;
    }

    public java.lang.String setNewComplexDocument(java.lang.String isPartOfPID, java.lang.String isVersionOfPID, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore qdc) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String copyObject(java.lang.String objectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String moveObject(java.lang.String objectPID, java.lang.String sourceObjectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String setPublishingState(java.lang.String articlePID, boolean state, boolean published) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String setNewDirectory(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException {
        return null;
    }

    public void setAdministrativeMetadata(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetAdministrativeMetadata_fault {
    }

    public de.nrw.dipp.dippCore.webservice.AdministrativeMetadata getAdministrativeMetadata(java.lang.String in0) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.GetAdministrativeMetadata_fault {
        return null;
    }

    public de.nrw.dipp.dippCore.webservice.FieldSearchResult findObjects(java.lang.String[] resultFieldArray, org.apache.axis.types.NonNegativeInteger maxResult, de.nrw.dipp.dippCore.webservice.ConditionType[] searchConditionList) throws java.rmi.RemoteException {
        return null;
    }

}
