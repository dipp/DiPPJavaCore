package de.nrw.dipp.dippCore.util.request;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.task.Upcast;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;



/**
 * <p>Title: MimeType.java</p>
 * <p>Description: Determines the mimeType for a given File.</p>
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>
 * Uses the jmimemagic-package
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: MimeType.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public final class MimeType {
	
    // Get Logger for MimeType
	private static Logger log = Logger.getLogger(MimeType.class);

	private Magic magic = new Magic();

	private static final class MimeTypeHolder {
	     static final MimeType mimeType =
	       new MimeType();
	   }
	private MimeType(){
	}
	
	public static MimeType getInstance(){
		return MimeTypeHolder.mimeType;
	}
	
	public String getMimeType(File aFile){
		String mimeType = "unkown";
		System.out.println("MimeType a");
		try{
			System.out.println("MimeType: file: " + aFile.getName()); 
			log.info("MimeType: file: " + aFile.getAbsolutePath());
			
			/*
			List aList = (List) magic.getMatchers();
			Iterator test = aList.iterator();
			
			while(test.hasNext()){
				log.info(test.next().toString());
			}
			*/
			
			MagicMatch match = magic.getMagicMatch(aFile);
			System.out.println("MimeType aaa");
			mimeType = match.getMimeType();
			System.out.println("MimeType b");
		}catch(Exception e){
			e.printStackTrace();
		}
/*		}catch(MagicException e){
			System.out.println("MimeType error a");
			e.printStackTrace();
		}catch(MagicParseException pe){
			System.out.println("MimeType error b");
			pe.printStackTrace();
		}catch(MagicMatchNotFoundException nfe){
			System.out.println("MimeType error c");
			nfe.printStackTrace();
		}catch(Exception e){
			System.out.println("MimeType error d");
			e.printStackTrace();
		}
*/		
		System.out.println("MimeType c: " + mimeType);
		
		return mimeType;
	}

/*	
	private void createMimeTypes(){
		String cWebapp				= "web-app";
		String cMimeMapping 		= "mime-mapping";
		String cMimeType			= "mime-type";
		String cExtension			= "extension";

		try{
			SAXBuilder 	builder = new SAXBuilder();
			Document 	doc = builder.build(getClass().getResourceAsStream("mimetype.xml"));
			Element rootElem = doc.getRootElement();
			rootElem.getText();
			Iterator it = rootElem.getContent().iterator();
			while (it.hasNext()){
				Content content = (Content)it.next();
				if (content instanceof Element){
					Element childOfRootElem = (Element)content;
					if (childOfRootElem.getName().equals(cMimeMapping)){
						Iterator itChild = childOfRootElem.getContent().iterator();
						String mimeType = "";
						String extension = "";
						while (itChild.hasNext()){
							Content childContent = (Content)itChild.next();
							if (childContent instanceof Element){
								Element childOfChildOfRootElement = (Element)childContent;
								if (childOfChildOfRootElement.getName().equals(cMimeType)){
									mimeType = childOfChildOfRootElement.getValue();
								}else if (childOfChildOfRootElement.getName().equals(cExtension)){										
									extension = childOfChildOfRootElement.getValue();
								}
							}
						}
						mMimeTypeProperties.setProperty(extension, mimeType);
					}
				}
			}
		}catch(JDOMException excJDOM){
			excJDOM.printStackTrace();
		}catch(IOException excIO){
			excIO.printStackTrace();
		}	

	}
*/
}
