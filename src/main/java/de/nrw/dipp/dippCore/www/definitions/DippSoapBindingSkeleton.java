/**
 * DippSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.www.definitions;

public class DippSoapBindingSkeleton implements de.nrw.dipp.dippCore.www.definitions.ContentModel, org.apache.axis.wsdl.Skeleton {
    private de.nrw.dipp.dippCore.www.definitions.ContentModel impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getArticleContentMetadata", _params, new javax.xml.namespace.QName("", "ExtendedMetadata"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "ExtendedMetadata"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "getArticleContentMetadata"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/getArticleContentMetadata");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getArticleContentMetadata") == null) {
            _myOperations.put("getArticleContentMetadata", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getArticleContentMetadata")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getQualifiedDublinCore", _params, new javax.xml.namespace.QName("", "QualifiedDublinCore"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "getQualifiedDublinCore"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/getQualifiedDublinCore");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getQualifiedDublinCore") == null) {
            _myOperations.put("getQualifiedDublinCore", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getQualifiedDublinCore")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"), de.nrw.dipp.dippCore.webservice.QualifiedDublinCore.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setQualifiedDublinCore", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setQualifiedDublinCore"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setQualifiedDublinCore");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setQualifiedDublinCore") == null) {
            _myOperations.put("setQualifiedDublinCore", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setQualifiedDublinCore")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"), de.nrw.dipp.dippCore.webservice.QualifiedDublinCore.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "zopeInstance"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "oaiSetName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "oaiSetSpec"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setNewJournal", _params, new javax.xml.namespace.QName("", "setNewJournalReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewJournal"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setNewJournal");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setNewJournal") == null) {
            _myOperations.put("setNewJournal", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setNewJournal")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewJournal_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.SetNewJournal_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setNewJournal_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"), de.nrw.dipp.dippCore.webservice.QualifiedDublinCore.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "nativeDocIdent"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storageType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "targetFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setNewArticle", _params, new javax.xml.namespace.QName("", "setNewArticleReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewArticle"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setNewArticle");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setNewArticle") == null) {
            _myOperations.put("setNewArticle", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setNewArticle")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewArticle_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.SetNewArticle_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setNewArticle_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "articlePID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sourceURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "targetFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setConvert", _params, new javax.xml.namespace.QName("", "setConvertReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setConvert"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setConvert");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setConvert") == null) {
            _myOperations.put("setConvert", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setConvert")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setConvert_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.SetConvert_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setConvert_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setNewContainer", _params, new javax.xml.namespace.QName("", "setNewContainerReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewContainer"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setNewContainer");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setNewContainer") == null) {
            _myOperations.put("setNewContainer", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setNewContainer")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewContainer_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.SetNewContainer_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setNewContainer_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "isPartOfPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "isVersionOfPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "qdc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"), de.nrw.dipp.dippCore.webservice.QualifiedDublinCore.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setNewComplexDocument", _params, new javax.xml.namespace.QName("", "createNewComplexDocumentReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewComplexDocument"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setNewComplexDocument");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setNewComplexDocument") == null) {
            _myOperations.put("setNewComplexDocument", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setNewComplexDocument")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "destObjectPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("copyObject", _params, new javax.xml.namespace.QName("", "copyObjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "copyObject"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/copyObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("copyObject") == null) {
            _myOperations.put("copyObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("copyObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "objectPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sourceObjectPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "destObjectPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("moveObject", _params, new javax.xml.namespace.QName("", "moveObjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "moveObject"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/moveObject");
        _myOperationsList.add(_oper);
        if (_myOperations.get("moveObject") == null) {
            _myOperations.put("moveObject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("moveObject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "articlePID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "state"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "published"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setPublishingState", _params, new javax.xml.namespace.QName("", "setPublishingStateReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setPublishingState"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setPublishingState");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setPublishingState") == null) {
            _myOperations.put("setPublishingState", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setPublishingState")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "admProperties"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "AdministrativeMetadata"), de.nrw.dipp.dippCore.webservice.AdministrativeMetadata.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setNewDirectory", _params, new javax.xml.namespace.QName("", "setNewDirectoryReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setNewDirectory"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setNewDirectory");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setNewDirectory") == null) {
            _myOperations.put("setNewDirectory", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setNewDirectory")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "admProperties"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "AdministrativeMetadata"), de.nrw.dipp.dippCore.webservice.AdministrativeMetadata.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setAdministrativeMetadata", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setAdministrativeMetadata"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/setAdministrativeMetadata");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setAdministrativeMetadata") == null) {
            _myOperations.put("setAdministrativeMetadata", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setAdministrativeMetadata")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "setAdministrativeMetadata_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.SetAdministrativeMetadata_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setAdministrativeMetadata_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getAdministrativeMetadata", _params, new javax.xml.namespace.QName("", "getAdministrativeMetadataReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "AdministrativeMetadata"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "getAdministrativeMetadata"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/getAdministrativeMetadata");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getAdministrativeMetadata") == null) {
            _myOperations.put("getAdministrativeMetadata", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getAdministrativeMetadata")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "getAdministrativeMetadata_fault"));
        _fault.setClassName("de.nrw.dipp.dippCore.webservice.GetAdministrativeMetadata_fault");
        _fault.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">getAdministrativeMetadata_fault"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "resultFieldArray"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "resultFieldArrayType"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "maxResult"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"), org.apache.axis.types.NonNegativeInteger.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "searchConditionList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "searchConditionListType"), de.nrw.dipp.dippCore.webservice.ConditionType[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("findObjects", _params, new javax.xml.namespace.QName("", "FieldSearchResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "FieldSearchResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "findObjects"));
        _oper.setSoapAction("http://www.dippCore.dipp.nrw.de/definitions/findObjects");
        _myOperationsList.add(_oper);
        if (_myOperations.get("findObjects") == null) {
            _myOperations.put("findObjects", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("findObjects")).add(_oper);
    }

    public DippSoapBindingSkeleton() {
        this.impl = new de.nrw.dipp.dippCore.www.definitions.DippSoapBindingImpl();
    }

    public DippSoapBindingSkeleton(de.nrw.dipp.dippCore.www.definitions.ContentModel impl) {
        this.impl = impl;
    }
    public de.nrw.dipp.dippCore.webservice.ExtendedMetadata getArticleContentMetadata(java.lang.String in0) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippCore.webservice.ExtendedMetadata ret = impl.getArticleContentMetadata(in0);
        return ret;
    }

    public de.nrw.dipp.dippCore.webservice.QualifiedDublinCore getQualifiedDublinCore(java.lang.String in0) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippCore.webservice.QualifiedDublinCore ret = impl.getQualifiedDublinCore(in0);
        return ret;
    }

    public void setQualifiedDublinCore(java.lang.String in0, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in1) throws java.rmi.RemoteException
    {
        impl.setQualifiedDublinCore(in0, in1);
    }

    public java.lang.String setNewJournal(de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in0, java.lang.String zopeInstance, java.lang.String oaiSetName, java.lang.String oaiSetSpec) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewJournal_fault
    {
        java.lang.String ret = impl.setNewJournal(in0, zopeInstance, oaiSetName, oaiSetSpec);
        return ret;
    }

    public java.lang.String setNewArticle(java.lang.String[] in0, java.lang.String in1, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore in2, java.lang.String nativeDocIdent, java.lang.String storageType, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewArticle_fault
    {
        java.lang.String ret = impl.setNewArticle(in0, in1, in2, nativeDocIdent, storageType, targetFormat);
        return ret;
    }

    public java.lang.String setConvert(java.lang.String articlePID, java.lang.String sourceURL, java.lang.String[] targetFormat) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetConvert_fault
    {
        java.lang.String ret = impl.setConvert(articlePID, sourceURL, targetFormat);
        return ret;
    }

    public java.lang.String setNewContainer(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetNewContainer_fault
    {
        java.lang.String ret = impl.setNewContainer(in0, in1, in2);
        return ret;
    }

    public java.lang.String setNewComplexDocument(java.lang.String isPartOfPID, java.lang.String isVersionOfPID, de.nrw.dipp.dippCore.webservice.QualifiedDublinCore qdc) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.setNewComplexDocument(isPartOfPID, isVersionOfPID, qdc);
        return ret;
    }

    public java.lang.String copyObject(java.lang.String objectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.copyObject(objectPID, destObjectPID);
        return ret;
    }

    public java.lang.String moveObject(java.lang.String objectPID, java.lang.String sourceObjectPID, java.lang.String destObjectPID) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.moveObject(objectPID, sourceObjectPID, destObjectPID);
        return ret;
    }

    public java.lang.String setPublishingState(java.lang.String articlePID, boolean state, boolean published) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.setPublishingState(articlePID, state, published);
        return ret;
    }

    public java.lang.String setNewDirectory(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.setNewDirectory(admProperties);
        return ret;
    }

    public void setAdministrativeMetadata(de.nrw.dipp.dippCore.webservice.AdministrativeMetadata admProperties) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.SetAdministrativeMetadata_fault
    {
        impl.setAdministrativeMetadata(admProperties);
    }

    public de.nrw.dipp.dippCore.webservice.AdministrativeMetadata getAdministrativeMetadata(java.lang.String in0) throws java.rmi.RemoteException, de.nrw.dipp.dippCore.webservice.GetAdministrativeMetadata_fault
    {
        de.nrw.dipp.dippCore.webservice.AdministrativeMetadata ret = impl.getAdministrativeMetadata(in0);
        return ret;
    }

    public de.nrw.dipp.dippCore.webservice.FieldSearchResult findObjects(java.lang.String[] resultFieldArray, org.apache.axis.types.NonNegativeInteger maxResult, de.nrw.dipp.dippCore.webservice.ConditionType[] searchConditionList) throws java.rmi.RemoteException
    {
        de.nrw.dipp.dippCore.webservice.FieldSearchResult ret = impl.findObjects(resultFieldArray, maxResult, searchConditionList);
        return ret;
    }

}
