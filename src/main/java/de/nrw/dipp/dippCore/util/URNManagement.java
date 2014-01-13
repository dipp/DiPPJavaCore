package de.nrw.dipp.dippCore.util;

import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.xmlbeans.XmlCursor;

import nbnDe11112004033116.AdministrativeDataType;
import nbnDe11112004033116.DeliveryType;
import nbnDe11112004033116.EpicurDocument;
import nbnDe11112004033116.FormatType;
import nbnDe11112004033116.HasVersionType;
import nbnDe11112004033116.IdentifierType;
import nbnDe11112004033116.IsPartOfType;
import nbnDe11112004033116.IsVersionOfType;
import nbnDe11112004033116.RecordType;
import nbnDe11112004033116.ResourceType;
import nbnDe11112004033116.ResupplyType;
import nbnDe11112004033116.TransferType;
import nbnDe11112004033116.UpdateStatusType;
import nbnDe11112004033116.EpicurDocument.Epicur;

/**
 * <p>Title: URNManagement.java</p>
 * <p>Description: Introduced for Fedora V2, this class manages a digital object and makes the old dirty FedoraInterface class obsolet.</p>
 * <p>Qa: Class for managing URN generation and registration? </p>
 *  
 * <p>History: created on 2004/12/21</p>
 * <p>last modified on 2006-11-10 by JS: isPartOf, hasVersion added</p>
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
 * @version $Id: URNManagement.java,v 1.2 2007/01/05 11:48:05 dippadm Exp $
 */
public class URNManagement {
	
//	private static final String cPersonID				= "F60004010";
//	private static final String cURN_snID				= "urn:nbn:de:0009";
	private EpicurDocument 			mEpicurDoc				= null;
	private EpicurDocument.Epicur 	mEpicur					= null;
	private AdministrativeDataType 	mAdmin					= null;
	private DeliveryType 			mDelivery				= null;
	private UpdateStatusType 		mUpdateStatus			= null;
	
//  Qa: constants needed to provide a Document state to DNB via OAI 
//	in the epicur format
	public static final int	cEpicurUpdateStatus_URN_NEW 			= 1; //"urn_new";
    public static final int	cEpicurUpdateStatus_URN_NEW_VERSION 	= 2; //"urn_new_version";
    public static final int	cEpicurUpdateStatus_URL_UPDATE_GENERAL 	= 3; //"url_update_general";
    public static final int	cEpicurUpdateStatus_URN_ALTERNATIVE 	= 4; //"urn_alternative";
    public static final int	cEpicurUpdateStatus_URN_NEW_PART	 	= 5; //"url_update_general";

	public URNManagement(){
		init();
	}
	
	/**
	 * <p><em>Title: create xepicur stream in a generic way</em></p>
	 * <p>Description: method should replace all updatestatus-dependend methods as a generic method that 
	 * creates the correct xepicur XML-Stream for various update states </p>
	 * 
	 * @param EpicurUpdateStatus
	 * @param values
	 * @return 
	 */
	public String createEpicurXML(int epicurUpdateStatus, Hashtable<String, String> metadataValues){
			EpicurDocument epicurDoc = EpicurDocument.Factory.newInstance();
			Epicur epicur = epicurDoc.addNewEpicur();
			
			// Insert SchemaLocation attributes.
			XmlCursor cursor = epicur.newCursor();
			cursor.toFirstContentToken();
			cursor.toLastAttribute();
			cursor.insertAttributeWithValue("schemaLocation","http://www.w3.com/2001/XMLSchema-instance", "urn:nbn:de:1111-2004033116 http://nbn-resolving.de/urn/resolver.pl?urn=urn:nbn:de:1111-2004033116");
			cursor.insertNamespace("xsi", "http://www.w3.com/2001/XMLSchema-instance");
			cursor.dispose();
						
			mAdmin = epicur.addNewAdministrativeData();
			mDelivery = mAdmin.addNewDelivery();
			TransferType transfer = mDelivery.addNewTransfer();
			transfer.setType(TransferType.Type.OAI);
			//ResupplyType resupply = mDelivery.addNewResupply();
			//resupply.setType(ResupplyType.Type.EMAIL);
			mUpdateStatus = mDelivery.addNewUpdateStatus();
			mUpdateStatus.setType(UpdateStatusType.Type.Enum.forInt(epicurUpdateStatus));
			System.out.println("INT: " + epicurUpdateStatus);
			System.out.println("Type: " + mUpdateStatus.getType());
			//mUpdateStatus.setType(UpdateStatusType.Type.Enum.forInt(0));

			//mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW);
			RecordType record = epicur.addNewRecord();
			IdentifierType identifier = record.addNewIdentifier();
			identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
			ResourceType resource = record.addNewResource();
			IdentifierType resourceIdentifier = resource.addNewIdentifier();
			FormatType resourceFormat = resource.addNewFormat();
			
			
			// get values offered by the the Hashtable
			
			identifier.setStringValue(metadataValues.get("urn"));
			System.out.println(identifier.getStringValue());

			if (metadataValues.containsKey("url")){
				resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
				resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
				resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
				resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
				resourceIdentifier.setStringValue(metadataValues.get("url"));
			}
			if (metadataValues.containsKey("MimeType")){
				resourceFormat.setScheme(FormatType.Scheme.IMT);
				resourceFormat.setStringValue(metadataValues.get("MimeType"));
			}
			if (metadataValues.containsKey("doi")){
				//TODO: implement if needed
			}
		
			

			return epicurDoc.toString();
			
	}
	
