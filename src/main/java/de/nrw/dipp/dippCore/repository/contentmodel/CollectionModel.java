package de.nrw.dipp.dippCore.repository.contentmodel;

public interface CollectionModel extends MetaModel{
	
	public static final String		cCollectionTypeJournal	= "journal";
	public static final String		cCollectionTypeVolume	= "volume";
	public static final String		cCollectionTypeSeries	= "series";
	public static final String		cCollectionTypeCategory	= "category";
	public static final String		cCollectionTypeTopic	= "topic";
	public static final String		cCollectionTypeArticle	= "article";
	public static final String		cCollectionTypeCongress	= "congress";
	public static final String		cCollectionTypeOther	= "other";

	public String getMetaType();
	public void setMetaType(String aMetaType);
	public String getMetaName();
	public void setMetaName(String aMetaName);
	public String getChunkURL();
	public String getAbsoluteURL();
	public void setChunkURL(String aURL);
	public void setAbsoluteURL(String aURL);
}
