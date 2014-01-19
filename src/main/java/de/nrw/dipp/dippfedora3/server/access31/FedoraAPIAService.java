/**
 * FedoraAPIAService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.access31;

public interface FedoraAPIAService extends javax.xml.rpc.Service {
    public java.lang.String getFedoraAPIAPortSOAPHTTPSAddress();

    public de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA getFedoraAPIAPortSOAPHTTPS() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA getFedoraAPIAPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getFedoraAPIAPortSOAPHTTPAddress();

    public de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA getFedoraAPIAPortSOAPHTTP() throws javax.xml.rpc.ServiceException;

    public de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA getFedoraAPIAPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
