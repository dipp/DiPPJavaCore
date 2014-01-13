package de.nrw.dipp.dippCore.repository;


import org.apache.log4j.Logger;

/**
 * <p>Title: FedoraAccess.java</p>
 * <p>Description: Class maps the API-A Requests to the right Fedora Adaptor 
 * (dippFedora* Implementation). It initializes an Instance of this Fedora Adaptor 
 * due to a case instruction.  </p>
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
 * @version $Id: FedoraAccess.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public final class FedoraAccess {

    // Get Logger for FedoraAccess
	private static Logger log = Logger.getLogger(FedoraAccess.class);


	private FedoraServicesAccess	mFedoraServices	= null;
	
	private static final class FedoraAccessHolder{
		static final FedoraAccess	fedoraAcc = new FedoraAccess();		
	}
	
	/**
	 * private Constructor for generating a new fedoraServices Instance
	 */
	private FedoraAccess(){
		init();
	}
	
	public static FedoraAccess getInstance(){
		return FedoraAccessHolder.fedoraAcc;
	}
	
	private void init(){
		try{
			Class fedoraServices = Class.forName(FedoraVersion.Factory.newInstance().getAdaptorAccessClassName());
			mFedoraServices = (FedoraServicesAccess)fedoraServices.newInstance();
			log.info(mFedoraServices.identify());
		}catch(Exception classExc){
		classExc.printStackTrace();
		}
	}
	
	public FedoraServicesAccess getFedoraServices(){		
		return mFedoraServices;
	}
}
