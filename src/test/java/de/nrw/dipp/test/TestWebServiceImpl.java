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

import de.nrw.dipp.definitions.extmetadata.ExtMdDocument.ExtMd;
import de.nrw.dipp.dippCore.repository.FedoraAccess;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.www.definitions.DippSoapBindingImpl;
import de.nrw.dipp.dippCore.webservice.AdministrativeMetadata;
import de.nrw.dipp.dippCore.webservice.CreatorPerson;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.IdentNumberType;
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

	private static DippSoapBindingImpl impl = new DippSoapBindingImpl();
	private String articlePid = "dipp:2181";
	// as long as we have now complete TestQdc Object:
	private QualifiedDublinCore qdcTest = getQdcTestObject(articlePid);

	//TODO: construct a complete dummy testQdc via TestQdc
	//private QualifiedDublinCore qdcTest = TestQdc.getTestQdc();
	
	/**
	 * 
	 */
	public TestWebServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Test public void callGetQualifiedDublinCore(){

		log.info("start with testing setQualifiedDublinCore using pid: " + articlePid);
		try {
			qdcTest = impl.getQualifiedDublinCore(articlePid);
		} catch (RemoteException e) {
			log.error(e);
		}
	}
	
	@Test public void callSetAdministrativeMetadata(){
		QualifiedDublinCore qdcTest = getQdcTestObject(articlePid); 
		AdministrativeMetadata admProperties = null;
		log.info("start with testing getQualifiedDublinCore, using articlePid: " +articlePid);
		
		
		try {
			impl.setAdministrativeMetadata(admProperties);
		} catch (RemoteException e) {
			log.error(e);
		}
		
	}
	
	@Test public void callSetQualifiedDublinCore(){
		QualifiedDublinCore qdcTest = getQdcTestObject(articlePid); 

		log.info("start with testing getQualifiedDublinCore, using QDC from articlePid: " + articlePid);
		try {
			impl.setQualifiedDublinCore(articlePid, qdcTest);
		} catch (RemoteException e) {
			log.error(e);
		}
		
	}
	
	@Test public void callGetArticleContentMetadata(){

		log.info("start with testing newArticle");
		
		ExtendedMetadata extMD = null;
		
		try {
			extMD = impl.getArticleContentMetadata(articlePid);
		} catch (RemoteException e) {
			log.error(e);
			e.printStackTrace();
		}
		log.info(extMD.toString());
	}

	@Test public void callSetNewArticle(){
		
		log.info("start with testing newArticle");
		
		String newArticlePid = null;
		String[] containerPid = {"dipp:1898"}; 
		String journalPid = "dipp:1898"; // ID of the journal the article should be part of 
		QualifiedDublinCore qdc = null; 
		
		String nativeDocIdent = "http://www.dipp.nrw.de/download/Beispiel.rtf"; //The URL where the Article-stream is accessible
		//nativeDocIdent = "http://www.zeitenblicke.de/2009/2/wunder/dippArticle.pdf"; //The URL where the Article-stream is accessible
		String storageType = "permanent";
		String[] targetFormat = {"pdf"};
		
		File tmpConvertDir = new File("convert");
		tmpConvertDir.mkdir();
		
		try {
			newArticlePid = impl.setNewArticle(containerPid, journalPid, qdcTest, nativeDocIdent, storageType, targetFormat);
		} catch (SetNewArticle_fault e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		setArticlePid(newArticlePid);

	}

	
	/**
	 * <p><em>Title: Create QualifiedDublinCore for test purposes</em></p>
	 * <p>Description: actually this method needs to request remote Fedora Repo
	 * to create an QualifiedDublinCore Object. It uses Metadata Class for this purpose</p>
	 * 
	 * @param articlePid
	 * @return 
	 */
	public QualifiedDublinCore getQdcTestObject(String articlePid){
		
		//log.info("create testQdc from existing QDC of article: " + articlePid);
		//QualifiedDublinCore qdcTest = Metadata.getQualifiedDublinCoreMetadata(articlePid);
		
		QualifiedDublinCore qdcTest = TestQdc.getTestQdc();
		return qdcTest;
	}
	
	public void setArticlePid(String pid){
		articlePid = pid;
	}
	
	public void setQdcTest(QualifiedDublinCore qdc){
		qdcTest = qdc;
	}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constant.setAbsolutPath("/home/aquast/git/dippCoreMvn");

		TestWebServiceImpl testWS = new TestWebServiceImpl();
		testWS.callSetNewArticle();
		//testWS.callGetQualifiedDublinCore();
		//testWS.callSetQualifiedDublinCore();
		//testWS.setArticlePid("temp:1866");
		//testWS.callGetArticleContentMetadata();
		log.info("finished WS implementation tests");
	}
}
