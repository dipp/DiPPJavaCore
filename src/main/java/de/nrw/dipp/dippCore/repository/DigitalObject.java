package de.nrw.dipp.dippCore.repository;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.contentmodel.MetaModel;
import de.nrw.dipp.dippCore.repository.metadata.rdf.RDF;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: DigitalObject.java</p>
 * <p>Description: A wrapper class for encapsuling fedora-digital-object fields.</p>
 * <p>DigitalObject instance seems to represent a Fedora Object with all its behaviors (Metadata).
 * It does <em>not</em> represents the Fedora Object Datastream itself.</p> 
 * <p>DigitalObject consists of the following behaviors:</p>
 * <ul>
 * <li><em>String pid</em> fedora unique identifier</li> 
 * <li><em>String label</em> fedora label, if not an eJournal itself label it is inherit from the eJournal-Instance</li> 
 * <li><em>String ftype</em> ???</li> 
 * <li><em>String state</em> fedora state, either active or inactive</li> 
 *<li><em>String ownerid</em> ???</li> 
 *<li><em>Calendar cDate</em> ??? which Date?</li> 
 *<li><em>Calendar mDate</em> ??? which Date?</li> 
 *<li><em>Calendar dcmDate</em> ??? which Date?</li> 
 *<li><em>String[] bDef</em> ??? behavior Definitions</li> 
 *</ul>
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
 * @version $Id: DigitalObject.java,v 1.2 2006/11/06 11:30:05 dippadm Exp $
 */
public class DigitalObject {

	
    // Get Logger for Class
	private static Logger log = Logger.getLogger(DigitalObject.class);


	// FoXML Metadata fields used in all versions 
	private java.lang.String pid;
    private java.lang.String label;
    private java.lang.String state;
    private java.lang.String ownerId;
    private Calendar cDate;
    private Calendar mDate;
    private Calendar dcmDate;
    private java.lang.String[] title;
    private java.lang.String[] creator;
    private java.lang.String[] subject;
    private java.lang.String[] description;
    private java.lang.String[] publisher;
    private java.lang.String[] contributor;
    private java.lang.String[] date;
    private java.lang.String[] type;
    private java.lang.String[] format;
    private java.lang.String[] identifier;
    private java.lang.String[] source;
    private java.lang.String[] language;
    private java.lang.String[] relation;
    private java.lang.String[] coverage;
    private java.lang.String[] rights;

    // FoXML Metadata fields used in Fedora prior to Versions 3.0
    private java.lang.String[] bDef;
    private java.lang.String[] bMech;
    private java.lang.String fType;
    private java.lang.String cModel;
    
    // TODO workaround: contentTypes, make a better solution
    private List<String> mContentTypes = new Vector<String>();
        
    private MetaModel collectionModel = null;
	private List<DigitalObject>		mChildList		= new Vector<DigitalObject>();
	private List<DigitalObject>		mParentList		= new Vector<DigitalObject>();
	
	private RDF			mRDFRelations	= null;

	public DigitalObject(String aPID){
		if (Constant.cFedoraVersion > 1){
			mRDFRelations = new RDF(aPID);
		}
		pid = aPID;
	}
	
	public void setRDF(RDF aRDF){
		mRDFRelations = aRDF;
	}
	
	public RDF getRDF(){
		return mRDFRelations;
	}
	
	public java.lang.String[] getBDef() {
		return bDef;
	}
	public void setBDef(java.lang.String[] def) {
		bDef = def;
	}
	public java.lang.String[] getBMech() {
		return bMech;
	}
	public void setBMech(java.lang.String[] mech) {
		bMech = mech;
	}
	public Calendar getCDate() {
		return cDate;
	}
	public void setCDate(Calendar date) {
		cDate = date;
	}
	public java.lang.String getCModel() {
		// Hack: if DigitalObject provides no CModel due to changes in 
		// Datamodel from Fedora 2 to Fedora 3 request getObjectProfile Operation
		// from WS API-A
		String model = new String();
		if(collectionModel == null){
			log.debug("CModel is not set for FedoraObject: " + this.pid);
			model = null;
		}
		else{
			model = collectionModel.getCModel();
		}
		return model;
	}
	public void setCModel(java.lang.String model) {
		cModel = model;
		log.info("CModel set to: " + cModel);
	}
	
