/**
 * 
 */
package de.nrw.dipp.dippCore.task;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * @author aquast
 *
 */
public class TaskGetMd extends DecoratorTask {
	
	// Initiate Logger for Class
	private static Logger log = Logger.getLogger(TaskGetMd.class);
	
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

		baseTask.convert();
		tParam = baseTask.getTaskParam();

		log.info("baseTask has finished, starting decoratorTask\n\n");

		String articlePid = tParam.getProperties().getProperty("articlePid");

		DigitalObject articleObj = null;;
		try {
			articleObj = DOManagement.retrieveDigitalObject(articlePid);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServiceManagement mFi = new ServiceManagement();
		QualifiedDublinCore qdc = Metadata.getQualifiedDublinCoreMetadata(articlePid);
		QualifiedDublinCore qdcJournal = Metadata.getQualifiedDublinCoreMetadata(mFi.getPidOfJournal(articleObj.getLabel()));
		String journalTitle = "";
		String journalISSN  = qdcJournal.getIdentifierISSN();
		if (qdcJournal.getTitle().length > 0 ){
			Element elemTitle = qdcJournal.getTitle()[0];
			journalTitle = elemTitle.getValue();				
		}
		postProcessXML(qdc, journalTitle, journalISSN);

		tParam.getProperties().setProperty("label", articleObj.getLabel());
		
		
	}
	

	/**
	 * <p><em>Title: Processes DocBookXml output from Upcast conversion, adds Metadata from QDC to DocBookXml</em></p>
	 * <p>Description: Calls XMLProcessor Class for parsing and adding informations to DocBookXml</p>
	 * 
	 * @param aQDC
	 * @param aJournalName
	 * @param aISSN
	 * @throws XMLException 
	 * @throws FileNotFoundException
	 * @throws IOException 
	 */
	private synchronized void postProcessXML(QualifiedDublinCore aQDC, String aJournalName, String aISSN){
		XMLProcessor xmlProc = XMLProcessor.getInstance();
		File outputFile = new File(tParam.getProperties().getProperty("outputFile"));
		try {
			xmlProc.setFile(outputFile);
		} catch (XMLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			log.info("postprocessXML: alternative length: " + aQDC.getAlternative().length);
			for (int i = 0; i < aQDC.getAlternative().length; i++){
				if (aQDC.getAlternative()[i].getType() != null){
					log.info("postprocessXML: alternative type: " + aQDC.getAlternative()[i].getType());
					if (aQDC.getAlternative()[i].getType().equals(Constant.cTitleAbbreviation)){
						log.info("postprocessXML: process value: " + aQDC.getAlternative()[i].getValue());
						xmlProc.processsTitleAbbreviation(aQDC.getAlternative()[i].getValue());
						continue;						
					}
				}
			}			
		}
		log.info("generate biblioCit in DocBookXML");
		if (aQDC.getBibliographicCitation().length > 0){
			log.info("process biblioCit exists");
			Citation bibCitation = aQDC.getBibliographicCitation()[0];
			xmlProc.processVolumenum(bibCitation.getJournalVolume());
			log.info("process biblioCit vol: " + bibCitation.getJournalVolume());
			xmlProc.processIssuenum(bibCitation.getJournalIssueNumber());
			log.info("process biblioCit issue: " + bibCitation.getJournalIssueNumber());
			xmlProc.processBiblioset(aJournalName, aISSN);
		}
		try {
			xmlProc.writeToOutputStream(new FileOutputStream(outputFile));
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}		
	}


}
