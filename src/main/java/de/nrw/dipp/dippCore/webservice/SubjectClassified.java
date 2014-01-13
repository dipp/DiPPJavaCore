/**
 * SubjectClassified.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class SubjectClassified  implements java.io.Serializable {
    private java.lang.String classificationIdent;

    private java.lang.String classificationSystem;

    private java.lang.String subjectClassified;

    public SubjectClassified() {
    }

    public SubjectClassified(
           java.lang.String classificationIdent,
           java.lang.String classificationSystem,
           java.lang.String subjectClassified) {
           this.classificationIdent = classificationIdent;
           this.classificationSystem = classificationSystem;
           this.subjectClassified = subjectClassified;
    }


    /**
     * Gets the classificationIdent value for this SubjectClassified.
     * 
     * @return classificationIdent
     */
    public java.lang.String getClassificationIdent() {
        return classificationIdent;
    }


    /**
     * Sets the classificationIdent value for this SubjectClassified.
     * 
     * @param classificationIdent
     */
    public void setClassificationIdent(java.lang.String classificationIdent) {
        this.classificationIdent = classificationIdent;
    }


    /**
     * Gets the classificationSystem value for this SubjectClassified.
     * 
     * @return classificationSystem
     */
    public java.lang.String getClassificationSystem() {
        return classificationSystem;
    }


    /**
     * Sets the classificationSystem value for this SubjectClassified.
     * 
     * @param classificationSystem
     */
    public void setClassificationSystem(java.lang.String classificationSystem) {
        this.classificationSystem = classificationSystem;
    }


    /**
     * Gets the subjectClassified value for this SubjectClassified.
     * 
     * @return subjectClassified
     */
    public java.lang.String getSubjectClassified() {
        return subjectClassified;
    }


    /**
     * Sets the subjectClassified value for this SubjectClassified.
     * 
     * @param subjectClassified
     */
    public void setSubjectClassified(java.lang.String subjectClassified) {
        this.subjectClassified = subjectClassified;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubjectClassified)) return false;
        SubjectClassified other = (SubjectClassified) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.classificationIdent==null && other.getClassificationIdent()==null) || 
             (this.classificationIdent!=null &&
              this.classificationIdent.equals(other.getClassificationIdent()))) &&
            ((this.classificationSystem==null && other.getClassificationSystem()==null) || 
             (this.classificationSystem!=null &&
              this.classificationSystem.equals(other.getClassificationSystem()))) &&
            ((this.subjectClassified==null && other.getSubjectClassified()==null) || 
             (this.subjectClassified!=null &&
              this.subjectClassified.equals(other.getSubjectClassified())));
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
        if (getClassificationIdent() != null) {
            _hashCode += getClassificationIdent().hashCode();
        }
        if (getClassificationSystem() != null) {
            _hashCode += getClassificationSystem().hashCode();
        }
        if (getSubjectClassified() != null) {
            _hashCode += getSubjectClassified().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubjectClassified.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "SubjectClassified"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classificationIdent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classificationIdent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classificationSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classificationSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subjectClassified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subjectClassified"));
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
