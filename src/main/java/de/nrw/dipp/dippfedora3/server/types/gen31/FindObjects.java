/**
 * FindObjects.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class FindObjects  implements java.io.Serializable {
    private java.lang.String[] resultFields;

    private org.apache.axis.types.NonNegativeInteger maxResults;

    private de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery query;

    public FindObjects() {
    }

    public FindObjects(
           java.lang.String[] resultFields,
           org.apache.axis.types.NonNegativeInteger maxResults,
           de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery query) {
           this.resultFields = resultFields;
           this.maxResults = maxResults;
           this.query = query;
    }


    /**
     * Gets the resultFields value for this FindObjects.
     * 
     * @return resultFields
     */
    public java.lang.String[] getResultFields() {
        return resultFields;
    }


    /**
     * Sets the resultFields value for this FindObjects.
     * 
     * @param resultFields
     */
    public void setResultFields(java.lang.String[] resultFields) {
        this.resultFields = resultFields;
    }


    /**
     * Gets the maxResults value for this FindObjects.
     * 
     * @return maxResults
     */
    public org.apache.axis.types.NonNegativeInteger getMaxResults() {
        return maxResults;
    }


    /**
     * Sets the maxResults value for this FindObjects.
     * 
     * @param maxResults
     */
    public void setMaxResults(org.apache.axis.types.NonNegativeInteger maxResults) {
        this.maxResults = maxResults;
    }


    /**
     * Gets the query value for this FindObjects.
     * 
     * @return query
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery getQuery() {
        return query;
    }


    /**
     * Sets the query value for this FindObjects.
     * 
     * @param query
     */
    public void setQuery(de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery query) {
        this.query = query;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FindObjects)) return false;
        FindObjects other = (FindObjects) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultFields==null && other.getResultFields()==null) || 
             (this.resultFields!=null &&
              java.util.Arrays.equals(this.resultFields, other.getResultFields()))) &&
            ((this.maxResults==null && other.getMaxResults()==null) || 
             (this.maxResults!=null &&
              this.maxResults.equals(other.getMaxResults()))) &&
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery())));
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
        if (getResultFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMaxResults() != null) {
            _hashCode += getMaxResults().hashCode();
        }
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FindObjects.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">findObjects"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultFields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "FieldSearchQuery"));
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
