package de.nrw.dipp.dippCore.repository.workflow;

/**
 * 
 * @author SCHIRRWAGEN
 *
 */
public interface WorkflowInstance {
	
	Workflow getWorkflow();
	
	State getCurrentState();
	
	Activity getExecutableActivities();
	
	void invoke(Activity activiy);
	
}
