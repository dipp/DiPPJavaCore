package de.nrw.dipp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;

import de.nrw.dipp.dippCore.task.XMLProcessor;
import de.nrw.dipp.dippCore.util.Constant;

public class TestXMLProcessor {

	public TestXMLProcessor(){
		try{
			processXML(new File("c:\\temp\\jvrb\\777\\article.xml"));
			System.out.println("TestXMLProcessor " + " after processXML");
		}catch(IOException ioExc){
			ioExc.printStackTrace();
			System.out.println("catch IOExc");
		}catch(Exception exc){
			exc.printStackTrace();
			System.out.println("catch exc");			
		}
	}
	
	public void processXML(File aFile)throws FileNotFoundException, IOException{
		XMLProcessor xmlProc = XMLProcessor.getInstance();
		xmlProc.setFile(aFile);			
		System.out.println("Method processXML" + " after setFile");
	}
	
	public static void main(String[] args){
		Constant.setAbsolutPath("c:\\");
		TestXMLProcessor xmlProc = new TestXMLProcessor();
	}
}
