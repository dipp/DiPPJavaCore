/**
 * FedoraAPIMBindingSOAPHTTPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.management21;

public class FedoraAPIMBindingSOAPHTTPImpl implements de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM{
    public java.lang.String ingest(byte[] XML, java.lang.String format, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String ingestObject(byte[] METSXML, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.UserInfo describeUser(java.lang.String id) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String modifyObject(java.lang.String pid, java.lang.String state, java.lang.String label, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public byte[] getObjectXML(java.lang.String pid) throws java.rmi.RemoteException {
        return null;
    }

    public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) throws java.rmi.RemoteException {
        return null;
    }

    public byte[] exportObject(java.lang.String pid) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String purgeObject(java.lang.String pid, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String addDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String controlGroup, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String addDisseminator(java.lang.String pid, java.lang.String bDefPid, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String modifyDatastreamByReference(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String modifyDatastreamByValue(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, byte[] dsContent, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String modifyDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String setDatastreamState(java.lang.String pid, java.lang.String dsID, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String setDisseminatorState(java.lang.String pid, java.lang.String dissID, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream getDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreams(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dsState) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreamHistory(java.lang.String pid, java.lang.String dsID) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator getDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminators(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dissState) throws java.rmi.RemoteException {
        return null;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminatorHistory(java.lang.String pid, java.lang.String dissID) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] purgeDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String endDT, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] purgeDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String endDT, java.lang.String logMessage) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getNextPID(org.apache.axis.types.NonNegativeInteger numPids, java.lang.String pidNamespace) throws java.rmi.RemoteException {
        return null;
    }

}
