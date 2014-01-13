/**
 * NewConfig.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Map;
import java.util.Iterator;

import de.nrw.dipp.diPPConfiguration.DippInstanceDocument;
import de.nrw.dipp.diPPConfiguration.DippInstanceDocument.DippInstance;
import de.nrw.dipp.diPPConfiguration.InstanceType;
import de.nrw.dipp.diPPConfiguration.ZopeInstanceType;
import de.nrw.dipp.diPPConfiguration.JournalType;
import de.nrw.dipp.diPPConfiguration.RepositoryType;

import org.apache.xmlbeans.XmlOptions;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * Class Config
 * 
 * <p><em>Title: Reads Configuration and provides Environment Informations to other Classes</em></p>
 * <p>Description: Replaces old Config Class. Uses one single Configuration File and uses XMLBeans Databinding.</p>
 * <p>Note: The Datatypes in use here are declared by DiPPConfiguration.xsd</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 14.07.2009
 *
 */
public class Config {

	// Initiate Logger for NewConfig
	private static Logger log = Logger.getLogger(Config.class);

	private static DippInstanceDocument 	mDoc			= null;
	private static DippInstance 			dippInstance	= null;
	private static InstanceType 			instance		= null;
	private static InstanceType[] 			instArray		= null;


	private Document 	mSaxDoc			= null;
	private Element		mRootElement	= null;


	/**
	 * Abstract Constructor
	 * required for testing purposes and XMLProcessor???
	 */
	public Config(){
		
	}

