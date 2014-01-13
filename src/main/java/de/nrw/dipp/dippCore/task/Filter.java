package de.nrw.dipp.dippCore.task;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
	import java.io.File;
	import java.io.FilenameFilter;
import java.util.List;
import java.util.Vector;
	public class Filter implements FilenameFilter {
	  protected String pattern;
	  protected List patternList = new Vector();
	  
	  public Filter (List aPatternList) {
	    patternList = aPatternList;
	  }
	  
	  public boolean accept (File dir, String name) {
	  	if (name.toLowerCase().lastIndexOf(".") != -1){
	  		String suffix = name.substring(name.toLowerCase().lastIndexOf("."));
		  	return patternList.contains(suffix);
	  	}else{
	  		return false;
	  	}
//	    return name.toLowerCase().endsWith(pattern.toLowerCase());
	  }
	  
	  public static void main (String args[]) {
/*	    if (args.length != 1) {
	       System.err.println ("usage: java Filter   ex. java Filter java");
	       return;
	       }
*/	    
	  	
	  	List p = new Vector();
	  	p.add(".jpg");
	  	p.add(".png");
	  	p.add(".gif");
	  	
	    Filter nf = new Filter (p);
	    // current directory
	    File dir = new File ("c:\\temp\\");
	    String[] strs = dir.list(nf);
	    for (int i = 0; i < strs.length; i++) {
	      System.out.println (strs[i]);
	      }
	    }
	}

