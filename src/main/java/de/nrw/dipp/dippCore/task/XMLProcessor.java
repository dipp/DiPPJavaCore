package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import de.nrw.dipp.dippCore.util.Config;
import de.nrw.dipp.dippCore.util.Config;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.CreatorPerson;
import de.nrw.dipp.dippCore.webservice.SubjectClassified;

/**
 * <p>Title: XMLProcessor.java</p>
 * <p>Description: </p>
 * <p>
 *  <ul>
 *      <li>changed on 20.06.2006: method signature changed for processIdentifier</li>
 *      <li>bugfix on 05.01.2007: exception handling in setFile, setInputstream</li>
 *  </ul>
 * </p>
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2004
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: XMLProcessor.java,v 1.4 2007/01/05 11:43:14 dippadm Exp $ * 
 */
public class XMLProcessor {

	private Document				mDocument		= null;
	private Element					mArticleInfo	= null;
	private Element					mArticle		= null;
	private static XMLProcessor		mXMLProcessor	= null;
	private static Properties		mISO639			= new Properties();
	private static Licence			mLicence		= null;
	private List<ImageProperties>	mImageList		= new Vector<ImageProperties>();
	
    // Get Logger for TaskXML
	private static Logger log = Logger.getLogger(XMLProcessor.class);
	

	private XMLProcessor(){
		try{
			mISO639.load(getClass().getResourceAsStream("iso639.properties"));
			mLicence = new Licence(getClass().getClassLoader().getResourceAsStream("licence.xml"));
		}catch(IOException ioExc){
			System.out.println(ioExc);
			ioExc.printStackTrace();
		}
	}
	
	public static XMLProcessor getInstance(){
		if (mXMLProcessor == null){
			mXMLProcessor = new XMLProcessor();
		}
		return mXMLProcessor;
	}
	
	/**
	 * renamed from setFilename to setFile
	 * param changed from String to File
	 * 
	 * @param aFile
	 */
	public void setFile(File aFile)throws XMLException{
		try{
			SAXBuilder saxBuilder = new SAXBuilder(false);
			saxBuilder.setEntityResolver(new EntityResolverImpl());
			mDocument = saxBuilder.build(aFile);		
			init();
		}catch(JDOMException jdomExc){
//			throw new XMLException("", jdomExc);
			throw (XMLException)
            	new XMLException().initCause(jdomExc);
		}catch(IOException ioExc){
			throw (XMLException)
        	new XMLException().initCause(ioExc);
		}
	}
	
	public void setInputstream(InputStream aInputstream)throws XMLException{
		try{
			SAXBuilder saxBuilder = new SAXBuilder(false);
			saxBuilder.setEntityResolver(new EntityResolverImpl());
			mDocument = saxBuilder.build(aInputstream);		
			init();
		}catch(JDOMException jdomExc){
			throw (XMLException)
        	new XMLException().initCause(jdomExc);
		}catch(IOException ioExc){
			throw (XMLException)
        	new XMLException().initCause(ioExc);
		}
	}
	
	private void init(){
		DocType docType = mDocument.getDocType();
		docType.setInternalSubset(""); // suppresses the "<!NOTATION" comments
		mArticle = mDocument.getRootElement();
		mArticleInfo = mArticle.getChild("articleinfo");
	}
	
	public void processLanguage(String aLanguage){
		if (mISO639.containsKey(aLanguage)){
			mArticle.setAttribute("lang", mISO639.getProperty(aLanguage));			
		}else{
			mArticle.removeAttribute("lang");
		}
	}
	
