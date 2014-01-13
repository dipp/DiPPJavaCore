/*
 * Created on 29.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.io.File;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Test {

	public static void main(String args[]){
		/*
		TaskManager tm = TaskManager.getInstance();
		Param p = new Param();
		p.setArticlePID("abc");
		tm.addTask(TaskManager.cRegisteredTaskXML, p);
		p = new Param();
		p.setArticlePID("xyz");
		tm.addTask(TaskManager.cRegisteredTaskXML, p);
*/
		
		File f = new File("c:\\temp\\bmm\\stan.rtf");
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getParent());
		System.out.println(f.getPath());
	}
}
