package de.nrw.dipp.dippCore.repository.workflow;

public abstract class WorkflowInstanceImpl implements WorkflowInstance {

	protected WorkflowInstanceImpl(){}
	
	private WorkflowImpl mWorkflow;
	
	
	public Workflow getWorkflow() {
		return getWorkflowImpl();
	}
	
	protected WorkflowImpl getWorkflowImpl(){
		return mWorkflow;
	}

	public State getCurrentState() {
		return null;
	}

	public Activity getExecutableActivities() {
		return null;
	}

	public void invoke(Activity activiy) {
		// TODO no function; Auto-generated method stub

	}

}
