/**
 * DoiXml.java - This file is part of the DiPP Project by hbz
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

import org.crossref.schema.x205.DoiBatchDocument;
import org.crossref.schema.x205.DoiBatchDocument.*;
import org.crossref.schema.x205.DepositorDocument.Depositor;
import org.crossref.schema.x205.HeadDocument.Head;
import org.crossref.schema.x205.BodyDocument.Body;
import org.crossref.schema.x205.IssnDocument.Issn;
import org.crossref.schema.x205.IssnDocument.Issn.MediaType;
import org.crossref.schema.x205.JournalArticleDocument.JournalArticle;
import org.crossref.schema.x205.JournalDocument.Journal;
import org.crossref.schema.x205.JournalMetadataDocument.JournalMetadata;
import org.crossref.schema.x205.OriginalLanguageTitleDocument.OriginalLanguageTitle;
import org.crossref.schema.x205.TitleDocument.Title;
import org.crossref.schema.x205.TitlesDocument.Titles;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;
import org.purl.dc.elements.x11.SimpleLiteral;


import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaType;

import de.nrw.dipp.dippCore.repository.DOManagement;


/**
 * Class DoiXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 07.10.2011
 *
 */
public class DoiXml extends InternalXmlService {

	// Initiate Logger for DoiXml
	private static Logger log = Logger.getLogger(DoiXml.class);

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {

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
			log.info("holla: " + jQdc.getIdentifierArray(i).schemaType().toString());
			if ((jQdc.getIdentifierArray(i).schemaType().getName().toString()).equals(ISSN.type.getName().toString())){
				log.info("holla1");
				SimpleLiteral lit = jQdc.getIdentifierArray(i);
				artQdc.addNewIdentifier();
				artQdc.setIdentifierArray(artQdc.sizeOfIdentifierArray()-1 , lit);
			}
		}			

		
		// create Root element
		DoiBatchDocument doiBatchDoc = DoiBatchDocument.Factory.newInstance();
		DoiBatch doiBatch = doiBatchDoc.addNewDoiBatch();
		
		// create Head element
		Head head = doiBatch.addNewHead();
		Depositor depos = head.addNewDepositor();
		depos.setEmailAddress("dipp@hbz-nrw.de"); //has to be set correctly
		
		
		
		// create Body element
		Body body = doiBatch.addNewBody();
		Journal journal = body.addNewJournal();
		JournalMetadata mdJournal = journal.addNewJournalMetadata();
		Issn issn = mdJournal.addNewIssn();
		mdJournal.addAbbrevTitle("My Test J.");
		
		issn.setMediaType(MediaType.PRINT);
	
		for(int i=0; i < artQdc.sizeOfIdentifierArray(); i++){

			SchemaType sType = artQdc.getIdentifierArray(i).type;
			log.info(sType.getName().toString());
			
			if((artQdc.getIdentifierArray(i).schemaType().equals(ISSN.type))){
				if((artQdc.getIdentifierArray(i).getStringValue().trim().length() > 0)){
					issn.setStringValue(artQdc.getIdentifierArray(i).getStringValue().replace("ISSN:", ""));
				}
			}
			
		}
		
		//issn.setStringValue("1234-5678");
		
		JournalArticle artJournal = journal.addNewJournalArticle();
		Titles titles = artJournal.addNewTitles();
		OriginalLanguageTitle lang  = titles.addNewOriginalLanguageTitle();
		
		lang.setLanguage(OriginalLanguageTitle.Language.EN);
		Title title = titles.addNewTitle();
		
		xmlObj = doiBatchDoc;


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
}
