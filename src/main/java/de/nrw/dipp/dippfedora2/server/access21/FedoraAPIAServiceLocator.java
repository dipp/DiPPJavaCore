/**
 * FedoraAPIAServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.access21;

public class FedoraAPIAServiceLocator extends org.apache.axis.client.Service implements de.nrw.dipp.dippfedora2.server.access21.FedoraAPIAService {

    public FedoraAPIAServiceLocator() {
    }


    public FedoraAPIAServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FedoraAPIAServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FedoraAPIAPortSOAPHTTP
    private java.lang.String FedoraAPIAPortSOAPHTTP_address = "http://localhost:8080/fedora/services/access";

    public java.lang.String getFedoraAPIAPortSOAPHTTPAddress() {
        return FedoraAPIAPortSOAPHTTP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FedoraAPIAPortSOAPHTTPWSDDServiceName = "Fedora-API-A-Port-SOAPHTTP";

    public java.lang.String getFedoraAPIAPortSOAPHTTPWSDDServiceName() {
        return FedoraAPIAPortSOAPHTTPWSDDServiceName;
    }

    public void setFedoraAPIAPortSOAPHTTPWSDDServiceName(java.lang.String name) {
        FedoraAPIAPortSOAPHTTPWSDDServiceName = name;
    }

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FedoraAPIAPortSOAPHTTP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFedoraAPIAPortSOAPHTTP(endpoint);
    }

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub(portAddress, this);
            _stub.setPortName(getFedoraAPIAPortSOAPHTTPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFedoraAPIAPortSOAPHTTPEndpointAddress(java.lang.String address) {
        FedoraAPIAPortSOAPHTTP_address = address;
    }


    // Use to get a proxy class for FedoraAPIAPortSOAPHTTPS
    private java.lang.String FedoraAPIAPortSOAPHTTPS_address = "https://localhost:8443/fedora/services/access";

    public java.lang.String getFedoraAPIAPortSOAPHTTPSAddress() {
        return FedoraAPIAPortSOAPHTTPS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FedoraAPIAPortSOAPHTTPSWSDDServiceName = "Fedora-API-A-Port-SOAPHTTPS";

    public java.lang.String getFedoraAPIAPortSOAPHTTPSWSDDServiceName() {
        return FedoraAPIAPortSOAPHTTPSWSDDServiceName;
    }

    public void setFedoraAPIAPortSOAPHTTPSWSDDServiceName(java.lang.String name) {
        FedoraAPIAPortSOAPHTTPSWSDDServiceName = name;
    }

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTPS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FedoraAPIAPortSOAPHTTPS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFedoraAPIAPortSOAPHTTPS(endpoint);
    }

    public de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA getFedoraAPIAPortSOAPHTTPS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub(portAddress, this);
            _stub.setPortName(getFedoraAPIAPortSOAPHTTPSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFedoraAPIAPortSOAPHTTPSEndpointAddress(java.lang.String address) {
        FedoraAPIAPortSOAPHTTPS_address = address;
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
            if (de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub(new java.net.URL(FedoraAPIAPortSOAPHTTP_address), this);
                _stub.setPortName(getFedoraAPIAPortSOAPHTTPWSDDServiceName());
                return _stub;
            }
            if (de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub _stub = new de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub(new java.net.URL(FedoraAPIAPortSOAPHTTPS_address), this);
                _stub.setPortName(getFedoraAPIAPortSOAPHTTPSWSDDServiceName());
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
        if ("Fedora-API-A-Port-SOAPHTTP".equals(inputPortName)) {
            return getFedoraAPIAPortSOAPHTTP();
        }
        else if ("Fedora-API-A-Port-SOAPHTTPS".equals(inputPortName)) {
            return getFedoraAPIAPortSOAPHTTPS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Port-SOAPHTTP"));
            ports.add(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Port-SOAPHTTPS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FedoraAPIAPortSOAPHTTP".equals(portName)) {
            setFedoraAPIAPortSOAPHTTPEndpointAddress(address);
        }
        else 
if ("FedoraAPIAPortSOAPHTTPS".equals(portName)) {
            setFedoraAPIAPortSOAPHTTPSEndpointAddress(address);
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
