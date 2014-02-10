/**
 * TaskPdf.java - This file is part of the DiPP Project by hbz
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

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * Class TaskPdf
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 06.02.2014
 *
 */
public class TaskPdf extends TaskService {

	// Initiate Logger for TaskPdf
	private static Logger log = Logger.getLogger(TaskPdf.class);

	/**
	 * 
	 */
	public TaskPdf() {
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
		log.info("Die interne URL des PDF-Files: " + tParam.getFileUtil().getNativeDocIdent());
		
		// all we have to do here is to copy PDF Datastream into Fedora DataObject
		DOManagement domg = new DOManagement();
		try{
			domg.addDatastream(tParam.getExtMetadata().getObjectPDF(), null, tParam.getFileUtil().getNativeFilename(), "application/pdf", getJournalLabel(), Constant.cFedoraDatastreamControlGroupManaged);
			log.info("added native PDF Document to Content Object \"pdf\"");
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}
}
