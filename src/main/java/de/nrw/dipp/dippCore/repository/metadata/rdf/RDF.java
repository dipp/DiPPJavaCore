package de.nrw.dipp.dippCore.repository.metadata.rdf;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.Fedora;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.FedoraCMA;
import de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary.OAI;

/**
 * <p>Title: RDF.java</p>
 * <p>Description: A class for managing RDF nodes.</p>
 * <p>new since DiPP2</p>
 * <p>this class and its methods are in early alpha status
 *  <ul>
 *  	<li>add 2006-04-06: public getSubjectURIs</li>
 *  	<li>add 2006-04-20: public toXMLObject</li>
 *  </ul>
 * </p>
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
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: RDF.java,v 1.2 2006/11/06 11:31:16 dippadm Exp $
 */
public class RDF {

	protected static final String cResourceNS = "info:fedora/";
	private Model mModel = ModelFactory.createDefaultModel();
	private Resource mSubject = null;
	private Map nsMap = new Hashtable();
	
	public RDF(String subjectID){
		mSubject = mModel.createResource(cResourceNS + subjectID);		
	}
	
	public RDF(InputStream aRDFstream){
		mModel.read(aRDFstream, "");		
	}
	
	/*
	 * add a statement to a model
	 */
	public void addStatement(Property predicate, String objectID){
		Resource object = mModel.createResource(cResourceNS + objectID);
		mModel.add(mSubject, predicate, object);
	}
	
	/*
	 * add a Property to a statement
	 */
	public void addProperty(Property predicate, String objectID){
		
		mModel.createResource(cResourceNS + objectID);
		mSubject.addProperty(predicate, objectID);
	}
	
	public void addNamespace(String aNS, String aURI){
		nsMap.put(aNS, aURI);
	}
	
	public void removeStatement(String subjectID, Property predicate, String objectID){
		Resource subject = mModel.getResource(cResourceNS + subjectID);
		Resource object = mModel.getResource(cResourceNS + objectID);
		mModel = mModel.removeAll(subject, predicate, object);
	}
	
	public void removeProperty(String subjectID, Property predicate){
		Resource subject = mModel.getResource(cResourceNS + subjectID);
		subject.removeAll(predicate);
	}
	
	public boolean contains(String subjectID, Property predicate, String objectID){
		Resource subject = mModel.createResource(cResourceNS + subjectID);
		Resource object = mModel.createResource(cResourceNS + objectID);
		return mModel.contains(subject, predicate, object);
	}
	
	public void write(OutputStream out){
		mModel.setNsPrefixes(nsMap);
		mModel.write(out);		
	}
	
	public void write(Writer writer){
		mModel.setNsPrefixes(nsMap);
		mModel.write(writer);		
	}
	
	
	public XmlObject toXMLObject(){
		XmlObject xml = null;
		nsMap.put("oai", OAI.getURI());
		nsMap.put("rel", Fedora.getURI());
		nsMap.put("fedora-model", FedoraCMA.getURI());
		StringWriter writer = new StringWriter();
		mModel.setNsPrefixes(nsMap);
		mModel.write(writer);
		try{
			xml = XmlObject.Factory.parse(writer.getBuffer().toString());
		}catch(XmlException xmlExc){
			xmlExc.printStackTrace();
		}
		return xml;
	}
	
	public void read(InputStream rdfStream){
		mModel.read(rdfStream, "");		
	}
	
/*	public void navigate(String predicateStr){
		Property predicate = mModel.createProperty(predicateStr);		
		NodeIterator objIt = mModel.listObjectsOfProperty(predicate);
		while (objIt.hasNext()){
			 System.out.println(((Resource)objIt.nextNode()).getURI());
		}
		
	}
*/	
	
	/**
	 * @param aPredicate
	 * @param aObject
	 * @return aList of subject URIs
	 */
	public List getSubjectURIs(Property aPredicate, String aObject){
		List subjects = new Vector();
		ResIterator resIt = mModel.listSubjectsWithProperty(aPredicate, aObject);
		while (resIt.hasNext()){
			Resource res = (Resource)resIt.nextResource();
			subjects.add(res.getURI());
		}
		return subjects;
	}

	
	
/*	public void test(){
		String resourceURI = "info:fedora/demo.11";
		String rootResourceURI = "info:fedora/demo.1";
		
		Model m = ModelFactory.createDefaultModel();
		m.createResource(fedoraNS);
		Resource subject = m.createResource(resourceURI);
		Resource object = m.createResource(rootResourceURI);
		Property predicate = m.createProperty(fedoraNS + "isMemberOfCollection");
		m.add(subject, predicate, object);
		m.setNsPrefix("fedora", fedoraNS);
		m.write(System.out);
	}
*/	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RDF rdf = new RDF("temp:2");
		rdf.addStatement(Fedora.cIsMemberOfColl, "demo:1") ;
		rdf.addProperty(OAI.itemID, "oai:dipp.nrw.de:temp:2");
		rdf.addNamespace("oai", OAI.getURI());
		rdf.addNamespace("rel", Fedora.getURI());
		rdf.write(System.out);
/*		testRDF rdf = new testRDF();
		rdf.addStatement("temp:2", testRDF.cIsMemberOfColl, "demo:1") ;
		rdf.addStatement("temp:2", testRDF.cIsDescriptionof, "temp:1") ;
		try{
			rdf.write(new FileOutputStream("c:\\temp\\rdf\\test.rdf"));
			rdf = new testRDF();
			rdf.read(new FileInputStream("c:\\temp\\rdf\\test.rdf"));
			rdf.navigate(testRDF.cIsMemberOfColl);
		}catch(IOException ioExc){
			ioExc.printStackTrace();
		}
*/		
	}

}
