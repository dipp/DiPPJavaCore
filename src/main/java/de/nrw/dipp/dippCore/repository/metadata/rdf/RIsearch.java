package de.nrw.dipp.dippCore.repository.metadata.rdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Property;

import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.OAI;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: RIsearch.java</p>
 * <p>Description: A class for querying the Fedora Ressource index.</p>
 * <p>new since DiPP2</p>
 * <p>this class and its methods are in early alpha status
 *  <ul>
 *  	<li/>
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
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: RIsearch.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class RIsearch {

	public static final String cType_Triples		= "triples";
	public static final String cType_Tuples			= "tuples";
	public static final String cLang_Tuples_itql 	= "itql";
	public static final String cLang_Tuples_rdql 	= "rdql";
	public static final String cLang_Triples_spo 	= "spo";
	public static final String cFormat_rdf_xml		= "RDF/XML";
	public static final String cFormat_N_Triples	= "N-Triples";
	public static final String cFormat_Simple		= "Simple";
	public static final String cFormat_CSV			= "CSV";
	
	private static final String cURI	= "http://" + Constant.cFedoraHost + ":" + Constant.cFedoraPort + "/fedora/risearch";
	private MultiThreadedHttpConnectionManager connMng	= new MultiThreadedHttpConnectionManager();
	private HttpClient httpClient = new HttpClient(connMng);
	private PostMethod postMethod = new PostMethod(cURI);
	private RDF mRDF	= null;
	private StringBuffer mResultBuffer = null;

	// Get Logger for this Class
	private static Logger log = Logger.getLogger(RIsearch.class);

	
	/**
	 * @param aType
	 * @param aFormat
	 * @param aLang
	 * @param aQuery
	 */
	public RIsearch(String aType, String aFormat, String aLang, String aQuery){
		createQuery("type", aType);
		createQuery("lang", aLang);
		createQuery("format", aFormat);
//		createQuery("query", "<info:fedora/test:1> * *");
//		createQuery("query", "<info:fedora/oai:article> * *");
//		createQuery("query", "* <http://www.openarchives.org/OAI/2.0/setSpec> *");
		createQuery("query", aQuery);
		try{
			if (!aFormat.equals(RIsearch.cFormat_rdf_xml)){
				convertToStringResult(getResult());
			}else{
				mRDF = new RDF(getResult());
			}
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
//			getSubject(OAI.setSpec, "ddc:370");
	}
	
	private void createQuery(String aName, String aValue){
		NameValuePair nv = new NameValuePair(aName, aValue);
		postMethod.addParameter(nv);
	}
	
	private InputStream getResult()throws IOException{
		InputStream response = null;

		UsernamePasswordCredentials mCred = new UsernamePasswordCredentials(Constant.cFedoraUser, Constant.cFedoraPassword);
		httpClient.getState().setCredentials(AuthScope.ANY , mCred);
		postMethod.setDoAuthentication(true); //fedora acces via https requires authorization
				
		int statusCode = httpClient.executeMethod(postMethod);
		
		// Implementing SSL Support:
		// In case Fedora sends a redirect from http to https connection, 
		// Request should be redirected and credentials will be used
		if(statusCode==302){
			Header locationHeader = postMethod.getResponseHeader("location");
			log.info("Fedora Repository is using SSL: \nHttp-Request is redirected to: \n" + locationHeader.getValue());

			//UsernamePasswordCredentials mCred = new UsernamePasswordCredentials(Constant.cFedoraUser, Constant.cFedoraPassword);
			httpClient.getState().setCredentials(AuthScope.ANY , mCred);
			postMethod.setDoAuthentication(true); //fedora acces via https requires authorization
			URI uri = null;
			//change location to https location
			try{
				uri = new URI(locationHeader.getValue(), true);
			}catch(URIException URIExc){
				System.out.println(URIExc);
			}
			postMethod.setURI(uri); //= new PostMethod(locationHeader.getValue());
			statusCode = httpClient.executeMethod(postMethod);
        }
        
		if (statusCode != 200){
			throw new IOException(HttpStatus.getStatusText(statusCode)
					+ ": " 
					+ postMethod.getResponseBodyAsString());
		}else{
			log.debug(postMethod.getResponseBodyAsString());
			log.debug(postMethod.getURI());
			response = postMethod.getResponseBodyAsStream();
			
		}
		return response;
	}
	
	/**
	 * <p>Qa: Method queries the Fedora Resource Index and returns 
	 * a "subject ID" for the given object</p>  
	 * @param aPredicate
	 * @param aObject
	 * @return the first found subjectID or an empty string
	 */
	public String getSubjectID(Property aPredicate, String aObject){
		String subjectID = "";
		List subjectList = mRDF.getSubjectURIs(aPredicate, aObject);
		if (!subjectList.isEmpty()){
			subjectID = ((String)subjectList.get(0)).substring(RDF.cResourceNS.length());
			// creating subjectID as substring from a String that starts with cResourceNS and ends with substring
			// cResourceNS actually looks like info:fedora/ maybe has to be changed to fedora 3? 
			log.info(subjectID);
		}
		return subjectID;
	}
	
	public String getRDF(){
		return mRDF.toString();
	}
	
	public String getStringResult(){
		return mResultBuffer.toString();
	}
	
	private void convertToStringResult(InputStream inStream)throws IOException{
		mResultBuffer = new StringBuffer();
		BufferedReader in
		   = new BufferedReader(new InputStreamReader(inStream));
		String str;
		while ((str = in.readLine()) != null){
			mResultBuffer.append(str + "\r\n");
		}
	}
	
	public static void main(String[] args){
		String query = "* <http://www.openarchives.org/OAI/2.0/setSpec> *";
		RIsearch riSearch = new RIsearch(RIsearch.cType_Triples, RIsearch.cFormat_rdf_xml, RIsearch.cLang_Triples_spo, query);
		log.info("object id for oai-setSpec ddc:370 is: " + riSearch.getSubjectID(OAI.setSpec, "ddc:370"));
	}

}
