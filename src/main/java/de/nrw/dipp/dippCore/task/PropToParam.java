/**
 * 
 */
package de.nrw.dipp.dippCore.task;

import java.util.Properties;

/**
 * Class PropToParam
 * 
 * <p><em>Title: </em></p>
 * <p>Description: A simple helper class to convert 
 * Properties into Param Object for DiPP legacy Task Classes 
 * 
 * @author aquast, email
 * creation date: 30.01.2014
 *
 */
public class PropToParam {

	private Properties taskProp = null;
	
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
		
		param.setDoNew(taskProp.getProperty("DoNew").equals("true"));
		param.setDoModify(taskProp.getProperty("DoModify").equals("true"));
		
		
		return param;
	}

	public void setProperties(Properties prop){
		taskProp = prop;
	}
}
