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


import nbnDe11112004033116.IdentifierType;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlString;
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


//import XMetaDissPlus Definition;
import de.dNb.standards.xmetadissplus.*;
import de.dNb.standards.xmetadissplus.XMetaDissDocument.XMetaDiss;
import de.dNb.standards.pc.*;
import de.dNb.standards.pc.NameType.OtherNameType;
import de.dNb.standards.pc.NameType.OtherNameType.Enum;
import de.dNb.standards.pc.PersonDocument.*;


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
public class XMetaDissPlusXml extends InternalXmlService {

	// Initiate Logger for ModsXml
	private static Logger log = Logger.getLogger(XMetaDissPlusXml.class);
	private XMetaDissDocument mDoc = null;

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {
		// TODO Auto-generated method stub

	    mDoc = XMetaDissDocument.Factory.newInstance();
	    XMetaDiss mDiss = mDoc.addNewXMetaDiss();
	    
	    
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

		for(int i=0; i < artQdc.sizeOfTitleArray(); i++){
			
			SimpleLiteral title = mDiss.addNewTitle();
			
			
			String mTitle = artQdc.getTitleArray(i).getStringValue();
			title.setStringValue(mTitle);
			title.setLang((artQdc.getTitleArray(i).getLang()));
			title.setType("ddb:titleISO639-2");
			}

		// generate Authors informations
		// use Vcard-Class for creation of Authors informations 
		for (int i=0; i < artQdc.sizeOfCreatorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getCreatorArray(i);

        	if (vcard.getLastName().trim().length() > 0){
                SimpleLiteral mName = mDiss.addNewCreator();
                
                
                //mName.setStringValue(vcard.getFirstName() + vcard.getLastName());
    			
    			
    			PersonDocument pDoc = PersonDocument.Factory.newInstance();
    			Person pers = pDoc.addNewPerson();
    			if(vcard.isSetPndIdentifier() && vcard.getPndIdentifier()!=""){
        			pers.setPNDNr(vcard.getPndIdentifier());    				
    			}
    			NameType nameT = pers.addNewName();
    			nameT.setForeName(vcard.getFirstName());
    			nameT.setSurName(vcard.getLastName());
       			nameT.setOtherNameType(OtherNameType.REAL_NAME);
       			
       			
       			mName.set(pDoc);
               // mName.xsetType(pDoc.xmlText().);


       			}
            
        	}
        	
		// generate Contributors informations
		// use Vcard-Class for creation of Authors informations 
		for (int i=0; i < artQdc.sizeOfContributorArray(); i++) {
        	VCard vcard = (VCard)artQdc.getContributorArray(i);

        	if (vcard.getLastName().trim().length() > 0){
                SimpleLiteral mName = mDiss.addNewContributor();
                
    			PersonDocument pDoc = PersonDocument.Factory.newInstance();
    			Person pers = pDoc.addNewPerson();
    			if(vcard.isSetPndIdentifier() && vcard.getPndIdentifier()!=""){
        			pers.setPNDNr(vcard.getPndIdentifier());    				
    			}
    			NameType nameT = pers.addNewName();
    			nameT.setForeName(vcard.getFirstName());
    			nameT.setSurName(vcard.getLastName());
       			nameT.setOtherNameType(OtherNameType.REAL_NAME);
       			
       			mName.set(pDoc);

       			}
            
        	}


		// generate Abstract informations
			for(int i=0; i < artQdc.sizeOfAbstractArray(); i++){

				String mAbstract = artQdc.getAbstractArray(i).getStringValue();
				String lang = artQdc.getAbstractArray(i).getLang();

				SimpleLiteral abstractElement = mDiss.addNewAbstract();
				abstractElement.setLang(lang);
				abstractElement.setStringValue(mAbstract);
				}
			
			// generate Publisher informations
			SimpleLiteral mPub = mDiss.addNewPublisher();
			mPub.setStringValue("Digital Peer Publishing (DiPP)");

			//generate Bibliographic informations
			for(int i = 0; i < artQdc.sizeOfBibliographicCitationArray(); i++){
				SimpleLiteral mBib = mDiss.addNewBibliographicCitation();
				//mBib.setStringValue(artQdc.getBibliographicCitationArray(i).toString());
				
				SimpleLiteral dateIssued = mDiss.addNewIssued();
				dateIssued.setStringValue(artQdc.getBibliographicCitationArray(i).getJournalIssueDate());


			}

			for(int i = 0; i < artQdc.sizeOfCreatedArray(); i++){
					SimpleLiteral dateCreated = mDiss.addNewCreated();
					dateCreated.setStringValue(artQdc.getCreatedArray(i).getStringValue());			
				}
				
				
			for(int i=0; i < artQdc.sizeOfIdentifierArray(); i++){
				log.info(artQdc.getIdentifierArray(i).schemaType().toString());
				log.info(URN.type.toString());
				
				if((artQdc.getIdentifierArray(i).schemaType().equals(URL.type))){
					SimpleLiteral mIdent = mDiss.addNewIdentifier();
					mIdent.setType("url");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(URN.type))){
					SimpleLiteral mIdent = mDiss.addNewIdentifier();
					mIdent.setType("urn");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(DOI.type))){
					SimpleLiteral mIdent = mDiss.addNewIdentifier();
					mIdent.setType("doi");
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
				if((artQdc.getIdentifierArray(i).schemaType().equals(ISSN.type))){
					SimpleLiteral issn = mDiss.addNewIdentifier();
					issn.setType("issn");
					if((artQdc.getIdentifierArray(i).getStringValue().trim().length() > 0)){
						issn.setStringValue(artQdc.getIdentifierArray(i).getStringValue().replace("ISSN:", ""));
					}
				}else{
					SimpleLiteral mIdent = mDiss.addNewIdentifier();
					mIdent.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}

			
			}
		
			

			//artQDC
	        // set subjects
	        for (int i=0; i < artQdc.sizeOfSubjectArray(); i++) {
	        	Keyword kw = artQdc.getSubjectArray(i);

	        	//ddc subjects
	        	for (int j=0; j < kw.sizeOfDdcArray(); j++) {
	        		if (kw.getDdcArray(j).trim().length() > 0){
	        			SimpleLiteral mClass = mDiss.addNewSubject();
	        			//mClass.setAuthorityURI("http://dewey.info");
	        			//mClass.setAuthority("ddc");
	        			//mClass.setValueURI("http://dewey.info/class/" + kw.getDdcArray(j) + "/");
	        			//mClass.setStringValue(kw.getDdcArray(j));
	        		}
	        	}

	        	
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
