package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Properties;
import java.util.Vector;
import java.lang.StringBuffer;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.exception.DiPPInternalNotFoundException;
import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.util.request.MimeType;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;


/**
 * <p>Title: TaskXML.java</p>
 * <p>Description: A class for managing Fedora-Requests.</p>
 * <p>
 *  <ul>
 *  	<li>created on 29.06.2005</li>
 *      <li>changed on 20.06.2006: supports DOI</li>
 *  </ul>
 * </p>
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
 * @version $Id: TaskXML.java,v 1.4 2007/01/05 11:43:14 dippadm Exp $
 */
public class TaskXML extends Observable implements Task {

    // Get Logger for TaskXML
	private static Logger log = Logger.getLogger(TaskXML.class);
	
	private Param	mParam	= null;
	
//	private File				mSrcFile 			= null;
//	private File				mOutputDir			= null;
//	private String				mSrcFileWithoutExt	= null;
	private ServiceManagement		mFi					= new ServiceManagement();
	private DOManagement domg	= new DOManagement();
	private File				mOutputFile			= null;
	private boolean				mSucceed			= false;				
	private Properties			mPerlscriptsProps	= new Properties();
	
	public TaskXML( Param aParam ){
		mParam = aParam;
		try{
			mPerlscriptsProps.load(getClass().getResourceAsStream("perlscripts.properties")); // "perlscripts.properties"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
			log.error(ioExc);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getObjectID()
	 */
	public synchronized String getObjectID() {
		return mParam.getArticlePID();
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getParam()
	 */
	public synchronized Param getParam() {
		return mParam;
	}
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#setParam(de.nrw.dipp.task.Param)
	 */
	public synchronized void setParam(Param aParam) {
		mParam = aParam;
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#isFinished()
	 */
	public synchronized boolean isFinished() {
		return false;
	}
	
	public boolean isSucceeded() {
		return mSucceed;
	}	
	
	public synchronized String getOutputFile()throws DiPPInternalNotFoundException{
		if (mOutputFile == null){
			throw new DiPPInternalNotFoundException("TaskXML: XML output file not set");
		}
		return mOutputFile.getAbsolutePath();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		Upcast upcast = null;
		FileUtil fu = mParam.getFileUtil();
		
		try{
			DigitalObject articleObj = DOManagement.retrieveDigitalObject(mParam.getArticlePID());
			
			System.out.println("TaskXML for id " + getObjectID() );
			QualifiedDublinCore qdc = Metadata.getQualifiedDublinCoreMetadata(mParam.getArticlePID());
			
			// Qa: Test if first conversion took place already, if not start it:
			// task are: convert rtf-files to DocBookXml 
			if (!fu.getNativeFileMimeType().equals("text/xml")){
				try{
					// rtf
					while( (upcast = Upcast.getInstance()) == null ){
						System.out.println("TaskXML for id " + getObjectID() + " wait");
						wait(2500);				
					}
					log.info("TaskXML for id " + getObjectID() + " got upcast");
//					mFi.setArticleConvertedStatus(mParam.getArticlePID(), false);
					
					String articleType = "";
					if (qdc.getArticleType().length > 0){
						articleType = qdc.getArticleType(0);
					}
					log.info("TaskXML: " + "upCast.setSourceFile: " + fu.getNativeFile().getAbsolutePath());
					upcast.setSourceFile(fu.getNativeFile());
					upcast.doConvert(fu.getTargetDir(), 1, articleObj.getLabel(), articleType);
					
					System.out.println("UpcastThread: 1.Teil OK");
					mOutputFile = new File ( mParam.getFileUtil().getTargetDir() + "/" + mParam.getFileUtil().getNativeFile().getName().substring(
							0, mParam.getFileUtil().getNativeFile().getName().lastIndexOf(".")) + ".xml" );
					
					if (fu.isCompressedZip() && fu.isBatchProcess()){
						// batch process
						log.info("Doing batchprocess with zip file" );
						batch(fu, upcast, articleObj.getLabel());
					}
				}catch(ConvertException e){
					e.printStackTrace();
				}finally{
					upcast.release();
					// ****************************************
					// * after releasing of upcast -> test, if target file(s) exists
					// ****************************************
				}			
			}
			
			// First conversion is finished start part 2: 
			// tasks are ???
			else{
				// xml
//				mFi.setArticleConvertedStatus(mParam.getArticlePID(), false);
				// copy xml file to target dir
				mOutputFile = new File ( mParam.getFileUtil().getTargetDir() + "/" + mParam.getFileUtil().getNativeFile().getName().substring(
						0, mParam.getFileUtil().getNativeFile().getName().lastIndexOf(".")) + ".xml" );
				copyFile(fu.getNativeFile(), mOutputFile);			
			}

			
			QualifiedDublinCore qdcJournal = Metadata.getQualifiedDublinCoreMetadata(mFi.getPidOfJournal(articleObj.getLabel()));
			String journalTitle = "";
			String journalISSN  = qdcJournal.getIdentifierISSN();
			if (qdcJournal.getTitle().length > 0 ){
				Element elemTitle = qdcJournal.getTitle()[0];
				journalTitle = elemTitle.getValue();				
			}
			postProcessXML(qdc, journalTitle, journalISSN);

			ExtendedMetadata mExtMeta = mParam.getExtMetadata();
			if (mParam.isDoNew()){
				addDatastream(mExtMeta.getObjectXML(), mOutputFile.getParent(), mOutputFile.getName(), "text/xml"	, mOutputFile.getName() );
			}else{
				Hashtable dsTable = mFi.getDatastreamTable(mExtMeta.getObjectXML());
				System.out.println("XML: getDatastreamList for " + mExtMeta.getObjectXML());
				if (dsTable.containsKey("DS1")){
					modifyDatastream(mExtMeta.getObjectXML(), "DS1",  mOutputFile.getParent(), mOutputFile.getName(), mOutputFile.getName(), "text/xml" );
				}else{
					addDatastream(mExtMeta.getObjectXML(), mOutputFile.getParent(), mOutputFile.getName(), "text/xml", mOutputFile.getName());						
				}
			}
			
			log.info("Image Resize: " + Constant.cConfiguration.isImageResize(mParam.getJournalLabel()));
			if (Constant.cConfiguration.isImageResize(mParam.getJournalLabel())){
				XMLProcessor xmlProc = XMLProcessor.getInstance();
				xmlProc.setFile(mOutputFile);
				List imgList = xmlProc.getImageList();
				log.info("TaskXML: imglistsize: " + imgList.size());
				Iterator it = imgList.iterator();
				while (it.hasNext()){
					ImageProperties imgProps = (ImageProperties)it.next();
					log.info(mPerlscriptsProps.getProperty("img") + " " + mOutputFile.getParent() + " " +  imgProps.getFilename() + " " + imgProps.getWidth() + " " + imgProps.getHeight());
					Process pImageResize = Runtime.getRuntime().exec(mPerlscriptsProps.getProperty("img") + " " + mOutputFile.getParent() + " " +  imgProps.getFilename() + " " + imgProps.getWidth() + " " + imgProps.getHeight());
					if (pImageResize.waitFor() == 0){
						log.info("TaskXML: process image: " + imgProps.getFilename() + " width: " + imgProps.getWidth() + " height: " + imgProps.getHeight());
					}else{
						log.error("Error in TaskXML, imageresize");
					}
				}
				
			}
			/* // BEGIN
			   // resize images
			    

			    
			*/  // END
			// handle media files
			Filter nf = new Filter (FileFilter.getInstance().getFilterList());
			// current directory
			File dir = new File (mOutputFile.getParent());
			String[] strs = dir.list(nf);
			if (mParam.isDoNew()){
				for (int i = 0; i < strs.length; i++) {
					String mimeType = MimeType.getInstance().getMimeType(new File(mOutputFile.getParent() + "/" + strs[i]));
					addDatastream(mExtMeta.getObjectMultimedia()	, mOutputFile.getParent()	, strs[i], mimeType, strs[i] );
				}
			}else{					
				Properties labelProps = mFi.getItemLabelProperties(mExtMeta.getObjectMultimedia());
				for (int i = 0; i < strs.length; i++) {
					String mimeType = MimeType.getInstance().getMimeType(new File(mOutputFile.getParent() + "/" + strs[i]));
					if (labelProps.containsKey(strs[i])){
						modifyDatastream(mExtMeta.getObjectMultimedia()	, labelProps.getProperty(strs[i]),  mOutputFile.getParent(), strs[i], strs[i], mimeType );
					}else{
						addDatastream(mExtMeta.getObjectMultimedia()	, mOutputFile.getParent()	, strs[i], mimeType, strs[i] );
					}
				}
			}

			if ( mOutputFile != null){
				if (mOutputFile.exists()){
					mSucceed = true;
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IOException e ){
			e.printStackTrace();
		}finally{
			
			setChanged();
			notifyObservers(mParam.getArticlePID());
		}
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: </p>
	 * 
	 * @param aFU
	 * @param aUpcast
	 * @param aLabel
	 * @throws ConvertException 
	 */
	private void batch(FileUtil aFU, Upcast aUpcast, String aLabel)throws ConvertException{
		Iterator it = aFU.getBatchProcessList().iterator();
		while (it.hasNext()){
			File processingFile = new File ((String)it.next());
			Properties processingProps = new Properties();
			try{
				processingProps.load(new FileInputStream(processingFile));
			}catch(IOException fExc){
				fExc.printStackTrace();
			}			
			List filterList = new Vector();
			filterList.add(".rtf");
			Filter nf = new Filter (filterList);
			// current directory
			File dir = new File (processingFile.getParent());
			File targetDir = new File(dir.getAbsolutePath() + "/target");
			targetDir.mkdir();
			Iterator itRtfFile = Arrays.asList(dir.list(nf)).iterator();
			while (itRtfFile.hasNext()){
				String srcFile = (String)itRtfFile.next();
				aUpcast.setSourceFile(new File(dir.getAbsolutePath() + "/" + srcFile));
				aUpcast.doConvert(targetDir.getAbsolutePath(), 1, aLabel, processingProps.getProperty("articleType", ""));				
				//
				File targetFile = new File ( targetDir.getAbsolutePath() + "/" + srcFile.substring(
						0, srcFile.lastIndexOf(".")) + ".xml" );

				addDatastream(mParam.getExtMetadata().getObjectSupplementary(), targetFile.getParent()	, targetFile.getName(), "text/xml", targetFile.getName() );
				// clean up
				filterList.clear();
				filterList.add(".xml");
				nf = new Filter(filterList);
				Iterator itXmlFile = Arrays.asList(targetDir.list(nf)).iterator();
				while (itXmlFile.hasNext()){
					String xmlFile = (String)itXmlFile.next();
					if (xmlFile.startsWith(srcFile.substring(0, srcFile.lastIndexOf("."))) && xmlFile.contains("-")){
						System.out.println(xmlFile + " contains " + xmlFile.contains("-"));
						System.out.println("try to delete " + xmlFile + " " + new File(targetDir.getAbsolutePath() + "/" + xmlFile).delete());
					}
				}
			}
		}

	}
	
	/**
	 * <p><em>Title: Processes DocBookXml output from Upcast conversion, adds Metadata from QDC to DocBookXml</em></p>
	 * <p>Description: Calls XMLProcessor Class for parsing and adding informations to DocBookXml</p>
	 * 
	 * @param aQDC
	 * @param aJournalName
	 * @param aISSN
	 * @throws FileNotFoundException
	 * @throws IOException 
	 */
	private synchronized void postProcessXML(QualifiedDublinCore aQDC, String aJournalName, String aISSN) throws FileNotFoundException, IOException{
		XMLProcessor xmlProc = XMLProcessor.getInstance();
		xmlProc.setFile(mOutputFile);
		xmlProc.processAuthor(Arrays.asList(aQDC.getCreatorPerson()), Arrays.asList(aQDC.getCreatorCorporated()));
		xmlProc.processLanguage(aQDC.getLanguage(0));
		if (aQDC.getIdentifierURN() != null){
			xmlProc.processIdentifier(aQDC.getIdentifierURN(), Constant.cDocBookIdentifierClassURN);			
		}
		/*
		if (aQDC.getIdentifierDOI() != null){
			xmlProc.processIdentifier(aQDC.getIdentifierDOI(), Constant.cDocBookIdentifierClassDOI);
		}*/
		xmlProc.processKeywords(Arrays.asList(aQDC.getSubject()));
		xmlProc.processSubjects(Arrays.asList(aQDC.getSubjectClassified()));
		if (aQDC.getRights().length > 0){
			xmlProc.processLegalnotice(aQDC.getLanguage(0), aQDC.getRights(0));			
		}
		
		if (aQDC.getAlternative().length > 0){
			System.out.println("TaskXML: postprocessXML: alternative length: " + aQDC.getAlternative().length);
			for (int i = 0; i < aQDC.getAlternative().length; i++){
				System.out.println("lang: " + aQDC.getAlternative()[i].getLang());
				System.out.println("type: " + aQDC.getAlternative()[i].getType());
				System.out.println("value: " + aQDC.getAlternative()[i].getValue());
				if (aQDC.getAlternative()[i].getType() != null){
					System.out.println("TaskXML: postprocessXML: alternative type: " + aQDC.getAlternative()[i].getType());
					if (aQDC.getAlternative()[i].getType().equals(Constant.cTitleAbbreviation)){
						System.out.println("TaskXML: postprocessXML: process value: " + aQDC.getAlternative()[i].getValue());
						xmlProc.processsTitleAbbreviation(aQDC.getAlternative()[i].getValue());
						continue;						
					}
				}
			}			
		}
		System.out.println("TaskXML: process biblioCit");
		if (aQDC.getBibliographicCitation().length > 0){
			System.out.println("TaskXML: process biblioCit exists");
			Citation bibCitation = aQDC.getBibliographicCitation()[0];
			xmlProc.processVolumenum(bibCitation.getJournalVolume());
			System.out.println("TaskXML: process biblioCit vol: " + bibCitation.getJournalVolume());
			xmlProc.processIssuenum(bibCitation.getJournalIssueNumber());
			System.out.println("TaskXML: process biblioCit issue: " + bibCitation.getJournalIssueNumber());
			xmlProc.processBiblioset(aJournalName, aISSN);
		}
		xmlProc.writeToOutputStream(new FileOutputStream(mOutputFile));		
	}
/*	
	private void writeNativeFile()throws Exception{
		mExtMeta = mParam.getExtMetadata();
		long timeStamp = Calendar.getInstance().getTimeInMillis();
		File outDir = new File(Constant.getAbsolutPath() + "/WEB-INF/convert/" + mParam.getArticlePID().replace(':', '_') + "_" + timeStamp);
		outDir.mkdir();
		mOutputDir = new File(outDir.getAbsolutePath() + "/target");
		mOutputDir.mkdir();
		String filename = mParam.getFilename().substring(mParam.getFilename().lastIndexOf("/") + 1);
		String outDirName = null;

		// wegen UpCast-Plisten und Target-Verzeichnis
		if (!MimeType.getInstance().getMimeType(filename).equals("application/rtf")){
			outDirName = mOutputDir.getAbsolutePath();
		}else{
			outDirName = outDir.getAbsolutePath();
		}
		
		mSrcFile = new File(outDirName + "/" + filename);
		mSrcFileWithoutExt = mSrcFile.getName().substring(0, mSrcFile.getName().lastIndexOf("."));
		
		byte[] buffer = new byte[8092];
		FileOutputStream out = new FileOutputStream(mSrcFile);
		
		InputStream inStream = mFi.getDatastream(mParam.getContentObjectPID(), mParam.getContentObjectDS());
		int len = -1;
		while ( (len = inStream.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		inStream.close();
		out.flush();
		out.close();
	}
*/	
	
	protected synchronized void addDatastream(String aPID, String aAbsolutPath, String aRelativeFilename, String aMimeType, String aLabel){
		log.info("AbsPath: " + aAbsolutPath);
		log.info("RelFile: " + aRelativeFilename);
		
		// Jochens Version
		//mFi.addDataStream(aPID, "", aAbsolutPath + "/" + aRelativeFilename, aMimeType, aLabel, Constant.cFedoraDatastreamControlGroupManaged);
		
		//Qa: my first Version with using an Byte Array
		/*try{
			FileInputStream fStream = new FileInputStream(aAbsolutPath + "/" + aRelativeFilename);
			BufferedInputStream bStream = new BufferedInputStream(fStream);
			
			DataInputStream dStream = new DataInputStream(fStream);
			String strStream = DataInputStream.readUTF(dStream);
			byte[] stream = strStream.getBytes("UTF-8");
			
			log.info("Stream is: " + new String(stream, "UTF-8") + " is Stream");
			//domg.addDatastream(aPID, null, aAbsolutPath + "/" + aRelativeFilename, aMimeType, aLabel, Constant.cFedoraDatastreamControlGroupManaged);
			//domg.addDatastream(aPID, null, aLabel, aMimeType, new FileInputStream(aAbsolutPath + "/" + aRelativeFilename).toString().getBytes(), "", Constant.cFedoraDatastreamControlGroupManaged);
			domg.addDatastream(aPID, null, aLabel, aMimeType, stream, "", Constant.cFedoraDatastreamControlGroupManaged);
		}catch(Exception Exc){
			log.error(Exc);
		}*/
		
		// Third version: Using new addDatastream method from DOManagement:
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
	
	protected synchronized void copyFile(File aInFile, File aOutFile)throws IOException{
		FileChannel sourceChannel = new FileInputStream(aInFile).getChannel();
		FileChannel destinationChannel = new FileOutputStream(aOutFile).getChannel();
		sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
		// or
		//  destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		sourceChannel.close();
		destinationChannel.close();
   }


}
