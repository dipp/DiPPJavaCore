/**
 * Package provides Adaptors to Access the Fedora Repository version 3 Web Services  
 */
package de.nrw.dipp.dippfedora3.repository.WsAdaptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.axis.AxisFault;
import org.apache.axis.types.NonNegativeInteger;
import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.FedoraServicesAccess;
import de.nrw.dipp.dippCore.repository.FedoraServicesManagement;
import de.nrw.dipp.dippCore.repository.SearchCondition;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactory;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactoryImpl;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RIsearch;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RDF;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippfedora3.server.access31.FedoraAPIABindingSOAPHTTPStub;
import de.nrw.dipp.dippfedora3.server.access31.FedoraAPIAService;
import de.nrw.dipp.dippfedora3.server.access31.FedoraAPIAServiceLocator;
import de.nrw.dipp.dippfedora3.server.management31.FedoraAPIMBindingSOAPHTTPStub;
import de.nrw.dipp.dippfedora3.server.management31.FedoraAPIMService;
import de.nrw.dipp.dippfedora3.server.management31.FedoraAPIMServiceLocator;
import de.nrw.dipp.dippfedora3.server.types.gen31.ComparisonOperator;
import de.nrw.dipp.dippfedora3.server.types.gen31.Condition;
import de.nrw.dipp.dippfedora3.server.types.gen31.Datastream;
import de.nrw.dipp.dippfedora3.server.types.gen31.DatastreamDef;
import de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchQuery;
import de.nrw.dipp.dippfedora3.server.types.gen31.FieldSearchResult;
import de.nrw.dipp.dippfedora3.server.types.gen31.MIMETypedStream;
import de.nrw.dipp.dippfedora3.server.types.gen31.ObjectFields;
import de.nrw.dipp.dippfedora3.server.types.gen31.ObjectProfile;


/**
 * <p>Adaptor for accessing Web Service API's A and M from Fedora Repository Version 3</p>
 * 
 * <ul>
 * <li><a target="_blank" href="http://fedora-commons.org/confluence/display/FCR30/API-A#API-A-WSDL">Fedora Version 2-3</a></li>
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 
 * @author Andres Quast (quast@hbz-nrw.de)
 *
 */
