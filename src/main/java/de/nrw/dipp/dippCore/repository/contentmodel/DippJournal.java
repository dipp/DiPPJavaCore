package de.nrw.dipp.dippCore.repository.contentmodel;

import de.nrw.dipp.dippCore.util.Constant;

public class DippJournal implements CollectionModel{

	private int			mSerialNumber	= -1;	// serial number of a journal (for URN-management)
	private String		mAbsoluteURL	= null;
	private String		mChunkURL		= null;
	private String		mMetaName		= null;
	private String		mMetaType		= null;
	
	
	public String getCModel() {
		return Constant.cContentModelJournal;
	}
	
	public int getSerialNumber() {
		return mSerialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		mSerialNumber = serialNumber;
	}

	public String getAbsoluteURL() {
		return mAbsoluteURL;
	}
	public String getChunkURL() {
		return mChunkURL;
	}
	public String getMetaName() {
		return mMetaName;
	}
	public void setMetaName(String aMetaName) {
		mMetaName = aMetaName;
	}
	public String getMetaType() {
		return mMetaType;
	}
	public void setMetaType(String aMetaType) {
		mMetaType = aMetaType;
	}
	public void setAbsoluteURL(String aURL) {
		mAbsoluteURL = aURL;
	}
	public void setChunkURL(String aURL) {
		mChunkURL = aURL;
	}
}
