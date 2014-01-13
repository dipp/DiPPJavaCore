/**
 * SetAdministrativeMetadata_fault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class SetAdministrativeMetadata_fault  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String setAdministrativeMetadata_fault;

    public SetAdministrativeMetadata_fault() {
    }

    public SetAdministrativeMetadata_fault(
           java.lang.String setAdministrativeMetadata_fault) {
        this.setAdministrativeMetadata_fault = setAdministrativeMetadata_fault;
    }


    /**
     * Gets the setAdministrativeMetadata_fault value for this SetAdministrativeMetadata_fault.
     * 
     * @return setAdministrativeMetadata_fault
     */
    public java.lang.String getSetAdministrativeMetadata_fault() {
        return setAdministrativeMetadata_fault;
    }


    /**
     * Sets the setAdministrativeMetadata_fault value for this SetAdministrativeMetadata_fault.
     * 
     * @param setAdministrativeMetadata_fault
     */
    public void setSetAdministrativeMetadata_fault(java.lang.String setAdministrativeMetadata_fault) {
        this.setAdministrativeMetadata_fault = setAdministrativeMetadata_fault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetAdministrativeMetadata_fault)) return false;
        SetAdministrativeMetadata_fault other = (SetAdministrativeMetadata_fault) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.setAdministrativeMetadata_fault==null && other.getSetAdministrativeMetadata_fault()==null) || 
             (this.setAdministrativeMetadata_fault!=null &&
              this.setAdministrativeMetadata_fault.equals(other.getSetAdministrativeMetadata_fault())));
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
        if (getSetAdministrativeMetadata_fault() != null) {
            _hashCode += getSetAdministrativeMetadata_fault().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetAdministrativeMetadata_fault.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setAdministrativeMetadata_fault"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setAdministrativeMetadata_fault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "setAdministrativeMetadata_fault"));
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
