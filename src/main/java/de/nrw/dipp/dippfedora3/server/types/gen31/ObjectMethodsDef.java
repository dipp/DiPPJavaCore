/**
 * ObjectMethodsDef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class ObjectMethodsDef  implements java.io.Serializable {
    private java.lang.String PID;

    private java.lang.String serviceDefinitionPID;

    private java.lang.String methodName;

    private de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef[] methodParmDefs;

    private java.lang.String asOfDate;

    public ObjectMethodsDef() {
    }

    public ObjectMethodsDef(
           java.lang.String PID,
           java.lang.String serviceDefinitionPID,
           java.lang.String methodName,
           de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef[] methodParmDefs,
           java.lang.String asOfDate) {
           this.PID = PID;
           this.serviceDefinitionPID = serviceDefinitionPID;
           this.methodName = methodName;
           this.methodParmDefs = methodParmDefs;
           this.asOfDate = asOfDate;
    }


    /**
     * Gets the PID value for this ObjectMethodsDef.
     * 
     * @return PID
     */
    public java.lang.String getPID() {
        return PID;
    }


    /**
     * Sets the PID value for this ObjectMethodsDef.
     * 
     * @param PID
     */
    public void setPID(java.lang.String PID) {
        this.PID = PID;
    }


    /**
     * Gets the serviceDefinitionPID value for this ObjectMethodsDef.
     * 
     * @return serviceDefinitionPID
     */
    public java.lang.String getServiceDefinitionPID() {
        return serviceDefinitionPID;
    }


    /**
     * Sets the serviceDefinitionPID value for this ObjectMethodsDef.
     * 
     * @param serviceDefinitionPID
     */
    public void setServiceDefinitionPID(java.lang.String serviceDefinitionPID) {
        this.serviceDefinitionPID = serviceDefinitionPID;
    }


    /**
     * Gets the methodName value for this ObjectMethodsDef.
     * 
     * @return methodName
     */
    public java.lang.String getMethodName() {
        return methodName;
    }


    /**
     * Sets the methodName value for this ObjectMethodsDef.
     * 
     * @param methodName
     */
    public void setMethodName(java.lang.String methodName) {
        this.methodName = methodName;
    }


    /**
     * Gets the methodParmDefs value for this ObjectMethodsDef.
     * 
     * @return methodParmDefs
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef[] getMethodParmDefs() {
        return methodParmDefs;
    }


    /**
     * Sets the methodParmDefs value for this ObjectMethodsDef.
     * 
     * @param methodParmDefs
     */
    public void setMethodParmDefs(de.nrw.dipp.dippfedora3.server.types.gen31.MethodParmDef[] methodParmDefs) {
        this.methodParmDefs = methodParmDefs;
    }


    /**
     * Gets the asOfDate value for this ObjectMethodsDef.
     * 
     * @return asOfDate
     */
    public java.lang.String getAsOfDate() {
        return asOfDate;
    }


    /**
     * Sets the asOfDate value for this ObjectMethodsDef.
     * 
     * @param asOfDate
     */
    public void setAsOfDate(java.lang.String asOfDate) {
        this.asOfDate = asOfDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObjectMethodsDef)) return false;
        ObjectMethodsDef other = (ObjectMethodsDef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PID==null && other.getPID()==null) || 
             (this.PID!=null &&
              this.PID.equals(other.getPID()))) &&
            ((this.serviceDefinitionPID==null && other.getServiceDefinitionPID()==null) || 
             (this.serviceDefinitionPID!=null &&
              this.serviceDefinitionPID.equals(other.getServiceDefinitionPID()))) &&
            ((this.methodName==null && other.getMethodName()==null) || 
             (this.methodName!=null &&
              this.methodName.equals(other.getMethodName()))) &&
            ((this.methodParmDefs==null && other.getMethodParmDefs()==null) || 
             (this.methodParmDefs!=null &&
              java.util.Arrays.equals(this.methodParmDefs, other.getMethodParmDefs()))) &&
            ((this.asOfDate==null && other.getAsOfDate()==null) || 
             (this.asOfDate!=null &&
              this.asOfDate.equals(other.getAsOfDate())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPID() != null) {
            _hashCode += getPID().hashCode();
        }
        if (getServiceDefinitionPID() != null) {
            _hashCode += getServiceDefinitionPID().hashCode();
        }
        if (getMethodName() != null) {
            _hashCode += getMethodName().hashCode();
        }
        if (getMethodParmDefs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMethodParmDefs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMethodParmDefs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAsOfDate() != null) {
            _hashCode += getAsOfDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObjectMethodsDef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectMethodsDef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceDefinitionPID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceDefinitionPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("methodName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "methodName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("methodParmDefs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "methodParmDefs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MethodParmDef"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "methodParmDef"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asOfDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asOfDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
