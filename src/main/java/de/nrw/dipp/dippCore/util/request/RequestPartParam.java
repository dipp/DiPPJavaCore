/*
 * Created on 21.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.util.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;


/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequestPartParam implements IfRequestPart {

	private String			_name = "";
	private byte[] 			_value;
	private String			_encoding = "";
	
	public RequestPartParam(	String name, 
						ServletInputStream in, 
						String boundary, 
						String encoding) throws IOException {
		_encoding = encoding;
    	
		// Copy the part's contents into a byte array
		PartInputStream pis = new PartInputStream(in, boundary);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
	    byte[] buf = new byte[128];
	    int read;
	    while ((read = pis.read(buf)) != -1) {
	      baos.write(buf, 0, read);
	    }
	    pis.close();
	    baos.close();
	    
	    // save it for later
	    _value = baos.toByteArray();
	    _name = name;
	    _encoding = encoding;
		
	}

	public byte[] getValue() {
	    return _value;
	}

	public String getStringValue() 
      throws UnsupportedEncodingException {
		return getStringValue(_encoding);
	}

	public String getStringValue(String encoding) 
      throws UnsupportedEncodingException {
		return new String(_value, encoding);
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
		return false;
	}

	/* (non-Javadoc)
	 * @see de.hbz.proto_gap_dipp.util.IfRequestPart#isParam()
	 */
	public boolean isParam() {
		return true;
	}

}
