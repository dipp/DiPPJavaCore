//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  FedoraServicesAccess.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"

package de.nrw.dipp.dippCore.repository;

//..begin "Imports"
import java.util.List;
import java.rmi.RemoteException;
import java.io.InputStream;
import java.util.Set;
import java.util.Properties;
//..end "Imports"


/**
 * <p><b>Title:</b> FedoraServicesAccess.java</p>
 * <p><b>Description:</b> An interface that defines all API-A relevant methods 
 * that have to be implemented in a Fedora Web Service Adaptor for interoperability 
 * with DOManagement and ServiceManagement.</p>
 * 
 * <p>Interface deals with the problem that Fedora tends to change their Web Services 
 * from Version to Version. It will be the Structure for creating new Web Service Adaptors</p>
 * <p>Supported Web Services descriptions are</p>
 * <ul>
 * <li><a target="_blank" href="http://www.fedora.info/definitions/1/0/api/Fedora-API-A.html">Fedora Version 1-2 (API-A)</a></li>
 * <li><a target="_blank" href="http://fedora.info/download/2.1.1/userdocs/client/servlet/soapclient/index.html">Fedora Version 2.1.1 (Fedora SOAP Client Documentation, API-A)</a></li> 
 * <li><a target="_blank" href="http://fedora-commons.org/confluence/display/FCR30/API-A">Fedora Version 3-x (API-A)</a></li>
 * </ul>
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
 * @author Jochen Schirrwagen, no longer employed at hbz
 * @author Andres Quast, quast@hbz-nrw.de
 * @author Hermann Kronenberg, kronenberg@hbz-nrw.de (Documentation param)
 * @version $Id: FedoraServicesAccess.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 
*/
public interface FedoraServicesAccess {

	public String identify();
	/**
	 * <p>Method queries the Fedora Repository for FedoraObjects that matches given criteria. 
	 * Gives back DigitalObject Instances containing all ObjectFields defined in the resultFieldsArray. 
	 * Query is defined by Param aSearchConditionList</p>
	 * <p><em>Hint: Method does not directly reflects an API-A Operation, because it must be Implemented in a way
	 * it gives back a DigitalObject[] Array instead of a FieldSearchResult Instance</em></p>
	 * <p><em>Hint 2: Because this Method is used from DiPP with requesting the cModel Field it has to 
	 * be implemented carefully for Fedora Version 3</em></p>
	 * <p><em>Hint 3: Method nowadays is implemeted in a way, that it calls the resumeFindObjects API-A
	 * Operation subsequently after calling findObjects Method. Therefore the MaxResults parameter 
	 * maybe is meaningless</em></p>
	 * 
	 * @param  aResultFieldArray <code>String[]</code> an Array of String that provides 
	 * the fields that should be returned in the response. Can be all fields existing in the 
	 * table doFields within the integrated Database of Fedora. Array have the fields, defined 
	 * at ServiceManagement.java :
	 *         <ul>
	 *         <li>pid = unique Fedora-ID of the object (e.g.: content:12, dipp:123 or temp:1)</li>
	 *         <li>cModel = Fedora Content Model of the object</li>
	 *         <li>label = unique ID of the eJournal to which a object belong to</li>
	 *         <li>...</li>
	 *         </ul>
	 * @param  aMaxResults <code>int</code> the maximum number of results that the server should 
	 * provide at once. <br>For the DiPP case it's normally 1 for got exactly one hit/object. 
	 * This may be null, in which case the server will return the maximum number of results it 
	 * has been configured to return.
	 * @param  aSearchConditionsList <code>List</code> a List of SearchConditions. The terms and conditions for the search.<br>
	 * For elements of SearchConditionList see also wrapper class <b>SearchCondition.java</b>
	 *         <ul>
	 *         <li>property = property of Object / name of search field </li>
	 *         <li>value = search text </li>
	 *         <li>condition = Search oparator (contains, equal, ...),
	 *         Possible values are: "has", "eq", "lt", "le", "gt" and "ge"
	 *         Operator may be used in combination with the ? and * wildcards 
	 *         to query for simple string patterns.</li>
	 *         </ul> 
	 * @return <code>DigitalObject[]</code> an Array of zero, one or more DigitalObjects. In 
	 * the case of DiPP it's mostly one {@link de.nrw.dipp.repository.DigitalObject DigitalObject}
	 * that is requested 
	 * @throws RemoteException
	 
	*/
	public de.nrw.dipp.dippCore.repository.DigitalObject[] findObjects(String[] aResultFieldArray, int aMaxResults, List<SearchCondition> aSearchConditionsList) throws RemoteException;
	/**
	 * <p>Method returns one Datastream from one Fedora Object identified by the Fedora Object PID and 
	 * by the Datastream ID.</p>
	 * <p><em>Hint: Method does not directly reflects an API-A Operation, 
	 * calls getDatastreamDissemination Operation</em></p>
	 * 
	 * @param aPID <code>String</code> unique Fedora-ID of the object (e.g.: content:12, dipp:123 or temp:1)
	 * @param aItemID <code>String</code> Datastream Identifier. Can be:
	 * <ul>
	 * <li>DC</li>
	 * <li>QDC</li>
	 * <li>RELS-EXT</li>
	 * <li>DiPPAdm</li>
	 * <li>DiPPExt</li>
	 * <li>oai_dc</li>
	 * <li>oai_doaj</li>
	 * <li>oai_epicur</li>
	 * </ul>
	 * 
	 * @return <code>byte[]</code> the requested Datastream as Array of byte
	 * @throws RemoteException
	 
	*/
	public byte[] getDataStreamContentAsBytes(String aPID, String aItemID)throws RemoteException;
	/**
	 * @param aPID <code>String</code> unique Fedora-ID of the object (e.g.: content:12, dipp:123 or temp:1)
	 * @param aItemID <code>String</code> Datastream Identifier. Can be:
	 * <ul>
	 * <li>DC</li>
	 * <li>QDC</li>
	 * <li>RELS-EXT</li>
	 * <li>DiPPAdm</li>
	 * <li>DiPPExt</li>
	 * <li>oai_dc</li>
	 * <li>oai_doaj</li>
	 * <li>oai_epicur</li>
	 * </ul>
	 * 
	 * @return <code>InputStream</code> a Datastream as InputStream
	 * @throws RemoteException
	 
	*/
	public InputStream getDataStreamContent(String aPID, String aItemID)throws RemoteException;
	/**
	 * <p>Method returns all available Datastreams from a given Fedora Object. Apparently it 
	 * is used in DiPP mainly for check if a datastream already exists within an Object.</p>
	 * 
	 * @param aPID <code>String</code> unique Fedora-ID of the object (e.g.: content:12, dipp:123 or temp:1)
	 * 
	 * @return <code>Set</code> a set that contains all Datastreams from a Fedora Object, 
	 * identified by their Datastream Identifiers
	 *    
	 * @throws RemoteException
	 
	*/
	public Set<String[]> getItemIndex(String aPID) throws RemoteException;
	/**
	 * <p>Method returns key/value pairs where the key is the Label, and the 
	 * value is the Datastream ID of each Datastream provided by the Fedora Object identified by PID</p>
	 * 
	 * @param aPID - unique Fedora-ID of the object (e.g.: content:12, dipp:123 or temp:1)
	 * 
	 * @return <code>Properties</code> key/value pairs of label and Datastream Id   
	 * @throws RemoteException
	 
	*/
	public Properties getItemLabelProps(String aPID) throws RemoteException;

}

