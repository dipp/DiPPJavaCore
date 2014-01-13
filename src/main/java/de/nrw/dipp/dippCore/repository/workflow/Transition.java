package de.nrw.dipp.dippCore.repository.workflow;

public class Transition {

	private State mSource;
	private State mDestination;
	
	protected Transition(State aSource, State aDestination){
		mSource = aSource;
		mDestination = aDestination;
	}
	
	public State getSource(){
		return mSource;
	}
	
	public State getDestination(){
		return mDestination;
	}
}
