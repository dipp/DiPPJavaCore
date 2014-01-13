package de.nrw.dipp.dippCore.repository;

import org.apache.log4j.Logger;


/**
 * <p>Title: FedoraManagement.java</p>
 * <p>Description: A instance class for managing Fedora-API-M-Requests.</p>
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
 * @version $Id: FedoraManagement.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public final class FedoraManagement {
	

    // Get Logger for FedoraManagement
	private static Logger log = Logger.getLogger(FedoraManagement.class);

	private FedoraServicesManagement mFedoraServices = null;
	
	private static final class FedoraManagementHolder{
		static final FedoraManagement fedoraMng = new FedoraManagement();
	}
	private FedoraManagement(){
		init();
	}	
	
	public static FedoraManagement getInstance(){
		return FedoraManagementHolder.fedoraMng;
	}
	
	
	private void init(){
		try{
			Class fedoraServices = Class.forName(FedoraVersion.Factory.newInstance().getAdaptorManagementClassName());
			mFedoraServices = (FedoraServicesManagement)fedoraServices.newInstance();
			log.info(mFedoraServices.identify());
		}catch(Exception classExc){
			classExc.printStackTrace();
		}
	}
	
	public FedoraServicesManagement getFedoraServices(){		
		return mFedoraServices;
	}
	
	
	
}
