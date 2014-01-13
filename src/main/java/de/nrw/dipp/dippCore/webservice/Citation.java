/**
 * Citation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class Citation  implements java.io.Serializable {
    private java.lang.String journalIssueDate;

    private java.lang.String journalTitle;

    private java.lang.String journalVolume;

    private java.lang.String journalIssueNumber;

    public Citation() {
    }

    public Citation(
           java.lang.String journalIssueDate,
           java.lang.String journalTitle,
           java.lang.String journalVolume,
           java.lang.String journalIssueNumber) {
           this.journalIssueDate = journalIssueDate;
           this.journalTitle = journalTitle;
           this.journalVolume = journalVolume;
           this.journalIssueNumber = journalIssueNumber;
    }


    /**
     * Gets the journalIssueDate value for this Citation.
     * 
     * @return journalIssueDate
     */
    public java.lang.String getJournalIssueDate() {
        return journalIssueDate;
    }


    /**
     * Sets the journalIssueDate value for this Citation.
     * 
     * @param journalIssueDate
     */
    public void setJournalIssueDate(java.lang.String journalIssueDate) {
        this.journalIssueDate = journalIssueDate;
    }


    /**
     * Gets the journalTitle value for this Citation.
     * 
     * @return journalTitle
     */
    public java.lang.String getJournalTitle() {
        return journalTitle;
    }


    /**
     * Sets the journalTitle value for this Citation.
     * 
     * @param journalTitle
     */
    public void setJournalTitle(java.lang.String journalTitle) {
        this.journalTitle = journalTitle;
    }


    /**
     * Gets the journalVolume value for this Citation.
     * 
     * @return journalVolume
     */
    public java.lang.String getJournalVolume() {
        return journalVolume;
    }


    /**
     * Sets the journalVolume value for this Citation.
     * 
     * @param journalVolume
     */
    public void setJournalVolume(java.lang.String journalVolume) {
        this.journalVolume = journalVolume;
    }


    /**
     * Gets the journalIssueNumber value for this Citation.
     * 
     * @return journalIssueNumber
     */
    public java.lang.String getJournalIssueNumber() {
        return journalIssueNumber;
    }


    /**
     * Sets the journalIssueNumber value for this Citation.
     * 
     * @param journalIssueNumber
     */
    public void setJournalIssueNumber(java.lang.String journalIssueNumber) {
        this.journalIssueNumber = journalIssueNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Citation)) return false;
        Citation other = (Citation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.journalIssueDate==null && other.getJournalIssueDate()==null) || 
             (this.journalIssueDate!=null &&
              this.journalIssueDate.equals(other.getJournalIssueDate()))) &&
            ((this.journalTitle==null && other.getJournalTitle()==null) || 
             (this.journalTitle!=null &&
              this.journalTitle.equals(other.getJournalTitle()))) &&
            ((this.journalVolume==null && other.getJournalVolume()==null) || 
             (this.journalVolume!=null &&
              this.journalVolume.equals(other.getJournalVolume()))) &&
            ((this.journalIssueNumber==null && other.getJournalIssueNumber()==null) || 
             (this.journalIssueNumber!=null &&
              this.journalIssueNumber.equals(other.getJournalIssueNumber())));
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
        if (getJournalIssueDate() != null) {
            _hashCode += getJournalIssueDate().hashCode();
        }
        if (getJournalTitle() != null) {
            _hashCode += getJournalTitle().hashCode();
        }
        if (getJournalVolume() != null) {
            _hashCode += getJournalVolume().hashCode();
        }
        if (getJournalIssueNumber() != null) {
            _hashCode += getJournalIssueNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Citation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Citation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("journalIssueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "journalIssueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("journalTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "journalTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("journalVolume");
        elemField.setXmlName(new javax.xml.namespace.QName("", "journalVolume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("journalIssueNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "journalIssueNumber"));
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
