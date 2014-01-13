package de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary;

import com.hp.hpl.jena.rdf.model.*;

/**
 * @author Jochen Schirrwagen, auto generated stub
 * <p>Title: This class provides a model with all required Properties for the generation
 * of OAI related RDF-Triples within RELS-EXT</p>
 * <p>The OAI relations for example are required for acquire each object to its correct OAI-Sets</p>   
 *
 */
public class OAI {

	private static Model mModel = ModelFactory.createDefaultModel();
	private static final String NS = "http://www.openarchives.org/OAI/2.0/";
	
	
	public static String getURI(){return NS;};
	public static final Resource NAMESPACE = mModel.createResource(NS);
	
	public static final Property itemID = mModel.createProperty("http://www.openarchives.org/OAI/2.0/itemID");
	public static final Property setSpec = mModel.createProperty("http://www.openarchives.org/OAI/2.0/setSpec");
	public static final Property setName = mModel.createProperty("http://www.openarchives.org/OAI/2.0/setName");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO no function; Auto-generated method stub

	}

}
