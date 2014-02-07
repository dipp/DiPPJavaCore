/**
 * GenericTaskTest.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.test.task;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.nrw.dipp.dippCore.repository.Download;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.task.DecoratorTask;
import de.nrw.dipp.dippCore.task.TaskParam;
import de.nrw.dipp.dippCore.task.TaskService;
import de.nrw.dipp.dippCore.task.TaskXml;
import de.nrw.dipp.dippCore.util.Config;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;

/**
 * Class GenericTaskTest
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 03.02.2014
 *
 */
public class GenericTaskTest {

	// Initiate Logger for GenericTaskTest
	private static Logger log = Logger.getLogger(GenericTaskTest.class);

	private String articlePid = "temp:1878";
	private ExtendedMetadata extendedMetadata = null; 
	/**
	 * 
	 */
	public GenericTaskTest() {
		// TODO Auto-generated constructor stub
	}

	@Test public void processTaskXml(){
		
		TaskParam tParam = new TaskParam();
		tParam.setProperties(getProp());
		tParam.setFileUtil(getFileUtilRtf());
		setExtendedMetadata();
		tParam.setExtMetadata(extendedMetadata);
		
		TaskService taskXml = TaskService.Factory.newInstance("TaskXml", tParam);
		DecoratorTask dTask = DecoratorTask.Factory.newInstance("TaskGetMd", taskXml);
		DecoratorTask dTask0 = DecoratorTask.Factory.newInstance("TaskHtml", dTask);
		DecoratorTask dTask1 = DecoratorTask.Factory.newInstance("TaskPloneReg", dTask0);
	
		
		Thread thread = new Thread(dTask1);
		thread.setName("TaskXml Thread0");
		thread.start();
		/*
		TaskService taskXml1 = TaskService.Factory.newInstance("TaskXml", tParam);
		DecoratorTask dTask1 = DecoratorTask.Factory.newInstance("TaskGetMd", taskXml1);

		
		Thread thread1 = new Thread(dTask1);
		thread1.setName("TaskXml Thread1");
		thread1.start();
		*/
		
		}

	@Test public void processTaskPdf(){
		
		
		TaskParam tParam = new TaskParam();
		tParam.setProperties(getProp());
		tParam.setFileUtil(getFileUtilPdf());
		
		//setExtendedMetadata();
		//tParam.setExtMetadata(extendedMetadata);
				
		TaskService taskPdf = TaskService.Factory.newInstance("TaskPdf", tParam);
		DecoratorTask dTaskPdf = DecoratorTask.Factory.newInstance("TaskPdfA", taskPdf);

		Thread thread1 = new Thread(dTaskPdf);
		thread1.setName("TaskPdf Thread1");
		thread1.start();

		
		}

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {

		Constant.setAbsolutPath("/home/aquast/git/dippCoreMvn");

		GenericTaskTest gTTest = new GenericTaskTest();
		gTTest.processTaskXml();
		gTTest.processTaskPdf();

	}
	

	public Properties getProp(){
		Properties taskProp = new Properties();
		
		taskProp.setProperty("articlePid", articlePid);
				
		return taskProp;
	}

	public FileUtil getFileUtilRtf(){
		FileUtil fUtil = new FileUtil();
		String nativeDocIdent = "http://www.dipp.nrw.de/download/Beispiel.rtf";
		fUtil.create(articlePid);
		fUtil.setNativeFileMimeType("text/rtf");
		try {
			Download dw = new Download(nativeDocIdent);
			dw.downloadToFile(fUtil.getNativeFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		fUtil.setNativeDocIdent(nativeDocIdent);
		log.info("fileUtil found MimeType " + fUtil.getNativeFileMimeType());
		return fUtil;
	}
	
	public FileUtil getFileUtilPdf(){
		FileUtil fUtil = new FileUtil();
		String nativeDocIdent = "http://www.zeitenblicke.de/2009/2/wunder/dippArticle.pdf";
		fUtil.create(articlePid);
		fUtil.setNativeFileMimeType("application/pdf");
		

		fUtil.setNativeDocIdent(nativeDocIdent);
		return fUtil;
	}

	public void setExtendedMetadata(){
		extendedMetadata = ServiceManagement.getDiPPExtendedMetadata(articlePid);
	}
}
