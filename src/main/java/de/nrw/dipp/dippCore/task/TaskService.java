/**
 * TaskService.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
 *
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
 */
package de.nrw.dipp.dippCore.task;

import java.util.Observable;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;

/**
 * Class TaskService
 * 
 * <p><em>Title: </em></p>
 * <p>Description: A class that provides an Factory for different Tasks. 
 * Should make TaskManager much more flexible</p>
 * 
 * @author aquast, email
 * creation date: 27.01.2014
 *
 */
public abstract class TaskService extends Observable implements Task {

	// Initiate Logger for TaskService
	private static Logger log = Logger.getLogger(TaskService.class);
	
	public static final int 	XML   = 0;
	public static final int 	HTML  = 1;
	public static final int 	PDF   = 2;
	public static final int		PLONE = 3;
	public static final int		PDFA  = 4;
	
	private Properties taskProp = new Properties();
	
	//legacy variables
	private ExtendedMetadata	extMetadata 		= null;
	private FileUtil			fileUtil			= null;
	private ConversionStatus	convStatus			= null;

	/**
	 * private constructor is accessibly by newInstnace method only 
	 */
	protected TaskService() {
		
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
		
		param.setDoNew(taskProp.getProperty("DoNew").equals("true"));
		param.setDoModify(taskProp.getProperty("DoModify").equals("true"));
		param.setExtMetadata(extMetadata);
		param.setFileUtil(fileUtil);
		param.setConversionStatus(convStatus);
		
		
		return param;
	}
	
	public static class Factory {
		
		public static TaskService newInstance(int taskType){
			TaskService tService = null;
			
			//Task task = new TaskXML(null);
			switch(taskType){
				case TaskService.XML:
					log.debug("factory creates TaskXML");

					PropToParam pp = new PropToParam();
					pp.setProperties(new Properties());
					Param param = pp.getParam();

					if (param != null){
						tService = new TaskXML(param);
					}else{
						log.error("cannot initialize new TaskXML");
					}

					break;
				
				case TaskService.HTML:
					log.debug("factory creates TaskHTML");

					pp = new PropToParam();
					pp.setProperties(new Properties());
					param = pp.getParam();

					if (param != null){
						tService = new TaskHTML(param);
					}else{
						log.error("cannot initialize new TaskHTML");
					}

					break;

				case TaskService.PDF:
					log.debug("factory creates TaskPDF");

					pp = new PropToParam();
					pp.setProperties(new Properties());
					param = pp.getParam();

					if (param != null){
						tService = new TaskDocBook2PDF(param);
					}else{
						log.error("cannot initialize new TaskPDF");
					}

					break;
				case TaskService.PLONE:
					log.debug("factory creates TaskPlone");

					pp = new PropToParam();
					pp.setProperties(new Properties());
					param = pp.getParam();

					if (param != null){
						tService = new TaskPloneRegister(param);
					}else{
						log.error("cannot initialize new TaskPloneRegister");
					}

					break;

			
			
			}	

			// TODO add correct task choice
			return tService;
		}
	}
		
}
