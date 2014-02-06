/**
 * 
 */
package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.util.FileUtil;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * @author aquast
 *
 */
public class TaskHtml extends DecoratorTask {

	// Initiate Logger for Class
	private static Logger log = Logger.getLogger(TaskHtml.class);
	
	private File sourceFile = null;
	private String sourceFileBaseName = null;
	private Properties perlscriptsProps	= new Properties();
	private boolean succeed = false;



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
	
	public void convert(){
		
		baseTask.convert();
		tParam = baseTask.getTaskParam();
		
		Upcast upcast = null;
		FileUtil fUtil = tParam.getFileUtil();
		String articlePid = tParam.getProperties().getProperty("articlePid");
		// we need to get the result File from the last Task
		sourceFile = new File(tParam.getProperties().getProperty("outputFile"));
		
		ServiceManagement mFi = new ServiceManagement();
		
		while( (upcast = Upcast.getInstance()) == null ){
		}

		try{
			perlscriptsProps.load(getClass().getResourceAsStream("perlscripts.properties")); // "perlscripts.properties"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}

		try{			
			DigitalObject articleObj = DOManagement.retrieveDigitalObject(articlePid);
			QualifiedDublinCore qdc = Metadata.getQualifiedDublinCoreMetadata(articlePid);

			log.info("starting TaskHtml for id " + articlePid);

			log.info("UpCast is ready to process "  + articlePid);
			
			String articleType = "";
			if (qdc.getArticleType().length > 0){
				articleType = qdc.getArticleType(0);
			}

			upcast.setSourceFile(sourceFile);
			upcast.doConvert(sourceFile.getParent(), 2, articleObj.getLabel(), articleType);

			log.info("UpcastThread: 2.Teil OK fuer: " + sourceFile.getAbsolutePath());
			sourceFileBaseName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
			
			log.info("Verzeichnispfad: "+ perlscriptsProps.getProperty("toc"));
			Process pToc = Runtime.getRuntime().exec(perlscriptsProps.getProperty("toc") 
					+ " " + sourceFile.getParent() + "/" + sourceFileBaseName 
					+ "-toc.html " + sourceFile.getParent() + "/ " 
					+ sourceFileBaseName + ".toc_html");
			Process pIndex = Runtime.getRuntime().exec(perlscriptsProps.getProperty("html") 
					+ " " + sourceFile.getParent() + "/" +  sourceFileBaseName 
					+ "-index.html " + sourceFile.getParent() + "/ " 
					+ sourceFileBaseName + ".index_html");
			Process pFast = Runtime.getRuntime().exec(perlscriptsProps.getProperty("fast") 
					+ " " + sourceFile.getParent() + "/" +  sourceFileBaseName 
					+ "-fast-raw.xml " + sourceFile.getParent() + "/ " 
					+ sourceFileBaseName + "-fast-clean.xml");


			//TODO: skip using ExtendedMetadata
			ExtendedMetadata mExtMeta = tParam.getExtMetadata();
			Hashtable<String, DigitalObjectDatastream> dsTableHTML = new Hashtable<String, DigitalObjectDatastream>();
			Hashtable<String, DigitalObjectDatastream> dsTableXML = new Hashtable<String, DigitalObjectDatastream>();

			// if an html representation already exists for article object 
			if (tParam.getProperties().containsKey("DoNew") && !tParam.getProperties().getProperty("DoNew").equals(true)){
				// get all Datastreams IDs and Stream representations 
				// of the Fedora content-Object including html streams  
				dsTableHTML = mFi.getDatastreamTable(mExtMeta.getObjectHTML());
				dsTableXML = mFi.getDatastreamTable(mExtMeta.getObjectXML());
			}
			
			// we wait for the perlscript process and progress here if its terminate normal
			if (pIndex.waitFor() == 0){
				if ( dsTableHTML.containsKey("DS1") ){
					modifyDatastream(mExtMeta.getObjectHTML(), "DS1", sourceFile.getParent(),
							sourceFileBaseName + ".index_html", "index_html", "text/html" );						
				}else{
					addDatastream(mExtMeta.getObjectHTML(), sourceFile.getParent(),
							sourceFileBaseName + ".index_html", "text/html"	, "index_html" );
				}
			}else{
				//TODO: implement
				// throw LogMessage				
			}
			if (pToc.waitFor() == 0){
				if (dsTableHTML.containsKey("DS2")){
					modifyDatastream(mExtMeta.getObjectHTML(), "DS2", sourceFile.getParent(),
							 sourceFileBaseName + ".toc_html"		, "toc_html", "text/html" );
				}else{
					addDatastream(mExtMeta.getObjectHTML(), sourceFile.getParent(),
							sourceFileBaseName + ".toc_html", "text/html"		, "toc_html" );
				}
			}else{
				// throw LogMessage
			}
			if (pFast.waitFor() == 0){
				File fastFile = new File(sourceFile.getParent() + "/" + sourceFileBaseName
						+ "-fast-clean.xml");
				if (dsTableXML.containsKey("DS2")){
					modifyDatastream(mExtMeta.getObjectXML(), "DS2",  sourceFile.getParent(),
							sourceFileBaseName + "-fast-clean.xml", 
							sourceFileBaseName + "-fast.xml", "text/xml" );						
				}else{
					addDatastream(mExtMeta.getObjectXML(), sourceFile.getParent(), 
							sourceFileBaseName + "-fast-clean.xml", "text/xml",
							sourceFileBaseName + "-fast.xml" );					
				}
			}else{
				// throw LogMessage
			}
		
			System.out.println("UpcastThread: finished");
			succeed = true;
		}catch(InterruptedException e){
			log.error("TaskHTML: " + "error interrupt" + e);
			e.printStackTrace();
		}catch(IOException e ){
			log.error("TaskHTML: " + "error io" + e);
			e.printStackTrace();
		}catch(ConvertException e){
			log.error("TaskHTML: " + "error convert" + e);
			e.printStackTrace();
		}finally{
			upcast.release();
			setChanged();
			notifyObservers(articlePid);
		}

	}
	
	protected synchronized void modifyDatastream(String aPID, String aDsId, String aAbsolutPath, String aRelativeFilename, String aLabel, String aMimeType)throws FileNotFoundException, IOException{
		DOManagement doManage = new DOManagement();
		doManage.modifyDatastreamByReference(aPID, aDsId, aLabel, aMimeType, new FileInputStream(aAbsolutPath + "/" + aRelativeFilename), "update Datastream, new conversion");
	}
	
	protected synchronized void addDatastream(String aPID, String aAbsolutPath, String aRelativeFilename, String aMimeType, String aLabel){
		DOManagement domg = new DOManagement();
		try{
			domg.addDatastream(aPID, null, aAbsolutPath + "/" + aRelativeFilename, aMimeType, aLabel, Constant.cFedoraDatastreamControlGroupManaged);
		}catch(Exception e){
			log.error(e);
		}

	}


}
