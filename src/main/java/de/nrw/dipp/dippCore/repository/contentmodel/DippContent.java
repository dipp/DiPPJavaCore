package de.nrw.dipp.dippCore.repository.contentmodel;


import de.nrw.dipp.dippCore.util.Constant;

public class DippContent implements ContentModel{

	private String mimeType = null;
	
	public String getCModel() {
		return Constant.cContentModelData;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
