//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  FoXml11Object.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"
/**
 * FoXml11Object.java - This file is part of the DiPP Project by hbz
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

//..begin "Imports"
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import fedora.fedoraSystemDef.foxml1.DigitalObjectDocument;
//..end "Imports"


/**
 * Class FoXml11Object
 * 
 * <p><em>Title: Instances of this class represent Fedora Version 3 Objects (according foxml-1.1.xsd) 
 * as XmlBeans Objects. </em></p>
 * <p>Description:  Class creates and manage a representation of a Fedora Object as XmLBeans Object. 
 * It wraps the different behaviors and methods of code auto generated by XmlBeans for different 
 * Fedora Versions.</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 25.03.2009
 *
 */
public class FoXml11Object implements FedoraXMLObject {

	  	// Initiate Logger for FoXmlObject
	private static Logger log = Logger.getLogger(FoXml11Object.class);
	
	private DigitalObjectDocument foDoc = null;
	private fedora.fedoraSystemDef.foxml1.DigitalObjectDocument.DigitalObject foObj = null;
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#addXMLDatastream(java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.apache.xmlbeans.XmlObject, java.lang.String)
	 */
	@Override
	public void addXMLDatastream(String id, String label, String mimeType,
			String versionId, XmlObject object, String type) {
		//type not used since Fedora 2 and also not used in DiPP
		fedora.fedoraSystemDef.foxml1.DatastreamType datastream = foObj.addNewDatastream();
		datastream.setCONTROLGROUP(fedora.fedoraSystemDef.foxml1.DatastreamType.CONTROLGROUP.X);
		datastream.setID(id);
		fedora.fedoraSystemDef.foxml1.DatastreamVersionType dsVersion = datastream.addNewDatastreamVersion();
		dsVersion.setLABEL(label);
		dsVersion.setMIMETYPE(mimeType);
		dsVersion.setID(versionId);
		fedora.fedoraSystemDef.foxml1.XmlContentType xmlContent = dsVersion.addNewXmlContent();
		xmlContent.set(object);
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#createXMLDocument(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createXMLDocument(String namespace, String apid,
			String label, String model) throws RemoteException {
		foDoc = fedora.fedoraSystemDef.foxml1.DigitalObjectDocument.Factory.newInstance();
		foObj = foDoc.addNewDigitalObject();
		
		foObj.setPID(apid);
		foObj.setVERSION(fedora.fedoraSystemDef.foxml1.DigitalObjectType.VERSION.X_1_1);
		fedora.fedoraSystemDef.foxml1.ObjectPropertiesType objProps = foObj.addNewObjectProperties();
		
		// Set required foxml properties
		fedora.fedoraSystemDef.foxml1.PropertyType prop = objProps.addNewProperty();
		prop.setNAME(fedora.fedoraSystemDef.foxml1.PropertyType.NAME.INFO_FEDORA_FEDORA_SYSTEM_DEF_MODEL_STATE);
		prop.setVALUE("Inactive");// mmh, is Inactive really the desired state?
		prop = objProps.addNewProperty();
		prop.setNAME(fedora.fedoraSystemDef.foxml1.PropertyType.NAME.INFO_FEDORA_FEDORA_SYSTEM_DEF_MODEL_LABEL);
		prop.setVALUE(label);
		prop = objProps.addNewProperty();
		prop.setNAME(fedora.fedoraSystemDef.foxml1.PropertyType.NAME.INFO_FEDORA_FEDORA_SYSTEM_DEF_MODEL_CREATED_DATE);
		prop.setVALUE(getDateTime(Calendar.getInstance()));
		prop = objProps.addNewProperty();
		prop.setNAME(fedora.fedoraSystemDef.foxml1.PropertyType.NAME.INFO_FEDORA_FEDORA_SYSTEM_DEF_VIEW_LAST_MODIFIED_DATE);
		prop.setVALUE(getDateTime(Calendar.getInstance()));
		return apid;
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#getPID()
	 */
	@Override
	public String getPID() {
		return foObj.getPID();
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraXMLObject#ingestXMLObject()
	 */
	@Override
	public String getXmlText() {
		return foDoc.xmlText();
	}
	
	private static String getDateTime(Calendar aCal){
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		return dateTimeFormat.format(aCal.getTime());
	}
	
	

}
