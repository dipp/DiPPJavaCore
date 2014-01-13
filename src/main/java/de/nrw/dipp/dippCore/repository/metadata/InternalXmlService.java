/**
 * InternalXmlService.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.repository.metadata;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.FedoraAccess;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RIsearch;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class InternalXmlService
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 19.09.2011
 *
 */
public abstract class InternalXmlService {

	// Initiate Logger for InternalXmlService
	private static Logger log = Logger.getLogger(InternalXmlService.class);

	private String formatLabel = null;
	private String messageAddStream = "added new " + formatLabel + " stream";
	private String messageModStream = "modified " + formatLabel + " stream";

	protected String artPid = null;
	protected MetadataDocument.Metadata artQdc = null;
	protected XmlObject xmlObj = null;
	protected static Properties options = new Properties();

		
	// Because Metadata stream labels are not as Class Names we have to create a mapping
	// as long as the old DiPP Version is in use!
	
	String test = null;
	
	
	/**
	 * Private Constructor: Objects can by initiated from Factory Class solely 
	 */
	protected InternalXmlService(){
	}

	public static class Factory {

		/**
		 * <p><em>Title: Method to create a new instance</em></p>
		 * <p>Description: Facory method to create a new instance of 
		 * one of the subclasses, that represent a metadata stream used in DiPP</p>
		 * 
		 * @param mdName the metadata format 
		 * @param pid the pid of the entire Fedora Object
		 * @return 
		 */
		public static InternalXmlService newInstance(String mdName, String pid){
			InternalXmlService ixl = null;
			// Because Metadata stream labels are not as Class Names we have to create a mapping
			// as long as the old DiPP Version is in use! Therefore making a little bit pattern matching
			String classNameFrag = mdName.replace("oai_", "");
			String firstChar = classNameFrag.substring(0,1).toUpperCase(); 
			String lastChars = classNameFrag.substring(1); 
			String className = firstChar + lastChars + "Xml";
			log.info(className);
			
			try {
				Class internalXmlSeviceClass = Class.forName("de.nrw.dipp.dippCore.repository.metadata." + className);
				ixl = (InternalXmlService) internalXmlSeviceClass.newInstance();
				ixl.setPid(pid);
				ixl.setOptions(options);
				ixl.setFormatLabel(mdName);
				ixl.artQdc = ixl.parseMDFromQdc(ixl.artPid);
				ixl.createXmlPresentation();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
			return ixl;
		}

		/**
		 * <p><em>Title: Method to create a new instance</em></p>
		 * <p>Description: Facory method to create a new instance of 
		 * one of the subclasses, that represent a metadata stream used in DiPP. This 
		 * method allows to that additional Properties,which may required for 
		 * correct datastream generation</p>
		 * 
		 * @param mdName the metadata format 
		 * @param pid the pid of the entire Fedora Object
		 * @param Options Properties for the data stream generation  
		 * @return 
		 */
		public static InternalXmlService newInstance(String mdName, String pid, Properties Options){
			InternalXmlService ixl = null;
			options = Options;
			ixl = newInstance(mdName, pid);
			return ixl;
			
		}
	}
	
	

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method checks if the generated XMLBeans representation is 
	 * valid against the given xsd</p>
	 * 
	 * @return 
	 */
	public boolean isValid(){
		// Set up the validation error listener.
		ArrayList<String> validationErrors = new ArrayList<String>();
		XmlOptions validationOptions = new XmlOptions();
		validationOptions.setErrorListener(validationErrors);		
	
		// During validation, errors are added to the ArrayList for
		// retrieval and printing by the printErrors method.
		boolean isValid = xmlObj.validate(validationOptions);
		// Print the errors if the XML is invalid.
		if (!isValid){
			log.info("---------------------");
			log.info("Validation Result: " + isValid);
			Iterator<String> iter = validationErrors.iterator();
			while (iter.hasNext()){
				log.info(iter.next());
			}
			log.info("---------------------");
		}

	return isValid;
}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method returns the XMLBeans representation of the 
	 * created Metadata stream as String. Method is thought for testing purposes mainly.</p>
	 * 
	 * @return 
	 */
	public String xml2String(){
		return xmlObj.toString();
	}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method serializes XMLBeans representation of the Metadata stream 
	 * into the FedoraObject by adding or modifying data stream</p>
	 * @param methodName defines if stream should be newly added (add) or modified (modify) 
	 *  
	 */
	public void writeInFedoraObject(String methodName){
	if(methodName.equals("add")){
		try {
			(new DOManagement()).addDatastream(artPid, formatLabel, formatLabel, "text/xml", xmlObj.xmlText().getBytes("UTF-8"), messageAddStream, Constant.cFedoraDatastreamControlGroupInternal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}else if(methodName.equals("modify")){
		try {
			(new DOManagement()).modifyDatastream(artPid, formatLabel, formatLabel, "text/xml", xmlObj.xmlText().getBytes("UTF-8"),  messageModStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			}
		}
	}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method creates a XMLBeans representation of the requested Metadata format</p>
	 *  
	 */
	protected abstract void createXmlPresentation();

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method creates a XMLBeans representation of QDC 
	 * by deserialize XML Data stream from Fedora Object</p>
	 * 
	 * @param pid
	 * @return 
	 */
	protected MetadataDocument.Metadata parseMDFromQdc(String pid){
		MetadataDocument.Metadata qdc = null;
		if (pid != null){
			try {
				byte [] qdcStream = (new DOManagement()).getDatastream(pid, "QDC");
				MetadataDocument qdcDoc = MetadataDocument.Factory.parse(new String(qdcStream, "UTF-8"));
				qdc = qdcDoc.getMetadata();

			} catch (Exception e) {
			
				log.error(e);

			}
		}else{
			log.warn("no pid available, can't parse QDC Stream");
		}
		return qdc;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method returns the Journal Pid associated with the Article. 
	 * Uses Fedora Resource Index (RI)</p>
	 * 
	 * @param aPid
	 * @return 
	 */
	protected String getJournalPidByRI(String aPid){
		
		String jPid = null;
		String query = "select $object from <#ri>where <info:fedora/" + aPid + ">  <fedora-model:label> $label and $object <fedora-model:label> $label and $object <fedora-model:contentModel> \'DiPP:eJournal\'";
		//log.info(query);
		RIsearch riSearch = new RIsearch(RIsearch.cType_Tuples, RIsearch.cFormat_Simple, RIsearch.cLang_Tuples_itql, query);
		
		String response = riSearch.getStringResult().trim();
		//log.info("RI Result for Journal Pid request: " + response);
		
		if (response.length() != 0){
			jPid = response.substring(22,response.length()-1);
			log.info(jPid.toString());
		}
		return jPid;
	}


	protected void setPid(String pid){
		artPid = pid;
	}
	
	protected void setFormatLabel(String FormatLabel){
		formatLabel = FormatLabel;
	}

	public abstract void setOptions(Properties Options);
	
	public abstract Properties getOptions();
	
	public XmlObject getXmlObj(){
		return xmlObj;
	}


}
