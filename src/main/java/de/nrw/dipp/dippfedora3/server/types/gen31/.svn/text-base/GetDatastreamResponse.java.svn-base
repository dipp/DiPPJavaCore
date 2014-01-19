/**
 * GetDatastreamResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetDatastreamResponse  implements java.io.Serializable {
    private de.nrw.dipp.dippfedora3.server.types.gen31.Datastream datastream;

    public GetDatastreamResponse() {
    }

    public GetDatastreamResponse(
           de.nrw.dipp.dippfedora3.server.types.gen31.Datastream datastream) {
           this.datastream = datastream;
    }


    /**
     * Gets the datastream value for this GetDatastreamResponse.
     * 
     * @return datastream
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.Datastream getDatastream() {
        return datastream;
    }


    /**
     * Sets the datastream value for this GetDatastreamResponse.
     * 
     * @param datastream
     */
    public void setDatastream(de.nrw.dipp.dippfedora3.server.types.gen31.Datastream datastream) {
        this.datastream = datastream;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDatastreamResponse)) return false;
        GetDatastreamResponse other = (GetDatastreamResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datastream==null && other.getDatastream()==null) || 
             (this.datastream!=null &&
              this.datastream.equals(other.getDatastream())));
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
        if (getDatastream() != null) {
            _hashCode += getDatastream().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDatastreamResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getDatastreamResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datastream");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datastream"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "Datastream"));
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
