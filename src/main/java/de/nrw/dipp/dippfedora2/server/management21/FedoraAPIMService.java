/**
 * FedoraAPIMService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.management21;

public interface FedoraAPIMService extends javax.xml.rpc.Service {
    public java.lang.String getFedoraAPIMPortSOAPHTTPSAddress();

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTPS() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getFedoraAPIMPortSOAPHTTPAddress();

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTP() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
