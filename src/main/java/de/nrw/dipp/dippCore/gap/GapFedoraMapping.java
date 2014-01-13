/*
 * Created on 03.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.gap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GapFedoraMapping {
	
	private Document 	mDoc			= null;
	private Element		mRootElement	= null;
	
	public GapFedoraMapping(){
		mDoc = new Document();
		mRootElement = new Element("mapping");
		mDoc.addContent(mRootElement);
	}
	
	public GapFedoraMapping(InputStream aXMLInputStream){
		try{
			SAXBuilder builder = new SAXBuilder();
			mDoc = builder.build(aXMLInputStream);
		}catch(JDOMException jdomExc){
			jdomExc.printStackTrace();
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}
	
	public void createDocument()throws IOException
	{
		Document doc = new Document();
		Element mapping = new Element("mapping");
		doc.addContent(mapping);
		Element param = new Element("param");
		mapping.addContent(param);
		Element key = new Element("key");
		key.setText("Afrikanistik Online");
		param.addContent(key);
		Element value = new Element("value");
		value.setText("10");
		param.addContent(value);
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(doc, System.out);
	}
	
	public void add(String aKey, String aValue){
		Element param = new Element("param");
		mRootElement.addContent(param);
		Element key = new Element("key");
		key.setText(aKey);
		param.addContent(key);
		Element value = new Element("value");
		value.setText(aValue);
		param.addContent(value);		
	}
	
	public void persist(OutputStream aOutstream)throws IOException{
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(mDoc, aOutstream);
	}
	
	public Properties getMappingProperties(){
		Properties props = new Properties();
		Iterator it = mDoc.getRootElement().getChildren().iterator();
		while(it.hasNext()){
			Element param = (Element)it.next();
			props.setProperty(param.getChild("key").getText(), param.getChild("value").getText());
		}
		return props;
	}
	
	public void toXMLString(){
		try{
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(mDoc, System.out);
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			GapFedoraMapping mapping = new GapFedoraMapping(new FileInputStream("c:\\user\\jochen\\src\\fedoraXMLBeans\\src\\de\\nrw\\dipp\\oai\\mapping.xml"));
			mapping.toXMLString();
			System.out.println(mapping.getMappingProperties().getProperty("zeitenblicke"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
/*		GapFedoraMapping mapping = new GapFedoraMapping();
		try{
			mapping.add("Afrikanistik online", "10");
			mapping.add("Constructions", "4");
			mapping.add("German Risk and Insurance Review", "8");
			mapping.add("RTejournal", "2");
			mapping.add("brains mind and media", "3");
			mapping.add("eleed", "5");
			mapping.add("jvrb", "6");
			mapping.add("languagATinternet", "7");
			mapping.add("zeitenblicke", "9");
			
			mapping.persist(new FileOutputStream("c:\\user\\jochen\\src\\fedoraXMLBeans\\src\\de\\nrw\\dipp\\oai\\mapping.xml"));
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
*/	
	}

}
