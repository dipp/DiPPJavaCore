/**
 * TestMimeTypeMagic.java - This file is part of the DiPP Project by hbz
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

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Class TestMimeTypeMagic
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 16.03.2014
 *
 */
public class TestMimeTypeMagic {

	// Initiate Logger for TestMimeTypeMagic
	private static Logger log = Logger.getLogger(TestMimeTypeMagic.class);
	
	private String fileName = "src/test/resources/materials/converter.xml";


	
	@Test public void testMimeType(){
		String mimeType = new String();
		
		File file = new File(fileName);
		log.info("File location = " + file.getAbsolutePath());
		mimeType = de.nrw.dipp.dippCore.util.request
				.MimeType.getInstance().getMimeType(new File(fileName));
		log.info("MimeType found: " + mimeType);
	}
	
	
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		TestMimeTypeMagic testMime = new TestMimeTypeMagic();
		testMime.testMimeType();
		
		// TODO Auto-generated method stub

	}
}
