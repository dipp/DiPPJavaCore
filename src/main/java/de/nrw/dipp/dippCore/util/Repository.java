package de.nrw.dipp.dippCore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Class Repository
 * 
 * <p><em>Title: Old (deprecated) Class for getting Repository Informations from the repository.xml</em></p>
 * <p>Description: In use only for these DiPP Instances that lacks DiPPCongiuration.xml</p>
 * 
 * @author Jochen Schirrwagen
 * creation date: 
 *
 */
public class Repository {

	private Document 	mDoc			= null;
	private Element		mRootElement	= null;
	
	public Repository(){
		mDoc = new Document();
		mRootElement = new Element("repository");
		mDoc.addContent(mRootElement);
	}

	/**
	 * Constructor
	 * 
	 * @param aXMLInputStream
	 */
	public Repository(InputStream aXMLInputStream){
		try{
			SAXBuilder builder = new SAXBuilder();
			mDoc = builder.build(aXMLInputStream);
		}catch(JDOMException jdomExc){
			jdomExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}


	/**
	 * <p><em>Title: Create a Repository Instance in dippConfiguration.xml</em></p>
	 * <p>Description:  Method actually NOT IN USE!!!</p>
	 * 
	 * @deprecated
	 * @param aName
	 * @param aHost
	 * @param aPort
	 * @param aVersion
	 * @param aUser
	 * @param aPassword 
	 */
	public void addRepositoryInstance(String aName, String aHost, String aPort, String aVersion, String aUser, String aPassword){
		Element repositoryElem = new Element("repository");
		mRootElement.addContent(repositoryElem);
		Element nameElem = new Element("name");
		nameElem.setText(aName);
		repositoryElem.addContent(nameElem);
		Element hostElem = new Element("host");
		hostElem.setText(aHost);
		repositoryElem.addContent(hostElem);
		Element portElem = new Element("port");
		portElem.setText(aPort);
		repositoryElem.addContent(portElem);
		Element versionElem = new Element("version");
		versionElem.setText(aVersion);
		repositoryElem.addContent(versionElem);
		Element userElem = new Element("user");
		userElem.setText(aUser);
		repositoryElem.addContent(userElem);
		Element passwordElem = new Element("password");
		passwordElem.setText(aPassword);
		repositoryElem.addContent(passwordElem);
	}
	
	/**
	 * <p><em>Title: Method to write a configuration file, not in use</em></p>
	 * <p>Description: </p>
	 * 
	 * @deprecated
	 * @param aFilename
	 * @throws IOException 
	 */
	public void persist(String aFilename)throws IOException{
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(mDoc, new FileOutputStream(aFilename));
	}
	
/*	public void createDocument(String aID, String aDescr, String aStyle)throws IOException{
		Document doc = new Document();
		Element config = new Element("config");
		doc.addContent(config);
		Element dippInstance = new Element("dippInstance");
		config.addContent(dippInstance);
		Element id = new Element("id");
		id.setText(aID);
		dippInstance.addContent(id);
		Element description = new Element("description");
		description.setText(aDescr);
		dippInstance.addContent(description);
		if (aStyle != null){
			Element configuration = new Element("configuration");
			configuration.setText(aStyle);
			dippInstance.addContent(configuration);
			configuration.setAttribute("type", "style");
			configuration.setAttribute("targetFormat", "pdf");
		}
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(doc, System.out);
	}
*/	
	
	/**
	 * <p><em>Title: Load informations about the repository into Running system</em></p>
	 * <p>Description: </p>
	 * 
	 * @return 
	 */
	public Properties getRepositoryProperties(){
		Properties props = new Properties();
		
		Iterator itInstances = mDoc.getRootElement().getChildren().iterator();
		while (itInstances.hasNext()){
			Element repositoryInstance = (Element)itInstances.next();
			String name = ((Element)repositoryInstance.getChildren("name").get(0)).getTextNormalize();
			props.setProperty("activeInst",	name);
			props.setProperty("name", 		name);
			props.setProperty("host", 		((Element)repositoryInstance.getChildren("host").get(0)).getTextNormalize());
			props.setProperty("port", 		((Element)repositoryInstance.getChildren("port").get(0)).getTextNormalize());
			props.setProperty("version",	((Element)repositoryInstance.getChildren("version").get(0)).getTextNormalize());
			props.setProperty("user", 		((Element)repositoryInstance.getChildren("user").get(0)).getTextNormalize());
			props.setProperty("password", 	((Element)repositoryInstance.getChildren("password").get(0)).getTextNormalize());
			writeRepData2DippConfiguration(props);
		}
		return props;
	}
	
	/**
	 * <p><em>Title: Load informations about the repository into Running system</em></p>
	 * <p>Description: </p>
	 * 
	 * @return 
	 */
	public Properties getFedoraInstance(){
		Properties props = new Properties();
		
		Iterator itInstances = mDoc.getRootElement().getChildren().iterator();
		while (itInstances.hasNext()){
			Element repositoryInstance = (Element)itInstances.next();
			String name = ((Element)repositoryInstance.getChildren("name").get(0)).getTextNormalize();
			props.setProperty("activeInst",	name);
			props.setProperty("name", 		name);
			props.setProperty("host", 		((Element)repositoryInstance.getChildren("host").get(0)).getTextNormalize());
			props.setProperty("port", 		((Element)repositoryInstance.getChildren("port").get(0)).getTextNormalize());
			props.setProperty("version",	((Element)repositoryInstance.getChildren("version").get(0)).getTextNormalize());
			props.setProperty("user", 		((Element)repositoryInstance.getChildren("user").get(0)).getTextNormalize());
			props.setProperty("password", 	((Element)repositoryInstance.getChildren("password").get(0)).getTextNormalize());
			writeRepData2DippConfiguration(props);
		}
		return props;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param props 
	 */
	public void writeRepData2DippConfiguration(Properties props){
		File confFile = new File(Constant.cConfigFileDir + Constant.cConfigFileName);
		
		if(!confFile.isFile()){
			System.out.println("found no ConfigFile, create new");
			//confFile = new File(Constant.cFallbackConfigFileDir + Constant.cConfigFileName);
		}
		// create a new DiPPConfiguration File
		Config nConf = new Config(confFile);
		nConf.createInstanceEntry(props);
		
		// write Journal Infos into Config file  
		Config test = new Config(Constant.class.getClassLoader().getResourceAsStream("dippConfiguration.xml"));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Repository c = new Repository();
/*		try{
			Repository c = new Repository(new FileInputStream("c:\\user\\jochen\\src\\dippFedora2\\src\\java\\repository.xml"));
			Properties props = c.getRepositoryProperties();
			System.out.println("host: " + props.getProperty("host"));
			System.out.println("port: " + props.getProperty("port"));
			System.out.println("version: " + props.getProperty("version"));
//			c.addRepositoryInstance("PythiaTest", "193.30.112.98", "9080", "1", "fedoraAdmin", "fssAug04");			
//			c.persist("c:\\user\\jochen\\src\\dippFedora2\\src\\java\\repository.xml");
			
		}catch(Exception e){
			e.printStackTrace();
		}
*/		
		System.out.println("Fedora Version in use: " + Constant.cFedoraVersion);
	}
	

}
