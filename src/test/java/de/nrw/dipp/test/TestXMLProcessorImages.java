package de.nrw.dipp.test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import de.nrw.dipp.dippCore.task.ImageProperties;

public class TestXMLProcessorImages {

	List imageList = new Vector();
	
	public TestXMLProcessorImages(File aFile){
		SAXBuilder builder = new SAXBuilder();
		try{
			Document doc = builder.build(aFile);
			Element root = doc.getRootElement();
			listChildren(root, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void listChildren(Element current, int depth) {
		printSpaces(depth);
		System.out.println(current.getName());
		if (current.getName().equals("imagedata")){
			printImageProperties(current);
		}
		List children = current.getChildren();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Element child = (Element)iterator.next();
			listChildren(child, depth+1);
		}
	}
	
	private void printImageProperties(Element imageElement){
		Attribute depth = imageElement.getAttribute("depth");
		Attribute width = imageElement.getAttribute("width");
		Attribute fileref = imageElement.getAttribute("fileref");
		if (depth != null & width != null & fileref != null){
			System.out.println("filename: " + fileref.getValue() + " depth: " + depth.getValue().substring(0, depth.getValue().indexOf("px")) + " width: " + width.getValue().substring(0, width.getValue().indexOf("px")));
			imageList.add(new ImageProperties(fileref.getValue(), width.getValue().substring(0, width.getValue().indexOf("px")), depth.getValue().substring(0, depth.getValue().indexOf("px"))));
		}
	}
	
	public List getImageList(){
		return imageList;
	}
	
	private void printSpaces(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
	
	
	
	public static void main(String[] args){
		TestXMLProcessorImages test = new TestXMLProcessorImages(new File("c:\\temp\\themis\\dippArticle.xml"));
	}
}
