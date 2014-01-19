package de.nrw.dipp.dippfedora2.repository;




import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
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
import de.nrw.dipp.dippCore.repository.SearchCondition;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactory;
import de.nrw.dipp.dippCore.repository.contentmodel.ModelFactoryImpl;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippfedora2.server.access21.FedoraAPIABindingSOAPHTTPStub;
import de.nrw.dipp.dippfedora2.server.access21.FedoraAPIAService;
import de.nrw.dipp.dippfedora2.server.access21.FedoraAPIAServiceLocator;
import de.nrw.dipp.dippfedora2.server.types.gen21.ComparisonOperator;
import de.nrw.dipp.dippfedora2.server.types.gen21.Condition;
import de.nrw.dipp.dippfedora2.server.types.gen21.DatastreamDef;
import de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchQuery;
import de.nrw.dipp.dippfedora2.server.types.gen21.FieldSearchResult;
import de.nrw.dipp.dippfedora2.server.types.gen21.MIMETypedStream;
import de.nrw.dipp.dippfedora2.server.types.gen21.ObjectFields;
import de.nrw.dipp.dippfedora2.server.types.gen21.Property;

/**
 * <p>Title: FedoraServicesAccessImpl2.java</p>
 * <p>Description: A implementation class for managing Fedora-API-A vers.2 Requests.</p>
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
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: FedoraServicesAccessImpl_2.java,v 1.2 2006/11/06 11:33:14 dippadm Exp $
 */
public class FedoraServicesAccessImpl_2 implements FedoraServicesAccess {

    // Get Logger for Class
	private static Logger log = Logger.getLogger(FedoraServicesAccessImpl_2.class);
	
	private FedoraAPIAService 				service = new FedoraAPIAServiceLocator();
	private FedoraAPIABindingSOAPHTTPStub	access	= null;
	private ModelFactory					modelFactory = new ModelFactoryImpl();

