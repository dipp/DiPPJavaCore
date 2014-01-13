/**
 * EpicurXml.java - This file is part of the DiPP Project by hbz
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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import nbnDe11112004033116.AdministrativeDataType;
import nbnDe11112004033116.DeliveryType;
import nbnDe11112004033116.EpicurDocument;
import nbnDe11112004033116.FormatType;
import nbnDe11112004033116.HasVersionType;
import nbnDe11112004033116.IdentifierType;
import nbnDe11112004033116.RecordType;
import nbnDe11112004033116.ResourceType;
import nbnDe11112004033116.TransferType;
import nbnDe11112004033116.UpdateStatusType;
import nbnDe11112004033116.EpicurDocument.Epicur;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.ISBN;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.URL;
import org.openarchives.oai.x20.oaiDc.URN;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.terms.PartType;

import de.nrw.dipp.dippCore.util.URNManagement;

/**
 * Class EpicurXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 26.09.2011
 *
 */
public class EpicurXml extends InternalXmlService {

	// Initiate Logger for EpicurXml
	private static Logger log = Logger.getLogger(EpicurXml.class);

	Properties epiOptions = new Properties();
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {
		int updateStatus = 1;
		String rFormat = "text/html";
		
		// set optional Parameters
		if(epiOptions.containsKey("updateStatus")){
			updateStatus = Integer.parseInt(epiOptions.getProperty("updateStatus"));
			log.info(updateStatus);
		}
		if (epiOptions.containsKey("resourceFormat")){
			rFormat = epiOptions.getProperty("resourceFormat");
		}
		
		EpicurDocument epicurDoc = EpicurDocument.Factory.newInstance();
		Epicur epicur = epicurDoc.addNewEpicur();
		
		// WORKAROUND!!! Insert SchemaLocation attributes.
		XmlCursor cursor = epicur.newCursor();
		cursor.toFirstContentToken();
		cursor.toLastAttribute();
		cursor.insertNamespace("", "urn:nbn:de:1111-2004033116");
		cursor.insertNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		cursor.insertAttributeWithValue("schemaLocation","http://www.w3.org/2001/XMLSchema-instance", "urn:nbn:de:1111-2004033116 http://www.persistent-identifier.de/xepicur/version1.0/xepicur.xsd");
		cursor.dispose();

		// create Administrative Data Part 
		AdministrativeDataType mAdmin = epicur.addNewAdministrativeData();
		DeliveryType mDelivery = mAdmin.addNewDelivery();
		TransferType transfer = mDelivery.addNewTransfer();
		transfer.setType(TransferType.Type.OAI);
		
		// Set update status
		UpdateStatusType updateStatusType = mDelivery.addNewUpdateStatus();
		updateStatusType.setType(UpdateStatusType.Type.Enum.forInt(updateStatus));
		log.info("UpdateStatus: " + updateStatusType.getType());

		// create record part
		RecordType record = epicur.addNewRecord();
		
		//URN Identifier preparation
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(artQdc.getIdentifierArray(0).getStringValue());

		// Resource Identifier preparation
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		FormatType resourceFormat = resource.addNewFormat();

		// HasVersion preparation for DOI
		HasVersionType hasVersion = null;
		
		// set Identifiers
		for (int i=0; i< artQdc.sizeOfIdentifierArray() ; i++){
			//find and set urn
			//if (artQdc.getIdentifierArray(i).getType().equals(URN.type.getName().toString())){
			SimpleLiteral simLit = artQdc.getIdentifierArray(i);
			log.debug(artQdc.getIdentifierArray(i).schemaType().toString());
			log.debug(URN.type.toString());
			

			if(simLit.schemaType().getName().toString().equals((URN.type.getName().toString()))){
				identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
				identifier.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
			}
			// now set resourceIdentifier
			else if (simLit.schemaType().getName().toString().equals(URL.type.getName().toString())){
				resourceIdentifier.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
				resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
				resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
				resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
			}
			else if (simLit.schemaType().getName().toString().equals(DOI.type.getName().toString())){
				// DOI can be set with update status "urn_new_version" only
				// @see http://www.persistent-identifier.de/xepicur/version1.0/xepicur.xsd
				if (updateStatus == 1){
					hasVersion = record.addNewHasVersion();
					hasVersion.setScheme(HasVersionType.Scheme.DOI);
					hasVersion.setStringValue(artQdc.getIdentifierArray(i).getStringValue());
				}
			
			}
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(rFormat);
		}
		
		xmlObj = epicurDoc;
		
	}


	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#setOptions(java.util.Properties)
	 */
	@Override
	public void setOptions(Properties Options) {
		epiOptions = Options;

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
