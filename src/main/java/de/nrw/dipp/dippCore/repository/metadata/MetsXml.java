/**
 * MetsXml.java - This file is part of the DiPP Project by hbz
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import gov.loc.mets.*;
import gov.loc.mets.FileGrpType.*;
import gov.loc.mets.DivType;
import gov.loc.mets.DivType.Fptr;
import gov.loc.mets.FileGrpType.File.FContent;
import gov.loc.mets.FileGrpType.File.FLocat;
import gov.loc.mets.MdSecType.MdWrap;
import gov.loc.mets.MdSecType.MdWrap.XmlData;
import gov.loc.mets.MetsDocument.Mets;
import gov.loc.mets.MetsTypo.FileSec;
import gov.loc.mets.MetsTypo.MetsHdr;
import gov.loc.mets.MetsTypo.MetsHdr.Agent;
import gov.loc.mets.StructMapType;
import gov.loc.mods.v3.IdentifierDefinition;


import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.openarchives.oai.x20.oaiDc.PubType;
import org.openarchives.oai.x20.oaiDc.URL;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.Download;
import de.nrw.dipp.dippCore.repository.SearchCondition;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.util.BagitCreator;
import de.nrw.dipp.dippCore.util.ChecksumGenerator;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class MetsXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * @author aquast, email
 * creation date: 31.01.2012
 *
 */
public class MetsXml extends InternalXmlService {

	// Initiate Logger for MetsXml
	private static Logger log = Logger.getLogger(MetsXml.class);

