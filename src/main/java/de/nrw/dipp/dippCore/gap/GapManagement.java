package de.nrw.dipp.dippCore.gap;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.xmlbeans.XmlException;
import org.openarchives.oai.x20.HeaderType;
import org.openarchives.oai.x20.ListRecordsType;
import org.openarchives.oai.x20.MetadataType;
import org.openarchives.oai.x20.OAIPMHDocument;
import org.openarchives.oai.x20.OAIPMHtype;
import org.openarchives.oai.x20.RecordType;
import org.openarchives.oai.x20.ResumptionTokenType;

import de.gapworks.oai.x20.oaiGap.GapDocument;
import de.gapworks.oai.x20.oaiGap.KeywordType;
import de.gapworks.oai.x20.oaiGap.SubjectType;
import de.gapworks.oai.x20.oaiGap.TitleType;
import de.nrw.dipp.dippCore.repository.ServiceManagement;
import de.nrw.dipp.dippCore.util.Constant;
import de.nrw.dipp.dippCore.webservice.Citation;
import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * <p>Title: GapManagement.java</p>
 * <p>Description: manages OAI-Export from Gap into the repository.</p>
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2004
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: GapManagement.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public class GapManagement extends TimerTask{

//	private static final String					cDownloadURLTest		= "http://www.gapdev.de/oai/oai2.php";
	private static final String					cDownloadURL			= "http://url.to.gap";
//	private static final String					cPublisherDiPP			= "DiPP NRW"; //"DIPP"; // //"GAP Verbund";// "DiPP NRW";
//	private static final String					cPublisherDiPPTest		= "DIPP";
	private static final String					cSubjectSchemeDNB		= "DNB functional groups";
	private static final String					cSubjectSchemeDDC		= "DDC Dewey-Decimal Classification";
	private static final String					cSubjectSchemeRights	= "Rechte / Lizenz";
	private static final String					cSubjectSchemeArticleType = "Stylesheet Konvertierung";
	private static final String					cGapCreatorRoleAuthor	= "author";
	private static final String[]				cTargetFormat			= {"html", "pdf"};							
    private static Properties 					s_pubtypeProps 			= new Properties();
    private static Hashtable 					s_dnb_ddcTable 			= new Hashtable();
    private static Properties 					sMappingProps			= new Properties();
    private static Properties 					sGapProps				= new Properties();
	private MultiThreadedHttpConnectionManager 	mThreadedConnMng 		= new MultiThreadedHttpConnectionManager();
	private Properties enLic = new Properties();
	private Properties deLic = new Properties();	
	/*	public GapManagement(boolean isTest){
		this();
		if (isTest){
			mGapURL = cDownloadURLTest;
		}
		
	}
*/	
	public GapManagement(){
		try{
			sGapProps.load(getClass().getClassLoader().getResourceAsStream("gap.properties"));
			System.out.println("____________________________________________________________");
			System.out.println("GapManagement");
			System.out.println("GAP-Container: " + sGapProps.getProperty("rootContainer"));
			System.out.println("GAP-URL: " + sGapProps.getProperty("url"));
			System.out.println("timestamp: " + sGapProps.getProperty("timestamp"));
			System.out.println("____________________________________________________________");
		}catch(IOException ioExc){
			ioExc.printStackTrace();			
		}

		enLic.setProperty("Digital Peer Publishing License", "DPPL");
		enLic.setProperty("free Digital Peer Publishing License", "fDPPL");
		enLic.setProperty("modular Digital Peer Publishing License", "mDPPL");

		deLic.setProperty("Digital Peer Publishing Lizenz", "DPPL");
		deLic.setProperty("freie Digital Peer Publishing Lizenz", "fDPPL");
		deLic.setProperty("modulare Digital Peer Publishing Lizenz", "mDPPL");
		
		GapFedoraMapping mapping = new GapFedoraMapping(getClass().getClassLoader().getResourceAsStream("mapping.xml"));
		sMappingProps = mapping.getMappingProperties();
		
		s_pubtypeProps.setProperty("Monograph", "monograph=Book");
		s_pubtypeProps.setProperty("Journal Article", "article");
		s_pubtypeProps.setProperty("Dissertation and Professional Dissertation", "dissertation");
		s_pubtypeProps.setProperty("Diploma Theses", "masterthesis");
		s_pubtypeProps.setProperty("Report", "report");
		s_pubtypeProps.setProperty("Paper", "paper");
	    s_pubtypeProps.setProperty("Conference Proceedings", "conf-proceeding");
		s_pubtypeProps.setProperty("Lecture", "lecture");
		s_pubtypeProps.setProperty("Music", "music");
		s_pubtypeProps.setProperty("Programs / Software", "program");
		s_pubtypeProps.setProperty("Plays", "play");
		s_pubtypeProps.setProperty("News", "news");
		s_pubtypeProps.setProperty("Standards", "standards");
		
		List ddcList = null;
		ddcList = new Vector();
		ddcList.add("630");	
		s_dnb_ddcTable.put("Agriculture, Gardening", ddcList);
		ddcList = new Vector();
		ddcList.add("930");
		s_dnb_ddcTable.put("Archaeology, Pre-History", ddcList);
		ddcList = new Vector();
		ddcList.add("720");		
		s_dnb_ddcTable.put("Architecture", ddcList);
		ddcList = new Vector();
		ddcList.add("910");		
		s_dnb_ddcTable.put("Atlases", ddcList);
		ddcList = new Vector();
		ddcList.add("800");
		s_dnb_ddcTable.put("Belles-Lettres", ddcList);
		ddcList = new Vector();
		ddcList.add("570");
		ddcList.add("580");
		ddcList.add("590");
		s_dnb_ddcTable.put("Biology", ddcList);
		ddcList = new Vector();
		ddcList.add("020");
		ddcList.add("060");
		ddcList.add("070");
		ddcList.add("090");
		s_dnb_ddcTable.put("Books and Libraries, Information and Documentation", ddcList);
		s_dnb_ddcTable.put("Calendars", new Vector());
		ddcList = new Vector();
		ddcList.add("660");
		ddcList.add("670");
		ddcList.add("770");
		s_dnb_ddcTable.put("Chemical Technology, Food Technology Textile Technology", ddcList);
		ddcList = new Vector();
		ddcList.add("540");		
		s_dnb_ddcTable.put("Chemistry", ddcList);
		s_dnb_ddcTable.put("Children's and Youth Literature", new Vector());
		ddcList = new Vector();
		ddcList.add("200");
		ddcList.add("220");
		ddcList.add("230");
		s_dnb_ddcTable.put("Christianity", ddcList);
		ddcList = new Vector();
		ddcList.add("470");
		ddcList.add("480");
		ddcList.add("870");
		ddcList.add("880");
		s_dnb_ddcTable.put("Classical Languages and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("741");
		s_dnb_ddcTable.put("Comics, Cartoons, Caricatures Miscellanea", ddcList);
		ddcList = new Vector();
		ddcList.add("380");
		ddcList.add("620");
		ddcList.add("760");
		s_dnb_ddcTable.put("Communications, Transport", ddcList);
		ddcList = new Vector();
		ddcList.add("004");		
		s_dnb_ddcTable.put("Computer Science, Data Processing", ddcList);
		ddcList = new Vector();
		ddcList.add("010");
		ddcList.add("910");
		s_dnb_ddcTable.put("Directories and Phone Books", ddcList);
		ddcList = new Vector();
		ddcList.add("640");		
		s_dnb_ddcTable.put("Domestic Science, Cooking, Hotel and Restaurant Management", ddcList);
		ddcList = new Vector();
		ddcList.add("710");
		ddcList.add("330");
		ddcList.add("360");
		s_dnb_ddcTable.put("Ecology, Area Planning, Landscape Architecture", ddcList);
		ddcList = new Vector();
		ddcList.add("330");		
		s_dnb_ddcTable.put("Economic History", ddcList);
		ddcList = new Vector();
		ddcList.add("330");
		ddcList.add("650");
		ddcList.add("670");
		s_dnb_ddcTable.put("Economics", ddcList);
		ddcList = new Vector();
		ddcList.add("620");		
		s_dnb_ddcTable.put("Electrical Engineering", ddcList);
		ddcList = new Vector();
		ddcList.add("420");
		ddcList.add("810");
		ddcList.add("820");
		s_dnb_ddcTable.put("English Language and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("130");		
		s_dnb_ddcTable.put("Esoterica Manuscripts, Book Art", ddcList);
		ddcList = new Vector();
		ddcList.add("390");
		ddcList.add("300");
		s_dnb_ddcTable.put("Folklore, Ethnology", ddcList);
		ddcList = new Vector();
		ddcList.add("400");
		ddcList.add("800");
		s_dnb_ddcTable.put("General and Comparative Linguistics and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("200");
		ddcList.add("220");
		ddcList.add("290");
		s_dnb_ddcTable.put("General and Comparative Theology, Non-Christian Religion", ddcList);
		ddcList = new Vector();
		ddcList.add("910");
		ddcList.add("914");
		s_dnb_ddcTable.put("Geography, Local and Regional Geography, Travel", ddcList);
		ddcList = new Vector();
		ddcList.add("550");
		ddcList.add("560");
		s_dnb_ddcTable.put("Geology", ddcList);
		ddcList = new Vector();
		ddcList.add("430");
		ddcList.add("830");
		s_dnb_ddcTable.put("German Language and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("900");
		ddcList.add("920");
		ddcList.add("930");
		ddcList.add("940");
		ddcList.add("950");
		ddcList.add("960");
		ddcList.add("970");
		ddcList.add("980");
		ddcList.add("990");
		s_dnb_ddcTable.put("History and Historical Sciences", ddcList);
		ddcList = new Vector();
		ddcList.add("300");
		ddcList.add("900");
		ddcList.add("920");
		ddcList.add("930");
		ddcList.add("940");
		ddcList.add("950");
		ddcList.add("960");
		ddcList.add("970");
		ddcList.add("980");
		ddcList.add("990");
		s_dnb_ddcTable.put("History of Civilization", ddcList);
		ddcList = new Vector();
		ddcList.add("640");		
		s_dnb_ddcTable.put("Hobbies, Handicrafts, Home Crafts", ddcList);
		ddcList = new Vector();
		ddcList.add("070");
		ddcList.add("791");
		s_dnb_ddcTable.put("Journalism", ddcList);
		ddcList = new Vector();
		ddcList.add("000");
		ddcList.add("030");
		ddcList.add("050");
		ddcList.add("060");
		ddcList.add("080");
		s_dnb_ddcTable.put("Knowledge and Culture in General", ddcList);
		ddcList = new Vector();
		ddcList.add("340");
		ddcList.add("360");
		s_dnb_ddcTable.put("Law", ddcList);
		ddcList = new Vector();
		ddcList.add("439");
		ddcList.add("830");
		s_dnb_ddcTable.put("Linguistics and Literature of other Germanic Languages", ddcList);
		ddcList = new Vector();
		ddcList.add("490");
		ddcList.add("890");
		s_dnb_ddcTable.put("Linguistics and Literature of other Languages", ddcList);
		ddcList = new Vector();
		ddcList.add("510");		
		s_dnb_ddcTable.put("Mathematics", ddcList);
		ddcList = new Vector();
		ddcList.add("610");		
		s_dnb_ddcTable.put("Medicine", ddcList);
		ddcList = new Vector();
		ddcList.add("355");		
		s_dnb_ddcTable.put("Military Science", ddcList);
		ddcList = new Vector();
		ddcList.add("620");
		ddcList.add("690");
		s_dnb_ddcTable.put("Mining, Civil Engineering, Environmental Technology", ddcList);
		ddcList = new Vector();
		ddcList.add("780");		
		s_dnb_ddcTable.put("Music", ddcList);
		ddcList = new Vector();
		ddcList.add("500");		
		s_dnb_ddcTable.put("Nature, Natural Sciences in General", ddcList);
		ddcList = new Vector();
		ddcList.add("370");
		s_dnb_ddcTable.put("Pedagogics, Education, Teaching", ddcList);
		ddcList = new Vector();
		ddcList.add("100");		
		s_dnb_ddcTable.put("Philosophy", ddcList);
		ddcList = new Vector();
		ddcList.add("770");
		s_dnb_ddcTable.put("Photography", ddcList);
		ddcList = new Vector();
		ddcList.add("530");
		ddcList.add("520");
		s_dnb_ddcTable.put("Physics, Astronomy", ddcList);
		ddcList = new Vector();
		ddcList.add("320");		
		s_dnb_ddcTable.put("Political Science", ddcList);
		ddcList = new Vector();
		ddcList.add("620");
		ddcList.add("670");
		s_dnb_ddcTable.put("Power, Mechanical and Manufacture Engineering", ddcList);
		ddcList = new Vector();
		ddcList.add("150");		
		s_dnb_ddcTable.put("Psychology", ddcList);
		ddcList = new Vector();
		ddcList.add("350");		
		s_dnb_ddcTable.put("Public Administration", ddcList);
		ddcList = new Vector();
		ddcList.add("010");
		ddcList.add("030");
		s_dnb_ddcTable.put("Reference Books, Bibliographies", ddcList);
		ddcList = new Vector();
		ddcList.add("440");
		ddcList.add("450");
		ddcList.add("460");
		ddcList.add("840");
		ddcList.add("850");
		ddcList.add("860");
		s_dnb_ddcTable.put("Romance Languages and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("490");
		ddcList.add("890");
		s_dnb_ddcTable.put("Slavic and Baltic Languages and Literature", ddcList);
		ddcList = new Vector();
		ddcList.add("300");
		ddcList.add("360");
		s_dnb_ddcTable.put("Sociology, Sociography", ddcList);
		ddcList = new Vector();
		ddcList.add("796");
		ddcList.add("793");
		s_dnb_ddcTable.put("Sports, Games", ddcList);
		ddcList = new Vector();
		ddcList.add("310");		
		s_dnb_ddcTable.put("Statistics", ddcList);
		ddcList = new Vector();
		ddcList.add("600");
		ddcList.add("620");
		s_dnb_ddcTable.put("Technical in General", ddcList);
		ddcList = new Vector();
		ddcList.add("S");
		s_dnb_ddcTable.put("Textbooks", ddcList);
		ddcList = new Vector();
		ddcList.add("792");
		ddcList.add("791");
		s_dnb_ddcTable.put("Theatre, Dance, Film", ddcList);
		ddcList = new Vector();
		ddcList.add("630");		
		s_dnb_ddcTable.put("Veterinary Medicine", ddcList);
		ddcList = new Vector();
		ddcList.add("730");		
		s_dnb_ddcTable.put("Visual Arts", ddcList);
		s_dnb_ddcTable.put("Vocational School Textbooks", new Vector());
		ddcList = new Vector();
		ddcList.add("330");
		ddcList.add("300");
		ddcList.add("350");
		s_dnb_ddcTable.put("Work", ddcList);

	}
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		try{
			getOAI();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getOAI()throws IOException{
		int statusCode = -1;
		int cursor = 0;
		
		String paramListRecord 		= "verb=ListRecords";
		String paramMetadataPrefix	= "metadataPrefix=oai_gap";
		String paramResumptionToken = "resumptionToken";
		BigInteger completeListSize	= null;
		BigInteger cursorValue		= null;
		String urlStr				= "";		
		String resumptionTokenValue	= null;
		GetMethod getMethod 		= null;
		Date 		responseDate	= null;
// http://www.gapdev.de/oai/oai2.php?verb=ListRecords&metadataPrefix=oai_dc&from=2004-12-13T00:48:07Z
		try{
			do{
				

				if (cursor == 0){
					urlStr = sGapProps.getProperty("url", cDownloadURL) + "?" + paramListRecord + "&" + paramMetadataPrefix + "&from=" + sGapProps.getProperty("timestamp", "2004-12-13T00:00:00Z") + "&x_set=" + sGapProps.getProperty("login") + "&x_password=" + sGapProps.getProperty("password");
					resumptionTokenValue	= "";
					System.out.println(urlStr);
				}else{
					urlStr = sGapProps.getProperty("url", cDownloadURL) + "?" + paramListRecord + "&" + paramResumptionToken + "=" + resumptionTokenValue + "&x_set=" + sGapProps.getProperty("login") + "&x_password=" + sGapProps.getProperty("password");
					resumptionTokenValue	= "";
				}
				
				HttpClient client = new HttpClient(mThreadedConnMng);
				getMethod = new GetMethod(urlStr);
				statusCode = client.executeMethod(getMethod);
				if (statusCode!=200) {
					throw new IOException(HttpStatus.getStatusText(statusCode)
							+ ": " 
							+ getMethod.getResponseBodyAsString());
				}

				/*
				 * TODO hier mögliche Parsing-Fehler abfangen, loggen und für toDo-AdminTool bereitstellen
				 */
				OAIPMHDocument oaiDoc = OAIPMHDocument.Factory.parse(getMethod.getResponseBodyAsStream());

				getMethod.releaseConnection();

				OAIPMHtype oai = oaiDoc.getOAIPMH();
				TimeZone tz = TimeZone.getTimeZone("Europe/London");
				TimeZone.setDefault(tz);
				responseDate = oai.getResponseDate().getTime();
				
				if (oai.getListRecords() == null){
					System.out.println("listRecord is null");
					System.out.println(oaiDoc.toString());
					return;
				}
				if (oai.getListRecords().isSetResumptionToken()){
					ResumptionTokenType resumptionToken = oai.getListRecords().getResumptionToken();
					resumptionTokenValue = resumptionToken.getStringValue();
					completeListSize 	= resumptionToken.getCompleteListSize();
					cursorValue			= resumptionToken.getCursor();
					System.out.println("resumption: " + resumptionTokenValue + " " + completeListSize + " " + cursorValue);
				}
				cursor++;
				process(oai);
//				------------------------
				ListRecordsType listRecords = oai.getListRecords();
				for ( int i = 0; i < listRecords.getRecordArray().length; i++){
					RecordType record =  listRecords.getRecordArray(i);
//					HeaderType header = record.getHeader();
					MetadataType metadata = record.getMetadata();
					GapDocument gapDoc = GapDocument.Factory.parse(metadata.xmlText());
					GapDocument.Gap gap = gapDoc.getGap();
					GapDocument.Gap.Publication publication = gap.getPublication();
					if (!(publication.getPublisher().toString().equals(sGapProps.getProperty("rootContainer")) )){
						continue;
					}else{
						
					}
					if (publication.isSetSource()){
						GapDocument.Gap.Publication.Source src = publication.getSource();
						if (src.isSetInjournal()){
							if (src.getInjournal().isSetJournalissn()){
								System.out.println("J: " + src.getInjournal().getJournalissn().toString());
							}
							if (src.getInjournal().isSetJournaltitle()){
								System.out.println("T: " + src.getInjournal().getJournaltitle().toString());
							}
							if (src.getInjournal().isSetJournalyear()){
								System.out.println("V: " + src.getInjournal().getJournalyear().toString());
							}
						}
					}
				}
//				-----------------------
				
//				oaiDoc.save(new File("c:\\temp\\oai\\records_" + cursor + ".xml"));
			}while(resumptionTokenValue.length() != 0);
			//2004-12-13T00:00:00Z			
			if (responseDate != null){
				sGapProps.setProperty("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(responseDate));
				FileOutputStream outStream = new FileOutputStream(getClass().getClassLoader().getResource("gap.properties").getFile());
				sGapProps.store(outStream, null);
				outStream.close();
				System.out.println("GapProperties updated to timestamp: " + sGapProps.getProperty("timestamp"));
			}else{
				System.out.println("GapProperties not updated");
			}
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		
	}
	
	private void process(OAIPMHtype oai)throws IOException, XmlException{
		
		try{
			ListRecordsType listRecords = oai.getListRecords();
			System.out.println("Length: " + listRecords.getRecordArray().length);
			loop1: for ( int i = 0; i < listRecords.getRecordArray().length; i++){
				RecordType record =  listRecords.getRecordArray(i);
				HeaderType header = record.getHeader();
				MetadataType metadata = record.getMetadata();
				GapDocument gapDoc = GapDocument.Factory.parse(metadata.xmlText());
				GapDocument.Gap gap = gapDoc.getGap();
				GapDocument.Gap.Publication publication = gap.getPublication();
				if (! (publication.getPublisher().toString().equals(sGapProps.getProperty("rootContainer")))){
					continue loop1;
				}
				QualifiedDublinCore qualifiedDC = mapMetadata(gap, header);
				ServiceManagement fi = new ServiceManagement();
				if (fi.existsArticle(header.getIdentifier())){
					System.out.println("already exists: " + header.getIdentifier());
					continue loop1;
				}
				
				String journalTitle = qualifiedDC.getBibliographicCitation()[0].getJournalTitle();
				Enumeration keys = sMappingProps.keys();
				while (keys.hasMoreElements()){
					System.out.println((String)keys.nextElement());
					
				}

				if (!sMappingProps.containsKey(journalTitle)){
					throw new Exception("There is no such journal named '" + journalTitle + "' in the repository");
				}

				String pidOfJournal = fi.getPidOfJournal(sMappingProps.getProperty(journalTitle));
				
				fi = new ServiceManagement();
				String[] container = new String[1];
				container[0] = pidOfJournal;

				fi.createNewArticle(container, pidOfJournal, qualifiedDC, qualifiedDC.getIdentifierURL() + "&oai_set=" + sGapProps.getProperty("login") + "&oai_password=" + sGapProps.getProperty("password"), Constant.cStorageTypePermanent, cTargetFormat, Constant.cDefaultFilename);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	protected QualifiedDublinCore mapMetadata(GapDocument.Gap aGap, HeaderType aHeader){
		QualifiedDublinCore metadata = new QualifiedDublinCore();
		
		String[] identifier = new String[1];
		identifier[0] = aHeader.getIdentifier();
		metadata.setSource(identifier);
		System.out.println("GapMangement: " + metadata.getSource(0));
		Calendar cal = Calendar.getInstance();
		cal.setTime(aHeader.getDatestamp().getTime());
		metadata.setDateSubmitted(cal);
		GapDocument.Gap.Publication publication = aGap.getPublication();
		
		if (publication.isSetSource()){
			GapDocument.Gap.Publication.Source src = publication.getSource();
			if (src.isSetInjournal()){
				if (src.getInjournal().isSetJournalissn()){
					metadata.setIdentifierISSN(src.getInjournal().getJournalissn().toString());
				}
				if (src.getInjournal().isSetJournaltitle()){
					Citation[] citation = new Citation[1];
					citation[0] = new Citation();
					citation[0].setJournalTitle(src.getInjournal().getJournaltitle().toString());
					if (src.isSetInseries()){
						if (src.getInseries().isSetSeriestitle()){
							citation[0].setJournalVolume(src.getInseries().getSeriestitle().toString());
						}
					}
					if (src.isSetInissue()){
						if (src.getInissue().isSetIssuetitle()){
							citation[0].setJournalIssueNumber(src.getInissue().getIssuetitle().toString());
						}
					}
					metadata.setBibliographicCitation(citation);
				}
/*				if (src.getInjournal().isSetJournalyear()){
					metadata.setCitationJournalVolume(src.getInjournal().getJournalyear().toString());
				}
*/				
			}
		}
		List persons = new Vector();
		List contributors = new Vector();
		for (int j = 0; j < aGap.getCreatorArray().length; j++){
			GapDocument.Gap.Creator creator = aGap.getCreatorArray(j);
			if (creator.isSetCreatorrole()){
				if (creator.getCreatorrole().equals(cGapCreatorRoleAuthor)){
					de.nrw.dipp.dippCore.webservice.CreatorPerson metadataCreator = new de.nrw.dipp.dippCore.webservice.CreatorPerson();
					persons.add(metadataCreator);
					if (creator.isSetCreatoremail()){
						metadataCreator.setEmailAddress(creator.getCreatoremail().toString());
					}
					if (creator.isSetCreatornfamily()){
						metadataCreator.setLastName(creator.getCreatornfamily().toString());
					}
					if (creator.isSetCreatorngiven()){
						metadataCreator.setFirstName(creator.getCreatorngiven().toString());
					}
					if (creator.isSetCreatororgname()){
						metadataCreator.setOrganization(creator.getCreatororgname().toString());
					}				
				}else{
					de.nrw.dipp.dippCore.webservice.Contributor metadataContributor = new de.nrw.dipp.dippCore.webservice.Contributor();
					contributors.add(metadataContributor);
					if (creator.isSetCreatoremail()){
						metadataContributor.setEmailAddress(creator.getCreatoremail().toString());
					}
					if (creator.isSetCreatornfamily()){
						metadataContributor.setLastName(creator.getCreatornfamily().toString());
					}
					if (creator.isSetCreatorngiven()){
						metadataContributor.setFirstName(creator.getCreatorngiven().toString());
					}
					if (creator.isSetCreatororgname()){
						metadataContributor.setOrganization(creator.getCreatororgname().toString());
					}				
					if (creator.isSetCreatorrole()){
						metadataContributor.setRole(creator.getCreatorrole().toString());
					}				
				}
			}else{
				de.nrw.dipp.dippCore.webservice.CreatorPerson metadataCreator = new de.nrw.dipp.dippCore.webservice.CreatorPerson();
				persons.add(metadataCreator);
				if (creator.isSetCreatoremail()){
					metadataCreator.setEmailAddress(creator.getCreatoremail().toString());
				}
				if (creator.isSetCreatornfamily()){
					metadataCreator.setLastName(creator.getCreatornfamily().toString());
				}
				if (creator.isSetCreatorngiven()){
					metadataCreator.setFirstName(creator.getCreatorngiven().toString());
				}
				if (creator.isSetCreatororgname()){
					metadataCreator.setOrganization(creator.getCreatororgname().toString());
				}				
			}
		}
		metadata.setCreatorPerson((de.nrw.dipp.dippCore.webservice.CreatorPerson[])persons.toArray(new de.nrw.dipp.dippCore.webservice.CreatorPerson[0]));
		metadata.setContributor((de.nrw.dipp.dippCore.webservice.Contributor[])contributors.toArray(new de.nrw.dipp.dippCore.webservice.Contributor[0]));
		
		Element[] dcAbstracts = new Element[aGap.getDescriptionArray().length];
		for ( int j = 0; j < aGap.getDescriptionArray().length; j++){
			TitleType title = aGap.getDescriptionArray(j);
			dcAbstracts[j] = new Element();
			dcAbstracts[j].setValue(title.getStringValue());
			dcAbstracts[j].setLang(title.getLang().toString());
			metadata.setDCTermsAbstract(dcAbstracts);
		}
		String[] format = new String[1];
		format[0] = aGap.getFormat();
		metadata.setFormat(format);
		metadata.setIdentifierURL(aGap.getIdentifier());
		String[] lang = new String[1];
		lang[0] = aGap.getLanguage();
		metadata.setLanguage(lang);
		
		String[] pubType = new String[1];
		pubType[0] = s_pubtypeProps.getProperty(aGap.getPubtype());
		metadata.setPubType(pubType);
		
		String[] docType = new String[1];
		docType[0] = aGap.getType().toLowerCase();
		metadata.setDocType(docType);
		
		List listSubject = Arrays.asList(aGap.getSubjectArray());
		Iterator itSubject = listSubject.iterator();
		List ddcList = new Vector();
		while (itSubject.hasNext()){
			SubjectType subject = (SubjectType)itSubject.next();
/*			if (subject.getScheme().equals(cSubjectSchemeDNB) ){
				if (s_dnb_ddcTable.containsKey(subject.getStringValue())){
					List ddcList = (List)s_dnb_ddcTable.get(subject.getStringValue());
					metadata.setDDC((String[])ddcList.toArray(new String[0]));
				}
*/				
			if (subject.getScheme().equals(cSubjectSchemeRights)){
				String[] rights = new String[1]; 
				rights[0] = subject.getStringValue();
				int lastIndex = rights[0].indexOf(",");
				if (lastIndex == -1){
					lastIndex = rights[0].indexOf(" ");
				}
				if (lastIndex != -1){
					rights[0] = rights[0].substring(0, lastIndex);
					metadata.setRights(rights);
				}
			}else if (subject.getScheme().equals(cSubjectSchemeArticleType)){
				String[] articleType = new String[1];
				articleType[0] = subject.getStringValue();
				metadata.setArticleType(articleType);
			}else if (subject.getScheme().equals(cSubjectSchemeDDC)){
				String ddc = subject.getStringValue();
				int lastIndex = ddc.indexOf(",");
				if (lastIndex == -1){
					lastIndex = ddc.indexOf(" ");
				}
				if (lastIndex != -1){
					ddcList.add(ddc.substring(0, lastIndex));					
				}
//				List ddcList = (List)s_dnb_ddcTable.get(subject.getStringValue());
//				metadata.setDDC((String[])ddcList.toArray(new String[0]));
				
			}else{
				String[] subjects = new String[1];
				subjects[0] = subject.getStringValue();
				metadata.setSubject(subjects);
			}			
		}
		metadata.setDDC((String[])ddcList.toArray(new String[0]));
		
		List listKeywords = Arrays.asList(aGap.getKeywordArray());
		Iterator itKeyword = listKeywords.iterator();
		List subjects = new Vector(listKeywords.size());
		
		while (itKeyword.hasNext()){
			KeywordType keyword = (KeywordType)itKeyword.next();
			subjects.add(keyword.getStringValue());
			metadata.setSubject((String[])subjects.toArray(new String[0]));
		}
		
		
		Element[] altTitles = new Element[aGap.getSubtitleArray().length]; 
		for ( int j = 0; j < aGap.getSubtitleArray().length; j++){
			TitleType subTitleType = aGap.getSubtitleArray(j);
			altTitles[j] = new Element();
			altTitles[j].setValue(subTitleType.getStringValue());
			altTitles[j].setLang(subTitleType.getLang().toString());			
		}
		metadata.setAlternative(altTitles);
		
		Element[] titles = new Element[aGap.getTitleArray().length]; 
		for ( int j = 0; j < aGap.getTitleArray().length; j++){
			TitleType titleType = aGap.getTitleArray(j);
			titles[j] = new Element();
			titles[j].setValue(titleType.getStringValue());
			titles[j].setLang(titleType.getLang().toString());
		}
		metadata.setTitle(titles);
		cal = Calendar.getInstance();
		cal.setTime(aGap.getDatesubmitted().getTime());
		metadata.setDateSubmitted(cal);
		return metadata;
	}
	
	public void readRecords()throws Exception{
		BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream("c:\\temp\\oai\\records.xml"));
		byte[] buf = new byte[8096];
		while (bufIn.read(buf) != -1){
			System.out.print(new String(buf));
		}
	}
	
	
	public static void main(String[] args){
//		System.out.println(GapManagement.class.getResource("gap.properties").getFile());		
		String oaiStr = "<OAI-PMH xmlns=\"http://www.openarchives.org/OAI/2.0/\">" +
		  				"<responseDate>2004-12-20T13:09:13Z</responseDate>" +
						"</OAI-PMH>";
		try{
			OAIPMHDocument oai = OAIPMHDocument.Factory.parse(oaiStr);
//			OAIPMHtype oaiType = oai.addNewOAIPMH();
//			oaiType.setResponseDate(Calendar.getInstance());
			System.out.println(TimeZone.getDefault().getID());
			TimeZone tz = TimeZone.getTimeZone("Europe/London");
			TimeZone.setDefault(tz);
//			oai.getOAIPMH().getResponseDate().setTimeZone(new TimeZone())
			Calendar cal = oai.getOAIPMH().getResponseDate();
//			cal.setTimeZone(tz);
			System.out.println(oai.toString() + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
	}

}
