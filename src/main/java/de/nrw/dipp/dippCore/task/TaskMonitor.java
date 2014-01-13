/*
 * Created on 29.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.util.Vector;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TaskMonitor extends Thread{
	
	Vector mTaskList	= new Vector();
	
	public void registerTask(Task aTask){
		mTaskList.add(aTask);
	}
	
	public synchronized void run(){
/*
		try{
			while(true){
				for (int i = 0; i < mTaskList.size(); i++){
					if ( ((Task)mTaskList.get(i)).isFinished() ){
						mTaskList.remove(i);
					}
				}
				
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
*/		
	}

}
