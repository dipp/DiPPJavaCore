/*
 * Created on 29.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.FedoraManagement;
import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;

/**
 * <p>Title: TaskDocBook2PDF.java</p>
 * <p>Description: A class for transforming docbook xml into pdf.</p>
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
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: TaskDocBook2PDF.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class TaskDocBook2PDF extends TaskService implements Task {

	private Param	mParam		= null;
	private File	mSrcFile 	= null;
	private boolean	mSucceed 	= false;
	private static final java.util.logging.Logger log
		= java.util.logging.Logger.getLogger(TaskDocBook2PDF.class.getName());

	/**
	 * pre-condition: - Verzeichnis mit xml-Datei  und Unterverzeichnis mit Media-Dateien ist vorhanden
	 * @param aParam
	 */
	public TaskDocBook2PDF( Param aParam ){
		mParam = aParam;
		try{
			FileHandler handler = new FileHandler(Constant.getAbsolutPath() + "/WEB-INF/log/doctransform%u.log", 10000, 8);
			log.addHandler(handler);
			log.setLevel(Level.FINEST);
		}catch(IOException exc){
			exc.printStackTrace();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getObjectID()
	 */
	public String getObjectID() {
		return mParam.getArticlePID();
	}
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getParam()
	 */
	public Param getParam() {
		return mParam;
	}
	
	public void setParam(Param aParam) {
		mParam = aParam;
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#isFinished()
	 */
	public boolean isFinished() {
		return false;
	}
	
	public boolean isSucceeded() {
		return mSucceed;
	}
	
	public void setInputFile(String aFilename){
		mSrcFile = new File(aFilename);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		String srcDir = mSrcFile.getParent(); // "/files/tmp/pdftest";
//		String texFile = mSrcFile.getName().substring(0, mSrcFile.getName().lastIndexOf(".")) + ".tex"; // "out.tex";
		String stylesheet = "/files/share/dblatex/xsl/01_customized.xsl";
		String xmlFile = mSrcFile.getName(); // "in.xml";
		String pdfFile = mSrcFile.getName().substring(0, mSrcFile.getName().lastIndexOf(".")) + ".pdf";
		String latexStyle = Constant.cConfiguration.getStylePDF(mParam.getJournalLabel());
		
		System.out.println("Parameter latex style: " + latexStyle);
		System.out.println("Parameter stylesheet: " + stylesheet);
		System.out.println("Parameter srcDir: " + srcDir);
		System.out.println("Parameter xmlFile: " + xmlFile);
		try{
			// get logo of journal if exist
			copyJournalContentMultimedia(mSrcFile.getParent() + "/");
			// run dblatex
			Process p = Runtime.getRuntime().exec(Constant.getAbsolutPath() + "/WEB-INF/scripts/dblatex.sh " + latexStyle + " " + stylesheet + " " + srcDir + " " + xmlFile);
	//		int rv1 = -1;
			if (p.waitFor() == 0){
				File f = new File(mSrcFile.getParent() + "/" + pdfFile);					
				if (f.exists()){
					ExtendedMetadata mExtMeta = mParam.getExtMetadata();
					if (mParam.isDoNew()){
						addDatastream(mExtMeta.getObjectPDF(), mSrcFile.getParent()	, pdfFile, pdfFile );
						log.finest(mParam.getContentObjectPID() + ": docbook to pdf transformation done; working dir: " + mSrcFile.getParent());
						mSucceed = true;
					}						
				}
			}else{
				log.warning(mParam.getContentObjectPID() + ": docbook to pdf transformation failed; working dir: " + mSrcFile.getParent());
			}
		}catch(InterruptedException intrExc){
			intrExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}finally{
			setChanged();
			notifyObservers(mParam.getArticlePID());			
		}
	}
	
	private void addDatastream(String aPID, String aAbsolutPath, String aRelativeFilename, String aLabel){
		ServiceManagement fi = new ServiceManagement();
		System.out.println("AbsPath: " + aAbsolutPath);
		System.out.println("RelFile: " + aRelativeFilename);
		
		DOManagement domg = new DOManagement();
		try{
			domg.addDatastream(aPID, null, aAbsolutPath + "/" + aRelativeFilename, "application/pdf", aLabel, Constant.cFedoraDatastreamControlGroupManaged);
		}catch(Exception e){
			//TODO change logger to log4j
			log.warning(e.toString());
		}
		//fi.addDataStream(aPID, null, aAbsolutPath + "/" + aRelativeFilename, "application/pdf", aLabel, Constant.cFedoraDatastreamControlGroupManaged);
	}
	
	private void copyJournalContentMultimedia(String aTargetDirectory) throws RemoteException, IOException{
		ServiceManagement fi = new ServiceManagement();
		String jPID = fi.getPidOfJournal(mParam.getJournalLabel());
		ExtendedMetadata extMD = fi.getDiPPExtendedMetadata(jPID);
		if (extMD != null){
			if (extMD.getObjectMultimedia() != null){
				String multimediaPID = extMD.getObjectMultimedia();			
				Hashtable dostreamTable = fi.getDatastreamTable(multimediaPID);
				Enumeration enumKeys = dostreamTable.keys();
				while (enumKeys.hasMoreElements()){
					String dsId = (String)enumKeys.nextElement();
					if (dsId.startsWith("DS")){
						DigitalObjectDatastream doStream = (DigitalObjectDatastream)dostreamTable.get(dsId);
						FileChannel destinationChannel = new FileOutputStream(aTargetDirectory + doStream.getLabel()).getChannel();
						ReadableByteChannel sourceChannel = Channels.newChannel(fi.getDatastream(multimediaPID, dsId));
						ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
						while (sourceChannel.read(buffer) != -1 || buffer.position() > 0){
							buffer.flip(); // Prepare to read from the buffer and write to the file
							destinationChannel.write(buffer); // Write some or all buffer contents
							buffer.compact(); // Discard all bytes that were written and prepare to
						}                     // read more from the file and store them in the buffer.
						sourceChannel.close();
						destinationChannel.close();
					}
				}
			}
		}
	}


}
