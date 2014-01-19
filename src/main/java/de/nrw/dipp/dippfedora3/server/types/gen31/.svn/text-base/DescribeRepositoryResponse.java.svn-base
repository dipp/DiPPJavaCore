/**
 * DescribeRepositoryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippfedora3.server.types.gen31;

public class DescribeRepositoryResponse  implements java.io.Serializable {
    private de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo repositoryInfo;

    public DescribeRepositoryResponse() {
    }

    public DescribeRepositoryResponse(
           de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo repositoryInfo) {
           this.repositoryInfo = repositoryInfo;
    }


    /**
     * Gets the repositoryInfo value for this DescribeRepositoryResponse.
     * 
     * @return repositoryInfo
     */
    public de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo getRepositoryInfo() {
        return repositoryInfo;
    }


    /**
     * Sets the repositoryInfo value for this DescribeRepositoryResponse.
     * 
     * @param repositoryInfo
     */
    public void setRepositoryInfo(de.nrw.dipp.dippfedora3.server.types.gen31.RepositoryInfo repositoryInfo) {
        this.repositoryInfo = repositoryInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescribeRepositoryResponse)) return false;
        DescribeRepositoryResponse other = (DescribeRepositoryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.repositoryInfo==null && other.getRepositoryInfo()==null) || 
             (this.repositoryInfo!=null &&
              this.repositoryInfo.equals(other.getRepositoryInfo())));
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
        if (getRepositoryInfo() != null) {
            _hashCode += getRepositoryInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescribeRepositoryResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", ">describeRepositoryResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repositoryInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repositoryInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.fedora.info/definitions/1/0/types/", "RepositoryInfo"));
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
