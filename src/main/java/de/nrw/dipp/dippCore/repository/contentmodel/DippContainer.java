package de.nrw.dipp.dippCore.repository.contentmodel;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: DippContainer.java</p>
 * <p>Description: Introduced since DiPP2, this class models a content model for container objects.</p>
 * <p>History: 
 * 	<ul>
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
 * @version $Id: DippContainer.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */

public class DippContainer implements CollectionModel {

	private String mChunkURL = "";
	private String mAbsoluteURL = "";
	private String mMetaType = "";
	private String mMetaName = "";
	
	public String getChunkURL() {
		return mChunkURL;
	}
	public void setChunkURL(String aURL) {
		mChunkURL = aURL;
	}

	public String getAbsoluteURL() {
		return mAbsoluteURL;
	}

	public void setAbsoluteURL(String aURL) {
		mAbsoluteURL = aURL;
	}
	public String getCModel() {
		return Constant.cContentModelContainer;
	}
/*
	public List getChildList() {
		return null;
	}

	public List getParentList() {
		return null;
	}
*/
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

}
