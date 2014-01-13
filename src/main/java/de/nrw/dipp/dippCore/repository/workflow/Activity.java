package de.nrw.dipp.dippCore.repository.workflow;

public class Activity {
	
	private String mName;

	protected Activity(String aName){
		mName = aName;
	}
	
	public String getName(){
		return mName;
	}
}
