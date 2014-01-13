/**
 * OaiSet.java - This file is part of the DiPP Project by hbz
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
package de.nrw.dipp.dippCore.repository.contentmodel;

import org.apache.log4j.Logger;

/**
 * Class OaiSet
 * 
 * <p><em>Title: MetaModel for OAI Set Objects</em></p>
 * <p>Description: actually Class is nothing more then a stub. 
 * Class stub is required for generation of oaiSetObject within ServiceManagement Class. 
 * Method persistNewDigitalObject requires a MetaModel defined for each 
 * DigitalObject</p>
 * 
 * @author Andres Quast, quast@hbz-nrw.de
 * creation date: 04.08.2009
 *
 */
public class OaiSet implements MetaModel {

	// Initiate Logger for OaiSetModel
	private static Logger log = Logger.getLogger(OaiSet.class);

	/* (non-Javadoc)
	 * @see de.nrw.dipp.dippCore.repository.contentmodel.MetaModel#getCModel()
	 */
	@Override
	public String getCModel() {
		// TODO Auto-generated method stub
		return "oaiSet";
	}
}
