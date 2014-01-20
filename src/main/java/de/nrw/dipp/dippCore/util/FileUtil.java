package de.nrw.dipp.dippCore.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import de.nrw.dipp.dippCore.task.Filter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * <p>Title: FileUtil.java</p>
 * <p>Description: A class handling files.</p>
 *
 * -----------------------------------------------------------------------------
 *
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: FileUtil.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class FileUtil {
	
	private File 	rootDir;
	private File 	targetDir;
	private File 	nativeFile;
	private String 	nativeFileMimeType;
	private boolean mIsCompressedZip = false;
	private File 	compressedFile;
	private List  	mBatchprocessList = new Vector();
	private String 	mNativeDocIdent = "";

    // Get Logger for Class
	private static Logger log = Logger.getLogger(FileUtil.class);

	public FileUtil(){
		
	}
	
	public void setNativeDocIdent(String aIdent){
		mNativeDocIdent = aIdent;
	}
	
	public String getNativeDocIdent(){
		return mNativeDocIdent;
	}
	
	public boolean isCompressedZip(){ return mIsCompressedZip;}
	
	public boolean isBatchProcess(){ return !mBatchprocessList.isEmpty();}
	
	public List getBatchProcessList(){
		return mBatchprocessList;
	}
	
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: Method creates an unique ID and directory with this 
	 * unique name for to store native file and each conversion result (file) </p>
	 * 
	 * @param aIdentifier 
	 */
	public void create(String aIdentifier){
		String id = "";
		int counter = 1;
		long timeStamp = Calendar.getInstance().getTimeInMillis();
		
		if (aIdentifier != null){
			id = aIdentifier;
		}
		
		rootDir = new File(Constant.getAbsolutPath() 
				+ "/WEB-INF/convert/" + id.replace(':', '_') + "_" + timeStamp + "_" + counter);
		while (rootDir.exists()){
			rootDir = new File(Constant.getAbsolutPath() 
					+ "/WEB-INF/convert/" + id.replace(':', '_') + "_" + timeStamp + "_" + counter);
			counter++;
		}
		
		/*
		rootDir = new File(Constant.getcUpcastWorkDirPath() 
				+ id.replace(':', '_') + "_" + timeStamp + "_" + counter);
		while (rootDir.exists()){
			rootDir = new File(Constant.getcUpcastWorkDirPath() 
					+ id.replace(':', '_') + "_" + timeStamp + "_" + counter);
			counter++;
		}
		*/
		rootDir.mkdir();
		targetDir = new File(rootDir.getAbsolutePath() + "/target");
		targetDir.mkdir();
		nativeFile = new File(rootDir.getAbsolutePath() + "/" + Constant.cDefaultFilename);
		log.info("Absolute Path: " + nativeFile.getAbsolutePath());
		try{
			nativeFile.createNewFile();
			log.info("created File: " + nativeFile.getAbsolutePath());
		}catch(IOException ioExc){
			log.error(ioExc);
			ioExc.printStackTrace();
		}finally{
			//nativeFile
		}
	}
	
	public String getRootDir(){
		log.info("called rootDirGetter, path is: " + rootDir.getAbsolutePath());
		return rootDir.getAbsolutePath();
	}
	
	public String getTargetDir(){
		log.info("called targetDirGetter, path is: " + targetDir.getAbsolutePath());
		return targetDir.getAbsolutePath();
	}
/*	
	public void setNativeFile(File aFile){
		nativeFile = aFile;
	}
	
	public void setNativeFile(String aFilename){
		nativeFile = new File(aFilename);
	}
*/	
	public File getNativeFile(){
		return nativeFile;
	}
	
	public File getCompressedFile(){
		return compressedFile;
	}
	
	public String getNativeFilename(){
		return nativeFile.getAbsolutePath();
	}
	
	public String getCompressedFileFilename(){
		return compressedFile.getAbsolutePath();
	}
	
	/*
	 * <p>set the filename by mimetype, valid only for "rtf" and "xml"</p>
	 */
	public void setNativeFileMimeType(String aMimeType){
		nativeFileMimeType = aMimeType;
		File newFile;
		if (nativeFileMimeType.endsWith("rtf")){
			if (nativeFile.getAbsolutePath().endsWith(".rtf")){
				newFile = new File(nativeFile.getAbsolutePath());
			}else{
				newFile = new File(nativeFile.getAbsolutePath() + ".rtf");				
			}					
			nativeFile.renameTo(newFile);
			nativeFile = newFile;
		}else if (nativeFileMimeType.endsWith("xml")){
			if (nativeFile.getAbsolutePath().endsWith(".xml")){
				newFile = new File(nativeFile.getAbsolutePath());
			}else{
				newFile = new File(nativeFile.getAbsolutePath() + ".xml");				
			}					
			nativeFile.renameTo(newFile);
			nativeFile = newFile;
		}
	}
	
	public String getNativeFileMimeType(){
		return nativeFileMimeType;
	}
	
	public void unzip()throws IOException{
		ZipFile zipFile = new ZipFile(nativeFile);
		BufferedOutputStream outStream = null;
		try{
			Enumeration enumZips = zipFile.entries();
			while ( enumZips.hasMoreElements()){
				ZipEntry entry = (ZipEntry)enumZips.nextElement();
				if (!entry.isDirectory()){
					log.info("create zip-entry " + entry.getName());
					File outFile = new File(nativeFile.getParent() + "/" + entry.getName());
					if (entry.getName().endsWith("processing.properties")){
						System.out.println("FileUtil: add " + entry.getName() + " to batchprocesslist");
						System.out.println("filutil: " + outFile.getAbsolutePath());
						mBatchprocessList.add(outFile.getAbsolutePath());
					}
					BufferedInputStream inStream = new BufferedInputStream(zipFile.getInputStream(entry));
					if (outFile.getParent() != null){
						System.out.println("create dir " + outFile.getParent());
						File dir = new File(outFile.getParent());
						dir.mkdirs();				
					}
					outStream = new BufferedOutputStream(new FileOutputStream(outFile));
					for (int c; (c = inStream.read()) != -1; ){
						outStream.write( (byte)c);
					}
				}
			}
		}finally{
			outStream.close();				
		}
		mIsCompressedZip = true;
	}
	
	public void declareMainFile(){
		List filterList = new Vector();
		filterList.add(".rtf");
		Filter nf = new Filter (filterList);
		// current directory
		File dir = new File (nativeFile.getParent());
		String[] files = dir.list(nf);
		if (files.length > 0){
			compressedFile = new File(nativeFile.getAbsolutePath());
			nativeFile = new File(compressedFile.getParent() + "/" + files[0]);
		}
	}
	
	public void writeStream(InputStream aInStream)throws IOException{
		BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(getNativeFilename()));
		int len = 0;
		try{
			while ( (len=aInStream.read()) != -1 ){
				outStream.write(len);
			}
		}finally{
			outStream.close();
			log.info("outStream wurde geschlossen");
		}
	}
	
	/**
	 * <p><em>Title: Create a File from byteStream</em></p>
	 * <p>Description: Method creates a file from the bytestream 
	 * representing the original PDF, that should be converted</p>
	 * 
	 * @param stream <code>String</code> 
	 * @return <code>String</code> Filename of newly created temporary File
	 */
	public static String saveStreamToFile(File outputFile, String stream){
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try{
			fos = new FileOutputStream(outputFile);
			bos = new BufferedOutputStream(fos);			
			bos.write(stream.getBytes("UTF-8"));
			bos.flush();
			bos.close();
			
		}catch(IOException ioExc){
			log.error(ioExc);
		}finally{
			if(bos != null){
				try{
					bos.close();
				}catch(IOException ioExc){
					log.error(ioExc);
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch(IOException ioExc){
					log.error(ioExc);
				}
			}
		}
		return outputFile.getName();
	}

	
}
