package de.nrw.dipp.dippCore.task;

public class ConversionStatus {

	public static final int cFlagConversion 	= 1;
	public static final int cFlagXML		 	= 2;
	public static final int cFlagHTML       	= 4;
	public static final int cFlagPDF	 		= 8;
	
	private int bitMask = 0;
	
	public void addBit(int aBit){
		bitMask = bitMask + aBit;
	}
	
	public String getBitMask(){
		return Integer.toBinaryString(bitMask);
	}

}//test
