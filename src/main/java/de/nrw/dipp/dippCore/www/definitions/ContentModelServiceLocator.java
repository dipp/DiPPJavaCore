/**
 * ContentModelServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.www.definitions;

public class ContentModelServiceLocator extends org.apache.axis.client.Service implements de.nrw.dipp.dippCore.www.definitions.ContentModelService {

    public ContentModelServiceLocator() {
    }


    public ContentModelServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContentModelServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for dipp
    private java.lang.String dipp_address = "http://127.0.0.1:9180/dipp3/services/dipp";

    public java.lang.String getdippAddress() {
        return dipp_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String dippWSDDServiceName = "dipp";

    public java.lang.String getdippWSDDServiceName() {
        return dippWSDDServiceName;
    }

    public void setdippWSDDServiceName(java.lang.String name) {
        dippWSDDServiceName = name;
    }

    public de.nrw.dipp.dippCore.www.definitions.ContentModel getdipp() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(dipp_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdipp(endpoint);
    }

    public de.nrw.dipp.dippCore.www.definitions.ContentModel getdipp(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.dipp.dippCore.www.definitions.DippSoapBindingStub _stub = new de.nrw.dipp.dippCore.www.definitions.DippSoapBindingStub(portAddress, this);
            _stub.setPortName(getdippWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdippEndpointAddress(java.lang.String address) {
        dipp_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.nrw.dipp.dippCore.www.definitions.ContentModel.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.dipp.dippCore.www.definitions.DippSoapBindingStub _stub = new de.nrw.dipp.dippCore.www.definitions.DippSoapBindingStub(new java.net.URL(dipp_address), this);
                _stub.setPortName(getdippWSDDServiceName());
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
        if ("dipp".equals(inputPortName)) {
            return getdipp();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.dippCore.dipp.nrw.de/definitions/", "ContentModelService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.dippCore.dipp.nrw.de/definitions/", "dipp"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("dipp".equals(portName)) {
            setdippEndpointAddress(address);
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
