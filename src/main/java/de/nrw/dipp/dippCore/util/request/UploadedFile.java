/*
 * Created on 23.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.util.request;

import java.io.File;


class UploadedFile {

  private String dir;
  private String filename;
  private String original;
  private String type;
  private String mimeType;

  UploadedFile(String dir, String filename, String original, String type, String aMimeType) {
    this.dir = dir;
    this.filename = filename;
    this.original = original;
    this.type = type;
    this.mimeType = aMimeType;
  }

  public String getMimeType(){
  	return mimeType;
  }

  public void setMimeType(String aMimeType){
  	this.mimeType = aMimeType;
  }

  public String getContentType() {
    return type;
  }

  public String getRelativeFilesystemName() {
    return filename;
  }

  public String getAbsoluteFilesystemName(){
  	return dir + File.separator + filename;
  }

  public String getOriginalFileName() {
    return original;
  }

  public File getFile() {
    if (dir == null || filename == null) {
      return null;
    }
    else {
      return new File(dir + File.separator + filename);
    }
  }
}