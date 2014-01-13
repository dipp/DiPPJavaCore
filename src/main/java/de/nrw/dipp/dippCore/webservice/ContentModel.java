/*
 * Created on 13.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.webservice;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ContentModel {

	public ExtendedMetadata 	getArticleContentMetadata(String aPID);
	public QualifiedDublinCore	getQualifiedDublinCore(String aPID);
	public void					setQualifiedDublinCore(String aPID, QualifiedDublinCore aQDC);
}
