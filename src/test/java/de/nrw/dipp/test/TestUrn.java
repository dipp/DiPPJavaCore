package de.nrw.dipp.test;

import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.URNGenerator;

public class TestUrn {

	protected static synchronized String getURN(int aSerialNumberJournal, int aPIDArticle){
		String urn = Constant.cNamespaceURN + aSerialNumberJournal + "-" + aPIDArticle;
		URNGenerator urnGen = new URNGenerator();
		urnGen.setURN(urn);
		urn = urn + urnGen.getChecksum();
		return urn;
	}
	protected static synchronized String getURN(String aSuffix){
		String urn = Constant.cNamespaceURN + aSuffix;
		URNGenerator urnGen = new URNGenerator();
		urnGen.setURN(urn);
		urn = urn + urnGen.getChecksum();
		return urn;
	}
	
	public static void main(String[] args){
		System.out.println(TestUrn.getURN("dfsl-v1-de"));
	}

}
