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

import java.util.ArrayList;
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
	
	
	private int taskType = -1;
	protected TaskParam tParam = new TaskParam();

	/**
	 * private constructor is accessibly by newInstance method only 
	 */
	protected TaskService() {
		
	}
	
	
	public int getTaskType(){
		return taskType;
	}
	
	protected void setTaskParam(TaskParam TParam){
		tParam = TParam;
	}
	
	protected TaskParam getTaskParam(){
		return tParam;
	}

	protected abstract void convert();
	
	public static class Factory {
		
		public static TaskService newInstance(int taskType, TaskParam tParam){
			TaskService tService = null;

			//workaround for mapping integer to String, used as long as method calls 
			//weren't changed
			
			ArrayList<String> classList = new ArrayList<String>();
			classList.add("TaskXML");
			classList.add("TaskHTML");
			classList.add("TaskDocBook2PDF");
			classList.add("TaskPloneRegister");
			
			TaskParam taskParam = tParam;
			Param param = taskParam.getParam();
			if (param != null){
				try{
					Class serviceClass =  Class.forName(classList.get(taskType));
					tService = (TaskService) serviceClass.newInstance();
					tService.taskType = taskType;
					log.info("initialized new "  + classList.get(taskType));
				}catch(Exception e){
					log.error(e);
				}
			}else{
				log.error("cannot initialize new " + classList.get(taskType));
			}


			// TODO add correct task choice
			return tService;
		}

		public static TaskService newInstance(String taskType, TaskParam tParam){
			TaskService tService = null;
			try{
				Class serviceClass =  Class.forName("de.nrw.dipp.dippCore.task." + taskType);
				tService = (TaskService) serviceClass.newInstance();
				tService.setTaskParam(tParam);
				log.info("initialized new "  + taskType);
			}catch(Exception e){
				log.error(e);
			}

			// TODO add correct task choice
		return tService;
		}

	}

}
