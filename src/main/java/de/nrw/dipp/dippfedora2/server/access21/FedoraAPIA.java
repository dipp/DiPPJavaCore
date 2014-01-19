/**
 * FedoraAPIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.access21;

public interface FedoraAPIA extends java.rmi.Remote {

    /**
     * Gets key metadata fields that describe the Fedora 
     * 				repository server including repository name, version, baseURL,
     * 				pid namespace, sample request URLs.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.RepositoryInfo describeRepository() throws java.rmi.RemoteException;

    /**
     * Gets a profile of the object which includes key
     * 				metadata fields and URLs for the object Dissemination 
     * 				Index and the object Item Index.  Can be thought of as a
     * 				default view of the object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectProfile getObjectProfile(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Inquires upon all object Disseminators to obtain Behavior Definition
     * pids,
     * 				and methodNames supported by a digital object.  This returns a
     * set of method definitions
     * 				that represent all possible disseminations that can be run on
     * the object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectMethodsDef[] listMethods(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Inquires upon all object Datastreams to obtain datastreams
     * contained
     * 				by a digital object.  This returns a set of datastream locations
     * 				that represent all possible datastreams available in the object.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamDef[] listDatastreams(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Gets a datastream in the digital object.  This is
     *             a shorter, convenience method for getDissemination 
     *             http://hostname:port/fedora/get/pid/fedora-system:3/getItem?itemID=dsID.
     * Note that if a particular date/timestamped view of a datastream is
     * needed, getDissemination still must be used.  When making direct 
     *             links to datastreams within web pages or applications,
     * this
     *             syntax is recommended.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDatastreamDissemination(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Gets a MIME-typed view of a digital object,
     * 				given the behavior definition id, a behavior method name, and
     * any behavior 
     * 				method parameters.	Information about behavior method names and
     * parameters is
     * 				obtained via other APA-A operations	(see getObjectMethods,	getBehaviorMethods,
     * 				and getBehaviorMethodsXML). The getDissemination operation essentially
     * 				encapsulates another operation (a behavior method on the Fedora
     * object). 
     * 				The getDissemination operation hides from the client the details
     * of
     * 				how a behavior method is fulfilled (i.e., what behavior mechanism
     * 
     * 				performs the work, and how the request is invoked upon the behavior
     * 				mechanism).
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDissemination(java.lang.String pid, java.lang.String bDefPid, java.lang.String methodName, de.nrw.dipp.dippfedora2.server.types.gen21.Property[] parameters, java.lang.String asOfDateTime) throws java.rmi.RemoteException;

    /**
     * Gets the requested ObjectFields on all objects in the
     *                 repository matching the given criteria.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult findObjects(java.lang.String[] resultFields, org.apache.axis.types.NonNegativeInteger maxResults, de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchQuery query) throws java.rmi.RemoteException;

    /**
     * Gets the next list of results from a truncated
     *                 findObjects response.
     */
    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult resumeFindObjects(java.lang.String sessionToken) throws java.rmi.RemoteException;

    /**
     * Gets a list of timestamps indicating when components changed
     * in an object.  This is a set of timestamps indicating when a datastream
     * or disseminator was created or modified in the object. These timestamps
     * can be used to request a timestamped dissemination request to view
     * the object as it appeared at a specific point in time.
     */
    public java.lang.String[] getObjectHistory(java.lang.String pid) throws java.rmi.RemoteException;
}
