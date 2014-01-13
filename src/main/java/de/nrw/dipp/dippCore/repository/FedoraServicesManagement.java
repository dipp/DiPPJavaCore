package de.nrw.dipp.dippCore.repository;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * <p><b>Title:</b> FedoraServicesManagement.java</p>
 * <p><b>Description:</b> An interface that defines all API-M relevant methods that have 
 * to be implemented in a Fedora Web Service Adaptor (API-M) for interoperability with 
 * DOManagement and ServiceManagement.</p>
 * 
 * <p>Interface deals with the problem that Fedora tends to change their Web Services 
 * from Version to Version. It will be the Structure for creating new Web Service Adaptors</p>
 * <p>Supported Web Services descriptions are</p>
 * <ul>
 * <li><a target="_blank" href="http://www.fedora.info/definitions/1/0/api/Fedora-API-M.html">Fedora Version 1-2</a></li>
 * <li><a target="_blank" href="http://fedora-commons.org/confluence/display/FCR30/API-M#API-M-WSDL">Fedora Version 2-3</a></li>
 * </ul>
 *   
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
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de,
 * @author Andres Quast (quast@hbz-nrw.de)
 * @version 
 */
public interface FedoraServicesManagement {

	/**
	 * <p>Method return an Information Set about the 
	 * Fedora Repository returned as String</p>
	 * @return <code>String</code> Fedora Repository description
	 */
	public String identify();
	
	/**
	 * <p>Method implements the ingest of a new Object in Fedora repository. Object to ingest is a byte stream</p>
	 * <p><em>Hint: Method directly reflects WS Operation ingest(...) from Fedora Versions 2, 3</em></p>
	 * @param aXMLObject <code>byte[]</code> a byte stream that represents the FOXML object to be ingested 
	 * @param aFormat <code>String</code>  Format-Identifier that determines of which fedora-xml format the Object 
	 * stream is:</p>
	 * <ul>
	 * <li>metslikefedora1 - Fedora 1 -2 legacy format</li>
	 * <li>foxml1.0 - Fedora 2 -3 currently used format</li>
	 * </ul>   
	 * @param aLogMessage <code>String</code> a Log message which is stored in Fedora
	 * @return <code>String</code> the PID of the ingested Object
	 * @throws RemoteException
	 */
	public String ingestObject(byte[] aXMLObject, String aFormat, String aLogMessage)throws RemoteException;

	/**
	 * <p>Method adds a Datastream to an existing Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be added
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aAltIDs <code>String[]</code> additional identifier(s) which can be supplied to the Datastream
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aIsVersionable <code>boolean</code> declares if Datastream is versionable 
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aFormatURI <code>String</code> ? optional format URI of the datastream
	 * @param aByteArray <code>byte[]</code> the Datastream itself as Array of byte
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aMetadataClass <code>String</code>
	 * @param aMetadataType <code>String</code>
	 * @param aControlgroup <code>String</code>  determines which physical relation to Fedoras Object 
	 * and Datastream should be made: 
	 * <ul>
	 * <li>X - Inline XML</li>
	 * <li>M - Managed</li>
	 * <li>R - Redirect</li>
	 * <li>E - External Referenced</li>
	 * </ul>
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @return <code>String</code> the PID of the changed Object?
	 */
	public String addDatastream(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aByteArray,  String aLogMsg, String aMetadataClass, String aMetadataType, String aControlgroup, String aChecksumType, String aChecksum);
//	public String addDatastream(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aByteArray,  String aLogMsg, String aMetadataClass, String aMetadataType, String aControlgroup);
	
	/**
	 * <p>Method adds a Datastream to an existing Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be added
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aFileName <code>String</code>  the name of the file to be applied as datastream.
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aControlgroup <code>String</code>  determines which physical relation to Fedoras Object 
	 * and Datastream should be made: 
	 * <ul>
	 * <li>X - Inline XML</li>
	 * <li>M - Managed</li>
	 * <li>R - Redirect</li>
	 * <li>E - External Referenced</li>
	 * </ul>
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @return <code>String</code> the PID of the changed Object?
	 */
	public String addDatastream(String aPID, String aDsID, String aFileName, String aMimeType, String aLabel, String aControlgroup, String aChecksumType, String aChecksum);
	
	/**
	 * <p>Method adds a Datastream to an existing Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be added
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aInputStream <code>InputStream</code>  an InputStream (java.io.InputStream) to be applied as datastream.
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aControlgroup <code>String</code>  determines which physical relation to Fedoras Object 
	 * and Datastream should be made: 
	 * <ul>
	 * <li>X - Inline XML</li>
	 * <li>M - Managed</li>
	 * <li>R - Redirect</li>
	 * <li>E - External Referenced</li>
	 * </ul>
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @return <code>String</code> the PID of the changed Object?
	 */
	public String addDatastream(String aPID, String aDsID, InputStream aInputStream, String aMimeType, String aLabel, String aControlgroup, String aChecksumType, String aChecksum);
//	public String addDatastream(String aPID, byte[] aByteArray, String aMimeType, String aLabel);
//	public String addDatastream_allParam(String aPID, String aLabel, String aMimeType, byte[] aByteArray, String aControlGroup, String aMDClass, String aMDType, String aState);
//  public InputStream getDatastreamContent(String aPID, String aDatastreamID)throws RemoteException, IOException;
	
