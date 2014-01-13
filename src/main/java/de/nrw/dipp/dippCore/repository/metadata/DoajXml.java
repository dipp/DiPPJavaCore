/**
 * DoajXml.java - This file is part of the DiPP Project by hbz
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

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import gov.ojp.it.jxdm.iso6392B.x10.LanguageCodeType;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.doaj.element.RecordType;
import org.doaj.element.RecordsDocument;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;
import org.openarchives.oai.x20.oaiDc.URL;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.elements.x11.VCard;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RIsearch;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class DoajXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 19.09.2011
 *
 */
public class DoajXml extends InternalXmlService {

	protected DoajXml(){
		//createXmlPresentation();
	}
	
	//private static String FORMAT_LABEL = "oai_doaj";
	//private static String MESSAGE_ADD_STREAM = "added new " + FORMAT_LABEL + " stream";
	//private static String MESSAGE_MOD_STREAM = "modified " + FORMAT_LABEL + " stream";
	
	private RecordsDocument.Records mRec = RecordsDocument.Records.Factory.newInstance();
	private RecordType mRecContent = mRec.addNewRecord();
	private RecordType.Authors authorArray = mRecContent.addNewAuthors();

	// Initiate Logger for DoajXml
	private static Logger log = Logger.getLogger(DoajXml.class);


	@Override
	public void setOptions(Properties Options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Properties getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createXmlPresentation() {
		// TODO Auto-generated method stub
		//generate Document Language
		StringBuffer sb = new StringBuffer();
				
		// get required metadata from the Journals qdc
		//qdc = Metadata.addParentJournalQdc(qdc, digObj.getLabel());
		String jPid = getJournalPidByRI(artPid);
		MetadataDocument jQdcDoc = null;
		try{
			byte[] jStream = (new DOManagement()).getDatastream(jPid, "QDC");
			jQdcDoc = MetadataDocument.Factory.parse(new String(jStream, "UTF-8"));
		}
		catch(Exception e){
			log.error(e);
		}

		MetadataDocument.Metadata jQdc = jQdcDoc.getMetadata();

		for(int i=0; i<jQdc.sizeOfIdentifierArray(); i++){
			if ((jQdc.getIdentifierArray(i).schemaType().getName().toString()).equals(ISSN.type.getName().toString())){
				SimpleLiteral lit = jQdc.getIdentifierArray(i);
				artQdc.addNewIdentifier();
				artQdc.setIdentifierArray(artQdc.sizeOfIdentifierArray()-1 , lit);
			}
		}			

		
		
		for (int i = 0; i<artQdc.sizeOfLanguageArray(); i++){
			String recLang = artQdc.getLanguageArray(i).getStringValue();
			LanguageCodeType.Enum langCode = LanguageCodeType.Enum.forString(recLang);
			if(langCode != null){
				mRecContent.setLanguage(langCode);
			}
			
			
		}
		
		// generate Publisher informations
		mRecContent.setPublisher("Digital Peer Publishing (DiPP)");
		
		// generate Journal informations
		for(int i = 0; i < artQdc.sizeOfBibliographicCitationArray(); i++){
			mRecContent.setJournalTitle(artQdc.getBibliographicCitationArray(i).getJournalTitle());
			mRecContent.setVolume(artQdc.getBibliographicCitationArray(i).getJournalVolume());
			mRecContent.setIssue(artQdc.getBibliographicCitationArray(i).getJournalIssueNumber());

			// generate Publication Date
			SimpleDateFormat issueDate = new SimpleDateFormat("yyyy-MM-dd");
			issueDate.setLenient(false);
			try {
				issueDate.parse(artQdc.getBibliographicCitationArray(i).getJournalIssueDate());
				mRecContent.setPublicationDate(artQdc.getBibliographicCitationArray(i).getJournalIssueDate());
			}
			catch(Exception e){
				log.info("Cannot use PublicationDate due to wrong Format, use Creation Date");
			}

		}
		if(mRecContent.getPublicationDate() == null){
			for(int i = 0; i < artQdc.sizeOfCreatedArray(); i++){
				mRecContent.setPublicationDate(artQdc.getCreatedArray(i).getStringValue());
			}			
		}
		
		// generate Title informations
		for(int i=0; i < artQdc.sizeOfTitleArray(); i++){
			RecordType.Title titleElement = mRecContent.addNewTitle();

			String mTitle = artQdc.getTitleArray(i).getStringValue();
			String lang = artQdc.getTitleArray(i).getLang();

			titleElement.setStringValue(mTitle);
			LanguageCodeType.Enum langCode = LanguageCodeType.Enum.forString(lang);		
			if(langCode != null){
				titleElement.setLanguage(LanguageCodeType.Enum.forString(lang));
				titleElement.setStringValue(mTitle);				
			}

		}

		// generate Authors informations
		// use Vcard-Class for creation of Authors informations 
		for (int i=0; i < artQdc.sizeOfCreatorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getCreatorArray(i);

        	if (vcard.getLastName().trim().length() > 0){
                RecordType.Authors.Author mAuthor = authorArray.addNewAuthor();

                //get Authors full name
                sb.append(vcard.getLastName());
                if (vcard.getFirstName() != null){
                	sb.append(", " + vcard.getFirstName());
                }
                mAuthor.setName(sb.toString()); 
                sb.setLength(0);

                //get Authors Email
                if(vcard.getEmail() != null){
                    if(vcard.getEmail().trim().length() > 0){
                		mAuthor.setEmail(vcard.getEmail());
                	}
                }
        	}
        	else if (vcard.getOrganization() != null){
    			if (vcard.getOrganization().trim().length() > 0){
                    //dc.addCreator(vcard.getOrganization().trim());
            	}        		
        	}
		}

		// generate Abstract informations
		for(int i=0; i < artQdc.sizeOfAbstractArray(); i++){
			RecordType.Abstract abstractElement = mRecContent.addNewAbstract();

			String mAbstract = artQdc.getAbstractArray(i).getStringValue();
			String lang = artQdc.getAbstractArray(i).getLang();
			
			abstractElement.setStringValue(mAbstract);
			LanguageCodeType.Enum langCode = LanguageCodeType.Enum.forString(lang);
			if(langCode != null){
				abstractElement.setLanguage(LanguageCodeType.Enum.forString(lang));
			}
			
			
		}

		// generate FullTextUrl information and add doi information if given
		for(int i=0; i < artQdc.sizeOfIdentifierArray(); i++){
			if((artQdc.getIdentifierArray(i).schemaType().equals(URL.type))){
				RecordType.FullTextUrl urlElement = mRecContent.addNewFullTextUrl();
				urlElement.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
			}
			if((artQdc.getIdentifierArray(i).schemaType().equals(DOI.type))){
				if((artQdc.getIdentifierArray(i).getStringValue().trim().length() > 0)){
					mRecContent.setDoi(artQdc.getIdentifierArray(i).getStringValue());
				}
			}
			if((artQdc.getIdentifierArray(i).schemaType().equals(ISSN.type))){
				if((artQdc.getIdentifierArray(i).getStringValue().trim().length() > 0)){
					mRecContent.setIssn(artQdc.getIdentifierArray(i).getStringValue().replace("ISSN:", ""));
				}
			}
		}
		xmlObj = mRec;
	}

}
