/**
 * FedoraAPIABindingSOAPHTTPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.access31;

public class FedoraAPIABindingSOAPHTTPImpl implements de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA{
    public de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo describeRepository() throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile getObjectProfile(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[] listMethods(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[] listDatastreams(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream getDatastreamDissemination(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream getDissemination(java.lang.String pid, java.lang.String serviceDefinitionPid, java.lang.String methodName, de.nrw.dipp.dippfedora3.server.types.gen31.Property[] parameters, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult findObjects(java.lang.String[] resultFields, org.apache.axis.types.NonNegativeInteger maxResults, de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery query) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult resumeFindObjects(java.lang.String sessionToken) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getObjectHistory(java.lang.String pid) throws java.rmi.RemoteException {
        return null;
    }

}
