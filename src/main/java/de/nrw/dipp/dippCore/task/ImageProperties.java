package de.nrw.dipp.dippCore.task;

public class ImageProperties {

	private String srcFileName;
	private String width;
	private String height;
	
	public ImageProperties(String aName, String aWidth, String aHeight){
		srcFileName = aName;
		width = aWidth;
		height = aHeight;
	}
	
	public String getWidth(){ return width;}
	public String getHeight(){ return height;}
	public String getFilename() { return srcFileName;}
}
