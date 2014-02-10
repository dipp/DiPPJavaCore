/**
 * 
 */
package de.nrw.dipp.dippCore.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * @author aquast
 *
 */
public class TaskPloneReg extends DecoratorTask {

	private static Logger log = Logger.getLogger(TaskPloneReg.class);
	
	private ServiceManagement mFi = null;
	private boolean succeed = false;
	private StringBuffer mDummyPageBuffer = new StringBuffer();

	
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
		return succeed;
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

		String label = getJournalLabel();

		log.info("label: " + label);

		ExtendedMetadata mExtMeta = tParam.getExtMetadata();
		QualifiedDublinCore qdc = Metadata
				.getQualifiedDublinCoreMetadata(articlePid);
		
		log.info("try to get Journal Qdc");

		mFi = new ServiceManagement();
		String journalPid = mFi.getPidOfJournal(label);
		log.info(journalPid);
		QualifiedDublinCore qdcJournal = Metadata.getQualifiedDublinCoreMetadata(journalPid);
		
		XmlRpcClient client = null;

		try {
			log.info("Client Url: " + Constant.cConfiguration.getIdentifier(label));
			client = getClient(Constant.cConfiguration.getIdentifier(label));
		} catch (MalformedURLException urlExc) {
			log.error(urlExc);
		}
		log.info("ClientObject: " + client.getClientConfig().toString());

		// read metadata informations required for RPC-Client:
		String journalPID = mFi.getPidOfJournal(label);
		
		String qdcTitle = "";
		if (qdc.getTitle().length > 0) {
			qdcTitle = qdc.getTitle(0).getValue();
		}
		String qdcCreator = "";
		if (qdc.getCreatorPerson().length > 0) {
			qdcCreator = qdc.getCreatorPerson(0).getLastName();
		}
		String qdcSubject = "";
		if (qdc.getSubject().length > 0) {
			String[] subjects = qdc.getSubject();
			for (int i = 0; i < subjects.length; i++) {
				qdcSubject = qdcSubject + ";" + subjects[i];
			}
		}
		String qdcRights = "";
		if (qdc.getRights().length > 0) {
			qdcRights = qdc.getRights(0);
		}

		// Read the extracted Metadata into an Array of String:
		String[] request_workflow_args = { articlePid, journalPID,
				" ", "dipp:article", qdcCreator, qdcTitle, " ", " " };

		// Execute RPC (remote procedure call to Zope)
		String resultWorkflowId = null;
		try {
			resultWorkflowId = (String) client.execute(
					"createWorkflowInstance", request_workflow_args);
		} catch (XmlRpcException exc) {
			log.error(exc);
		}
		log.info("registered workflow-instance: " + resultWorkflowId);
		log.info("getObjectHTML is: " + mExtMeta.getObjectHTML());

