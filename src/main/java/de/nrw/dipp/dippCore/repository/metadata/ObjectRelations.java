package de.nrw.dipp.dippCore.repository.metadata;

import java.util.Iterator;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.Fedora;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: ObjectRelations.java</p>
 * <p>Description: Set rdf relations between objects.</p>
 * <p>
 *  <ul>
 *  	<li/>
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
 * @version $Id: ObjectRelations.java,v 1.3 2007/01/05 11:35:02 dippadm Exp $
 */
public class ObjectRelations {

//	private static final String cQuery = "* <http://www.openarchives.org/OAI/2.0/setSpec> *";
//	private static final RIsearch cRIsearch = new RIsearch(RIsearch.cType_Triples, RIsearch.cFormat_rdf_xml, RIsearch.cLang_Triples_spo, cQuery);

	public static synchronized void mapRelation2RDF(DigitalObject aDigitalObject) throws Exception{
		// switch ContentType
		if (aDigitalObject.getCModel().equals(Constant.cContentModelArticle)){
			mapArticle(aDigitalObject);
		}else if (aDigitalObject.getCModel().equals(Constant.cContentModelContainer)){
			mapContainer(aDigitalObject);
		}else if (aDigitalObject.getCModel().equals(Constant.cContentModelData)){
			mapData(aDigitalObject);
		}else if (aDigitalObject.getCModel().equals(Constant.cContentModelJournal)){
			mapJournal(aDigitalObject);
		}		
	}
	
	/*
	 * extended 2006-11-09 by js: article objects can have parts
	 */
	private static void mapArticle(DigitalObject aDigitalObject)throws Exception{
		if (aDigitalObject.getParentList().isEmpty()){
			System.out.println("mapArticle: article objects must have at least one parent object: " + aDigitalObject.getPid());
		}
		Iterator it = aDigitalObject.getParentList().iterator();
		while (it.hasNext()){
			DigitalObject parentObj = (DigitalObject)it.next();
			if (parentObj.getCModel().equals(Constant.cContentModelJournal) ||
					parentObj.getCModel().equals(Constant.cContentModelContainer)){
				aDigitalObject.getRDF().addStatement(Fedora.cIsMemberOfColl, parentObj.getPid());
				parentObj.getRDF().addStatement(Fedora.cHasCollectionMember, aDigitalObject.getPid());
				persistModel(parentObj, "hasCollMember " + aDigitalObject.getPid());
			}else if (parentObj.getCModel().equals(Constant.cContentModelArticle)){
				aDigitalObject.getRDF().addStatement(Fedora.cIsPartof, parentObj.getPid());
				parentObj.getRDF().addStatement(Fedora.cHasPart, aDigitalObject.getPid());
				persistModel(parentObj, "hasPartMember " + aDigitalObject.getPid());				
			}else{
				System.out.println("mapArticle: there is something wrong with the relation of " + aDigitalObject.getPid());
			}
		}
		it = aDigitalObject.getChildList().iterator();
		while (it.hasNext()){
			DigitalObject childObj = (DigitalObject)it.next();
			if (childObj.getCModel().equals(Constant.cContentModelData)){
				aDigitalObject.getRDF().addStatement(Fedora.cHasConstituent, childObj.getPid());
				childObj.getRDF().addStatement(Fedora.cIsConstituentof, aDigitalObject.getPid());
				persistModel(childObj, "isConstituentOf " + aDigitalObject.getPid());
			}else{
				System.out.println("mapArticle: article object " + aDigitalObject.getPid() + " are not allowed to have childs other than datas");			
			}
		}
	}
	
