/**
 * FedoraAPIMService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.management31;

public interface FedoraAPIMService extends javax.xml.rpc.Service {
    public java.lang.String getFedoraAPIMPortSOAPHTTPSAddress();

    public de.nrw.dipp.dippfedora3.server.management31.FedoraAPIM getFedoraAPIMPortSOAPHTTPS() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora3.server.management31.FedoraAPIM getFedoraAPIMPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getFedoraAPIMPortSOAPHTTPAddress();

    public de.nrw.dipp.dippfedora3.server.management31.FedoraAPIM getFedoraAPIMPortSOAPHTTP() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora3.server.management31.FedoraAPIM getFedoraAPIMPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