		try {
			List<String[]> argsHTML = getRequestArgs(mExtMeta.getObjectHTML(), "FedoraDocument", resultWorkflowId);
			List<String[]> argsMulti = getRequestArgs(mExtMeta.getObjectMultimedia(), "FedoraMultimedia", resultWorkflowId);
			List<String[]> argsNative = getRequestArgs(mExtMeta.getObjectNative(), "FedoraMultimedia", resultWorkflowId);
			List<String[]> argsPDF = getRequestArgs(mExtMeta.getObjectPDF(), "FedoraMultimedia", resultWorkflowId);
			List<String[]> argsXML = getRequestArgs(mExtMeta.getObjectXML(), "FedoraDocument", resultWorkflowId);

			if (argsHTML == null || argsHTML.isEmpty()) {
				log.info("No HTML Object exists, create a dummy index page");
				mDummyPageBuffer.append("<p>This is only a dummy page, because no html content was created</p>\r\n");
				mDummyPageBuffer.append("<p>Feel free to modify this page and look in the folder contents for other content objects</p>\r\n");
				addDatastream(mExtMeta.getObjectHTML(), mDummyPageBuffer.toString().getBytes("utf-8"), "text/html",	"index_html");
				argsHTML = getRequestArgs(mExtMeta.getObjectHTML(),	"FedoraDocument", resultWorkflowId);
			}
			for (int i = 0; i < argsHTML.size(); i++) {
				String[] args = (String[]) argsHTML.get(i);
				client.execute("createContent", args);
			}
			log.info("registered html content");
			for (int i = 0; i < argsMulti.size(); i++) {
				String[] args = (String[]) argsMulti.get(i);
				client.execute("createContent", args);
			}
			log.info("registered multimedia content");
			for (int i = 0; i < argsNative.size(); i++) {
				String[] args = (String[]) argsNative.get(i);
				client.execute("createContent", args);
			}
			log.info("registered native content");
			for (int i = 0; i < argsPDF.size(); i++) {
				String[] args = (String[]) argsPDF.get(i);
				client.execute("createContent", args);
			}
			log.info("registered pdf content");
			for (int i = 0; i < argsXML.size(); i++) {
				String[] args = (String[]) argsXML.get(i);
				client.execute("createContent", args);
			}
			log.info("registered xml content");
			succeed = true;

			// last step: set article status flag in repository to active
			if (succeed) {
				mFi.setPublishingState(articlePid, true, false);
			}
		} catch (Exception Exc) {
			log.error(Exc);
			Exc.printStackTrace();
		} finally {
			setChanged();
			notifyObservers(articlePid);
		}

		log.info("finished RPC calls to Plone");
		// QualifiedDublinCore qdcJournal =
		// Metadata.getQualifiedDublinCoreMetadata();
		// mParam.
	}

		
	public XmlRpcClient getClient(String aServerURL)
			throws MalformedURLException {
		log.info("Starte XmlRPC Client mit: " + aServerURL);
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(aServerURL));
		config.setBasicUserName(Constant.cPloneUser);
		config.setBasicPassword(Constant.cPlonePassword);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		return client;
	}

	/**
	 * <p>
	 * <em>Title: Read the DatastreamTable of an Fedora Object for existing Datastreams</em>
	 * </p>
	 * <p>
	 * Description: method calls the getDatastream Operation via
	 * ServiceManagement for getting a list of existing Datastreams within a
	 * Fedora Object
	 * </p>
	 * 
	 * @param aPID
	 * @param aObjType
	 * @param aRemoteParentId
	 * @return
	 * @throws IOException
	 */
	public List<String[]> getRequestArgs(String aPID, String aObjType,
			String aRemoteParentId) throws IOException {

		List<String[]> argsList = new Vector<String[]>();
		Hashtable<String,DigitalObjectDatastream> dostreamTable = mFi.getDatastreamTable(aPID);
		Enumeration<String> enumKeys = dostreamTable.keys();
		while (enumKeys.hasMoreElements()) {
			String dsId = (String) enumKeys.nextElement();
			if (!(dsId.startsWith("DC") || dsId.startsWith("RELS-EXT"))) {
				DigitalObjectDatastream doStream = (DigitalObjectDatastream) dostreamTable
						.get(dsId);
				log.debug("id: " + doStream.getId());
				log.debug("label: " + doStream.getLabel());
				log.debug("location: " + doStream.getLocation());
				log.debug("mimetype: " + doStream.getMimeType());
				log.debug("size: " + doStream.getSize());

				String[] args = { aObjType, aRemoteParentId, aPID,
						doStream.getId(), doStream.getLabel() };
				argsList.add(args);
			}
		}
		return argsList;
	}

	protected synchronized void addDatastream(String aPID, byte[] dsStream,
			String aMimeType, String aLabel) {

		// Call WSAdaptor directly via DOManagement:
		try {
			DOManagement domMgn = new DOManagement();
			domMgn.addDatastream(aPID, null, aLabel, aMimeType, dsStream, "LogMessage:empty", Constant.cFedoraDatastreamControlGroupManaged);
			log.info("created Datastream for PID " + aPID);
		} catch (Exception e) {
			log.info(e);
		}
	}


}
