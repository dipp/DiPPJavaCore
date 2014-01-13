/**
 * QualifiedDublinCore.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.dipp.dippCore.webservice;

public class QualifiedDublinCore  implements java.io.Serializable {
    private de.nrw.dipp.dippCore.webservice.Element[] DCTermsAbstract;

    private java.lang.String[] DDC;

    private java.lang.String[] accessRights;

    private de.nrw.dipp.dippCore.webservice.Element[] alternative;

    private java.lang.String[] articleType;

    private java.lang.String[] audience;

    private java.util.Calendar available;

    private de.nrw.dipp.dippCore.webservice.Citation[] bibliographicCitation;

    private java.lang.String[] conformsTo;

    private de.nrw.dipp.dippCore.webservice.Contributor[] contributor;

    private java.lang.String[] coverage;

    private java.util.Calendar created;

    private de.nrw.dipp.dippCore.webservice.CreatorCorporated[] creatorCorporated;

    private de.nrw.dipp.dippCore.webservice.CreatorPerson[] creatorPerson;

    private java.lang.String[] date;

    private java.util.Calendar dateAccepted;

    private java.util.Calendar dateCopyrighted;

    private java.util.Calendar dateSubmitted;

    private java.lang.String[] description;

    private java.lang.String[] docType;

    private java.lang.String[] educationLevel;

    private java.lang.String[] extent;

    private java.lang.String[] format;

    private java.lang.String[] hasFormat;

    private de.nrw.dipp.dippCore.webservice.Part[] hasPart;

    private java.lang.String[] hasVersionOf;

    private java.lang.String[] identifier;

    private java.lang.String identifierDOI;

    private java.lang.String identifierISBN;

    private java.lang.String identifierISSN;

    private java.lang.String identifierURL;

    private java.lang.String identifierURN;

    private java.lang.String[] isFormatOf;

    private de.nrw.dipp.dippCore.webservice.Part[] isPartOf;

    private java.lang.String[] isReferencedBy;

    private java.lang.String[] isReplacedBy;

    private java.lang.String[] isRequiredBy;

    private de.nrw.dipp.dippCore.webservice.Part[] isVersionOf;

    private java.util.Calendar issued;

    private java.lang.String[] language;

    private java.lang.String[] mediator;

    private java.lang.String[] medium;

    private java.util.Calendar modified;

    private java.lang.String[] pubType;

    private java.lang.String[] publisher;

    private java.lang.String[] references;

    private java.lang.String[] relation;

    private java.lang.String[] replaces;

    private java.lang.String[] requires;

    private java.lang.String[] rights;

    private java.lang.String[] spatial;

    private java.lang.String[] source;

    private java.lang.String[] subject;

    private de.nrw.dipp.dippCore.webservice.SubjectClassified[] subjectClassified;

    private de.nrw.dipp.dippCore.webservice.Element[] tableOfContents;

    private java.lang.String[] temporal;

    private de.nrw.dipp.dippCore.webservice.Element[] title;

    private java.lang.String[] type;

    private java.util.Calendar valid;

    public QualifiedDublinCore() {
    }

    public QualifiedDublinCore(
           de.nrw.dipp.dippCore.webservice.Element[] DCTermsAbstract,
           java.lang.String[] DDC,
           java.lang.String[] accessRights,
           de.nrw.dipp.dippCore.webservice.Element[] alternative,
           java.lang.String[] articleType,
           java.lang.String[] audience,
           java.util.Calendar available,
           de.nrw.dipp.dippCore.webservice.Citation[] bibliographicCitation,
           java.lang.String[] conformsTo,
           de.nrw.dipp.dippCore.webservice.Contributor[] contributor,
           java.lang.String[] coverage,
           java.util.Calendar created,
           de.nrw.dipp.dippCore.webservice.CreatorCorporated[] creatorCorporated,
           de.nrw.dipp.dippCore.webservice.CreatorPerson[] creatorPerson,
           java.lang.String[] date,
           java.util.Calendar dateAccepted,
           java.util.Calendar dateCopyrighted,
           java.util.Calendar dateSubmitted,
           java.lang.String[] description,
           java.lang.String[] docType,
           java.lang.String[] educationLevel,
           java.lang.String[] extent,
           java.lang.String[] format,
           java.lang.String[] hasFormat,
           de.nrw.dipp.dippCore.webservice.Part[] hasPart,
           java.lang.String[] hasVersionOf,
           java.lang.String[] identifier,
           java.lang.String identifierDOI,
           java.lang.String identifierISBN,
           java.lang.String identifierISSN,
           java.lang.String identifierURL,
           java.lang.String identifierURN,
           java.lang.String[] isFormatOf,
           de.nrw.dipp.dippCore.webservice.Part[] isPartOf,
           java.lang.String[] isReferencedBy,
           java.lang.String[] isReplacedBy,
           java.lang.String[] isRequiredBy,
           de.nrw.dipp.dippCore.webservice.Part[] isVersionOf,
           java.util.Calendar issued,
           java.lang.String[] language,
           java.lang.String[] mediator,
           java.lang.String[] medium,
           java.util.Calendar modified,
           java.lang.String[] pubType,
           java.lang.String[] publisher,
           java.lang.String[] references,
           java.lang.String[] relation,
           java.lang.String[] replaces,
           java.lang.String[] requires,
           java.lang.String[] rights,
           java.lang.String[] spatial,
           java.lang.String[] source,
           java.lang.String[] subject,
           de.nrw.dipp.dippCore.webservice.SubjectClassified[] subjectClassified,
           de.nrw.dipp.dippCore.webservice.Element[] tableOfContents,
           java.lang.String[] temporal,
           de.nrw.dipp.dippCore.webservice.Element[] title,
           java.lang.String[] type,
           java.util.Calendar valid) {
           this.DCTermsAbstract = DCTermsAbstract;
           this.DDC = DDC;
           this.accessRights = accessRights;
           this.alternative = alternative;
           this.articleType = articleType;
           this.audience = audience;
           this.available = available;
           this.bibliographicCitation = bibliographicCitation;
           this.conformsTo = conformsTo;
           this.contributor = contributor;
           this.coverage = coverage;
           this.created = created;
           this.creatorCorporated = creatorCorporated;
           this.creatorPerson = creatorPerson;
           this.date = date;
           this.dateAccepted = dateAccepted;
           this.dateCopyrighted = dateCopyrighted;
           this.dateSubmitted = dateSubmitted;
           this.description = description;
           this.docType = docType;
           this.educationLevel = educationLevel;
           this.extent = extent;
           this.format = format;
           this.hasFormat = hasFormat;
           this.hasPart = hasPart;
           this.hasVersionOf = hasVersionOf;
           this.identifier = identifier;
           this.identifierDOI = identifierDOI;
           this.identifierISBN = identifierISBN;
           this.identifierISSN = identifierISSN;
           this.identifierURL = identifierURL;
           this.identifierURN = identifierURN;
           this.isFormatOf = isFormatOf;
           this.isPartOf = isPartOf;
           this.isReferencedBy = isReferencedBy;
           this.isReplacedBy = isReplacedBy;
           this.isRequiredBy = isRequiredBy;
           this.isVersionOf = isVersionOf;
           this.issued = issued;
           this.language = language;
           this.mediator = mediator;
           this.medium = medium;
           this.modified = modified;
           this.pubType = pubType;
           this.publisher = publisher;
           this.references = references;
           this.relation = relation;
           this.replaces = replaces;
           this.requires = requires;
           this.rights = rights;
           this.spatial = spatial;
           this.source = source;
           this.subject = subject;
           this.subjectClassified = subjectClassified;
           this.tableOfContents = tableOfContents;
           this.temporal = temporal;
           this.title = title;
           this.type = type;
           this.valid = valid;
    }


    /**
     * Gets the DCTermsAbstract value for this QualifiedDublinCore.
     * 
     * @return DCTermsAbstract
     */
    public de.nrw.dipp.dippCore.webservice.Element[] getDCTermsAbstract() {
        return DCTermsAbstract;
    }


    /**
     * Sets the DCTermsAbstract value for this QualifiedDublinCore.
     * 
     * @param DCTermsAbstract
     */
    public void setDCTermsAbstract(de.nrw.dipp.dippCore.webservice.Element[] DCTermsAbstract) {
        this.DCTermsAbstract = DCTermsAbstract;
    }

    public de.nrw.dipp.dippCore.webservice.Element getDCTermsAbstract(int i) {
        return this.DCTermsAbstract[i];
    }

    public void setDCTermsAbstract(int i, de.nrw.dipp.dippCore.webservice.Element _value) {
        this.DCTermsAbstract[i] = _value;
    }


    /**
     * Gets the DDC value for this QualifiedDublinCore.
     * 
     * @return DDC
     */
    public java.lang.String[] getDDC() {
        return DDC;
    }


    /**
     * Sets the DDC value for this QualifiedDublinCore.
     * 
     * @param DDC
     */
    public void setDDC(java.lang.String[] DDC) {
        this.DDC = DDC;
    }

    public java.lang.String getDDC(int i) {
        return this.DDC[i];
    }

    public void setDDC(int i, java.lang.String _value) {
        this.DDC[i] = _value;
    }


    /**
     * Gets the accessRights value for this QualifiedDublinCore.
     * 
     * @return accessRights
     */
    public java.lang.String[] getAccessRights() {
        return accessRights;
    }


    /**
     * Sets the accessRights value for this QualifiedDublinCore.
     * 
     * @param accessRights
     */
    public void setAccessRights(java.lang.String[] accessRights) {
        this.accessRights = accessRights;
    }

    public java.lang.String getAccessRights(int i) {
        return this.accessRights[i];
    }

    public void setAccessRights(int i, java.lang.String _value) {
        this.accessRights[i] = _value;
    }


    /**
     * Gets the alternative value for this QualifiedDublinCore.
     * 
     * @return alternative
     */
    public de.nrw.dipp.dippCore.webservice.Element[] getAlternative() {
        return alternative;
    }


    /**
     * Sets the alternative value for this QualifiedDublinCore.
     * 
     * @param alternative
     */
    public void setAlternative(de.nrw.dipp.dippCore.webservice.Element[] alternative) {
        this.alternative = alternative;
    }

    public de.nrw.dipp.dippCore.webservice.Element getAlternative(int i) {
        return this.alternative[i];
    }

    public void setAlternative(int i, de.nrw.dipp.dippCore.webservice.Element _value) {
        this.alternative[i] = _value;
    }


    /**
     * Gets the articleType value for this QualifiedDublinCore.
     * 
     * @return articleType
     */
    public java.lang.String[] getArticleType() {
        return articleType;
    }


    /**
     * Sets the articleType value for this QualifiedDublinCore.
     * 
     * @param articleType
     */
    public void setArticleType(java.lang.String[] articleType) {
        this.articleType = articleType;
    }

    public java.lang.String getArticleType(int i) {
        return this.articleType[i];
    }

    public void setArticleType(int i, java.lang.String _value) {
        this.articleType[i] = _value;
    }


    /**
     * Gets the audience value for this QualifiedDublinCore.
     * 
     * @return audience
     */
    public java.lang.String[] getAudience() {
        return audience;
    }


    /**
     * Sets the audience value for this QualifiedDublinCore.
     * 
     * @param audience
     */
    public void setAudience(java.lang.String[] audience) {
        this.audience = audience;
    }

    public java.lang.String getAudience(int i) {
        return this.audience[i];
    }

    public void setAudience(int i, java.lang.String _value) {
        this.audience[i] = _value;
    }


    /**
     * Gets the available value for this QualifiedDublinCore.
     * 
     * @return available
     */
    public java.util.Calendar getAvailable() {
        return available;
    }


    /**
     * Sets the available value for this QualifiedDublinCore.
     * 
     * @param available
     */
    public void setAvailable(java.util.Calendar available) {
        this.available = available;
    }


    /**
     * Gets the bibliographicCitation value for this QualifiedDublinCore.
     * 
     * @return bibliographicCitation
     */
    public de.nrw.dipp.dippCore.webservice.Citation[] getBibliographicCitation() {
        return bibliographicCitation;
    }


    /**
     * Sets the bibliographicCitation value for this QualifiedDublinCore.
     * 
     * @param bibliographicCitation
     */
    public void setBibliographicCitation(de.nrw.dipp.dippCore.webservice.Citation[] bibliographicCitation) {
        this.bibliographicCitation = bibliographicCitation;
    }

    public de.nrw.dipp.dippCore.webservice.Citation getBibliographicCitation(int i) {
        return this.bibliographicCitation[i];
    }

    public void setBibliographicCitation(int i, de.nrw.dipp.dippCore.webservice.Citation _value) {
        this.bibliographicCitation[i] = _value;
    }


    /**
     * Gets the conformsTo value for this QualifiedDublinCore.
     * 
     * @return conformsTo
     */
    public java.lang.String[] getConformsTo() {
        return conformsTo;
    }


    /**
     * Sets the conformsTo value for this QualifiedDublinCore.
     * 
     * @param conformsTo
     */
    public void setConformsTo(java.lang.String[] conformsTo) {
        this.conformsTo = conformsTo;
    }

    public java.lang.String getConformsTo(int i) {
        return this.conformsTo[i];
    }

    public void setConformsTo(int i, java.lang.String _value) {
        this.conformsTo[i] = _value;
    }


    /**
     * Gets the contributor value for this QualifiedDublinCore.
     * 
     * @return contributor
     */
    public de.nrw.dipp.dippCore.webservice.Contributor[] getContributor() {
        return contributor;
    }


    /**
     * Sets the contributor value for this QualifiedDublinCore.
     * 
     * @param contributor
     */
    public void setContributor(de.nrw.dipp.dippCore.webservice.Contributor[] contributor) {
        this.contributor = contributor;
    }

    public de.nrw.dipp.dippCore.webservice.Contributor getContributor(int i) {
        return this.contributor[i];
    }

    public void setContributor(int i, de.nrw.dipp.dippCore.webservice.Contributor _value) {
        this.contributor[i] = _value;
    }


    /**
     * Gets the coverage value for this QualifiedDublinCore.
     * 
     * @return coverage
     */
    public java.lang.String[] getCoverage() {
        return coverage;
    }


    /**
     * Sets the coverage value for this QualifiedDublinCore.
     * 
     * @param coverage
     */
    public void setCoverage(java.lang.String[] coverage) {
        this.coverage = coverage;
    }

    public java.lang.String getCoverage(int i) {
        return this.coverage[i];
    }

    public void setCoverage(int i, java.lang.String _value) {
        this.coverage[i] = _value;
    }


    /**
     * Gets the created value for this QualifiedDublinCore.
     * 
     * @return created
     */
    public java.util.Calendar getCreated() {
        return created;
    }


    /**
     * Sets the created value for this QualifiedDublinCore.
     * 
     * @param created
     */
    public void setCreated(java.util.Calendar created) {
        this.created = created;
    }


    /**
     * Gets the creatorCorporated value for this QualifiedDublinCore.
     * 
     * @return creatorCorporated
     */
    public de.nrw.dipp.dippCore.webservice.CreatorCorporated[] getCreatorCorporated() {
        return creatorCorporated;
    }


    /**
     * Sets the creatorCorporated value for this QualifiedDublinCore.
     * 
     * @param creatorCorporated
     */
    public void setCreatorCorporated(de.nrw.dipp.dippCore.webservice.CreatorCorporated[] creatorCorporated) {
        this.creatorCorporated = creatorCorporated;
    }

    public de.nrw.dipp.dippCore.webservice.CreatorCorporated getCreatorCorporated(int i) {
        return this.creatorCorporated[i];
    }

    public void setCreatorCorporated(int i, de.nrw.dipp.dippCore.webservice.CreatorCorporated _value) {
        this.creatorCorporated[i] = _value;
    }


    /**
     * Gets the creatorPerson value for this QualifiedDublinCore.
     * 
     * @return creatorPerson
     */
    public de.nrw.dipp.dippCore.webservice.CreatorPerson[] getCreatorPerson() {
        return creatorPerson;
    }


    /**
     * Sets the creatorPerson value for this QualifiedDublinCore.
     * 
     * @param creatorPerson
     */
    public void setCreatorPerson(de.nrw.dipp.dippCore.webservice.CreatorPerson[] creatorPerson) {
        this.creatorPerson = creatorPerson;
    }

    public de.nrw.dipp.dippCore.webservice.CreatorPerson getCreatorPerson(int i) {
        return this.creatorPerson[i];
    }

    public void setCreatorPerson(int i, de.nrw.dipp.dippCore.webservice.CreatorPerson _value) {
        this.creatorPerson[i] = _value;
    }


    /**
     * Gets the date value for this QualifiedDublinCore.
     * 
     * @return date
     */
    public java.lang.String[] getDate() {
        return date;
    }


    /**
     * Sets the date value for this QualifiedDublinCore.
     * 
     * @param date
     */
    public void setDate(java.lang.String[] date) {
        this.date = date;
    }

    public java.lang.String getDate(int i) {
        return this.date[i];
    }

    public void setDate(int i, java.lang.String _value) {
        this.date[i] = _value;
    }


    /**
     * Gets the dateAccepted value for this QualifiedDublinCore.
     * 
     * @return dateAccepted
     */
    public java.util.Calendar getDateAccepted() {
        return dateAccepted;
    }


    /**
     * Sets the dateAccepted value for this QualifiedDublinCore.
     * 
     * @param dateAccepted
     */
    public void setDateAccepted(java.util.Calendar dateAccepted) {
        this.dateAccepted = dateAccepted;
    }


    /**
     * Gets the dateCopyrighted value for this QualifiedDublinCore.
     * 
     * @return dateCopyrighted
     */
    public java.util.Calendar getDateCopyrighted() {
        return dateCopyrighted;
    }


    /**
     * Sets the dateCopyrighted value for this QualifiedDublinCore.
     * 
     * @param dateCopyrighted
     */
    public void setDateCopyrighted(java.util.Calendar dateCopyrighted) {
        this.dateCopyrighted = dateCopyrighted;
    }


    /**
     * Gets the dateSubmitted value for this QualifiedDublinCore.
     * 
     * @return dateSubmitted
     */
    public java.util.Calendar getDateSubmitted() {
        return dateSubmitted;
    }


    /**
     * Sets the dateSubmitted value for this QualifiedDublinCore.
     * 
     * @param dateSubmitted
     */
    public void setDateSubmitted(java.util.Calendar dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }


    /**
     * Gets the description value for this QualifiedDublinCore.
     * 
     * @return description
     */
    public java.lang.String[] getDescription() {
        return description;
    }


    /**
     * Sets the description value for this QualifiedDublinCore.
     * 
     * @param description
     */
    public void setDescription(java.lang.String[] description) {
        this.description = description;
    }

    public java.lang.String getDescription(int i) {
        return this.description[i];
    }

    public void setDescription(int i, java.lang.String _value) {
        this.description[i] = _value;
    }


    /**
     * Gets the docType value for this QualifiedDublinCore.
     * 
     * @return docType
     */
    public java.lang.String[] getDocType() {
        return docType;
    }


    /**
     * Sets the docType value for this QualifiedDublinCore.
     * 
     * @param docType
     */
    public void setDocType(java.lang.String[] docType) {
        this.docType = docType;
    }

    public java.lang.String getDocType(int i) {
        return this.docType[i];
    }

    public void setDocType(int i, java.lang.String _value) {
        this.docType[i] = _value;
    }


    /**
     * Gets the educationLevel value for this QualifiedDublinCore.
     * 
     * @return educationLevel
     */
    public java.lang.String[] getEducationLevel() {
        return educationLevel;
    }


    /**
     * Sets the educationLevel value for this QualifiedDublinCore.
     * 
     * @param educationLevel
     */
    public void setEducationLevel(java.lang.String[] educationLevel) {
        this.educationLevel = educationLevel;
    }

    public java.lang.String getEducationLevel(int i) {
        return this.educationLevel[i];
    }

    public void setEducationLevel(int i, java.lang.String _value) {
        this.educationLevel[i] = _value;
    }


    /**
     * Gets the extent value for this QualifiedDublinCore.
     * 
     * @return extent
     */
    public java.lang.String[] getExtent() {
        return extent;
    }


    /**
     * Sets the extent value for this QualifiedDublinCore.
     * 
     * @param extent
     */
    public void setExtent(java.lang.String[] extent) {
        this.extent = extent;
    }

    public java.lang.String getExtent(int i) {
        return this.extent[i];
    }

    public void setExtent(int i, java.lang.String _value) {
        this.extent[i] = _value;
    }


    /**
     * Gets the format value for this QualifiedDublinCore.
     * 
     * @return format
     */
    public java.lang.String[] getFormat() {
        return format;
    }


    /**
     * Sets the format value for this QualifiedDublinCore.
     * 
     * @param format
     */
    public void setFormat(java.lang.String[] format) {
        this.format = format;
    }

    public java.lang.String getFormat(int i) {
        return this.format[i];
    }

    public void setFormat(int i, java.lang.String _value) {
        this.format[i] = _value;
    }


    /**
     * Gets the hasFormat value for this QualifiedDublinCore.
     * 
     * @return hasFormat
     */
    public java.lang.String[] getHasFormat() {
        return hasFormat;
    }


    /**
     * Sets the hasFormat value for this QualifiedDublinCore.
     * 
     * @param hasFormat
     */
    public void setHasFormat(java.lang.String[] hasFormat) {
        this.hasFormat = hasFormat;
    }

    public java.lang.String getHasFormat(int i) {
        return this.hasFormat[i];
    }

    public void setHasFormat(int i, java.lang.String _value) {
        this.hasFormat[i] = _value;
    }


    /**
     * Gets the hasPart value for this QualifiedDublinCore.
     * 
     * @return hasPart
     */
    public de.nrw.dipp.dippCore.webservice.Part[] getHasPart() {
        return hasPart;
    }


    /**
     * Sets the hasPart value for this QualifiedDublinCore.
     * 
     * @param hasPart
     */
    public void setHasPart(de.nrw.dipp.dippCore.webservice.Part[] hasPart) {
        this.hasPart = hasPart;
    }

    public de.nrw.dipp.dippCore.webservice.Part getHasPart(int i) {
        return this.hasPart[i];
    }

    public void setHasPart(int i, de.nrw.dipp.dippCore.webservice.Part _value) {
        this.hasPart[i] = _value;
    }


    /**
     * Gets the hasVersionOf value for this QualifiedDublinCore.
     * 
     * @return hasVersionOf
     */
    public java.lang.String[] getHasVersionOf() {
        return hasVersionOf;
    }


    /**
     * Sets the hasVersionOf value for this QualifiedDublinCore.
     * 
     * @param hasVersionOf
     */
    public void setHasVersionOf(java.lang.String[] hasVersionOf) {
        this.hasVersionOf = hasVersionOf;
    }

    public java.lang.String getHasVersionOf(int i) {
        return this.hasVersionOf[i];
    }

    public void setHasVersionOf(int i, java.lang.String _value) {
        this.hasVersionOf[i] = _value;
    }


    /**
     * Gets the identifier value for this QualifiedDublinCore.
     * 
     * @return identifier
     */
    public java.lang.String[] getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this QualifiedDublinCore.
     * 
     * @param identifier
     */
    public void setIdentifier(java.lang.String[] identifier) {
        this.identifier = identifier;
    }

    public java.lang.String getIdentifier(int i) {
        return this.identifier[i];
    }

    public void setIdentifier(int i, java.lang.String _value) {
        this.identifier[i] = _value;
    }


    /**
     * Gets the identifierDOI value for this QualifiedDublinCore.
     * 
     * @return identifierDOI
     */
    public java.lang.String getIdentifierDOI() {
        return identifierDOI;
    }


    /**
     * Sets the identifierDOI value for this QualifiedDublinCore.
     * 
     * @param identifierDOI
     */
    public void setIdentifierDOI(java.lang.String identifierDOI) {
        this.identifierDOI = identifierDOI;
    }


    /**
     * Gets the identifierISBN value for this QualifiedDublinCore.
     * 
     * @return identifierISBN
     */
    public java.lang.String getIdentifierISBN() {
        return identifierISBN;
    }


    /**
     * Sets the identifierISBN value for this QualifiedDublinCore.
     * 
     * @param identifierISBN
     */
    public void setIdentifierISBN(java.lang.String identifierISBN) {
        this.identifierISBN = identifierISBN;
    }


    /**
     * Gets the identifierISSN value for this QualifiedDublinCore.
     * 
     * @return identifierISSN
     */
    public java.lang.String getIdentifierISSN() {
        return identifierISSN;
    }


    /**
     * Sets the identifierISSN value for this QualifiedDublinCore.
     * 
     * @param identifierISSN
     */
    public void setIdentifierISSN(java.lang.String identifierISSN) {
        this.identifierISSN = identifierISSN;
    }


    /**
     * Gets the identifierURL value for this QualifiedDublinCore.
     * 
     * @return identifierURL
     */
    public java.lang.String getIdentifierURL() {
        return identifierURL;
    }


    /**
     * Sets the identifierURL value for this QualifiedDublinCore.
     * 
     * @param identifierURL
     */
    public void setIdentifierURL(java.lang.String identifierURL) {
        this.identifierURL = identifierURL;
    }


    /**
     * Gets the identifierURN value for this QualifiedDublinCore.
     * 
     * @return identifierURN
     */
    public java.lang.String getIdentifierURN() {
        return identifierURN;
    }


    /**
     * Sets the identifierURN value for this QualifiedDublinCore.
     * 
     * @param identifierURN
     */
    public void setIdentifierURN(java.lang.String identifierURN) {
        this.identifierURN = identifierURN;
    }


    /**
     * Gets the isFormatOf value for this QualifiedDublinCore.
     * 
     * @return isFormatOf
     */
    public java.lang.String[] getIsFormatOf() {
        return isFormatOf;
    }


    /**
     * Sets the isFormatOf value for this QualifiedDublinCore.
     * 
     * @param isFormatOf
     */
    public void setIsFormatOf(java.lang.String[] isFormatOf) {
        this.isFormatOf = isFormatOf;
    }

    public java.lang.String getIsFormatOf(int i) {
        return this.isFormatOf[i];
    }

    public void setIsFormatOf(int i, java.lang.String _value) {
        this.isFormatOf[i] = _value;
    }


    /**
     * Gets the isPartOf value for this QualifiedDublinCore.
     * 
     * @return isPartOf
     */
    public de.nrw.dipp.dippCore.webservice.Part[] getIsPartOf() {
        return isPartOf;
    }


    /**
     * Sets the isPartOf value for this QualifiedDublinCore.
     * 
     * @param isPartOf
     */
    public void setIsPartOf(de.nrw.dipp.dippCore.webservice.Part[] isPartOf) {
        this.isPartOf = isPartOf;
    }

    public de.nrw.dipp.dippCore.webservice.Part getIsPartOf(int i) {
        return this.isPartOf[i];
    }

    public void setIsPartOf(int i, de.nrw.dipp.dippCore.webservice.Part _value) {
        this.isPartOf[i] = _value;
    }


    /**
     * Gets the isReferencedBy value for this QualifiedDublinCore.
     * 
     * @return isReferencedBy
     */
    public java.lang.String[] getIsReferencedBy() {
        return isReferencedBy;
    }


    /**
     * Sets the isReferencedBy value for this QualifiedDublinCore.
     * 
     * @param isReferencedBy
     */
    public void setIsReferencedBy(java.lang.String[] isReferencedBy) {
        this.isReferencedBy = isReferencedBy;
    }

    public java.lang.String getIsReferencedBy(int i) {
        return this.isReferencedBy[i];
    }

    public void setIsReferencedBy(int i, java.lang.String _value) {
        this.isReferencedBy[i] = _value;
    }


    /**
     * Gets the isReplacedBy value for this QualifiedDublinCore.
     * 
     * @return isReplacedBy
     */
    public java.lang.String[] getIsReplacedBy() {
        return isReplacedBy;
    }


    /**
     * Sets the isReplacedBy value for this QualifiedDublinCore.
     * 
     * @param isReplacedBy
     */
    public void setIsReplacedBy(java.lang.String[] isReplacedBy) {
        this.isReplacedBy = isReplacedBy;
    }

    public java.lang.String getIsReplacedBy(int i) {
        return this.isReplacedBy[i];
    }

    public void setIsReplacedBy(int i, java.lang.String _value) {
        this.isReplacedBy[i] = _value;
    }


    /**
     * Gets the isRequiredBy value for this QualifiedDublinCore.
     * 
     * @return isRequiredBy
     */
    public java.lang.String[] getIsRequiredBy() {
        return isRequiredBy;
    }


    /**
     * Sets the isRequiredBy value for this QualifiedDublinCore.
     * 
     * @param isRequiredBy
     */
    public void setIsRequiredBy(java.lang.String[] isRequiredBy) {
        this.isRequiredBy = isRequiredBy;
    }

    public java.lang.String getIsRequiredBy(int i) {
        return this.isRequiredBy[i];
    }

    public void setIsRequiredBy(int i, java.lang.String _value) {
        this.isRequiredBy[i] = _value;
    }


    /**
     * Gets the isVersionOf value for this QualifiedDublinCore.
     * 
     * @return isVersionOf
     */
    public de.nrw.dipp.dippCore.webservice.Part[] getIsVersionOf() {
        return isVersionOf;
    }


    /**
     * Sets the isVersionOf value for this QualifiedDublinCore.
     * 
     * @param isVersionOf
     */
    public void setIsVersionOf(de.nrw.dipp.dippCore.webservice.Part[] isVersionOf) {
        this.isVersionOf = isVersionOf;
    }

    public de.nrw.dipp.dippCore.webservice.Part getIsVersionOf(int i) {
        return this.isVersionOf[i];
    }

    public void setIsVersionOf(int i, de.nrw.dipp.dippCore.webservice.Part _value) {
        this.isVersionOf[i] = _value;
    }


    /**
     * Gets the issued value for this QualifiedDublinCore.
     * 
     * @return issued
     */
    public java.util.Calendar getIssued() {
        return issued;
    }


    /**
     * Sets the issued value for this QualifiedDublinCore.
     * 
     * @param issued
     */
    public void setIssued(java.util.Calendar issued) {
        this.issued = issued;
    }


    /**
     * Gets the language value for this QualifiedDublinCore.
     * 
     * @return language
     */
    public java.lang.String[] getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this QualifiedDublinCore.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String[] language) {
        this.language = language;
    }

    public java.lang.String getLanguage(int i) {
        return this.language[i];
    }

    public void setLanguage(int i, java.lang.String _value) {
        this.language[i] = _value;
    }


    /**
     * Gets the mediator value for this QualifiedDublinCore.
     * 
     * @return mediator
     */
    public java.lang.String[] getMediator() {
        return mediator;
    }


    /**
     * Sets the mediator value for this QualifiedDublinCore.
     * 
     * @param mediator
     */
    public void setMediator(java.lang.String[] mediator) {
        this.mediator = mediator;
    }

    public java.lang.String getMediator(int i) {
        return this.mediator[i];
    }

    public void setMediator(int i, java.lang.String _value) {
        this.mediator[i] = _value;
    }


    /**
     * Gets the medium value for this QualifiedDublinCore.
     * 
     * @return medium
     */
    public java.lang.String[] getMedium() {
        return medium;
    }


    /**
     * Sets the medium value for this QualifiedDublinCore.
     * 
     * @param medium
     */
    public void setMedium(java.lang.String[] medium) {
        this.medium = medium;
    }

    public java.lang.String getMedium(int i) {
        return this.medium[i];
    }

    public void setMedium(int i, java.lang.String _value) {
        this.medium[i] = _value;
    }


    /**
     * Gets the modified value for this QualifiedDublinCore.
     * 
     * @return modified
     */
    public java.util.Calendar getModified() {
        return modified;
    }


    /**
     * Sets the modified value for this QualifiedDublinCore.
     * 
     * @param modified
     */
    public void setModified(java.util.Calendar modified) {
        this.modified = modified;
    }


    /**
     * Gets the pubType value for this QualifiedDublinCore.
     * 
     * @return pubType
     */
    public java.lang.String[] getPubType() {
        return pubType;
    }


    /**
     * Sets the pubType value for this QualifiedDublinCore.
     * 
     * @param pubType
     */
    public void setPubType(java.lang.String[] pubType) {
        this.pubType = pubType;
    }

    public java.lang.String getPubType(int i) {
        return this.pubType[i];
    }

    public void setPubType(int i, java.lang.String _value) {
        this.pubType[i] = _value;
    }


    /**
     * Gets the publisher value for this QualifiedDublinCore.
     * 
     * @return publisher
     */
    public java.lang.String[] getPublisher() {
        return publisher;
    }


    /**
     * Sets the publisher value for this QualifiedDublinCore.
     * 
     * @param publisher
     */
    public void setPublisher(java.lang.String[] publisher) {
        this.publisher = publisher;
    }

    public java.lang.String getPublisher(int i) {
        return this.publisher[i];
    }

    public void setPublisher(int i, java.lang.String _value) {
        this.publisher[i] = _value;
    }


    /**
     * Gets the references value for this QualifiedDublinCore.
     * 
     * @return references
     */
    public java.lang.String[] getReferences() {
        return references;
    }


    /**
     * Sets the references value for this QualifiedDublinCore.
     * 
     * @param references
     */
    public void setReferences(java.lang.String[] references) {
        this.references = references;
    }

    public java.lang.String getReferences(int i) {
        return this.references[i];
    }

    public void setReferences(int i, java.lang.String _value) {
        this.references[i] = _value;
    }


    /**
     * Gets the relation value for this QualifiedDublinCore.
     * 
     * @return relation
     */
    public java.lang.String[] getRelation() {
        return relation;
    }


    /**
     * Sets the relation value for this QualifiedDublinCore.
     * 
     * @param relation
     */
    public void setRelation(java.lang.String[] relation) {
        this.relation = relation;
    }

    public java.lang.String getRelation(int i) {
        return this.relation[i];
    }

    public void setRelation(int i, java.lang.String _value) {
        this.relation[i] = _value;
    }


    /**
     * Gets the replaces value for this QualifiedDublinCore.
     * 
     * @return replaces
     */
    public java.lang.String[] getReplaces() {
        return replaces;
    }


    /**
     * Sets the replaces value for this QualifiedDublinCore.
     * 
     * @param replaces
     */
    public void setReplaces(java.lang.String[] replaces) {
        this.replaces = replaces;
    }

    public java.lang.String getReplaces(int i) {
        return this.replaces[i];
    }

    public void setReplaces(int i, java.lang.String _value) {
        this.replaces[i] = _value;
    }


    /**
     * Gets the requires value for this QualifiedDublinCore.
     * 
     * @return requires
     */
    public java.lang.String[] getRequires() {
        return requires;
    }


    /**
     * Sets the requires value for this QualifiedDublinCore.
     * 
     * @param requires
     */
    public void setRequires(java.lang.String[] requires) {
        this.requires = requires;
    }

    public java.lang.String getRequires(int i) {
        return this.requires[i];
    }

    public void setRequires(int i, java.lang.String _value) {
        this.requires[i] = _value;
    }


    /**
     * Gets the rights value for this QualifiedDublinCore.
     * 
     * @return rights
     */
    public java.lang.String[] getRights() {
        return rights;
    }


    /**
     * Sets the rights value for this QualifiedDublinCore.
     * 
     * @param rights
     */
    public void setRights(java.lang.String[] rights) {
        this.rights = rights;
    }

    public java.lang.String getRights(int i) {
        return this.rights[i];
    }

    public void setRights(int i, java.lang.String _value) {
        this.rights[i] = _value;
    }


    /**
     * Gets the spatial value for this QualifiedDublinCore.
     * 
     * @return spatial
     */
    public java.lang.String[] getSpatial() {
        return spatial;
    }


    /**
     * Sets the spatial value for this QualifiedDublinCore.
     * 
     * @param spatial
     */
    public void setSpatial(java.lang.String[] spatial) {
        this.spatial = spatial;
    }

    public java.lang.String getSpatial(int i) {
        return this.spatial[i];
    }

    public void setSpatial(int i, java.lang.String _value) {
        this.spatial[i] = _value;
    }


    /**
     * Gets the source value for this QualifiedDublinCore.
     * 
     * @return source
     */
    public java.lang.String[] getSource() {
        return source;
    }


    /**
     * Sets the source value for this QualifiedDublinCore.
     * 
     * @param source
     */
    public void setSource(java.lang.String[] source) {
        this.source = source;
    }

    public java.lang.String getSource(int i) {
        return this.source[i];
    }

    public void setSource(int i, java.lang.String _value) {
        this.source[i] = _value;
    }


    /**
     * Gets the subject value for this QualifiedDublinCore.
     * 
     * @return subject
     */
    public java.lang.String[] getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this QualifiedDublinCore.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String[] subject) {
        this.subject = subject;
    }

    public java.lang.String getSubject(int i) {
        return this.subject[i];
    }

    public void setSubject(int i, java.lang.String _value) {
        this.subject[i] = _value;
    }


    /**
     * Gets the subjectClassified value for this QualifiedDublinCore.
     * 
     * @return subjectClassified
     */
    public de.nrw.dipp.dippCore.webservice.SubjectClassified[] getSubjectClassified() {
        return subjectClassified;
    }


    /**
     * Sets the subjectClassified value for this QualifiedDublinCore.
     * 
     * @param subjectClassified
     */
    public void setSubjectClassified(de.nrw.dipp.dippCore.webservice.SubjectClassified[] subjectClassified) {
        this.subjectClassified = subjectClassified;
    }

    public de.nrw.dipp.dippCore.webservice.SubjectClassified getSubjectClassified(int i) {
        return this.subjectClassified[i];
    }

    public void setSubjectClassified(int i, de.nrw.dipp.dippCore.webservice.SubjectClassified _value) {
        this.subjectClassified[i] = _value;
    }


    /**
     * Gets the tableOfContents value for this QualifiedDublinCore.
     * 
     * @return tableOfContents
     */
    public de.nrw.dipp.dippCore.webservice.Element[] getTableOfContents() {
        return tableOfContents;
    }


    /**
     * Sets the tableOfContents value for this QualifiedDublinCore.
     * 
     * @param tableOfContents
     */
    public void setTableOfContents(de.nrw.dipp.dippCore.webservice.Element[] tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    public de.nrw.dipp.dippCore.webservice.Element getTableOfContents(int i) {
        return this.tableOfContents[i];
    }

    public void setTableOfContents(int i, de.nrw.dipp.dippCore.webservice.Element _value) {
        this.tableOfContents[i] = _value;
    }


    /**
     * Gets the temporal value for this QualifiedDublinCore.
     * 
     * @return temporal
     */
    public java.lang.String[] getTemporal() {
        return temporal;
    }


    /**
     * Sets the temporal value for this QualifiedDublinCore.
     * 
     * @param temporal
     */
    public void setTemporal(java.lang.String[] temporal) {
        this.temporal = temporal;
    }

    public java.lang.String getTemporal(int i) {
        return this.temporal[i];
    }

    public void setTemporal(int i, java.lang.String _value) {
        this.temporal[i] = _value;
    }


    /**
     * Gets the title value for this QualifiedDublinCore.
     * 
     * @return title
     */
    public de.nrw.dipp.dippCore.webservice.Element[] getTitle() {
        return title;
    }


    /**
     * Sets the title value for this QualifiedDublinCore.
     * 
     * @param title
     */
    public void setTitle(de.nrw.dipp.dippCore.webservice.Element[] title) {
        this.title = title;
    }

    public de.nrw.dipp.dippCore.webservice.Element getTitle(int i) {
        return this.title[i];
    }

    public void setTitle(int i, de.nrw.dipp.dippCore.webservice.Element _value) {
        this.title[i] = _value;
    }


    /**
     * Gets the type value for this QualifiedDublinCore.
     * 
     * @return type
     */
    public java.lang.String[] getType() {
        return type;
    }


    /**
     * Sets the type value for this QualifiedDublinCore.
     * 
     * @param type
     */
    public void setType(java.lang.String[] type) {
        this.type = type;
    }

    public java.lang.String getType(int i) {
        return this.type[i];
    }

    public void setType(int i, java.lang.String _value) {
        this.type[i] = _value;
    }


    /**
     * Gets the valid value for this QualifiedDublinCore.
     * 
     * @return valid
     */
    public java.util.Calendar getValid() {
        return valid;
    }


    /**
     * Sets the valid value for this QualifiedDublinCore.
     * 
     * @param valid
     */
    public void setValid(java.util.Calendar valid) {
        this.valid = valid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QualifiedDublinCore)) return false;
        QualifiedDublinCore other = (QualifiedDublinCore) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCTermsAbstract==null && other.getDCTermsAbstract()==null) || 
             (this.DCTermsAbstract!=null &&
              java.util.Arrays.equals(this.DCTermsAbstract, other.getDCTermsAbstract()))) &&
            ((this.DDC==null && other.getDDC()==null) || 
             (this.DDC!=null &&
              java.util.Arrays.equals(this.DDC, other.getDDC()))) &&
            ((this.accessRights==null && other.getAccessRights()==null) || 
             (this.accessRights!=null &&
              java.util.Arrays.equals(this.accessRights, other.getAccessRights()))) &&
            ((this.alternative==null && other.getAlternative()==null) || 
             (this.alternative!=null &&
              java.util.Arrays.equals(this.alternative, other.getAlternative()))) &&
            ((this.articleType==null && other.getArticleType()==null) || 
             (this.articleType!=null &&
              java.util.Arrays.equals(this.articleType, other.getArticleType()))) &&
            ((this.audience==null && other.getAudience()==null) || 
             (this.audience!=null &&
              java.util.Arrays.equals(this.audience, other.getAudience()))) &&
            ((this.available==null && other.getAvailable()==null) || 
             (this.available!=null &&
              this.available.equals(other.getAvailable()))) &&
            ((this.bibliographicCitation==null && other.getBibliographicCitation()==null) || 
             (this.bibliographicCitation!=null &&
              java.util.Arrays.equals(this.bibliographicCitation, other.getBibliographicCitation()))) &&
            ((this.conformsTo==null && other.getConformsTo()==null) || 
             (this.conformsTo!=null &&
              java.util.Arrays.equals(this.conformsTo, other.getConformsTo()))) &&
            ((this.contributor==null && other.getContributor()==null) || 
             (this.contributor!=null &&
              java.util.Arrays.equals(this.contributor, other.getContributor()))) &&
            ((this.coverage==null && other.getCoverage()==null) || 
             (this.coverage!=null &&
              java.util.Arrays.equals(this.coverage, other.getCoverage()))) &&
            ((this.created==null && other.getCreated()==null) || 
             (this.created!=null &&
              this.created.equals(other.getCreated()))) &&
            ((this.creatorCorporated==null && other.getCreatorCorporated()==null) || 
             (this.creatorCorporated!=null &&
              java.util.Arrays.equals(this.creatorCorporated, other.getCreatorCorporated()))) &&
            ((this.creatorPerson==null && other.getCreatorPerson()==null) || 
             (this.creatorPerson!=null &&
              java.util.Arrays.equals(this.creatorPerson, other.getCreatorPerson()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              java.util.Arrays.equals(this.date, other.getDate()))) &&
            ((this.dateAccepted==null && other.getDateAccepted()==null) || 
             (this.dateAccepted!=null &&
              this.dateAccepted.equals(other.getDateAccepted()))) &&
            ((this.dateCopyrighted==null && other.getDateCopyrighted()==null) || 
             (this.dateCopyrighted!=null &&
              this.dateCopyrighted.equals(other.getDateCopyrighted()))) &&
            ((this.dateSubmitted==null && other.getDateSubmitted()==null) || 
             (this.dateSubmitted!=null &&
              this.dateSubmitted.equals(other.getDateSubmitted()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              java.util.Arrays.equals(this.description, other.getDescription()))) &&
            ((this.docType==null && other.getDocType()==null) || 
             (this.docType!=null &&
              java.util.Arrays.equals(this.docType, other.getDocType()))) &&
            ((this.educationLevel==null && other.getEducationLevel()==null) || 
             (this.educationLevel!=null &&
              java.util.Arrays.equals(this.educationLevel, other.getEducationLevel()))) &&
            ((this.extent==null && other.getExtent()==null) || 
             (this.extent!=null &&
              java.util.Arrays.equals(this.extent, other.getExtent()))) &&
            ((this.format==null && other.getFormat()==null) || 
             (this.format!=null &&
              java.util.Arrays.equals(this.format, other.getFormat()))) &&
            ((this.hasFormat==null && other.getHasFormat()==null) || 
             (this.hasFormat!=null &&
              java.util.Arrays.equals(this.hasFormat, other.getHasFormat()))) &&
            ((this.hasPart==null && other.getHasPart()==null) || 
             (this.hasPart!=null &&
              java.util.Arrays.equals(this.hasPart, other.getHasPart()))) &&
            ((this.hasVersionOf==null && other.getHasVersionOf()==null) || 
             (this.hasVersionOf!=null &&
              java.util.Arrays.equals(this.hasVersionOf, other.getHasVersionOf()))) &&
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              java.util.Arrays.equals(this.identifier, other.getIdentifier()))) &&
            ((this.identifierDOI==null && other.getIdentifierDOI()==null) || 
             (this.identifierDOI!=null &&
              this.identifierDOI.equals(other.getIdentifierDOI()))) &&
            ((this.identifierISBN==null && other.getIdentifierISBN()==null) || 
             (this.identifierISBN!=null &&
              this.identifierISBN.equals(other.getIdentifierISBN()))) &&
            ((this.identifierISSN==null && other.getIdentifierISSN()==null) || 
             (this.identifierISSN!=null &&
              this.identifierISSN.equals(other.getIdentifierISSN()))) &&
            ((this.identifierURL==null && other.getIdentifierURL()==null) || 
             (this.identifierURL!=null &&
              this.identifierURL.equals(other.getIdentifierURL()))) &&
            ((this.identifierURN==null && other.getIdentifierURN()==null) || 
             (this.identifierURN!=null &&
              this.identifierURN.equals(other.getIdentifierURN()))) &&
            ((this.isFormatOf==null && other.getIsFormatOf()==null) || 
             (this.isFormatOf!=null &&
              java.util.Arrays.equals(this.isFormatOf, other.getIsFormatOf()))) &&
            ((this.isPartOf==null && other.getIsPartOf()==null) || 
             (this.isPartOf!=null &&
              java.util.Arrays.equals(this.isPartOf, other.getIsPartOf()))) &&
            ((this.isReferencedBy==null && other.getIsReferencedBy()==null) || 
             (this.isReferencedBy!=null &&
              java.util.Arrays.equals(this.isReferencedBy, other.getIsReferencedBy()))) &&
            ((this.isReplacedBy==null && other.getIsReplacedBy()==null) || 
             (this.isReplacedBy!=null &&
              java.util.Arrays.equals(this.isReplacedBy, other.getIsReplacedBy()))) &&
            ((this.isRequiredBy==null && other.getIsRequiredBy()==null) || 
             (this.isRequiredBy!=null &&
              java.util.Arrays.equals(this.isRequiredBy, other.getIsRequiredBy()))) &&
            ((this.isVersionOf==null && other.getIsVersionOf()==null) || 
             (this.isVersionOf!=null &&
              java.util.Arrays.equals(this.isVersionOf, other.getIsVersionOf()))) &&
            ((this.issued==null && other.getIssued()==null) || 
             (this.issued!=null &&
              this.issued.equals(other.getIssued()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              java.util.Arrays.equals(this.language, other.getLanguage()))) &&
            ((this.mediator==null && other.getMediator()==null) || 
             (this.mediator!=null &&
              java.util.Arrays.equals(this.mediator, other.getMediator()))) &&
            ((this.medium==null && other.getMedium()==null) || 
             (this.medium!=null &&
              java.util.Arrays.equals(this.medium, other.getMedium()))) &&
            ((this.modified==null && other.getModified()==null) || 
             (this.modified!=null &&
              this.modified.equals(other.getModified()))) &&
            ((this.pubType==null && other.getPubType()==null) || 
             (this.pubType!=null &&
              java.util.Arrays.equals(this.pubType, other.getPubType()))) &&
            ((this.publisher==null && other.getPublisher()==null) || 
             (this.publisher!=null &&
              java.util.Arrays.equals(this.publisher, other.getPublisher()))) &&
            ((this.references==null && other.getReferences()==null) || 
             (this.references!=null &&
              java.util.Arrays.equals(this.references, other.getReferences()))) &&
            ((this.relation==null && other.getRelation()==null) || 
             (this.relation!=null &&
              java.util.Arrays.equals(this.relation, other.getRelation()))) &&
            ((this.replaces==null && other.getReplaces()==null) || 
             (this.replaces!=null &&
              java.util.Arrays.equals(this.replaces, other.getReplaces()))) &&
            ((this.requires==null && other.getRequires()==null) || 
             (this.requires!=null &&
              java.util.Arrays.equals(this.requires, other.getRequires()))) &&
            ((this.rights==null && other.getRights()==null) || 
             (this.rights!=null &&
              java.util.Arrays.equals(this.rights, other.getRights()))) &&
            ((this.spatial==null && other.getSpatial()==null) || 
             (this.spatial!=null &&
              java.util.Arrays.equals(this.spatial, other.getSpatial()))) &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              java.util.Arrays.equals(this.source, other.getSource()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              java.util.Arrays.equals(this.subject, other.getSubject()))) &&
            ((this.subjectClassified==null && other.getSubjectClassified()==null) || 
             (this.subjectClassified!=null &&
              java.util.Arrays.equals(this.subjectClassified, other.getSubjectClassified()))) &&
            ((this.tableOfContents==null && other.getTableOfContents()==null) || 
             (this.tableOfContents!=null &&
              java.util.Arrays.equals(this.tableOfContents, other.getTableOfContents()))) &&
            ((this.temporal==null && other.getTemporal()==null) || 
             (this.temporal!=null &&
              java.util.Arrays.equals(this.temporal, other.getTemporal()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              java.util.Arrays.equals(this.title, other.getTitle()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              java.util.Arrays.equals(this.type, other.getType()))) &&
            ((this.valid==null && other.getValid()==null) || 
             (this.valid!=null &&
              this.valid.equals(other.getValid())));
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
        if (getDCTermsAbstract() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDCTermsAbstract());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDCTermsAbstract(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDDC() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDDC());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDDC(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccessRights() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccessRights());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccessRights(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAlternative() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAlternative());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAlternative(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArticleType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArticleType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArticleType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAudience() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAudience());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAudience(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAvailable() != null) {
            _hashCode += getAvailable().hashCode();
        }
        if (getBibliographicCitation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBibliographicCitation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBibliographicCitation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getConformsTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConformsTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConformsTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContributor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContributor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContributor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCoverage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCoverage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCoverage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreated() != null) {
            _hashCode += getCreated().hashCode();
        }
        if (getCreatorCorporated() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCreatorCorporated());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCreatorCorporated(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreatorPerson() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCreatorPerson());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCreatorPerson(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDate() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDate());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDate(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateAccepted() != null) {
            _hashCode += getDateAccepted().hashCode();
        }
        if (getDateCopyrighted() != null) {
            _hashCode += getDateCopyrighted().hashCode();
        }
        if (getDateSubmitted() != null) {
            _hashCode += getDateSubmitted().hashCode();
        }
        if (getDescription() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescription());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescription(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEducationLevel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEducationLevel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEducationLevel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFormat() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormat());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFormat(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHasFormat() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHasFormat());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHasFormat(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHasPart() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHasPart());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHasPart(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHasVersionOf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHasVersionOf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHasVersionOf(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdentifier() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentifier());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentifier(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdentifierDOI() != null) {
            _hashCode += getIdentifierDOI().hashCode();
        }
        if (getIdentifierISBN() != null) {
            _hashCode += getIdentifierISBN().hashCode();
        }
        if (getIdentifierISSN() != null) {
            _hashCode += getIdentifierISSN().hashCode();
        }
        if (getIdentifierURL() != null) {
            _hashCode += getIdentifierURL().hashCode();
        }
        if (getIdentifierURN() != null) {
            _hashCode += getIdentifierURN().hashCode();
        }
        if (getIsFormatOf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsFormatOf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsFormatOf(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsPartOf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsPartOf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsPartOf(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsReferencedBy() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsReferencedBy());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsReferencedBy(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsReplacedBy() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsReplacedBy());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsReplacedBy(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsRequiredBy() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsRequiredBy());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsRequiredBy(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsVersionOf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIsVersionOf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIsVersionOf(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIssued() != null) {
            _hashCode += getIssued().hashCode();
        }
        if (getLanguage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLanguage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLanguage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMediator() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMediator());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMediator(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMedium() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMedium());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMedium(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getModified() != null) {
            _hashCode += getModified().hashCode();
        }
        if (getPubType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPubType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPubType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPublisher() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPublisher());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPublisher(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReferences() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReferences());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReferences(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRelation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRelation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReplaces() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReplaces());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReplaces(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRequires() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequires());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequires(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRights() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRights());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRights(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSpatial() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSpatial());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSpatial(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSource() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSource());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSource(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubject() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubject());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubject(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubjectClassified() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubjectClassified());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubjectClassified(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTableOfContents() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTableOfContents());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTableOfContents(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTemporal() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTemporal());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTemporal(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTitle() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTitle());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTitle(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValid() != null) {
            _hashCode += getValid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QualifiedDublinCore.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "QualifiedDublinCore"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCTermsAbstract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DCTermsAbstract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Element"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DDC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DDC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessRights");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accessRights"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alternative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alternative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Element"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("articleType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "articleType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audience");
        elemField.setXmlName(new javax.xml.namespace.QName("", "audience"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("available");
        elemField.setXmlName(new javax.xml.namespace.QName("", "available"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bibliographicCitation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bibliographicCitation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Citation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conformsTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conformsTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contributor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contributor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Contributor"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coverage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coverage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created");
        elemField.setXmlName(new javax.xml.namespace.QName("", "created"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creatorCorporated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creatorCorporated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "CreatorCorporated"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creatorPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creatorPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "CreatorPerson"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateAccepted");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateAccepted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateCopyrighted");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateCopyrighted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateSubmitted");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateSubmitted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("educationLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "educationLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("format");
        elemField.setXmlName(new javax.xml.namespace.QName("", "format"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasPart");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasPart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Part"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasVersionOf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasVersionOf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierDOI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifierDOI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierISBN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifierISBN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierISSN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifierISSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifierURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identifierURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFormatOf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isFormatOf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPartOf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isPartOf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Part"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReferencedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isReferencedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReplacedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isReplacedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRequiredBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isRequiredBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isVersionOf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isVersionOf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Part"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issued");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issued"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mediator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medium");
        elemField.setXmlName(new javax.xml.namespace.QName("", "medium"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisher");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisher"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("references");
        elemField.setXmlName(new javax.xml.namespace.QName("", "references"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replaces");
        elemField.setXmlName(new javax.xml.namespace.QName("", "replaces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requires");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requires"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rights");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rights"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spatial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spatial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subjectClassified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subjectClassified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "SubjectClassified"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableOfContents");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tableOfContents"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Element"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.dippCore.dipp.nrw.de", "Element"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
