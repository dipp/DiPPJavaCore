/**
 * SetNewContainer_fault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class SetNewContainer_fault  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String setNewContainer_fault;

    public SetNewContainer_fault() {
    }

    public SetNewContainer_fault(
           java.lang.String setNewContainer_fault) {
        this.setNewContainer_fault = setNewContainer_fault;
    }


    /**
     * Gets the setNewContainer_fault value for this SetNewContainer_fault.
     * 
     * @return setNewContainer_fault
     */
    public java.lang.String getSetNewContainer_fault() {
        return setNewContainer_fault;
    }


    /**
     * Sets the setNewContainer_fault value for this SetNewContainer_fault.
     * 
     * @param setNewContainer_fault
     */
    public void setSetNewContainer_fault(java.lang.String setNewContainer_fault) {
        this.setNewContainer_fault = setNewContainer_fault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetNewContainer_fault)) return false;
        SetNewContainer_fault other = (SetNewContainer_fault) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.setNewContainer_fault==null && other.getSetNewContainer_fault()==null) || 
             (this.setNewContainer_fault!=null &&
              this.setNewContainer_fault.equals(other.getSetNewContainer_fault())));
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
        if (getSetNewContainer_fault() != null) {
            _hashCode += getSetNewContainer_fault().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetNewContainer_fault.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", ">setNewContainer_fault"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setNewContainer_fault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "setNewContainer_fault"));
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
