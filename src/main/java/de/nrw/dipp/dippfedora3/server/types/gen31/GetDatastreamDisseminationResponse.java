/**
 * GetDatastreamDisseminationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetDatastreamDisseminationResponse  implements java.io.Serializable {
    private de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream dissemination;

    public GetDatastreamDisseminationResponse() {
    }

    public GetDatastreamDisseminationResponse(
           de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream dissemination) {
           this.dissemination = dissemination;
    }


    /**
     * Gets the dissemination value for this GetDatastreamDisseminationResponse.
     * 
     * @return dissemination
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream getDissemination() {
        return dissemination;
    }


    /**
     * Sets the dissemination value for this GetDatastreamDisseminationResponse.
     * 
     * @param dissemination
     */
    public void setDissemination(de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream dissemination) {
        this.dissemination = dissemination;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDatastreamDisseminationResponse)) return false;
        GetDatastreamDisseminationResponse other = (GetDatastreamDisseminationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dissemination==null && other.getDissemination()==null) || 
             (this.dissemination!=null &&
              this.dissemination.equals(other.getDissemination())));
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
        if (getDissemination() != null) {
            _hashCode += getDissemination().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDatastreamDisseminationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamDisseminationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dissemination");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dissemination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "MIMETypedStream"));
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