	public void processSubjects(List aSubjectList){
		Hashtable subjectTables = new Hashtable();
		Iterator it = aSubjectList.iterator();
		while (it.hasNext()){
			SubjectClassified subClassified = (SubjectClassified)it.next();
			List subjectHashList = null;
			if (subjectTables.containsKey(subClassified.getClassificationSystem())){
				subjectHashList = (List)subjectTables.get(subClassified.getClassificationSystem());				
			}else{
				subjectHashList = new Vector();
				subjectTables.put(subClassified.getClassificationSystem(), subjectHashList);
			}
			subjectHashList.add(subClassified.getSubjectClassified());
		}
		Element subjectSet = null; 
		if (!subjectTables.isEmpty()){
			Enumeration enumKeys = subjectTables.keys();// elements();
			while (enumKeys.hasMoreElements()){
				subjectSet = new Element("subjectset");
				mArticleInfo.addContent(subjectSet);
				String scheme = (String)enumKeys.nextElement();
				subjectSet.setAttribute("scheme", scheme);
				List subjectHashList = (List)subjectTables.get(scheme);
				Iterator itSubject = subjectHashList.iterator();
				while (itSubject.hasNext()){
					Element subject = new Element("subject"); 
					subjectSet.addContent(subject);
					subject.setText((String)itSubject.next());					
				}
			}
		}
	}
	
	public void processKeywords(List aKeywordList){
		Element keywordSet = new Element("keywordset"); 
		mArticleInfo.addContent(keywordSet);
		Iterator it = aKeywordList.iterator();
		while (it.hasNext()){
			Element keyword = new Element("keyword"); 
			keywordSet.addContent(keyword);
			keyword.setText((String)it.next());
		}
	}
	
	/*
	 * method signature changed 20-06-2006 by JS: now supports any type of identifier
	 */
	public void processIdentifier(String aIdentifier, String aIdentClass){
		List identifierList = mArticleInfo.getChildren("biblioid");
		Properties identifierProps = new Properties();
		if ( !identifierList.isEmpty() ){
			Iterator it = identifierList.iterator();
			while (it.hasNext()){
				Element identElem = (Element)it.next();
				identifierProps.setProperty(identElem.getAttribute("class").getValue(), identElem.getText());				
			}
			mArticleInfo.removeChildren("biblioid");
		}
		Element biblioID = new Element("biblioid"); 
		mArticleInfo.addContent(biblioID);
		biblioID.setAttribute("class", aIdentClass);
		biblioID.setText(aIdentifier);
		Enumeration keyEnums = identifierProps.keys();
		while (keyEnums.hasMoreElements()){
			String key = (String)keyEnums.nextElement();			
			biblioID = new Element("biblioid"); 
			mArticleInfo.addContent(biblioID);
			biblioID.setAttribute("class", key);
			biblioID.setText(identifierProps.getProperty(key));			
		}
	}
	
	public void processBiblioset(String aJournalTitle, String aISSN){
		Element biblioset = new Element("biblioset"); 
		mArticleInfo.addContent(biblioset);
		biblioset.setAttribute("relation", "journal");
		Element issn = new Element("issn");
		biblioset.addContent(issn);
		issn.setText(aISSN);
		Element title = new Element("title");
		biblioset.addContent(title);
		title.setText(aJournalTitle);
	}
	
	public void processIssuenum(String aIssue){
		Element elem = new Element("issuenum"); 
		mArticleInfo.addContent(elem);
		elem.setText(aIssue);
	}
	
	public void processVolumenum(String aVolume){
		Element elem = new Element("volumenum"); 
		mArticleInfo.addContent(elem);
		elem.setText(aVolume);
	}
	
	public void processLegalnotice(String aLanguage, String aLicenceType){
		Element elem = new Element("legalnotice");
		Element title = new Element("title");
		Element para = new Element("para");
		elem.addContent(title);
		elem.addContent(para);
		if (aLanguage.equals("ger")){
			title.setText(mLicence.getTitle("de"));
			para.setText(mLicence.getLicence("de", aLicenceType.toLowerCase()));			
		}else{
			title.setText(mLicence.getTitle("en"));
			para.setText(mLicence.getLicence("en", aLicenceType.toLowerCase()));			
		}
		mArticleInfo.addContent(elem);
	}
	
