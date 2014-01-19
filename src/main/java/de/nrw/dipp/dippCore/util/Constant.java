package de.nrw.dipp.dippCore.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import java.io.*;

// Qa: Classes for log4j Logger  
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;





/**
 * <p>Title: Constant.java</p>
 * <p>Defines constants for DiPP-Services<br />
 *  which includes fedora access informations, contentmodel descriptions, <br />
 *  configuration file access, upcast licence location,... </p>
 *
 * -----------------------------------------------------------------------------
 *
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: Constant.java,v 1.3 2006/11/06 11:32:01 dippadm Exp $
 */
public class Constant {
	
	//private static final Properties repProps = (new Repository(Constant.class.getClassLoader().getResourceAsStream("repository.xml"))).getRepositoryProperties();

	//private static 		File confFile					= getConfFileLocation();
	
	public static final String cInfoAuthor				= "Jochen Schirrwagen (schirrwagen@hbz-nrw.de),\n *     Andres Quast (quast@hbz-nrw.de),\n *     Hermann Kronenberg (kronenberg@hbz-nrw.de)";
	public static final String cInfoVersion				= "Version 1";
	public static final String cInfoSubVersion			= ".2";
	public static final String cInfoCopyRight			= "(c)2009-2011 by Digital Peer Publishing (www.dipp.nrw.de)";

	public static final String	cConfigFileDir 			= ConfDirLocator.getConfFileLocation();

	/*private static 		Properties repProps 			= getInstanceProperties();			

	public static final int 	cFedoraVersion			= Integer.parseInt(repProps.getProperty("version")); // V1->Api1, V2->Api2
	public static final String 	cFedoraName				= repProps.getProperty("name");
	public static final String 	cFedoraHost				= repProps.getProperty("host");
	public static final int 	    cFedoraPort				= Integer.parseInt(repProps.getProperty("port"));
	public static final String 	cFedoraUser				= repProps.getProperty("user");
	public static final String 	cFedoraPassword			= repProps.getProperty("password");
	*/

	public static final int 		cFedoraVersion			= getInstanceProperties().getFedoraVersion(); // V1->Api1, V2->Api2
	public static final String 	cFedoraName				= getInstanceProperties().getFedoraName();
	public static final String 	cFedoraHost				= getInstanceProperties().getFedoraHost();
	public static final int 	    cFedoraPort				= getInstanceProperties().getFedoraPort();
	public static final String 	cFedoraUser				= getInstanceProperties().getFedoraUser();
	public static final String 	cFedoraPassword			= getInstanceProperties().getFedoraPassword();
	
	public static final String 	cPloneUser				= "dippadm"; // move it in the dipp-conf file
	public static final String	cPlonePassword			= "z23t5ng";
	
	public static final String	cContentModelJournal	= "DiPP:eJournal";
	public static final String	cContentModelContainer	= "DiPP:container";
	public static final String	cContentModelArticle	= "DiPP:article";
	public static final String	cContentModelData		= "DiPP:data";
	public static final String	cContentModelOaiSet		= "oai:Set";
	
		
	public static final String	cContentTypeNative		= "native";
	public static final String	cContentTypeMultimedia	= "multimedia";
	public static final String	cContentTypeXML			= "xml";
	public static final String	cContentTypeHTML		= "html";
	public static final String	cContentTypePDF			= "pdf";
	
	// TODO make this better: If new ContentTypes are declared they should be available from list
	public static List<String>  cContentTypes 
			= new Vector<String>(Arrays.asList(new String[]{cContentTypeNative, cContentTypeMultimedia,
					cContentTypeXML, cContentTypeHTML, cContentTypePDF}));	

	public static final String	cFedoraMetadataClassDescriptive	= "descriptive";
	public static final String	cFedoraMetadataClassTechnical	= "technical";
	
	
	public static final String	cLabelTestJournal		= "1";
	
	public static final String	cNamespaceURN			= "urn:nbn:de:0009-";
	
	public static final String	cPrefixFilenameArticle		= "dippArticle";
	
	public static final String	cMetsTypeFedora				= "FedoraObject";
	public static final String	cFedoraStatusActive			= "A";
	public static final String	cFedoraStatusInactive		= "I";
	public static final String 	cFedoraDatastreamControlGroupInternal	= "X";
	public static final String 	cFedoraDatastreamControlGroupExternal	= "E";
	public static final String 	cFedoraDatastreamControlGroupManaged	= "M";
	public static final String	cFedoraObjectFormatMets		= "metslikefedora1"; // for Fedora Version 2 only!
	public static final String	cFedoraObjectFormatFoXML	= "foxml1.0"; // for Fedora Version 2 only!
	public static final String	cFedoraObjectFormatFoXML11	= "info:fedora/fedora-system:FOXML-1.1"; // for Fedora Version 3 only!
	