	/**
	 * Constructor for DiPP 1.1 backward compatibility
	 * @param iStream aXMLInputStream
	 * @deprecated only for backward compatibility to DiPP 2 Version
	 */
	public Config(InputStream aXMLInputStream){
		
		//calling new constructor first to create an Default Config File
		this(new File(Constant.cConfigFileDir + Constant.cConfigFileName));
		
		log.info("calling deprecated constructor because no Configuration File was found");		
		try{
			SAXBuilder builder = new SAXBuilder();
			mSaxDoc = builder.build(aXMLInputStream);
			mRootElement = mSaxDoc.getRootElement();
		}catch(JDOMException jdomExc){
			jdomExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
		log.info("write Journaldata");
		writeJournalData2DippConfiguration();
	}

	/*	public NewConfig(InputStream iStream){
		try{
			mDoc = DippInstanceDocument.Factory.parse(iStream);			
		}catch(Exception e){
			log.error(e);
		}
		instArray = mDoc.getDippInstance().getInstanceArray();
	}
*/
	/**
	 * Constructor for DiPP 1.2
	 * @param confFile
	 */
	public Config(File confFile){

		// Create a new File if not exists. At least one Instance is required for this
		// is obsolete in Constant Class is adapted to find out the Dipp Version
		log.info(confFile.getAbsoluteFile());
		if(!confFile.isFile()){
			Properties instanceProp = new Properties();
			instanceProp.put("activeInst", "Melpomene");
			instanceProp.put("name", "Melpomene");
			instanceProp.put("version", "3");
			instanceProp.put("host", "193.30.112.78");
			instanceProp.put("port", "9280");
			instanceProp.put("user", "fedoraAdmin");
			instanceProp.put("password", "fssAug04");
			createNewConfigFile(instanceProp);
		}
		try{
			mDoc = DippInstanceDocument.Factory.parse(confFile);
			log.info("parse Configuration File: " + confFile.getAbsolutePath());
		}catch(Exception e){
			log.error(e);
		}
		instArray = mDoc.getDippInstance().getInstanceArray();
	}


	private void writeJournalData2DippConfiguration(){

		// create a zopeInstance to write journals to
		createZopeInstance(getInstance().getFedoraName(), "Dummy Instance, fill with correct Name!", "Dummy, set Version correctly");
		Properties journalProp = new Properties(); 
		
		Iterator itInstances = mSaxDoc.getRootElement().getChildren().iterator();
		while (itInstances.hasNext()){
			Element dippInstance = (Element)itInstances.next();
			journalProp.put("label", dippInstance.getChild("id").getText());
			journalProp.put("name", dippInstance.getChild("description").getText());
			if(dippInstance.getChild("imageresize") != null){
				journalProp.put("imageResize", dippInstance.getChild("imageresize").getText());
			}
			if(dippInstance.getChild("identifier") != null){
				journalProp.put("identUrl",  dippInstance.getChild("identifier").getText());
				log.info("found: " + dippInstance.getChild("identifier").getText());
			}
			createJournalEntry(getInstance().getFedoraName(), "Dummy Instance, fill with correct Name!", journalProp);
		}

	}

	/**
	 * <p><em>Title: returns the localUrl (Plone) of a Journal Instance</em></p>
	 * <p>Description: Searches for an eJournal Instance and returns an URL if an identifier Element exists</p>
	 * 
	 * @param label <code>String</code> the Label of a Journal
	 * @return <code>String</code> the Url of the Plone Journal Representation 
	 */
	public String getIdentifier(String label){
		//TODO Implement this method
		String localUrl = null;
		
		for (int i = 0; i < instArray.length; i++){
			ZopeInstanceType[] zopeArray = instArray[i].getZopeInstanceArray();
			for (int l = 0; l < zopeArray.length; l++){
				JournalType[] journalArray =  zopeArray[l].getJournalArray(); 
				for (int k = 0; k < journalArray.length; k++){
					if(label.equals(journalArray[k].getLabel())){
						localUrl = journalArray[k].getIdentifierUrl();
					}
				}
			}
				
		}
		return localUrl;
	}
	
	/**
	 * <p><em>Title: Reads into System the value of the imageResize Element</em></p>
	 * <p>Description: </p>
	 * 
	 * @param label <code>String</code> the Label of a Journal
	 * @return <code>boolean</code> should Images be resized or not
	 */
	public boolean isImageResize(String label){
		boolean resize = false;
		
		
		for (int i = 0; i < instArray.length; i++){
			ZopeInstanceType[] zopeArray = instArray[i].getZopeInstanceArray();
			for (int l = 0; l < zopeArray.length; l++){
				JournalType[] journalArray =  zopeArray[l].getJournalArray(); 
				for (int k = 0; k < journalArray.length; k++){
					if(label.equals(journalArray[k].getLabel())){
						resize = journalArray[k].getImageResize();
					}
				}
			}
				
		}
		return resize;
	}
	
	/**
	 * <p><em>Title: Return the OaiSetName</em></p>
	 * <p>Description: Method returns the OAI-Set Name for a Journal, identified by its Label</p>
	 * 
	 * @param label <code>String</code> a label for a Journal
	 * @return <code>String</code> the Name of the OAISet assigned to a Journal
	 */
	public static String getOaiSetName(String label){
		String oaiSetName = null;
		
		for (int i = 0; i < instArray.length; i++){
			if (instArray[i].getInstanceID().equals(Constant.cFedoraName)){
				ZopeInstanceType[] zopeArray = instArray[i].getZopeInstanceArray();
				for (int l = 0; l < zopeArray.length; l++){
					JournalType[] journalArray =  zopeArray[l].getJournalArray(); 
					for (int k = 0; k < journalArray.length; k++){
						if(label.equals(journalArray[k].getLabel())){
							oaiSetName = journalArray[k].getOaiJournalSetName();
						}
					}
				}				
			}
				
		}
		return oaiSetName;
	}

	/**
	 * <p><em>Title: Return the OaiSetSpec</em></p>
	 * <p>Description: Method returns the OAI-Set Specifier for a Journal, identified by its Label</p>
	 * 
	 * @param label <code>String</code> a label for a Journal
	 * @return <code>String</code> the Specifier of the OAISet assigned to a Journal
	 */
	public static String getOaiSetSpec(String label){
		String oaiSetSpec = null;
		
		
		for (int i = 0; i < instArray.length; i++){
			if (instArray[i].getInstanceID().equals(Constant.cFedoraName)){
				ZopeInstanceType[] zopeArray = instArray[i].getZopeInstanceArray();
				for (int l = 0; l < zopeArray.length; l++){
					JournalType[] journalArray =  zopeArray[l].getJournalArray(); 
					for (int k = 0; k < journalArray.length; k++){
						if(label.equals(journalArray[k].getLabel())){
							oaiSetSpec = journalArray[k].getOaiJournalSetSpec();
						}
					}
				}	
			}				
		}
		return "journal:" + oaiSetSpec;
	}

	public String getStylePDF(String aID){
		// Element is actually not implemented and not used by DiPP
		// Method can only return null
		return null;
	}

	public boolean hasWorkflowStep(String aID, String aWfStep){
		// Element is actually not implemented and not used by DiPP
		// Method can only return true
		return true;
	}

	/**
	 * <p><em>Title: Create a new ConfigFile Stub in Memory</em></p>
	 * <p>Description: </p>
	 * 
	 * @param instanceProp 
	 */
	public void createNewConfigFile(Properties instanceProp){
		mDoc = DippInstanceDocument.Factory.newInstance();
		dippInstance = mDoc.addNewDippInstance();
		this.createInstanceEntry(instanceProp);
	}
	
	/**
	 * <p><em>Title: Create and append a new Instance Stub</em></p>
	 * <p>Description: If not already exists, Method creates and appends a new Instance 
	 * to the DiPPConfiguration.xml. If declared, instance will be set active</p>
	 * 
	 * @param instanceProp 
	 */
	public void createInstanceEntry(Properties instanceProp){
		instArray = mDoc.getDippInstance().getInstanceArray();
		boolean exists = false;
		for(InstanceType inst: instArray){
			if(inst.getInstanceID().equals(instanceProp.getProperty("name"))){
				log.info("DiPP Instance " + inst.getInstanceID() + " already exists, skip writing new Instance");
				exists = true;
				
			}
		}
		
		if(!exists){
			instance = mDoc.getDippInstance().addNewInstance();
			instance.setInstanceID(instanceProp.getProperty("name"));
			RepositoryType repo = instance.addNewRepository();
			repo.setName(instanceProp.getProperty("name"));
			repo.setHost(instanceProp.getProperty("host"));
			repo.setPort(instanceProp.getProperty("port"));
			repo.setVersion(Integer.parseInt(instanceProp.getProperty("version")));
			repo.setUser(instanceProp.getProperty("user"));
			repo.setPassword(instanceProp.getProperty("password"));
			
			// maybe set this Instance active
			if(instanceProp.containsKey("activeInst")){
				mDoc.getDippInstance().setActiveInstance(instanceProp.getProperty("activeInst"));
			}
			saveConfig();
		}
	}

	/**
	 * <p><em>Title: Create an Entry for a Journal within the DiPPConfiguration.xml</em></p>
	 * <p>Description: Method creates an Entry for a Journal within the DiPPConfiguration.xml</p>
	 * 
	 * @param instName
	 * @param ploneInstName
	 * @param journalProp
	 * @return <code>boolean</code> true if method successfully generates a Journal entry  
	 */
	public boolean createJournalEntry(String instName, String zopeInstName, Properties journalProp){
		boolean success = false; 
		
		log.info("start creating a Journal entry");
		for(int i = 0; i < instArray.length; i++){
			if(instArray[i].getInstanceID().equals(instName)){
				ZopeInstanceType[] zopeArray = instArray[i].getZopeInstanceArray();
				for (int l = 0; l < zopeArray.length; l++){
					if(zopeArray[l].getName().equals(zopeInstName)){
						JournalType journal = zopeArray[l].addNewJournal();
						journal.setLabel(journalProp.getProperty("label"));
						journal.setName(journalProp.getProperty("name"));
						if(journalProp.containsKey("globUrl")){
							journal.setGlobalUrl(journalProp.getProperty("globUrl"));							
						}
						if(journalProp.containsKey("oaiSetName")){
							journal.setOaiJournalSetName(journalProp.getProperty("oaiSetName"));							
						}
						if(journalProp.containsKey("oaiSetSpec")){
							journal.setOaiJournalSetSpec(journalProp.getProperty("oaiSetSpec"));							
						}
						if(journalProp.containsKey("imageResize") && journalProp.getProperty("imageResize").equals("true")){
							journal.setImageResize(true);
						}
						if(!journalProp.containsKey("identUrl")){
							journal.setIdentifierUrl("http://" + Constant.cFedoraHost + ":" + zopeArray[l].getPort() + "/" + journal.getName() + "/DiPP/");
						}
						else if(journalProp.containsKey("identUrl")){
							journal.setIdentifierUrl(journalProp.getProperty("identUrl"));
						}
					success = true;
					}
				}
			}
		}
		saveConfig();
		return success;
	}

	/**
	 * <p><em>Title: Create an Zope Instance within the DiPPConfiguration.xml </em></p>
	 * <p>Description: </p>
	 * 
	 * @param instName
	 * @param zopeInstName
	 * @param version
	 * @return 
	 */
	public boolean createZopeInstance(String instName, String zopeInstName, String version){
		boolean exists = false;
		boolean success = false;

		for(int i = 0; i < instArray.length; i++){
			log.info("found Instance: " + instArray[i].getInstanceID());
			
			if(instArray[i].getInstanceID().equals(instName)){
				log.info("count Zope Instances: " + instArray[i].sizeOfZopeInstanceArray());
				for(ZopeInstanceType zopeInstance : instArray[i].getZopeInstanceArray()){
					if(zopeInstance.getName().equals(zopeInstName)){
						exists = true;
						log.info("Zope Instance already exists");
					}
				}
				if(!exists){
					ZopeInstanceType zopeInst = instArray[i].addNewZopeInstance();
					zopeInst.setName(zopeInstName);
					zopeInst.setVersion(version);
					//TODO get port from anywhere else
					zopeInst.setPort("8080");
				}
			}
		}
		log.debug("write zopeInstance");
		saveConfig();
		return success;
	}

	
	/**
	 * <p><em>Title: Write DiPPConfiguration.xml</em></p>
	 * <p>Description: Writes the actual XmlBeans Representation of DiPPConfiguration.xml
	 *  into the File</p>
	 *  
	 */
	private void saveConfig(){
		Map<String, String> nsMap = new Hashtable<String, String>();
		nsMap.put("http://dipp.nrw.de/DiPPConfiguration", "dipp");
		
		XmlOptions options = new XmlOptions();
		options.setSavePrettyPrint();
		options.setSaveSuggestedPrefixes(nsMap);
		
		log.info("save Configuration File as: " + Constant.cConfigFileDir + Constant.cConfigFileName );
		try{
			mDoc.save(new File(Constant.cConfigFileDir + Constant.cConfigFileName), options);
		}catch(Exception e){
			log.error(e);
		}			
	}
	
		
	/**
	 * <p><em>Title: Get the Properties of the main Fedora Repository Instance</em></p>
	 * <p>Description: Method gets the properties from the Fedora Instance marked as active in the 
	 * DiPPConfiguration File. Writes this into a Properties Object</p>
	 * 
	 * @return 
	 */
	public Properties getInstanceProperties(){
		Properties prop = new Properties();
		String active = mDoc.getDippInstance().getActiveInstance();
		FedoraInstance fedInst = new FedoraInstance(active);
		
//		fedInst.
		log.info(active);
		log.info(instArray.length);
		for(InstanceType readInstance : instArray){
			log.debug(readInstance.getInstanceID());
			if(readInstance.getInstanceID().equals(active)){
				prop.put("version", String.valueOf(readInstance.getRepository().getVersion()));
				prop.put("name", readInstance.getRepository().getName());
				prop.put("host", readInstance.getRepository().getHost());
				prop.put("port", readInstance.getRepository().getPort());
				//TODO: allow the next attributes to be null: 
				prop.put("user", readInstance.getRepository().getUser());
				prop.put("password", readInstance.getRepository().getPassword());
			}
		}
		
		return prop;
	}

	/**
	 * <p><em>Title: Get the Values of the main Fedora Repository Instance</em></p>
	 * <p>Description: Method gets the properties from the Fedora Instance marked as active in the 
	 * DiPPConfiguration File as FedoraInstance Object</p>
	 * 
	 * @return 
	 */
	public FedoraInstance getInstance(){
		String active = mDoc.getDippInstance().getActiveInstance();
		FedoraInstance fedInst = new FedoraInstance(active);		
		return fedInst;
	}

	/**
	 * <p><em>Title: Get the Values of the main Fedora Repository Instance</em></p>
	 * <p>Description: Method gets the properties from the Fedora Instance marked as active in the 
	 * DiPPConfiguration File as FedoraInstance Object</p>
	 * 
	 * @return 
	 */
	public FedoraInstance getInstance(String instanceName){
		FedoraInstance fedInst = new FedoraInstance(instanceName);		
		return fedInst;
	}

	/**
	 * <p><em>Title: Getter for the InstanceType Array</em></p>
	 * <p>Description: </p>
	 * 
	 * @return 
	 */
	public static InstanceType[] getInstArray(){
		return instArray;			
	}
		
	
}