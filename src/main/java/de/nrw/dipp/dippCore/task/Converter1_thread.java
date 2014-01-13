package de.nrw.dipp.dippCore.task;

import java.io.File;


/**
 * <p>Title: UpcastThread.java</p>
 * <p>Description: Thread class for upcast tasks.</p>
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
 * @version 
 */
public class Converter1_thread implements Runnable {

	private static final String	cTOC		= "toc_html";
	private static final String cINDEX		= "index_html";
	
	private File				mSrcFile 			= null;
	private File				mOutputDir			= null;
	private boolean				mIsFinished			= false;
	private String				mTargetFilename		= "";
	private Param				mTask				= null; 
	private String				mSrcFileWithoutExt	= null;
	
	public Converter1_thread(Param aTask){
		/*
		 * Kro deleted todo
		 */
		mTask = aTask;
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		System.out.println("UpCastThread: run for id: " + mTask.getArticlePID());
		try{
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Upcast upcast = Upcast.getInstance();
		try{
		}finally{
			mIsFinished = true;			
			upcast.doNotify();
		}
	}
	
	public boolean isFinished(){
		return mIsFinished;
	}
	
	public String getHtmlFileName(){
		return mTargetFilename;
	}
	

}
