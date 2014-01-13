package de.nrw.dipp.dippCore.repository.metadata.dc;

import org.apache.xmlbeans.XmlObject;
import org.openarchives.oai.x20.oaiDc.DcDocument;
import org.openarchives.oai.x20.oaiDc.OaiDcType;
import org.purl.dc.elements.x11.ElementType;


/**
 * <p>Title: DublinCoreSimplified.java</p>
 * <p>Description: A class for generating dc simplied XML objects.</p>
 * <p>Created on 11.08.2004</p>
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
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: DublinCoreSimplified.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class DublinCoreSimplified {
/*
	private List _title						= new Vector();
	private List _creator					= new Vector();
	private List _subject					= new Vector();
*/	
//	private List _descr
	private DcDocument	_dcDoc				= null;
	private OaiDcType 	_oaiDC				= null;
	
	public DublinCoreSimplified(){
		init();
	}
	
	private void init(){
		_dcDoc = DcDocument.Factory.newInstance();
		_oaiDC = _dcDoc.addNewDc();
	} 
	public XmlObject getSimplifiedDublinCore(){
		return _dcDoc;
	}
	
	public void addTitle(String aTitle){
		ElementType element = _oaiDC.addNewTitle();
//		element.setLang("de");
		element.setStringValue(aTitle);
	}
	
	public void addCreator(String aCreator){
		ElementType element = _oaiDC.addNewCreator();
		element.setStringValue(aCreator);
	}
	
	public void addSubject(String aSubject){
		ElementType element = _oaiDC.addNewSubject();
		element.setStringValue(aSubject);
	}
	public void addRights(String aRights){
		ElementType element = _oaiDC.addNewRights();
		element.setStringValue(aRights);
	}
	public void addCoverage(String aCoverage){
		ElementType element = _oaiDC.addNewCoverage();
		element.setStringValue(aCoverage);
	}
	public void addRelation(String aRelation){
		ElementType element = _oaiDC.addNewRelation();
		element.setStringValue(aRelation);
	}
	public void addLanguage(String aLanguage){
		ElementType element = _oaiDC.addNewLanguage();
		element.setStringValue(aLanguage);
	}
	public void addSource(String aSource){
		ElementType element = _oaiDC.addNewSource();
		element.setStringValue(aSource);
	}
	public void addIdentifier(String aIdent){
		ElementType element = _oaiDC.addNewIdentifier();
		element.setStringValue(aIdent);
	}
	public void addFormat(String aFormat){
		ElementType element = _oaiDC.addNewFormat();
		element.setStringValue(aFormat);
	}
	public void addType(String aType){
		ElementType element = _oaiDC.addNewType();
		element.setStringValue(aType);
	}
	public void addDate(String aDate){
		ElementType element = _oaiDC.addNewDate();
		element.setStringValue(aDate);
	}
	public void addContributor(String aContributor){
		ElementType element = _oaiDC.addNewContributor();
		element.setStringValue(aContributor);
	}
	public void addPublisher(String aPublisher){
		ElementType element = _oaiDC.addNewPublisher();
		element.setStringValue(aPublisher);
	}
	public void addDecsription(String aDescr){
		ElementType element = _oaiDC.addNewDescription();
		element.setStringValue(aDescr);
	}
	
}
