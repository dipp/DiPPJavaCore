package de.nrw.dipp.dippCore.repository.metadata.dc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlOptions;
import org.openarchives.oai.x20.oaiDc.ArticleType;
import org.openarchives.oai.x20.oaiDc.BK;
import org.openarchives.oai.x20.oaiDc.DDC;
import org.openarchives.oai.x20.oaiDc.DOI;
import org.openarchives.oai.x20.oaiDc.DocType;
import org.openarchives.oai.x20.oaiDc.ISBN;
import org.openarchives.oai.x20.oaiDc.ISSN;
import org.openarchives.oai.x20.oaiDc.JEL;
import org.openarchives.oai.x20.oaiDc.LCC;
import org.openarchives.oai.x20.oaiDc.LCSH;
import org.openarchives.oai.x20.oaiDc.MESH;
import org.openarchives.oai.x20.oaiDc.MetadataDocument;
import org.openarchives.oai.x20.oaiDc.PACS;
import org.openarchives.oai.x20.oaiDc.PubType;
import org.openarchives.oai.x20.oaiDc.RVK;
import org.openarchives.oai.x20.oaiDc.SWD;
import org.openarchives.oai.x20.oaiDc.UDC;
import org.openarchives.oai.x20.oaiDc.URL;
import org.openarchives.oai.x20.oaiDc.URN;
import org.purl.dc.elements.x11.Keyword;
import org.purl.dc.elements.x11.SimpleLiteral;
import org.purl.dc.elements.x11.VCard;
import org.purl.dc.terms.Citation;
import org.purl.dc.terms.PartType;

import de.nrw.dipp.dippCore.webservice.Element;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;

/**
 * <p>Title: DublinCoreQualified.java</p>
 * <p>Description: A model class for Qualified Dublin Core.</p>
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
 * @version $Id: DublinCoreQualified.java,v 1.2 2007/01/05 11:35:56 dippadm Exp $
 */
public class DublinCoreQualified {

	private static final String		cCreatorPerson		= "person";
	private static final String		cCreatorCorporation	= "corporation";
	private static final String		cContributor		= "contributor";
	private static Hashtable 		cClassificatonSystems	= new Hashtable();
	
	private MetadataDocument mMetadataDoc = null;
	private MetadataDocument.Metadata	mMetadata = null;
	private List			mDCtypeList	= new Vector();
	private List			mDDCList	= new Vector();
	
	public DublinCoreQualified(){
		init();
	}
	
	private void init(){
		XmlOptions options = new XmlOptions();
		options.setCharacterEncoding("utf-8");
		mMetadataDoc = MetadataDocument.Factory.newInstance(options);
		mMetadata = mMetadataDoc.addNewMetadata();
		cClassificatonSystems.put("swd", SWD.type);
		cClassificatonSystems.put("ddc", DDC.type);
		cClassificatonSystems.put("lcsh", LCSH.type);
		cClassificatonSystems.put("mesh", MESH.type);
		cClassificatonSystems.put("lcc", LCC.type);
		cClassificatonSystems.put("udc", UDC.type);
		cClassificatonSystems.put("rvk", RVK.type);
		cClassificatonSystems.put("bk", BK.type);
		cClassificatonSystems.put("pacs", PACS.type);
		cClassificatonSystems.put("jel", JEL.type);
	}
	
	public static String getClassificationSystem(SchemaType aType){
		String classSystem = "";
//		if (cClassificatonSystems.containsValue(aType)){
			if (aType.getName().equals(SWD.type.getName())){
				classSystem = "swd";
			}else if (aType.getName().equals(DDC.type.getName())){
				classSystem = "ddc";
			}else if (aType.getName().equals(LCSH.type.getName())){
				classSystem = "lcsh";
			}else if (aType.getName().equals(MESH.type.getName())){
				classSystem = "mesh";
			}else if (aType.getName().equals(LCC.type.getName())){
				classSystem = "lcc";
			}else if (aType.getName().equals(UDC.type.getName())){
				classSystem = "udc";
			}else if (aType.getName().equals(RVK.type.getName())){
				classSystem = "rvk";
			}else if (aType.getName().equals(BK.type.getName())){
				classSystem = "bk";
			}else if (aType.getName().equals(PACS.type.getName())){
				classSystem = "pacs";
			}else if (aType.getName().equals(JEL.type.getName())){
				classSystem = "jel";
			}
//		}
		return classSystem;
	}
	