	public void processsTitleAbbreviation(String aAbbrev){
		Element elem = new Element("titleabbrev");
		elem.setText(aAbbrev);
		mArticleInfo.addContent(elem);
	}
	
	
	public void processAuthor(List aPersonList, List aCorporationList){
		mArticleInfo.removeChildren("author");
		Element authorgroup = new Element("authorgroup"); 
		mArticleInfo.addContent(authorgroup);
		Iterator it = aPersonList.iterator();
		while (it.hasNext()){
			CreatorPerson person = (CreatorPerson)it.next();
			Element author = new Element("author"); 
			authorgroup.addContent(author);
			Element firstName = new Element("firstname"); 
			author.addContent(firstName);
			firstName.setText(person.getFirstName());
			Element surName = new Element("surname"); 
			author.addContent(surName);
			surName.setText(person.getLastName());
			if (person.getOrganization() != null){
				if (person.getOrganization().length() > 0){
					Element affiliation = new Element("affiliation"); 
					author.addContent(affiliation);
					Element org = new Element("orgname");
					affiliation.addContent(org);
					org.setText(person.getOrganization());
				}				
			}
		}
	}
	
	/**
	 * <p><em>Title: Iterate through DocBookXml-Elements and search for imagedata Tags</em></p>
	 * <p>Description: helper Method for getImageList</p>
	 * 
	 * @param current 
	 */
	private synchronized void listChildren(Element current) {
		if (current.getName().equals("imagedata")){
			setImageProperties(current);
		}
		List children = current.getChildren();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Element child = (Element)iterator.next();
			listChildren(child);
		}
	}
	
	/**
	 * <p><em>Title: Creates a new entry into a List of imageProperties</em></p>
	 * <p>Description: Method creates imageProperties Objects and adds them to a List of imageProperties. 
	 * It therefore if parses the ImageElement of the generated DocBookXml file for fileref, depth 
	 * and width values. If all required informations are supplied from the element a new 
	 * imageProperties will be added to the List</p>
	 * 
	 * @param imageElement 
	 */
	//TODO Values of px are expected for depth and width, they will be used as values for image resize. 
	// Therefore values must be calculated anywhere before this method and supplied to the DocBook-Xml File. 
	// But where?   
	private void setImageProperties(Element imageElement){
		Attribute depth = imageElement.getAttribute("depth");
		Attribute width = imageElement.getAttribute("width");
		Attribute fileref = imageElement.getAttribute("fileref");
		if (depth != null & width != null & fileref != null){
			double pxWidth = 0;
			double pxHeight = 0;
			//TODO patch mm-Values to px values, if mm where found in DocBook XML. 
			// Qa: The next is a workaround only, because I can't locate the correct
			// place for px value generation until now :-(  
			if(depth.getValue().indexOf("mm") != -1){
				// Transform mm-values into dpi
				log.info("filename: " + fileref.getValue() + " depth: " + depth.getValue().substring(0, depth.getValue().indexOf("mm")) + " width: " + width.getValue().substring(0, width.getValue().indexOf("mm")));
				pxWidth = Double.valueOf(width.getValue().substring(0, width.getValue().indexOf("mm"))) * 3.78;
				pxHeight = Double.valueOf(depth.getValue().substring(0, depth.getValue().indexOf("mm"))) * 3.78;
			}
			else if(depth.getValue().indexOf("px") != -1){
				pxWidth = Integer.parseInt(width.getValue().substring(0, width.getValue().indexOf("px")));
				pxHeight = Integer.parseInt(depth.getValue().substring(0, depth.getValue().indexOf("px")));
			}
			if (pxWidth >= Constant.cResizeLimit){
				String scale2Y = Double.toString(pxWidth * pxHeight / Constant.cResizeLimit).substring(0, Double.toString(pxWidth * pxHeight / Constant.cResizeLimit).indexOf("."));
				mImageList.add(new ImageProperties(fileref.getValue(), Double.toString(Constant.cResizeLimit), scale2Y));
			}
			else{
			log.warn("Image not added to Resizing List:  depth: " + depth.getValue() + "width: " + width.getValue() + "fileref: " + fileref.getValue());
			}
		}
	}
	
	// get all image names, width, height
	public synchronized List<ImageProperties> getImageList(){
		mImageList.clear();
		listChildren(mArticle);
		return mImageList;
	}
	
	public void writeToOutputStream(OutputStream aOutstream)throws IOException{		
		Format f = Format.getRawFormat();
//		f.setIndent(" ");
		new XMLOutputter(f).output(mDocument, aOutstream);
	}
	
	public String writeTo()throws IOException{
		Format f = Format.getRawFormat();
//		f.setIndent(" ");
		return new XMLOutputter(f).outputString(mDocument);
	}
	
}
