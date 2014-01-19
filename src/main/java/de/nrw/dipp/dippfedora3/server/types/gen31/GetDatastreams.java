/**
 * GetDatastreams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetDatastreams  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String asOfDateTime;

    private java.lang.String dsState;

    public GetDatastreams() {
    }

    public GetDatastreams(
           java.lang.String pid,
           java.lang.String asOfDateTime,
           java.lang.String dsState) {
           this.pid = pid;
           this.asOfDateTime = asOfDateTime;
           this.dsState = dsState;
    }


    /**
     * Gets the pid value for this GetDatastreams.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this GetDatastreams.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the asOfDateTime value for this GetDatastreams.
     * 
     * @return asOfDateTime
     */
    public java.lang.String getAsOfDateTime() {
        return asOfDateTime;
    }


    /**
     * Sets the asOfDateTime value for this GetDatastreams.
     * 
     * @param asOfDateTime
     */
    public void setAsOfDateTime(java.lang.String asOfDateTime) {
        this.asOfDateTime = asOfDateTime;
    }


    /**
     * Gets the dsState value for this GetDatastreams.
     * 
     * @return dsState
     */
    public java.lang.String getDsState() {
        return dsState;
    }


    /**
     * Sets the dsState value for this GetDatastreams.
     * 
     * @param dsState
     */
    public void setDsState(java.lang.String dsState) {
        this.dsState = dsState;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDatastreams)) return false;
        GetDatastreams other = (GetDatastreams) obj;
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
            ((this.asOfDateTime==null && other.getAsOfDateTime()==null) || 
             (this.asOfDateTime!=null &&
              this.asOfDateTime.equals(other.getAsOfDateTime()))) &&
            ((this.dsState==null && other.getDsState()==null) || 
             (this.dsState!=null &&
              this.dsState.equals(other.getDsState())));
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
        if (getAsOfDateTime() != null) {
            _hashCode += getAsOfDateTime().hashCode();
        }
        if (getDsState() != null) {
            _hashCode += getDsState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDatastreams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreams"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asOfDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asOfDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsState"));
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