	// good idea to make this vars instance vars?
	private MetsDocument mDoc = null;
	private Mets mMets = null;
	
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.metadata.InternalXmlService#createXmlPresentation()
	 */
	@Override
	protected void createXmlPresentation() {

		mDoc = MetsDocument.Factory.newInstance();
		mMets = mDoc.addNewMets();
		
		//create Header Section
		MetsHdr header = mMets.addNewMetsHdr();
		header.setCREATEDATE(getCalendar());
		header.setRECORDSTATUS("Complete");
		/*
		Agent agentI = header.addNewAgent();
		agentI.setName("Andres Quast");
		agentI.setROLE(Agent.ROLE.CREATOR);
		agentI.setTYPE(Agent.TYPE.INDIVIDUAL);
		*/
		Agent agentO = header.addNewAgent();
		agentO.setName("Hochschulbibliothekszentrum NRW (hbz)");
		agentO.setROLE(Agent.ROLE.CREATOR);
		agentO.setTYPE(Agent.TYPE.ORGANIZATION);
		

		XmlData xData = null;
		MdWrap mWrap = null;
		MdSecType dmdSec = null;
		//TODO remove hardcoding for different md-formats:
		//create DC Meta Data Section
		int idC = 1000;
		dmdSec = mMets.addNewDmdSec();
		dmdSec.setID("dmd" + idC++);
		mWrap = dmdSec.addNewMdWrap();
		mWrap.setMDTYPE(MdWrap.MDTYPE.DC);
		mWrap.setLABEL("Dublin Core Simple");
		xData = mWrap.addNewXmlData();
		InternalXmlService dcXml = InternalXmlService.Factory.newInstance("oai_dc", artPid);
		
		xData.set(dcXml.getXmlObj());
        
		//create Mods Meta Data Section
		dmdSec = mMets.addNewDmdSec();
		dmdSec.setID("dmd" + idC++);
		mWrap = dmdSec.addNewMdWrap();
		mWrap.setMDTYPE(MdWrap.MDTYPE.OTHER);
		mWrap.setLABEL("MODS");
		xData = mWrap.addNewXmlData();
		InternalXmlService modsXml = InternalXmlService.Factory.newInstance("mods", artPid);

		xData.set(modsXml.getXmlObj());

		// create amdSec
		/*AmdSecType aSec = mMets.addNewAmdSec();
		MdSecType aRight = aSec.addNewRightsMD();
		aRight.setID("rights"  + idC++); 
		aRight.addNewMdWrap();*/
		
		//create and prepare FileSec
		FileSec fSec = mMets.addNewFileSec();
		//FileGrpType fileGrp = null;
		FileGrpType pdfGrp =  null;
		FileGrpType htmlGrp = null;
		FileGrpType imageGrp = null;
		FileGrpType restGrp = null;

		String pubType = null;
		for (int i = 0; i < artQdc.getTypeArray().length; i++){
			log.info(artQdc.getTypeArray(i).schemaType());
			if (artQdc.getTypeArray(i).schemaType().equals(PubType.type)){
				pubType = artQdc.getTypeArray(i).getStringValue();
			}
		}
		// prepare StructMap
		int count = 1000;
		StructMapType sMap = null;
		sMap = createStructMap(mMets, "log_" + count++, "Logical", pubType, "HTML Representation");
		
		
		//add SplashPage as File
		FileGrpType fGrp = fSec.addNewFileGrp();
		
		for(int i =0 ; i< artQdc.getIdentifierArray().length; i++){
			
			//find Splash-Page-Url 
			if((artQdc.getIdentifierArray(i).schemaType().equals(URL.type))){
				File file = fGrp.addNewFile();
				file.setID("file" + i);
				file.setMIMETYPE("text/html");
				FLocat fLoc = file.addNewFLocat();
				fLoc.setLOCTYPE(FileGrpType.File.FLocat.LOCTYPE.URL);
				fLoc.setHref(artQdc.getIdentifierArray(i).getStringValue());
				
				createStructMapContent(sMap.getDiv(), file, "SplashPage");

			}
		}
		

		
		// Create file groups for html presentation and pdf presentation
		//first get all Fedora Content-Objects related to the article
		DigitalObject[] digObjs = getDigitalObjects();
		

		log.info("DSLength: " + digObjs.length);
		for (int i=0; i < digObjs.length; i++){

			//get Datastream metadata
			Hashtable<String, DigitalObjectDatastream> DsTable = getDataStreams(digObjs[i].getPid());
			Enumeration<DigitalObjectDatastream> DsEnum = DsTable.elements();
			
			while(DsEnum.hasMoreElements()){
				//get DigitalObjectDatastream as value of Hashtable. 
				// find required information within the DigitalObjectDatastream
				DigitalObjectDatastream dStream = DsEnum.nextElement();
				
				// Start creating FileSec
				// Datastream of DC, RELS-EXT or "text/xml" should not be exposed in METS, skip them 
				if(! (dStream.getId().equals("RELS-EXT") 
						|| dStream.getId().equals("DC") 
						|| dStream.getMimeType().equals("text/xml") 
						|| dStream.getMimeType().equals("text/rtf"))){

					FLocat fLoc = null;
					
					if(dStream.getMimeType().equals("text/html")){
						if(htmlGrp == null){
							htmlGrp =  fSec.addNewFileGrp();
							htmlGrp.setID("htmlFiles");
						}
						File file = htmlGrp.addNewFile();
						file.setID("html" + dStream.getId());
						//file.setID(dStream.getLabel());
						file.setMIMETYPE(dStream.getMimeType());
						fLoc = file.addNewFLocat();
						fLoc.setLOCTYPE(FileGrpType.File.FLocat.LOCTYPE.URL);
						fLoc.setHref("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/get/" + digObjs[i].getPid() +"/" + dStream.getId());
						createStructMapContent(sMap.getDiv(), file, dStream.getLabel());
						

					}else if (dStream.getMimeType().startsWith("image")){
						if(imageGrp == null){
							imageGrp =  fSec.addNewFileGrp();
							imageGrp.setID("imageFiles");
						}
						File file = imageGrp.addNewFile();
						file.setID("image_" + i + "_" + dStream.getId());			
						//file.setID(dStream.getLabel());
						file.setMIMETYPE(dStream.getMimeType());
						fLoc = file.addNewFLocat();
						fLoc.setLOCTYPE(FileGrpType.File.FLocat.LOCTYPE.URL);
						fLoc.setHref("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/get/" + digObjs[i].getPid() +"/" + dStream.getId());
						sMap = createStructMap(mMets, "log_"+ count++ ,"Logical",  pubType, "HTML Representation");
						createStructMapContent(sMap.getDiv(), file, dStream.getLabel());
						
					}else if (dStream.getMimeType().equals("application/pdf")){
						if(pdfGrp == null){
							pdfGrp =  fSec.addNewFileGrp();
							pdfGrp.setID("pdfFiles");
						}
						File file = pdfGrp.addNewFile();
						file.setID("pdf_" + i + "_" + dStream.getId());			
						//file.setID(dStream.getLabel());
						file.setMIMETYPE(dStream.getMimeType());
						fLoc = file.addNewFLocat();
						fLoc.setLOCTYPE(FileGrpType.File.FLocat.LOCTYPE.URL);
						fLoc.setHref("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/get/" + digObjs[i].getPid() +"/" + dStream.getId());
						//div = createStructMapDiv(sMap, digObjs[i].getPid(), dStream.getId());
						//sMap = createStructMap(mMets, "Logical", "log_" + count++);
						sMap = createStructMap(mMets, "log_"+ count++ , "Logical", pubType, "PDF Representation");
						createStructMapContent(sMap.getDiv(), file, dStream.getLabel());
						
	
					}else{
						if(restGrp == null){
							restGrp =  fSec.addNewFileGrp();
							restGrp.setID("otherFiles");
						}
						File file = restGrp.addNewFile();
						file.setID(dStream.getId());
						file.setMIMETYPE(dStream.getMimeType());
						fLoc = file.addNewFLocat();
						fLoc.setLOCTYPE(FileGrpType.File.FLocat.LOCTYPE.URL);
						fLoc.setHref("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/get/" + digObjs[i].getPid() +"/" + dStream.getId());
						sMap = createStructMap(mMets, "log_" + count++, "Logical", pubType, "Other Content");
						createStructMapContent(sMap.getDiv(), file, dStream.getLabel());
						
					}
					
					// Now create StrucMap?
					
					//bCreator.downloadDataStream(fLoc.getHref(), dStream);

				} 
			}			
		}
		
		xmlObj = mDoc;
	    //bCreator.addMetaFileFromString(xmlObj.xmlText());

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
	
	private Calendar getCalendar(){
		Calendar cal = Calendar.getInstance();
		return cal;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method searches for Fedora Objects which are defined by search conditions 
	 * (including the given PID) and generates a List of Fedora Objects that matches the search 
	 * 
	 * @param aPID
	 * @return
	 * @throws Exception 
	 */
	private DigitalObject[] getDigitalObjects(){

		DigitalObject[] digObjs = null;
		List<SearchCondition> conditions = new Vector<SearchCondition>();
		SearchCondition condition = new SearchCondition();
		condition.setProperty("relation");
		condition.setCondition(SearchCondition.cContains);
		condition.setValue(artPid);
		conditions.add(condition);
		
		/*
		condition = new SearchCondition();
		condition.setProperty("title");
		condition.setCondition(SearchCondition.cContains);
		condition.setValue("pdf");
		conditions.add(condition);
		*/
		
		try{
			digObjs = DOManagement.retrieveDigitalObjects(conditions);
		}catch(RemoteException e){
			//throw new Exception(e);
		}
		if (digObjs == null){
			return null;
		}else{
			return digObjs;			
		}
	}
	
	/**
	 * <p><em>Title: get Hashtable with information about associated Datastreams</em></p>
	 * <p>Description: Method uses the getDatastreamTable Method from ServiceManagement to 
	 * find all Datastreams associated with an DiPP-Article</p>
	 * 
	 * @param pid
	 * @return 
	 */
	private Hashtable<String, DigitalObjectDatastream> getDataStreams(String pid){
		ServiceManagement sManag = new ServiceManagement();
		Hashtable<String, DigitalObjectDatastream> DsTable = new Hashtable<String, DigitalObjectDatastream>();
		try {
			DsTable = sManag.getDatastreamTable(pid);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return DsTable;
	}
	
	/**
	 * <p><em>Title: create StructMap Element for METS Container</em></p>
	 * <p>Description: Method creates new StructMap Element if no StructMap Element 
	 * with the param Id exists. If already exists the StructMap Element of this Id is returned. 
	 * Method is slightly similar to Singleton Pattern. </p>
	 * 
	 * @param mMets
	 * @param Id
	 * @param type
	 * @param label
	 * @return 
	 */
	private StructMapType createStructMap(Mets mMets, String Id, String sType, String dType, String Label){
		
		StructMapType sMap = null;
		int exist = 0;
		
		for (int i=0;  i < mMets.getStructMapArray().length; i++){
			if(mMets.getStructMapArray(i).getDiv().getLABEL().equals(Label)){
				exist = i;
			}
		}
		if(exist > 0){
			sMap = mMets.getStructMapArray(exist);
		}else{
			sMap = mMets.addNewStructMap();				
			//sMap.setID(Id);
			sMap.setTYPE(sType);
			DivType div = sMap.addNewDiv();
			div.setID(Id);
			div.setTYPE(dType);
			div.setLABEL(Label);
		}
		
		return sMap;
		
	}
	
	/*
	private DivType createStructMapDiv(StructMapType sMap, String type, String Id){
		
		DivType div = null;
		// make StructMapDiv unique
		if(sMap.getDiv() != null 
				//&& sMap.getDiv().getTYPE().equals(type)
				){
			div = sMap.getDiv();
		}else{
			div = sMap.addNewDiv();
			//DivType div = Div.addNewDiv();
			div.setTYPE(type);
			div.setID(Id);				
		}
		return div;
		
	}
	*/

	private DivType createStructMapContent(DivType Div, File file, String oFileName){
		
		DivType iDiv = null;
		int exist = -1;
		
		for (int i=0;  i < Div.getDivArray().length; i++){
			if(Div.getDivArray(i).getLABEL().equals(oFileName)){
				exist = i;
				log.debug(exist);
			}
		}
		if(exist > -1){
			iDiv = Div.getDivArray(exist);
		}else{
			iDiv = Div.addNewDiv();				
			iDiv.setTYPE("type");
			iDiv.setLABEL(oFileName);
		}

		Fptr fptr = iDiv.addNewFptr();
		fptr.setFILEID(file.getID());
		//fptr.setID(Id);
		return iDiv;
	}
	
}
