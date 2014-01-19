/**
 * FedoraAPIMServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.management21;

public class FedoraAPIMServiceLocator extends org.apache.axis.client.Service implements de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMService {

    public FedoraAPIMServiceLocator() {
    }


    public FedoraAPIMServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FedoraAPIMServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FedoraAPIMPortSOAPHTTPS
    private java.lang.String FedoraAPIMPortSOAPHTTPS_address = "https://localhost:8443/fedora/services/management";

    public java.lang.String getFedoraAPIMPortSOAPHTTPSAddress() {
        return FedoraAPIMPortSOAPHTTPS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FedoraAPIMPortSOAPHTTPSWSDDServiceName = "Fedora-API-M-Port-SOAPHTTPS";

    public java.lang.String getFedoraAPIMPortSOAPHTTPSWSDDServiceName() {
        return FedoraAPIMPortSOAPHTTPSWSDDServiceName;
    }

    public void setFedoraAPIMPortSOAPHTTPSWSDDServiceName(java.lang.String name) {
        FedoraAPIMPortSOAPHTTPSWSDDServiceName = name;
    }

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTPS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FedoraAPIMPortSOAPHTTPS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFedoraAPIMPortSOAPHTTPS(endpoint);
    }

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub(portAddress, this);
            _stub.setPortName(getFedoraAPIMPortSOAPHTTPSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFedoraAPIMPortSOAPHTTPSEndpointAddress(java.lang.String address) {
        FedoraAPIMPortSOAPHTTPS_address = address;
    }


    // Use to get a proxy class for FedoraAPIMPortSOAPHTTP
    private java.lang.String FedoraAPIMPortSOAPHTTP_address = "http://localhost:8080/fedora/services/management";

    public java.lang.String getFedoraAPIMPortSOAPHTTPAddress() {
        return FedoraAPIMPortSOAPHTTP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FedoraAPIMPortSOAPHTTPWSDDServiceName = "Fedora-API-M-Port-SOAPHTTP";

    public java.lang.String getFedoraAPIMPortSOAPHTTPWSDDServiceName() {
        return FedoraAPIMPortSOAPHTTPWSDDServiceName;
    }

    public void setFedoraAPIMPortSOAPHTTPWSDDServiceName(java.lang.String name) {
        FedoraAPIMPortSOAPHTTPWSDDServiceName = name;
    }

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FedoraAPIMPortSOAPHTTP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFedoraAPIMPortSOAPHTTP(endpoint);
    }

    public de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM getFedoraAPIMPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub(portAddress, this);
            _stub.setPortName(getFedoraAPIMPortSOAPHTTPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFedoraAPIMPortSOAPHTTPEndpointAddress(java.lang.String address) {
        FedoraAPIMPortSOAPHTTP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub(new java.net.URL(FedoraAPIMPortSOAPHTTPS_address), this);
                _stub.setPortName(getFedoraAPIMPortSOAPHTTPSWSDDServiceName());
                return _stub;
            }
            if (de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub(new java.net.URL(FedoraAPIMPortSOAPHTTP_address), this);
                _stub.setPortName(getFedoraAPIMPortSOAPHTTPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Fedora-API-M-Port-SOAPHTTPS".equals(inputPortName)) {
            return getFedoraAPIMPortSOAPHTTPS();
        }
        else if ("Fedora-API-M-Port-SOAPHTTP".equals(inputPortName)) {
            return getFedoraAPIMPortSOAPHTTP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Port-SOAPHTTPS"));
            ports.add(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Port-SOAPHTTP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FedoraAPIMPortSOAPHTTPS".equals(portName)) {
            setFedoraAPIMPortSOAPHTTPSEndpointAddress(address);
        }
        else 
if ("FedoraAPIMPortSOAPHTTP".equals(portName)) {
            setFedoraAPIMPortSOAPHTTPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
