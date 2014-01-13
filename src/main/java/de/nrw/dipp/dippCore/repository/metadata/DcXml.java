/**
 * DcXml.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.nrw.dipp.dippCore.repository.metadata;

import gov.loc.mods.v3.ClassificationDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openarchives.oai.x20.oaiDc.BK;
import org.openarchives.oai.x20.oaiDc.DDC;
import org.openarchives.oai.x20.oaiDc.DcDocument;
import org.openarchives.oai.x20.oaiDc.DocType;
import org.openarchives.oai.x20.oaiDc.LCC;
import org.openarchives.oai.x20.oaiDc.LCSH;
import org.openarchives.oai.x20.oaiDc.MESH;
import org.openarchives.oai.x20.oaiDc.OaiDcType;
import org.openarchives.oai.x20.oaiDc.PACS;
import org.openarchives.oai.x20.oaiDc.PubType;
import org.openarchives.oai.x20.oaiDc.RVK;
import org.openarchives.oai.x20.oaiDc.SWD;
import org.openarchives.oai.x20.oaiDc.UDC;
import org.purl.dc.elements.x11.Keyword;
import org.purl.dc.elements.x11.VCard;

import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreSimplified;

/**
 * Class DcXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 29.09.2011
 *
 */
public class DcXml extends InternalXmlService {

