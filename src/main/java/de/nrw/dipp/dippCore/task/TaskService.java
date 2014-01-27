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

import org.apache.log4j.Logger;

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
public class TaskService {

	// Initiate Logger for TaskService
	private static Logger log = Logger.getLogger(TaskService.class);
	
	public static final int 	XML   = 0;
	public static final int 	HTML  = 1;
	public static final int 	PDF   = 2;
	public static final int		PLONE = 3;
	
	private Task task = null;

	/**
	 * private constructor is accessibly by newInstnace method only 
	 */
	private TaskService() {
		
	}
	
	
	public class Factory {
		
		public TaskService newInstance(int taskType){
			TaskService tService = null;
			
			switch(taskType){
				case TaskService.XML:
					log.debug("factory creates TaskXML");

					//TODO: Implement Param correctly!!!
					task = new TaskXML(new Param());

					/*
					((TaskXML)task).addObserver(this);
					mHashtableXML.put(aParam.getArticlePID(), task);
					log.info("before starting task");
					*/
					tService = new TaskService();
					break;
				}	

			// TODO add correct task choice
			return tService;
		}
	}
	
	public Task getTask(){
		return task;
	}
}
