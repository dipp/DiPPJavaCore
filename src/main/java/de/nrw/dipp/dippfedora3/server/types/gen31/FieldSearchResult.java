/**
 * FieldSearchResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class FieldSearchResult  implements java.io.Serializable {
    private de.nrw.dipp.dippfedora3.server.types.gen31.ListSession listSession;

    private de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields[] resultList;

    public FieldSearchResult() {
    }

    public FieldSearchResult(
           de.nrw.dipp.dippfedora3.server.types.gen31.ListSession listSession,
           de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields[] resultList) {
           this.listSession = listSession;
           this.resultList = resultList;
    }


    /**
     * Gets the listSession value for this FieldSearchResult.
     * 
     * @return listSession
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.ListSession getListSession() {
        return listSession;
    }


    /**
     * Sets the listSession value for this FieldSearchResult.
     * 
     * @param listSession
     */
    public void setListSession(de.nrw.dipp.dippfedora3.server.types.gen31.ListSession listSession) {
        this.listSession = listSession;
    }


    /**
     * Gets the resultList value for this FieldSearchResult.
     * 
     * @return resultList
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields[] getResultList() {
        return resultList;
    }


    /**
     * Sets the resultList value for this FieldSearchResult.
     * 
     * @param resultList
     */
    public void setResultList(de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields[] resultList) {
        this.resultList = resultList;
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
            ((this.resultList==null && other.getResultList()==null) || 
             (this.resultList!=null &&
              java.util.Arrays.equals(this.resultList, other.getResultList())));
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
        if (getResultList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultList(), i);
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
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listSession");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listSession"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ListSession"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "ObjectFields"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "objectFields"));
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
