/**
 * TestQdc.java - This file is part of the DiPP Project by hbz
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

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import de.nrw.dipp.dippCore.webservice.SubjectClassified;

/**
 * Class TestQdc
 * 
 * <p><em>Title: </em></p>
 * <p>Description: A simple Class to generate an 
 * QualifiedDublinCoreObject with data< for Testing purposes/p>
 * 
 * @author aquast, email
 * creation date: 28.01.2014
 *
 */
public class TestQdc {

	// Initiate Logger for TestQdc
	private static Logger log = Logger.getLogger(TestQdc.class);

	/**
	 * 
	 */
	public TestQdc() {
		// TODO Auto-generated constructor stub
	}
	
	public static QualifiedDublinCore getTestQdc(){
		
		QualifiedDublinCore qdcTest = new QualifiedDublinCore(); 
		Element[] title = new Element[2];
		title[0] = new Element();
		title[0].setValue("A dummy article for Java Junit test");
		title[0].setLang("eng");
		title[1] = new Element();
		title[1].setValue("A dummy article for Java Junit test");
		title[1].setLang("eng");
		qdcTest.setTitle(title);

		String[] subjUnQualified = new String[]{"Test", "JUnit Test"};
		qdcTest.setSubject(subjUnQualified);
		
		String[] ddc = new String[]{"010"};
		qdcTest.setDDC(ddc);
		
		SubjectClassified[] sClass = new SubjectClassified[1];
		sClass[0] = new SubjectClassified(); 
		sClass[0].setClassificationIdent("JEL");
		sClass[0].setClassificationSystem("?");
		sClass[0].setSubjectClassified("Informationstechnologie");
		qdcTest.setSubjectClassified(sClass);
		
		
		
		return qdcTest;
		
	}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestQdc.getTestQdc();
	}
}
