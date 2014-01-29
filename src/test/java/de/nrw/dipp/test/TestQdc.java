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

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.openarchives.oai.x20.oaiDc.ArticleType;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.DocType;
import org.openarchives.oai.x20.oaiDc.ISBN;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.PubType;
import org.openarchives.oai.x20.oaiDc.URL;
import org.openarchives.oai.x20.oaiDc.URN;
import org.purl.dc.elements.x11.Keyword;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.elements.x11.VCard;

import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreQualified;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.Contributor;
import de.nrw.dipp.dippCore.webservice.CreatorCorporated;
import de.nrw.dipp.dippCore.webservice.CreatorPerson;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.IdentNumberType;
import de.nrw.dipp.dippCore.webservice.Part;
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
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method creates a QualifiedDublinCore Object from scratch.
	 * Object should be used for testing purposes only</p>
	 * 
	 * @return 
	 */
	public static QualifiedDublinCore getTestQdc(){
		
		QualifiedDublinCore qdcTest = new QualifiedDublinCore(); 
		
		Element[] title = new Element[2];
		title[0] = new Element();
		title[0].setValue("A dummy article for JUnit tests");
		title[0].setLang("eng");
		title[1] = new Element();
		title[1].setValue("really just for test");
		title[1].setLang("eng");
		qdcTest.setTitle(title);

		Element[] alttitle = new Element[1];
		alttitle[0] = new Element();
		alttitle[0].setValue("Ein Testartikel für Tests mit JUnit ");
		alttitle[0].setLang("ger");
		qdcTest.setAlternative(alttitle);
		
		qdcTest.setDescription(
				new String[]{"This article is just a dummy for JUnit testing purposes"});

		qdcTest.setSubject(new String[]{"Test", "JUnit Test"});
		
		qdcTest.setDDC(new String[]{"010"});
		
		SubjectClassified[] sClass = new SubjectClassified[1];
		sClass[0] = new SubjectClassified(); 
		sClass[0].setClassificationIdent("JEL");
		sClass[0].setClassificationSystem("?");
		sClass[0].setSubjectClassified("Informationstechnologie");
		qdcTest.setSubjectClassified(sClass);
		
		Citation[] bibCite = new Citation[1];
		bibCite[0] = new Citation();
		bibCite[0].setJournalTitle("Test Jounal");
		bibCite[0].setJournalIssueDate("2014-01-29");
		bibCite[0].setJournalIssueNumber("1");
		bibCite[0].setJournalVolume("2014");
		qdcTest.setBibliographicCitation(bibCite);
				
		
		qdcTest.setCreated(Calendar.getInstance());
		qdcTest.setModified(Calendar.getInstance());
		qdcTest.setIssued(Calendar.getInstance());
		qdcTest.setValid(Calendar.getInstance());
		
		qdcTest.setDateSubmitted(Calendar.getInstance());
		qdcTest.setDateAccepted(Calendar.getInstance());
		qdcTest.setDateCopyrighted(Calendar.getInstance());
		
		qdcTest.setDocType(new String[]{"article"});
		qdcTest.setPubType(new String[]{"article"});
		qdcTest.setArticleType(new String[]{"peer-reviewed"});
		qdcTest.setFormat(new String[]{"html"});
		
		qdcTest.setIdentifier(new String[]{"12345"});
		qdcTest.setIdentifierDOI("10.xxx-xxx-xxx");
		qdcTest.setIdentifierURN("nbn.xxx.xxx.xxx");
		qdcTest.setIdentifierISBN("xxx-xxxx-xxx");
		qdcTest.setIdentifierISSN("xxxx-xxxx");
		qdcTest.setIdentifierURL("http://no-domain.xxx");

		qdcTest.setLanguage(new String[]{"ger"});
		
		
		CreatorPerson[] person = new CreatorPerson[1];
		person[0] = new CreatorPerson();
		person[0].setFirstName("Andres");
		person[0].setLastName("Quast");
		person[0].setEmailAddress("quast@hbz-nrw.de");
		person[0].setPNDIdentNumber("");
		person[0].setDippIdentNumber("000-0002");
		
		IdentNumberType[] identNumber = new IdentNumberType[1];
		identNumber[0] = new IdentNumberType();
		identNumber[0].setIdentNumber("00000-00002-3187-2536");
		identNumber[0].setType("ORCID-ID");
		person[0].setIdentNumber(identNumber);
		qdcTest.setCreatorPerson(person);
		
		Contributor[] contrib = new Contributor[1];
		contrib[0] = new Contributor();
		contrib[0].setFirstName("Peter");
		contrib[0].setLastName("Reimer");
		contrib[0].setEmailAddress("Reimer@hbz-nrw.de");
		contrib[0].setPNDIdentNumber("");
		contrib[0].setDippIdentNumber("000-0001");
		
		identNumber = new IdentNumberType[1];
		identNumber[0] = new IdentNumberType();
		identNumber[0].setIdentNumber("00000-00002-3187-2536");
		identNumber[0].setType("ORCID-ID");
		contrib[0].setIdentNumber(identNumber);
		qdcTest.setContributor(contrib);

		
		
		
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
