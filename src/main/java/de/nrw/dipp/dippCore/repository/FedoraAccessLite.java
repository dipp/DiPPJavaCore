package de.nrw.dipp.dippCore.repository;

import java.io.IOException;

import de.nrw.dipp.dippCore.util.Constant;

public class FedoraAccessLite {

	private static String url = "http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + 
	"/fedora/get/";
	
	private static final class FedoraAccessLiteHolder{
		static final FedoraAccessLite fal = new FedoraAccessLite();
	}
	
	private FedoraAccessLite(){}
	
	public static FedoraAccessLite getInstance(){
		return FedoraAccessLiteHolder.fal;
	}
	
	public byte[] getDataStreamContentAsBytes(String aPID, String aItemID)throws IOException{
		Download dw = new Download(url);
		// If no Fedora Authorization via http is required	
		// return dw.getDatastreamContentAsBytes(aPID, aItemID, false);
		// If Fedora Authorization via http/https is required:
		return dw.getDatastreamContentAsBytes(aPID, aItemID, true);
	}
}
