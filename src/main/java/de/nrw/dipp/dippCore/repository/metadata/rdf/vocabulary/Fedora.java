package de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Jochen Schirrwagen, auto generated stub
 * <p>Title: This class provides a model with all required Properties for the generation
 * of fedora/dipp related RDF-Triples within RELS-EXT</p>   
 *
 */
public class Fedora {


	private static Model mModel = ModelFactory.createDefaultModel();
	private static final String NS = "info:fedora/fedora-system:def/relations-external#";
	
	
	public static String getURI(){return NS;};
	public static final Resource NAMESPACE = mModel.createResource(NS);
	
    public static final Property cIsMemberOfColl 	= mModel.createProperty(NS + "isMemberOfCollection");
    public static final Property cIsMemberof		= mModel.createProperty(NS + "isMemberOf");
    public static final Property cIsSubsetof		= mModel.createProperty(NS + "isSubsetOf");
    public static final Property cIsConstituentof	= mModel.createProperty(NS + "isConstituentOf");
    public static final Property cIsPartof			= mModel.createProperty(NS + "isPartOf");
    public static final Property cIsDescriptionof	= mModel.createProperty(NS + "isDescriptionOf");
    public static final Property cIsMetadatafor		= mModel.createProperty(NS + "isMetadataFor");
    public static final Property cIsAnnotationof	= mModel.createProperty(NS + "isAnnotationOf");
    public static final Property cIsDependentof		= mModel.createProperty(NS + "isDependentOf");
    public static final Property cIsDerivationof	= mModel.createProperty(NS + "isDerivationOf");
    public static final Property cHasCollectionMember	= mModel.createProperty(NS + "hasCollectionMember");
    public static final Property cHasSubset			= mModel.createProperty(NS + "hasSubset");
    public static final Property cHasMember			= mModel.createProperty(NS + "hasMember");
    public static final Property cHasConstituent	= mModel.createProperty(NS + "hasConstituent"); // Bestandteil, Komponente
    public static final Property cHasPart			= mModel.createProperty(NS + "hasPart");
    public static final Property cHasDerivation		= mModel.createProperty(NS + "hasDerivation");
    public static final Property cHasDependent		= mModel.createProperty(NS + "hasDependent");
    public static final Property cHasAnnotation		= mModel.createProperty(NS + "hasAnnotation");
    public static final Property cHasMetadata		= mModel.createProperty(NS + "hasMetadata");
    public static final Property cHasDescription	= mModel.createProperty(NS + "hasDescription");
    public static final Property cHasEquivalent		= mModel.createProperty(NS + "hasEquivalent");
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO no function; Auto-generated method stub

	}

}
