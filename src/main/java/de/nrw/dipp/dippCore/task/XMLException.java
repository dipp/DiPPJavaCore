package de.nrw.dipp.dippCore.task;

import java.io.IOException;
/**
 * <p>Title: XMLException.java</p>
 * <p>Description: high level exception class, extends IOException.</p>
 * <p>
 *  <ul>
 *  	<li>created on 05.01.2007</li>
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
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2004
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: XMLException.java,v 1.1 2007/01/05 11:43:14 dippadm Exp $
 */
public class XMLException extends IOException{

	static final long serialVersionUID = -1;

	public XMLException(){
		super();
	}
	
	public XMLException(String aExcMsg){
		super(aExcMsg);
	}
	
/*	public XMLException(String aMsg, Throwable aRootCause){
		super(aMsg, aRootCause);
	}
*/	
}
