/**
 * GetDatastream.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetDatastream  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String dsID;

    private java.lang.String asOfDateTime;

    public GetDatastream() {
    }

    public GetDatastream(
           java.lang.String pid,
           java.lang.String dsID,
           java.lang.String asOfDateTime) {
           this.pid = pid;
           this.dsID = dsID;
           this.asOfDateTime = asOfDateTime;
    }


    /**
     * Gets the pid value for this GetDatastream.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this GetDatastream.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the dsID value for this GetDatastream.
     * 
     * @return dsID
     */
    public java.lang.String getDsID() {
        return dsID;
    }


    /**
     * Sets the dsID value for this GetDatastream.
     * 
     * @param dsID
     */
    public void setDsID(java.lang.String dsID) {
        this.dsID = dsID;
    }


    /**
     * Gets the asOfDateTime value for this GetDatastream.
     * 
     * @return asOfDateTime
     */
    public java.lang.String getAsOfDateTime() {
        return asOfDateTime;
    }


    /**
     * Sets the asOfDateTime value for this GetDatastream.
     * 
     * @param asOfDateTime
     */
    public void setAsOfDateTime(java.lang.String asOfDateTime) {
        this.asOfDateTime = asOfDateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDatastream)) return false;
        GetDatastream other = (GetDatastream) obj;
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
        if (getDsID() != null) {
            _hashCode += getDsID().hashCode();
        }
        if (getAsOfDateTime() != null) {
            _hashCode += getAsOfDateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDatastream.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastream"));
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
        elemField.setFieldName("asOfDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asOfDateTime"));
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
