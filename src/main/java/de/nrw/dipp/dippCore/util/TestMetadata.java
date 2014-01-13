package de.nrw.dipp.dippCore.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;

import de.nrw.dipp.dippCore.repository.DOManagement;
import de.nrw.dipp.dippCore.repository.DigitalObject;
import de.nrw.dipp.dippCore.repository.FedoraAccess;

/**
 * @author aquast
 *         <p>
 *         Class provides a testing utility for the methods of Metadata.class.
 *         Writes to stout the xml-output of the different methods that
 *         generates xml-fragments
 *         </p>
 *         <p>
 *         requires an MetadataPrefix and a textfile similar to the
 *         OAIManagementImpl.class
 *         </p>
 * 
 */
public class TestMetadata {

	// command line arguments
	private CommandLine cmd = null;
	private static Options options = null; // cmd line options

	private static final String cCmdOptionMetaDataFormat = "m";
	private static final String cCmdOptionLongMetaDataFormat = "metadataFormat";
	private static final String cCmdOptionFilename = "f";
	private static final String cCmdOptionLongFilename = "file";

	static {
		options = new Options();
		options.addOption(cCmdOptionMetaDataFormat,
				cCmdOptionLongMetaDataFormat, true,
				"DS Prefixes: oai_dc|oai_epicur|DoajXml");
		options.addOption(cCmdOptionFilename, cCmdOptionLongFilename, true,
				"filename to write in or read from");

	}

	private String dsPrefix = null;
	private List<String[]> items = null;

	// Get Logger for this Class
	private Logger log = Logger.getLogger(TestMetadata.class);

	public void loadArgs(String[] args) {
		CommandLineParser parser = new PosixParser();
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			log.error(e);
			System.err.println("Error while parsing arguments");
			System.exit(1);
		}
		if (!cmd.hasOption(cCmdOptionMetaDataFormat)
				| !cmd.hasOption(cCmdOptionFilename)) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("TestMetadata ", options);
			System.exit(1);
		}

		String filename = cmd.getOptionValue(cCmdOptionFilename);
		// FIXME: Qa: HotFix: exit program if a tilde or . is found within file
		// name
		if (filename.charAt(0) == '~' || filename.charAt(0) == '.') {
			log
					.error("Program exits due to wrong file name pattern!\n"
							+ "Please use either a filename solely or give an absolute path\n"
							+ "filename is: " + filename);
			System.exit(1);
		}

		dsPrefix = cmd.getOptionValue(cCmdOptionMetaDataFormat);
		log.info(dsPrefix);
		log.info(filename);
		items = generateList(filename);
		
		generateFragments(items, dsPrefix);

	}

	public List<String[]> generateList(String filename) {
		List<String[]> items = new ArrayList<String[]>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith("#")) { // is needed due to implementation of Datestamps
					String[] item = line.split("\\s"); // array of pid and label and any further informations
					items.add(item);
				}
			}
			reader.close();
		} catch (IOException e) {
			log.error(e);
		}
		return items;
	}

	public void generateFragments(List<String[]> items, String dsPrefix){
		List<DigitalObject> digObjs = null; 
		log.info("PIDS found: " + items.size());
		log.info("using Fedora version constant: "+ Constant.cFedoraVersion);
		
		DOManagement domng = new DOManagement();
		log.info("starting Fedora Request");
		

		try{
			log.info(Constant.cConfiguration);
			DigitalObject digObjTest = domng.retrieveDigitalObject("dipp:1132");
//			digObjs = DOManagement.retrieveDigitalObjects(conditions);
		}
		catch(Exception e){
			log.error(e);
		}

		for(int i = 0; i < items.size(); i++){
			String[] item = items.get(i);
			FedoraAccess.getInstance();
			MetadataDocument testfragment = null;
			try{
				log.info(item[0]);
				/*byte[] stream = FedoraAccess.getInstance().getFedoraServices().getDataStreamContentAsBytes(item[0], "QDC");
				MetadataDocument qdcDoc = MetadataDocument.Factory.parse(new String(stream, "UTF-8"));
				MetadataDocument.Metadata qdc = qdcDoc.getMetadata();
				if (dsPrefix.equals("oai_dc")){
					byte[] test = Metadata.getDCXML(qdc);
		            testfragment = MetadataDocument.Factory.parse(new String(test, "UTF-8"));
				}
				if (dsPrefix.equals("oai_epicur")){
					byte[] test = Metadata.getEpicurXML(qdc, URNManagement.cEpicurUpdateStatus_URN_NEW);
		            testfragment = MetadataDocument.Factory.parse(new String(test, "UTF-8"));
				}
				if (dsPrefix.equals("DoajXml")){
					byte[] test = Metadata.getDOAJXML(qdc);
		            testfragment = MetadataDocument.Factory.parse(new String(test, "UTF-8"));
				}
				log.info("Ergebnis-Fragment" + testfragment);
				*/
				DigitalObject digObj = DOManagement.retrieveDigitalObject(item[0]);
				digObjs.add(digObj);
			}
			catch(Exception e){
				log.error(e);
			}
		}
	}
	
	public static void main(String[] args) {

		TestMetadata tmd = new TestMetadata();
		tmd.loadArgs(args);
	}
}
