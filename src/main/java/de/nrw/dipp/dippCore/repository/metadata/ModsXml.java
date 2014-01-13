/**
 * ModsXml.java - This file is part of the DiPP Project by hbz
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

import java.util.Properties;


import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.openarchives.oai.x20.oaiDc.BK;
import org.openarchives.oai.x20.oaiDc.DDC;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.LCC;
import org.openarchives.oai.x20.oaiDc.LCSH;
import org.openarchives.oai.x20.oaiDc.MESH;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;
import org.openarchives.oai.x20.oaiDc.PACS;
import org.openarchives.oai.x20.oaiDc.RVK;
import org.openarchives.oai.x20.oaiDc.SWD;
import org.openarchives.oai.x20.oaiDc.UDC;
import org.openarchives.oai.x20.oaiDc.URL;
import org.openarchives.oai.x20.oaiDc.URN;
import org.purl.dc.elements.x11.Keyword;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.elements.x11.VCard;

import de.nrw.dipp.dippCore.repository.DOManagement;


//import gov.loc.mods.v3.ModsDefinition;
import gov.loc.mods.v3.*;
import gov.ojp.it.jxdm.iso6392B.x10.LanguageCodeType;

/**
 * Class ModsXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 23.01.2012
 *
 */
public class ModsXml extends InternalXmlService {

