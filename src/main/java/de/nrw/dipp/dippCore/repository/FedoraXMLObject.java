/**
 * FedoraXMLObject.java - This file is part of the DiPP Project by hbz
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

import java.rmi.RemoteException;
import org.apache.xmlbeans.XmlObject;

/**
 * Class FedoraXMLObject
 * 
 * <p><em>Title: Interface for representing Fedora XML Objects as XmlBeans (since Version2: FoXML)</em></p>
 * <p>Description:  An Interface that provides all necessary Methods for creation, 
 * modification,ingest and output of a Fedora XML Object. It has to be implemented for 
 * all existing and forthcoming Versions of Fedora for hiding all the changes in 
 * Fedora XML Objects to the DOManagement class.</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 23.03.2009
 *
 */
public interface FedoraXMLObject {

	

	/**
	 * <p>Method creates a new Fedora Document as a xmlBeans based representation.</p> 
	 * hint: Method is called from the DOManagement init() Method solely</p>
	 * 
	 * @param aPIDNamespace  <code>String</code> The Namespace Prefix of the PID. Reflects 
	 * the ContentModel the newly generated Object will belongs to. 
	 * @param aPID <code>String</code> PID of the Fedora XML Object created, 
	 * since Version 2: if this is null a new PID will be generated automatically when 
	 * Object is ingested  
	 * @param aLabel <code>String</code> a Label representing the DiPP:eJournal object 
	 * to which the XML Object is dedicated  
	 * @param aCModel <code>String</code> the Content Model to which the object is dedicated
	 * @return <code>String</code> the PID of the created Object, in most cases it's the PID 
	 * declared in the aPid parameter   
	 * @throws RemoteException
	 */
	public String createXMLDocument(String aPIDNamespace, String aPID, String aLabel, String aCModel) throws RemoteException;

	/**
	 * <p>Method adds a Datastream to an Instance of XML Object for Fedora. Does 
	 * not write an Object to Fedora Repository. It is used to add all required 
	 * Datastreams to a new xmlBeans based representation of Fedora Object.</p> 
	 * 
	 * @param aDSId <code>String</code> the datastream Identifier
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the 
	 * type of Datastream   
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aXMLObject <code>XmlObject</code> the Datastream content as XmlObject (xmlbeans)
	 */
	public void addXMLDatastream(String aDSId, String aLabel, String aMimeType, String aVersionId, XmlObject aXMLObject, String aDSType);

	/**
	 * <p>Method gets the PID of a fedora Object representation (either MetsObj or FoXML 
	 * representation)</p>
	 * 
	 * @return <code>String</code> the PID of Fedora Object represented by the xmlBeans Instance 
	 */
	public String getPID();

	/**
	 * <p>Method returns the Fedora Object xmlBeans representation as String</p>
	 *  
	 * @return <code>String</code> the Fedora Object as String
	 */
	public String getXmlText();

	//public String toXML();

}
