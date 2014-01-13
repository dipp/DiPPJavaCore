/**
 * AdministrativeMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class AdministrativeMetadata  implements java.io.Serializable {
    private java.lang.String[] childList;

    private java.lang.String metaName;

    private java.lang.String chunkURL;

    private java.lang.String absoluteURL;

    private java.lang.String metaType;

    private java.lang.String[] parentList;

    public AdministrativeMetadata() {
    }

    public AdministrativeMetadata(
           java.lang.String[] childList,
           java.lang.String metaName,
           java.lang.String chunkURL,
           java.lang.String absoluteURL,
           java.lang.String metaType,
           java.lang.String[] parentList) {
           this.childList = childList;
           this.metaName = metaName;
           this.chunkURL = chunkURL;
           this.absoluteURL = absoluteURL;
           this.metaType = metaType;
           this.parentList = parentList;
    }


    /**
     * Gets the childList value for this AdministrativeMetadata.
     * 
     * @return childList
     */
    public java.lang.String[] getChildList() {
        return childList;
    }


    /**
     * Sets the childList value for this AdministrativeMetadata.
     * 
     * @param childList
     */
    public void setChildList(java.lang.String[] childList) {
        this.childList = childList;
    }

    public java.lang.String getChildList(int i) {
        return this.childList[i];
    }

    public void setChildList(int i, java.lang.String _value) {
        this.childList[i] = _value;
    }


    /**
     * Gets the metaName value for this AdministrativeMetadata.
     * 
     * @return metaName
     */
    public java.lang.String getMetaName() {
        return metaName;
    }


    /**
     * Sets the metaName value for this AdministrativeMetadata.
     * 
     * @param metaName
     */
    public void setMetaName(java.lang.String metaName) {
        this.metaName = metaName;
    }


    /**
     * Gets the chunkURL value for this AdministrativeMetadata.
     * 
     * @return chunkURL
     */
    public java.lang.String getChunkURL() {
        return chunkURL;
    }


    /**
     * Sets the chunkURL value for this AdministrativeMetadata.
     * 
     * @param chunkURL
     */
    public void setChunkURL(java.lang.String chunkURL) {
        this.chunkURL = chunkURL;
    }


    /**
     * Gets the absoluteURL value for this AdministrativeMetadata.
     * 
     * @return absoluteURL
     */
    public java.lang.String getAbsoluteURL() {
        return absoluteURL;
    }


    /**
     * Sets the absoluteURL value for this AdministrativeMetadata.
     * 
     * @param absoluteURL
     */
    public void setAbsoluteURL(java.lang.String absoluteURL) {
        this.absoluteURL = absoluteURL;
    }


    /**
     * Gets the metaType value for this AdministrativeMetadata.
     * 
     * @return metaType
     */
    public java.lang.String getMetaType() {
        return metaType;
    }


    /**
     * Sets the metaType value for this AdministrativeMetadata.
     * 
     * @param metaType
     */
    public void setMetaType(java.lang.String metaType) {
        this.metaType = metaType;
    }


    /**
     * Gets the parentList value for this AdministrativeMetadata.
     * 
     * @return parentList
     */
    public java.lang.String[] getParentList() {
        return parentList;
    }


    /**
     * Sets the parentList value for this AdministrativeMetadata.
     * 
     * @param parentList
     */
    public void setParentList(java.lang.String[] parentList) {
        this.parentList = parentList;
    }

    public java.lang.String getParentList(int i) {
        return this.parentList[i];
    }

    public void setParentList(int i, java.lang.String _value) {
        this.parentList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AdministrativeMetadata)) return false;
        AdministrativeMetadata other = (AdministrativeMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.childList==null && other.getChildList()==null) || 
             (this.childList!=null &&
              java.util.Arrays.equals(this.childList, other.getChildList()))) &&
            ((this.metaName==null && other.getMetaName()==null) || 
             (this.metaName!=null &&
              this.metaName.equals(other.getMetaName()))) &&
            ((this.chunkURL==null && other.getChunkURL()==null) || 
             (this.chunkURL!=null &&
              this.chunkURL.equals(other.getChunkURL()))) &&
            ((this.absoluteURL==null && other.getAbsoluteURL()==null) || 
             (this.absoluteURL!=null &&
              this.absoluteURL.equals(other.getAbsoluteURL()))) &&
            ((this.metaType==null && other.getMetaType()==null) || 
             (this.metaType!=null &&
              this.metaType.equals(other.getMetaType()))) &&
            ((this.parentList==null && other.getParentList()==null) || 
             (this.parentList!=null &&
              java.util.Arrays.equals(this.parentList, other.getParentList())));
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
        if (getChildList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChildList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChildList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMetaName() != null) {
            _hashCode += getMetaName().hashCode();
        }
        if (getChunkURL() != null) {
            _hashCode += getChunkURL().hashCode();
        }
        if (getAbsoluteURL() != null) {
            _hashCode += getAbsoluteURL().hashCode();
        }
        if (getMetaType() != null) {
            _hashCode += getMetaType().hashCode();
        }
        if (getParentList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParentList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParentList(), i);
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
        new org.apache.axis.description.TypeDesc(AdministrativeMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "AdministrativeMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "childList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metaName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "metaName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chunkURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chunkURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("absoluteURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "absoluteURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metaType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "metaType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
