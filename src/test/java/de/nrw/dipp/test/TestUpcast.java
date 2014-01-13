/**
 * TestUpcast.java - This file is part of the DiPP Project by hbz
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
import de.nrw.dipp.dippCore.task.*;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class TestUpcast
 * 
 * <p><em>Title: Class provides direct access to the UpCast-Class</em></p>
 * <p>Description: </p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 21.01.2010
 *
 */
public class TestUpcast {

	// Initiate Logger for TestUpcast
	private static Logger log = Logger.getLogger(TestUpcast.class);

	/**
	 * <p><em>Title:</em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Constant.setAbsolutPath("/home/aquast/plist");
		TestUpcast test = new TestUpcast();
		File srcFile = test.setFile();
		Upcast ups = Upcast.getInstance();
		ups.setSourceFile(srcFile);
		try {
			ups.doConvert("/home/aquast/testUpcast", 2, "11", "dippArticle");
		} catch (ConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public File setFile(){
		File srcFile = new File("/home/aquast/downloader/blubb/test.rtf");
		return srcFile;
	}
}