public class FedoraServicesImpl_3 implements FedoraServicesManagement,
		FedoraServicesAccess {
	

	private FedoraAPIMService				serviceM = new FedoraAPIMServiceLocator();
	private FedoraAPIMBindingSOAPHTTPStub	management = null;	

	private FedoraAPIAService				serviceA = new FedoraAPIAServiceLocator();
	private FedoraAPIABindingSOAPHTTPStub	access = null;
	private ModelFactory					modelFactory = new ModelFactoryImpl();


	// Get Logger for DOManagement
	private static Logger log = Logger.getLogger(FedoraServicesImpl_3.class);

	public FedoraServicesImpl_3(){
		//construct Binding to API M
		try{
			URL url = new URL ("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/services/management");
			management = new FedoraAPIMBindingSOAPHTTPStub(url, serviceM);
			management.setUsername(Constant.cFedoraUser);
			management.setPassword(Constant.cFedoraPassword);
		}
		catch(MalformedURLException urlExc){
			
		}
		catch(AxisFault axisExc){
			
		}
		
		//construct Binding to API A
		try{
			URL url = new URL ("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/services/access");
			access = new FedoraAPIABindingSOAPHTTPStub(url, serviceA);
			access.setUsername(Constant.cFedoraUser);
			access.setPassword(Constant.cFedoraPassword);
		}
		catch(MalformedURLException urlExc){
			
		}
		catch(AxisFault axisExc){
			
		}
	}
	
	
	// Methods from Interface FedoraServicesManagement
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#addDatastream(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addDatastream(String apid, String dsID, String[] altIDs,
			String label, boolean isVersionable, String mimeType,
			String formatURI, byte[] byteArray, String logMsg,
			String metadataClass, String metadataType, String controlgroup,
			String checksumType, String checksum) {
		// TODO Auto-generated method stub
		// Qa: API-M provides only one addDatastream operation that takes a Location (URL) as argument 
		// from where the datastream content will be retrieved. 
		// This method retrieves an Array of byte, which will be uploaded to the server.
		// It seems to me straight forward to move this transformation to DOManagement for getting an adaptor 
		// that better reflects the Fedora WS API 
		// 
		// Method was used only by DOManagement and is abounded now due to reimplementation 
		// of calling method in DOManagement.
		return null;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#addDatastream(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addDatastream(String apid, String dsID, String fileName,
			String mimeType, String label, String controlgroup,
			String checksumType, String checksum) {
			String DsId = "";
			try{
				DsId = management.addDatastream(apid, dsID,  new String[0], label, true, mimeType, null, fileName, controlgroup, "A", checksumType, checksum, "added Datastream to Fedora Object");
			}catch(IOException ioE){
				ioE.printStackTrace();
			}
		return DsId;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#addDatastream(java.lang.String, java.lang.String, java.io.InputStream, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addDatastream(String apid, String dsID,
			InputStream inputStream, String mimeType, String label,
			String controlgroup, String checksumType, String checksum) {
		// TODO Auto-generated method stub
		// Qa: API-M provides only one addDatastream operation that takes a Location (URL) as argument 
		// from where the datastream content will be retrieved. 
		// This method retrieves an Inputstream, which will be uploaded to the server.
		// It seems to me straight forward to move this transformation to DOManagement for getting an adaptor 
		// that better reflects the Fedora WS API 
		// 
		// Method was used only by ServiceManagement and is abounded now due to reimplementation 
		// of calling method in ServiceManagement.
		return null;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#getDatastreamTable(java.lang.String)
	 */
	@Override
	public Hashtable<String,DigitalObjectDatastream> getDatastreamTable(String aPID) throws RemoteException {
		Hashtable<String,DigitalObjectDatastream> dsTable = new Hashtable<String,DigitalObjectDatastream>();
		Datastream[] dsArray = management.getDatastreams(aPID, null, "A");
		// API-M WS Operation getDatastream description at 
		// http://fedora-commons.org/confluence/display/FCR30/API-M#API-M-WSDL
		// lacks the isOfDate Parameter
		log.info("size of dsArray: " + dsArray.length);
		for (int i = 0; i < dsArray.length; i++){
			Datastream ds = dsArray[i];
			DigitalObjectDatastream doStream = new DigitalObjectDatastream();
			doStream.setId(ds.getID());
			doStream.setLabel(ds.getLabel());
			doStream.setLocation(ds.getLocation());
			doStream.setMimeType(ds.getMIMEType());
			doStream.setSize(ds.getSize());
			dsTable.put(ds.getID(), doStream);
		}
		return dsTable;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#getNextPID(java.lang.String)
	 */
	@Override
	public String getNextPID(String namespace) throws RemoteException {
		NonNegativeInteger nonNegInt = new NonNegativeInteger("1");
		return management.getNextPID(nonNegInt, namespace)[0];
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#identify()
	 */
	@Override
	public String identify() {
		return "FedoraServices Implementation Version " + Constant.cFedoraVersion + "\r\n" + "connected to\r\n" +
		   "Host: " + Constant.cFedoraHost + "\r\n" +
		   "Port: " + Constant.cFedoraPort + "\r\n" +
		   "User: " + Constant.cFedoraUser + "\r\n";
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#ingestObject(byte[], java.lang.String, java.lang.String)
	 */
	@Override
	public String ingestObject(byte[] object, String format, String logMessage)
			throws RemoteException {
		return management.ingest(object, format, logMessage);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void modifyDatastreamByReference(String apid, String dsID,
			String[] altIDs, String label, boolean isVersionable,
			String mimeType, String formatURI, byte[] dsContent,
			String dsState, String logMsg, String checksumType,
			String checksum, boolean force) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		// currently no calls to this method

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, java.io.InputStream, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void modifyDatastreamByReference(String apid, String dsID,
			String[] altIDs, String label, boolean isVersionable,
			String mimeType, String formatURI, InputStream inputStream,
			String dsState, String logMsg, String checksumType,
			String checksum, boolean force) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		// Method is replaced by the new method below 16.02.2009
		// all calls to this method are now redirected to the new method
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyDatastreamByReference(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void modifyDatastreamByReference(String apid, String dsID,
			String[] altIDs, String label, String mimeType,
			String formatURI, String aLocation, String checksumType,
			String checksum, String logMsg, boolean force) throws RemoteException, IOException {
		management.modifyDatastreamByReference(apid, dsID, altIDs, label, mimeType, formatURI, aLocation, checksumType, checksum, logMsg, force);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyDatastreamContent(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, boolean, java.lang.String, java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void modifyDatastreamContent(String apid, String dsID,
			String[] altIDs, String label, boolean isVersionable,
			String mimeType, String formatURI, byte[] dsContent,
			String dsState, String logMsg, String checksumType,
			String checksum, boolean force) throws RemoteException {
		management.modifyDatastreamByValue(apid, dsID, altIDs, label, mimeType, formatURI, dsContent, checksumType, checksum, logMsg, force);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#modifyObjectState(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyObjectState(String apid, String state, String label,
			String ownerId, String logMessage) throws RemoteException {
		management.modifyObject(apid, state, label, ownerId, logMessage);
		// what meaning has ownerId? is it required for anything? 

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#purgeDatastream(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String[] purgeDatastream(String apid, String dsID, String endDate,
			String logMsg, boolean force) throws RemoteException {
		String startDate = null;
		return management.purgeDatastream(apid, dsID, startDate, endDate, logMsg, force);
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesManagement#purgeObject(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String purgeObject(String apid, String logMsg, boolean force)
			throws RemoteException {
		return management.purgeObject(apid, logMsg, force);
	}

	public byte[] export(java.lang.String pid, java.lang.String format, java.lang.String context) 
		throws java.rmi.RemoteException{
		// Method will be implemented for export and migration of Articles from one single Journal. 
		return management.export(pid, format, context);
	}
	
	
	
// Methods from Interface FedoraServicesAccess

	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#findObjects(java.lang.String[], int, java.util.List)
	 */
	@Override
	public DigitalObject[] findObjects(String[] resultFieldArray,
			int maxResults, List searchConditionsList) throws RemoteException {

		// an important method for getting objects from a search. Unfortunately a search 
		// for cModel is not supported since Fedora Version 3. Therefore a way 
		// must be found to search for Objects that match a special Content Model
		DigitalObject[] digObjs = null;
		String cModel = null;
		
		// this method part is mostly similar to fedora Implementation 2  
		FieldSearchQuery fsQuery = new FieldSearchQuery();
		
		List<String> resultField  = new Vector<String>();
		resultField.addAll(Arrays.asList(resultFieldArray));
		
		// Remove cModel Field from resultField
		for(int i = 0; i<resultField.size(); i++){
			log.debug("removing cModel from resultFieldArray");
			if(resultField.get(i).equals("cModel")){
				resultField.remove(i);
			}
		}
		
		String[] fieldsArray = new String[resultField.size()];
		fieldsArray = resultField.toArray(fieldsArray);
		
		// generate Conditions for an Object search with the findObject Operation
		// especially for Fedora Version 3: skip any cModel search 
		List<Condition> conditionsList = getSearchConditionList(searchConditionsList);
		Condition[] conditions = new Condition[conditionsList.size()];
		conditions = conditionsList.toArray(conditions);
		// die beiden vorherigen Linien in kürzer:
		//Condition[] conditions = conditionsList.toArray(new Condition[0]);
		
		// now start the search with findObjects and resumeFindObjects Methods
		fsQuery.setConditions(conditions);
		NonNegativeInteger maxResult = new NonNegativeInteger(maxResults + "");
		FieldSearchResult fsResult = access.findObjects(fieldsArray, maxResult, fsQuery);
		
		List<DigitalObject> digObjList = new Vector<DigitalObject>();
		ObjectFields[] objFields = fsResult.getResultList();
		digObjList.addAll(Arrays.asList(mapFields(fsResult.getResultList())));
		while(fsResult.getListSession() != null){
			fsResult = access.resumeFindObjects(fsResult.getListSession().getToken());
			digObjList.addAll(Arrays.asList(mapFields(fsResult.getResultList())));
		}
		
		digObjs = digObjList.toArray(new DigitalObject[0]);
		
		// new part for fedora 3 implementation
		// first, find out, if the query includes a cModel request.
		// if so select all these Objects that belongs to the Model
		// try with getRDF method?
		Iterator<SearchCondition> it = searchConditionsList.iterator();
		while(it.hasNext()){
			SearchCondition searchCond = (SearchCondition)it.next();
			if(searchCond.getProperty().equals("cModel")){
				cModel = searchCond.getValue();
				log.debug("cModel " + cModel + " is requested, performing a request for cModel");
				digObjs = getDigObjectsByModel(cModel, digObjList);
			}
		}
				
		return digObjs;
	}
	
	
	/**
	 * Method creates a API-A Condition Instance and 
	 * adds a all available Informations from a SearchCondition instance 
	 * plus a search operator
	 *  
	 * Skip cModel Condition for fedora 3 implementation, because cModel can not be 
	 * requested any longer by findObjects Operation. Due to that a new Method has to 
	 * be implemented in the findObjects Method, finding these Objects with the correct cModel
	 *        
	 * @param aSearchConditions
	 * @return
	 */
	private List<Condition> getSearchConditionList(List<SearchCondition> aSearchConditions){
		List<Condition> fedoraConditions = new Vector<Condition>();
		Iterator<SearchCondition> it = aSearchConditions.iterator();
		while (it.hasNext()){
			SearchCondition searchCondition = (SearchCondition)it.next();
			Condition	fedoraCondition		= new Condition();

			// set Property and Value to Condition Object if Property is not cModel
			// skip cModel request
			if(!(searchCondition.getProperty().equals("cModel"))){
				fedoraCondition.setProperty(searchCondition.getProperty());
				fedoraCondition.setValue(searchCondition.getValue());
				fedoraCondition.setOperator(ComparisonOperator.fromString(searchCondition.getCondition()));
				fedoraConditions.add(fedoraCondition);				
			}
		}
		return fedoraConditions;
	}
	
	/**
	 * Method maps ObjectFields returned from a Fedora request into DigitalObject Behaviors. 
	 * Generates a Digital Object representation of a Fedora Object
	 * @param objFields <code>ObjectFields[]</code> an Array of ObjectFields including all behaviors
	 *  of a Fedora Object  
	 * @return DigitalObject
	 */
	private DigitalObject[] mapFields(ObjectFields[] aObjFields){
		DigitalObject[] digObj = new DigitalObject[aObjFields.length];
		String CModel = null;
		String bCModel = null;
		
		for (int i = 0; i < aObjFields.length; i++){
			digObj[i] = new DigitalObject(aObjFields[i].getPid());

			// Qa: following lines are deprecated for Fedora Version 3:
			//digObj[i].setMetaModel(modelFactory.createModel(aObjFields[i].getCModel()));
			//digObj[i].setBDef(aObjFields[i].getBDef());
			//digObj[i].setBMech(aObjFields[i].getBMech());
			//digObj[i].setFType(aObjFields[i].getFType());

			// cModel will be included to a DigitalObject not as String but as
			// a model representation			
			if((CModel = getDigObjectModelByRI(aObjFields[i].getPid()))!=null){
				log.info("ContentModelByRI: " + CModel);
				digObj[i].setMetaModel(modelFactory.createModel(CModel));
			}
			// Hack (Qa): due to insufficient synchronization if Resource Index, we can not get
			// the CM-Informations from rdf-triples from newly generated object. Therefore
			// the WS Operation is needed as fallback 
			else if((bCModel = getDigObjectModel(aObjFields[i].getPid()))!=null){
				log.warn("Resource Index not synchronized, WS Operation is used for accessing CModel for Object");
				log.debug("Object-Pid: " + aObjFields[i].getPid());
				log.debug("ContentModelByRI: " + CModel);
				log.debug("ContentModel: " + bCModel);
				digObj[i].setMetaModel(modelFactory.createModel(bCModel));
			}
//			System.out.println("cdate: " + aObjFields[i].getCDate());
//			digObj[i].setCDate(aObjFields[i].getCDate());
			digObj[i].setContributor(aObjFields[i].getContributor());
			digObj[i].setCoverage(aObjFields[i].getCoverage());
			digObj[i].setCreator(aObjFields[i].getCreator());
			digObj[i].setDate(aObjFields[i].getDate());
//			System.out.println("Date: " + aObjFields[i].getDate());
//			System.out.println("dcmdate: " + aObjFields[i].getDcmDate());
//			digObj[i].setDcmDate(aObjFields[i].getDcmDate());
			digObj[i].setDescription(aObjFields[i].getDescription());
			digObj[i].setFormat(aObjFields[i].getFormat());
			digObj[i].setIdentifier(aObjFields[i].getIdentifier());
			digObj[i].setLabel(aObjFields[i].getLabel());
			digObj[i].setLanguage(aObjFields[i].getLanguage());
//			System.out.println("mdate: " + aObjFields[i].getMDate());
//			digObj[i].setMDate(aObjFields[i].getMDate());
			digObj[i].setOwnerId(aObjFields[i].getOwnerId());
			digObj[i].setPublisher(aObjFields[i].getPublisher());
			digObj[i].setRelation(aObjFields[i].getRelation());
			digObj[i].setRights(aObjFields[i].getRights());
			digObj[i].setSource(aObjFields[i].getSource());
			digObj[i].setState(aObjFields[i].getState());
			digObj[i].setSubject(aObjFields[i].getSubject());
			digObj[i].setTitle(aObjFields[i].getTitle());
			digObj[i].setType(aObjFields[i].getType());
		}
		return digObj;
	}

	/**
	 * 1. Versuch einer Methode, für alle DigitalObjects aus einer Liste von 
	 * DigitalObjects das ContentModel ermittelt.
	 * @param digObjList
	 * @return
	 */
	private DigitalObject[] getDigObjectsByModel(String cModel, List<DigitalObject> digObjList){
		// im Moment noch unklar auf welche Weise diese Methode, die einem DigitalObject 
		// eine Instanz eines DigitalObjectModels zufügt am besten implementiert werden kann. 
		// Frage ist vor allem, wie die Information über das CModel abgefragt wird
		// 1. Abfrage des Research Indexes
		// 2. Abfrage über getObject Methode
		
		// Nein! die DigitalObjects besitzen inzwischen die Information, welchem cModel 
		// sie angehören. Ich muss die Information nur wieder rausholen. 
		
		//String query = "*  <fedora-model:hasModel> <info:fedora/"+ cModel + ">";
		//RIsearch riSearch = new RIsearch(RIsearch.cType_Triples, RIsearch.cFormat_rdf_xml, RIsearch.cLang_Triples_spo, query);
		/*		if (digObj.getRDF() == null){
		aDigitalObject.setRDF(new RDF(aDigitalObject.getPid()));
		}
		*/
		List<DigitalObject> digObjByModelList = new Vector<DigitalObject>();
		
		Iterator<DigitalObject> it = digObjList.iterator();
		while(it.hasNext()){
			DigitalObject digObj = it.next();
			String aPid = digObj.getPid();
			log.debug(aPid);
			
			if(digObj.getCModel() != null && digObj.getCModel().equals(cModel)){
				digObjByModelList.add(digObj);
			}
			log.debug(digObj.getCModel());
			log.debug(cModel);			
		}
		DigitalObject[] digObjs = digObjByModelList.toArray(new DigitalObject[0]);
		return digObjs;
	}

	/**
	 * <p>Method returns the name of the ContentModel that belongs to the several DigitalObject</p>
	 * <p>1. Versuch einer Methode, die für ein bestimmtes DigitalObject das Content Model 
	 * ermittelt. Umsetzung mit der API-A getDigitalObject Operation</p>
	 * @param aPid <code>String</code> the Pid of Fedora Object for that a Content Model is searched  
	 * @return <code>String</code> the name of ContentModel used by the DigitalObject 
	 */
	private String getDigObjectModel(String aPid){
	
		String cModel = null;
		ObjectProfile objProf = null;
		
		try{
			objProf = access.getObjectProfile(aPid, "");
		}catch(RemoteException remExc){
			log.error(remExc);
		}
		String[] cModels = objProf.getObjModels();
		for(int i=0; i < cModels.length; i++){
			log.debug("found cModel: " +  cModels[i] + " for PID: " + aPid);
			if(cModels[i].substring(12).equals(Constant.cContentModelJournal)){
				cModel = cModels[i].substring(12);
			}
			else if(cModels[i].substring(12).equals(Constant.cContentModelArticle)){
				cModel = cModels[i].substring(12);
			}
			else if(cModels[i].substring(12).equals(Constant.cContentModelData)){
				cModel = cModels[i].substring(12);
			}
			else if(cModels[i].substring(12).equals(Constant.cContentModelContainer)){
				cModel = cModels[i].substring(12);
			}
			else if(cModels[i].substring(12).equals("fedora-system:FedoraObject-3.0")){
				// Do nothing
			}
			else if(cModels[i].substring(12).equals("oai:Set")){
				// Do nothing
			}
			else{
				log.debug("found no appropriate model for Model: " + cModels[i].substring(12) + " and PID: " + aPid);
			}
		}
		return cModel;
	}
	
	/**
	 * <p>Method returns the name of the ContentModel that belongs to the several DigitalObject</p>
	 * <p>Versuch einer Methode, die für ein bestimmtes DigitalObject das Content Model 
	 * ermittelt. Umsetzung mit dem Resource Index. Sollte performanter sein.</p>
	 * @param aPid <code>String</code> the Pid of Fedora Object for that a Content Model is searched  
	 * @return <code>String</code> the name of ContentModel used by the DigitalObject 
	 */
	private String getDigObjectModelByRI(String aPid){
	
		String cModel = null;
		//ObjectProfile objProf = null;
		
		String query = "select $object from <#ri>where <info:fedora/" + aPid + ">  <fedora-model:hasModel> $object and $object <dc:title> \'Generated CModel\'";
		log.debug(query);
		//query = "select $object from <#ri>where <info:fedora/dipp:23> <fedora-model:hasModel> $object and $object <dc:title> \'Generated CModel\'";
		RIsearch riSearch = new RIsearch(RIsearch.cType_Tuples, RIsearch.cFormat_Simple, RIsearch.cLang_Tuples_itql, query);
		
		//RDF rdf = riSearch.getRDFObject();
		//cModel = rdf.toXMLObject().toString();
		
		String response = riSearch.getStringResult().trim();
		log.debug("RI Result for CModel request: " + response);
		
		if (response.length() != 0){
			cModel = response.substring(22,response.length()-1);
			log.debug(cModel.toString());
		}
		return cModel;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getDataStreamContent(java.lang.String, java.lang.String)
	 */
	@Override
	public InputStream getDataStreamContent(String apid, String itemID)
			throws RemoteException {
		return new ByteArrayInputStream(getDataStreamContentAsBytes(apid, itemID));
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getDataStreamContentAsBytes(java.lang.String, java.lang.String)
	 */
	@Override
	public byte[] getDataStreamContentAsBytes(String apid, String itemID)
			throws RemoteException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 2);
		MIMETypedStream stream = access.getDatastreamDissemination(apid, itemID, dateFormat.format(cal.getTime()));

		// Block is used to call getDessimination instead of getDatastreamDissemination Operation
		// is there any reason for using this in the future?
/*		Property property = new Property();
		property.setName("itemID");
		property.setValue(itemID);
		Property[] propertyArray = new Property[1];
		propertyArray[0] = property; 
		MIMETypedStream stream = access.getDissemination(apid, "fedora-system:3", "getItem", propertyArray, dateFormat.format(cal.getTime()));
		return access.getDissemination(aPID, "fedora-system:3", "getItem", propertyArray, dateFormat.format(cal.getTime())).getStream();
*/
		return stream.getStream();

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getItemIndex(java.lang.String)
	 */
	@Override
	public Set getItemIndex(String apid) throws RemoteException {
		return getItemHandler(apid).getIdSet();
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getItemLabelProps(java.lang.String)
	 */
	@Override
	public Properties getItemLabelProps(String apid) throws RemoteException {
		return getItemHandler(apid).getLabels();

	}

	/**
	 * <p>Methods generates an Object of DigitalObjectDatastreamSet providing descriptions of all 
	 * associated Datastreams as DigitalObjectDatastream Instance</p>
	 * 
	 * @param aPID <code>String</code> the PID of a FedoraObject 
	 * @return <code>DigitalObjectDatastreamSet</code> a Set of Datastream-descriptions 
	 * found for all Datastreams associated with the Fedora Object   
	 * @throws RemoteException
	 */
	private DigitalObjectDatastreamSet getItemHandler(String aPID)throws RemoteException{
		DigitalObjectDatastreamSet itemHandler = new DigitalObjectDatastreamSet();
		
		Iterator<DatastreamDef> itDsDef = Arrays.asList(access.listDatastreams(aPID, null)).iterator();
		while (itDsDef.hasNext()){
			DatastreamDef dsDef = (DatastreamDef)itDsDef.next();
			DigitalObjectDatastream doDS = new DigitalObjectDatastream();
			doDS.setId(dsDef.getID());
			doDS.setLabel(dsDef.getLabel());
			doDS.setMimeType(dsDef.getMIMEType());
			itemHandler.addDatastream(doDS);
		}
		
		return itemHandler;
	}

	
	class DigitalObjectDatastreamSet{

		private Set<String>	mItemSet = new HashSet<String>();
		private List<DigitalObjectDatastream> mItemList = new Vector<DigitalObjectDatastream>();

		public void addDatastream(DigitalObjectDatastream aDS){
			mItemList.add(aDS);
			mItemSet.add(aDS.getId());
		}
		
		public Set<String> getIdSet() {
			return mItemSet;
		}
		
		public Properties getLabels() {
			Properties labelProps = new Properties();
			Iterator<DigitalObjectDatastream> it = mItemList.iterator();
			while (it.hasNext()){
				DigitalObjectDatastream item = (DigitalObjectDatastream)it.next();
				labelProps.setProperty(item.getLabel(), item.getId());
			}
			return labelProps;
		}
	}

	// only a test for getDigObjectModelByRI: remove after implementation of method!!!
	public static void main(String[] args){
	  FedoraServicesImpl_3 test = new FedoraServicesImpl_3();
	  String result = test.getDigObjectModelByRI("content:15197");
	  log.info(result);
  }

	/**
	 * <p><em>Title: Define a new WS Interface for Fedora follower Instances</em></p>
	 * <p>Description: </p>
	 * 
	 * @param FedoraProp 
	 */
/*	public void changeWSInterface(Properties FedoraProp){
		//construct Binding to API M
		try{
			URL url = new URL ("http://" + FedoraProp.getProperty("host") + ":" + FedoraProp.getProperty("port") + "/fedora/services/management");
			management = new FedoraAPIMBindingSOAPHTTPStub(url, serviceM);
			management.setUsername(FedoraProp.getProperty("user"));
			management.setPassword(FedoraProp.getProperty("password"));
		}
		catch(MalformedURLException urlExc){
			
		}
		catch(AxisFault axisExc){
			
		}
		
		//construct Binding to API A
		try{
			URL url = new URL ("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/services/access");
			access = new FedoraAPIABindingSOAPHTTPStub(url, serviceA);
			access.setUsername(Constant.cFedoraUser);
			access.setPassword(Constant.cFedoraPassword);
		}
		catch(MalformedURLException urlExc){
			
		}
		catch(AxisFault axisExc){
			
		}
	}
*/

}
