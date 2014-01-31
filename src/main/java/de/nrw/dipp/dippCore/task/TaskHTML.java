package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.util.request.MimeType;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * <p>Title: TaskHTML.java</p>
 * <p>Description: A thread class for handling html-transformation.</p>
 *
 * -----------------------------------------------------------------------------
 *
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: TaskHTML.java,v 1.2 2006/11/06 11:31:44 dippadm Exp $
 */
public class TaskHTML extends TaskXML {

	private Param	mParam	= null;
	
	private ServiceManagement	mFi					= new ServiceManagement();	
	private File				mSrcFile			= null;
	private String 				mSrcFileWithoutExt;
	private boolean 			mSucceed			= false;
	private Properties			mPerlscriptsProps	= new Properties();
	
	public TaskHTML( Param aParam ){
		super(aParam);
		mParam = aParam;
		try{
			mPerlscriptsProps.load(getClass().getResourceAsStream("perlscripts.properties")); // "perlscripts.properties"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getObjectID()
	 */
	public synchronized  String getObjectID() {
		return mParam.getArticlePID();
	}
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#getParam()
	 */
	public synchronized  Param getParam() {
		return mParam;
	}
	/* (non-Javadoc)
	 * @see de.nrw.dipp.task.Task#setParam(de.nrw.dipp.task.Param)
	 */
	public synchronized  void setParam(Param aParam) {
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
	
	public synchronized void setInputFile(String aFilename){
		mSrcFile = new File(aFilename);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		Upcast upcast = null;
		try{
			System.out.println("TaskHTML for id " + getObjectID() );
			// ****************************************
			// * before aquire of upcast -> test, whether sourcefile exists
			// ****************************************
			while( (upcast = Upcast.getInstance()) == null ){
				System.out.println("TaskHTML for id " + getObjectID() + " wait");
				wait(2500);				
			}
			System.out.println("TaskHTML for id " + getObjectID() + " got upcast");
//			srcFile = new File(mOutputDir + "/" + mSrcFileWithoutExt + ".xml");
			QualifiedDublinCore qdc = Metadata.getQualifiedDublinCoreMetadata(mParam.getArticlePID());
			DigitalObject articleObj = DOManagement.retrieveDigitalObject(mParam.getArticlePID());
			String articleType = "";
			if (qdc.getArticleType().length > 0){
				articleType = qdc.getArticleType(0);
			}
			System.out.println("TaskHTML: " + "a: " + mSrcFile.getAbsolutePath());
			System.out.println("TaskHTML: " + MimeType.getInstance().getMimeType(mSrcFile));
//			if (MimeType.getInstance().getMimeType(mSrcFile).equals("text/xml")){
			if (true){
			
				upcast.setSourceFile(mSrcFile);
				upcast.doConvert(mSrcFile.getParent(), 2, articleObj.getLabel(), articleType);
				System.out.println("TaskHTML: " + "d");

				System.out.println("UopcastThread: 2.Teil OK fuer: " + mSrcFile.getAbsolutePath());
				mSrcFileWithoutExt = mSrcFile.getName().substring(0, mSrcFile.getName().lastIndexOf("."));
				System.out.println("TaskHTML: " + "e");
				
				if (mParam.getFileUtil().isCompressedZip() && mParam.getFileUtil().isBatchProcess()){
					// batch process
					System.out.println("TaskXML: yes do batchprocess" );
					batch(mParam.getFileUtil(), upcast, articleObj.getLabel());
				}
				

				Process pToc = Runtime.getRuntime().exec(mPerlscriptsProps.getProperty("toc") + " " + mSrcFile.getParent() + "/" + mSrcFileWithoutExt + "-toc.html " + mSrcFile.getParent() + "/ " + mSrcFileWithoutExt + ".toc_html");
				Process pIndex = Runtime.getRuntime().exec(mPerlscriptsProps.getProperty("html") + " " + mSrcFile.getParent() + "/" +  mSrcFileWithoutExt + "-index.html " + mSrcFile.getParent() + "/ " + mSrcFileWithoutExt + ".index_html");
				Process pFast = Runtime.getRuntime().exec(mPerlscriptsProps.getProperty("fast") + " " + mSrcFile.getParent() + "/" +  mSrcFileWithoutExt + "-fast-raw.xml " + mSrcFile.getParent() + "/ " + mSrcFileWithoutExt + "-fast-clean.xml");

			
				ExtendedMetadata mExtMeta = mParam.getExtMetadata();
				Hashtable dsTableHTML = new Hashtable();
				Hashtable dsTableXML = new Hashtable();
			
				if (!mParam.isDoNew()){
					dsTableHTML = mFi.getDatastreamTable(mExtMeta.getObjectHTML());
					dsTableXML = mFi.getDatastreamTable(mExtMeta.getObjectXML());
				}

				if (pIndex.waitFor() == 0){
					if ( dsTableHTML.containsKey("DS1") ){
						modifyDatastream(mExtMeta.getObjectHTML()	, "DS1",  mSrcFile.getParent()	, mSrcFileWithoutExt + ".index_html", "index_html", "text/html" );						
					}else{
						addDatastream(mExtMeta.getObjectHTML()	, mSrcFile.getParent()	, mSrcFileWithoutExt + ".index_html", "text/html"	, "index_html" );
					}
				}else{
					// throw LogMessage				
				}
				if (pToc.waitFor() == 0){
					if (dsTableHTML.containsKey("DS2")){
						modifyDatastream(mExtMeta.getObjectHTML()	, "DS2",  mSrcFile.getParent()	, mSrcFileWithoutExt + ".toc_html"		, "toc_html", "text/html" );
					}else{
						addDatastream(mExtMeta.getObjectHTML()	, mSrcFile.getParent()	, mSrcFileWithoutExt + ".toc_html", "text/html"		, "toc_html" );
					}
				}else{
					// throw LogMessage
				}
				if (pFast.waitFor() == 0){
					File fastFile = new File(mSrcFile.getParent() + "/" + mSrcFileWithoutExt + "-fast-clean.xml");
					if (dsTableXML.containsKey("DS2")){
						modifyDatastream(mExtMeta.getObjectXML()	, "DS2",  mSrcFile.getParent()	, mSrcFileWithoutExt + "-fast-clean.xml", mSrcFileWithoutExt + "-fast.xml", "text/xml" );						
					}else{
						addDatastream(mExtMeta.getObjectXML()	, mSrcFile.getParent()	, mSrcFileWithoutExt + "-fast-clean.xml", "text/xml", mSrcFileWithoutExt + "-fast.xml" );					
					}
				}else{
					// throw LogMessage
				}
				
				
			}else{
				System.out.println("invalid mimeType");
			}
		
			System.out.println("UpcastThread: finished");
			mSucceed = true;
		}catch(InterruptedException e){
			System.out.println("TaskHTML: " + "error interrupt");
			e.printStackTrace();
		}catch(IOException e ){
			System.out.println("TaskHTML: " + "error io");
			e.printStackTrace();
		}catch(ConvertException e){
			System.out.println("TaskHTML: " + "error convert");
			e.printStackTrace();
		}finally{
			upcast.release();
			setChanged();
			notifyObservers(mParam.getArticlePID());
		}
	}
	
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
			filterList.add(".xml");
			Filter nf = new Filter (filterList);
			// current directory
			File targetDir = new File(processingFile.getParent() + "/target");
			Iterator itXmlFile = Arrays.asList(targetDir.list(nf)).iterator();
			while (itXmlFile.hasNext()){
				String srcFile = (String)itXmlFile.next();
				aUpcast.setSourceFile(new File(targetDir.getAbsolutePath() + "/" + srcFile));
				aUpcast.doConvert(targetDir.getAbsolutePath(), 2, aLabel, processingProps.getProperty("articleType", ""));
				//
				File postProcessTargetFile = new File ( targetDir.getAbsolutePath() + "/" + srcFile.substring(
						0, srcFile.lastIndexOf(".")) + "-index.html" );
				if (!postProcessTargetFile.exists()){
					System.out.println("!!! " + postProcessTargetFile.getAbsolutePath() + " does not exist!!!");
				}
				String targetHTML = postProcessTargetFile.getParent() + "/ " + srcFile.substring(0, srcFile.lastIndexOf(".")) + ".html";
				try{
					Process pIndex = Runtime.getRuntime().exec(mPerlscriptsProps.getProperty("html") + " " + postProcessTargetFile.getAbsolutePath() + " " + targetHTML);
					if (pIndex.waitFor() == 0){
						System.out.println("TaskHTML: add to supplementary: " + postProcessTargetFile.getParent() + "/" + srcFile.substring(0, srcFile.lastIndexOf(".")) + ".html");
						addDatastream(mParam.getExtMetadata().getObjectSupplementary(), postProcessTargetFile.getParent()	, srcFile.substring(0, srcFile.lastIndexOf(".")) + ".html", "text/html", srcFile.substring(0, srcFile.lastIndexOf(".")) + ".html" );
					}
				}catch(IOException ioExc){
					ioExc.printStackTrace();
				}catch(InterruptedException intExc){
					intExc.printStackTrace();
				}
				
			}
		}

	}

}
