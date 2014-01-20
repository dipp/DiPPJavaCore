package de.nrw.dipp.dippCore.task;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.exception.DiPPInternalNotFoundException;
import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.ServiceManagement;

/**
 * <p>Title: TaskManager.java</p>
 * <p>Description: A observer class for handling data management and file conversion.</p>
 *
 * <p>History:
 *   <ul>
 *    <li> created on 2005-06-29 </li>
 *    <li> 2006-03-28: registered copy task  </li>
 *    <li> 2006-12-08: registered plone task</li>
 * </p> 
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
 * @version $Id: TaskManager.java,v 1.3 2007/01/05 11:36:30 dippadm Exp $
 */
public class TaskManager implements Observer{
	
	public static final int 	cRegisteredTaskXML   = 0;
	public static final int 	cRegisteredTaskHTML  = 1;
	public static final int 	cRegisteredTaskPDF   = 2;
	public static final int		cRegisteredTaskPlone = 3;
	
	public Hashtable 	mHashtableXML		= new Hashtable();
	public Hashtable 	mHashtableHTML		= new Hashtable();
	public Hashtable 	mHashtablePDF		= new Hashtable();
	public Hashtable	mHashtableNative	= new Hashtable();
	public Hashtable	mHashtablePlone		= new Hashtable();
	
	private static final class ControllerHolder{ 
		static final TaskManager controller = new TaskManager();
	}
	
	
	private TaskManager(){
	}
	
    // Get Logger for TaskManager
	private static Logger log = Logger.getLogger(TaskManager.class);

	public static synchronized TaskManager getInstance(){
		return ControllerHolder.controller;
	}

	public synchronized void addTask(int registeredTask, Param aParam, boolean aStart){
		Task task;
		switch(registeredTask){
			case cRegisteredTaskXML: 
				log.info("calling TaskXML");
				task = new TaskXML(aParam);
				log.info("add Observer");
				((TaskXML)task).addObserver(this);
				mHashtableXML.put(aParam.getArticlePID(), task);
				log.info("before starting task");

				if (aStart){
					log.info("starting task now");
					new Thread(task).start();
					log.info("task started");
				}
				break;
			case cRegisteredTaskHTML:
				log.info("calling TaskHTML");
				task = new TaskHTML(aParam);
				((TaskHTML)task).addObserver(this);
				mHashtableHTML.put(aParam.getArticlePID(), task);
				break;
			case cRegisteredTaskPDF:
//				task = new TaskPDF(aParam);
//				((TaskPDF)task).addObserver(this);
				log.info("calling TaskDocBook2PDF");
				task = new TaskDocBook2PDF(aParam);
				((TaskDocBook2PDF)task).addObserver(this);
				mHashtablePDF.put(aParam.getArticlePID(), task);
				break;
			case cRegisteredTaskPlone:
				log.info("calling TaskPloneRegister");
				task = new TaskPloneRegister(aParam);
				((TaskPloneRegister)task).addObserver(this);
				mHashtablePlone.put(aParam.getArticlePID(), task);
				if (aStart){
					new Thread(task).start();					
				}
				break;
		}		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public synchronized void update(Observable arg0, Object arg1){
		log.debug("current xml tasks in Hashtable: " + mHashtableXML.size());
		log.debug("current html tasks in Hashtable: " + mHashtableHTML.size());
		log.debug("current pdf tasks in Hashtable: " + mHashtablePDF.size());
		log.debug("current plone tasks in Hashtable: " + mHashtablePlone.size());
		String oid = null;
		if (arg1 != null){
			oid = (String)arg1;
		}
		try{
			if (arg0 instanceof TaskHTML){
				mHashtableHTML.remove(oid);
				ConversionStatus convStatus = ((TaskHTML)arg0).getParam().getConversionStatus();
				if ( ((TaskHTML)arg0).isSucceeded()){
					convStatus.addBit((-ConversionStatus.cFlagHTML));
				}
				if (!mHashtablePDF.containsKey(oid)){
					updateConversionStatus(oid, convStatus.getBitMask());
					if (mHashtablePlone.containsKey(oid)){
						new Thread( (Task)mHashtablePlone.get(oid) ).start();						
					}
				}
			}else if (arg0 instanceof TaskXML){
				TaskXML taskXML = (TaskXML)arg0;
				ConversionStatus convStatus = ((TaskXML)arg0).getParam().getConversionStatus();
				if (taskXML.isSucceeded()){
					convStatus.addBit((-ConversionStatus.cFlagXML));
					if (!mHashtableHTML.containsKey(oid) && !mHashtablePDF.containsKey(oid)){
						updateConversionStatus(oid, convStatus.getBitMask());
						if (mHashtablePlone.containsKey(oid)){
							new Thread( (Task)mHashtablePlone.get(oid) ).start();
						}
					}
					if (mHashtableHTML.containsKey(oid) ){
						TaskHTML taskHTML = (TaskHTML)mHashtableHTML.get(oid);
						log.info("TaskMonitor:a taskxml outputfile: " + ((TaskXML)arg0).getOutputFile());
						taskHTML.setInputFile( ((TaskXML)arg0).getOutputFile());
						new Thread((Task)mHashtableHTML.get(oid) ).start();
					}
					if (mHashtablePDF.containsKey(oid) ){
						TaskDocBook2PDF taskPDF = (TaskDocBook2PDF)mHashtablePDF.get(oid);
						log.info("TaskMonitor:b taskxml outputfile: " + ((TaskXML)arg0).getOutputFile());
						taskPDF.setInputFile( ((TaskXML)arg0).getOutputFile());
						new Thread((Task)mHashtablePDF.get(oid) ).start();
					}
				}else{
					log.error("TaskXML not succeeded!");
					mHashtableHTML.remove(oid);
					mHashtablePDF.remove(oid);
					updateConversionStatus(oid, convStatus.getBitMask());
					if (mHashtablePlone.containsKey(oid)){
						new Thread( (Task)mHashtablePlone.get(oid) ).start();
					}
				}
				mHashtableXML.remove(oid);
			}else if (arg0 instanceof TaskDocBook2PDF){
				mHashtablePDF.remove(oid);
				ConversionStatus convStatus = ((TaskDocBook2PDF)arg0).getParam().getConversionStatus();
				if ( ((TaskDocBook2PDF)arg0).isSucceeded()){
					convStatus.addBit((-ConversionStatus.cFlagPDF));
				}
				if (!mHashtableHTML.containsKey(oid)){
					updateConversionStatus(oid, convStatus.getBitMask());
					if (mHashtablePlone.containsKey(oid)){
						new Thread( (Task)mHashtablePlone.get(oid) ).start();
					}
				}
			}else if (arg0 instanceof TaskPloneRegister){
				mHashtablePlone.remove(oid);
				
			}
		}catch(DiPPInternalNotFoundException err){
			err.printStackTrace();
		}
	}
	
	private synchronized void updateConversionStatus(String aOID, String aConversionStatus){
		ServiceManagement fi = new ServiceManagement();
		try{
			fi.setArticleConvertedStatus(aOID, aConversionStatus);
			log.info(aOID + " converted Status: " + aConversionStatus);
		}catch(IOException ioExc){
			ioExc.printStackTrace();				
		}		
	}
	
}
