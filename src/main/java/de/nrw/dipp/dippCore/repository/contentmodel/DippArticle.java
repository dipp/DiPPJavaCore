package de.nrw.dipp.dippCore.repository.contentmodel;

import java.util.Hashtable;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: DippArticle.java</p>
 * <p>Description: Introduced for Fedora V2, this class models 
 * a content model for article objects and makes the old TextArticle 
 * class obsolet.</p>
 * <p>History: 
 * 	<ul>
 * 		<li>created on 2006/03/21</li>
 * 		<li>modified on 2006/03/30: public set|get ConversionStatus</li>
 *  </ul>
 * </p>
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
 * @version $Id: DippArticle.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class DippArticle implements CollectionModel{

	private String		mConversionStatus	= "";
	private boolean		isNew			= true;
	private boolean 	isLocked		= true;
	private Hashtable<String,DippContent> mContentObjects = new Hashtable<String,DippContent>();
	private String		mIdentifier		= "";
	private String		mIdentifierSource	= "";
	private String		mMetaType			= null;
	private String		mMetaName			= null;
	private String		mAbsoluteURL	= null;
	private String		mChunkURL		= null;

	
	public String getCModel() {
		return Constant.cContentModelArticle;
	}
	public boolean isConverted() {		
		return mConversionStatus.equals(Constant.cDecsriptionDippConverted);
	}


	public void setConversionStatus(String aConversionStatus) {
		mConversionStatus = aConversionStatus;
	}
	
	public String getConversionStatus(){
		return mConversionStatus;
	}
	
	public String getIdentifierNativeDoc() {
		return mIdentifier;
	}
	public void setIdentifierNativeDoc(String identifier) {
		mIdentifier = identifier;
	}
	public String getIdentifierSource() {
		return mIdentifierSource;
	}
	public void setIdentifierSource(String identifierSource) {
		mIdentifierSource = identifierSource;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public void addContentObject(String aType, DippContent aContent){
		mContentObjects.put(aType, aContent);
	}
	public DippContent getContentObject(String aType){
		return (DippContent)mContentObjects.get(aType);
	}
	public String getMetaType() {
		return mMetaType;
	}
	public void setMetaType(String aMetaType) {
		mMetaType = aMetaType;
	}
	public String getMetaName() {
		return mMetaName;
	}
	public void setMetaName(String aMetaName) {
		mMetaName = aMetaName;
	}
	public String getAbsoluteURL() {
		return mAbsoluteURL;
	}
	public String getChunkURL() {
		return mChunkURL;
	}
	public void setAbsoluteURL(String aURL) {
		mAbsoluteURL = aURL;
	}
	public void setChunkURL(String aURL) {
		mChunkURL = aURL;
	}
	
}
