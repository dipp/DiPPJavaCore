/**
 * ChecksumGenerator.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.nrw.dipp.dippCore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;

/**
 * Class ChecksumGenerator
 * 
 * <p><em>Title: Class generates a checksum for any File</em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 27.02.2012
 *
 */
public class ChecksumGenerator {

	// Initiate Logger for ChecksumGenerator
	private static Logger log = Logger.getLogger(ChecksumGenerator.class);

	private byte[] stream = null;
	private String algorithm = null;
	
	/**
	 * <p><em>Title: Generate Checksum for Array of Byte</em></p>
	 * <p>Description: </p>
	 * 
	 * @return 
	 */
	private String getChecksum(){
		
		StringBuffer mdBuff = new StringBuffer();
		MessageDigest md = null;


		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		md.update(stream);

		/* digest returns the Hash as Array of Byte */
		byte[] digest = md.digest();

		/* run through the Array of Bytes and convert bytes */
		for (byte d : digest) {

			/* code from http... */
			/* Funktionierende Variante */
			/* Sicherstellen, das keine negativen Vorzeichen vorhanden sind */
			/* Sicherstellen, das der Hexwert des Bytes 2-stellig ausgegeben wird (Vornullen) */
			mdBuff.append(Integer.toHexString((d & 0xFF) | 0x100).toLowerCase().substring(1, 3));
		}

		return mdBuff.toString();
	}
	
	public String getMD5(byte[] Stream){
		String checksum = null;
		algorithm = "MD5";
		log.info(algorithm);
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}

	public String getRIPEMD160(byte[] Stream){
		String checksum = null;
		algorithm = "RIPEMD160";
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}

	public String getSHA1(byte[] Stream){
		String checksum = null;
		algorithm = "SHA1";
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}

	public String getSHA256(byte[] Stream){
		String checksum = null;
		algorithm = "SHA256";
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}


	public String getSHA384(byte[] Stream){
		String checksum = null;
		algorithm = "SHA384";
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}

	public String getSHA512(byte[] Stream){
		String checksum = null;
		algorithm = "SHA512";
		stream = Stream;
		checksum = getChecksum();
		return checksum;
	}

}
