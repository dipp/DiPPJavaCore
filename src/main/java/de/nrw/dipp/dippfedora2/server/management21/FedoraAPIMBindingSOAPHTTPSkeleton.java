/**
 * FedoraAPIMBindingSOAPHTTPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.management21;

public class FedoraAPIMBindingSOAPHTTPSkeleton implements de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM, org.apache.axis.wsdl.Skeleton {
    private de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "XML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "format"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("ingest", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "ingest"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#ingest");
        _myOperationsList.add(_oper);
        if (_myOperations.get("ingest") == null) {
            _myOperations.put("ingest", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("ingest")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "METSXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("ingestObject", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "ingestObject"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#ingestObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("ingestObject") == null) {
            _myOperations.put("ingestObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("ingestObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("describeUser", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "UserInfo"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "describeUser"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#describeUser");
        _myOperationsList.add(_oper);
        if (_myOperations.get("describeUser") == null) {
            _myOperations.put("describeUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("describeUser")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "state"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "label"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modifyObject", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "modifyObject"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#modifyObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modifyObject") == null) {
            _myOperations.put("modifyObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modifyObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getObjectXML", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getObjectXML"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getObjectXML");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getObjectXML") == null) {
            _myOperations.put("getObjectXML", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getObjectXML")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "format"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "context"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("export", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "export"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#export");
        _myOperationsList.add(_oper);
        if (_myOperations.get("export") == null) {
            _myOperations.put("export", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("export")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("exportObject", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "exportObject"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#exportObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("exportObject") == null) {
            _myOperations.put("exportObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("exportObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "force"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("purgeObject", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "purgeObject"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#purgeObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("purgeObject") == null) {
            _myOperations.put("purgeObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("purgeObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "altIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "versionable"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "MIMEType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "formatURI"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsLocation"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "controlGroup"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addDatastream", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "addDatastream"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#addDatastream");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addDatastream") == null) {
            _myOperations.put("addDatastream", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addDatastream")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bDefPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bMechPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bindingMap"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamBindingMap"), de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addDisseminator", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "addDisseminator"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#addDisseminator");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addDisseminator") == null) {
            _myOperations.put("addDisseminator", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addDisseminator")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "altIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "versionable"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "MIMEType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "formatURI"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsLocation"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "force"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modifyDatastreamByReference", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "modifyDatastreamByReference"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#modifyDatastreamByReference");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modifyDatastreamByReference") == null) {
            _myOperations.put("modifyDatastreamByReference", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modifyDatastreamByReference")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "altIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "versionable"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "MIMEType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "formatURI"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsContent"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "force"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modifyDatastreamByValue", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "modifyDatastreamByValue"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#modifyDatastreamByValue");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modifyDatastreamByValue") == null) {
            _myOperations.put("modifyDatastreamByValue", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modifyDatastreamByValue")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bMechPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bindingMap"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamBindingMap"), de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "force"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modifyDisseminator", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "modifyDisseminator"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#modifyDisseminator");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modifyDisseminator") == null) {
            _myOperations.put("modifyDisseminator", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modifyDisseminator")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setDatastreamState", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "setDatastreamState"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#setDatastreamState");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setDatastreamState") == null) {
            _myOperations.put("setDatastreamState", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setDatastreamState")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setDisseminatorState", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "setDisseminatorState"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#setDisseminatorState");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setDisseminatorState") == null) {
            _myOperations.put("setDisseminatorState", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setDisseminatorState")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDatastream", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Datastream"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDatastream"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDatastream");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDatastream") == null) {
            _myOperations.put("getDatastream", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDatastream")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDatastreams", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDatastream"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDatastreams"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDatastreams");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDatastreams") == null) {
            _myOperations.put("getDatastreams", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDatastreams")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDatastreamHistory", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDatastream"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDatastreamHistory"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDatastreamHistory");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDatastreamHistory") == null) {
            _myOperations.put("getDatastreamHistory", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDatastreamHistory")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDisseminator", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Disseminator"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDisseminator"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDisseminator");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDisseminator") == null) {
            _myOperations.put("getDisseminator", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDisseminator")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDisseminators", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDisseminator"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDisseminators"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDisseminators");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDisseminators") == null) {
            _myOperations.put("getDisseminators", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDisseminators")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDisseminatorHistory", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDisseminator"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDisseminatorHistory"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDisseminatorHistory");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDisseminatorHistory") == null) {
            _myOperations.put("getDisseminatorHistory", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDisseminatorHistory")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "endDT"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "force"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("purgeDatastream", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDateTimeString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "purgeDatastream"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#purgeDatastream");
        _myOperationsList.add(_oper);
        if (_myOperations.get("purgeDatastream") == null) {
            _myOperations.put("purgeDatastream", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("purgeDatastream")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dissID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "endDT"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "logMessage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("purgeDisseminator", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDateTimeString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "purgeDisseminator"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#purgeDisseminator");
        _myOperationsList.add(_oper);
        if (_myOperations.get("purgeDisseminator") == null) {
            _myOperations.put("purgeDisseminator", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("purgeDisseminator")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "numPids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"), org.apache.axis.types.NonNegativeInteger.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pidNamespace"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getNextPID", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getNextPID"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getNextPID");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getNextPID") == null) {
            _myOperations.put("getNextPID", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getNextPID")).add(_oper);
    }

    public FedoraAPIMBindingSOAPHTTPSkeleton() {
        this.impl = new de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPImpl();
    }

    public FedoraAPIMBindingSOAPHTTPSkeleton(de.nrw.dipp.dippfedora2.server.management21.FedoraAPIM impl) {
        this.impl = impl;
    }
    public java.lang.String ingest(byte[] XML, java.lang.String format, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.ingest(XML, format, logMessage);
        return ret;
    }

    public java.lang.String ingestObject(byte[] METSXML, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.ingestObject(METSXML, logMessage);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.UserInfo describeUser(java.lang.String id) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.UserInfo ret = impl.describeUser(id);
        return ret;
    }

    public java.lang.String modifyObject(java.lang.String pid, java.lang.String state, java.lang.String label, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.modifyObject(pid, state, label, logMessage);
        return ret;
    }

    public byte[] getObjectXML(java.lang.String pid) throws java.rmi.RemoteException
    {
        byte[] ret = impl.getObjectXML(pid);
        return ret;
    }

    public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) throws java.rmi.RemoteException
    {
        byte[] ret = impl.export(pid, format, context);
        return ret;
    }

    public byte[] exportObject(java.lang.String pid) throws java.rmi.RemoteException
    {
        byte[] ret = impl.exportObject(pid);
        return ret;
    }

    public java.lang.String purgeObject(java.lang.String pid, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.purgeObject(pid, logMessage, force);
        return ret;
    }

    public java.lang.String addDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String controlGroup, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.addDatastream(pid, dsID, altIDs, dsLabel, versionable, MIMEType, formatURI, dsLocation, controlGroup, dsState, logMessage);
        return ret;
    }

    public java.lang.String addDisseminator(java.lang.String pid, java.lang.String bDefPid, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.addDisseminator(pid, bDefPid, bMechPid, dissLabel, bindingMap, dissState, logMessage);
        return ret;
    }

    public java.lang.String modifyDatastreamByReference(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, java.lang.String dsLocation, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.modifyDatastreamByReference(pid, dsID, altIDs, dsLabel, versionable, MIMEType, formatURI, dsLocation, dsState, logMessage, force);
        return ret;
    }

    public java.lang.String modifyDatastreamByValue(java.lang.String pid, java.lang.String dsID, java.lang.String[] altIDs, java.lang.String dsLabel, boolean versionable, java.lang.String MIMEType, java.lang.String formatURI, byte[] dsContent, java.lang.String dsState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.modifyDatastreamByValue(pid, dsID, altIDs, dsLabel, versionable, MIMEType, formatURI, dsContent, dsState, logMessage, force);
        return ret;
    }

    public java.lang.String modifyDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String bMechPid, java.lang.String dissLabel, de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamBindingMap bindingMap, java.lang.String dissState, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.modifyDisseminator(pid, dissID, bMechPid, dissLabel, bindingMap, dissState, logMessage, force);
        return ret;
    }

    public java.lang.String setDatastreamState(java.lang.String pid, java.lang.String dsID, java.lang.String dsState, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.setDatastreamState(pid, dsID, dsState, logMessage);
        return ret;
    }

    public java.lang.String setDisseminatorState(java.lang.String pid, java.lang.String dissID, java.lang.String dissState, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.setDisseminatorState(pid, dissID, dissState, logMessage);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream getDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Datastream ret = impl.getDatastream(pid, dsID, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreams(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dsState) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] ret = impl.getDatastreams(pid, asOfDateTime, dsState);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] getDatastreamHistory(java.lang.String pid, java.lang.String dsID) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Datastream[] ret = impl.getDatastreamHistory(pid, dsID);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator getDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator ret = impl.getDisseminator(pid, dissID, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminators(java.lang.String pid, java.lang.String asOfDateTime, java.lang.String dissState) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] ret = impl.getDisseminators(pid, asOfDateTime, dissState);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] getDisseminatorHistory(java.lang.String pid, java.lang.String dissID) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.Disseminator[] ret = impl.getDisseminatorHistory(pid, dissID);
        return ret;
    }

    public java.lang.String[] purgeDatastream(java.lang.String pid, java.lang.String dsID, java.lang.String endDT, java.lang.String logMessage, boolean force) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.purgeDatastream(pid, dsID, endDT, logMessage, force);
        return ret;
    }

    public java.lang.String[] purgeDisseminator(java.lang.String pid, java.lang.String dissID, java.lang.String endDT, java.lang.String logMessage) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.purgeDisseminator(pid, dissID, endDT, logMessage);
        return ret;
    }

    public java.lang.String[] getNextPID(org.apache.axis.types.NonNegativeInteger numPids, java.lang.String pidNamespace) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.getNextPID(numPids, pidNamespace);
        return ret;
    }

}
