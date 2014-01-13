package de.nrw.dipp.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.xmlbeans.XmlException;
import org.openarchives.oai.x20.HeaderType;
import org.openarchives.oai.x20.ListIdentifiersType;
import org.openarchives.oai.x20.OAIPMHDocument;
import org.openarchives.oai.x20.ResumptionTokenType;

public class TestOaiRecords {

	private Properties specProps = new Properties();
	
	public void parseOAI(String oaiURL)throws XmlException, IOException{
		
		OAIPMHDocument oai = OAIPMHDocument.Factory.parse(new URL(oaiURL));
		ListIdentifiersType listIdents = oai.getOAIPMH().getListIdentifiers();
		HeaderType[] headers = listIdents.getHeaderArray();
		for (int i = 0; i < headers.length; i++){
			List specList = Arrays.asList(headers[i].getSetSpecArray());
			if (!specList.contains("pub-type:journal")){
				Iterator itSpecList = specList.iterator();
				while (itSpecList.hasNext()){
					String spec = (String)itSpecList.next();
					int counter = 0;
					if (specProps.containsKey(spec)){
						counter = Integer.parseInt(specProps.getProperty(spec));
					}
					specProps.setProperty(spec, (++counter) + "");
				}
					
			}
		}
		//specProps.store(System.out, "");
		System.out.println("size of headerarray: " + oai.getOAIPMH().getListIdentifiers().sizeOfHeaderArray());
		if (listIdents.isSetResumptionToken()){
			if (listIdents.getResumptionToken().getStringValue().length() > 0){
				String resToken = listIdents.getResumptionToken().getStringValue();
				System.out.println("resumptionToken: " + resToken);
				String newOaiURL = "http://www.dipp.nrw.de/repository/oai?verb=ListIdentifiers&resumptionToken=" + resToken;
				parseOAI(newOaiURL);				
			}else{
				specProps.store(System.out, "");				
			}
		}else{
			specProps.store(System.out, "");
		}
	}
	
	public static void main(String[] args){
		String oaiURL = "http://www.dipp.nrw.de/repository/oai?verb=ListIdentifiers&metadataPrefix=oai_dc";
		try{
			TestOaiRecords t = new TestOaiRecords();
			t.parseOAI(oaiURL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
//http://www.dipp.nrw.de/repository/oai?verb=ListIdentifiers&metadataPrefix=oai_dc&set=ddc:670