	public static final String	cCoverageDippContent		= "dipp-content";
	public static final String	cCoverageTestContent		= "test-content";
	public static final String	cPublisherDippPublished		= "dipp-published";
	public static final String	cPublisherDippUnPublished	= "dipp-unpublished";
	public static final String	cDecsriptionDippConverted	= "dipp-converted";
	public static final String	cDecsriptionDippFailedConversion	= "dipp-failed-conversion";
	public static final String	cDecsriptionDippNoConversion		= "dipp-no-conversion";
	public static final String	cCreatorPerson				= "person";
	// TODO read pidNamespace from xml configuration
	public static final String	cPIDNamespaceContent		= "content";
	public static final String	cPIDNamespaceDipp			= "dipp";
	public static final String	cPIDNamespaceTest			= "test";
	public static final String	cPIDNamespaceTemp			= "temp";
	public static final String	cPIDNamespaceDipa			= "dipa";
	public static final String	cPIDNamespaceDemo			= "demo";
	public static final String	cPIDNamespaceHBZ			= "hbz";
	public static final String	cPIDNamespaceOai			= "oai";
	
	public static final String	cTestJournalName			= "Dipptestjournal";
	public static final String	cStorageTypePermanent		= "permanent";
	public static final String	cStorageTypeTemporary		= "temporary";
	
	public static final String	cDSidRELS_Ext				= "RELS-EXT";
	public static final String	cDSidQDC					= "QDC";
	public static final String	cDSidDC						= "DC";
	public static final String	cDSidOAI_EPICUR				= "oai_epicur";
	public static final String	cDSidOAI_DC					= "oai_dc";
	//Qa: constant for doaj Datastream not in use at the moment: 
	public static final String	cDSidOAI_DOAJ				= "DoajXml";
	public static final String	cDSidDIPP_ADM				= "DiPPAdm";
	public static final String	cDSidDIPP_EXT				= "DiPPExt";
	
	
	public static final String 	cDefaultFilename			= "dippArticle";
	
	public static final String 	cTitleAbbreviation			= "abbrev";
	
	public static final String 	cExternalScripts 			= "perlscripts.properties"; // "externalscripts.properties"; /* former perlscripts.properties */
	public static final String	cLicensePathUpcast			= "/license/upcast.license";

	public static final String	cFallbackConfigFileDir 		= "/conf/";
	public static final String	cConfigFileName				= "DiPPConfiguration.xml";
	private static 		String	cAbsolutPath				= "/files/share/tomcat/webapps/dipp3"; //for Alkyoneus
	private static 		String	cUpcastWorkDirPath			= "/files/share/upcastWork/convert/"; //for Alkyoneus
	
	public static final String cDocBookIdentifierClassURN	= "uri";
	public static final String cDocBookIdentifierClassDOI	= "doi";
	
	public static final String cWfStepURN					= "urn";
	public static final int cResizeLimit					= 500;
	
	// Configuration should be changeable during runtime, unfortunately this means it will be created each time it is called?
	public static Config cConfiguration	= new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName));
	//public static final Config	cConfiguration	= new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName));
	//public static final Config	cConfiguration	= new Config(Constant.class.getClassLoader().getResourceAsStream("dippConfiguration.xml"));
	//public static final Config	cConfiguration	= new Config(new File("dippConfiguration.xml"));
	
	// Initialize logger object 
	private static Logger log = null;
	
	public static String getAbsolutPath() {
		return cAbsolutPath;
	}
	public static void setAbsolutPath(String absolutPath) {
		/*
		 * TODO make sure the path exist otherwise create it
		 */
		cAbsolutPath = absolutPath;
	}
	
	public static String getcUpcastWorkDirPath() {
		return cUpcastWorkDirPath;
	}
	/**
	 *  Method for initiate Logging System which include Logger 
	 *  Configuration from log4j.properties 
	 *  @author Andres Quast 
	 */
	public static void initLog() {
		
		File logConfiguration = new File(cConfigFileDir + "log4j.properties");		
		if (logConfiguration.isFile()) {
			try {
				PropertyConfigurator.configure(logConfiguration.getAbsolutePath());
				System.out.println("read log4j-configuration file at: " + logConfiguration.getAbsolutePath());
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		else{
			System.out.println("cannot read log4j-configuration file at: " + cConfigFileDir + "log4j.properties");
		}
		log = Logger.getLogger(Constant.class);
		log.info("Logging System activated,");
	}
	
	/**
	 * <p><em>Title: Read into system the Properties of a Fedora Instance</em></p>
	 * <p>Description: Method searches for a Configuration File and loads Fedora Instance configurations into the
	 * running system</p>
	 * 
	 * @return repProp a Properties Object containing necessary informations about the 
	 * Fedora Repository actually in use as active repository
	 */
	private static FedoraInstance getInstanceProperties(){
		
		Properties repProp = new Properties();
		FedoraInstance fedoraInst = null;
		System.out.println(Constant.cConfigFileDir);
		if(new File(Constant.cConfigFileDir + Constant.cConfigFileName).isFile()){
			System.out.println("Reading DiPP Configuration from: " + Constant.cConfigFileDir + Constant.cConfigFileName);
			//repProp = (new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName)).getInstanceProperties());
			fedoraInst = (new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName)).getInstance());
		}else{
			System.out.println("Reading DiPP Configuration from: repository.xml");
			repProp = (new Repository(Constant.class.getClassLoader().getResourceAsStream("repository.xml"))).getRepositoryProperties();
			fedoraInst = (new Config(new File(Constant.cConfigFileDir + Constant.cConfigFileName)).getInstance());
			System.out.println(fedoraInst.getFedoraName());
		}
		System.out.println(fedoraInst.getFedoraVersion());
		//return repProp;
		return fedoraInst;
	}
		
}
