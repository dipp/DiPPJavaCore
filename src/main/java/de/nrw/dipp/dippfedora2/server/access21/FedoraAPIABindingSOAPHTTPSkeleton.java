/**
 * FedoraAPIABindingSOAPHTTPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora2.server.access21;

public class FedoraAPIABindingSOAPHTTPSkeleton implements de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA, org.apache.axis.wsdl.Skeleton {
    private de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA impl;
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
        };
        _oper = new org.apache.axis.description.OperationDesc("describeRepository", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RepositoryInfo"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "describeRepository"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#describeRepository");
        _myOperationsList.add(_oper);
        if (_myOperations.get("describeRepository") == null) {
            _myOperations.put("describeRepository", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("describeRepository")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getObjectProfile", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectProfile"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getObjectProfile"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getObjectProfile");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getObjectProfile") == null) {
            _myOperations.put("getObjectProfile", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getObjectProfile")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listMethods", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfObjectMethodsDef"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "listMethods"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#listMethods");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listMethods") == null) {
            _myOperations.put("listMethods", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listMethods")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listDatastreams", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfDatastreamDef"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "listDatastreams"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#listDatastreams");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listDatastreams") == null) {
            _myOperations.put("listDatastreams", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listDatastreams")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDatastreamDissemination", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDatastreamDissemination"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDatastreamDissemination");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDatastreamDissemination") == null) {
            _myOperations.put("getDatastreamDissemination", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDatastreamDissemination")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bDefPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "methodName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfProperty"), de.nrw.dipp.dippfedora2.server.types.gen21.Property[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDissemination", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getDissemination"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getDissemination");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDissemination") == null) {
            _myOperations.put("getDissemination", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDissemination")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "resultFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "maxResults"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"), org.apache.axis.types.NonNegativeInteger.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "query"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchQuery"), de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchQuery.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("findObjects", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "findObjects"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#findObjects");
        _myOperationsList.add(_oper);
        if (_myOperations.get("findObjects") == null) {
            _myOperations.put("findObjects", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("findObjects")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("resumeFindObjects", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "resumeFindObjects"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#resumeFindObjects");
        _myOperationsList.add(_oper);
        if (_myOperations.get("resumeFindObjects") == null) {
            _myOperations.put("resumeFindObjects", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("resumeFindObjects")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getObjectHistory", _params, new javax.xml.namespace.QName("", "response"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/api/", "getObjectHistory"));
        _oper.setSoapAction("http://www.fedora.info/definitions/1/0/api/#getObjectHistory");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getObjectHistory") == null) {
            _myOperations.put("getObjectHistory", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getObjectHistory")).add(_oper);
    }

    public FedoraAPIABindingSOAPHTTPSkeleton() {
        this.impl = new de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPImpl();
    }

    public FedoraAPIABindingSOAPHTTPSkeleton(de.nrw.dipp.dippfedora2.server.access21.FedoraAPIA impl) {
        this.impl = impl;
    }
    public de.nrw.dipp.dippfedora2.server.types.gen21.RepositoryInfo describeRepository() throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.RepositoryInfo ret = impl.describeRepository();
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectProfile getObjectProfile(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.ObjectProfile ret = impl.getObjectProfile(pid, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.ObjectMethodsDef[] listMethods(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.ObjectMethodsDef[] ret = impl.listMethods(pid, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamDef[] listDatastreams(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamDef[] ret = impl.listDatastreams(pid, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDatastreamDissemination(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream ret = impl.getDatastreamDissemination(pid, dsID, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream getDissemination(java.lang.String pid, java.lang.String bDefPid, java.lang.String methodName, de.nrw.dipp.dippfedora2.server.types.gen21.Property[] parameters, java.lang.String asOfDateTime) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream ret = impl.getDissemination(pid, bDefPid, methodName, parameters, asOfDateTime);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult findObjects(java.lang.String[] resultFields, org.apache.axis.types.NonNegativeInteger maxResults, de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchQuery query) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult ret = impl.findObjects(resultFields, maxResults, query);
        return ret;
    }

    public de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult resumeFindObjects(java.lang.String sessionToken) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult ret = impl.resumeFindObjects(sessionToken);
        return ret;
    }

    public java.lang.String[] getObjectHistory(java.lang.String pid) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.getObjectHistory(pid);
        return ret;
    }

}
