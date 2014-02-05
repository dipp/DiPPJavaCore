/**
 * 
 */
package de.nrw.dipp.dippCore.task;

import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public abstract class DecoratorTask extends TaskService{

	// Initiate Logger for Class
	private static Logger log = Logger.getLogger(DecoratorTask.class);

	public TaskService baseTask = null;
	
	public void setBaseTask(TaskService task){
		baseTask = task;
	}
	
	public static class Factory{
		public static DecoratorTask newInstance(String decoratorTask, TaskService task){
			DecoratorTask dTask = null;
			
			try {
				Class decoratorClass = Class.forName("de.nrw.dipp.dippCore.task" + decoratorTask);
				dTask = (DecoratorTask) decoratorClass.newInstance();
				dTask.setBaseTask(task);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
			
			return dTask;
		}
		
		public String getString(){
			return "holla";
		}
		
	}
}
