/*
 * Created on 06.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.repository;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DigitalObjectDatastream {

	private String mItemID			= null;
	private String mItemLabel		= null;
	private String mItemURL			= null;
	private String mItemMimeType	= null;
	private String mItemCreateDate  = null;
	private long mSize;
	
	
	public String getId() {
		return mItemID;
	}
	public void setId(String itemID) {
		mItemID = itemID;
	}
	public String getLabel() {
		return mItemLabel;
	}
	public void setLabel(String itemLabel) {
		mItemLabel = itemLabel;
	}
	public String getMimeType() {
		return mItemMimeType;
	}
	public void setMimeType(String itemMimeType) {
		mItemMimeType = itemMimeType;
	}
	public String getLocation() {
		return mItemURL;
	}
	public void setLocation(String itemURL) {
		mItemURL = itemURL;
	}
	public long getSize() {
		return mSize;
	}
	public void setSize(long size) {
		// This Setter was formerly protected but due to introducing 
		// a new package WSAdaptor now have to be public :-( 
		mSize = size;
	}
	public void setCreateDate(String itemCreateDate) {
		mItemCreateDate = itemCreateDate;
	}
	public String getCreateDate() {
		return mItemCreateDate;
	}

}