	public MetadataDocument getQualifiedDublinCore(){
		return mMetadataDoc;
	}
	
	public String getXml(){
		return mMetadataDoc.toString();
	}
	
	public List getDdc(){
		return mDDCList;
	}
	
	public void setQualifiedDublinCore(QualifiedDublinCore aQDCMetadata){
		SimpleDateFormat	dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			
			// a Title is required
			for (int i = 0; i < aQDCMetadata.getTitle().length; i++){
				SimpleLiteral lit = mMetadata.addNewTitle();
				setLiteral(aQDCMetadata.getTitle()[i], lit);
			}

			// this Metadate is not required 
			if(aQDCMetadata.getAlternative()!= null){
				for (int i = 0; i < aQDCMetadata.getAlternative().length; i++){
					SimpleLiteral lit = mMetadata.addNewAlternative();
					setLiteral(aQDCMetadata.getAlternative()[i], lit);
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getDCTermsAbstract()!= null){
				for (int i = 0; i < aQDCMetadata.getDCTermsAbstract().length; i++){
					SimpleLiteral lit = mMetadata.addNewAbstract();
					setLiteral(aQDCMetadata.getDCTermsAbstract()[i], lit);
				}				
			}

			// this Metadate is not required 
			if(aQDCMetadata.getTableOfContents()!= null){
				for (int i = 0; i < aQDCMetadata.getTableOfContents().length; i++){
					SimpleLiteral lit = mMetadata.addNewTableOfContents();
					setLiteral(aQDCMetadata.getTableOfContents()[i], lit);
				}
			}
			
			// Create an empty Array if DDC is not set due to other methods that expect
			// this Array and do not check existence
			if(aQDCMetadata.getDDC()== null){
				aQDCMetadata.setDDC(new String[0]);
			}

			// Create an empty Array if Subject is not set due to other methods that expect 
			// this Array and do not check existence
			if(aQDCMetadata.getSubject()== null){
				aQDCMetadata.setSubject(new String[0]);
			}

			setSubjectDDC(Arrays.asList(aQDCMetadata.getDDC()).iterator());
			mDDCList = Arrays.asList(aQDCMetadata.getDDC());
			setSubject(Arrays.asList(aQDCMetadata.getSubject()).iterator());

			if(aQDCMetadata.getSubjectClassified()!= null){
				for (int i = 0; i < aQDCMetadata.getSubjectClassified().length; i++){
					Keyword kw = mMetadata.addNewSubject();
					de.nrw.dipp.dippCore.webservice.SubjectClassified classified = aQDCMetadata.getSubjectClassified()[i];
					kw.addClassified(classified.getSubjectClassified());
					SimpleLiteral literal = kw.addNewClassification();
					literal.setStringValue(classified.getClassificationIdent());
					if (cClassificatonSystems.get(classified.getClassificationSystem()) != null){
						literal.changeType((SchemaType)cClassificatonSystems.get(classified.getClassificationSystem()));
					}
				}
			}
			
			// Create an empty Array if DocType is not set due to other methods that expect 
			// this Array and do not check existence
			if(aQDCMetadata.getDocType()== null){
				aQDCMetadata.setDocType(new String[0]);
			}
			for (int i = 0; i < aQDCMetadata.getDocType().length; i++){
				SimpleLiteral lit = mMetadata.addNewType();
				lit.setStringValue(aQDCMetadata.getDocType(i));
				lit.changeType(DocType.type);
				mDCtypeList.add("doc-type");
				mDCtypeList.add("doc-type:" + aQDCMetadata.getDocType(i));
			}				

			// Create an empty Array if PubType is not set due to other methods that expect 
			// this Array and do not check existence
			if(aQDCMetadata.getPubType()== null){
				aQDCMetadata.setPubType(new String[0]);
			}
			for (int i = 0; i < aQDCMetadata.getPubType().length; i++){
				SimpleLiteral lit = mMetadata.addNewType();
				lit.setStringValue(aQDCMetadata.getPubType(i));
				lit.changeType(PubType.type);
				mDCtypeList.add("pub-type");
				mDCtypeList.add("pub-type:" + aQDCMetadata.getPubType(i));
			}	
			
			// this Metadate is not required 
			if(aQDCMetadata.getArticleType()!= null){
				for (int i = 0; i < aQDCMetadata.getArticleType().length; i++){
					SimpleLiteral lit = mMetadata.addNewType();
					lit.setStringValue(aQDCMetadata.getArticleType(i));
					lit.changeType(ArticleType.type);
					mDCtypeList.add("article-type");
					mDCtypeList.add("article-type:" + aQDCMetadata.getArticleType(i));
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getFormat()!= null){
				for (int i = 0; i < aQDCMetadata.getFormat().length; i++){
					SimpleLiteral lit = mMetadata.addNewFormat();
					lit.setStringValue(aQDCMetadata.getFormat(i));
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getBibliographicCitation()!= null){
				for (int i = 0; i < aQDCMetadata.getBibliographicCitation().length; i++){
					de.nrw.dipp.dippCore.webservice.Citation webCitation = aQDCMetadata.getBibliographicCitation()[i];
					Citation citation = mMetadata.addNewBibliographicCitation();
					citation.setJournalIssueDate(webCitation.getJournalIssueDate());
					citation.setJournalIssueNumber(webCitation.getJournalIssueNumber());
					citation.setJournalTitle(webCitation.getJournalTitle());
					citation.setJournalVolume(webCitation.getJournalVolume());
				}
			}
			
			// this Metadate is not required 
			if(aQDCMetadata.getContributor()!= null){
				for (int i = 0; i < aQDCMetadata.getContributor().length; i++){
					VCard vcard = mMetadata.addNewContributor();
					de.nrw.dipp.dippCore.webservice.Contributor contributor = aQDCMetadata.getContributor()[i];
					setVCard(vcard, contributor);
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getCreatorPerson()!= null){
				for (int i = 0; i < aQDCMetadata.getCreatorPerson().length; i++){
					VCard vcard = mMetadata.addNewCreator();
					de.nrw.dipp.dippCore.webservice.CreatorPerson person = aQDCMetadata.getCreatorPerson()[i];
					setVCard(vcard, person);
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getCreatorCorporated()!= null){
				for (int i = 0; i < aQDCMetadata.getCreatorCorporated().length; i++){
					VCard vcard = mMetadata.addNewCreator();
					de.nrw.dipp.dippCore.webservice.CreatorCorporated corporated = aQDCMetadata.getCreatorCorporated()[i];
					setVCard(vcard, corporated);
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getHasPart()!= null){
				for (int i = 0; i < aQDCMetadata.getHasPart().length; i++){
					de.nrw.dipp.dippCore.webservice.Part wsPart = aQDCMetadata.getHasPart()[i];
					PartType partType = mMetadata.addNewHasPart();
					setPart(wsPart, partType);
				}
			}

			// this Metadate is not required 
			if(aQDCMetadata.getIsPartOf()!= null){
				for (int i = 0; i < aQDCMetadata.getIsPartOf().length; i++){
					de.nrw.dipp.dippCore.webservice.Part wsPart = aQDCMetadata.getIsPartOf()[i];
					PartType partType = mMetadata.addNewIsPartOf();
					setPart(wsPart, partType);
				}
			}
							
			// this Metadate is not required 
			if(aQDCMetadata.getIsVersionOf()!= null){
				for (int i = 0; i < aQDCMetadata.getIsVersionOf().length; i++){
					de.nrw.dipp.dippCore.webservice.Part wsPart = aQDCMetadata.getIsVersionOf()[i];
					PartType partType = mMetadata.addNewIsVersionOf();
					setPart(wsPart, partType);
				}
			}
			
			// this Metadate is not required 
			if(aQDCMetadata.getLanguage()!= null){
				for (int i = 0; i < aQDCMetadata.getLanguage().length; i++){
					SimpleLiteral lit = mMetadata.addNewLanguage();
					lit.setStringValue(aQDCMetadata.getLanguage(i));
				}
			}
			
			// this Metadate is not required 
			if(aQDCMetadata.getPublisher()!= null){
				for (int i = 0; i < aQDCMetadata.getPublisher().length; i++){
					SimpleLiteral lit = mMetadata.addNewPublisher();
					lit.setStringValue(aQDCMetadata.getPublisher(i));
				}
			}
			
			// this Metadate is not required 
			if(aQDCMetadata.getRights()!= null){
				for (int i = 0; i < aQDCMetadata.getRights().length; i++){
					SimpleLiteral lit = mMetadata.addNewRights();
					lit.setStringValue(aQDCMetadata.getRights(i));
				}
			}
			
			if (aQDCMetadata.getAvailable() != null){
				SimpleLiteral dateLit = mMetadata.addNewAvailable();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getAvailable().getTime()));
			}
			if (aQDCMetadata.getCreated() != null){
				SimpleLiteral dateLit = mMetadata.addNewCreated();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getCreated().getTime()));
			}
			if (aQDCMetadata.getDateAccepted() != null){
				SimpleLiteral dateLit = mMetadata.addNewDateAccepted();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getDateAccepted().getTime()));
			}
			if (aQDCMetadata.getDateCopyrighted() != null){
				SimpleLiteral dateLit = mMetadata.addNewDateCopyrighted();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getDateCopyrighted().getTime()));
			}
			if (aQDCMetadata.getDateSubmitted() != null){
				SimpleLiteral dateLit = mMetadata.addNewDateSubmitted();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getDateSubmitted().getTime()));
			}
			if (aQDCMetadata.getIssued() != null){
				SimpleLiteral dateLit = mMetadata.addNewIssued();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getIssued().getTime()));
			}
			if (aQDCMetadata.getModified() != null){
				SimpleLiteral dateLit = mMetadata.addNewModified();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getModified().getTime()));
			}
			if (aQDCMetadata.getValid() != null){
				SimpleLiteral dateLit = mMetadata.addNewValid();
				dateLit.setStringValue(dateFormat.format(aQDCMetadata.getValid().getTime()));
			}
			if (aQDCMetadata.getIdentifierDOI() != null){
				SimpleLiteral uriLit = mMetadata.addNewIdentifier();
				uriLit.setStringValue(aQDCMetadata.getIdentifierDOI());
				uriLit.changeType(DOI.type);
			}
			if (aQDCMetadata.getIdentifierURN() != null){
				SimpleLiteral uriLit = mMetadata.addNewIdentifier();
				uriLit.setStringValue(aQDCMetadata.getIdentifierURN());
				uriLit.changeType(URN.type);
			}
			if (aQDCMetadata.getIdentifierURL() != null){
				SimpleLiteral uriLit = mMetadata.addNewIdentifier();
				uriLit.setStringValue(aQDCMetadata.getIdentifierURL());
				uriLit.changeType(URL.type);			
			}
			if (aQDCMetadata.getIdentifierISBN() != null){
				SimpleLiteral uriLit = mMetadata.addNewIdentifier();
				uriLit.setStringValue(aQDCMetadata.getIdentifierISBN());
				uriLit.changeType(ISBN.type);		
			}
			if (aQDCMetadata.getIdentifierISSN() != null){
				SimpleLiteral uriLit = mMetadata.addNewIdentifier();
				uriLit.setStringValue(aQDCMetadata.getIdentifierISSN());
				uriLit.changeType(ISSN.type);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void setPart(de.nrw.dipp.dippCore.webservice.Part aPart, PartType aPartType){
		SimpleLiteral literal = aPartType.addNewTitle();
		literal.setStringValue(aPart.getTitle());
		if (aPart.getURL() != null){
			literal = aPartType.addNewIdentifier();
			literal.setStringValue(aPart.getURL());
			literal.changeType(URL.type);			
		}
		if (aPart.getURN() != null){
			literal = aPartType.addNewIdentifier();
			literal.setStringValue(aPart.getURN());
			literal.changeType(URN.type);			
		}
	}
	private void setVCard(VCard aFedoraVCard, de.nrw.dipp.dippCore.webservice.VCard aWebserviceVcard){
		VCard vcard = aFedoraVCard;

		XmlAnySimpleType type = vcard.addNewType();
		if (aWebserviceVcard instanceof de.nrw.dipp.dippCore.webservice.Contributor){
			type.setStringValue(cContributor);
			vcard.setAcademicTitle(aWebserviceVcard.getAcademicTitle());
			vcard.setFirstName(aWebserviceVcard.getFirstName());
			vcard.setLastName(aWebserviceVcard.getLastName());
			vcard.setPndIdentifier(aWebserviceVcard.getPNDIdentNumber());
			vcard.setRole(aWebserviceVcard.getRole());
		}else if (aWebserviceVcard instanceof de.nrw.dipp.dippCore.webservice.CreatorPerson){
			type.setStringValue(cCreatorPerson);
			vcard.setAcademicTitle(aWebserviceVcard.getAcademicTitle());
			vcard.setFirstName(aWebserviceVcard.getFirstName());
			vcard.setLastName(aWebserviceVcard.getLastName());
			vcard.setPndIdentifier(aWebserviceVcard.getPNDIdentNumber());
		}else if (aWebserviceVcard instanceof de.nrw.dipp.dippCore.webservice.CreatorCorporated){
			type.setStringValue(cCreatorCorporation);
			vcard.setLastName(aWebserviceVcard.getInstitutionelAuthor());
			vcard.setGkdIdentifier(aWebserviceVcard.getGKDIdentNumber());
		}
		vcard.setEmail(aWebserviceVcard.getEmailAddress());
		vcard.setPostal(aWebserviceVcard.getPostalAddress());
		vcard.setOrganization(aWebserviceVcard.getOrganization());
	}
	
	private void setLiteral(Element aElement, SimpleLiteral aLiteral){
		try{
			aLiteral.setStringValue(aElement.getValue());
			aLiteral.setLang(aElement.getLang());
			if (aElement.getType() != null){
				if (!aElement.getType().trim().equals("")){
					aLiteral.setType(aElement.getType());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testIt(){
		SimpleLiteral literal = mMetadata.addNewAbstract();		
		literal.setStringValue("abstract");
		literal.setLang("ger");
		
		System.out.println(mMetadataDoc.xmlText());
	}
	
/*	private void setPart(Part aPart, PartType aPartType){
		SimpleLiteral literal = aPartType.addNewTitle();
		literal.setStringValue(aPart.getTitle());
		literal = aPartType.addNewIdentifier();
		literal.setStringValue(aPart.getURL());
		literal.changeType(URL.type);
		literal = aPartType.addNewIdentifier();
		literal.setStringValue(aPart.getURN());
		literal.changeType(URN.type);		
	}
*/	
	public List getDCTypeList(){
		return mDCtypeList;
	}
	
/*	private void setCreator(Iterator aIteratorCreator){
		VCard vcard = null;
		while (aIteratorCreator.hasNext()){
			de.nrw.dipp.form.qualified_dublin_core.VCard creator = (de.nrw.dipp.form.qualified_dublin_core.VCard)aIteratorCreator.next();
			if (creator instanceof Contributor){
				vcard = mMetadata.addNewContributor();		
			}else{
				vcard = mMetadata.addNewCreator();
			}
			XmlAnySimpleType type = vcard.addNewType();
			if (creator instanceof CreatorPerson){
				type.setStringValue(cCreatorPerson);
				vcard.setFirstName(creator.getFirstName());
				vcard.setLastName(creator.getLastName());
			}else if (creator instanceof CreatorCorporated){
				type.setStringValue(cCreatorCorporation);
				vcard.setLastName(creator.getInstitutionelAuthor());
				vcard.setGkdIdentifier(creator.getGKDIdentNumber());
			}else if (creator instanceof Contributor){
				type.setStringValue(creator.getRole());
				vcard.setFirstName(creator.getFirstName());
				vcard.setLastName(creator.getLastName());
			}
			vcard.setAcademicTitle(creator.getAcademicTitle());
			vcard.setEmail(creator.getEmailAddress());
			vcard.setPostal(creator.getPostalAddress());
			vcard.setPndIdentifier(creator.getPNDIdentNumber());
			vcard.setOrganization(creator.getOrganization());
		}
	}
*/	
	private void setSubject(Iterator aIterator){
		while (aIterator.hasNext()){
			Keyword kw = mMetadata.addNewSubject();
			kw.addUnclassified((String)aIterator.next());
		}		
	}
	
	private void setSubjectDDC(Iterator aIterator){
		while (aIterator.hasNext()){
			Keyword kw = mMetadata.addNewSubject();
			kw.addDdc((String)aIterator.next());
		}		
	}
/*	private void setSubjectClassified(Iterator aIterator){
		while (aIterator.hasNext()){
			SubjectClassified classified = (SubjectClassified)aIterator.next();
			Keyword kw = mMetadata.addNewSubject();
			kw.addClassified(classified.getSubjectClassified());
			SimpleLiteral literal = kw.addNewClassification();
			literal.setStringValue(classified.getClassificationIdent());
			if (cClassificatonSystems.get(classified.getClassificationSystem()) != null){
				literal.changeType((SchemaType)cClassificatonSystems.get(classified.getClassificationSystem()));
			}
		}		
	}
*/	
}
