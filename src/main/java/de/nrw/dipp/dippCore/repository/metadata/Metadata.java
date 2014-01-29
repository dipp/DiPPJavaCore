package de.nrw.dipp.dippCore.repository.metadata;

import gov.ojp.it.jxdm.iso6392B.x10.LanguageCodeType;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.doaj.element.RecordType;
import org.doaj.element.RecordsDocument;
import org.openarchives.oai.x20.oaiDc.ArticleType;
import org.openarchives.oai.x20.oaiDc.BK;
import org.openarchives.oai.x20.oaiDc.DDC;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.DocType;
import org.openarchives.oai.x20.oaiDc.ISBN;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.JEL;
import org.openarchives.oai.x20.oaiDc.LCC;
import org.openarchives.oai.x20.oaiDc.LCSH;
import org.openarchives.oai.x20.oaiDc.MESH;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;
import org.openarchives.oai.x20.oaiDc.PACS;
import org.openarchives.oai.x20.oaiDc.PubType;
import org.openarchives.oai.x20.oaiDc.RVK;
import org.openarchives.oai.x20.oaiDc.SWD;
import org.openarchives.oai.x20.oaiDc.UDC;
import org.openarchives.oai.x20.oaiDc.URL;
import org.openarchives.oai.x20.oaiDc.URN;
import org.purl.dc.elements.x11.Keyword;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.elements.x11.VCard;
import org.purl.dc.terms.PartType;

import de.nrw.dipp.definitions.admmetadata.AdmMdDocument;
import de.nrw.dipp.definitions.admmetadata.ElementType;
import de.nrw.dipp.definitions.admmetadata.Reference;
import de.nrw.dipp.definitions.extmetadata.Content;
import de.nrw.dipp.definitions.extmetadata.ExtMdDocument;
import de.nrw.dipp.dippCore.exception.DiPPXMLProcessingException;
import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.FedoraAccess;
import de.nrw.dipp.dippCore.repository.FedoraAccessLite;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.contentmodel.CollectionModel;
import de.nrw.dipp.dippCore.repository.contentmodel.DippArticle;
import de.nrw.dipp.dippCore.repository.contentmodel.DippContainer;
import de.nrw.dipp.dippCore.repository.contentmodel.DippJournal;
import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreQualified;
import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreSimplified;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RDF;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.Fedora;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.URNManagement;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.Contributor;
import de.nrw.dipp.dippCore.webservice.CreatorCorporated;
import de.nrw.dipp.dippCore.webservice.CreatorPerson;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.IdentNumberType;
import de.nrw.dipp.dippCore.webservice.Part;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import de.nrw.dipp.dippCore.webservice.SubjectClassified;

/**
 * <p>Title: Metadata.java</p>
 * <p>Description: A class parsing from and writing to XML-Metadata-Streams.</p>
 * <p>
 *  <ul>
 *  	<li>modified 2006-04-19: creation of data objects now with the DOManager, added rdf datastream  -> not yet completed</li>
 *      <li>TODO move adm and ext metadata methods from ServiceManagement to this class</li>
 *  </ul>
 * </p>
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
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: Metadata.java,v 1.3 2007/01/05 11:35:02 dippadm Exp $
 */

public class Metadata {

    // Get Logger for this Class
	private static Logger log = Logger.getLogger(Metadata.class);
	