	// Initiate Logger for ModsXml
	private static Logger log = Logger.getLogger(ModsXml.class);
	private ModsDocument mDoc = null; //ModsDocument.Factory.newInstance();

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {
		// TODO Auto-generated method stub

	    mDoc = ModsDocument.Factory.newInstance();
	    ModsDefinition mDef = mDoc.addNewMods();
		mDef.setVersion(ModsVersionAttributeDefinition.X_3_4);
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

		// generate Title informations
		TitleInfoDefinition titleInfo = mDef.addNewTitleInfo();

		for(int i=0; i < artQdc.sizeOfTitleArray(); i++){
			
			String mTitle = artQdc.getTitleArray(i).getStringValue();

			XsString title = titleInfo.addNewTitle();
			title.setStringValue(mTitle);
			title.setLang((artQdc.getTitleArray(i).getLang()));
			}

		// generate Authors informations
		// use Vcard-Class for creation of Authors informations 
		for (int i=0; i < artQdc.sizeOfCreatorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getCreatorArray(i);

        	if (vcard.getLastName().trim().length() > 0){
                NameDefinition mName = mDef.addNewName();
                NameTypeAttributeDefinition.Enum.forString("personal");
                NameDefinition.type.enumEntryForString("personal");
                
                mName.setType(NameTypeAttributeDefinition.Enum.forString("personal"));
                //set family name
                NamePartDefinition lastNamePart = mName.addNewNamePart();
                lastNamePart.setType(NamePartTypeAttributeDefinition.FAMILY);
                lastNamePart.setStringValue(vcard.getLastName());
                //set given name
                NamePartDefinition firstNamePart = mName.addNewNamePart();
                firstNamePart.setType(NamePartTypeAttributeDefinition.GIVEN);
                firstNamePart.setStringValue(vcard.getFirstName());
                //set role author
                RoleDefinition rDef = mName.addNewRole();
                RoleTermDefinition rTerm = rDef.addNewRoleTerm();
                rTerm.setType(CodeOrTextDefinition.TEXT);
                rTerm.setStringValue("author");

                if (vcard.getOrganization() != null){
        			if (vcard.getOrganization().trim().length() > 0){
        				XsString mAf = mName.addNewAffiliation();
        				mAf.setStringValue(vcard.getOrganization().trim());
        				}
        			}

                //get Authors Email
                if(vcard.getEmail() != null){
                    if(vcard.getEmail().trim().length() > 0){
                    	//TODO: implement if possible
                    	}
                	}

       			}
            
        	}
        	
		// generate Contributors informations
		// use Vcard-Class for creation of Authors informations 
		for (int i=0; i < artQdc.sizeOfContributorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getContributorArray(i);

        	if (vcard.getLastName().trim().length() > 0){
                NameDefinition mName = mDef.addNewName();
                NameTypeAttributeDefinition.Enum.forString("personal");
                NameDefinition.type.enumEntryForString("personal");
                
                mName.setType(NameTypeAttributeDefinition.Enum.forString("personal"));
                //set family name
                NamePartDefinition lastNamePart = mName.addNewNamePart();
                lastNamePart.setType(NamePartTypeAttributeDefinition.FAMILY);
                lastNamePart.setStringValue(vcard.getLastName());
                //set given name
                NamePartDefinition firstNamePart = mName.addNewNamePart();
                firstNamePart.setType(NamePartTypeAttributeDefinition.GIVEN);
                firstNamePart.setStringValue(vcard.getFirstName());
                //set role author
                RoleDefinition rDef = mName.addNewRole();
                RoleTermDefinition rTerm = rDef.addNewRoleTerm();
                rTerm.setType(CodeOrTextDefinition.TEXT);
                rTerm.setStringValue("contributor");

                if (vcard.getOrganization() != null){
        			if (vcard.getOrganization().trim().length() > 0){
        				XsString mAf = mName.addNewAffiliation();
        				mAf.setStringValue(vcard.getOrganization().trim());
        				}
        			}

                //get Authors Email
                if(vcard.getEmail() != null){
                    if(vcard.getEmail().trim().length() > 0){
                    	//TODO: implement if possible
                    	}
                	}

       			}
            
        	}
			// generate Abstract informations
			for(int i=0; i < artQdc.sizeOfAbstractArray(); i++){

				String mAbstract = artQdc.getAbstractArray(i).getStringValue();
				String lang = artQdc.getAbstractArray(i).getLang();

				AbstractDefinition abstractElement = mDef.addNewAbstract();
				abstractElement.setLang(lang);
				abstractElement.setStringValue(mAbstract);
				}
			
			// generate Publisher informations
			OriginInfoDefinition oIDef = mDef.addNewOriginInfo();
			StringPlusSupplied mPub = oIDef.addNewPublisher();
			mPub.setStringValue("Digital Peer Publishing (DiPP)");
			RelatedItemDefinition host = mDef.addNewRelatedItem();
			host.setType(RelatedItemTypeAttributeDefinition.HOST);

			//generate Bibliographic informations
			for(int i = 0; i < artQdc.sizeOfBibliographicCitationArray(); i++){
				DateDefinition dateIssued = oIDef.addNewDateIssued();
				dateIssued.setStringValue(artQdc.getBibliographicCitationArray(i).getJournalIssueDate());
				dateIssued.setEncoding(DateEncodingAttributeDefinition.ISO_8601);

				TitleInfoDefinition tInfo = host.addNewTitleInfo();
				XsString tName = tInfo.addNewTitle();
				tName.setStringValue(artQdc.getBibliographicCitationArray(i).getJournalTitle());

				PartDefinition pDef = mDef.addNewPart();

				DetailDefinition dDef0 = pDef.addNewDetail(); 
				dDef0.setType("volume");
				XsString volume = dDef0.addNewNumber();
				volume.setStringValue(artQdc.getBibliographicCitationArray(i).getJournalVolume());
				
				DetailDefinition dDef1 = pDef.addNewDetail(); 
				dDef1.setType("issue");
				XsString issue = dDef1.addNewNumber();
				issue.setStringValue(artQdc.getBibliographicCitationArray(i).getJournalIssueNumber());

			}
			for(int i = 0; i < artQdc.sizeOfCreatedArray(); i++){
					DateDefinition dateCreated = oIDef.addNewDateCreated();
					dateCreated.setStringValue(artQdc.getCreatedArray(i).getStringValue());			
					dateCreated.setEncoding(DateEncodingAttributeDefinition.ISO_8601);
				}
				
				
			for(int i=0; i < artQdc.sizeOfIdentifierArray(); i++){
				//log.debug(artQdc.getIdentifierArray(i).schemaType().);
				log.debug(URN.type);

				if((artQdc.getIdentifierArray(i).schemaType().equals(URL.type))){
					IdentifierDefinition mIdent = mDef.addNewIdentifier();
					mIdent.setType("url");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(URN.type))){
					IdentifierDefinition mIdent = mDef.addNewIdentifier();
					mIdent.setType("urn");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(DOI.type))){
					IdentifierDefinition mIdent = mDef.addNewIdentifier();
					mIdent.setType("doi");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(ISSN.type))){
					IdentifierDefinition issn = host.addNewIdentifier();
					issn.setType("issn");
					if((artQdc.getIdentifierArray(i).getStringValue().trim().length() > 0)){
						issn.setStringValue(artQdc.getIdentifierArray(i).getStringValue().replace("ISSN:", ""));
					}
				}

			
			}
		
			//TODO: implement check for DiPP-Object type
			GenreDefinition gDef = mDef.addNewGenre();
			gDef.setStringValue("article");
			

			//artQDC
	        // set subjects
	        for (int i=0; i < artQdc.sizeOfSubjectArray(); i++) {
	        	Keyword kw = artQdc.getSubjectArray(i);

	        	//ddc subjects
	        	for (int j=0; j < kw.sizeOfDdcArray(); j++) {
	        		if (kw.getDdcArray(j).trim().length() > 0){
	        			ClassificationDefinition mClass = mDef.addNewClassification();
	        			mClass.setAuthorityURI("http://dewey.info");
	        			mClass.setAuthority("ddc");
	        			mClass.setValueURI("http://dewey.info/class/" + kw.getDdcArray(j) + "/");
	        			mClass.setStringValue(kw.getDdcArray(j));
	        		}
	        	}

	        	//classified subjects
	        	for (int j=0; j < kw.sizeOfClassifiedArray(); j++) {
	        		if (kw.getClassifiedArray(j).trim().length() > 0){
	        			ClassificationDefinition mClass = mDef.addNewClassification();
	        			if (kw.getClassificationArray(j).schemaType().getName().equals(DDC.type.getName())){
	        				mClass.setAuthority(getClassificationSystem(kw.getClassificationArray(j).schemaType()));
		        			mClass.setAuthorityURI("http://dewey.info");
	        				mClass.setStringValue(kw.getClassifiedArray(j));
		        			mClass.setValueURI("http://dewey.info/class/" + kw.getClassificationArray(j).getStringValue() + "/");
	        			}else{
	        				mClass.setAuthority(getClassificationSystem(kw.getClassificationArray(j).schemaType()));
	        				mClass.setStringValue((kw.getClassifiedArray(j)));
	        			}
	        		}
	        	}
	        	
	            for (int j=0; j < kw.sizeOfUnclassifiedArray(); j++) {
	            	if (kw.getUnclassifiedArray(j).trim().length() > 0){
	            		SubjectDefinition mSubj = mDef.addNewSubject();
	            		StringPlusAuthority mTopic = mSubj.addNewTopic();
	            		mTopic.setStringValue(((String) kw.getUnclassifiedArray(j)));
	            	}
	            }
	        }

		
	        XmlCursor cursor = mDoc.newCursor();
			String location = "http://www.loc.gov/mods/v3" +
					     "     http://www.loc.gov/standards/mods/v3/mods-3-4.xsd";
			if (cursor.toFirstChild())
			{
				//cursor.insertNamespace("dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
				cursor.insertNamespace("mods", "http://www.loc.gov/mods/v3");
				//cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation"), location);
			}

	        
		xmlObj = mDoc;


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
