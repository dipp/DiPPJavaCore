/*
 * Created on 11.05.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.TimerTask;

import de.nrw.dipp.dippCore.util.Constant;


/**
 * <p>Title: FileTask.java</p>
 * <p>Description: A timer task class for scheduled Fedora-Management Tasks.</p>
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
 * @version $Id: FileTask.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class FileTask extends TimerTask {


	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		try{
			purgeTemporaryObjects();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void purgeTemporaryObjects()throws RemoteException{
		
		File dir = new File(Constant.getAbsolutPath() + "/WEB-INF/convert/");
		if (dir.exists()){
			File[] dirContent = dir.listFiles();		
			for (int i = 0; i < dirContent.length; i++){
				if (dirContent[i].isDirectory()){
					int index = dirContent[i].getName().lastIndexOf("_");
					if (index != -1){
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DAY_OF_MONTH, -1);
						long treshold = cal.getTimeInMillis();
						if (Long.parseLong(dirContent[i].getName().substring(index+1)) <= treshold){
							removeDir(dirContent[i]);
						}
					}
				}
			}
		}
		
	}
	
	private void removeDir(File dir){
		File[] dirContent = dir.listFiles();		
		for (int i = 0; i < dirContent.length; i++){
			if (dirContent[i].isDirectory()){
				removeDir(dirContent[i]);
			}
			dirContent[i].delete();			
		}
		dir.delete();
	}
	
}
