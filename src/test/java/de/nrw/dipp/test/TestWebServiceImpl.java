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

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.junit.Test;

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
		DippSoapBindingImpl impl = new DippSoapBindingImpl();
		String[] pidNS = null; //Namespace of the PID;
		String journalPid = "dipp:1"; // ID of the journal the article should be part of 
		QualifiedDublinCore qdc = null; //TODO create dummy qdc
		String nativeDocIdent = "http://www.dipp.nrw.de/download/Beispiel.rtf"; //The URL where the Article-stream is accessible
		String storageType = null;
		String[] targetFormat = null;
		
		try {
			impl.setNewArticle(pidNS, journalPid, qdc, nativeDocIdent, storageType, targetFormat);
		} catch (SetNewArticle_fault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
	}
}
