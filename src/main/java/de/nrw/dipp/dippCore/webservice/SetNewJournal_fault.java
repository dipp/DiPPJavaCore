/**
 * SetNewJournal_fault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class SetNewJournal_fault  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String setNewJournal_fault;

    public SetNewJournal_fault() {
    }

    public SetNewJournal_fault(
           java.lang.String setNewJournal_fault) {
        this.setNewJournal_fault = setNewJournal_fault;
    }


    /**
     * Gets the setNewJournal_fault value for this SetNewJournal_fault.
     * 
     * @return setNewJournal_fault
     */
    public java.lang.String getSetNewJournal_fault() {
        return setNewJournal_fault;
    }


    /**
     * Sets the setNewJournal_fault value for this SetNewJournal_fault.
     * 
     * @param setNewJournal_fault
     */
    public void setSetNewJournal_fault(java.lang.String setNewJournal_fault) {
        this.setNewJournal_fault = setNewJournal_fault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetNewJournal_fault)) return false;
        SetNewJournal_fault other = (SetNewJournal_fault) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.setNewJournal_fault==null && other.getSetNewJournal_fault()==null) || 
             (this.setNewJournal_fault!=null &&
              this.setNewJournal_fault.equals(other.getSetNewJournal_fault())));
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
        if (getSetNewJournal_fault() != null) {
            _hashCode += getSetNewJournal_fault().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetNewJournal_fault.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setNewJournal_fault"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setNewJournal_fault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "setNewJournal_fault"));
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
