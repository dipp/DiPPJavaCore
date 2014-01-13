package de.nrw.dipp.dippCore.repository.contentmodel;

public interface ContentModel extends MetaModel{
	
	public String getCModel();
	public String getMimeType();
	public void setMimeType(String aMimeType);
}
