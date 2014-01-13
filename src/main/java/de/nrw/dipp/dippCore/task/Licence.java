package de.nrw.dipp.dippCore.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class Licence {

	private Document 	mDoc			= null;
	private Element		mRootElement	= null;

	public Licence(){
		mDoc = new Document();
		mRootElement = new Element("legalNote");
		mDoc.addContent(mRootElement);		
	}

	public Licence(InputStream aXMLInputStream){
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
		Element legalNote = new Element("legalNote");
		doc.addContent(legalNote);
		Element licence_de = new Element("licence");
		licence_de.setAttribute("lang", "de");		
		Element title_de = new Element("title");
		licence_de.addContent(title_de);
		legalNote.addContent(licence_de);
		title_de.setText("Lizenz");
		Element text_de_dppl = new Element("text");
		licence_de.addContent(text_de_dppl);
		text_de_dppl.setAttribute("type", "dppl");
		text_de_dppl.setText("Jedermann darf dieses Werk unter den Bedingungen " + 
						"der Digital Peer Publishing Lizenz elektronisch " + 
						"übermitteln und zum Download bereitstellen. " + 
						"Der Lizenztext ist im Internet abrufbar unter " + 
						"der Adresse http://www.dipp.nrw.de/lizenzen/dppl/dppl/DPPL_v2_de_06-2004.html");
		Element text_de_fdppl = new Element("text");
		licence_de.addContent(text_de_fdppl);
		text_de_fdppl.setAttribute("type", "fdppl");
		text_de_fdppl.setText("Jedermann darf dieses Werk unter den Bedingungen " + 
						"der freien Digital Peer Publishing Lizenz elektronisch " + 
						"übermitteln und zum Download bereitstellen. " + 
						"Der Lizenztext ist im Internet abrufbar unter " + 
						"der Adresse http://www.dipp.nrw.de/lizenzen/dppl/fdppl/f-DPPL_v1_de_11-2004.html");
		Element text_de_mdppl = new Element("text");
		licence_de.addContent(text_de_mdppl);
		text_de_mdppl.setAttribute("type", "mdppl");
		text_de_mdppl.setText("Jedermann darf dieses Werk unter den Bedingungen " + 
						"der freien Digital Peer Publishing Lizenz elektronisch " + 
						"übermitteln und zum Download bereitstellen. " + 
						"Der Lizenztext ist im Internet abrufbar unter " + 
						"der Adresse http://www.dipp.nrw.de/lizenzen/dppl/mdppl/m-DPPL_v1_de_11-2004.html");
		// english
		Element licence_en = new Element("licence");
		legalNote.addContent(licence_en);
		licence_en.setAttribute("lang", "en");
		Element title_en = new Element("title");
		licence_en.addContent(title_en);
		title_en.setText("Licence");
		Element text_en_dppl = new Element("text");
		licence_en.addContent(text_en_dppl);
		text_en_dppl.setAttribute("type", "dppl");
		text_en_dppl.setText("Any party may pass on this Work by electronic means " + 
								"and make it available for download under the terms and conditions of " + 
								"the Digital Peer Publishing Licence. The text of the licence may be accessed " + 
								"and retrieved via Internet at http://www.dipp.nrw.de/lizenzen/dppl/dppl/DPPL_v2_en_06-2004.html");
		Element text_en_fdppl = new Element("text");
		licence_en.addContent(text_en_fdppl);
		text_en_fdppl.setAttribute("type", "fdppl");
		text_en_fdppl.setText("Any party may pass on this Work by electronic means " + 
				"and make it available for download under the terms and conditions of " + 
				"the free Digital Peer Publishing Licence. The text of the licence may be accessed " + 
				"and retrieved via Internet at http://www.dipp.nrw.de/lizenzen/dppl/fdppl/f-DPPL_v1_de_11-2004.html");
		Element text_en_mdppl = new Element("text");
		licence_en.addContent(text_en_mdppl);
		text_en_mdppl.setAttribute("type", "mdppl");
		text_en_mdppl.setText("Any party may pass on this Work by electronic means " + 
								"and make it available for download under the terms and conditions of " + 
								"the modular Digital Peer Publishing Licence. The text of the licence may be accessed " + 
								"and retrieved via Internet at http://www.dipp.nrw.de/lizenzen/dppl/mdppl/m-DPPL_v1_de_11-2004.html");
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(doc, System.out);
	}
	
	public String getTitle(String aLanguage){
		String title = "";
		Iterator it = mDoc.getRootElement().getChildren().iterator();
		while(it.hasNext()){
			Element licence = (Element)it.next();
			if (licence.getAttributeValue("lang").equals(aLanguage)){
				title = licence.getChild("title").getText();
				continue;
			}
		}
		return title;
	}
	
	public String getLicence(String aLanguage, String aLicenceType){
		String licencetext = "";
		Iterator it = mDoc.getRootElement().getChildren().iterator();
		while(it.hasNext()){
			Element licence = (Element)it.next();
			if (licence.getAttributeValue("lang").equals(aLanguage)){
				Iterator itText = licence.getChildren("text").iterator();
				while (itText.hasNext()){
					Element text = (Element)itText.next();
					if (text.getAttributeValue("type").equals(aLicenceType)){
						licencetext = text.getText();
						continue;
					}
				}
				continue;
			}
		}
		return licencetext;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			FileInputStream inStream = new FileInputStream("c:\\user\\jochen\\src\\dipp\\src\\java\\licence.xml");			
			Licence l = new Licence(inStream);
			System.out.println("title: " + l.getTitle("en"));
			System.out.println("text: " + l.getLicence("en", "fdppl"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
