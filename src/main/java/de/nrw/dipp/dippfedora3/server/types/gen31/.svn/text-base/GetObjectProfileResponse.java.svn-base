/**
 * GetObjectProfileResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetObjectProfileResponse  implements java.io.Serializable {
    private de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile objectProfile;

    public GetObjectProfileResponse() {
    }

    public GetObjectProfileResponse(
           de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile objectProfile) {
           this.objectProfile = objectProfile;
    }


    /**
     * Gets the objectProfile value for this GetObjectProfileResponse.
     * 
     * @return objectProfile
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile getObjectProfile() {
        return objectProfile;
    }


    /**
     * Sets the objectProfile value for this GetObjectProfileResponse.
     * 
     * @param objectProfile
     */
    public void setObjectProfile(de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile objectProfile) {
        this.objectProfile = objectProfile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetObjectProfileResponse)) return false;
        GetObjectProfileResponse other = (GetObjectProfileResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectProfile==null && other.getObjectProfile()==null) || 
             (this.objectProfile!=null &&
              this.objectProfile.equals(other.getObjectProfile())));
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
        if (getObjectProfile() != null) {
            _hashCode += getObjectProfile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetObjectProfileResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getObjectProfileResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectProfile"));
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
