package de.nrw.dipp.dippCore.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObjectDatastream;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.repository.metadata.Metadata;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.ExtendedMetadata;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * <p>
 * Title: TaskPloneRegister.java
 * </p>
 * <p>
 * Description: registers digital objects and datastreams via xml-rpc in plone.
 * </p>
 * 
 * Qa: How does this work exactly?
 * 
 * <p>
 * History:
 * <ul>
 * <li> created on 2006-12-08 </li>
 * </p>
 * -----------------------------------------------------------------------------
 * 
 * <p>
 * <b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at <a
 * href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a>
 * </p>
 * 
 * <p>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * </p>
 * 
 * <p>
 * Portions created for the Fedora Repository System are Copyright &copy;
 * 2002-2004 by The Rector and Visitors of the University of Virginia and
 * Cornell University. All rights reserved."
 * </p>
 * 
 * -----------------------------------------------------------------------------
 * 
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: TaskPloneRegister.java,v 1.1 2007/01/05 11:39:24 dippadm Exp $
 */
public class TaskPloneRegister extends Observable implements Task {

	private Param mParam = null;
	private boolean mSucceed = false;

	private StringBuffer mDummyPageBuffer = new StringBuffer();

	private static Logger log = Logger.getLogger(TaskPloneRegister.class);
	private ServiceManagement mFi = new ServiceManagement();

	private DOManagement domMgn = new DOManagement();

	protected TaskPloneRegister(Param aParam) {
		
		mParam = aParam;
		/*
		 * try{ //TODO Qa: Reimplement this with log4j //FileHandler handler =
		 * new FileHandler(Constant.getAbsolutPath() +
		 * "/WEB-INF/log/ploneregister%u.log", 10000, 8); //log.(handler);
		 * //log.setLevel(Level.FINEST); }catch(IOException exc){
		 * exc.printStackTrace(); }
		 */
	}

	public Param getParam() {
		return mParam;
	}

	public void setParam(Param aParam) {
		mParam = aParam;
	}

	public boolean isFinished() {
		return false;
	}

	public boolean isSucceeded() {
		return mSucceed;
	}

	public void run() {
		ExtendedMetadata mExtMeta = mParam.getExtMetadata();
		QualifiedDublinCore qdc = Metadata
				.getQualifiedDublinCoreMetadata(mParam.getArticlePID());
		log.debug("Journal-Label: " + mParam.getJournalLabel());
		log.debug("Journal-Identifier (Url?): "
				+ Constant.cConfiguration.getIdentifier(mParam
						.getJournalLabel()));

		XmlRpcClient client = null;

		try {
			client = getClient(Constant.cConfiguration.getIdentifier(mParam
					.getJournalLabel()));
		} catch (MalformedURLException urlExc) {
			log.error(urlExc);
		}
		log.debug("ClientObject: " + client.getClientConfig().toString());

		// read metadata informations required for RPC-Client:
		String journalPID = mFi.getPidOfJournal(mParam.getJournalLabel());
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
		String[] request_workflow_args = { mParam.getArticlePID(), journalPID,
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
			mSucceed = true;

			// last step: set article status flag in repository to active
			if (mSucceed) {
				mFi.setPublishingState(mParam.getArticlePID(), true, false);
			}
		} catch (Exception Exc) {
			log.error(Exc);
			Exc.printStackTrace();
		} finally {
			setChanged();
			notifyObservers(mParam.getArticlePID());
		}

		log.info("RPC Client Aufrufe finished");
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
		Enumeration enumKeys = dostreamTable.keys();
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
			domMgn.addDatastream(aPID, null, aLabel, aMimeType, dsStream, "LogMessage:empty", Constant.cFedoraDatastreamControlGroupManaged);
			log.info("created Datastream for PID " + aPID);
		} catch (Exception e) {
			log.info(e);
		}
	}
}
