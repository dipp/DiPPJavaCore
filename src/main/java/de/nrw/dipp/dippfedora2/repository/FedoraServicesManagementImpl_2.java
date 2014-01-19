package de.nrw.dipp.dippfedora2.repository;



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.axis.AxisFault;
import org.apache.axis.types.NonNegativeInteger;

import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.FedoraServicesManagement;
import de.nrw.dipp.dippCore.repository.Upload;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMBindingSOAPHTTPStub;
import de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMService;
import de.nrw.dipp.dippfedora2.server.management21.FedoraAPIMServiceLocator;
import de.nrw.dipp.dippfedora2.server.types.gen21.Datastream;


/**
 * <p>Title: FedoraServicesManagementImpl_2.java</p>
 * <p>Description: A wrapper class for encapsulating Fedora2.0-Management-Requests.</p>
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
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: FedoraServicesManagementImpl_2.java,v 1.1 2006/04/26 08:53:20 dippadm Exp $
 */
public class FedoraServicesManagementImpl_2 implements FedoraServicesManagement {

	private FedoraAPIMService				service = new FedoraAPIMServiceLocator();
	private FedoraAPIMBindingSOAPHTTPStub	management = null;
	
	
	public FedoraServicesManagementImpl_2(){
		try{
			URL url = new URL("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/services/management");
			management = new FedoraAPIMBindingSOAPHTTPStub(url, service);
			management.setUsername(Constant.cFedoraUser);
			management.setPassword(Constant.cFedoraPassword);
		}catch(MalformedURLException urlExc){
			
		}catch(AxisFault axisExc){
			
		}		
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#identify()
	 */
	public String identify() {
		return "FedoraServicesManagement Implementation Version " + Constant.cFedoraVersion + "\r\n" + "connected to\r\n" +
		   "Host: " + Constant.cFedoraHost + "\r\n" +
		   "Port: " + Constant.cFedoraPort + "\r\n" +
		   "User: " + Constant.cFedoraUser + "\r\n";
//		   "Password: " + Constant.cFedoraPassword + "\r\n";
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#ingestObject(byte[], java.lang.String)
	 */
	public String ingestObject(byte[] aXMLObject, String aFormat, String aLogMessage)
			throws RemoteException {
		return management.ingest(aXMLObject, aFormat, aLogMessage);
	}

	/**
	 * <p>adds a datastream to the digital object</p> 
	 * @param aPID	the fedora digital object id
	 * @param aDsID	a datastream id (optional)
	 * @param aAltIDs alternate identifiers for the datastream (optional)
	 * @param aLabel the label for the datastream
	 * @param aIsVersionable versionable
	 * @param aMimeType the mime type
	 * @param aFormatURI the format URI (a more granular format identifier than mime type). (optional)
	 * @param aByteArray the datastream as byte array
	 * @param aLogMsg a log message
	 * @param aMetadataClass obsolet
	 * @param aMetadataType obsolet
	 * @return the datastream id of the newly added datastream
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#addDatastream(java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addDatastream(String aPID, String aDsID, String[] aAltIDs, 
			String aLabel, boolean aIsVersionable, String aMimeType, 
			String aFormatURI, byte[] aByteArray, String aLogMsg, 
			String aMetadataClass, String aMetadataType, String aControlgroup, 
			String aChecksumType, String aChecksum) {
		String id = null;
		//Qa: Code doublette, try to extinct
		/*
		Upload upload = new Upload(	Constant.cFedoraHost, 
				Constant.cFedoraPort,
				Constant.cFedoraUser, 
				Constant.cFedoraPassword);
		String location = null;
		try{
			location = upload.upload(new ByteArrayInputStream(aByteArray));
		}catch(IOException fExc){
			fExc.printStackTrace();
		}
		*/
		try{
			String location = getUploadLocation(new ByteArrayInputStream(aByteArray));
			id = addDS(aPID, aDsID, aAltIDs, aLabel, aIsVersionable, aMimeType, aFormatURI, location, aLogMsg, aControlgroup);
		}
		//TODO declare catch block correctly
		catch(Exception e){
			
		}
		return id;
	}

	private String addDS(	String aPID, String aDsID, String[] aAltIDs, String aLabel, 
							boolean aIsVersionable, String aMimeType, String aFormatURI, String aLocation, 
							String aLogMsg, String aControlGroup)throws IOException{
		return management.addDatastream(aPID, aDsID, aAltIDs, aLabel, aIsVersionable, aMimeType, aFormatURI, aLocation, aControlGroup, "A", aLogMsg);
	}
	
	private String getUploadLocation(InputStream aInputStream)throws IOException{
		Upload upload = new Upload(	Constant.cFedoraHost, 
				Constant.cFedoraPort,
				Constant.cFedoraUser, 
				Constant.cFedoraPassword);
		return upload.upload(aInputStream);		
	}
	
	private String getUploadLocation(String aFileName)throws IOException{
		Upload upload = new Upload(	Constant.cFedoraHost, 
				Constant.cFedoraPort,
				Constant.cFedoraUser, 
				Constant.cFedoraPassword);
		return upload.upload(new File(aFileName));		
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#addDatastream(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addDatastream(String aPID, String aDsID, String aLocation, String aMimeType, String aLabel, String aControlgroup, String aChecksumType, String aChecksum) {
		String id = "";
		try{
			if (aControlgroup.equals(Constant.cFedoraDatastreamControlGroupExternal)){
				id = addDS(aPID, aDsID, new String[0], aLabel, true, aMimeType, null, aLocation, "", aControlgroup);
			}else{
				id = addDS(aPID, aDsID, new String[0], aLabel, true, aMimeType, null, aLocation, "", aControlgroup);				
			}
		}catch(FileNotFoundException fExc){
			fExc.printStackTrace();
		}catch(IOException ioE){
			ioE.printStackTrace();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#addDatastream(java.lang.String, java.io.InputStream, java.lang.String, java.lang.String)
	 */
	public String addDatastream(String aPID, String aDsID, InputStream aInputStream, String aMimeType, String aLabel, String aControlgroup, String aChecksumType, String aChecksum) {
		String id = "";
		try{
			String location = getUploadLocation(aInputStream);
			String mimeType = aMimeType;
			id = addDS(aPID, aDsID, new String[0], aLabel, true, mimeType, null, location, "", aControlgroup);
		}catch(IOException fExc){
			fExc.printStackTrace();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#modifyDatastreamContent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, byte[], java.lang.String)
	 */
	public void modifyDatastreamContent(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aDsContent, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce) throws RemoteException {
		management.modifyDatastreamByValue(aPID, aDsID, aAltIDs, aLabel, aIsVersionable, aMimeType, aFormatURI, aDsContent, aDsState, aLogMsg, aForce);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String, java.lang.String, byte[], java.lang.String)
	 * @deprecated no Calls
	 */
	public void modifyDatastreamByReference(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, byte[] aDsContent, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce) throws RemoteException, IOException {
		management.modifyDatastreamByReference(aPID, aDsID, aAltIDs, aLabel, aIsVersionable, aMimeType, aFormatURI, getUploadLocation(new ByteArrayInputStream(aDsContent)), aDsState, aLogMsg, aForce );
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.InputStream, java.lang.String)
	 * @deprecated no Calls
	 */
	public void modifyDatastreamByReference(String aPID, String aDsID, String[] aAltIDs, String aLabel, boolean aIsVersionable, String aMimeType, String aFormatURI, InputStream aInputStream, String aDsState, String aLogMsg, String aChecksumType, String aChecksum, boolean aForce) throws RemoteException, IOException {
		management.modifyDatastreamByReference(aPID, aDsID, aAltIDs, aLabel, aIsVersionable, aMimeType, aFormatURI, getUploadLocation(aInputStream), aDsState, aLogMsg, aForce);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void modifyDatastreamByReference(String apid, String dsID,
			String[] altIDs, String label, String mimeType,
			String formatURI, String aLocation, String checksumType,
			String checksum, String logMsg, boolean force) throws RemoteException, IOException {
		management.modifyDatastreamByReference(apid, dsID, altIDs, label, true, mimeType, formatURI, aLocation, "A", logMsg, force);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#getDatastreamIDs(java.lang.String)
	 */
	public Hashtable<String, DigitalObjectDatastream> getDatastreamTable(String aPID)throws RemoteException {
		Hashtable<String, DigitalObjectDatastream> dsTable = new Hashtable<String, DigitalObjectDatastream>();
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.HOUR, +20);
//		Datastream[] dsArray = management.getDatastreams(aPID, getDateTime(cal), "A");
		Datastream[] dsArray = management.getDatastreams(aPID, null, "A");
		System.out.println("size of dsArray: " + dsArray.length);
		for (int i = 0; i < dsArray.length; i++){
			Datastream ds = dsArray[i];
			DigitalObjectDatastream doStream = new DigitalObjectDatastream();
			doStream.setId(ds.getID());
			doStream.setLabel(ds.getLabel());
			doStream.setLocation(ds.getLocation());
			doStream.setMimeType(ds.getMIMEType());
			doStream.setSize(ds.getSize());
			doStream.setCreateDate(ds.getCreateDate());
			dsTable.put(ds.getID(), doStream);
		}
		return dsTable;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#modifyObjectState(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void modifyObjectState(String aPID, String aState, String aLabel, String aOwnerId, String aLogMessage) throws RemoteException {
		management.modifyObject(aPID, aState, aLabel, aLogMessage);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#getNextPID(java.lang.String)
	 */
	public String getNextPID(String aNamespace) throws RemoteException {
		NonNegativeInteger nonNegInt = new NonNegativeInteger("1");
		return management.getNextPID(nonNegInt, aNamespace)[0];
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServiceManagement#purgeObject(java.lang.String, java.lang.String)
	 */
	public String purgeObject(String aPID, String aLogMsg, boolean aForce) throws RemoteException {
		return management.purgeObject(aPID, aLogMsg, aForce);
	}
	
	public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) 
	throws java.rmi.RemoteException{
	// Method will be implemented for export and migration of Articles from one single Journal. 
	return management.export(pid, format, context);
}

	
	public String[] purgeDatastream(String aPID, String aDsID, String endDate,
			String aLogMsg, boolean aForce) throws RemoteException {
		return management.purgeDatastream(aPID, aDsID, endDate, aLogMsg, aForce);
	}
	
	private static String getDateTime(Calendar aCal){
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		return dateTimeFormat.format(aCal.getTime());
	}

}
