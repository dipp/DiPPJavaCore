/*
 * RequestHelper.java
 *
 * Created on 10. September 2002, 14:00
 */

package de.nrw.dipp.dippCore.service;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author  jschirr
 */
public interface RequestHelper {
    public HttpServletRequest getRequest();
    public HttpServletResponse getResponse();
//    public Action getAction() throws Exception;
    public Properties getProperties();
    public HttpSession getSession();
    public String getRequestPropertiesToString();
}
