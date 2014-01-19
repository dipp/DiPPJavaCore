/**
 * PurgeObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class PurgeObject  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String logMessage;

    private boolean force;

    public PurgeObject() {
    }

    public PurgeObject(
           java.lang.String pid,
           java.lang.String logMessage,
           boolean force) {
           this.pid = pid;
           this.logMessage = logMessage;
           this.force = force;
    }


    /**
     * Gets the pid value for this PurgeObject.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this PurgeObject.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the logMessage value for this PurgeObject.
     * 
     * @return logMessage
     */
    public java.lang.String getLogMessage() {
        return logMessage;
    }


    /**
     * Sets the logMessage value for this PurgeObject.
     * 
     * @param logMessage
     */
    public void setLogMessage(java.lang.String logMessage) {
        this.logMessage = logMessage;
    }


    /**
     * Gets the force value for this PurgeObject.
     * 
     * @return force
     */
    public boolean isForce() {
        return force;
    }


    /**
     * Sets the force value for this PurgeObject.
     * 
     * @param force
     */
    public void setForce(boolean force) {
        this.force = force;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PurgeObject)) return false;
        PurgeObject other = (PurgeObject) obj;
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
            ((this.logMessage==null && other.getLogMessage()==null) || 
             (this.logMessage!=null &&
              this.logMessage.equals(other.getLogMessage()))) &&
            this.force == other.isForce();
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
        if (getLogMessage() != null) {
            _hashCode += getLogMessage().hashCode();
        }
        _hashCode += (isForce() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PurgeObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("force");
        elemField.setXmlName(new javax.xml.namespace.QName("", "force"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
