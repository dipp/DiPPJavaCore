/*
 * Created on 25.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Monitor implements Observer{

	private Vector	mTaskList	= new Vector();
	private boolean mIsBusy		= false;
	private ExecuteTask execTask = null;
	
	private static final class ControllerHolder{ 
		static final Monitor controller = new Monitor();
	}
	
	
	
	private Monitor(){
		Upcast.getInstance().addObserver(this);
		execTask = new ExecuteTask();
		execTask.start();		
	}
	
	public static synchronized Monitor getInstance(){
		return ControllerHolder.controller;
	}
	
	public void addNewTask(Param aTask){
		mTaskList.addElement(aTask);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		// check for observable arg0 and object arg1 for next task
		if (arg0 != null){
			if (arg0 instanceof Upcast) {
				if (arg1 instanceof Param) {
					Param task = (Param) arg1;
					
				}
				
			}
		}
		System.out.println("monitor was notified by UpCast");
		mIsBusy = false;
	}
	private class ExecuteTask extends Thread{
		
		public synchronized void run(){
			try{
			while(true){
				if (!mTaskList.isEmpty()){
					if (!mIsBusy){
						mIsBusy = true;
						Param task = (Param)mTaskList.firstElement();
						mTaskList.removeElement(task);
						Converter1_thread upcastThread = new Converter1_thread(task);
						new Thread(upcastThread).start();				
					}
				}
				wait(5000);				
			}
			}catch(InterruptedException interruptExc){
				interruptExc.printStackTrace();
			}
		}
		
		// Workflow: method void setNextTask(Object) ; Object getNextTask()
		// delegate nextTask to a new Thread
	}
	
	
	
}
