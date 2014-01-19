/**
 * FedoraAPIM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.management21;

public interface FedoraAPIM extends java.rmi.Remote {

    /**
     * Creates a new digital object in the repository, given the 
     *       data in the provided XML document, and a format indentifier
     * for the XML document format.  
     *       The valid formats are currently: "foxml1.0" and "metslikefedora1".
     * The object's initial 
     *       state will be A (active).   If the XML document does not specify
     * the
     *       OBJID attribute of the root element, the repository will generate
     * and return a new pid 
     *       for the object resulting from this request.  That pid will have
     * the namespace of 
     *       the repository.  If the XML document specifies a pid, it will
     * be assigned 
     *       to the digital object provided that 1) it conforms to the Fedora
     * pid 
     *       Syntax, 2) it uses a namespace that matches the "retainPIDs"
     * value
     *       configured for the repository, and 3) 
     *       it does not collide with an existing pid of an object in the
     * repository.
     */
    public java.lang.String ingest(byte[] XML, java.lang.String format, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * DEPRECATED IN FEDORA 2.0. USE ingest.  
     * 			Creates a new digital object in the repository, given the data
     * in the provided
     * 			XML document, 	and a format indentifier for the METS document format.
     * 
     * 			The object's initial  state will be A (active).   If the XML document
     * does not 
     * 			specify a pid in the OBJID attribute of the  root element, the
     * repository will 
     * 			generate and return a new pid for the object resulting from this
     * request.  
     * 			That pid will have the namespace of the repository.  If the XML
     * document 
     * 			specifies a pid, it will be assigned to the digital object provided
     * that 
     * 			1) it conforms to the Fedora pid Syntax, 2) it uses a namespace
     * that matches
     *             the "retainPIDs" value configured for the repository,
     * and 3) it 
     *             does not collide with an existing pid of an object in
     * 
     * 			the repository.
     */
    public java.lang.String ingestObject(byte[] METSXML, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Gets key information about the named user.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.UserInfo describeUser(java.lang.String id) throws java.rmi.RemoteException;

    /**
     * Changes the state and/or label of the object.
     */
    public java.lang.String modifyObject(java.lang.String pid, java.lang.String state, java.lang.String label, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Gets the XML portion of the entire METS-encoded 
     *       digital object for external use (viewing, editing, moving to
     * another 
     *       repository).  XML metadata datastreams will be included inline,
     * content 
     *       datastreams will not be included, and external datastreams will
     * be 
     *       referenced by url.
     */
    public byte[] getObjectXML(java.lang.String pid) throws java.rmi.RemoteException;

    /**
     * Exports the entire digital object in the specified XML
     * 			format ("foxml1.0" or "metslikefedora1"), and encoded appropriately
     * 
     * 			for the specified export context ("public", "migrate", or "archive").
     */
    public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) throws java.rmi.RemoteException;

    /**
     * DEPRECATED IN FEDORA 2.0. USE export.
     */
    public byte[] exportObject(java.lang.String pid) throws java.rmi.RemoteException;

    /**
     * Permanently removes an object from the repository.
     */
    public java.lang.String purgeObject(java.lang.String pid, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException;

    /**
     * Creates a new datastream in the object.
     */
    public java.lang.String addDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String controlGroup, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Creates a disseminator in the object.
     */
    public java.lang.String addDisseminator(java.lang.String pid, java.lang.String bDefPid, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Modifies an existing datastream in an object, by reference.
     */
    public java.lang.String modifyDatastreamByReference(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException;

    /**
     * Modifies an existing datastream in an object, by value.
     */
    public java.lang.String modifyDatastreamByValue(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, byte[] dsContent, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException;

    /**
     * Modifies a disseminator in the object.
     */
    public java.lang.String modifyDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException;

    /**
     * Sets the state of a datastream to the specified state value.
     */
    public java.lang.String setDatastreamState(java.lang.String pid, java.lang.String dsID, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Sets the state of a disseminator to the specified value.
     */
    public java.lang.String setDisseminatorState(java.lang.String pid, java.lang.String dissID, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Gets a datastream in an object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream getDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Gets all the datastreams in an object as of a certain date/time
     * and in a certain state.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreams(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dsState) throws java.rmi.RemoteException;

    /**
     * Gets all versions of a datastream, sorted from most to least
     * recent.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreamHistory(java.lang.String pid, java.lang.String dsID) throws java.rmi.RemoteException;

    /**
     * Gets a disseminator in an object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator getDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Gets all disseminators in an object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminators(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dissState) throws java.rmi.RemoteException;

    /**
     * Gets the creation dates of each version of a disseminator.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminatorHistory(java.lang.String pid, java.lang.String dissID) throws java.rmi.RemoteException;

    /**
     * Permanently removes one or more versions of a datastream from
     * an object.
     */
    public java.lang.String[] purgeDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String endDT, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException;

    /**
     * Permanently removes one or more versions of a disseminator
     * from an object.
     */
    public java.lang.String[] purgeDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String endDT, java.lang.String logMessage) throws java.rmi.RemoteException;

    /**
     * Retrieves the specified number of next available pid(s) for
     * a given pid namespace.
     */
    public java.lang.String[] getNextPID(org.apache.axis.types.NonNegativeInteger numPids, java.lang.String pidNamespace) throws java.rmi.RemoteException;
}
