/**
 * TestMimeType.java - This file is part of the DiPP Project by hbz
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

import java.io.File;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.util.request.MimeType;

/**
 * Class TestMimeType
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 07.02.2014
 *
 */
public class TestMimeType {

	// Initiate Logger for TestMimeType
	private static Logger log = Logger.getLogger(TestMimeType.class);

	/**
	 * 
	 */
	public TestMimeType() {
		// TODO Auto-generated constructor stub
	}

	public void test(){
		MimeType mType = MimeType.getInstance();
		String result = mType.getMimeType(new File("/home/aquast/timeline"));
		log.info(result);
	}
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMimeType tMimit = new TestMimeType();
		tMimit.test();
	}
}
