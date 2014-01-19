/**
 * GetDissemination.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetDissemination  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String serviceDefinitionPid;

    private java.lang.String methodName;

    private de.nrw.dipp.dippfedora3.server.types.gen31.Property[] parameters;

    private java.lang.String asOfDateTime;

    public GetDissemination() {
    }

    public GetDissemination(
           java.lang.String pid,
           java.lang.String serviceDefinitionPid,
           java.lang.String methodName,
           de.nrw.dipp.dippfedora3.server.types.gen31.Property[] parameters,
           java.lang.String asOfDateTime) {
           this.pid = pid;
           this.serviceDefinitionPid = serviceDefinitionPid;
           this.methodName = methodName;
           this.parameters = parameters;
           this.asOfDateTime = asOfDateTime;
    }


    /**
     * Gets the pid value for this GetDissemination.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this GetDissemination.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the serviceDefinitionPid value for this GetDissemination.
     * 
     * @return serviceDefinitionPid
     */
    public java.lang.String getServiceDefinitionPid() {
        return serviceDefinitionPid;
    }


    /**
     * Sets the serviceDefinitionPid value for this GetDissemination.
     * 
     * @param serviceDefinitionPid
     */
    public void setServiceDefinitionPid(java.lang.String serviceDefinitionPid) {
        this.serviceDefinitionPid = serviceDefinitionPid;
    }


    /**
     * Gets the methodName value for this GetDissemination.
     * 
     * @return methodName
     */
    public java.lang.String getMethodName() {
        return methodName;
    }


    /**
     * Sets the methodName value for this GetDissemination.
     * 
     * @param methodName
     */
    public void setMethodName(java.lang.String methodName) {
        this.methodName = methodName;
    }


    /**
     * Gets the parameters value for this GetDissemination.
     * 
     * @return parameters
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.Property[] getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this GetDissemination.
     * 
     * @param parameters
     */
    public void setParameters(de.nrw.dipp.dippfedora3.server.types.gen31.Property[] parameters) {
        this.parameters = parameters;
    }


    /**
     * Gets the asOfDateTime value for this GetDissemination.
     * 
     * @return asOfDateTime
     */
    public java.lang.String getAsOfDateTime() {
        return asOfDateTime;
    }


    /**
     * Sets the asOfDateTime value for this GetDissemination.
     * 
     * @param asOfDateTime
     */
    public void setAsOfDateTime(java.lang.String asOfDateTime) {
        this.asOfDateTime = asOfDateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDissemination)) return false;
        GetDissemination other = (GetDissemination) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pid==null && other.getPid()==null) || 
             (this.pid!=null &&
              this.pid.equals(other.getPid()))) &&
            ((this.serviceDefinitionPid==null && other.getServiceDefinitionPid()==null) || 
             (this.serviceDefinitionPid!=null &&
              this.serviceDefinitionPid.equals(other.getServiceDefinitionPid()))) &&
            ((this.methodName==null && other.getMethodName()==null) || 
             (this.methodName!=null &&
              this.methodName.equals(other.getMethodName()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              java.util.Arrays.equals(this.parameters, other.getParameters()))) &&
            ((this.asOfDateTime==null && other.getAsOfDateTime()==null) || 
             (this.asOfDateTime!=null &&
              this.asOfDateTime.equals(other.getAsOfDateTime())));
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
        if (getPid() != null) {
            _hashCode += getPid().hashCode();
        }
        if (getServiceDefinitionPid() != null) {
            _hashCode += getServiceDefinitionPid().hashCode();
        }
        if (getMethodName() != null) {
            _hashCode += getMethodName().hashCode();
        }
        if (getParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAsOfDateTime() != null) {
            _hashCode += getAsOfDateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDissemination.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDissemination"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceDefinitionPid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceDefinitionPid"));
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
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Property"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parameter"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asOfDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asOfDateTime"));
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