	private static void mapContainer(DigitalObject aDigitalObject) throws Exception{
		if (aDigitalObject.getParentList().isEmpty()){
			System.out.println("mapContainer: container object " + aDigitalObject.getPid() + " must have at least one parent object");
		}
		Iterator it = aDigitalObject.getParentList().iterator();
		while (it.hasNext()){
			DigitalObject parentObj = (DigitalObject)it.next();
			if (parentObj.getCModel().equals(Constant.cContentModelJournal) ||
					parentObj.getCModel().equals(Constant.cContentModelContainer)){
				aDigitalObject.getRDF().addStatement(Fedora.cIsSubsetof, parentObj.getPid());
				parentObj.getRDF().addStatement(Fedora.cHasSubset, aDigitalObject.getPid());
				persistModel(parentObj, "hasSubset of " + aDigitalObject.getPid());
			}else{
				System.out.println("mapContainer: there is something wrong with the 'isParentOf' relation of " + aDigitalObject.getPid());
			}
		}
		it = aDigitalObject.getChildList().iterator();
		while (it.hasNext()){
			DigitalObject childObj = (DigitalObject)it.next();
			if (childObj.getCModel().equals(Constant.cContentModelContainer)){
				aDigitalObject.getRDF().addStatement(Fedora.cHasSubset, childObj.getPid());
				childObj.getRDF().addStatement(Fedora.cIsSubsetof, aDigitalObject.getPid());
				persistModel(childObj, "isSubset of " + aDigitalObject.getPid());
			}else if (childObj.getCModel().equals(Constant.cContentModelArticle)){
				aDigitalObject.getRDF().addStatement(Fedora.cHasCollectionMember, childObj.getPid());
				childObj.getRDF().addStatement(Fedora.cIsMemberOfColl, aDigitalObject.getPid());
				persistModel(childObj, "isMemberOfColl " + aDigitalObject.getPid());
			}else{
				System.out.println("mapContainer: there is something wrong with the 'hasChild' relation of " + aDigitalObject.getPid());
			}
		}
	}
	
	private static void mapData(DigitalObject aDigitalObject)throws Exception{
		Iterator it = aDigitalObject.getParentList().iterator();
		while (it.hasNext()){
			DigitalObject parentObj = (DigitalObject)it.next();
			if (parentObj.getCModel().equals(Constant.cContentModelArticle)){
				aDigitalObject.getRDF().addStatement(Fedora.cIsConstituentof, parentObj.getPid());
				parentObj.getRDF().addStatement(Fedora.cHasConstituent, aDigitalObject.getPid());
				persistModel(parentObj, "hasConstituentOf " + aDigitalObject.getPid());
			}else{
				System.out.println("mapData: there is something wrong with the 'isParentOf' relation of " + aDigitalObject.getPid());
			}
		}			
		if (!aDigitalObject.getChildList().isEmpty()){
			System.out.println("mapData: data object " +  aDigitalObject.getPid() + " are not allowed to have any childs: ");			
		}		
	}
	
	private static void mapJournal(DigitalObject aDigitalObject)throws Exception{
		// parentList should be empty
		Iterator it = aDigitalObject.getChildList().iterator();
		while (it.hasNext()){
			DigitalObject childObj = (DigitalObject)it.next();
			if (childObj.getCModel().equals(Constant.cContentModelContainer)){
				aDigitalObject.getRDF().addStatement(Fedora.cHasSubset, childObj.getPid());
				childObj.getRDF().addStatement(Fedora.cIsSubsetof, aDigitalObject.getPid());
				persistModel(childObj, "isSubset of " + aDigitalObject.getPid());
			}else if (childObj.getCModel().equals(Constant.cContentModelArticle)){
				aDigitalObject.getRDF().addStatement(Fedora.cHasCollectionMember, childObj.getPid());
				childObj.getRDF().addStatement(Fedora.cIsMemberOfColl, aDigitalObject.getPid());
				persistModel(childObj, "isMemberOfColl of " + aDigitalObject.getPid());
			}else{
				System.out.println("mapJournal: there is something wrong with the 'hasChild' relation of " + aDigitalObject.getPid());
			}
		}
	}
	
	public static synchronized void persistModel(DigitalObject aDigitalObject, String aLog) throws Exception{
		DOManagement digObjManage = new DOManagement();
		digObjManage.addOrModify(aDigitalObject.getPid(), "RELS-EXT", "Relationships", "text/xml", aDigitalObject.getRDF().toXMLObject().xmlText().getBytes("UTF-8"), aLog, Constant.cFedoraDatastreamControlGroupInternal);
	}
	
	
	public static synchronized void setRelation(DigitalObject childObject, DigitalObject parentObject, boolean addRelation){
		if (childObject.getCModel().equals(Constant.cContentModelArticle)){
			setRelationArticle(childObject, parentObject, addRelation);
		}else if (childObject.getCModel().equals(Constant.cContentModelContainer)){
			setRelationContainer(childObject, parentObject, addRelation);
		}else if (childObject.getCModel().equals(Constant.cContentModelData)){
			setRelationData(childObject, parentObject, addRelation);
		}
	}
	
