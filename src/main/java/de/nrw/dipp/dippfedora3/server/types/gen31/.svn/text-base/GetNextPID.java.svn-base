/**
 * GetNextPID.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class GetNextPID  implements java.io.Serializable {
    private org.apache.axis.types.NonNegativeInteger numPIDs;

    private java.lang.String pidNamespace;

    public GetNextPID() {
    }

    public GetNextPID(
           org.apache.axis.types.NonNegativeInteger numPIDs,
           java.lang.String pidNamespace) {
           this.numPIDs = numPIDs;
           this.pidNamespace = pidNamespace;
    }


    /**
     * Gets the numPIDs value for this GetNextPID.
     * 
     * @return numPIDs
     */
    public org.apache.axis.types.NonNegativeInteger getNumPIDs() {
        return numPIDs;
    }


    /**
     * Sets the numPIDs value for this GetNextPID.
     * 
     * @param numPIDs
     */
    public void setNumPIDs(org.apache.axis.types.NonNegativeInteger numPIDs) {
        this.numPIDs = numPIDs;
    }


    /**
     * Gets the pidNamespace value for this GetNextPID.
     * 
     * @return pidNamespace
     */
    public java.lang.String getPidNamespace() {
        return pidNamespace;
    }


    /**
     * Sets the pidNamespace value for this GetNextPID.
     * 
     * @param pidNamespace
     */
    public void setPidNamespace(java.lang.String pidNamespace) {
        this.pidNamespace = pidNamespace;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNextPID)) return false;
        GetNextPID other = (GetNextPID) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numPIDs==null && other.getNumPIDs()==null) || 
             (this.numPIDs!=null &&
              this.numPIDs.equals(other.getNumPIDs()))) &&
            ((this.pidNamespace==null && other.getPidNamespace()==null) || 
             (this.pidNamespace!=null &&
              this.pidNamespace.equals(other.getPidNamespace())));
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
        if (getNumPIDs() != null) {
            _hashCode += getNumPIDs().hashCode();
        }
        if (getPidNamespace() != null) {
            _hashCode += getPidNamespace().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNextPID.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">getNextPID"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numPIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numPIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pidNamespace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pidNamespace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
