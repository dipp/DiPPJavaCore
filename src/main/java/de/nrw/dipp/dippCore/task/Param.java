package de.nrw.dipp.dippCore.task;

import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;

/**
 * <p>Title: Param.java</p>
 * <p>Description: A bean class for storing conversation task properties.</p>
 *
 * -----------------------------------------------------------------------------
 *
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: Param.java,v 1.2 2006/11/06 11:31:44 dippadm Exp $
 */
public class Param {

	private String				mArticlePID			= null;
	private String				mContentObjectPID	= null;
	private String				mContentObjectDS	= null;
	private String				mJournalLabel		= null;
	
	private boolean				mDoNew				= false;
	private boolean				mDoModify			= false;
	private ExtendedMetadata	mExtMetadata 		= null;
	private FileUtil			mFileUtil			= null;
	private ConversionStatus	mConvStatus			= null;
	
	public Param(){
		
	}
	
	public void setConversionStatus(ConversionStatus aConvStatus){
		mConvStatus = aConvStatus;
	}
	
	public ConversionStatus getConversionStatus(){
		return mConvStatus;
	}
	
	public String getArticlePID() {
		return mArticlePID;
	}
	public void setArticlePID(String articlePID) {
		mArticlePID = articlePID;
	}
	public String getJournalLabel() {
		return mJournalLabel;
	}
	public void setJournalLabel(String journalLabel) {
		mJournalLabel = journalLabel;
	}
	public String getContentObjectDS() {
		return mContentObjectDS;
	}
	public void setContentObjectDS(String contentObjectDS) {
		mContentObjectDS = contentObjectDS;
	}
	public String getContentObjectPID() {
		return mContentObjectPID;
	}
	public void setContentObjectPID(String contentObjectPID) {
		mContentObjectPID = contentObjectPID;
	}
	public boolean isDoModify() {
		return mDoModify;
	}
	public void setDoModify(boolean doModify) {
		mDoModify = doModify;
	}
	public boolean isDoNew() {
		return mDoNew;
	}
	public void setDoNew(boolean doNew) {
		mDoNew = doNew;
	}
	public ExtendedMetadata getExtMetadata() {
		return mExtMetadata;
	}
	public void setExtMetadata(ExtendedMetadata extMetadata) {
		mExtMetadata = extMetadata;
	}
	public void setFileUtil(FileUtil aFileUtil){
		mFileUtil = aFileUtil;
	}
	public FileUtil getFileUtil(){
		return mFileUtil;
	}
	
}
