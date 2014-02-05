/**
 * TaskXml.java - This file is part of the DiPP Project by hbz
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

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * Class TaskXml
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 03.02.2014
 *
 */
public class TaskXml extends TaskService implements Task {

	// Initiate Logger for TaskXml
	private static Logger log = Logger.getLogger(TaskXml.class);

	boolean isSucceeded = false;

	/**
	 * 
	 */
	public TaskXml() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Upcast upcast = null;
		isSucceeded = false;
		FileUtil fUtil = tParam.getFileUtil();
		String articlePid = tParam.getProperties().getProperty("articlePid");
		
		// Getting QualifiedDublinCore for the File we want to process
		try{			
			DigitalObject articleObj = DOManagement.retrieveDigitalObject(articlePid);
			QualifiedDublinCore qdc = Metadata.getQualifiedDublinCoreMetadata(articlePid);

			log.info("starting TaskXml for id " + articlePid);

			if (!fUtil.getNativeFileMimeType().equals("text/xml")){
				try{
					// rtf upCast needs some time to start wait for this...
					while( (upcast = Upcast.getInstance()) == null ){
						log.info("waiting for UpCast becoming ready to processing");
						wait(2500);				
					}
					log.info("UpCast is ready to process "  + articlePid);
					
					String articleType = "";
					if (qdc.getArticleType().length > 0){
						articleType = qdc.getArticleType(0);
						}

					log.info("UpCast is ready to process "  + articlePid);

					log.debug("TaskXML: " + "upCast.setSourceFile: " + fUtil.getNativeFile().getAbsolutePath());
					upcast.setSourceFile(fUtil.getNativeFile());
					upcast.doConvert(fUtil.getTargetDir(), 1, articleObj.getLabel(), articleType);
					
					log.info("Upcast has processesd XML file successfully");
					File mOutputFile = new File ( fUtil.getTargetDir() + "/" + fUtil.getNativeFile().getName().substring(
							0, fUtil.getNativeFile().getName().lastIndexOf(".")) + ".xml" );
					
					if (fUtil.isCompressedZip() && fUtil.isBatchProcess()){
						// batch process
						log.info("Doing batchprocess with zip file" );
						log.error("Batch is not implemented here, please use deprecated method");
						//batch(fUtil, upcast, articleObj.getLabel());
						}
					}catch(ConvertException e){
						log.error(e);
						e.printStackTrace();
					}finally{
					upcast.release();
					}
				}
			}catch(Exception e){
				log.error(e);
			}finally{
				isSucceeded = true;
			}

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
		return isSucceeded;
	}
}
