package de.nrw.dipp.dippCore.repository.workflow;

public class WorkflowException extends Exception{
	
	public WorkflowException(){
		super();		
	}
	
	public WorkflowException(String aMessage){
		super(aMessage);
	}
	
	public WorkflowException(String aMessage, Throwable aCause){
		super(aMessage, aCause);
	}
	
	public WorkflowException(Throwable aCause){
		super(aCause);
	}

}