	private static void setRelationArticle(DigitalObject childObject, DigitalObject parentObject, boolean addRelation){
		if (parentObject.getCModel().equals(Constant.cContentModelContainer) || parentObject.getCModel().equals(Constant.cContentModelJournal)){
			if (addRelation){
				childObject.getRDF().addStatement(Fedora.cIsMemberOfColl, parentObject.getPid());
				parentObject.getRDF().addStatement(Fedora.cHasCollectionMember, childObject.getPid());				
			}else{
				childObject.getRDF().removeStatement(childObject.getPid(), Fedora.cIsMemberOfColl, parentObject.getPid());
				parentObject.getRDF().removeStatement(parentObject.getPid(), Fedora.cHasCollectionMember, childObject.getPid());
			}
		}
	}
	
	private static void setRelationData(DigitalObject childObject, DigitalObject parentObject, boolean addRelation){
		if (parentObject.getCModel().equals(Constant.cContentModelData)){
			if (addRelation){
				childObject.getRDF().addStatement(Fedora.cIsConstituentof, parentObject.getPid());			
				parentObject.getRDF().addStatement(Fedora.cHasConstituent, childObject.getPid());
			}else{
				childObject.getRDF().removeStatement(childObject.getPid(), Fedora.cIsConstituentof, parentObject.getPid());
				parentObject.getRDF().removeStatement(parentObject.getPid(), Fedora.cHasConstituent, childObject.getPid());				
			}
		}		
	}
	
	private static void setRelationContainer(DigitalObject childObject, DigitalObject parentObject, boolean addRelation){
		if (parentObject.getCModel().equals(Constant.cContentModelContainer) || parentObject.getCModel().equals(Constant.cContentModelJournal)){
			if (addRelation){
				childObject.getRDF().addStatement(Fedora.cIsSubsetof, parentObject.getPid());
				parentObject.getRDF().addStatement(Fedora.cHasSubset, childObject.getPid());				
			}else{
				childObject.getRDF().removeStatement(childObject.getPid(), Fedora.cIsSubsetof, parentObject.getPid());
				parentObject.getRDF().removeStatement(parentObject.getPid(), Fedora.cHasSubset, childObject.getPid());				
			}
		}		
	}
	
	/*
	private static void addRdfOAI(DigitalObject aDigitalObject){
		ServiceManagement servmng = new ServiceManagement();
		QualifiedDublinCore qdc = servmng.getQualifiedDublinCoreMetadata(aDigitalObject.getPid());
		String[] ddc = qdc.getDDC();
		String[] docType = qdc.getDocType();
		String[] pubType = qdc.getPubType();
		aDigitalObject.getRDF().addProperty(OAI.itemID, "oai:dipp.nrw.de:" + aDigitalObject.getPid());
		for (int i = 0; i < ddc.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, cRIsearch.getSubjectID(OAI.setSpec, "ddc:" + ddc[i]));
		}
		for (int i = 0; i < docType.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, cRIsearch.getSubjectID(OAI.setSpec, "doc-type:" + docType[i]));
		}
		for (int i = 0; i < pubType.length; i++){
			aDigitalObject.getRDF().addStatement(Fedora.cIsMemberof, cRIsearch.getSubjectID(OAI.setSpec, "pub-type:" + pubType[i]));
		}
		
	}
	
	private void addOAIds(DigitalObject aDigitalObject){
		// this includes datastreams for oai_dc and oai_urn
		// maybe later we can replace oai_urn with a behavior mechanism 
		// " state='A'"
		// " coverage~dipp-content" + " publisher~dipp-published")));

	}
	
	private void interRelationCheck(){
		
	}
*/	
}
