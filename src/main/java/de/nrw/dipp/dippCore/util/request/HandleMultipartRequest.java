/*
 * Created on 21.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.util.request;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleMultipartRequest{

	private static String 		DEFAULT_ENCODING 	= "ISO-8859-1";
	private String				_contentType		= "";
	private int					_contentLength		= 0;
	private String				_boundary			= "";
	private Properties			_queryProps			= new Properties();
	private ServletInputStream	_inputStream		= null;
	private String 				_encoding 			= DEFAULT_ENCODING;
	private RequestPartFile		_lastFilePart		= null;
	private File				_uploadDir			= null;
	protected Hashtable 		_uploadFileTable	= new Hashtable();       // name - UploadedFile
	
	public HandleMultipartRequest(HttpServletRequest request, String saveDirectory)  throws IOException{

	    _uploadDir = new File(saveDirectory);

	    // Check saveDirectory is truly a directory
	    if (!_uploadDir.isDirectory())
	      throw new IllegalArgumentException("Not a directory: " + saveDirectory);

	    // Check saveDirectory is writable
	    if (!_uploadDir.canWrite())
	      throw new IllegalArgumentException("Not writable: " + saveDirectory);
		
		if (request.getContentType() != null){
				_contentType = request.getContentType();
			}else if (request.getHeader("Content-Type") != null){
				_contentType = request.getHeader("Content-Type");
			}
			if (!_contentType.toLowerCase().startsWith("multipart/form-data")){
				throw new IOException("contentType is not multipart/form-data");
			}
			_contentLength = request.getContentLength();
			_boundary = extractBoundary(_contentType);
			_inputStream = request.getInputStream();
		    do {
		        String line = readLine();
		        if (line == null) {
		          throw new IOException("Corrupt form data: premature ending");
		        }
		        // See if this line is the boundary, and if so break
		        if (line.startsWith(_boundary)) {
		          break;  // success
		        }
		      } while (true);

			try{
				_queryProps = new Properties(); //getProperties(request);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		    IfRequestPart part;
		    while ((part = readNextPart()) != null) {
		      String name = part.getName();
		      if (part.isParam()) {
		        // It's a parameter part, add it to the vector of values
		        RequestPartParam paramPart = (RequestPartParam) part;
		        String value = paramPart.getStringValue();
		    	if (name == null){
		    	}
		        Vector existingValues = (Vector)_queryProps.get(name);
		        if (existingValues == null) {
		          existingValues = new Vector();
		          _queryProps.put(name, existingValues);
		        }
		        existingValues.addElement(value);
		      }
		      else if (part.isFile()) {
		        // It's a file part
		      	System.out.println("it's a file");
		        RequestPartFile filePart = (RequestPartFile) part;
		        String fileName = filePart.getFileName();
		      	System.out.println("it's a file: " + fileName);
		        if (fileName != null) {
//		          filePart.setRenamePolicy(policy);  // null policy is OK
		          // The part actually contained a file
		          filePart.writeTo(_uploadDir);
		          _uploadFileTable.put(name, new UploadedFile(_uploadDir.toString(),
		                                           filePart.getFileName(),
		                                           fileName,
		                                           filePart.getContentType(), filePart.getMimeType()));
		        }
		        else { 
		          // The field did not contain a file
		          _uploadFileTable.put(name, new UploadedFile(null, null, null, null, null));
		        }
		      }
		    }
			
	}

	public String getContentType(){
		return _contentType;
	}
	
	public int getContentLength(){
		return _contentLength;
	}
	
	public String getBoundary(){
		return _boundary;
	}
	
	public Properties getRequestPropertyParameter(){
		Properties requestProperties = new Properties();
		Enumeration e = _queryProps.keys();
		while ( e.hasMoreElements()){
			String key = (String)e.nextElement();
			String[] value = (String[])((Vector)_queryProps.get(key)).toArray(new String[0]);
            if (value.length == 1){
                requestProperties.put(key, value[0]);
            }else{
    			requestProperties.put(key, value);
            }

		}
		return requestProperties;
	}
	
	public Hashtable getRequestPropertyFiles(){
		return _uploadFileTable;
/*		Properties requestProperties = new Properties();
		Enumeration e =_uploadFileTable.keys(); 
		while (e.hasMoreElements()){
			String key = (String)e.nextElement();
			requestProperties.put(key, ((UploadedFile)_uploadFileTable.get(key)).getAbsoluteFilesystemName());
		}
		return requestProperties;
*/		
	}
	
	
	public Enumeration getParameterNames(){		
		return _queryProps.keys();
	}
	
	public Enumeration getFileNames(){
		return _uploadFileTable.keys();
	}

	public String getFilesystemName(String name) {
	    try {
	      UploadedFile file = (UploadedFile)_uploadFileTable.get(name);
	      return file.getRelativeFilesystemName();  // may be null
	    }
	    catch (Exception e) {
	      return null;
	    }
	}
	public String getContentType(String name) {
	    try {
	      UploadedFile file = (UploadedFile)_uploadFileTable.get(name);
	      return file.getContentType();  // may be null
	    }
	    catch (Exception e) {
	      return null;
	    }
	}
	
	/*
	 * if name is null returns the first available file otherwise null
	 * if name is not null returns the file with the given name if available
	 */
	public File getFile(String name) {
	    UploadedFile file = null;
		try {
			if (name == null){
				Properties requestProperties = new Properties();
				Enumeration e =_uploadFileTable.keys(); 
				while (e.hasMoreElements()){
					String key = (String)e.nextElement();
					file = (UploadedFile)_uploadFileTable.get(key);
					break;
				}
				
			}else{
				file = (UploadedFile)_uploadFileTable.get(name);
			}
			return file.getFile();  // may be null
	    }
	    catch (Exception e) {
	      return null;
	    }
	}
	
	
	private String extractBoundary(String aLine){
	    int index = aLine.lastIndexOf("boundary=");
	    if (index == -1) {
	      return null;
	    }
	    String boundary = aLine.substring(index + 9);  // 9 for "boundary="
	    if (boundary.charAt(0) == '"') {
	      // The boundary is enclosed in quotes, strip them
	      index = boundary.lastIndexOf('"');
	      boundary = boundary.substring(1, index);
	    }

	    // The real boundary is always preceeded by an extra "--"
	    boundary = "--" + boundary;

	    return boundary;		
	}

	/**
     *  translates servlet request to java properties
     */
