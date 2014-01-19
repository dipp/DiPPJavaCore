/**
 * FedoraAPIABindingSOAPHTTPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.access31;


public class FedoraAPIABindingSOAPHTTPStub extends org.apache.axis.client.Stub implements de.nrw.dipp.dippfedora3.server.access31.FedoraAPIA {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeRepository");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RepositoryInfo"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "repositoryInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getObjectProfile");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectProfile"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "objectProfile"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listMethods");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectMethodsDef"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "objectMethod"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listDatastreams");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamDef"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "datastreamDef"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDatastreamDissemination");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dsID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "dissemination"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDissemination");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceDefinitionPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "methodName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">>getDissemination>parameters"), de.nrw.dipp.dippfedora3.server.types.gen31.Property[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "parameter"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "asOfDateTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "dissemination"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "resultFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "maxResults"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"), org.apache.axis.types.NonNegativeInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "query"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchQuery"), de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resumeFindObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult"));
        oper.setReturnClass(de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getObjectHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "modifiedDate"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public FedoraAPIABindingSOAPHTTPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public FedoraAPIABindingSOAPHTTPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public FedoraAPIABindingSOAPHTTPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">>getDissemination>parameters");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Property[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Property");
            qName2 = new javax.xml.namespace.QName("", "parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">addDatastream");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.AddDatastream.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">addDatastreamResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.AddDatastreamResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">addRelationship");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.AddRelationship.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">addRelationshipResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.AddRelationshipResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">compareDatastreamChecksum");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.CompareDatastreamChecksum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">compareDatastreamChecksumResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.CompareDatastreamChecksumResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">DatastreamBindingMap>dsBindings");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamBinding[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamBinding");
            qName2 = new javax.xml.namespace.QName("", "dsBinding");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">export");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Export.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">exportResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ExportResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">FieldSearchQuery>conditions");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Condition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Condition");
            qName2 = new javax.xml.namespace.QName("", "condition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">FieldSearchResult>resultList");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectFields");
            qName2 = new javax.xml.namespace.QName("", "objectFields");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastream");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetDatastream.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamHistory");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetDatastreamHistory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamHistoryResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Datastream[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Datastream");
            qName2 = new javax.xml.namespace.QName("", "datastream");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetDatastreamResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreams");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetDatastreams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamsResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Datastream[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Datastream");
            qName2 = new javax.xml.namespace.QName("", "datastream");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getNextPID");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetNextPID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getNextPIDResponse");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "pid");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getObjectXML");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetObjectXML.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getObjectXMLResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetObjectXMLResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getRelationships");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.GetRelationships.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getRelationshipsResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.RelationshipTuple[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RelationshipTuple");
            qName2 = new javax.xml.namespace.QName("", "relationships");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">ingest");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Ingest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">ingestResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.IngestResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">MIMETypedStream>header");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Property[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Property");
            qName2 = new javax.xml.namespace.QName("", "property");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyDatastreamByReference");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyDatastreamByReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyDatastreamByReferenceResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyDatastreamByReferenceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyDatastreamByValue");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyDatastreamByValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyDatastreamByValueResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyDatastreamByValueResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyObject");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">modifyObjectResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ModifyObjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">ObjectMethodsDef>methodParmDefs");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MethodParmDef");
            qName2 = new javax.xml.namespace.QName("", "methodParmDef");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">ObjectProfile>objModels");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "model");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeDatastream");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PurgeDatastream.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeDatastreamResponse");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "purgedVersionDate");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeObject");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PurgeObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeObjectResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PurgeObjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeRelationship");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PurgeRelationship.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeRelationshipResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PurgeRelationshipResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">resumeFindObjectsResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ResumeFindObjectsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">setDatastreamState");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.SetDatastreamState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">setDatastreamStateResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.SetDatastreamStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">setDatastreamVersionable");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.SetDatastreamVersionable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">setDatastreamVersionableResponse");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.SetDatastreamVersionableResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ArrayOfString");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ComparisonOperator");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ComparisonOperator.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Condition");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Condition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Datastream");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Datastream.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamBinding");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamBinding.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamBindingMap");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamBindingMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamControlGroup");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamControlGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "DatastreamDef");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "datastreamInputType");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "defaultInputType");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.DefaultInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchQuery");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ListSession");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ListSession.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MethodParmDef");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectFields");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectMethodsDef");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectProfile");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "passByRef");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PassByRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "passByValue");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.PassByValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Property");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.Property.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RelationshipTuple");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.RelationshipTuple.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RepositoryInfo");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "userInputType");
            cachedSerQNames.add(qName);
            cls = de.nrw.dipp.dippfedora3.server.types.gen31.UserInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo describeRepository() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#describeRepository");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "describeRepository"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile getObjectProfile(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#getObjectProfile");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "getObjectProfile"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid, asOfDateTime});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[] listMethods(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#listMethods");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "listMethods"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid, asOfDateTime});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[]) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.ObjectMethodsDef[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[] listDatastreams(java.lang.String pid, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#listDatastreams");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "listDatastreams"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid, asOfDateTime});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[]) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream getDatastreamDissemination(java.lang.String pid, java.lang.String dsID, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#getDatastreamDissemination");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "getDatastreamDissemination"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid, dsID, asOfDateTime});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream getDissemination(java.lang.String pid, java.lang.String serviceDefinitionPid, java.lang.String methodName, de.nrw.dipp.dippfedora3.server.types.gen31.Property[] parameters, java.lang.String asOfDateTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#getDissemination");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "getDissemination"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid, serviceDefinitionPid, methodName, parameters, asOfDateTime});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult findObjects(java.lang.String[] resultFields, org.apache.axis.types.NonNegativeInteger maxResults, de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery query) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#findObjects");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "findObjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {resultFields, maxResults, query});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult resumeFindObjects(java.lang.String sessionToken) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#resumeFindObjects");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "resumeFindObjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] getObjectHistory(java.lang.String pid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.fedora.info/definitions/1/0/api/#getObjectHistory");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "getObjectHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