	/**
	 * <p>Method modify existing Datastream within a Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aAltIDs <code>String[]</code> additional identifier(s) which can be supplied to the Datastream
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aIsVersionable <code>boolean</code> declares if Datastream is versionable 
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aFormatURI <code>String</code> ? optional format URI of the datastream
	 * @param aDsContent <code>byte[]</code> the replacement datastream as Array of byte
	 * @param aDsState <code>String</code> if not null or "" (?) changes State of Datastream:
	 * <ul>
	 * <li>A - Active</li>
	 * <li>I - Inactive</li>
	 * <li>D - Deleted</li>
	 * </ul>
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aMetadataClass <code>String</code>
	 * @param aMetadataType <code>String</code>
	 * @param aControlgroup <code>String</code>  determines which physical relation to Fedoras Object 
	 * and Datastream should be made: 
	 * <ul>
	 * <li>X - Inline XML</li>
	 * <li>M - Managed</li>
	 * <li>R - Redirect</li>
	 * <li>E - External Referenced</li>
	 * </ul>
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @param aForce <code>boolean</code> if true update will be executed regardless of breaking dependencies 
	 * @throws RemoteException
	 */
	public void modifyDatastreamContent(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aDsContent, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce) throws RemoteException;
	
	
	/**
	 * <p>Method modify existing Datastream within a Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aAltIDs <code>String[]</code> additional identifier(s) which can be supplied to the Datastream
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aIsVersionable <code>boolean</code> declares if Datastream is versionable 
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aFormatURI <code>String</code> ? optional format URI of the datastream 
	 * @param aDsContent <code>byte[]</code> the replacement datastream as Array of byte
	 * @param aDsState <code>String</code> if not null or "" (?) changes State of Datastream:
	 * <ul>
	 * <li>A - Active</li>
	 * <li>I - Inactive</li>
	 * <li>D - Deleted</li>
	 * </ul>
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @param aForce <code>boolean</code> if true update will be executed regardless of breaking dependencies 
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void modifyDatastreamByReference(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aDsContent, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce) throws RemoteException, IOException;

	/**
	 * <p>Method modify existing Datastream within a Fedora Object</p>
	 * <p><em>Hint (Qa): This Method is introduced with the Fedora Version 3 Adaptor. 
	 * It was implemented to provide a method that directly reflects the 
	 * modifyDatastreamByRefenrence(...) Operation of API-M. This should allow 
	 * the deletion of Upload Class calls within the Adaptor itself.</em></p>
	 *  
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be added 
	 * @param aAltIDs <code>String[]</code> additional identifier(s) which can be supplied to the Datastream
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aFormatURI <code>String</code> ? optional format URI of the datastream 
	 * @param aLocation <code>String</code> the location (URL) where the Datastream can be found as File 
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aForce <code>boolean</code> if true update will be executed regardless of breaking dependencies 
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void modifyDatastreamByReference(String aPID, String aDsID, String[] aAltIDs, String aLabel, String aMimeType, String aFormatURI, String aLocation, String aChecksumType, String aChecksum, String aLogMsg, boolean aForce) throws RemoteException, IOException;

	/**
	 * <p>Method modify existing Datastream within a Fedora Object</p>
	 * <p><em>Hint: Method does not directly reflects a WS Operation from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object to which the Datastream should be modified
	 * @param aDsID <code>String</code> Identifier of Datastream that will be modified 
	 * @param aAltIDs <code>String[]</code> additional identifier(s) which can be supplied to the Datastream
	 * @param aLabel <code>String</code> a Label for the Datastream, often describes the type of Datastream   
	 * @param aIsVersionable <code>boolean</code> declares if Datastream is versionable 
	 * @param aMimeType <code>String</code> the MimeType of Datastream 
	 * @param aFormatURI <code>String</code> ? optional format URI of the datastream 
	 * @param aInputStream <code>InputStream</code>  an InputStream (java.io.InputStream) to be applied as datastream.
	 * @param aDsState <code>String</code> if not null or "" (?) changes State of Datastream:
	 * <ul>
	 * <li>A - Active</li>
	 * <li>I - Inactive</li>
	 * <li>D - Deleted</li>
	 * </ul>
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aChecksumType <code>String</code> if other then default Checksum generation should be used: which?
	 * @param aChecksum <code>String</code> if other then automatic generated Checksum should be used 
	 * it has to be applied here  
	 * @param aForce <code>boolean</code> if true update will be executed regardless of breaking dependencies 
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void modifyDatastreamByReference(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, InputStream aInputStream, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce)throws RemoteException, IOException;

	/**
	 * <p>Method returns a table of all existing Datastreams within an Fedora Object ?</p>
	 * <p><em>Hint: Method does not directly reflect a WS Operation from Fedora Versions 2, 3.</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object which Datastreams are requested
	 * @return <code>HashTable</code> a table of all existing Datastreams within an Fedora Object ? 
	 * @throws RemoteException
	 */
	public Hashtable getDatastreamTable(String aPID) throws RemoteException;
	// TODO: if parameters asOfDateTime and dsState will be added, method conforms to 
	// getDatastreams Operation
	
