/**
 * PurgeDatastream.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class PurgeDatastream  implements java.io.Serializable {
    private java.lang.String pid;

    private java.lang.String dsID;

    private java.lang.String startDT;

    private java.lang.String endDT;

    private java.lang.String logMessage;

    private boolean force;

    public PurgeDatastream() {
    }

    public PurgeDatastream(
           java.lang.String pid,
           java.lang.String dsID,
           java.lang.String startDT,
           java.lang.String endDT,
           java.lang.String logMessage,
           boolean force) {
           this.pid = pid;
           this.dsID = dsID;
           this.startDT = startDT;
           this.endDT = endDT;
           this.logMessage = logMessage;
           this.force = force;
    }


    /**
     * Gets the pid value for this PurgeDatastream.
     * 
     * @return pid
     */
    public java.lang.String getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this PurgeDatastream.
     * 
     * @param pid
     */
    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }


    /**
     * Gets the dsID value for this PurgeDatastream.
     * 
     * @return dsID
     */
    public java.lang.String getDsID() {
        return dsID;
    }


    /**
     * Sets the dsID value for this PurgeDatastream.
     * 
     * @param dsID
     */
    public void setDsID(java.lang.String dsID) {
        this.dsID = dsID;
    }


    /**
     * Gets the startDT value for this PurgeDatastream.
     * 
     * @return startDT
     */
    public java.lang.String getStartDT() {
        return startDT;
    }


    /**
     * Sets the startDT value for this PurgeDatastream.
     * 
     * @param startDT
     */
    public void setStartDT(java.lang.String startDT) {
        this.startDT = startDT;
    }


    /**
     * Gets the endDT value for this PurgeDatastream.
     * 
     * @return endDT
     */
    public java.lang.String getEndDT() {
        return endDT;
    }


    /**
     * Sets the endDT value for this PurgeDatastream.
     * 
     * @param endDT
     */
    public void setEndDT(java.lang.String endDT) {
        this.endDT = endDT;
    }


    /**
     * Gets the logMessage value for this PurgeDatastream.
     * 
     * @return logMessage
     */
    public java.lang.String getLogMessage() {
        return logMessage;
    }


    /**
     * Sets the logMessage value for this PurgeDatastream.
     * 
     * @param logMessage
     */
    public void setLogMessage(java.lang.String logMessage) {
        this.logMessage = logMessage;
    }


    /**
     * Gets the force value for this PurgeDatastream.
     * 
     * @return force
     */
    public boolean isForce() {
        return force;
    }


    /**
     * Sets the force value for this PurgeDatastream.
     * 
     * @param force
     */
    public void setForce(boolean force) {
        this.force = force;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PurgeDatastream)) return false;
        PurgeDatastream other = (PurgeDatastream) obj;
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
            ((this.startDT==null && other.getStartDT()==null) || 
             (this.startDT!=null &&
              this.startDT.equals(other.getStartDT()))) &&
            ((this.endDT==null && other.getEndDT()==null) || 
             (this.endDT!=null &&
              this.endDT.equals(other.getEndDT()))) &&
            ((this.logMessage==null && other.getLogMessage()==null) || 
             (this.logMessage!=null &&
              this.logMessage.equals(other.getLogMessage()))) &&
            this.force == other.isForce();
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
        if (getStartDT() != null) {
            _hashCode += getStartDT().hashCode();
        }
        if (getEndDT() != null) {
            _hashCode += getEndDT().hashCode();
        }
        if (getLogMessage() != null) {
            _hashCode += getLogMessage().hashCode();
        }
        _hashCode += (isForce() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PurgeDatastream.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">purgeDatastream"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("force");
        elemField.setXmlName(new javax.xml.namespace.QName("", "force"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
