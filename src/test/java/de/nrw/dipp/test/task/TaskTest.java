package de.nrw.dipp.test.task;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.task.Param;
import de.nrw.dipp.dippCore.task.Task;
import de.nrw.dipp.dippCore.task.TaskService;

public class TaskTest extends TaskService implements Task {

	private Param param = new Param();
	
	public TaskTest(Param Param){
		param = Param;
	}
	// Initiate Logger for TaskTest
	private static Logger log = Logger.getLogger(TaskTest.class);

	public TaskTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public Param getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParam(Param aParam) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSucceeded() {
		// TODO Auto-generated method stub
		return false;
	}
}
