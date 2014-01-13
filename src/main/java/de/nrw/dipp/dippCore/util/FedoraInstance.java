/**
 * FedoraInstance.java - This file is part of the DiPP Project by hbz
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

import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.dipp.diPPConfiguration.DippInstanceDocument;
import de.nrw.dipp.diPPConfiguration.InstanceType;

/**
 * Class FedoraInstance
 * 
 * <p><em>Title: A Class that holds all informations required to access a Fedora Repository </em></p>
 * <p>Description: This class holds all informations required to access a Fedora Repository Instance.
 * Instance Informations will be stored in Objects of this class to gain a more flexible treatment of 
 * Fedora Instances at runtime. Subscription to a Fedora Instance can be changed during runtime 
 * for example allowing to store the same Fedora Object in different Repositories. </p>
 * 
 *   
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 20.01.2010
 *
 */
public class FedoraInstance {


	private static Properties instanceProp = null;
	private InstanceType[] instArray = Config.getInstArray();
	//private static DippInstanceDocument mDoc = null;

	// Properties as Strings and ints
	private int FedoraVersion = 0;
	private String	FedoraName = null;
	private String FedoraHost = null;
	private int FedoraPort	= 0;
	private String	FedoraUser = null;
	private String	FedoraPassword = null;

	
	// Initiate Logger for FedoraInstanceProperties
	private static Logger log = Logger
			.getLogger(FedoraInstance.class);

	
	public FedoraInstance(String instanceName){
		this.setInstanceProperties(instanceName);
	}
	
	/**
	 * <p><em>Title: Sets the Instance Properties from xmlBeans representation of the DiPPConfiguration file</em></p>
	 * <p>Description: </p>
	 * 
	 * @param instanceName 
	 */
	public void setInstanceProperties(String instanceName){
		instanceProp = new Properties();
		String active = instanceName;
		log.debug(active);
		log.debug(instArray.length);
		for(InstanceType readInstance : instArray){
			log.debug(readInstance.getInstanceID());
			if(readInstance.getInstanceID().equals(active)){
				instanceProp.put("version", String.valueOf(readInstance.getRepository().getVersion()));
				instanceProp.put("name", readInstance.getRepository().getName());
				instanceProp.put("host", readInstance.getRepository().getHost());
				instanceProp.put("port", readInstance.getRepository().getPort());
				//TODO: allow the next attributes to be null: 
				instanceProp.put("user", readInstance.getRepository().getUser());
				instanceProp.put("password", readInstance.getRepository().getPassword());
			}
		}
		FedoraVersion = Integer.parseInt(instanceProp.getProperty("version"));
		FedoraName = instanceProp.getProperty("name");
		FedoraHost = instanceProp.getProperty("host");
		FedoraPort = Integer.parseInt(instanceProp.getProperty("port"));
		FedoraUser = instanceProp.getProperty("user");
		FedoraPassword = instanceProp.getProperty("password");
		
	}


	/**
	 * @return the fedoraVersion
	 */
	public int getFedoraVersion() {
		return FedoraVersion;
	}


	/**
	 * @return the fedoraName
	 */
	public String getFedoraName() {
		return FedoraName;
	}


	/**
	 * @return the fedoraHost
	 */
	public String getFedoraHost() {
		return FedoraHost;
	}


	/**
	 * @return the fedoraPort
	 */
	public int getFedoraPort() {
		return FedoraPort;
	}


	/**
	 * @return the fedoraUser
	 */
	public String getFedoraUser() {
		return FedoraUser;
	}


	/**
	 * @return the fedoraPassword
	 */
	public String getFedoraPassword() {
		return FedoraPassword;
	}

	
}
