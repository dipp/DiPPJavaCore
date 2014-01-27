/**
 * TestWebServiceImpl.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.test;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openarchives.oai.x20.oaiDc.ArticleType;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;

import de.nrw.dipp.dippCore.repository.FedoraAccess;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.www.definitions.DippSoapBindingImpl;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import de.nrw.dipp.dippCore.webservice.SetNewArticle_fault;

/**
 * Class TestWebServiceImpl
 * 
 * <p><em>Title: Test WS Server Side</em></p>
 * <p>Description: A class to Test the Axis Generated DiPP 
 * WebService Implementation on Server side</p>
 * 
 * @author aquast, email
 * creation date: 23.01.2014
 *
 */
public class TestWebServiceImpl {

	// Initiate Logger for TestWebServiceImpl
	private static Logger log = Logger.getLogger(TestWebServiceImpl.class);

	/**
	 * 
	 */
	public TestWebServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Test public void callSetNewArticle(){
		
		log.debug("start newArticleTest");
		DippSoapBindingImpl impl = new DippSoapBindingImpl();
		
		String result = null;
		String[] containerPid = {"dipp:1898"}; 
		String journalPid = "dipp:1"; // ID of the journal the article should be part of 
		QualifiedDublinCore qdc = null; //TODO create dummy qdc
		String nativeDocIdent = "http://www.dipp.nrw.de/download/Beispiel.rtf"; //The URL where the Article-stream is accessible
		String storageType = "temporary";
		String[] targetFormat = {"html"};
		
		File tmpConvertDir = new File("convert");
		tmpConvertDir.mkdir();
		qdc = getQdcTestObject("dipp:2152");
		
		try {
			result = impl.setNewArticle(containerPid, journalPid, qdc, nativeDocIdent, storageType, targetFormat);
		} catch (SetNewArticle_fault e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		log.info("Result of newArticle method: " + result);
		
		tmpConvertDir.delete();
	}

	
	/**
	 * <p><em>Title: Create QualifiedDublinCore for test purposes</em></p>
	 * <p>Description: actualy this method needs to request remote Fedora Repo
	 * to create an QualifiedDublinCore Object. It uses Metadata Class for this purpose</p>
	 * 
	 * @param articlePid
	 * @return 
	 */
	public QualifiedDublinCore getQdcTestObject(String articlePid){
		QualifiedDublinCore qdcTest = Metadata.getQualifiedDublinCoreMetadata(articlePid);
		log.debug("Result of Fedora request: " + qdcTest.toString());
		return Metadata.getQualifiedDublinCoreMetadata(articlePid);
	}
	
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestWebServiceImpl testWS = new TestWebServiceImpl();
		testWS.callSetNewArticle();
		log.debug("finished Test new article");
	}
}
