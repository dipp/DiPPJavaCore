/*
 * AbstractRequestHelper.java
 *
 * Created on 10. September 2002, 14:04
 * History: 
 * 			04/11/2003 by JS: eclipse-project
 */

package de.nrw.dipp.dippCore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
/**
 *
 * @author  jschirr
 */
abstract class AbstractRequestHelper implements RequestHelper{

    private HttpServletRequest _request = null;
    private HttpServletResponse _response = null;
    private Properties _properties = null;
//    private Action _action = null;

    /** Creates a new instance of AbstractRequestHelper */
    AbstractRequestHelper( HttpServletRequest request, HttpServletResponse response){
        _request = request;
        _response = response; 
        _properties = getProperties( request );
    }
    
    public HttpServletRequest getRequest() {
        return _request;
    }
    
    public HttpServletResponse getResponse() {
        return _response;
    }

    public Properties getProperties(){
        return _properties;
    }
    /**
     *  translates servlet request to java properties
     */
    private Properties getProperties(HttpServletRequest request){
        Properties properties = new Properties();
        for (Enumeration enumeration = request.getParameterNames();
                                enumeration.hasMoreElements();){
            Object obj = enumeration.nextElement();
            String[] values = request.getParameterValues((String)obj);
            if (values.length == 1){
//            	ServiceLocator.logError("abstractRequestHelper: " + obj + " " + values[0]);
//                System.out.println("abstractRequestHelper: " + obj + " " + values[0]);
                properties.put(obj, values[0]);
            }else{
                for (int i = 0; i < values.length; i++){
//                	ServiceLocator.logError("abstractRequestHelper: " + obj + " " + values[i]);
//                    System.out.println("abstractRequestHelper: " + obj + " " + values[i]);
                }
                
                properties.put(obj, values);
            }
        }
        return properties;
    }
    
/*    public Action getAction() throws Exception{
        return _action;
    }
*/    
    public HttpSession getSession() {
        return getRequest().getSession();
    }
    
    public String getRequestPropertiesToString(){
        StringBuffer strBuf = new StringBuffer();
        
        for (Enumeration enumeration = _request.getParameterNames();
                                enumeration.hasMoreElements();){
            Object obj = enumeration.nextElement();
            String[] values = _request.getParameterValues((String)obj);
            if (values.length == 1 && values[0].length() > 0){
                strBuf.append(obj + " " + values[0] + " ");
            }else{
                for (int i = 0; i < values.length; i++){
                    if (values[i].length() > 0){
                        strBuf.append(obj + " " + values[i] + " ");
                    }
                }
            }
        }
        return strBuf.toString();
    }
    
}
