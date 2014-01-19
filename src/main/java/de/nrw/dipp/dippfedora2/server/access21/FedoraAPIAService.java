/**
 * FedoraAPIAService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.access21;

public interface FedoraAPIAService extends javax.xml.rpc.Service {
    public java.lang.String getFedoraAPIAPortSOAPHTTPAddress();

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTP() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getFedoraAPIAPortSOAPHTTPSAddress();

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTPS() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