	public static synchronized void setHierarchyByDiPPAdministrativeMetaData(DigitalObject aDigitalObject)throws RemoteException{
		AdmMdDocument admDoc = null;
		try{
			admDoc = AdmMdDocument.Factory.parse(new String(FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(aDigitalObject.getPid(), "DiPPAdm")));
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
		AdmMdDocument.AdmMd aAdm = admDoc.getAdmMd();
		if (aAdm.isSetIsChildOf()){
			Reference[] refArray = aAdm.getIsChildOf().getReferenceArray();
			for (int i = 0; i < refArray.length; i++){
				Reference ref = refArray[i];
				DigitalObject parent = new DigitalObject(ref.getItemID());
				if (ref.getType() == ElementType.Enum.forInt(ElementType.INT_LOGIC)){
					parent.setMetaModel(new DippContainer()); 
				}else{
					parent.setMetaModel(new DippArticle());
				}
				if (parent.getPid() == null || parent.getPid().equals("")){
					System.out.println("ServiceMng: error in getItem: parentItem for " + aDigitalObject.getPid() + " has no pid");
				}else{
					aDigitalObject.addParentItem(parent);
				}
			}			
		}
		if (aAdm.isSetIsParentOf()){
			Reference[] refArray = aAdm.getIsParentOf().getReferenceArray();
			for (int i = 0; i < refArray.length; i++){
				Reference ref = refArray[i];
				DigitalObject child = new DigitalObject(ref.getItemID());
				if (ref.getType() == ElementType.Enum.forInt(ElementType.INT_LOGIC)){
					child.setMetaModel(new DippContainer()); 
				}else{
					child.setMetaModel(new DippArticle());
				}
				if (child.getPid() == null || child.getPid().equals("")){
					System.out.println("ServiceMng: error in getItem: parentItem for " + aDigitalObject.getPid() + " has no pid");
				}else{
					aDigitalObject.addChildItem(child);
				}
			}
		}
		if (aDigitalObject.getMetaModel() instanceof CollectionModel) {
			CollectionModel cModel = (CollectionModel)aDigitalObject.getMetaModel() ;
			if (aAdm.isSetChunkURL()){
				cModel.setChunkURL(aAdm.getChunkURL());
			}
			if (aAdm.isSetAbsoluteURL()){
				cModel.setAbsoluteURL(aAdm.getAbsoluteURL());
			}
		}
	}	

	
	public static synchronized AdmMdDocument createRepositoryAdministrativeMetadata(DigitalObject aDigitalObject) throws DiPPXMLProcessingException{
		AdmMdDocument admDoc = AdmMdDocument.Factory.newInstance();
		AdmMdDocument.AdmMd adm = admDoc.addNewAdmMd();
		if (aDigitalObject.getMetaModel() instanceof CollectionModel){
			//System.out.println("Metadata createRepAdmMetadata aDigObj is of Type logic");
			adm.setType(ElementType.Enum.forInt(ElementType.INT_LOGIC));
			if (aDigitalObject.getCModel().equals(Constant.cContentModelJournal)){
				adm.setSerialNumber(new BigInteger( ((DippJournal)aDigitalObject.getMetaModel()).getSerialNumber() + ""));
			}
		}else{
			//System.out.println("Metadata createRepAdmMetadata aDigObj is of Type content");
			adm.setType(ElementType.Enum.forInt(ElementType.INT_CONTENT));			
		}
		if (!aDigitalObject.getParentList().isEmpty()){
			//System.out.println("Metadata createRepAdmMetadata parentList not empty");
			AdmMdDocument.AdmMd.IsChildOf parents = adm.addNewIsChildOf();
			Iterator itParent = aDigitalObject.getParentList().iterator();
			while (itParent.hasNext()){
				DigitalObject parentItem = (DigitalObject)itParent.next();
				Reference reference = parents.addNewReference();
				reference.setItemID(parentItem.getPid());
				if (parentItem.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
					reference.setType(ElementType.Enum.forInt(ElementType.INT_CONTENT));
				}else{
					reference.setType(ElementType.Enum.forInt(ElementType.INT_LOGIC));					
				}
			}
		}
		if (!aDigitalObject.getChildList().isEmpty()){
			//System.out.println("Metadata createRepAdmMetadata childList not empty");
			AdmMdDocument.AdmMd.IsParentOf childs = adm.addNewIsParentOf();
			Iterator itChild = aDigitalObject.getChildList().iterator();
			while (itChild.hasNext()){
				DigitalObject childItem = (DigitalObject)itChild.next();
				Reference reference = childs.addNewReference();
				reference.setItemID(childItem.getPid());
				if (childItem.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
					reference.setType(ElementType.Enum.forInt(ElementType.INT_CONTENT));					
				}else{
					reference.setType(ElementType.Enum.forInt(ElementType.INT_LOGIC));					
				}
			}
		}
		AdmMdDocument.AdmMd.MetaName metaName = adm.addNewMetaName();
		//System.out.println("Metadata2: " + ((CollectionModel)aDigitalObject.getMetaModel()).getMetaName());
		metaName.setStringValue(((CollectionModel)aDigitalObject.getMetaModel()).getMetaName());
		try{
			metaName.setOfType(AdmMdDocument.AdmMd.MetaName.OfType.Enum.forString(((CollectionModel)aDigitalObject.getMetaModel()).getMetaType()));
		}catch(Exception e){
			throw new DiPPXMLProcessingException("Metatype " + ((CollectionModel)aDigitalObject.getMetaModel()).getMetaType() + " does not exist");
		}
		
		adm.setChunkURL(((CollectionModel)aDigitalObject.getMetaModel()).getChunkURL());
		adm.setAbsoluteURL(((CollectionModel)aDigitalObject.getMetaModel()).getAbsoluteURL());
		return admDoc;
	}
	
	/**
	 * this method creates a metadata container for the ExtMd format
	 * @param aDigObj
	 * @return ExtMd Metadata
	 */
	public static synchronized ExtMdDocument createRepositoryExtendedMetadataContainer(DigitalObject aDigObj){
		DippArticle model = (DippArticle)aDigObj.getMetaModel();
		ExtMdDocument extDoc = ExtMdDocument.Factory.newInstance();
		ExtMdDocument.ExtMd ext = extDoc.addNewExtMd();
		ext.setIsConverted(model.isConverted());
		ext.setIsLocked(model.isLocked());
		ext.setIsNew(model.isNew());		
		Content content = ext.addNewContent();
		return extDoc;
	}
	
	/**
	 * <p><em>Title: Returns a list of possible Content Types for Content Objects</em></p>
	 * <p>Description: </p>
	 * 
	 * @return <code>List of String</code>
	 */
	private static synchronized List<String> getContentTypes(){
		List<String> types = new Vector<String>();
		types.add("html");
		types.add("multimedia");
		types.add("native");
		types.add("other");
		types.add("pdf");
		types.add("xml");
		types.add("supplementary");
		return types;
	}
	
	/**
	 * method was declared as deprecated by JS, Qa changes it and bring it into use again
	 * see createRepositoryExtendedMetadataContainer
	 * @param aTxtObject
	 * @return
	 */
	public static synchronized ExtMdDocument createRepositoryExtendedMetadata(DigitalObject aTxtObject){
		DippArticle cModel = (DippArticle)aTxtObject.getMetaModel();
		ExtMdDocument extDoc = ExtMdDocument.Factory.newInstance();
		ExtMdDocument.ExtMd ext = extDoc.addNewExtMd();
		ext.setIsConverted(cModel.isConverted());
		ext.setIsLocked(cModel.isLocked());
		ext.setIsNew(cModel.isNew());
		
		List<DigitalObject> contObjs = aTxtObject.getChildList();
		Content content = ext.addNewContent();
		de.nrw.dipp.definitions.extmetadata.Reference ref = null;
		for (DigitalObject contObj: contObjs){
			log.debug("ContentObject-Pid: " + contObj.getPid());
			log.debug("ContentObject-Pid: " + contObj.getTitle()[0]);
			if (contObj.getTitle()[0].equals("html")){
				ref = content.addNewHtml();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("multimedia")){
				ref = content.addNewMultimedia();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("native")){
				ref = content.addNewNative();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("other")){
				ref = content.addNewOther();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("pdf")){
				ref = content.addNewPdf();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("xml")){
				ref = content.addNewXml();
				ref.setItemID(contObj.getPid());			
			}
			if (contObj.getTitle()[0].equals("supplementary")){
				ref = content.addNewSupplementary();
				ref.setItemID(contObj.getPid());			
			}
		}		
		return extDoc;
	}
	
/*	public static synchronized ExtMdDocument createRepositoryJournalExtendedMetadata(Collection aJournalObject){
		ExtMdDocument extDoc = ExtMdDocument.Factory.newInstance();
		ExtMdDocument.ExtMd ext = extDoc.addNewExtMd();
		
		
		Content content = ext.addNewContent();
		de.nrw.dipp.definitions.extmetadata.Reference ref = content.addNewMultimedia();
		ref.setItemID(createDataObject(aJournalObject.getLabel(), "multimedia", aJournalObject.getPID()));
		
		return extDoc;
	}
*/	
	public static synchronized XmlObject createSetInfoObject(DigitalObject digObj){
		
		// first create a XmlObject according DC simple schema
		// this gets only one element
		DublinCoreSimplified dc = new DublinCoreSimplified();
		String[] description = digObj.getDescription();
		dc.addDecsription(description[0]);
		
		
		//now change NameSpace of Document element 
		// senseless ;-)
		QName qName = new QName("setDescriptions");
		QName oaiNsp = new QName("http://www.openarchives.org/OAI/2.0/oai_dc/", "dc", "oai_dc");
		QName dcNsp = new QName("http://purl.org/dc/elements/1.1/", "description", "dc");
		//opt.setLoadReplaceDocumentElement(qName);
		XmlOptions opt = new XmlOptions();
		opt.setSaveSyntheticDocumentElement(qName);
		opt.setSavePrettyPrint();
		opt.setSaveAggresiveNamespaces();
		XmlObject setInfo = XmlObject.Factory.newInstance(opt);
		setInfo.xmlText();
		XmlCursor cursor = setInfo.newCursor();
		cursor.toNextToken();
		cursor.beginElement("setDescriptions");
		cursor.beginElement("setDescription");
		cursor.beginElement(oaiNsp);
		cursor.beginElement(dcNsp);
		cursor.insertElementWithText(dcNsp, description[0]);
		//cursor.insertElement(dc.getSimplifiedDublinCore().xmlText());
		//setInfo = dc.getSimplifiedDublinCore();
		log.info(setInfo.xmlText(opt));
		
		return setInfo;
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: creates an DublinCoreSimplified xmlBeans representation. 
	 * Unfortunately this is not equivalent to the OAI_DC xmlBeans representation</p>
	 * 
	 * @param aDigitalObject
	 * @param aQDC
	 * @param aTypeList
	 * @param aDDC
	 * @param isTest
	 * @return 
	 */
	public static synchronized XmlObject createRepositoryDublinCoreSimplified(DigitalObject aDigitalObject, MetadataDocument.Metadata aQDC, List aTypeList, List aDDC, boolean isTest){
		DublinCoreSimplified dc = new DublinCoreSimplified();
		Iterator it = aDDC.iterator();
		while (it.hasNext()){
			dc.addSubject("ddc:" + (String)it.next());
		}
		it = aTypeList.iterator();
		while (it.hasNext()){
			dc.addType((String)it.next());
		}
		if (aDigitalObject.getMetaModel().getCModel().equals(Constant.cContentModelArticle)){
			if (isTest){
				dc.addCoverage(Constant.cCoverageTestContent);				
			}else{
				dc.addCoverage(Constant.cCoverageDippContent);
			}
			dc.addPublisher(Constant.cPublisherDippUnPublished);
			DippArticle txtArticle = (DippArticle)aDigitalObject.getMetaModel();
			if (txtArticle.isConverted()){
				dc.addDecsription(Constant.cDecsriptionDippConverted);
			}else if (txtArticle.getConversionStatus().trim().length() > 0){
				dc.addDecsription(txtArticle.getConversionStatus());
			}
			dc.addTitle(aQDC.getTitleArray(0).getStringValue());
			if (aQDC.getCreatorArray() != null){
				if (aQDC.getCreatorArray().length > 0){
					for (int i = 0; i < aQDC.getCreatorArray().length; i++){
						VCard vcard = aQDC.getCreatorArray(i);
						if (vcard.getType().getStringValue().equals(Constant.cCreatorPerson)){
							dc.addCreator(vcard.getFirstName() + " " + vcard.getLastName());
						}
					}
				}
			}
			dc.addSource(txtArticle.getIdentifierSource() );
			dc.addIdentifier(txtArticle.getIdentifierNativeDoc());
		}else{
			dc.addTitle(aDigitalObject.getTitle()[0]);
			if (aDigitalObject.getCModel().equals(Constant.cContentModelContainer)){
				if (aDigitalObject.getParentList().isEmpty()){
					System.out.println("Metadata error: parentList is empty");
				}else if (((DigitalObject)aDigitalObject.getParentList().get(0)).getPid() == null){
					System.out.println("Metadata error: parentObj pid is null");					
				}
				dc.addSource(((DigitalObject)aDigitalObject.getParentList().get(0)).getPid());
			}
		}
		Iterator relationIt = aDigitalObject.getChildList().iterator();
		while (relationIt.hasNext()){
			DigitalObject relationItem = (DigitalObject)relationIt.next();
			dc.addRelation("isParentOf " + relationItem.getPid());
		}
		relationIt = aDigitalObject.getParentList().iterator();
		while (relationIt.hasNext()){
			DigitalObject relationItem = (DigitalObject)relationIt.next();
			dc.addRelation("isChildOf " + relationItem.getPid());
		}
		return dc.getSimplifiedDublinCore();
	}

	
	// Qa: This Method should be moved to ServiceManagement, because it handles DOManagement 
	//requests and does not work with metadata. So I try to move it.
	/*	private static synchronized String createDataObject(String aLabel, String aType, DigitalObject aParentObject){
		String pid = null;
		DOManagement digObjMgt = new DOManagement();
		try{
			pid = digObjMgt.init(Constant.cPIDNamespaceContent, null, aLabel, Constant.cContentModelData);		
			if (Constant.cFedoraVersion == 2){
				digObjMgt.addDatastreamToDigObj("RELS-EXT", "Relationships", "text/xml", "RELS-EXT1.0", getRDF(pid, aParentObject), "");
			}
			digObjMgt.addDatastreamToDigObj("DC", "DC record", "text/xml", "DC1.0", Metadata.createDataObjectDC(aParentObject, aType), Constant.cFedoraMetadataClassDescriptive);
			digObjMgt.ingestFedoraObject("ingest of content object");
			log.info("Metadata: createDataObject: " + pid);
		}catch(RemoteException remoteExc){
			remoteExc.printStackTrace();
		}
		return pid;
	}	
*/	
	public static XmlObject getRDF(String aPID, DigitalObject aParentObj){
		RDF rdf = new RDF(aPID);
		rdf.addStatement(Fedora.cIsConstituentof, aParentObj.getPid());
		aParentObj.getRDF().addStatement(Fedora.cHasConstituent, aPID);
		return rdf.toXMLObject();
	}
	
	public static XmlObject createDataObjectDC(DigitalObject aParentObject, String aType){
		DublinCoreSimplified dc = new DublinCoreSimplified();
		//System.out.println("Metadata: createDataObjectDC: relation to " + aParentObject.getPid());
		dc.addRelation(aParentObject.getPid());
		dc.addTitle(aType);
		return dc.getSimplifiedDublinCore();		
	}	
	
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Reads in the objects qDC metadata from Datastream into a 
	 * QualifiedDublinCore XmlBeans object which is defined by the axis Framework</p>
	 * 
	 * @param aArticlePID the FedoraObject PID
	 * @return QualifiedDublinCore xmlBens representation required for websevice
	 */
	public static QualifiedDublinCore getQualifiedDublinCoreMetadata(String aArticlePID){
		QualifiedDublinCore qdcMetadata = new QualifiedDublinCore();
		
		try{
//			byte[] stream = FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(aArticlePID, "QDC");
			byte[] stream = FedoraAccessLite.getInstance().getDataStreamContentAsBytes(aArticlePID, "QDC");
			MetadataDocument qdcDoc = MetadataDocument.Factory.parse(new String(stream, "UTF-8"));
			MetadataDocument.Metadata qdc = qdcDoc.getMetadata();

			// read Abstract Element into QualifiedDublinCore
			List<Element> elemList = new Vector<Element>(qdc.sizeOfAbstractArray());
			for (int i = 0; i < qdc.sizeOfAbstractArray(); i++){
				SimpleLiteral lit = qdc.getAbstractArray(i);
				Element element = new Element();
				element.setLang(lit.getLang());
				element.setValue(lit.getStringValue());
				elemList.add(element);
			}
			qdcMetadata.setDCTermsAbstract((Element[])elemList.toArray(new Element[0]));

			// read Title Element into QualifiedDublinCore
			elemList = new Vector<Element>(qdc.sizeOfTitleArray());
			for (int i = 0; i < qdc.sizeOfTitleArray(); i++){
				SimpleLiteral lit = qdc.getTitleArray(i);
				Element element = new Element();
				element.setLang(lit.getLang());
				element.setValue(lit.getStringValue());
				elemList.add(element);
			}
			qdcMetadata.setTitle((Element[])elemList.toArray(new Element[0]));
			
			// read Alternative Title Element into QualifiedDublinCore
			elemList = new Vector<Element>(qdc.sizeOfAlternativeArray());
			for (int i = 0; i < qdc.sizeOfAlternativeArray(); i++){
				SimpleLiteral lit = qdc.getAlternativeArray(i);
				Element element = new Element();
				element.setLang(lit.getLang());
				if (lit.isSetType()){
					element.setType(lit.getType());
				}
				element.setValue(lit.getStringValue());
				elemList.add(element);
			}
			qdcMetadata.setAlternative((Element[])elemList.toArray(new Element[0]));

			
			// read Contributor VCards into QualifiedDublinCore 
			List<de.nrw.dipp.dippCore.webservice.VCard> elemListPerson = new Vector<de.nrw.dipp.dippCore.webservice.VCard>();
			List<de.nrw.dipp.dippCore.webservice.VCard> elemListCorporated = new Vector<de.nrw.dipp.dippCore.webservice.VCard>();
			List<de.nrw.dipp.dippCore.webservice.VCard> elemListContributor = new Vector<de.nrw.dipp.dippCore.webservice.VCard>();
			for (int i = 0; i < qdc.sizeOfCreatorArray(); i++){
				VCard vcard = qdc.getCreatorArray(i);
				de.nrw.dipp.dippCore.webservice.VCard creator = getCreator(vcard);
				if (creator != null){
					if (creator instanceof CreatorPerson){
						elemListPerson.add(creator);
					}else if (creator instanceof CreatorCorporated){
						elemListCorporated.add(creator);
//					}else if (creator instanceof Contributor){
//						elemListContributor.add(creator);
					}
				}
			}
			qdcMetadata.setCreatorPerson((CreatorPerson[])elemListPerson.toArray(new CreatorPerson[0]));
			qdcMetadata.setCreatorCorporated((CreatorCorporated[])elemListCorporated.toArray(new CreatorCorporated[0]));
			
			// read Contributors into QualifiedDublinCore
			for (int i = 0; i < qdc.sizeOfContributorArray(); i++){
				VCard vcard = qdc.getContributorArray(i);
				de.nrw.dipp.dippCore.webservice.VCard contributor = getCreator(vcard);
				if (contributor != null){
					elemListContributor.add((de.nrw.dipp.dippCore.webservice.VCard)contributor);					
				}
			}
			qdcMetadata.setContributor((Contributor[])elemListContributor.toArray(new Contributor[0]));

			//log.info("Creator: " + qdcMetadata.getCreatorPerson(0));
			//log.info("Coporated: " + qdcMetadata.getCreatorCorporated(0));
			//log.info("Contributor: " + qdcMetadata.getContributor(0));
			// read DDC Strings and unclassified Subjects into QualifiedDublinCore 
			List<String> elemDDC = new Vector<String>();
			List<String> elemUnclassified = new Vector<String>();
			List<SubjectClassified> subjectList = new Vector<SubjectClassified>();
			for (int i = 0; i < qdc.sizeOfSubjectArray(); i++){
				Keyword kw = qdc.getSubjectArray(i);
				
				for( int j = 0; j < kw.sizeOfDdcArray(); j++){
					elemDDC.add(kw.getDdcArray(j));
				}
				for( int j = 0; j < kw.sizeOfUnclassifiedArray(); j++){
					elemUnclassified.add(kw.getUnclassifiedArray(j));
				}

				for (int j = 0; j < kw.sizeOfClassifiedArray(); j++){
					SubjectClassified subjClassified = new SubjectClassified();
					subjClassified.setSubjectClassified(kw.getClassifiedArray(j));
					SimpleLiteral lit = kw.getClassificationArray(j);
					subjClassified.setClassificationIdent(lit.getStringValue());
					subjClassified.setClassificationSystem(DublinCoreQualified.getClassificationSystem(lit.schemaType()));
					subjectList.add(subjClassified);
				}				
				
			}
			qdcMetadata.setDDC(elemDDC.toArray(new String[0])); //kw.getDdcArray());
			qdcMetadata.setSubject(elemUnclassified.toArray(new String[0])); // kw.getUnclassifiedArray());
			qdcMetadata.setSubjectClassified(subjectList.toArray(new SubjectClassified[0]));				
			
			// read Citation into QualifiedDublinCore
			List<Citation> citationList = new Vector<Citation>(qdc.sizeOfBibliographicCitationArray());
			for (int i = 0; i < qdc.sizeOfBibliographicCitationArray(); i++){
				org.purl.dc.terms.Citation fedoraCitation = qdc.getBibliographicCitationArray(i);
				
				Citation citation = new Citation(); 
				citation.setJournalTitle(fedoraCitation.getJournalTitle());
				citation.setJournalIssueDate(fedoraCitation.getJournalIssueDate());
				citation.setJournalIssueNumber(fedoraCitation.getJournalIssueNumber());
				citation.setJournalVolume(fedoraCitation.getJournalVolume());
				citationList.add(citation);
			}
			qdcMetadata.setBibliographicCitation(citationList.toArray(new Citation[0]));
			
			// read Datestamps into QualifiedDublinCore
			for (int i = 0; i < qdc.sizeOfIssuedArray(); i++){
				SimpleLiteral lit = qdc.getIssuedArray(i);
				qdcMetadata.setIssued(getCalendar(lit.getStringValue()));
			}

			for (int i = 0; i < qdc.sizeOfCreatedArray(); i++){
				SimpleLiteral lit = qdc.getCreatedArray(i);
				qdcMetadata.setCreated(getCalendar(lit.getStringValue()));
			}
			
			for (int i = 0; i < qdc.sizeOfModifiedArray(); i++){
				SimpleLiteral lit = qdc.getModifiedArray(i);
				qdcMetadata.setModified(getCalendar(lit.getStringValue()));
			}
			
			for (int i = 0; i < qdc.sizeOfValidArray(); i++){
				SimpleLiteral lit = qdc.getValidArray(i);
				qdcMetadata.setValid(getCalendar(lit.getStringValue()));
			}


			// some more date information added 
			for (int i = 0; i < qdc.sizeOfDateAcceptedArray(); i++){
				SimpleLiteral lit = qdc.getDateAcceptedArray(i);
				qdcMetadata.setDateAccepted(getCalendar(lit.getStringValue()));
			}

			for (int i = 0; i < qdc.sizeOfDateSubmittedArray(); i++){
				SimpleLiteral lit = qdc.getDateSubmittedArray(i);
				qdcMetadata.setDateSubmitted(getCalendar(lit.getStringValue()));
			}
			for (int i = 0; i < qdc.sizeOfDateCopyrightedArray(); i++){
				SimpleLiteral lit = qdc.getDateCopyrightedArray(i);
				qdcMetadata.setDateCopyrighted(getCalendar(lit.getStringValue()));
			}
			
			// read Types into Array
			List<String> elemDocList = new Vector<String>();
			List<String> elemPubList = new Vector<String>();
			List<String> elemArticleList = new Vector<String>();
			
			
			for (int i = 0; i < qdc.sizeOfTypeArray(); i++){
				SimpleLiteral lit = qdc.getTypeArray(i);
				if ((lit.schemaType().getName().toString()).equals(DocType.type.getName().toString())){
					elemDocList.add(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(PubType.type.getName().toString())){
					elemPubList.add(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(ArticleType.type.getName().toString())){
					elemArticleList.add(lit.getStringValue());
				}
			}
			qdcMetadata.setDocType((String[])elemDocList.toArray(new String[0]));
			qdcMetadata.setPubType((String[])elemPubList.toArray(new String[0]));
			qdcMetadata.setArticleType((String[])elemArticleList.toArray(new String[0]));
			
			List<String> stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfFormatArray(); i++){
				SimpleLiteral lit = qdc.getFormatArray(i);
				stringList.add(lit.getStringValue());
			}
			qdcMetadata.setFormat(stringList.toArray(new String[0]));

			
			stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfIdentifierArray(); i++){
				SimpleLiteral lit = qdc.getIdentifierArray(i);
				if (lit.schemaType().getName().toString().equals(URL.type.getName().toString())){
					qdcMetadata.setIdentifierURL(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(DOI.type.getName().toString())){
					qdcMetadata.setIdentifierDOI(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(URN.type.getName().toString())){
					qdcMetadata.setIdentifierURN(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(ISBN.type.getName().toString())){
					qdcMetadata.setIdentifierISBN(lit.getStringValue());
				}else if (lit.schemaType().getName().toString().equals(ISSN.type.getName().toString())){
					qdcMetadata.setIdentifierISSN(lit.getStringValue());
				}else{
					stringList.add(lit.getStringValue());
				}				
			}
			qdcMetadata.setIdentifier(stringList.toArray(new String[0]));
			
			// read Language into QualifiedDublinCore
			stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfLanguageArray(); i++){
				SimpleLiteral lit = qdc.getLanguageArray(i);
				stringList.add(lit.getStringValue());
			}
			qdcMetadata.setLanguage(stringList.toArray(new String[0]));
			
			// read Part informations into QualifiedDublinCore
			qdcMetadata.setIsPartOf((Part[])getPartOfList(qdc.getIsPartOfArray(), qdc.sizeOfIsPartOfArray()).toArray(new Part[0]));
			qdcMetadata.setHasPart((Part[])getPartOfList(qdc.getHasPartArray(), qdc.sizeOfHasPartArray()).toArray(new Part[0]));
			qdcMetadata.setIsVersionOf((Part[])getPartOfList(qdc.getIsVersionOfArray(), qdc.sizeOfIsVersionOfArray()).toArray(new Part[0]));

			stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfRightsArray(); i++){
				SimpleLiteral lit = qdc.getRightsArray(i);
				stringList.add(lit.getStringValue());
			}
			qdcMetadata.setRights(stringList.toArray(new String[0]));

			stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfPublisherArray(); i++){
				SimpleLiteral lit = qdc.getPublisherArray(i);
				stringList.add(lit.getStringValue());
			}
			qdcMetadata.setPublisher(stringList.toArray(new String[0]));

			stringList = new Vector<String>();
			for (int i = 0; i < qdc.sizeOfSourceArray(); i++){
				SimpleLiteral lit = qdc.getSourceArray(i);
				stringList.add(lit.getStringValue());
			}
			qdcMetadata.setSource(stringList.toArray(new String[0]));

			elemList = new Vector<Element>();
			for (int i = 0; i < qdc.sizeOfTableOfContentsArray(); i++){
				SimpleLiteral lit = qdc.getTableOfContentsArray(i);
				Element elem = new Element();
				elem.setLang(lit.getLang());
				elem.setValue(lit.getStringValue());
				elemList.add(elem);
			}
			qdcMetadata.setTableOfContents(elemList.toArray(new Element[0]));

		}catch(RemoteException remExc){
			remExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
		
		return qdcMetadata;
		
	}
	
	/**
	 * 
	 * @param aFedoraVCard
	 * @return
	 */
	private static de.nrw.dipp.dippCore.webservice.VCard getCreator(VCard aFedoraVCard){
		de.nrw.dipp.dippCore.webservice.VCard creator = null;
		if (aFedoraVCard.isSetType()){
			if (aFedoraVCard.getType().getStringValue().equals("person")){						
				creator = new CreatorPerson();
			}else if (aFedoraVCard.getType().getStringValue().equals("corporation")){
				creator = new CreatorCorporated();				
			}else{
				creator = new Contributor();
				if (aFedoraVCard.isSetRole()){
					creator.setRole(aFedoraVCard.getRole() );
				}
			}
			creator.setFirstName(aFedoraVCard.getFirstName());
			if (creator instanceof CreatorCorporated) {
				creator.setInstitutionelAuthor(aFedoraVCard.getLastName());
			}else{
				creator.setLastName(aFedoraVCard.getLastName());				
			}
			if (aFedoraVCard.isSetAcademicTitle()){
				creator.setAcademicTitle(aFedoraVCard.getAcademicTitle());					
			}
			if (aFedoraVCard.isSetEmail()){
				creator.setEmailAddress(aFedoraVCard.getEmail());
			}
			if (aFedoraVCard.isSetGkdIdentifier()){
				creator.setGKDIdentNumber(aFedoraVCard.getGkdIdentifier());
			}
			if (aFedoraVCard.isSetOrganization()){
				creator.setOrganization(aFedoraVCard.getOrganization());
			}
			if (aFedoraVCard.isSetPndIdentifier()){
				creator.setPNDIdentNumber(aFedoraVCard.getPndIdentifier());
			}
			if (aFedoraVCard.isSetPostal()){
				creator.setPostalAddress(aFedoraVCard.getPostal());
			}
			if (aFedoraVCard.isSetGkdIdentifier()){
				creator.setGKDIdentNumber(aFedoraVCard.getGkdIdentifier());						
			}
			//starting adding new Identifier fields to system logic
			if (aFedoraVCard.isSetDippIdentifier()){
				creator.setDippIdentNumber(aFedoraVCard.getDippIdentifier());						
			}
			if (aFedoraVCard.getIdentifierArray() != null){

				creator.getIdentNumber();
				IdentNumberType[] id = new IdentNumberType[aFedoraVCard.getIdentifierArray().length];
				
				creator.setIdentNumber(id);
				
				for(int i=0; i< aFedoraVCard.getIdentifierArray().length; i++){
					creator.setIdentNumber(i, new IdentNumberType());
					creator.getIdentNumber(i).setIdentNumber(aFedoraVCard.getIdentifierArray(i).getIdentifier());
					creator.getIdentNumber(i).setType(aFedoraVCard.getIdentifierArray(i).getType());
				}
			}

		}
		return creator;		
	}

	private static List getPartOfList(PartType[] aArray, int aSize){
		List elemList = new Vector();
		for (int i = 0; i < aSize; i++){
			PartType partFedora = aArray[i];
			Part partWebService = new Part();
			partWebService.setTitle(partFedora.getTitle().getStringValue());
			for (int j = 0; j < partFedora.sizeOfIdentifierArray(); j++){
				SimpleLiteral partIdentLit = partFedora.getIdentifierArray(j);
				if (partIdentLit.schemaType().getName().toString().equals(URL.type.getName().toString())){
					partWebService.setURL(partIdentLit.getStringValue());						
				}else if (partIdentLit.schemaType().getName().toString().equals(URN.type.getName().toString())){
					partWebService.setURN(partIdentLit.getStringValue());
				}
			}
			elemList.add(partWebService);			
		}
		return elemList;
	}
	
	private static Calendar getCalendar(String aFormattedDateString){
		Calendar cal = Calendar.getInstance();
		try{
			Scanner scanner = new Scanner(aFormattedDateString).useDelimiter("\\s*-\\s*");
			int year = scanner.nextInt();
			int month = scanner.nextInt();
			int day = scanner.nextInt();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, (month -1));
			cal.set(Calendar.DAY_OF_MONTH, day);
		}catch(InputMismatchException mismatchExc){
			mismatchExc.printStackTrace();					
		}				
		return cal;
	}

	/**
	 * <p>a Method that adds some Journal Metadata to a Article qdc Metadata Object</p>
	 * <ul>
	 * <li>at the moment it just adds the ISSN as an identifier</li>
	 * </ul> 
	 * @param aQDC Article qdc Metadata object
	 * @param aLabel Label of Article and Journal
	 * @return Article Metadata object enriched with some journal informations 
	 */
	public static MetadataDocument.Metadata addParentJournalQdc(MetadataDocument.Metadata aQDC, String aLabel){
		ServiceManagement smgnt = new ServiceManagement();
		String jPid = smgnt.getPidOfJournal(aLabel);
		log.info("Journal Pid: " + jPid);
		
		MetadataDocument jQdcDoc = null;
		DOManagement dom = new DOManagement();
		try{
			byte[] jStream = dom.getDatastream(jPid, "QDC");
			jQdcDoc = MetadataDocument.Factory.parse(new String(jStream, "UTF-8"));
		}
		catch(Exception e){
			log.error(e);
		}
		
		//get ISSN from Journal QDC and add ISSN to Article QDC 
		MetadataDocument.Metadata qdc = jQdcDoc.getMetadata();
		SimpleLiteral lit = null;
		for(int i=0; i<qdc.sizeOfIdentifierArray(); i++){
			if ((qdc.getIdentifierArray(i).schemaType().getName().toString()).equals(ISSN.type.getName().toString())){
				//issn = qdc.getIdentifierArray(i).getStringValue().replace("ISSN:", "");
				lit = qdc.getIdentifierArray(i);
				aQDC.addNewIdentifier();
				aQDC.setIdentifierArray(aQDC.sizeOfIdentifierArray()-1 , lit);
			}
		}			
		return aQDC;
	}	
}