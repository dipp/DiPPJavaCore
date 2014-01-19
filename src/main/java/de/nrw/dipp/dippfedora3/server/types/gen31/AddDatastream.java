/**
 * AddDatastream.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class AddDatastream  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String dsID;

    private java.lang.String[] altIDs;

    private java.lang.String dsLabel;

    private boolean versionable;

    private java.lang.String MIMEType;

    private java.lang.String formatURI;

    private java.lang.String dsLocation;

    private java.lang.String controlGroup;

    private java.lang.String dsState;

    private java.lang.String checksumType;

    private java.lang.String checksum;

    private java.lang.String logMessage;

    public AddDatastream() {
    }

    public AddDatastream(
           java.lang.String pid,
           java.lang.String dsID,
           java.lang.String[] altIDs,
           java.lang.String dsLabel,
           boolean versionable,
           java.lang.String MIMEType,
           java.lang.String formatURI,
           java.lang.String dsLocation,
           java.lang.String controlGroup,
           java.lang.String dsState,
           java.lang.String checksumType,
           java.lang.String checksum,
           java.lang.String logMessage) {
           this.pid = pid;
           this.dsID = dsID;
           this.altIDs = altIDs;
           this.dsLabel = dsLabel;
           this.versionable = versionable;
           this.MIMEType = MIMEType;
           this.formatURI = formatURI;
           this.dsLocation = dsLocation;
           this.controlGroup = controlGroup;
           this.dsState = dsState;
           this.checksumType = checksumType;
           this.checksum = checksum;
           this.logMessage = logMessage;
    }


    /**
     * Gets the pid value for this AddDatastream.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this AddDatastream.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the dsID value for this AddDatastream.
     * 
     * @return dsID
     */
    public java.lang.String getDsID() {
        return dsID;
    }


    /**
     * Sets the dsID value for this AddDatastream.
     * 
     * @param dsID
     */
    public void setDsID(java.lang.String dsID) {
        this.dsID = dsID;
    }


    /**
     * Gets the altIDs value for this AddDatastream.
     * 
     * @return altIDs
     */
    public java.lang.String[] getAltIDs() {
        return altIDs;
    }


    /**
     * Sets the altIDs value for this AddDatastream.
     * 
     * @param altIDs
     */
    public void setAltIDs(java.lang.String[] altIDs) {
        this.altIDs = altIDs;
    }


    /**
     * Gets the dsLabel value for this AddDatastream.
     * 
     * @return dsLabel
     */
    public java.lang.String getDsLabel() {
        return dsLabel;
    }


    /**
     * Sets the dsLabel value for this AddDatastream.
     * 
     * @param dsLabel
     */
    public void setDsLabel(java.lang.String dsLabel) {
        this.dsLabel = dsLabel;
    }


    /**
     * Gets the versionable value for this AddDatastream.
     * 
     * @return versionable
     */
    public boolean isVersionable() {
        return versionable;
    }


    /**
     * Sets the versionable value for this AddDatastream.
     * 
     * @param versionable
     */
    public void setVersionable(boolean versionable) {
        this.versionable = versionable;
    }


    /**
     * Gets the MIMEType value for this AddDatastream.
     * 
     * @return MIMEType
     */
    public java.lang.String getMIMEType() {
        return MIMEType;
    }


    /**
     * Sets the MIMEType value for this AddDatastream.
     * 
     * @param MIMEType
     */
    public void setMIMEType(java.lang.String MIMEType) {
        this.MIMEType = MIMEType;
    }


    /**
     * Gets the formatURI value for this AddDatastream.
     * 
     * @return formatURI
     */
    public java.lang.String getFormatURI() {
        return formatURI;
    }


    /**
     * Sets the formatURI value for this AddDatastream.
     * 
     * @param formatURI
     */
    public void setFormatURI(java.lang.String formatURI) {
        this.formatURI = formatURI;
    }


    /**
     * Gets the dsLocation value for this AddDatastream.
     * 
     * @return dsLocation
     */
    public java.lang.String getDsLocation() {
        return dsLocation;
    }


    /**
     * Sets the dsLocation value for this AddDatastream.
     * 
     * @param dsLocation
     */
    public void setDsLocation(java.lang.String dsLocation) {
        this.dsLocation = dsLocation;
    }


    /**
     * Gets the controlGroup value for this AddDatastream.
     * 
     * @return controlGroup
     */
    public java.lang.String getControlGroup() {
        return controlGroup;
    }


    /**
     * Sets the controlGroup value for this AddDatastream.
     * 
     * @param controlGroup
     */
    public void setControlGroup(java.lang.String controlGroup) {
        this.controlGroup = controlGroup;
    }


    /**
     * Gets the dsState value for this AddDatastream.
     * 
     * @return dsState
     */
    public java.lang.String getDsState() {
        return dsState;
    }


    /**
     * Sets the dsState value for this AddDatastream.
     * 
     * @param dsState
     */
    public void setDsState(java.lang.String dsState) {
        this.dsState = dsState;
    }


    /**
     * Gets the checksumType value for this AddDatastream.
     * 
     * @return checksumType
     */
    public java.lang.String getChecksumType() {
        return checksumType;
    }


    /**
     * Sets the checksumType value for this AddDatastream.
     * 
     * @param checksumType
     */
    public void setChecksumType(java.lang.String checksumType) {
        this.checksumType = checksumType;
    }


    /**
     * Gets the checksum value for this AddDatastream.
     * 
     * @return checksum
     */
    public java.lang.String getChecksum() {
        return checksum;
    }


    /**
     * Sets the checksum value for this AddDatastream.
     * 
     * @param checksum
     */
    public void setChecksum(java.lang.String checksum) {
        this.checksum = checksum;
    }


    /**
     * Gets the logMessage value for this AddDatastream.
     * 
     * @return logMessage
     */
    public java.lang.String getLogMessage() {
        return logMessage;
    }


    /**
     * Sets the logMessage value for this AddDatastream.
     * 
     * @param logMessage
     */
    public void setLogMessage(java.lang.String logMessage) {
        this.logMessage = logMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddDatastream)) return false;
        AddDatastream other = (AddDatastream) obj;
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
            ((this.dsID==null && other.getDsID()==null) || 
             (this.dsID!=null &&
              this.dsID.equals(other.getDsID()))) &&
            ((this.altIDs==null && other.getAltIDs()==null) || 
             (this.altIDs!=null &&
              java.util.Arrays.equals(this.altIDs, other.getAltIDs()))) &&
            ((this.dsLabel==null && other.getDsLabel()==null) || 
             (this.dsLabel!=null &&
              this.dsLabel.equals(other.getDsLabel()))) &&
            this.versionable == other.isVersionable() &&
            ((this.MIMEType==null && other.getMIMEType()==null) || 
             (this.MIMEType!=null &&
              this.MIMEType.equals(other.getMIMEType()))) &&
            ((this.formatURI==null && other.getFormatURI()==null) || 
             (this.formatURI!=null &&
              this.formatURI.equals(other.getFormatURI()))) &&
            ((this.dsLocation==null && other.getDsLocation()==null) || 
             (this.dsLocation!=null &&
              this.dsLocation.equals(other.getDsLocation()))) &&
            ((this.controlGroup==null && other.getControlGroup()==null) || 
             (this.controlGroup!=null &&
              this.controlGroup.equals(other.getControlGroup()))) &&
            ((this.dsState==null && other.getDsState()==null) || 
             (this.dsState!=null &&
              this.dsState.equals(other.getDsState()))) &&
            ((this.checksumType==null && other.getChecksumType()==null) || 
             (this.checksumType!=null &&
              this.checksumType.equals(other.getChecksumType()))) &&
            ((this.checksum==null && other.getChecksum()==null) || 
             (this.checksum!=null &&
              this.checksum.equals(other.getChecksum()))) &&
            ((this.logMessage==null && other.getLogMessage()==null) || 
             (this.logMessage!=null &&
              this.logMessage.equals(other.getLogMessage())));
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
        if (getDsID() != null) {
            _hashCode += getDsID().hashCode();
        }
        if (getAltIDs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAltIDs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAltIDs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDsLabel() != null) {
            _hashCode += getDsLabel().hashCode();
        }
        _hashCode += (isVersionable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMIMEType() != null) {
            _hashCode += getMIMEType().hashCode();
        }
        if (getFormatURI() != null) {
            _hashCode += getFormatURI().hashCode();
        }
        if (getDsLocation() != null) {
            _hashCode += getDsLocation().hashCode();
        }
        if (getControlGroup() != null) {
            _hashCode += getControlGroup().hashCode();
        }
        if (getDsState() != null) {
            _hashCode += getDsState().hashCode();
        }
        if (getChecksumType() != null) {
            _hashCode += getChecksumType().hashCode();
        }
        if (getChecksum() != null) {
            _hashCode += getChecksum().hashCode();
        }
        if (getLogMessage() != null) {
            _hashCode += getLogMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddDatastream.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">addDatastream"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("altIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "altIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "versionable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MIMEType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MIMEType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatURI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formatURI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "controlGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checksumType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checksumType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checksum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checksum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logMessage"));
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
