/**
 * FedoraCMA.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.nrw.dipp.dippCore.repository.metadata.rdf.vocabulary;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Property;

/**
 * Class FedoraCMA
 * 
 * <p><em>Title: Jena RDF Model for fedora ContentModel Architecture</em></p>
 * <p>Description:  This class provides a Jena Model with all required Properties for the generation
 * of Fedora Content Model related RDF-Triples within RELS-EXT. Required since Fedora Version 3</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 02.04.2009
 *
 */
public class FedoraCMA {

	// Initiate Logger for FedoraCMA
	private static Logger log = Logger.getLogger(FedoraCMA.class);


	private static Model mModel = ModelFactory.createDefaultModel();
	private static final String NS = "info:fedora/fedora-system:def/model#";

	public static String getURI(){return NS;};
	public static final Resource NAMESPACE = mModel.createResource(NS);
	
	// new Properties required for Fedora Version 3 ContentModel implementation:
    //<fedora-model:hasModel rdf:resource="info:fedora/DiPP:eJournal" xmlns:fedora-model="info:fedora/fedora-system:def/model#"/>
    public static final Property setContentModel = mModel.createProperty(NS + "hasModel");
    
}
