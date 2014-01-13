package de.nrw.dipp.dippCore.repository.workflow;

public class State {
	
	private String mId;

	protected State(String aId){
		mId = aId;
	}
	
	public String getId(){
		return mId;
	}
}
