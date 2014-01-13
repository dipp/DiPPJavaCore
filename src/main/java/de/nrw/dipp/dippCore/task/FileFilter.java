/*
 * Created on 29.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileFilter {

	private static FileFilter mFilter = null;
	private List mFilterList	= null;
	
	private FileFilter(){
		try{
			String aItem = null;
			mFilterList = new Vector();
			DataInputStream inStream = new DataInputStream(getClass().getResourceAsStream("fileFilter.txt"));
			while ( (aItem = inStream.readLine()) != null){
				mFilterList.add(aItem);
			}
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}
	
	public static FileFilter getInstance(){
		if (mFilter == null){
			mFilter = new FileFilter();
		}
		return mFilter;
	}
	
	public List getFilterList(){
		return mFilterList;
	}
}
