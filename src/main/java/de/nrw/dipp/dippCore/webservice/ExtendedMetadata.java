/**
 * ExtendedMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class ExtendedMetadata  implements java.io.Serializable {
    private java.lang.String objectHTML;

    private java.lang.String objectMultimedia;

    private java.lang.String objectNative;

    private java.lang.String objectOther;

    private java.lang.String objectPDF;

    private java.lang.String objectXML;

    private java.lang.String objectSupplementary;

    public ExtendedMetadata() {
    }

    public ExtendedMetadata(
           java.lang.String objectHTML,
           java.lang.String objectMultimedia,
           java.lang.String objectNative,
           java.lang.String objectOther,
           java.lang.String objectPDF,
           java.lang.String objectXML,
           java.lang.String objectSupplementary) {
           this.objectHTML = objectHTML;
           this.objectMultimedia = objectMultimedia;
           this.objectNative = objectNative;
           this.objectOther = objectOther;
           this.objectPDF = objectPDF;
           this.objectXML = objectXML;
           this.objectSupplementary = objectSupplementary;
    }


    /**
     * Gets the objectHTML value for this ExtendedMetadata.
     * 
     * @return objectHTML
     */
    public java.lang.String getObjectHTML() {
        return objectHTML;
    }


    /**
     * Sets the objectHTML value for this ExtendedMetadata.
     * 
     * @param objectHTML
     */
    public void setObjectHTML(java.lang.String objectHTML) {
        this.objectHTML = objectHTML;
    }


    /**
     * Gets the objectMultimedia value for this ExtendedMetadata.
     * 
     * @return objectMultimedia
     */
    public java.lang.String getObjectMultimedia() {
        return objectMultimedia;
    }


    /**
     * Sets the objectMultimedia value for this ExtendedMetadata.
     * 
     * @param objectMultimedia
     */
    public void setObjectMultimedia(java.lang.String objectMultimedia) {
        this.objectMultimedia = objectMultimedia;
    }


    /**
     * Gets the objectNative value for this ExtendedMetadata.
     * 
     * @return objectNative
     */
    public java.lang.String getObjectNative() {
        return objectNative;
    }


    /**
     * Sets the objectNative value for this ExtendedMetadata.
     * 
     * @param objectNative
     */
    public void setObjectNative(java.lang.String objectNative) {
        this.objectNative = objectNative;
    }


    /**
     * Gets the objectOther value for this ExtendedMetadata.
     * 
     * @return objectOther
     */
    public java.lang.String getObjectOther() {
        return objectOther;
    }


    /**
     * Sets the objectOther value for this ExtendedMetadata.
     * 
     * @param objectOther
     */
    public void setObjectOther(java.lang.String objectOther) {
        this.objectOther = objectOther;
    }


    /**
     * Gets the objectPDF value for this ExtendedMetadata.
     * 
     * @return objectPDF
     */
    public java.lang.String getObjectPDF() {
        return objectPDF;
    }


    /**
     * Sets the objectPDF value for this ExtendedMetadata.
     * 
     * @param objectPDF
     */
    public void setObjectPDF(java.lang.String objectPDF) {
        this.objectPDF = objectPDF;
    }


    /**
     * Gets the objectXML value for this ExtendedMetadata.
     * 
     * @return objectXML
     */
    public java.lang.String getObjectXML() {
        return objectXML;
    }


    /**
     * Sets the objectXML value for this ExtendedMetadata.
     * 
     * @param objectXML
     */
    public void setObjectXML(java.lang.String objectXML) {
        this.objectXML = objectXML;
    }


    /**
     * Gets the objectSupplementary value for this ExtendedMetadata.
     * 
     * @return objectSupplementary
     */
    public java.lang.String getObjectSupplementary() {
        return objectSupplementary;
    }


    /**
     * Sets the objectSupplementary value for this ExtendedMetadata.
     * 
     * @param objectSupplementary
     */
    public void setObjectSupplementary(java.lang.String objectSupplementary) {
        this.objectSupplementary = objectSupplementary;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtendedMetadata)) return false;
        ExtendedMetadata other = (ExtendedMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectHTML==null && other.getObjectHTML()==null) || 
             (this.objectHTML!=null &&
              this.objectHTML.equals(other.getObjectHTML()))) &&
            ((this.objectMultimedia==null && other.getObjectMultimedia()==null) || 
             (this.objectMultimedia!=null &&
              this.objectMultimedia.equals(other.getObjectMultimedia()))) &&
            ((this.objectNative==null && other.getObjectNative()==null) || 
             (this.objectNative!=null &&
              this.objectNative.equals(other.getObjectNative()))) &&
            ((this.objectOther==null && other.getObjectOther()==null) || 
             (this.objectOther!=null &&
              this.objectOther.equals(other.getObjectOther()))) &&
            ((this.objectPDF==null && other.getObjectPDF()==null) || 
             (this.objectPDF!=null &&
              this.objectPDF.equals(other.getObjectPDF()))) &&
            ((this.objectXML==null && other.getObjectXML()==null) || 
             (this.objectXML!=null &&
              this.objectXML.equals(other.getObjectXML()))) &&
            ((this.objectSupplementary==null && other.getObjectSupplementary()==null) || 
             (this.objectSupplementary!=null &&
              this.objectSupplementary.equals(other.getObjectSupplementary())));
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
        if (getObjectHTML() != null) {
            _hashCode += getObjectHTML().hashCode();
        }
        if (getObjectMultimedia() != null) {
            _hashCode += getObjectMultimedia().hashCode();
        }
        if (getObjectNative() != null) {
            _hashCode += getObjectNative().hashCode();
        }
        if (getObjectOther() != null) {
            _hashCode += getObjectOther().hashCode();
        }
        if (getObjectPDF() != null) {
            _hashCode += getObjectPDF().hashCode();
        }
        if (getObjectXML() != null) {
            _hashCode += getObjectXML().hashCode();
        }
        if (getObjectSupplementary() != null) {
            _hashCode += getObjectSupplementary().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtendedMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "ExtendedMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectHTML");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectHTML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectMultimedia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectMultimedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectNative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectNative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectOther");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectOther"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectPDF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectPDF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectXML");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectSupplementary");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectSupplementary"));
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
