package de.nrw.dipp.dippCore.repository.workflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorkflowImpl implements Workflow {

	private State 	mInitialState;
	private Set 	transitions = new HashSet();
	private Map		states		= new HashMap();
	private Map		activities	= new HashMap();
	
	protected WorkflowImpl (State aState){
		mInitialState = aState;
	}
	
	public State getInitialState() {
		return mInitialState;
	}

	public Transition[] getLeavingTransitions(State aState) {
		Set leavingTransitions = new HashSet();
		Transition[] transitions = getTransistions();
		for (int i = 0; i < transitions.length; i++){
			if (transitions[i].getSource() == aState){
				leavingTransitions.add(transitions[i]);
			}
		}
		return (Transition[]) leavingTransitions.toArray(new Transition[leavingTransitions.size()]);
	}
	
	private void addState(State aState){
		states.put(aState.getId(), aState);
	}
	
	protected void addTransition(Transition aTransition){
		transitions.add(aTransition);
		addState(aTransition.getSource());
		addState(aTransition.getDestination());
	}
	
	protected Transition[] getTransistions(){
		return (Transition[])transitions.toArray(new Transition[transitions.size()]);
	}
	
	protected State getDestination(Transition aTransition){
		return ((Transition)aTransition).getDestination();
	}
	
	protected void addActivity(Activity aActivity){
		activities.put(aActivity.getName(), aActivity);
	}
	
	public Activity getActivity(String aName) throws WorkflowException{
		if (!activities.containsKey(aName)){
			throw new WorkflowException("Workflow does not contain the activity '" + aName + "'!");
		}
		return (Activity)activities.get(aName);
	}

}
