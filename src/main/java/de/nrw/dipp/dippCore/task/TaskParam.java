package de.nrw.dipp.dippCore.task;

import java.util.Properties;

import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;

/**
 * <p>Title: TaskParam.java</p>
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
public class TaskParam {

	private Properties taskProp = null;
	
	
	private ExtendedMetadata	mExtMetadata 		= null;
	private FileUtil			mFileUtil			= null;
	private ConversionStatus	mConvStatus			= null;
	
	public TaskParam(){
		taskProp = new Properties();
	}

	public void setProperties(Properties prop){
		taskProp = prop;
	}
	
	public Properties getProperties(){
		return taskProp;
	}

	
	/**
	 * method makes directly accessibly setProperty method from Properties Object inside 
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, String value){
		taskProp.setProperty(key, value);
	}
	
	public String getProperty(String key){
		return taskProp.getProperty(key);
	}

	public void setConversionStatus(ConversionStatus aConvStatus){
		mConvStatus = aConvStatus;
	}
	
	public ConversionStatus getConversionStatus(){
		return mConvStatus;
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
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Helper method to convert Properties into Param for 
	 * legacy task Classes</p>
	 * 
	 * @return 
	 */
	public Param getParam(){
		Param param = new Param();
		
		param.setArticlePID(taskProp.getProperty("articlePid"));
		param.setContentObjectPID(taskProp.getProperty("contentObjectPid"));
		param.setContentObjectDS(taskProp.getProperty("contentObjectDataStream"));
		param.setJournalLabel(taskProp.getProperty("journalLabel"));
		
		if(taskProp.containsKey("DoNew")){
			param.setDoNew(taskProp.getProperty("DoNew").equals("true"));
		}

		if(taskProp.containsKey("DoModify")){
			param.setDoModify(taskProp.getProperty("DoModify").equals("true"));
		}
		
		param.setExtMetadata(mExtMetadata);
		param.setConversionStatus(mConvStatus);
		param.setFileUtil(mFileUtil);
		
		return param;
	}

}
