/**
 * 
 */
package de.nrw.dipp.dippCore.task;


import org.apache.log4j.Logger;

/**
 * @author aquast
 *
 */
public class TaskGetMd extends DecoratorTask {
	
	// Initiate Logger for Class
	private static Logger log = Logger.getLogger(TaskGetMd.class);
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#getParam()
	 */
	@Override
	public Param getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#setParam(de.nrw.dipp.dippCore.task.Param)
	 */
	@Override
	public void setParam(Param aParam) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#isFinished()
	 */
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#isSucceeded()
	 */
	@Override
	public boolean isSucceeded() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		convert();
		
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.TaskService#convert()
	 */
	@Override
	protected void convert() {

		baseTask.convert();
		log.info("Der BaseTask ist gelaufen, hier würde der nächste dieser Task starten");
		
		
	}

}