	/**
	 * <p>Method modify the Fedora Object State <em>and/or Label</em></p>
	 * <p><em>Hint: Method directly reflects WS Operation modifyObject(...) from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object which should get another State
	 * @param aState <code>String</code> a State that will be set, null means unchanged:
	 * <ul>
	 * <li>A - Active</li>
	 * <li>I - Inactive</li>
	 * <li>D - Deleted</li>
	 * </ul>
	 * @param aLabel <code>String</code> a Label for the Fedora Object, null means leave unchanged    
	 * @param aOwnerId <code>String</code> a OwnerID (introduced with Fedora Version 3?)
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @throws RemoteException
	 */
	public void modifyObjectState(String aPID, String aState, String aLabel, String aOwnerId, String aLogMessage)throws RemoteException;
    
	/**
	 * <p>Method retrieves a the next available PID that belongs to a given Namespace</p>   
	 * <p><em>Hint: Method does not directly reflect a WS Operation from Fedora Versions 2, 3</em></p>
	 * @paraml <code>String</code> aNamespace as they will be defined in the Constant.Class. Examples: 
	 * <ul>
	 * <li>dipa - TestNamespace</li>
	 * <li>test - TestNamespace</li>
	 * <li>dipp - productive Namespace</li>
	 * <li>temp - temporary Object Namespace</li>
	 * <li>...</li>
	 * </ul>
	 * @return <code>String</code> the next available PID within a Namespace 
	 * @throws RemoteException
	 */
	public String getNextPID(String aNamespace)throws RemoteException;
	
	/**
	 * <p>Method deletes existing Fedora Object from Reposiitory</p>
	 * <p><em>Hint: Method directly reflects WS Operation purgeObject(...) from Fedora Versions 2, 3</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object which has to be deleted
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aForce <code>boolean</code> if true delete will be executed regardless of breaking dependencies 
	 * @return <code>String</code> timestamp from fedora Server when operation takes place 
	 * @throws RemoteException
	 */
	public String purgeObject(String aPID, String aLogMsg, boolean aForce)throws RemoteException;
	
	/**
	 * <p>Method deletes one or more existing Datastream Versions from a Fedora Object</p>
	 * <p><em>Hint: Method directly reflects WS Operation purgeDatastream(...) from Fedora Version 2. In Version 3 a startDate Argument is added</em></p>
	 * @param aPID <code>String</code> PID of the Fedora Object which has to be deleted
	 * @param aDsID <code>String</code> Identifier of Datastream that will be deleted 
	 * @param aEndDate <code>String</code> a Date until that Datastream versions will be deleted. The range of 
	 * datastream Versions to be deleted starts from the first/oldest accessible Version until this date  
	 * @param aLogMsg <code>String</code> a Log Message which is stored in Fedora
	 * @param aForce <code>boolean</code> if true delete will be executed regardless of breaking dependencies 
	 * @return <code>String</code> timestamp from fedora Server when operation takes place 
	 * @throws RemoteException
	 */
	public String[] purgeDatastream(String aPID, String aDsID, String aEndDate, String aLogMsg, boolean aForce) throws RemoteException;

	
	/**
	 * <p>Method export an entire Fedora Object in a specified XML format, and encoded appropriately
	 *  for the specified export context.</p>
	 * <p><em>Hint: Method directly reflects WS Operation export(...) from Fedora Version 2 and 3</em></p>
	 * @param pid <code>String</code> PID of the Fedora Object which should be exported
	 * @param format <code>String</code> one of the following FoXML Formats: 
	 * <ul>
	 * <li>info:fedora/fedora-system:FOXML-1.1 - most recent</li>
	 * <li>info:fedora/fedora-system:FOXML-1.0 - Format of Version 2</li>
	 * <li>info:fedora/fedora-system::METSFedoraExt-1.1 - Format of Version 1</li>
	 * <li>info:fedora/fedora-system::METSFedoraExt-1.0 - Format of Version 1</li>
	 * </ul>
	 * @param context <code>String</code> determines how the URL of Object will be stored: 
	 * <ul>
	 * <li>public</li>
	 * <li>archive</li>
	 * <li>migrate</li>
	 * </ul>
	 * @return <code>byte[]</code> an Array of Byte as representation of the entire Fedora Object 
	 * @throws RemoteException
	 */
	public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) throws java.rmi.RemoteException;

}
