/*
 * BeanObject.java
 *
 * Created on 21. September 2004, 15:34
 */

package de.nrw.dipp.dippCore.util.request;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.util.*;

/**
 *  adapted from de.tourism.web.utils.BeanResultObject
 * @author  jschirr
 */
public class RequestMapper {

    private static RequestMapper _beanObj_ref = null;
    
    static{
        try{
            _beanObj_ref = new RequestMapper();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /** Creates a new instance of BeanObject */
    private RequestMapper() {
    }
    
    public static RequestMapper getInstance() throws Exception{
        return _beanObj_ref;
    }
    
    private static Properties getBeanPropertyDescriptors(Object bean){
        Properties propDescriptors = new Properties();
        Class beanclass = bean.getClass();
        try{
            BeanInfo info = Introspector.getBeanInfo(beanclass);            
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (int i = 0; i < props.length; i++){
                if (props[i].getWriteMethod() != null){
                  propDescriptors.put(props[i].getName(), props[i]);
                }
            }
        }catch(IntrospectionException exceptionIntrospection){
            exceptionIntrospection.printStackTrace();
        }
        return propDescriptors;
    }
    
    public static synchronized void setBeanProperties(Object aBean, Properties aProperties) throws Exception{
        PropertyDescriptor propDescriptor;
        Method writeMethod = null;
        Class type = null;
        String key = "";
        Object bean = aBean;
        Properties propertyDescriptors;
        
        propertyDescriptors = getBeanPropertyDescriptors(bean);
        try{
            Enumeration enumProps = propertyDescriptors.propertyNames();
            while(enumProps.hasMoreElements()){
                key = (String)enumProps.nextElement();
                propDescriptor = (PropertyDescriptor)propertyDescriptors.get(key);
                if (aProperties.containsKey(key)){
                    if ( (writeMethod = propDescriptor.getWriteMethod()) != null){
                        type = propDescriptor.getPropertyType();
                        if (type.isArray()){
                            String[] values = null;
                            Class t = type.getComponentType();
                            if (aProperties.get(key).getClass().isArray())
                                values = (String[])aProperties.get(key);
                            else
                                values = new String[]{(String)aProperties.get(key)};
                            if (values == null){
                                return;
                            }
                            writeMethod.invoke(bean, new Object[]{values});
                        }else if(type.isPrimitive()){
        		    Object oval = convert((String)aProperties.get(key), type);
                        	    if ( oval != null ){
                                        writeMethod.invoke(bean, new Object[] { oval });
                                    }
                        }else{
                        	Object[] obj = new Object[]{(String)aProperties.get(key)};
                        	if (obj != null){
                        		System.out.println("KEY: " + key + " VALUE:" + (String)aProperties.get(key));
                        		writeMethod.invoke(bean, obj);
                        	}
                        }
                    }
                }
            }
        }catch(Exception e){
        	System.out.println("Problem bei Key: " + key);
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    
    
    private static java.lang.reflect.Method getWriteMethod(Class beanClass, String prop)
    throws Exception {
	java.lang.reflect.Method method = null;	
        Class type = null;
	try {
	    java.beans.BeanInfo info
                = java.beans.Introspector.getBeanInfo(beanClass);
	    if ( info != null ) {
		java.beans.PropertyDescriptor pd[]
		    = info.getPropertyDescriptors();
		for (int i = 0 ; i < pd.length ; i++) {
		    if ( pd[i].getName().equals(prop) ) {
			method = pd[i].getWriteMethod();
			type   = pd[i].getPropertyType();
			break;
		    }
		}
            } else {        
                // just in case introspection silently fails.
                throw new Exception("jsp.error.beans.nobeaninfo");
            }
        } catch (Exception ex) {
            throw new Exception (ex);
        }
        if (method == null) {
            if (type == null) {
                throw new Exception("bla");
            } else {
                throw new Exception("jsp.error.beans.nomethod.setproperty");
            }
        }
        return method;
    }
    
   // __begin convertMethod
    public static Object convert(String s, Class t) throws Exception {
        try {
            if (s == null) {
                if (t.equals(Boolean.class) || t.equals(Boolean.TYPE))
                    s = "false";
                else
                    return null;
            }
    
            if ( t.equals(Boolean.class) || t.equals(Boolean.TYPE) ) {
                if (s.equalsIgnoreCase("on") || s.equalsIgnoreCase("true"))
                    s = "true";
                else
                    s = "false";
                return new Boolean(s);
            } else if ( t.equals(Byte.class) || t.equals(Byte.TYPE) ) {
                return new Byte(s);
            } else if (t.equals(Character.class) || t.equals(Character.TYPE)) {
                return s.length() > 0 ? new Character(s.charAt(0)) : null;
            } else if ( t.equals(Short.class) || t.equals(Short.TYPE) ) {
                return new Short(s);
            } else if ( t.equals(Integer.class) || t.equals(Integer.TYPE) ) {            	
                return s.length() > 0 ? new Integer(s) : null;
            } else if ( t.equals(Float.class) || t.equals(Float.TYPE) ) {
                return new Float(s);
            } else if ( t.equals(Long.class) || t.equals(Long.TYPE) ) {
                return new Long(s);
            } else if ( t.equals(Double.class) || t.equals(Double.TYPE) ) {
                return new Double(s);
            } else if ( t.equals(String.class) ) {
                return s;
            } else if ( t.equals(java.io.File.class) ) {
                return new java.io.File(s);
            }
        } catch (Exception ex) {
            throw new Exception (ex);
        }
        return s;
    }
    // __end convertMethod
}