	// Initiate Logger for DcXml
	private static Logger log = Logger.getLogger(DcXml.class);

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {
        StringBuffer tempBuffer = new StringBuffer();
		DublinCoreSimplified dc = new DublinCoreSimplified();
        
		// set Titles
		for (int i=0; i<artQdc.sizeOfTitleArray(); i++) {
        	if (artQdc.getTitleArray(i).getStringValue().length() > 0){
                dc.addTitle(artQdc.getTitleArray(i).getStringValue());
        	}
        }
        for (int i=0; i<artQdc.sizeOfAlternativeArray(); i++) {
        	if (artQdc.getAlternativeArray(i).getStringValue().length() > 0){
                dc.addTitle(artQdc.getAlternativeArray(i).getStringValue());
        	}
        }
        
        // set Creators
        for (int i=0; i < artQdc.sizeOfCreatorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getCreatorArray(i);
        	if (vcard.getLastName().trim().length() > 0){
                tempBuffer.append(vcard.getLastName());
                if (vcard.getFirstName() != null){
                	tempBuffer.append(", " + vcard.getFirstName());
                }
                dc.addCreator(tempBuffer.toString());
                tempBuffer.setLength(0);
        	}else if (vcard.getOrganization() != null){
    			if (vcard.getOrganization().trim().length() > 0){
                    dc.addCreator(vcard.getOrganization().trim());
            	}        		
        	}
        }

        // set subjects
        for (int i=0; i < artQdc.sizeOfSubjectArray(); i++) {
        	Keyword kw = (Keyword)artQdc.getSubjectArray(i);
       
        	for (int j=0; j < kw.sizeOfDdcArray(); j++) {
        		if (kw.getDdcArray(j).trim().length() > 0){
        			
        			dc.addSubject(kw.getDdcArray(j));
        			dc.addSubject("http://dewey.info/class/" + kw.getDdcArray(j) + "/");
        		}
        	}

        	for (int j=0; j < kw.sizeOfUnclassifiedArray(); j++) {
            	if (kw.getUnclassifiedArray(j).trim().length() > 0){
                    dc.addSubject(((String) kw.getUnclassifiedArray(j)));
            	}
            }
            for (int j=0; j < kw.sizeOfClassifiedArray(); j++) {
            	if (kw.getClassifiedArray(j).trim().length() > 0){
            		
                    if (kw.getClassificationArray(j).schemaType().getName().equals(DDC.type.getName())){
                        dc.addSubject(( getClassificationSystem(kw.getClassificationArray(j).schemaType()) + ": " + kw.getClassificationArray(j).getStringValue()));                    	                    	
                    }else{
                        dc.addSubject(( getClassificationSystem(kw.getClassificationArray(j).schemaType()) + ": " + kw.getClassifiedArray(j)));
                    }
            	}
            }
        }
        
        // set descriptions
        for (int i=0; i < artQdc.sizeOfDescriptionArray(); i++) {
        	if (artQdc.getDescriptionArray(i).getStringValue().length() > 0){
                dc.addDecsription(( artQdc.getDescriptionArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfAbstractArray(); i++) {
        	if (artQdc.getAbstractArray(i).getStringValue().length() > 0){
                dc.addDecsription((artQdc.getAbstractArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfTableOfContentsArray(); i++) {
        	if (artQdc.getTableOfContentsArray(i).getStringValue().length() > 0){        		
                dc.addDecsription((artQdc.getTableOfContentsArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfPublisherArray(); i++) {
        	if (artQdc.getPublisherArray(i).getStringValue().length() > 0){
                dc.addPublisher((artQdc.getPublisherArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.getContributorArray().length; i++) {
        	VCard vcard = (VCard)artQdc.getContributorArray(i);
        	if (vcard.getLastName().trim().length() > 0){
                tempBuffer.append(vcard.getLastName());
                if (vcard.getFirstName() != null){
                	tempBuffer.append(", " + vcard.getFirstName());
                }
                dc.addContributor((tempBuffer.toString()));
                tempBuffer.setLength(0);
        	}
        }
        for (int i=0; i < artQdc.sizeOfCreatedArray(); i++) {
        	if (artQdc.getCreatedArray(i).getStringValue().length() > 0){
                dc.addDate((artQdc.getCreatedArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfBibliographicCitationArray(); i++) {
        	org.purl.dc.terms.Citation citation = artQdc.getBibliographicCitationArray(i);
            if (citation.getJournalIssueDate() != null){
            	dc.addDate(((String) citation.getJournalIssueDate())); 
            }
        }
        
        for (int i=0; i < artQdc.getTypeArray().length; i++) {
        	if (!artQdc.getTypeArray(i).schemaType().getName().toString().equals(DocType.type.getName().toString()) &&
        		!artQdc.getTypeArray(i).schemaType().getName().toString().equals(PubType.type.getName().toString())
        	){
                dc.addType(((String) artQdc.getTypeArray(i).getStringValue()));
        	}
        }
        
        for (int i=0; i < artQdc.sizeOfFormatArray(); i++) {
        	if (artQdc.getFormatArray(i).getStringValue().length() > 0){
                dc.addFormat((artQdc.getFormatArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfIdentifierArray(); i++) {
        	if (artQdc.getIdentifierArray(i).getStringValue().length() > 0){
                dc.addIdentifier((artQdc.getIdentifierArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfSourceArray(); i++) {
        	if (artQdc.getSourceArray(i).getStringValue().length() > 0){
                dc.addSource((artQdc.getSourceArray(i).getStringValue()));
        	}
        }
        for (int i=0; i < artQdc.sizeOfBibliographicCitationArray(); i++) {
        	org.purl.dc.terms.Citation citation = artQdc.getBibliographicCitationArray(i);
            if (citation.getJournalTitle() != null){
            	if (citation.getJournalTitle().trim().length() > 0){
            		tempBuffer.append(((String) artQdc.getBibliographicCitationArray(i).getJournalTitle() + " ; "));
            	}
            }
            if (citation.getJournalVolume() != null){
            	if (citation.getJournalVolume().trim().length() > 0){
                	tempBuffer.append(((String) artQdc.getBibliographicCitationArray(i).getJournalVolume() + " , "));
            	}
            }
            if (citation.getJournalIssueNumber() != null){
            	if (citation.getJournalIssueNumber().trim().length() > 0){
            		tempBuffer.append(((String) artQdc.getBibliographicCitationArray(i).getJournalIssueNumber()));
            	}
            }
            dc.addSource(tempBuffer.toString());
            tempBuffer.setLength(0);
        }
        for (int i=0; i < artQdc.sizeOfLanguageArray(); i++) {
        	if (artQdc.getLanguageArray(i).getStringValue().length() > 0){
                dc.addLanguage(artQdc.getLanguageArray(i).getStringValue());
        	}
        }
        for (int i=0; i < artQdc.sizeOfRelationArray(); i++) {
        	if (artQdc.getRelationArray(i).getStringValue().length() > 0){
        		dc.addRelation((artQdc.getRelationArray(i).getStringValue()));
        	}
        }
        
        for (int i=0; i < artQdc.sizeOfCoverageArray(); i++) {
        	if (artQdc.getCoverageArray(i).getStringValue().length() > 0){
        		dc.addCoverage(artQdc.getCoverageArray(i).getStringValue());
        	}
        }
        for (int i=0; i < artQdc.sizeOfRightsArray(); i++) {
        	if (artQdc.getRightsArray(i).getStringValue().length() > 0){
        		dc.addRights(artQdc.getRightsArray(i).getStringValue());
        	}
        }
        
        XmlObject dcXML = dc.getSimplifiedDublinCore();
        
        
        XmlCursor cursor = dcXML.newCursor();
		String location = "http://www.openarchives.org/OAI/2.0/oai_dc/\n" +
				"          http://www.openarchives.org/OAI/2.0/oai_dc.xsd";
		if (cursor.toFirstChild())
		{
		  //cursor.insertNamespace("oai_dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
		  cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation"), location);
		  //cursor.insertNamespace("dc", "http://purl.org/dc/elements/1.1/");
		}
		 
        
        XmlOptions xmlOptions = new XmlOptions();
        Map nsMap = new HashMap();
        nsMap.put("http://purl.org/dc/elements/1.1/", "dc");
        nsMap.put("http://www.openarchives.org/OAI/2.0/oai_dc/", "oai_dc");
        xmlOptions.setSaveSuggestedPrefixes(nsMap);
        xmlOptions.setSaveAggresiveNamespaces();
        //return  
        String test = dcXML.xmlText(xmlOptions);
        
        //HACK: workaround for namespace probs
        try {
			xmlObj = XmlObject.Factory.parse(test);
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//xmlObj = dcXML;

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#setOptions(java.util.Properties)
	 */
	@Override
	public void setOptions(Properties Options) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#getOptions()
	 */
	@Override
	public Properties getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getClassificationSystem(SchemaType aType){
		String classSystem = "";
//		if (cClassificatonSystems.containsValue(aType)){
			if (aType.getName().equals(SWD.type.getName())){
				classSystem = "swd";
			}else if (aType.getName().equals(DDC.type.getName())){
				classSystem = "ddc";
			}else if (aType.getName().equals(LCSH.type.getName())){
				classSystem = "lcsh";
			}else if (aType.getName().equals(MESH.type.getName())){
				classSystem = "mesh";
			}else if (aType.getName().equals(LCC.type.getName())){
				classSystem = "lcc";
			}else if (aType.getName().equals(UDC.type.getName())){
				classSystem = "udc";
			}else if (aType.getName().equals(RVK.type.getName())){
				classSystem = "rvk";
			}else if (aType.getName().equals(BK.type.getName())){
				classSystem = "bk";
			}else if (aType.getName().equals(PACS.type.getName())){
				classSystem = "pacs";
			}
//		}
		return classSystem;
	}

}
