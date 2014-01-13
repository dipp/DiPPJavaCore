package de.nrw.dipp.dippCore.task;

import java.io.File;
import java.util.Observable;
import java.util.Properties;

import de.infinityloop.upcast.UpcastEngine;
import de.infinityloop.util.ILException;
//import de.infinityloop.util.ILException;
import de.nrw.dipp.dippCore.util.Constant;

/**
 * <p>Title: Upcast.java</p>
 * <p>Description: A wrapper class for the upcast converter.</p>
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
 * @version $Id: Upcast.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */

public final class Upcast extends Observable{

	private static final String	cUpCastInstanceID	= "DiPP-Upcast-Instance";
	private static String cUpCastOutputDir			= "";
	
	private UpcastEngine 		mEngine 	= null;
//	private ConvertProperties	mConvProps 	= null; 
	private File				mSrcFile	= null;
//	private Properties			mPerlscriptsProps	= new Properties();
	private static boolean		cLocked		= false;
	
	private static final class UpcastHolder{ 
		static final Upcast upcast = new Upcast();
	}
	
	
	
	private Upcast(){
		try{
			cUpCastOutputDir = Constant.getAbsolutPath() + "/WEB-INF/convert";
//			cUpCastConfigFile = Constant.getAbsolutPath() + "/WEB-INF/preferences.xml";
			UpcastEngine.setLicense(UpcastEngine.class.getResourceAsStream(Constant.cLicensePathUpcast));
//			mPerlscriptsProps.load(getClass().getResourceAsStream("perlscripts.properties")); // "perlscripts.properties"));
			mEngine = new UpcastEngine(cUpCastInstanceID);
			mEngine.setDebuggingMode(false);
			mEngine.setGlobalParameter("outputDir", cUpCastOutputDir);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static synchronized Upcast getInstance(){ 
		if (cLocked){
			return null;
		}else{
			cLocked = true;
			return UpcastHolder.upcast;
		}
	}
	
	public void setDebug(boolean aDebug){
		mEngine.setDebuggingMode(aDebug);
	}
	
	public void setConvertProperties(ConvertProperties aConvProps){
//		mConvProps = aConvProps;
	}
	
	public void setSourceFile(File aSrcFile){
		mSrcFile = aSrcFile;
	}
	
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method starts the UpCast Conversion </p>
	 * 
	 * @param aOutputDir
	 * @param aPartOfConversion
	 * @param aSerialNumberOfJournal
	 * @param aArticleType
	 * @throws ConvertException 
	 */
	public void doConvert(String aOutputDir, int aPartOfConversion, String aSerialNumberOfJournal, String aArticleType)throws ConvertException{
		try{
			String upCastConfigFile = null;
			upCastConfigFile = Constant.getAbsolutPath() + "/WEB-INF/plist/" + aSerialNumberOfJournal;
			String configFile_2 = Constant.getAbsolutPath() + "/WEB-INF/plist/" + aSerialNumberOfJournal + "-part02";
			if (isReadable(upCastConfigFile + "-" + aArticleType + "-part01.plist")){
				upCastConfigFile = upCastConfigFile + "-" + aArticleType;
			}
			switch(aPartOfConversion) {
				case 1:	upCastConfigFile = upCastConfigFile + "-part01.plist";
						System.out.println("upcast-File: " + upCastConfigFile + " outDir: " + aOutputDir);
						mEngine.convertDocumentWithConfiguration(mSrcFile.getAbsolutePath(), aOutputDir, upCastConfigFile);
						break;
				case 2:	upCastConfigFile = upCastConfigFile + "-part02.plist";
						System.out.println("upcast-File: " + upCastConfigFile + " outDir: " + aOutputDir);
						mEngine.convertDocumentWithConfiguration(mSrcFile.getAbsolutePath(), aOutputDir, upCastConfigFile);
						break;
			}
		}catch(ILException ilExc){
			ilExc.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ConvertException("Fehler beim Konvertieren!");
		}
	}
	
	private boolean isReadable(String aFilename){
		File f = new File(aFilename);
		return (f.exists() && f.canRead());
	}
	
	
	public String getHtmlFileName(){
		return mSrcFile.getName().substring(0, mSrcFile.getName().lastIndexOf("."));
	}
	
/*	protected String getPerlscriptTOC(){
		return mPerlscriptsProps.getProperty("toc");
	}
	
	protected String getPerlscriptHTML(){
		return mPerlscriptsProps.getProperty("html");
	}
	
	protected String getPerlscriptFAST(){
		return mPerlscriptsProps.getProperty("fast");
	}
*/	
	public void doNotify(){
		setChanged();
		notifyObservers();
		release();
	}
	
	public void release(){
		cLocked = false;
	}
	
		
}