	public void init(){
/*		XmlOptions options = new XmlOptions();
		System.out.println(options.setSaveNamespacesFirst());
*/		
		mEpicurDoc = EpicurDocument.Factory.newInstance();
		mEpicur = mEpicurDoc.addNewEpicur();
		
		XmlCursor cursor = mEpicur.newCursor();
		cursor.toFirstContentToken();
		cursor.toLastAttribute();
		
		// Insert SchemaLocation attributes.
		cursor.insertAttributeWithValue("schemaLocation","http://www.w3.com/2001/XMLSchema-instance", "urn:nbn:de:1111-2004033116 http://nbn-resolving.de/urn/resolver.pl?urn=urn:nbn:de:1111-2004033116");
//		cursor.insertAttributeWithValue("version", "2.9");
		cursor.insertNamespace("xsi", "http://www.w3.com/2001/XMLSchema-instance");
//		cursor.insertNamespace("xsd", "http://www.w3.com/2001/XMLSchema");
		cursor.dispose();
		
		mAdmin = mEpicur.addNewAdministrativeData();
		mDelivery = mAdmin.addNewDelivery();
/*		AuthorizationType authorization = mDelivery.addNewAuthorization();
		authorization.setPersonId(cPersonID);
		authorization.setUrnSnid(cURN_snID);
*/
		TransferType transfer = mDelivery.addNewTransfer();
		transfer.setType(TransferType.Type.OAI);
		ResupplyType resupply = mDelivery.addNewResupply();
		resupply.setType(ResupplyType.Type.EMAIL);
		mUpdateStatus = mDelivery.addNewUpdateStatus();
		mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW_VERSION);
	}
	
	/**
	 * @deprecated
	 * <p>Method creates the epicur XML-Fragment that is given to fedora as 
	 * epicur DS. This Method is used for a new (never registered) Object </p>
	 * @param aURN
	 * @param aURL
	 * @param aMimeType
	 * @return an epicur XML-Fragment as String 
	 */
	public String getInsertURN(String aURN, String aURL, String aMimeType){
		mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW);		
		RecordType record = mEpicur.addNewRecord();
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(aURN);
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
		resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
		resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
		resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
		resourceIdentifier.setStringValue(aURL);
		FormatType resourceFormat = resource.addNewFormat();
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(aMimeType);

		return mEpicurDoc.toString();
	}
	
	/**
	 * @deprecated
	 *
	 * <p><em>Title:  </em></p>
	 * <p>Description: Method declares an object with an urn as child of another object identified by another url,
	 * the ParentUrl. 
	 * Method is <em>not<em> use. </p>
	 * @param aURN
	 * @param aURL
	 * @param aMimeType
	 * @param aParentURN
	 * @param aParentURL
	 * @return 
	 */
	public String getInsertURNPart(String aURN, String aURL, String aMimeType, String aParentURN, String aParentURL){
		mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW);
		RecordType record = mEpicur.addNewRecord();
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(aURN);
		
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
		resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
		resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
		resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
		resourceIdentifier.setStringValue(aURL);
		FormatType resourceFormat = resource.addNewFormat();
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(aMimeType);
		
		IsPartOfType isPartOf = record.addNewIsPartOf();
		IdentifierType isPartOfIdentifier = isPartOf.addNewIdentifier();
		isPartOfIdentifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		isPartOfIdentifier.setStringValue(aParentURN);
		isPartOfIdentifier = isPartOf.addNewIdentifier();
		isPartOfIdentifier.setScheme(IdentifierType.Scheme.URL);
		isPartOfIdentifier.setStringValue(aParentURL);

		return mEpicurDoc.toString();
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method for acquire an new URN for a new Version of an article that is published in a new Version.
	 * Method is <em>not<em> use.</p>
	 * 
	 * @param aURN
	 * @param aURL
	 * @param aMimeType
	 * @param aDOI
	 * @return 
	 */
	public String getInsertHasVersion(String aURN, String aURL, String aMimeType, String aDOI){
		mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW);
		RecordType record = mEpicur.addNewRecord();
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(aURN);
		
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
		resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
		resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
		resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
		resourceIdentifier.setStringValue(aURL);
		FormatType resourceFormat = resource.addNewFormat();
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(aMimeType);
		
		HasVersionType hasVersion = record.addNewHasVersion();
		hasVersion.setScheme(HasVersionType.Scheme.DOI);
		hasVersion.setStringValue(aDOI);

		return mEpicurDoc.toString();
	}
	
	public String getUpdateNewVersionURN(String aNewURN, String aOldURN, String aURL, String aMimeType){
		mUpdateStatus.setType(UpdateStatusType.Type.URN_NEW_VERSION);		
		RecordType record = mEpicur.addNewRecord();
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(aNewURN);
		IsVersionOfType isVersionOf = record.addNewIsVersionOf();
		isVersionOf.setScheme(IsVersionOfType.Scheme.URN_NBN_DE);
		isVersionOf.setStringValue(aOldURN);
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
		resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
		resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
		resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
		resourceIdentifier.setStringValue(aURL);
		FormatType resourceFormat = resource.addNewFormat();
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(aMimeType);
		
		return mEpicurDoc.toString();
	}
	
	public String getUpdateURL(String aURN, String aURL, String aMimeType){
		mUpdateStatus.setType(UpdateStatusType.Type.URL_UPDATE_GENERAL);		
		RecordType record = mEpicur.addNewRecord();
		IdentifierType identifier = record.addNewIdentifier();
		identifier.setScheme(IdentifierType.Scheme.URN_NBN_DE);
		identifier.setStringValue(aURN);
		ResourceType resource = record.addNewResource();
		IdentifierType resourceIdentifier = resource.addNewIdentifier();
		resourceIdentifier.setScheme(IdentifierType.Scheme.URL);
		resourceIdentifier.setType(IdentifierType.Type.FRONTPAGE);
		resourceIdentifier.setRole(IdentifierType.Role.PRIMARY);
		resourceIdentifier.setOrigin(IdentifierType.Origin.ORIGINAL);
		resourceIdentifier.setStringValue(aURL);
		FormatType resourceFormat = resource.addNewFormat();
		resourceFormat.setScheme(FormatType.Scheme.IMT);
		resourceFormat.setStringValue(aMimeType);
		
		return mEpicurDoc.toString();		
	}
	
	
	/**
	 * Qa: Main-method obviously tests URN-Management-Class
	 * Maybe it is also required to create URN manually?
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		URNManagement urn = new URNManagement();
//		System.out.println(urn.getInsertURN("urn:nbn:de:0009-1-123", "http://bla.bla.de", "text/html"));
//		System.out.println(urn.getInsertURNPart("urn:nbn:de:0009-3-2891", "http://www.brains-minds-media.org/archive/289", "text/html", "urn:nbn:de:0009-3-6153"));
		System.out.println(urn.getInsertHasVersion("urn:nbn:de:0009-12-6243", "http://www.elogistics-journal.de/archiv/2006/oktober/624", "text/html", "10.2195/LJ_Not_Ref_Minkin_102006"));
//		urn.setIdentifier("urn:nbn:de:0009-1-123", "http://www.dipp.nrw.de");
//		System.out.println(urn.getXepicur());
	}
}
