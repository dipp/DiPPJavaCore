package de.nrw.dipp.dippCore.util;

import java.util.Properties;


/**
 * <p>Title: URNGenerator.java</p>
 * <p>Description: A class implementing an algorithm for URN by computing the checksum.</p>
 *
 * <p>Taken/adapted from The Persistent Identifier Group at Deutsche Bibliothek,
 * <a href="http://www.persistent-identifier.de/?link=316">http://www.persistent-identifier.de/?link=316</a>
 * </p>
 * 
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2004
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: URNGenerator.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class URNGenerator {
	
	private Properties		mKonkordanz = new Properties();
	private char[] urnArray = null;
	public URNGenerator(){
		init();
	}
	
	  /**
	   * <p>Sets the URN namespace and a persistent identifier excluding the checksum.</p>
	   *
	   * @param aURN A URN.
	   */
	public void setURN(String aURN){
		urnArray = aURN.toLowerCase().toCharArray();
		StringBuffer strBuf	= new StringBuffer();
		for (int i = 0; i < urnArray.length; i++){
			strBuf.append(Integer.parseInt(mKonkordanz.getProperty(String.valueOf(urnArray[i]))));
		}
		urnArray = strBuf.toString().toCharArray();
	}
	
	  /**
	   * <p>gets the checksum of the given URN.</p>
	   *
	   * @return checksum.
	   */
	public String getChecksum(){
		String checksum 	= "";
		int ps 			= 0;
		for (int i = 1; i <= urnArray.length; i++){
			ps = ps + i * Integer.parseInt(String.valueOf(urnArray[i-1]));
		}
		int q = ps / Integer.parseInt(String.valueOf(urnArray[urnArray.length - 1]));
		checksum = String.valueOf(q);
		return String.valueOf(checksum.charAt(checksum.length() - 1));
	}
	
	private void init(){
		mKonkordanz.setProperty("0", "1");
		mKonkordanz.setProperty("1", "2");
		mKonkordanz.setProperty("2", "3");
		mKonkordanz.setProperty("3", "4");
		mKonkordanz.setProperty("4", "5");
		mKonkordanz.setProperty("5", "6");
		mKonkordanz.setProperty("6", "7");
		mKonkordanz.setProperty("7", "8");
		mKonkordanz.setProperty("8", "9");
		mKonkordanz.setProperty("9", "41");
		mKonkordanz.setProperty("a", "18");
		mKonkordanz.setProperty("b", "14");
		mKonkordanz.setProperty("c", "19");
		mKonkordanz.setProperty("d", "15");
		mKonkordanz.setProperty("e", "16");
		mKonkordanz.setProperty("f", "21");
		mKonkordanz.setProperty("g", "22");
		mKonkordanz.setProperty("h", "23");
		mKonkordanz.setProperty("i", "24");
		mKonkordanz.setProperty("j", "25");
		mKonkordanz.setProperty("k", "42");
		mKonkordanz.setProperty("l", "26");
		mKonkordanz.setProperty("m", "27");
		mKonkordanz.setProperty("n", "13");
		mKonkordanz.setProperty("o", "28");
		mKonkordanz.setProperty("p", "29");
		mKonkordanz.setProperty("q", "31");
		mKonkordanz.setProperty("r", "12");
		mKonkordanz.setProperty("s", "32");
		mKonkordanz.setProperty("t", "33");
		mKonkordanz.setProperty("u", "11");
		mKonkordanz.setProperty("v", "34");
		mKonkordanz.setProperty("w", "35");
		mKonkordanz.setProperty("x", "36");
		mKonkordanz.setProperty("y", "37");
		mKonkordanz.setProperty("z", "38");
		mKonkordanz.setProperty("-", "39");
		mKonkordanz.setProperty(":", "17");
	}
	
	

}
