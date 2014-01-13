/**
 * BagitCreator.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.util;

import gov.loc.mets.DivType;
import gov.loc.mets.FileGrpType;
import gov.loc.mets.MetsDocument;
import gov.loc.mets.MetsDocument.Mets;
import gov.loc.mets.MetsTypo.FileSec;
import gov.loc.mets.StructMapType;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.Download;
import de.nrw.dipp.dippCore.repository.SearchCondition;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.InternalXmlService;

/**
 * Class BagitCreator
 * 
 * <p><em>Title: Creates Bagit Container Format as described in DA NRW</em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 20.02.2012
 *
 */
public class BagitCreator {

	// Initiate Logger for BagitCreator
	private static Logger log = Logger.getLogger(BagitCreator.class);

	private MetsDocument mDoc = null;
	private String bagitId = null;
	private String bagitName = null;
	
	
	public BagitCreator(String name){
		bagitId = name;
		bagitName = bagitId.replace(":", "_");
		//createContainerDirs();
		//createBagitTxtFiles();
		
		
	}
	
	

	
	/**
	 * <p><em>Title: Read in Mets-Document as XmlBean</em></p>
	 * <p>Description: Method read in Mets-Document as XmlBean. Mets Document is used to as starting 
	 * point to create Bagit</p>
	 * 
	 * @param MDoc 
	 */
	public void setMetsDocument(XmlObject MDoc){
		// cast ist notwendig, weil Testklasse MetsDoc nicht kennen soll, ob das gut ist?
		mDoc = (MetsDocument) MDoc;
		createBagitInformation();
		addMetaFileFromString(MDoc.xmlText());
		createBagitTxtFiles();
	}
	
	
	private void createBagitInformation(){
		Mets mMets = mDoc.getMets();

		// first find Files associated
		ArrayList<String[]> fileId = new ArrayList<String[]>();
		String lFileName = null;
		FileSec fSec = mMets.getFileSec();
		
		StructMapType[] sMapA = mMets.getStructMapArray();
		String fileID = null;
		
		for(int i=0; i < fSec.getFileGrpArray().length; i++){
			FileGrpType fGrp = fSec.getFileGrpArray(i);
			for(int j= 0; j < fGrp.getFileArray().length; j++){
				gov.loc.mets.FileGrpType.File file = fGrp.getFileArray(j);
				
				// only URLs are useful, local references will be ignored 
				if(file.getFLocat().getLOCTYPE().equals(FileGrpType.File.FLocat.LOCTYPE.URL)){
					
					log.info(file.getFLocat().getHref());
					fileID = file.getID();
					for(int k=0; k < sMapA.length; k++){
						for(int m=0; m < mMets.getStructMapArray(k).getDiv().getDivArray().length; m++){
							for(int n=0; n< mMets.getStructMapArray(k).getDiv().getDivArray(m).getFptrArray().length; n++){
								//log.info("huhu" + fileID);
								if(mMets.getStructMapArray(k).getDiv().getDivArray(m).getFptrArray(n).getFILEID().equals(fileID)){
									lFileName = mMets.getStructMapArray(k).getDiv().getDivArray(m).getLABEL();
									log.info(lFileName);
								}
							}
						}
					}
					
					String[] ids = new String[]{file.getFLocat().getHref(), fileID, lFileName};
					downloadDataStream(file.getFLocat().getHref(), lFileName);
					fileId.add(ids);
				}
			}
		}

	}
	
	/**
	 * <p><em>Title: Download all relevant Datastreams for a DiPP-Article</em></p>
	 * <p>Description: Due to security issues the alt Themis Server does not expose the Fedora
	 * API-A to public. Therefore if mets should be created for complex article content
	 * we have to download all associated and relevant datastreams and create a container format for it.
	 *  </p>
	 * 
	 * @param pid 
	 */
	public void downloadDataStream(String uri, String fName){
		
		StringBuffer sb = new StringBuffer();
		Download dLoad = new Download(uri);
		
		try {
			dLoad.downloadToFile(createContainerDirs() + "/" + fName);
			ChecksumGenerator cksum = new ChecksumGenerator();
			String sum = cksum.getMD5(dLoad.getBytes(uri, false));
			log.info(sum);
			sb.append(fName + " " + sum + "\n");
			FileUtil.saveStreamToFile(new File(System.getProperty("user.home") + "/mets/" + bagitName + "/" + "metadata_md5.txt"), sb.toString());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method creates the Directories needed for the Bagit 
	 * as sub directories of a dirctory named as Bagit</p>
	 * 
	 * @return 
	 */
	private String createContainerDirs(){
		
		File file = new File(System.getProperty("user.home") + "/mets/" + bagitName + "/data");
		if(!file.exists()){
			file.mkdirs();
		}

		return file.getAbsolutePath();
	}


	public void addMetaFileFromString(String stream){
		FileUtil.saveStreamToFile(new File(System.getProperty("user.home") + "/mets/" + bagitName + "/" + bagitName + "_mets.xml"), stream);
	}
	
	
	private void createBagitTxtFiles(){
		
		ArrayList<File> fileList = new ArrayList<File>();
		fileList.add(new File("bagit.txt"));
		fileList.add(new File("manifest-md5.txt"));
		//fileList.add(new File("test"));
		
		Iterator<File> fileIt = fileList.iterator();
		while(fileIt.hasNext()){
			File file = new File(System.getProperty("user.home") + "/mets/" + bagitName + "/" + fileIt.next().getName());
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}

}
