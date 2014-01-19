/**
 * GetObjectXMLResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetObjectXMLResponse  implements java.io.Serializable {
    private byte[] objectXML;

    public GetObjectXMLResponse() {
    }

    public GetObjectXMLResponse(
           byte[] objectXML) {
           this.objectXML = objectXML;
    }


    /**
     * Gets the objectXML value for this GetObjectXMLResponse.
     * 
     * @return objectXML
     */
    public byte[] getObjectXML() {
        return objectXML;
    }


    /**
     * Sets the objectXML value for this GetObjectXMLResponse.
     * 
     * @param objectXML
     */
    public void setObjectXML(byte[] objectXML) {
        this.objectXML = objectXML;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetObjectXMLResponse)) return false;
        GetObjectXMLResponse other = (GetObjectXMLResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectXML==null && other.getObjectXML()==null) || 
             (this.objectXML!=null &&
              java.util.Arrays.equals(this.objectXML, other.getObjectXML())));
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
        if (getObjectXML() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjectXML());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjectXML(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetObjectXMLResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getObjectXMLResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectXML");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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