/**
 * 
 */
package com.web.utils;

import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.web.constants.SessionConstants;

/**
 * @author author
 *
 */
public class EDSWebUtils {

	private static Map<String, Object> getSessionMap(){
		return FacesContext.getCurrentInstance().getExternalContext().
				getSessionMap();
	}
	
	public static void setObjectinSession(Object obj,SessionConstants sessionkey){
		getSessionMap().put(sessionkey.toString(), obj);
	}
	
	public static Object getObjectFromSession(SessionConstants sessionkey){
		return getSessionMap().get(sessionkey.toString());
	}
	
	public static boolean removeObjectFromSession(SessionConstants sessionkey){
		
		if(getSessionMap().containsKey(sessionkey)){
			getSessionMap().remove(sessionkey.toString());
			return true;
		}
		return false;
	}
	
	public static void removeObjectsFromSession(List<SessionConstants> sessionkeys){
		Map<String,Object> sessionMap = getSessionMap();
		for(String sessionVals:sessionMap.keySet()){
			if(sessionkeys.contains(SessionConstants.valueOf(sessionVals)))
				sessionMap.remove(sessionVals);
		}
	}
	
	public static void removeAllFromSession(){
		Map<String,Object> sessionMap = getSessionMap();
		for(SessionConstants sessionVals:SessionConstants.values()){
			if(sessionMap.containsKey(sessionVals.toString()))
				sessionMap.remove(sessionVals);
		}
	}
	
	public static void addMessage(String message,Severity severity){
		FacesContext.getCurrentInstance().addMessage(severity.toString(), 
				new FacesMessage(severity,message,message));
	}

	public static void addSuccessMessage(String message){
		FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_INFO.toString(), 
				new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}
	
	public static void addErrorMessage(String message){
		FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,message,message));
	}
	
	public static boolean hasErrors() {
		return FacesContext.getCurrentInstance().
				getMessages(FacesMessage.SEVERITY_ERROR.toString()).hasNext();
	}
	
	public static Object getBean(String beanName){
	    Object bean = null;
	    FacesContext fc = FacesContext.getCurrentInstance();
	    if(fc!=null){
	         ELContext elContext = fc.getELContext();
	         bean = elContext.getELResolver().getValue(elContext, null, beanName);
	    }

	    return bean;
	}
}
