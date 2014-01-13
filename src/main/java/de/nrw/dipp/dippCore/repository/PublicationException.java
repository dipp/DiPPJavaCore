package de.nrw.dipp.dippCore.repository;

import java.io.IOException;

public class PublicationException extends IOException{
	
	public PublicationException(){
		super();
	}
	
	public PublicationException(String aMessage){
		super(aMessage);
	}
/*
	public PublicationException(String aMessage, Throwable aCause){
		super(aMessage, aCause);
	}

	public PublicationException(Throwable aCause){
		super(aCause);
	}
*/
}
