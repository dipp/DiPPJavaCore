/**
 * TaskPdfA.java - This file is part of the DiPP Project by hbz
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

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.nrw.dipp.dippCore.util.FileUtil;

/**
 * Class TaskPdfA
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 06.02.2014
 *
 */
public class TaskPdfA extends DecoratorTask {

	// Initiate Logger for TaskPdfA
	private static Logger log = Logger.getLogger(TaskPdfA.class);

	/**
	 * 
	 */
	public TaskPdfA() {
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
		
		File sourceFile = null;
		baseTask.convert();
		tParam = baseTask.getTaskParam();
		FileUtil fUtil = tParam.getFileUtil();
		
		fUtil.getNativeFile();
		log.info("baseTask has finished, starting decoratorTask\n\n");

		//sourceFile = new File(tParam.getProperties().getProperty("outputFile"));
		
		
		callRemoteService(fUtil.getNativeDocIdent());
		
		

	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method calls the remote PDF/A Service via RestFul API</p>
	 *  
	 */
	private void callRemoteService(String pdfUri){
		String uri = "http://nyx.hbz-nrw.de/pdfa/";
		Client client = createClient();
		WebResource wResource = client.resource(uri + "api/convertFromUrl/autoConf");
		wResource = wResource
			.queryParam("inputFile", pdfUri);

		log.info(wResource);
		log.info(wResource.accept(MediaType.APPLICATION_JSON).post(String.class).toString());
		
		
		
	}
	

	

	private Client createClient(){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		return client;
	}

}
