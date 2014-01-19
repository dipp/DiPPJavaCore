/**
 * PurgeRelationship.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class PurgeRelationship  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String relationship;

    private java.lang.String object;

    private boolean isLiteral;

    private java.lang.String datatype;

    public PurgeRelationship() {
    }

    public PurgeRelationship(
           java.lang.String pid,
           java.lang.String relationship,
           java.lang.String object,
           boolean isLiteral,
           java.lang.String datatype) {
           this.pid = pid;
           this.relationship = relationship;
           this.object = object;
           this.isLiteral = isLiteral;
           this.datatype = datatype;
    }


    /**
     * Gets the pid value for this PurgeRelationship.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this PurgeRelationship.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the relationship value for this PurgeRelationship.
     * 
     * @return relationship
     */
    public java.lang.String getRelationship() {
        return relationship;
    }


    /**
     * Sets the relationship value for this PurgeRelationship.
     * 
     * @param relationship
     */
    public void setRelationship(java.lang.String relationship) {
        this.relationship = relationship;
    }


    /**
     * Gets the object value for this PurgeRelationship.
     * 
     * @return object
     */
    public java.lang.String getObject() {
        return object;
    }


    /**
     * Sets the object value for this PurgeRelationship.
     * 
     * @param object
     */
    public void setObject(java.lang.String object) {
        this.object = object;
    }


    /**
     * Gets the isLiteral value for this PurgeRelationship.
     * 
     * @return isLiteral
     */
    public boolean isIsLiteral() {
        return isLiteral;
    }


    /**
     * Sets the isLiteral value for this PurgeRelationship.
     * 
     * @param isLiteral
     */
    public void setIsLiteral(boolean isLiteral) {
        this.isLiteral = isLiteral;
    }


    /**
     * Gets the datatype value for this PurgeRelationship.
     * 
     * @return datatype
     */
    public java.lang.String getDatatype() {
        return datatype;
    }


    /**
     * Sets the datatype value for this PurgeRelationship.
     * 
     * @param datatype
     */
    public void setDatatype(java.lang.String datatype) {
        this.datatype = datatype;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PurgeRelationship)) return false;
        PurgeRelationship other = (PurgeRelationship) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pid==null && other.getPid()==null) || 
             (this.pid!=null &&
              this.pid.equals(other.getPid()))) &&
            ((this.relationship==null && other.getRelationship()==null) || 
             (this.relationship!=null &&
              this.relationship.equals(other.getRelationship()))) &&
            ((this.object==null && other.getObject()==null) || 
             (this.object!=null &&
              this.object.equals(other.getObject()))) &&
            this.isLiteral == other.isIsLiteral() &&
            ((this.datatype==null && other.getDatatype()==null) || 
             (this.datatype!=null &&
              this.datatype.equals(other.getDatatype())));
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
        if (getPid() != null) {
            _hashCode += getPid().hashCode();
        }
        if (getRelationship() != null) {
            _hashCode += getRelationship().hashCode();
        }
        if (getObject() != null) {
            _hashCode += getObject().hashCode();
        }
        _hashCode += (isIsLiteral() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDatatype() != null) {
            _hashCode += getDatatype().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PurgeRelationship.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeRelationship"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relationship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object");
        elemField.setXmlName(new javax.xml.namespace.QName("", "object"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datatype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datatype"));
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