	public FedoraServicesAccessImpl_2(){
		try{
			URL url = new URL("http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/services/access");
			access = new FedoraAPIABindingSOAPHTTPStub(url, service);
			access.setUsername(Constant.cFedoraUser);
			access.setPassword(Constant.cFedoraPassword);
		}catch(MalformedURLException urlExc){
			urlExc.printStackTrace();
		}catch(AxisFault axisExc){
			axisExc.printStackTrace();
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
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#findObjects(java.lang.String[], int, info.fedora.www.definitions._1._0.types.FieldSearchQuery)
	 */
	public DigitalObject[] findObjects(String[] aResultFieldArray,
			int aMaxResults, List aSearchConditions) throws RemoteException {
		List resultList = getRepositorySearchResult(aResultFieldArray, aMaxResults, aSearchConditions);
		return (DigitalObject[])resultList.toArray(new DigitalObject[0]);
	}
	
	private List getRepositorySearchResult(String[] aResultFieldArray, int aMaxResults, List aSearchConditions)throws RemoteException{
		List digitalObjectList = new Vector();
		NonNegativeInteger nonNegInt = new NonNegativeInteger(aMaxResults  + "");

		FieldSearchQuery fsq = new FieldSearchQuery();
		fsq.setConditions( (Condition[])getSearchConditionList(aSearchConditions).toArray(new Condition[0]));
		FieldSearchResult fsr = access.findObjects(aResultFieldArray, nonNegInt, fsq);		
		digitalObjectList.addAll(Arrays.asList(mapFields(fsr.getResultList())));
		while(fsr.getListSession() != null){
			fsr = access.resumeFindObjects(fsr.getListSession().getToken());
			digitalObjectList.addAll(Arrays.asList(mapFields(fsr.getResultList())));
		}
		
		return digitalObjectList;
	}
	private List getSearchConditionList(List aSearchConditions){
		List fedoraConditions = new Vector();
		Iterator it = aSearchConditions.iterator();
		while (it.hasNext()){
			SearchCondition searchCondition = (SearchCondition)it.next();
			Condition	fedoraCondition		= new Condition();
			fedoraCondition.setProperty(searchCondition.getProperty());
			fedoraCondition.setValue(searchCondition.getValue());
			fedoraCondition.setOperator(ComparisonOperator.fromString(searchCondition.getCondition()));
			fedoraConditions.add(fedoraCondition);
		}
		return fedoraConditions;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getDataStream(java.lang.String, java.lang.String)
	 */
	public byte[] getDataStreamContentAsBytes(String aPID, String aItemID)
			throws RemoteException {
		Property property = new Property();
		property.setName("itemID");
		property.setValue(aItemID);
		Property[] propertyArray = new Property[1];
		propertyArray[0] = property; 
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 2);
		MIMETypedStream stream = access.getDatastreamDissemination(aPID, aItemID, dateFormat.format(cal.getTime()));
//		MIMETypedStream stream = access.getDissemination(aPID, "fedora-system:3", "getItem", propertyArray, dateFormat.format(cal.getTime()));
		return stream.getStream();
//		return access.getDissemination(aPID, "fedora-system:3", "getItem", propertyArray, dateFormat.format(cal.getTime())).getStream();
	}
	
	public InputStream getDataStreamContent(String aPID, String aItemID) throws RemoteException {
		return new ByteArrayInputStream(getDataStreamContentAsBytes(aPID, aItemID));
	}
	

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getItemIndex(java.lang.String)
	 */
	public Set getItemIndex(String aPID)throws RemoteException {
		return getItemHandler(aPID).getIdSet();
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.repository.FedoraServicesAccess#getItemLabelProps(java.lang.String)
	 */
	public Properties getItemLabelProps(String aPID)throws RemoteException {
		return getItemHandler(aPID).getLabels();
	}
	
	
	private DigitalObjectDatastreamSet getItemHandler(String aPID)throws RemoteException{
		DigitalObjectDatastreamSet itemHandler = new DigitalObjectDatastreamSet();
		
		Iterator itDsDef = Arrays.asList(access.listDatastreams(aPID, null)).iterator();
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
	
	/**
	 * Method maps arrays returned from an fedora request into Digital Object Behaviors
	 * @param aObjFields
	 * @return
	 */
	private DigitalObject[] mapFields(ObjectFields[] aObjFields){
		DigitalObject[] digObj = new DigitalObject[aObjFields.length];
		for (int i = 0; i < aObjFields.length; i++){
			digObj[i] = new DigitalObject(aObjFields[i].getPid());
			digObj[i].setBDef(aObjFields[i].getBDef());
			digObj[i].setBMech(aObjFields[i].getBMech());
//			System.out.println("cdate: " + aObjFields[i].getCDate());
//			digObj[i].setCDate(aObjFields[i].getCDate());
			// Qa: next line will be deprecated for fedora version 3:
			digObj[i].setMetaModel(modelFactory.createModel(aObjFields[i].getCModel()));
			digObj[i].setContributor(aObjFields[i].getContributor());
			digObj[i].setCoverage(aObjFields[i].getCoverage());
			digObj[i].setCreator(aObjFields[i].getCreator());
			digObj[i].setDate(aObjFields[i].getDate());
//			System.out.println("Date: " + aObjFields[i].getDate());
//			System.out.println("dcmdate: " + aObjFields[i].getDcmDate());
//			digObj[i].setDcmDate(aObjFields[i].getDcmDate());
			digObj[i].setDescription(aObjFields[i].getDescription());
			digObj[i].setFormat(aObjFields[i].getFormat());
			digObj[i].setFType(aObjFields[i].getFType());
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
	
	
	
	class DigitalObjectDatastreamSet{

		private Set					mItemSet			= new HashSet();
		private List				mItemList			= new Vector();

		public void addDatastream(DigitalObjectDatastream aDS){
			mItemList.add(aDS);
			mItemSet.add(aDS.getId());
		}
		
		public Set getIdSet() {
			return mItemSet;
		}
		
		public Properties getLabels() {
			Properties labelProps = new Properties();
			Iterator it = mItemList.iterator();
			while (it.hasNext()){
				DigitalObjectDatastream item = (DigitalObjectDatastream)it.next();
				labelProps.setProperty(item.getLabel(), item.getId());
			}
			return labelProps;
		}
	}


}
