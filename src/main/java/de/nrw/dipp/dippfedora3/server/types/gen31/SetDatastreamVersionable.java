/**
 * SetDatastreamVersionable.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class SetDatastreamVersionable  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String dsID;

    private boolean versionable;

    private java.lang.String logMessage;

    public SetDatastreamVersionable() {
    }

    public SetDatastreamVersionable(
           java.lang.String pid,
           java.lang.String dsID,
           boolean versionable,
           java.lang.String logMessage) {
           this.pid = pid;
           this.dsID = dsID;
           this.versionable = versionable;
           this.logMessage = logMessage;
    }


    /**
     * Gets the pid value for this SetDatastreamVersionable.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this SetDatastreamVersionable.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the dsID value for this SetDatastreamVersionable.
     * 
     * @return dsID
     */
    public java.lang.String getDsID() {
        return dsID;
    }


    /**
     * Sets the dsID value for this SetDatastreamVersionable.
     * 
     * @param dsID
     */
    public void setDsID(java.lang.String dsID) {
        this.dsID = dsID;
    }


    /**
     * Gets the versionable value for this SetDatastreamVersionable.
     * 
     * @return versionable
     */
    public boolean isVersionable() {
        return versionable;
    }


    /**
     * Sets the versionable value for this SetDatastreamVersionable.
     * 
     * @param versionable
     */
    public void setVersionable(boolean versionable) {
        this.versionable = versionable;
    }


    /**
     * Gets the logMessage value for this SetDatastreamVersionable.
     * 
     * @return logMessage
     */
    public java.lang.String getLogMessage() {
        return logMessage;
    }


    /**
     * Sets the logMessage value for this SetDatastreamVersionable.
     * 
     * @param logMessage
     */
    public void setLogMessage(java.lang.String logMessage) {
        this.logMessage = logMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetDatastreamVersionable)) return false;
        SetDatastreamVersionable other = (SetDatastreamVersionable) obj;
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
            ((this.dsID==null && other.getDsID()==null) || 
             (this.dsID!=null &&
              this.dsID.equals(other.getDsID()))) &&
            this.versionable == other.isVersionable() &&
            ((this.logMessage==null && other.getLogMessage()==null) || 
             (this.logMessage!=null &&
              this.logMessage.equals(other.getLogMessage())));
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
        if (getDsID() != null) {
            _hashCode += getDsID().hashCode();
        }
        _hashCode += (isVersionable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLogMessage() != null) {
            _hashCode += getLogMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetDatastreamVersionable.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">setDatastreamVersionable"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "versionable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
