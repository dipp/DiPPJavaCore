/**
 * FedoraAPIABindingSOAPHTTPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.access21;

public class FedoraAPIABindingSOAPHTTPImpl implements de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA{
    public de.nrw.dipp.dippfedora2.server.types.gen21.RepositoryInfo describeRepository() throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectProfile getObjectProfile(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectMethodsDef[] listMethods(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamDef[] listDatastreams(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDatastreamDissemination(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDissemination(java.lang.String pid, java.lang.String bDefPid, java.lang.String methodName, de.nrw.dipp.dippfedora2.server.types.gen21.Property[] parameters, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult findObjects(java.lang.String[] resultFields, org.apache.axis.types.NonNegativeInteger maxResults, de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchQuery query) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult resumeFindObjects(java.lang.String sessionToken) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getObjectHistory(java.lang.String pid) throws java.rmi.RemoteException {
        return null;
    }

}
