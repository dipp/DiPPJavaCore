/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.service;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.nrw.dipp.dippCore.repository.ServiceManagement;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HelperServlet extends HttpServlet {

	static final long serialVersionUID = -1;

    //get Logger instance
    private Logger log = Logger.getLogger(HelperServlet.class);
	

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
//		super.doGet(arg0, arg1);
		processRequest(arg0, arg1);
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
//		super.doPost(arg0, arg1);
		processRequest(arg0, arg1);
	}
	private void processRequest(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		RequestHelper helper = new AgencyRequestHelper(aRequest, aResponse);
		Properties props = helper.getProperties();
		log.info("use method processRequest");
		if (props.containsKey("action")){			
			if (props.getProperty("action").equals("convert")){
				ServiceManagement fi = new ServiceManagement();
				System.out.println("ID: " + props.getProperty("articlePID"));
				System.out.println("URL: " + props.getProperty("documentURL"));
		        fi.convert(props.getProperty("articlePID"), props.getProperty("documentURL"), (String[])props.get("targetFormat"));								
			}
		}
	}
}
