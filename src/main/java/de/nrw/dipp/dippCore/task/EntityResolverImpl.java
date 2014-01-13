/*
 * Created on 02.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.io.FileInputStream;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.nrw.dipp.dippCore.util.Constant;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EntityResolverImpl implements EntityResolver {

	/* (non-Javadoc)
	 * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
	 */
	public InputSource resolveEntity(String publicID, String systemID)
	throws SAXException, IOException {
		if (publicID.equals("-//OASIS//DTD DocBook XML V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/docbookx.dtd"));
            // return a special input source
		}else if (publicID.equals("-//OASIS//DTD DocBook CALS Table Model V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/calstblx.dtd"));
		}else if (publicID.equals("-//OASIS//DTD XML Exchange Table Model 19990315//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/soextblx.dtd"));
		}else if (publicID.equals("-//OASIS//ELEMENTS DocBook Information Pool V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/dbpoolx.mod"));
		}else if (publicID.equals("-//OASIS//ELEMENTS DocBook Document Hierarchy V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/dbhierx.mod"));
		}else if (publicID.equals("-//OASIS//ENTITIES DocBook Additional General Entities V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/dbgenent.mod"));
		}else if (publicID.equals("-//OASIS//ENTITIES DocBook Notations V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/dbnotnx.mod"));
		}else if (publicID.equals("-//OASIS//ENTITIES DocBook Character Entities V4.2//EN")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/dbcentx.mod"));

		
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Diacritical Marks//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-dia.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Numeric and Special Graphic//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-num.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Publishing//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-pub.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES General Technical//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-tech.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Latin 1//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-lat1.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Latin 2//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-lat2.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Greek Letters//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-grk1.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Monotoniko Greek//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-grk2.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Greek Symbols//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-grk3.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Alternative Greek Symbols//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-grk4.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Arrow Relations//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amsa.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Binary Operators//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amsb.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Delimiters//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amsc.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Negated Relations//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amsn.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Ordinary//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amso.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Added Math Symbols: Relations//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-amsr.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Box and Line Drawing//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-box.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Russian Cyrillic//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-cyr1.ent"));
		}else if (publicID.equals("ISO 8879:1986//ENTITIES Non-Russian Cyrillic//EN//XML")) {
			return new InputSource(new FileInputStream(Constant.getAbsolutPath() + "/WEB-INF/catalog/ent/iso-cyr2.ent"));
		}else {
            // use the default behaviour
			return null;
		}
	}

}
