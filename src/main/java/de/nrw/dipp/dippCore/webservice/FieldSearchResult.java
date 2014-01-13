/**
 * FieldSearchResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class FieldSearchResult  implements java.io.Serializable {
    private de.nrw.dipp.dippCore.webservice.ListSessionType listSession;

    private de.nrw.dipp.dippCore.webservice.ObjectFields[] objectFields;

    public FieldSearchResult() {
    }

    public FieldSearchResult(
           de.nrw.dipp.dippCore.webservice.ListSessionType listSession,
           de.nrw.dipp.dippCore.webservice.ObjectFields[] objectFields) {
           this.listSession = listSession;
           this.objectFields = objectFields;
    }


    /**
     * Gets the listSession value for this FieldSearchResult.
     * 
     * @return listSession
     */
    public de.nrw.dipp.dippCore.webservice.ListSessionType getListSession() {
        return listSession;
    }


    /**
     * Sets the listSession value for this FieldSearchResult.
     * 
     * @param listSession
     */
    public void setListSession(de.nrw.dipp.dippCore.webservice.ListSessionType listSession) {
        this.listSession = listSession;
    }


    /**
     * Gets the objectFields value for this FieldSearchResult.
     * 
     * @return objectFields
     */
    public de.nrw.dipp.dippCore.webservice.ObjectFields[] getObjectFields() {
        return objectFields;
    }


    /**
     * Sets the objectFields value for this FieldSearchResult.
     * 
     * @param objectFields
     */
    public void setObjectFields(de.nrw.dipp.dippCore.webservice.ObjectFields[] objectFields) {
        this.objectFields = objectFields;
    }

    public de.nrw.dipp.dippCore.webservice.ObjectFields getObjectFields(int i) {
        return this.objectFields[i];
    }

    public void setObjectFields(int i, de.nrw.dipp.dippCore.webservice.ObjectFields _value) {
        this.objectFields[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FieldSearchResult)) return false;
        FieldSearchResult other = (FieldSearchResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listSession==null && other.getListSession()==null) || 
             (this.listSession!=null &&
              this.listSession.equals(other.getListSession()))) &&
            ((this.objectFields==null && other.getObjectFields()==null) || 
             (this.objectFields!=null &&
              java.util.Arrays.equals(this.objectFields, other.getObjectFields())));
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
        if (getListSession() != null) {
            _hashCode += getListSession().hashCode();
        }
        if (getObjectFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjectFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjectFields(), i);
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
        new org.apache.axis.description.TypeDesc(FieldSearchResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "FieldSearchResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listSession");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListSession"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "ListSessionType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectFields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ObjectFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "ObjectFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
