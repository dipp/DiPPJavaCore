/*
 * Created on 29.09.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.webservice;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface VCard {

	public void setLastName(String aLastName);
	public void setFirstName(String aFirstName);
	public void setAcademicTitle(String aAcademicTitle);
	public void setOrganization(String aOrganization);
	public void setPostalAddress(String aPostalAddress);
	public void setEmailAddress(String aEmailAddress);
	public void setPNDIdentNumber(String aPNDIdent);
	public void setGKDIdentNumber(String aGKDIdent);
	
	
	public void setRole(String aRole);
	public void setInstitutionelAuthor(String aInstitutionelAuthor);
	public void setDippIdentNumber(String dippIdentNumber);
	public void setIdentNumber(IdentNumberType identNumber);

	public String getLastName();
	public String getFirstName();
	public String getAcademicTitle();
	public String getOrganization();
	public String getPostalAddress();
	public String getEmailAddress();
	public String getPNDIdentNumber();
	public String getGKDIdentNumber();

	public String getRole();
	public String getInstitutionelAuthor();
	public String getDippIdentNumber();
	public IdentNumberType getIdentNumber();

}
