/**
 * TaskImage.java - This file is part of the DiPP Project by hbz
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
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class TaskImage
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 10.02.2014
 *
 */
public class TaskImage extends DecoratorTask {

	// Initiate Logger for TaskImage
	private static Logger log = Logger.getLogger(TaskImage.class);
	
	private File sourceFile = null;
	private String sourceFileBaseName = null;
	private Properties perlscriptsProps	= new Properties();
	private boolean succeed = false;


	/**
	 * 
	 */
	public TaskImage() {
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
		File sourceFile = new File(tParam.getProperties().getProperty("outputFile"));

		log.info("Image Resize: " + Constant.cConfiguration.isImageResize(getJournalLabel()));
		
		try{
			perlscriptsProps.load(getClass().getResourceAsStream("perlscripts.properties")); // "perlscripts.properties"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}

		if (Constant.cConfiguration.isImageResize(getJournalLabel())){
			XMLProcessor xmlProc = XMLProcessor.getInstance();
			try {
				xmlProc.setFile(sourceFile);
			} catch (XMLException e1) {
				log.error(e1);
				//e1.printStackTrace();
			}
			List<ImageProperties> imgList = xmlProc.getImageList();
			log.info("TaskImage: ListSize: " + imgList.size());
			Iterator<ImageProperties> it = imgList.iterator();
			while (it.hasNext()){
				ImageProperties imgProps = it.next();
				log.info(perlscriptsProps.getProperty("img") + " " + sourceFile.getParent() + " " +  imgProps.getFilename() + " " + imgProps.getWidth() + " " + imgProps.getHeight());
				Process pImageResize;
				try {
					pImageResize = Runtime.getRuntime()
							.exec(perlscriptsProps.getProperty("img") + " " 
							+ sourceFile.getParent() + " " 
							+  imgProps.getFilename() + " " 
							+ imgProps.getWidth() + " " + imgProps.getHeight());
					if (pImageResize.waitFor() == 0){
						log.info("TaskXML: process image: " + imgProps.getFilename() + " width: " + imgProps.getWidth() + " height: " + imgProps.getHeight());
					}else{
						log.error("Error in TaskXML, imageresize");
					}
				} catch (Exception e) {
					log.error(e);
					//e.printStackTrace();
				}
			}
			
		}

	}
}