/*    private Properties getProperties(HttpServletRequest request){
        Properties properties = new Properties();
        for (Enumeration enum = request.getParameterNames();
                                enum.hasMoreElements();){
            Object obj = enum.nextElement();
            System.out.println("PRioperties: " + (String)obj);
            String[] values = request.getParameterValues((String)obj);
            if (values.length == 1){
            	System.out.println("PRoiperties: " + values[0]);
                properties.put(obj, values[0]);
            }else{
                for (int i = 0; i < values.length; i++){
                }
                
                properties.put(obj, values);
            }
        }
        return properties;
    }
*/    
    
    
    private IfRequestPart readNextPart() throws IOException {
        // Make sure the last file was entirely read from the input
        if (_lastFilePart != null) {
          _lastFilePart.getInputStream().close();
          _lastFilePart = null;
        }
        
        // Read the headers; they look like this (not all may be present):
        // Content-Disposition: form-data; name="field1"; filename="file1.txt"
        // Content-Type: type/subtype
        // Content-Transfer-Encoding: binary
        Vector headers = new Vector();

        String line = readLine();
        if (line == null) {
          // No parts left, we're done
          return null;
        }
        else if (line.length() == 0) {
          // IE4 on Mac sends an empty line at the end; treat that as the end.
          // Thanks to Daniel Lemire and Henri Tourigny for this fix.
          return null;
        }

        // Read the following header lines we hit an empty line
        // A line starting with whitespace is considered a continuation;
        // that requires a little special logic.  Thanks to Nic Ferrier for
        // identifying a good fix.
        while (line != null && line.length() > 0) {
          String nextLine = null;
          boolean getNextLine = true;
          while (getNextLine) {
            nextLine = readLine();
            if (nextLine != null
                && (nextLine.startsWith(" ")
          	  || nextLine.startsWith("\t"))) {
              line = line + nextLine;
            }
            else {
              getNextLine = false;
            }
          }
          // Add the line to the header list
          headers.addElement(line);
          line = nextLine;
        }

        // If we got a null above, it's the end
        if (line == null) {
          return null;
        }

        String name = null;
        String filename = null;
        String origname = null;
        String contentType = "text/plain";  // rfc1867 says this is the default

        Enumeration headerEnum = headers.elements();
        while (headerEnum.hasMoreElements()) {
          String headerline = (String) headerEnum.nextElement();
          if (headerline.toLowerCase().startsWith("content-disposition:")) {
            // Parse the content-disposition line
            String[] dispInfo = extractDispositionInfo(headerline);
            // String disposition = dispInfo[0];  // not currently used
            name = dispInfo[1];
            filename = dispInfo[2];
            origname = dispInfo[3];
          }
          else if (headerline.toLowerCase().startsWith("content-type:")) {
            // Get the content type, or null if none specified
            String type = extractContentType(headerline);
            if (type != null) {
              contentType = type;
            }
          }
        }

        // Now, finally, we read the content (end after reading the boundary)
        if (filename == null) {
          // This is a parameter, add it to the vector of values
          // The encoding is needed to help parse the value
          return new RequestPartParam(name, _inputStream, _boundary, _encoding);
        }
        else {
          // This is a file
          if (filename.equals("")) {
            filename = null; // empty filename, probably an "empty" file param
          }
          _lastFilePart = new RequestPartFile(name, _inputStream, _boundary,
                                      contentType, filename, origname);
          return _lastFilePart;
        }
      }
    private String readLine() throws IOException {
        StringBuffer sbuf = new StringBuffer();
        int result;
        String line;
        byte[] buf = new byte[8 * 1024];

        do {
          result = _inputStream.readLine(buf, 0, buf.length);  // does +=
          if (result != -1) {
            sbuf.append(new String(buf, 0, result, _encoding));
          }
        } while (result == buf.length);  // loop only if the buffer was filled

        if (sbuf.length() == 0) {
          return null;  // nothing read, must be at the end of stream
        }

        // Cut off the trailing \n or \r\n
        // It should always be \r\n but IE5 sometimes does just \n
        // Thanks to Luke Blaikie for helping make this work with \n
        int len = sbuf.length();
        if (len >= 2 && sbuf.charAt(len - 2) == '\r') {
          sbuf.setLength(len - 2);  // cut \r\n
        }
        else if (len >= 1 && sbuf.charAt(len - 1) == '\n') {
          sbuf.setLength(len - 1);  // cut \n
        }
        return sbuf.toString();
      }
    
    private String[] extractDispositionInfo(String line) throws IOException {
        // Return the line's data as an array: disposition, name, filename
        String[] retval = new String[4];

        // Convert the line to a lowercase string without the ending \r\n
        // Keep the original line for error messages and for variable names.
        String origline = line;
        line = origline.toLowerCase();

        // Get the content disposition, should be "form-data"
        int start = line.indexOf("content-disposition: ");
        int end = line.indexOf(";");
        if (start == -1 || end == -1) {
          throw new IOException("Content disposition corrupt: " + origline);
        }
        String disposition = line.substring(start + 21, end);
        if (!disposition.equals("form-data")) {
          throw new IOException("Invalid content disposition: " + disposition);
        }

        // Get the field name
        start = line.indexOf("name=\"", end);  // start at last semicolon
        end = line.indexOf("\"", start + 7);   // skip name=\"
        int startOffset = 6;
        if (start == -1 || end == -1) {
          // Some browsers like lynx don't surround with ""
          // Thanks to Deon van der Merwe, dvdm@truteq.co.za, for noticing
          start = line.indexOf("name=", end);
          end = line.indexOf(";", start + 6);
          if (start == -1) {
            throw new IOException("Content disposition corrupt: " + origline);
          }
          else if (end == -1) {
            end = line.length();
          }
          startOffset = 5;  // without quotes we have one fewer char to skip
        }
        String name = origline.substring(start + startOffset, end);

        // Get the filename, if given
        String filename = null;
        String origname = null;
        start = line.indexOf("filename=\"", end + 2);  // start after name
        end = line.indexOf("\"", start + 10);          // skip filename=\"
        if (start != -1 && end != -1) {                // note the !=
          filename = origline.substring(start + 10, end);
          origname = filename;
          // The filename may contain a full path.  Cut to just the filename.
          int slash =
            Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
          if (slash > -1) {
            filename = filename.substring(slash + 1);  // past last slash
          }
        }

        // Return a String array: disposition, name, filename
        // empty filename denotes no file posted!
        retval[0] = disposition;
        retval[1] = name;
        retval[2] = filename;
        retval[3] = origname;
        return retval;
      }
    private static String extractContentType(String line) throws IOException {
        // Convert the line to a lowercase string
        line = line.toLowerCase();

        // Get the content type, if any
        // Note that Opera at least puts extra info after the type, so handle
        // that.  For example:  Content-Type: text/plain; name="foo"
        // Thanks to Leon Poyyayil, leon.poyyayil@trivadis.com, for noticing this.
        int end = line.indexOf(";");
        if (end == -1) {
          end = line.length();
        }

        return line.substring(13, end).trim();  // "content-type:" is 13
      }
}
