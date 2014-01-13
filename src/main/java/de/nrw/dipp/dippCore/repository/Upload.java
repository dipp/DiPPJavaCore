package de.nrw.dipp.dippCore.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.auth.AuthScope;


/**
 * <p>Title: Upload.java</p>
 * <p>Description: A class for managing Fedora-Requests.</p>
 * <p>History: created on 2004/09/06</p>
 * <p>
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
 * @version $Id: Upload.java,v 1.2 2007/01/05 11:33:56 dippadm Exp $
 */public class Upload {

	// Get Logger for this Class
	private Logger log = Logger.getLogger(Upload.class);
		
	
	 
	private MultiThreadedHttpConnectionManager 	mThreadedConnMng 	= new MultiThreadedHttpConnectionManager();
	// get host from a static locator
	private String								mUploadURL			= "";
	private UsernamePasswordCredentials			mCred				= null;
	private HttpConnectionManagerParams 		conParams 			= new HttpConnectionManagerParams();
	private HttpClientParams 					cliParams 			= new HttpClientParams();


	// Constructor needs Connection Params
	public Upload(String aHost, int aPort, String aUser, String aPassword){
		mCred = new UsernamePasswordCredentials(aUser, aPassword);
		mUploadURL = "http://" + aHost + ":" + aPort + "/fedora/management/upload";
		log.info("The UploadUrl: " + mUploadURL);
	}
	
	/**
	 * <p><em>Title: Generates a HttpClient with all needed params</em></p>
	 * <p>Description: Method generates Instances of various classes, all needed to
	 * create a HttpClient with the required properties</p>
	 * 
	 * @param withAuthentication <code>boolean</code> Defines if authentification is required 
	 * @return <code>HTTPClient>/code> a Http Client for connecting a Server
	 */
	private HttpClient createClient(boolean withAuthentication){
		conParams.setConnectionTimeout(10000);
		mThreadedConnMng.setParams(conParams);
		cliParams.setAuthenticationPreemptive(true);

		HttpClient client = new HttpClient(mThreadedConnMng);
		if (withAuthentication){
			client.setParams(cliParams);
			client.getState().setCredentials(AuthScope.ANY, mCred);				
		}
		return client;

	}

	
    /**
     * <p><em>Title: upload</em></p>
     * <p>Description: method uploads a Stream into a temporary directory on the DiPP server. 
     * It is required to provide external datastreams for being added to a fedora object via
     * addDatastream and modifyDatastreamByReference Operations</p>
     * 
     * @param in <code>InputStream</code> that should be uploaded into the temporary directory  
     * @return <code>String</code>
     * @throws IOException 
     */
    public String upload(InputStream in) throws IOException {
        File tempFile=File.createTempFile("fedora-upload-", null);
        FileOutputStream out=new FileOutputStream(tempFile);
        try {
            pipeStream(in, out, 8192);
            return upload(tempFile);
        } finally {
            in.close();
            out.close();
            if (!tempFile.delete()) {
                log.warn("WARNING: Could not remove temporary file: "
                        + tempFile.getName());
                tempFile.deleteOnExit();
            }
        }
    }
	
	public String upload(File aFile)throws FileNotFoundException, IOException{
		String id = "";
		PostMethod postMethod = null;
		try{
			log.debug("upload: Filename: " + aFile.getAbsolutePath());
			log.debug("upload: isFile: " + aFile.isFile());
			log.debug("upload: isAbsolute: " + aFile.isAbsolute());
			log.debug("upload: isDir: " + aFile.isDirectory());
			log.debug("upload: isHidden: " + aFile.isHidden());

			HttpClient client = createClient(true);
			/*postMethod = new MultipartPostMethod(mUploadURL);
			postMethod.setDoAuthentication(true);
			postMethod.addParameter("file", aFile);
			*/
			postMethod = new PostMethod(mUploadURL);
			//postMethod.setDoAuthentication(true);
			//postMethod.("file", aFile);
			
			Part[] parts = {new FilePart("file", aFile)};
			postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));

			int statusCode = client.executeMethod(postMethod);
			
			// Implementing SSL Support:
			// In case Fedora sends a redirect from http to https connection, 
			// Request should be redirected and credentials will be used
			if(statusCode==302){
				Header locationHeader = postMethod.getResponseHeader("location");
				log.info("Fedora Repository is using SSL: Http-Request is redirected to: \n" + locationHeader.getValue());

				//UsernamePasswordCredentials mCred = new UsernamePasswordCredentials(Constant.cFedoraUser, Constant.cFedoraPassword);
				//httpClient.getState().setCredentials(AuthScope.ANY , mCred);
				postMethod.setDoAuthentication(true); //fedora access via https requires authorization
				URI uri = null;
				//change location to https location
				try{
					uri = new URI(locationHeader.getValue(), true);
				}catch(URIException URIExc){
					log.error(URIExc);
				}
				postMethod.setURI(uri); //= new PostMethod(locationHeader.getValue());
				statusCode = client.executeMethod(postMethod);
	        }
			
			if (statusCode!=201) {
	            throw new IOException(HttpStatus.getStatusText(statusCode)
	                    + ": " 
	                    + replaceNewlines(postMethod.getResponseBodyAsString(), " "));
	        }
			id = replaceNewlines(postMethod.getResponseBodyAsString(), "");
		}finally{
			if (postMethod != null){
					postMethod.releaseConnection();
			}
		}
		return id;
	}

	/**
     * Replace newlines with the given string.
     */
    private static String replaceNewlines(String in, String replaceWith) {
        return in.replaceAll("\r", replaceWith).replaceAll("\n", replaceWith);
    }
	
    
    /**
     * <p><em>Title: </em></p>
     * <p>Description: </p>
     * 
     * @param in
     * @param out
     * @param bufSize
     * @throws IOException 
     */
    private void pipeStream(InputStream in, OutputStream out, int bufSize)
    	throws IOException {
    	try {
    		byte[] buf = new byte[bufSize];
    		int len;
    		while ( ( len = in.read( buf ) ) > 0 ) {
    			out.write( buf, 0, len );
    		}
    	} finally {
    		try {
    			in.close();
    			out.close();
    		} catch (IOException e) {
    			log.error("WARNING: Could not close stream.");
    		}
    	}
    }
    
}
