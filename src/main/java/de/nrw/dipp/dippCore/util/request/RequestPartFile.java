/*
 * Created on 21.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.util.request;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletInputStream;


/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class RequestPartFile implements IfRequestPart {

	private String			_name 			= "";
	private String			_fileName		= "";
	private String			_filePath		= "";
	private String			_contentType	= "";
	private String			_mimeType		= "";
	private PartInputStream _partInStream	= null;
	
	public RequestPartFile(	String name, 
							ServletInputStream in, 
							String boundary,
							String contentType, 
							String fileName, 
							String filePath)
    	throws IOException {
	    _fileName = fileName;
	    _filePath = filePath;
	    _contentType = contentType;
	    _partInStream = new PartInputStream(in, boundary);
	    _name = name;
		
	}
	
	  public String getFileName() {
	    return _fileName;
	  }
	  public String getFilePath() {
	    return _filePath;
	  }
	  public String getContentType() {
	    return _contentType;
	  }

	  /*
	   * determines the MIME-Type by File-Extension
	   * needs improvement by a framework like JHOVE
	   * return MIME-Type
	   */
	  public String getMimeType(){
//	  	System.out.println(_fileName.substring(_fileName.indexOf(".") + 1));
//	  	return MimeType.getInstance().getMimeTypeProperties().getProperty(_fileName.substring(_fileName.indexOf(".") + 1), "unknown");
		  return null;
	  }
	  
	  public InputStream getInputStream() {
	    return _partInStream;
	  }
	  public long writeTo(File fileOrDirectory) throws IOException {
	    long written = 0;
	    
	    OutputStream fileOut = null;
	    try {
	      // Only do something if this part contains a file
	      if (_fileName != null) {
	        // Check if user supplied directory
	        File file;
	        if (fileOrDirectory.isDirectory()) {
	          // Write it to that dir the user supplied, 
	          // with the filename it arrived with
	          file = new File(fileOrDirectory, _fileName);
	        }
	        else {
	          // Write it to the file the user supplied,
	          // ignoring the filename it arrived with
	          file = fileOrDirectory;
	        }
	        fileOut = new BufferedOutputStream(new FileOutputStream(file));
	        written = write(fileOut);
	      }
	    }
	    finally {
	      if (fileOut != null) fileOut.close();
	    }
	    return written;
	  }
	  public long writeTo(OutputStream out) throws IOException {
	    long size=0;
	    // Only do something if this part contains a file
	    if (_fileName != null) {
	      // Write it out
	      size = write( out );
	    }
	    return size;
	  }
	  private long write(OutputStream out) throws IOException {
	    // decode macbinary if this was sent
	    if (_contentType.equals("application/x-macbinary")) {
	    	throw new IOException("Content-Type application/x-macbinary is not supported");
	    }
	    long size=0;
	    int read;
	    byte[] buf = new byte[8 * 1024];
	    while((read = _partInStream.read(buf)) != -1) {
	      out.write(buf, 0, read);
	      size += read;
	    }
	    return size;
	  }

	/* (non-Javadoc)
	 * @see de.hbz.proto_gap_dipp.util.IfRequestPart#getName()
	 */
	public String getName() {
		return _name;
	}

	/* (non-Javadoc)
	 * @see de.hbz.proto_gap_dipp.util.IfRequestPart#isFile()
	 */
	public boolean isFile() {
		return true;
	}

	/* (non-Javadoc)
	 * @see de.hbz.proto_gap_dipp.util.IfRequestPart#isParam()
	 */
	public boolean isParam() {
		return false;
	}

}
