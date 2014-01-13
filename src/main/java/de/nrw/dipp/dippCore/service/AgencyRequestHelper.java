/*
 * AgencyRequestHelper.java
 *
 * Created on 10. September 2002, 14:19
 * History: 04/11/2003 by JS: eclipse-project
 */

package de.nrw.dipp.dippCore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author  jschirr
 */
public class AgencyRequestHelper extends AbstractRequestHelper{

//    private ActionFactory actionFactory = new ActionFactoryImpl();
    
    /** Creates a new instance of AgencyRequestHelper */
    AgencyRequestHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }
    
/*    public Action getAction()throws ActionException{
        return actionFactory.createAction( getProperties().getProperty("action"));
    }
*/    
}
