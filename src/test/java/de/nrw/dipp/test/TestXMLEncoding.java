package de.nrw.dipp.test;

import org.apache.xmlbeans.XmlOptions;

import de.nrw.dipp.dippCore.repository.metadata.dc.DublinCoreQualified;
import de.nrw.dipp.dippCore.webservice.QualifiedDublinCore;
import fedora.fedoraSystemDef.foxml.DatastreamType;
import fedora.fedoraSystemDef.foxml.DatastreamVersionType;
import fedora.fedoraSystemDef.foxml.DigitalObjectDocument;
import fedora.fedoraSystemDef.foxml.XmlContentType;
import fedora.fedoraSystemDef.foxml.DigitalObjectDocument.DigitalObject;

public class TestXMLEncoding {

	public static void main(String[] args){
		DigitalObjectDocument mDoDoc = null;
		DigitalObject mDigObj = null;
		
		QualifiedDublinCore qdc = new QualifiedDublinCore();
		String[] type = {"Social Work & Society"};
		qdc.setArticleType(type);
		DublinCoreQualified dcqualified = new DublinCoreQualified();
		dcqualified.setQualifiedDublinCore(qdc);
//		System.out.println(dcqualified.getQualifiedDublinCore().xmlText());
		
		
		
		XmlOptions xmlOpts = new XmlOptions();
		xmlOpts.setCharacterEncoding("UTF-8");
		mDoDoc = DigitalObjectDocument.Factory.newInstance();
		mDigObj = mDoDoc.addNewDigitalObject();

		DatastreamType datastream = mDigObj.addNewDatastream();
		datastream.setCONTROLGROUP(DatastreamType.CONTROLGROUP.X);
		DatastreamVersionType dsVersion = datastream.addNewDatastreamVersion();
		XmlContentType xmlContent = dsVersion.addNewXmlContent();
		xmlContent.set(dcqualified.getQualifiedDublinCore());
		
		System.out.println(mDoDoc.xmlText());
		
		
		
	}
}
