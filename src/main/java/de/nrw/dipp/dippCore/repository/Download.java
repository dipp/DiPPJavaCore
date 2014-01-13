/*
 * Created on 08.09.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import de.nrw.dipp.dippCore.util.Constant;

import org.apache.log4j.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Download {

	private MultiThreadedHttpConnectionManager 	mThreadedConnMng 	= new MultiThreadedHttpConnectionManager();
	// get host from a static locator
	private String                              mDownloadURL 		= "";
	private UsernamePasswordCredentials			mCred				= null;
	private HttpConnectionManagerParams 		conParams 			= new HttpConnectionManagerParams();
	private HttpClientParams 					cliParams 			= new HttpClientParams();
	
	
	// Get Logger for this Class
	private Logger log = Logger.getLogger(Download.class);

	
	 
	/**
	 * Constructor that takes authentification data. 
	 * Probably only for testing purposes 
	 * @param aHost
	 * @param aPort
	 * @param aUser
	 * @param aPassword
	 */
	public Download(String aHost, int aPort, String aUser, String aPassword){
		mCred = new UsernamePasswordCredentials(aUser, aPassword);
		mDownloadURL = "http://" + aHost + ":" + aPort + "/fedora/get/";
	}
	
	/**
	 * Default Constructor
	 * Requires the Download URL as String
	 * @param aURL
	 */
	public Download(String aURL){
		mDownloadURL = aURL;
		mCred = new UsernamePasswordCredentials(Constant.cFedoraUser, Constant.cFedoraPassword);
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
	
	// Qa: Method in use? no Call Hierarchy!
	public InputStream getDatastreamContent(String aPID, String aDsID, boolean aWithAuth)throws IOException{
		StringBuffer urlBuf = new StringBuffer();
		
		urlBuf.append(mDownloadURL);
		urlBuf.append(aPID);
		urlBuf.append('/');
		urlBuf.append(aDsID);
		return download(urlBuf.toString());
	}
	
	public byte[] getDatastreamContentAsBytes(String aPID, String aDsID, boolean aWithAuth)throws IOException{
		StringBuffer urlBuf = new StringBuffer();
		
		urlBuf.append(mDownloadURL);
		urlBuf.append(aPID);
		urlBuf.append('/');
		urlBuf.append(aDsID);
		return getBytes(urlBuf.toString(), aWithAuth);
	}
	
	// Qa: Method in use? no Call Hierarchy!
	public InputStream getDissemination(String aPID, String aDsID, Date aTimeStamp)throws IOException{
		StringBuffer urlBuf = new StringBuffer();
		
		urlBuf.append(mDownloadURL);
		urlBuf.append(aPID);
		urlBuf.append('/');
		urlBuf.append("fedora-system:3");
		urlBuf.append('/');
		urlBuf.append("getItem");
		urlBuf.append("?itemID=");
		urlBuf.append(aDsID);
		return download(urlBuf.toString());
	}
	
	// Qa: Method in use? no Call Hierarchy!
	public InputStream downloadFromURL(boolean withAuthentication)throws IOException{
		GetMethod getMethod = null;
		int statusCode = -1;
		try{
			HttpClient client = createClient(withAuthentication);
			getMethod = new GetMethod(mDownloadURL);
			if (withAuthentication){
				getMethod.setDoAuthentication(true);
				getMethod.setFollowRedirects(false);				
			}
			statusCode = client.executeMethod(getMethod);
	        if (statusCode!=200) {
	            throw new IOException(HttpStatus.getStatusText(statusCode)
	                    + ": " 
	                    + getMethod.getResponseBodyAsString());
	        }			
	        return getMethod.getResponseBodyAsStream();
		}finally{
            if (getMethod!=null && statusCode != 200) getMethod.releaseConnection();			
		}
		
	}
	
	public void downloadToFile(String aFile)throws IOException{
		GetMethod getMethod = null;
		int statusCode = -1;
		try{
			HttpClient client = createClient(true);
			getMethod = new GetMethod(mDownloadURL);
			getMethod.setDoAuthentication(true);
			getMethod.setFollowRedirects(false); // do not follow automatically, see below  
			statusCode = client.executeMethod(getMethod);

			// Implementing SSL Support:
			// In case Fedora sends a redirect from http to https connection, 
			// Request should be redirected
			if(statusCode==302){
				Header locationHeader = getMethod.getResponseHeader("location");
				log.info("Fedora Repository is using SSL: Http-Request is redirected to: \n" + locationHeader.getValue());
				
				//change location to https location
				getMethod = new GetMethod(locationHeader.getValue());
				statusCode = client.executeMethod(getMethod);
	        }
	        

			if (statusCode!=200) {
	            throw new IOException(HttpStatus.getStatusText(statusCode)
	                    + ": " 
	                    + getMethod.getResponseBodyAsString());
	        }
	        FileOutputStream fileOutStream = new FileOutputStream(aFile); 
	        InputStream bis = getMethod.getResponseBodyAsStream();
	        ByteArrayOutputStream bot = new ByteArrayOutputStream();
			byte[] buf = new byte[2048];
    		int len;
    		while ( ( len = bis.read( buf ) ) > 0 ) {
    			bot.write( buf, 0, len );
    		}
	        fileOutStream.write(bot.toByteArray());
    		try {
    			bis.close();
    			bot.close();
    		} catch (IOException e) {
    			log.error("WARNING: Could not close stream.");
    		}
		}finally{
            if (getMethod!=null && statusCode != 200) getMethod.releaseConnection();			
		}		
	}
	
	public byte[] getBytes(String aURL, boolean aWithAuth)throws IOException{
		GetMethod getMethod = null;
		int statusCode = -1;
        try{
			HttpClient client = createClient(aWithAuth);
			getMethod = new GetMethod(aURL);
			getMethod.setDoAuthentication(aWithAuth);
			getMethod.setFollowRedirects(false);
			statusCode = client.executeMethod(getMethod);
	        
			// Implementing SSL Support:
			// In case Fedora sends a redirect from http to https connection, 
			// Request should be redirected and credentials will be used
			if(statusCode==302){
				Header locationHeader = getMethod.getResponseHeader("location");
				log.info("Fedora Repository is using SSL: Http-Request is redirected to: \n" + locationHeader.getValue());
				
				//change location to https location
				getMethod = new GetMethod(locationHeader.getValue());
				statusCode = client.executeMethod(getMethod);
	        }
	        
			if (statusCode!=200) {
		        	log.info("Fedora Repository Request failed: \n" + getMethod.getResponseBodyAsString());
	            throw new IOException(HttpStatus.getStatusText(statusCode)
	                    + ": " 
	                    + getMethod.getResponseBodyAsString());
	        }
	        //log.debug(getMethod.getResponseBodyAsString());

	        InputStream bis = getMethod.getResponseBodyAsStream();
	        ByteArrayOutputStream bot = new ByteArrayOutputStream();
	        
			byte[] buf = new byte[2048];
    		int len;
    		while ( ( len = bis.read( buf ) ) > 0 ) {
    			bot.write( buf, 0, len );
    		}
   	        byte[] byStr = bot.toByteArray();
    		try {
    			bis.close();
    			bot.close();
    		} catch (IOException e) {
    			log.error("WARNING: Could not close stream.");
    		}
	        return byStr; 
	        //return getMethod.getResponseBody();
		}finally{
			if (getMethod!=null && statusCode != 200) getMethod.releaseConnection();			
		}
		
	}
	
	public InputStream download(String aURL)throws IOException{
		GetMethod getMethod = null;
		int statusCode = -1;
		try{
			HttpClient client = createClient(true);
			getMethod = new GetMethod(aURL);
			getMethod.setDoAuthentication(true);
			getMethod.setFollowRedirects(false);
			statusCode = client.executeMethod(getMethod);
	        if (statusCode!=200) {
	            throw new IOException(HttpStatus.getStatusText(statusCode)
	                    + ": " 
	                    + getMethod.getResponseBodyAsString());
	        }			
	        return getMethod.getResponseBodyAsStream();
		}finally{
            if (getMethod!=null && statusCode != 200) getMethod.releaseConnection();			
		}
		
	}
	
	
	public static void main(String[] args){
		File directory = new File("C:\\temp\\oai\\test");
		directory.mkdir();

		Download dw = new Download("http://www.gapdev.de/upload/jschirrwagen_rev_200408271054.doc");
		try{
			dw.downloadToFile("C:\\temp\\oai\\test\\jschirrwagen_rev_200408271054.doc");
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}

}
