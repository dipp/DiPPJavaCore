/**
 * MetsObjectMethods.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.repository;

import gov.loc.mets.AmdSecType;
import gov.loc.mets.DmdSecType;
import gov.loc.mets.MdSecType;
import gov.loc.mets.MetsDocument;
import gov.loc.mets.MetsTypo;

import java.rmi.RemoteException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class MetsObjectMethods
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 23.03.2009
 *
 */
public class MetsXmlObject implements FedoraXMLObject {

	// Initiate Logger for MetsObjectMethods
	private static Logger log = Logger.getLogger(MetsXmlObject.class);
	
	private MetsDocument metsDoc = null;
	private MetsDocument.Mets metsObj = null;

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#addXMLDatastream(java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.apache.xmlbeans.XmlObject, java.lang.String)
	 */
	@Override
	public void addXMLDatastream(String aDSId, String label, String mimeType,
			String versionId, XmlObject object, String aDSType) {
		MdSecType mdSecType = null;
		MdSecType.MdWrap mdWrap = null;
		if (aDSType.equals(Constant.cFedoraMetadataClassTechnical) ){
			AmdSecType amdMets = metsObj.addNewAmdSec();
			amdMets.setSTATUS(Constant.cFedoraStatusActive);
			amdMets.setID(aDSId);
			mdSecType = amdMets.addNewTechMD();			
			mdWrap = mdSecType.addNewMdWrap();
			mdWrap.setMDTYPE(MdSecType.MdWrap.MDTYPE.OTHER);
		}else if (aDSType.equals(Constant.cFedoraMetadataClassDescriptive)){
			DmdSecType dmdSecFedora = metsObj.addNewDmdSecFedora();
			dmdSecFedora.setSTATUS(Constant.cFedoraStatusActive);
			dmdSecFedora.setID(aDSId);
			mdSecType = dmdSecFedora.addNewDescMD();
			mdWrap = mdSecType.addNewMdWrap();
			mdWrap.setMDTYPE(MdSecType.MdWrap.MDTYPE.DC);
		}
		mdSecType.setID(versionId); //"DiPPExt1.0");
		mdSecType.setCREATED(Calendar.getInstance());
		mdWrap.setMIMETYPE(mimeType); //"text/xml");
		mdWrap.setLABEL(label); //"DiPPExt record");
		MdSecType.MdWrap.XmlData xmlData = mdWrap.addNewXmlData();
		xmlData.set(object);

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#createXMLDocument(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createXMLDocument(String namespace, String aPid,
			String label, String model) throws RemoteException {

		
		metsDoc = MetsDocument.Factory.newInstance();		
		metsObj = metsDoc.addNewMets();
		metsObj.setTYPE(Constant.cMetsTypeFedora);
		metsObj.setPROFILE(model);
		metsObj.setOBJID(aPid);	
		metsObj.setLABEL(label);
		MetsTypo.MetsHdr metsHeader = metsObj.addNewMetsHdr();
		metsHeader.setRECORDSTATUS(Constant.cFedoraStatusInactive);

		metsHeader.setCREATEDATE(Calendar.getInstance());
		metsHeader.setLASTMODDATE(Calendar.getInstance());		

		return aPid;

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#getPID()
	 */
	@Override
	public String getPID() {
		return metsObj.getOBJID();		 
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#ingestXMLObject()
	 */
	@Override
	public String getXmlText() {
		return metsDoc.xmlText();
	}

}
