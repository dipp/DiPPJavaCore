/**
 * IngestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class IngestResponse  implements java.io.Serializable {
    private java.lang.String objectPID;

    public IngestResponse() {
    }

    public IngestResponse(
           java.lang.String objectPID) {
           this.objectPID = objectPID;
    }


    /**
     * Gets the objectPID value for this IngestResponse.
     * 
     * @return objectPID
     */
    public java.lang.String getObjectPID() {
        return objectPID;
    }


    /**
     * Sets the objectPID value for this IngestResponse.
     * 
     * @param objectPID
     */
    public void setObjectPID(java.lang.String objectPID) {
        this.objectPID = objectPID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IngestResponse)) return false;
        IngestResponse other = (IngestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectPID==null && other.getObjectPID()==null) || 
             (this.objectPID!=null &&
              this.objectPID.equals(other.getObjectPID())));
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
        if (getObjectPID() != null) {
            _hashCode += getObjectPID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IngestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">ingestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectPID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectPID"));
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
