/**
 * ConditionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class ConditionType  implements java.io.Serializable {
    /* Defines which field from the Fedora Repository
     *       					Database should be searched. Maybe this can be
     *       					better an option list, but I'm unsure if ZSI
     *       					supports this. */
    private java.lang.String property;

    private de.nrw.dipp.dippCore.webservice.OperatorType operator;

    private java.lang.String value;

    public ConditionType() {
    }

    public ConditionType(
           java.lang.String property,
           de.nrw.dipp.dippCore.webservice.OperatorType operator,
           java.lang.String value) {
           this.property = property;
           this.operator = operator;
           this.value = value;
    }


    /**
     * Gets the property value for this ConditionType.
     * 
     * @return property   * Defines which field from the Fedora Repository
     *       					Database should be searched. Maybe this can be
     *       					better an option list, but I'm unsure if ZSI
     *       					supports this.
     */
    public java.lang.String getProperty() {
        return property;
    }


    /**
     * Sets the property value for this ConditionType.
     * 
     * @param property   * Defines which field from the Fedora Repository
     *       					Database should be searched. Maybe this can be
     *       					better an option list, but I'm unsure if ZSI
     *       					supports this.
     */
    public void setProperty(java.lang.String property) {
        this.property = property;
    }


    /**
     * Gets the operator value for this ConditionType.
     * 
     * @return operator
     */
    public de.nrw.dipp.dippCore.webservice.OperatorType getOperator() {
        return operator;
    }


    /**
     * Sets the operator value for this ConditionType.
     * 
     * @param operator
     */
    public void setOperator(de.nrw.dipp.dippCore.webservice.OperatorType operator) {
        this.operator = operator;
    }


    /**
     * Gets the value value for this ConditionType.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this ConditionType.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConditionType)) return false;
        ConditionType other = (ConditionType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.property==null && other.getProperty()==null) || 
             (this.property!=null &&
              this.property.equals(other.getProperty()))) &&
            ((this.operator==null && other.getOperator()==null) || 
             (this.operator!=null &&
              this.operator.equals(other.getOperator()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getProperty() != null) {
            _hashCode += getProperty().hashCode();
        }
        if (getOperator() != null) {
            _hashCode += getOperator().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConditionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "conditionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("property");
        elemField.setXmlName(new javax.xml.namespace.QName("", "property"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "operatorType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
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
