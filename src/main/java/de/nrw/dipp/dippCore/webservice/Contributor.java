/**
 * Contributor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class Contributor  implements java.io.Serializable {
    private java.lang.String GKDIdentNumber;

    private java.lang.String PNDIdentNumber;

    private java.lang.String dippIdentNumber;

    private de.nrw.dipp.dippCore.webservice.IdentNumberType identNumber;

    private java.lang.String academicTitle;

    private java.lang.String emailAddress;

    private java.lang.String firstName;

    private java.lang.String institutionelAuthor;

    private java.lang.String lastName;

    private java.lang.String organization;

    private java.lang.String postalAddress;

    private java.lang.String role;

    public Contributor() {
    }

    public Contributor(
           java.lang.String GKDIdentNumber,
           java.lang.String PNDIdentNumber,
           java.lang.String dippIdentNumber,
           de.nrw.dipp.dippCore.webservice.IdentNumberType identNumber,
           java.lang.String academicTitle,
           java.lang.String emailAddress,
           java.lang.String firstName,
           java.lang.String institutionelAuthor,
           java.lang.String lastName,
           java.lang.String organization,
           java.lang.String postalAddress,
           java.lang.String role) {
           this.GKDIdentNumber = GKDIdentNumber;
           this.PNDIdentNumber = PNDIdentNumber;
           this.dippIdentNumber = dippIdentNumber;
           this.identNumber = identNumber;
           this.academicTitle = academicTitle;
           this.emailAddress = emailAddress;
           this.firstName = firstName;
           this.institutionelAuthor = institutionelAuthor;
           this.lastName = lastName;
           this.organization = organization;
           this.postalAddress = postalAddress;
           this.role = role;
    }


    /**
     * Gets the GKDIdentNumber value for this Contributor.
     * 
     * @return GKDIdentNumber
     */
    public java.lang.String getGKDIdentNumber() {
        return GKDIdentNumber;
    }


    /**
     * Sets the GKDIdentNumber value for this Contributor.
     * 
     * @param GKDIdentNumber
     */
    public void setGKDIdentNumber(java.lang.String GKDIdentNumber) {
        this.GKDIdentNumber = GKDIdentNumber;
    }


    /**
     * Gets the PNDIdentNumber value for this Contributor.
     * 
     * @return PNDIdentNumber
     */
    public java.lang.String getPNDIdentNumber() {
        return PNDIdentNumber;
    }


    /**
     * Sets the PNDIdentNumber value for this Contributor.
     * 
     * @param PNDIdentNumber
     */
    public void setPNDIdentNumber(java.lang.String PNDIdentNumber) {
        this.PNDIdentNumber = PNDIdentNumber;
    }


    /**
     * Gets the dippIdentNumber value for this Contributor.
     * 
     * @return dippIdentNumber
     */
    public java.lang.String getDippIdentNumber() {
        return dippIdentNumber;
    }


    /**
     * Sets the dippIdentNumber value for this Contributor.
     * 
     * @param dippIdentNumber
     */
    public void setDippIdentNumber(java.lang.String dippIdentNumber) {
        this.dippIdentNumber = dippIdentNumber;
    }


    /**
     * Gets the identNumber value for this Contributor.
     * 
     * @return identNumber
     */
    public de.nrw.dipp.dippCore.webservice.IdentNumberType getIdentNumber() {
        return identNumber;
    }


    /**
     * Sets the identNumber value for this Contributor.
     * 
     * @param identNumber
     */
    public void setIdentNumber(de.nrw.dipp.dippCore.webservice.IdentNumberType identNumber) {
        this.identNumber = identNumber;
    }


    /**
     * Gets the academicTitle value for this Contributor.
     * 
     * @return academicTitle
     */
    public java.lang.String getAcademicTitle() {
        return academicTitle;
    }


    /**
     * Sets the academicTitle value for this Contributor.
     * 
     * @param academicTitle
     */
    public void setAcademicTitle(java.lang.String academicTitle) {
        this.academicTitle = academicTitle;
    }


    /**
     * Gets the emailAddress value for this Contributor.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this Contributor.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the firstName value for this Contributor.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this Contributor.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the institutionelAuthor value for this Contributor.
     * 
     * @return institutionelAuthor
     */
    public java.lang.String getInstitutionelAuthor() {
        return institutionelAuthor;
    }


    /**
     * Sets the institutionelAuthor value for this Contributor.
     * 
     * @param institutionelAuthor
     */
    public void setInstitutionelAuthor(java.lang.String institutionelAuthor) {
        this.institutionelAuthor = institutionelAuthor;
    }


    /**
     * Gets the lastName value for this Contributor.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this Contributor.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the organization value for this Contributor.
     * 
     * @return organization
     */
    public java.lang.String getOrganization() {
        return organization;
    }


    /**
     * Sets the organization value for this Contributor.
     * 
     * @param organization
     */
    public void setOrganization(java.lang.String organization) {
        this.organization = organization;
    }


    /**
     * Gets the postalAddress value for this Contributor.
     * 
     * @return postalAddress
     */
    public java.lang.String getPostalAddress() {
        return postalAddress;
    }


    /**
     * Sets the postalAddress value for this Contributor.
     * 
     * @param postalAddress
     */
    public void setPostalAddress(java.lang.String postalAddress) {
        this.postalAddress = postalAddress;
    }


    /**
     * Gets the role value for this Contributor.
     * 
     * @return role
     */
    public java.lang.String getRole() {
        return role;
    }


    /**
     * Sets the role value for this Contributor.
     * 
     * @param role
     */
    public void setRole(java.lang.String role) {
        this.role = role;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Contributor)) return false;
        Contributor other = (Contributor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.GKDIdentNumber==null && other.getGKDIdentNumber()==null) || 
             (this.GKDIdentNumber!=null &&
              this.GKDIdentNumber.equals(other.getGKDIdentNumber()))) &&
            ((this.PNDIdentNumber==null && other.getPNDIdentNumber()==null) || 
             (this.PNDIdentNumber!=null &&
              this.PNDIdentNumber.equals(other.getPNDIdentNumber()))) &&
            ((this.dippIdentNumber==null && other.getDippIdentNumber()==null) || 
             (this.dippIdentNumber!=null &&
              this.dippIdentNumber.equals(other.getDippIdentNumber()))) &&
            ((this.identNumber==null && other.getIdentNumber()==null) || 
             (this.identNumber!=null &&
              this.identNumber.equals(other.getIdentNumber()))) &&
            ((this.academicTitle==null && other.getAcademicTitle()==null) || 
             (this.academicTitle!=null &&
              this.academicTitle.equals(other.getAcademicTitle()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.institutionelAuthor==null && other.getInstitutionelAuthor()==null) || 
             (this.institutionelAuthor!=null &&
              this.institutionelAuthor.equals(other.getInstitutionelAuthor()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.organization==null && other.getOrganization()==null) || 
             (this.organization!=null &&
              this.organization.equals(other.getOrganization()))) &&
            ((this.postalAddress==null && other.getPostalAddress()==null) || 
             (this.postalAddress!=null &&
              this.postalAddress.equals(other.getPostalAddress()))) &&
            ((this.role==null && other.getRole()==null) || 
             (this.role!=null &&
              this.role.equals(other.getRole())));
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
        if (getGKDIdentNumber() != null) {
            _hashCode += getGKDIdentNumber().hashCode();
        }
        if (getPNDIdentNumber() != null) {
            _hashCode += getPNDIdentNumber().hashCode();
        }
        if (getDippIdentNumber() != null) {
            _hashCode += getDippIdentNumber().hashCode();
        }
        if (getIdentNumber() != null) {
            _hashCode += getIdentNumber().hashCode();
        }
        if (getAcademicTitle() != null) {
            _hashCode += getAcademicTitle().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getInstitutionelAuthor() != null) {
            _hashCode += getInstitutionelAuthor().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getOrganization() != null) {
            _hashCode += getOrganization().hashCode();
        }
        if (getPostalAddress() != null) {
            _hashCode += getPostalAddress().hashCode();
        }
        if (getRole() != null) {
            _hashCode += getRole().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Contributor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Contributor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GKDIdentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GKDIdentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PNDIdentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PNDIdentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dippIdentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dippIdentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "IdentNumberType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("academicTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "academicTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionelAuthor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "institutionelAuthor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postalAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("", "role"));
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