	public java.lang.String[] getContributor() {
		return contributor;
	}
	public void setContributor(java.lang.String[] contributor) {
		this.contributor = contributor;
	}
	public java.lang.String[] getCoverage() {
		return coverage;
	}
	public void setCoverage(java.lang.String[] coverage) {
		this.coverage = coverage;
	}
	public java.lang.String[] getCreator() {
		return creator;
	}
	public void setCreator(java.lang.String[] creator) {
		this.creator = creator;
	}
	public java.lang.String[] getDate() {
		return date;
	}
	public void setDate(java.lang.String[] date) {
		this.date = date;
	}
	public Calendar getDcmDate() {
		return dcmDate;
	}
	public void setDcmDate(Calendar dcmDate) {
		this.dcmDate = dcmDate;
	}
	public java.lang.String[] getDescription() {
		return description;
	}
	public void setDescription(java.lang.String[] description) {
		this.description = description;
	}
	public java.lang.String[] getFormat() {
		return format;
	}
	public void setFormat(java.lang.String[] format) {
		this.format = format;
	}
	public java.lang.String getFType() {
		return fType;
	}
	public void setFType(java.lang.String type) {
		fType = type;
	}
	public java.lang.String[] getIdentifier() {
		return identifier;
	}
	public void setIdentifier(java.lang.String[] identifier) {
		this.identifier = identifier;
	}
	public java.lang.String getLabel() {
		return label;
	}
	public void setLabel(java.lang.String label) {
		this.label = label;
	}
	public java.lang.String[] getLanguage() {
		return language;
	}
	public void setLanguage(java.lang.String[] language) {
		this.language = language;
	}
	public Calendar getMDate() {
		return mDate;
	}
	public void setMDate(Calendar date) {
		mDate = date;
	}
	public java.lang.String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(java.lang.String ownerId) {
		this.ownerId = ownerId;
	}
	public java.lang.String getPid() {
		return pid;
	}

/*	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}
*/	
	public java.lang.String[] getPublisher() {
		return publisher;
	}
	public void setPublisher(java.lang.String[] publisher) {
		this.publisher = publisher;
	}
	public java.lang.String[] getRelation() {
		return relation;
	}
	public void setRelation(java.lang.String[] relation) {
		this.relation = relation;
	}
	public java.lang.String[] getRights() {
		return rights;
	}
	public void setRights(java.lang.String[] rights) {
		this.rights = rights;
	}
	public java.lang.String[] getSource() {
		return source;
	}
	public void setSource(java.lang.String[] source) {
		this.source = source;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String[] getSubject() {
		return subject;
	}
	public void setSubject(java.lang.String[] subject) {
		this.subject = subject;
	}
	public java.lang.String[] getTitle() {
		return title;
	}
	public void setTitle(java.lang.String[] title) {
		this.title = title;
	}
	public java.lang.String[] getType() {
		return type;
	}
	public void setType(java.lang.String[] type) {
		this.type = type;
	}
	
	public MetaModel getMetaModel() {
		return collectionModel;
	}
	public void setMetaModel(MetaModel contentModel) {
		this.collectionModel = contentModel;
	}
	public List<DigitalObject> getChildList() {
		return mChildList;
	}
	public List<DigitalObject> getParentList() {
		return mParentList;
	}
	public void addParentItem(DigitalObject aItem) {
		mParentList.add(aItem);
	}

	public void addChildItem(DigitalObject aItem) {
		mChildList.add(aItem);
	}
	public boolean isParentOf(DigitalObject aItem) {
		Iterator<DigitalObject> it = mChildList.iterator();
		while (it.hasNext()){
			DigitalObject listItem = (DigitalObject)it.next();
			if (listItem.getPid().equals(aItem.getPid())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isChildOf(DigitalObject aItem) {
		Iterator<DigitalObject> it = mParentList.iterator();
		while (it.hasNext()){
			 DigitalObject listItem = (DigitalObject)it.next();
			if (listItem.getPid().equals(aItem.getPid())){
				return true;
			}
		}
		return false;
	}
	
	public boolean removeChildItem(DigitalObject aItem){
		Iterator<DigitalObject> it = mChildList.iterator();
		while (it.hasNext()){
			DigitalObject listItem = (DigitalObject)it.next();
			if (listItem.getPid().equals(aItem.getPid())){
				mChildList.remove(listItem);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeParentItem(DigitalObject aItem){
		Iterator<DigitalObject> it = mParentList.iterator();
		while (it.hasNext()){
			DigitalObject listItem = (DigitalObject)it.next();
			if (listItem.getPid().equals(aItem.getPid())){
				mParentList.remove(listItem);
				return true;
			}
		}
		return false;
	}

	public void setContentTypeList(List<String> aContentTypes){
		mContentTypes = aContentTypes;
	}
	
	public List<String> getContentTypeList(){
		return mContentTypes;
	}
}
