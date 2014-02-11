/**
 * TaskMedia.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.request.MimeType;

/**
 * Class TaskMedia
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 10.02.2014
 *
 */
public class TaskMedia extends DecoratorTask {

	// Initiate Logger for TaskMedia
	private static Logger log = Logger.getLogger(TaskMedia.class);

	private File sourceFile = null;

	/**
	 * 
	 */
	public TaskMedia() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#getParam()
	 */
	@Override
	public Param getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#setParam(de.nrw.dipp.dippCore.task.Param)
	 */
	@Override
	public void setParam(Param aParam) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#isFinished()
	 */
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.Task#isSucceeded()
	 */
	@Override
	public boolean isSucceeded() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		convert();

	}

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.task.TaskService#convert()
	 */
	@Override
	protected void convert() {

		baseTask.convert();
		tParam = baseTask.getTaskParam();
		
		log.info("baseTask has finished, starting decoratorTask\n\n");

		Filter nf = new Filter (FileFilter.getInstance().getFilterList());
		// current directory
		sourceFile = new File(tParam.getProperties().getProperty("outputFile"));

		ServiceManagement mFi = new ServiceManagement();
		File dir = new File (sourceFile.getParent());
		String[] strs = dir.list(nf);
		if (tParam.getProperties().containsKey("DoNew")){
			for (int i = 0; i < strs.length; i++) {
				String mimeType = MimeType.getInstance()
						.getMimeType(new File(sourceFile.getParent() + "/" + strs[i]));
				addDatastream(tParam.getExtMetadata().getObjectMultimedia(),
						sourceFile.getParent()	, strs[i], mimeType, strs[i] );
			}
		}else{					
			Properties labelProps = new Properties();;
			try {
				labelProps = mFi.getItemLabelProperties(tParam.getExtMetadata().getObjectMultimedia());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < strs.length; i++) {
				String mimeType = MimeType.getInstance().getMimeType(new File(sourceFile.getParent() + "/" + strs[i]));
				if (labelProps.containsKey(strs[i])){
					try {
						modifyDatastream(tParam.getExtMetadata().getObjectMultimedia(),
								labelProps.getProperty(strs[i]),  sourceFile.getParent(), strs[i], strs[i], mimeType );
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					addDatastream(tParam.getExtMetadata().getObjectMultimedia(),
							sourceFile.getParent(), strs[i], mimeType, strs[i] );
				}
			}
		}

	}
	
	protected synchronized void addDatastream(String aPID, String aAbsolutPath, String aRelativeFilename, String aMimeType, String aLabel){
		log.info("AbsPath: " + aAbsolutPath);
		log.info("RelFile: " + aRelativeFilename);
		
		DOManagement domg = new DOManagement();
		try{
			domg.addDatastream(aPID, null, aAbsolutPath + "/" + aRelativeFilename, aMimeType, aLabel, Constant.cFedoraDatastreamControlGroupManaged);
		}catch(Exception e){
			log.error(e);
		}

	}
	
	protected synchronized void modifyDatastream(String aPID, String aDsId, String aAbsolutPath, String aRelativeFilename, String aLabel, String aMimeType)throws FileNotFoundException, IOException{
		DOManagement doManage = new DOManagement();
		doManage.modifyDatastreamByReference(aPID, aDsId, aLabel, aMimeType, new FileInputStream(aAbsolutPath + "/" + aRelativeFilename), "update Datastream, new conversion");
	}

}
