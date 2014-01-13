/**
 * FedoraVersion.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.repository;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class FedoraVersion
 * 
 * <p><em>Title: A Class for binding DiPP to different Fedora Versions</em></p>
 * <p>Description: Class evaluates and represents all Informations required to bind DiPP to 
 * the recently used Fedora Repository Version. Class provides a Factory for generating 
 * FedoraVersion Objects at runtime and simplify maintenance.</p>
 * <p>In Future this will be the only Class from the dippCore Project that have to be 
 * changed to integrate new Fedora Versions. All other changes should be done hopefully in 
 * dippfedora[Version] then.</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 17.03.2009
 *
 */
public class FedoraVersion {

	// Initiate Logger for FedoraVersionFactory
	private static Logger log = Logger.getLogger(FedoraVersion.class);

	private String versionAsString;	// is not in use at the moment, but in future?
	private int versionAsInt;
	private String fedoraXmlFormat;
	private String AdaptorClassName;
	private String AdaptorAccessClassName;
	private String AdaptorManagementClassName;
	
	// Generate XmlBeans representation for independence of Fedora Version
	private XmlObject fedoraXmlDoc = null;
	private FedoraXMLObject fedoraXmlObject = null;


	/**
	 * Private Constructor: Objects can by initiated from Factory Class solely 
	 */
	private FedoraVersion(){

	}
	
	public static class Factory {
			
		public static FedoraVersion newInstance(){
			FedoraVersion fedoraVersion = new FedoraVersion();
			fedoraVersion.versionAsInt = Constant.cFedoraVersion;

			fedoraVersion.versionAsString = "" + Constant.cFedoraVersion;

			//Class fedoraServices;
			try{
				switch(Constant.cFedoraVersion){
					case 1:
						fedoraVersion.fedoraXmlFormat = "metslikefedora1";
						fedoraVersion.AdaptorAccessClassName = "de.nrw.dipp.dippfedora1.repository.FedoraServicesAccessImpl_1";
						fedoraVersion.AdaptorManagementClassName = "de.nrw.dipp.dippfedora1.repository.FedoraServicesManagementImpl_1";
						// Next version maybe replaces FedoraAccess and FedoraManagement Classes? 
						//fedoraServices = Class.forName("de.nrw.dipp.repository.FedoraServicesAccessImpl_1");
						fedoraVersion.fedoraXmlObject = new MetsXmlObject();
						break;
					case 2:
						fedoraVersion.fedoraXmlFormat = "foxml1.0";
						fedoraVersion.AdaptorAccessClassName = "de.nrw.dipp.dippfedora2.repository.FedoraServicesAccessImpl_2";
						fedoraVersion.AdaptorManagementClassName = "de.nrw.dipp.dippfedora2.repository.FedoraServicesManagementImpl_2";
						// Next version maybe replaces FedoraAccess and FedoraManagement Classes? 
						//fedoraServices = Class.forName("de.nrw.dipp.repository.FedoraServicesAccessImpl_2");
						fedoraVersion.fedoraXmlObject = new FoXml10Object();
						break;
					case 3:
						fedoraVersion.fedoraXmlFormat = "info:fedora/fedora-system:FOXML-1.1";
						fedoraVersion.AdaptorClassName = "de.nrw.dipp.dippfedora3.repository.WsAdaptor.FedoraServicesImpl_3";
						fedoraVersion.AdaptorAccessClassName = "de.nrw.dipp.dippfedora3.repository.WsAdaptor.FedoraServicesImpl_3";
						fedoraVersion.AdaptorManagementClassName = "de.nrw.dipp.dippfedora3.repository.WsAdaptor.FedoraServicesImpl_3";
						// Next version maybe replaces FedoraAccess and FedoraManagement Classes? 
						//fedoraServices = Class.forName("de.nrw.dipp.repository.WsAdaptor.FedoraServicesImpl_3");
						fedoraVersion.fedoraXmlObject = new FoXml11Object();
						break;
					// add code for forthcoming versions here...
				}
			}catch(Exception classExc){
				log.error("found no appropriate class for Fedora Version " 
						+ Constant.cFedoraVersion + "Exception is: \n" + classExc);
				classExc.printStackTrace();
			}
			return fedoraVersion;			
		}
	}



	/**
	 * @return the versionAsString
	 */
	public String getVersionAsString() {
		return versionAsString;
	}


	/**
	 * @return the versionAsInt
	 */
	public int getVersionAsInt() {
		return versionAsInt;
	}


	/**
	 * @return the foXMLFormat
	 */
	public String getFedoraXmlFormat() {
		return fedoraXmlFormat;
	}


	/**
	 * @return the adaptorClassName
	 */
	public String getAdaptorClassName() {
		return AdaptorClassName;
	}


	/**
	 * @return the adaptorAccessClassName
	 */
	public String getAdaptorAccessClassName() {
		return AdaptorAccessClassName;
	}


	/**
	 * @return the adaptorManagementClassName
	 */
	public String getAdaptorManagementClassName() {
		return AdaptorManagementClassName;
	}


	/**
	 * @return the fedoraXmlDoc
	 */
	public XmlObject getFedoraXmlDoc() {
		return fedoraXmlDoc;
	}


	/**
	 * @return the xmlMethods
	 */
	public FedoraXMLObject getFedoraXmlObject() {
		return fedoraXmlObject;
	}

}
