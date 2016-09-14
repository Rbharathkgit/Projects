/**
 * 
 */
package com.web.beans.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.web.beans.DatapointPageBean;
import com.web.beans.TemplatePageBean;
import com.web.beans.WorkspacePageBean;
import com.web.domain.Community;
import com.web.domain.Subscriber;
import com.web.utils.EDSWebUtils;

/**
 * @author author
 *
 */
@FacesConverter(value = "subscriberConverter")
public class SubscriberConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		String viewId = arg0.getViewRoot().getViewId();
		long id = 0;
		try{
			id = Long.parseLong(arg2);
		}catch(Exception e){
			return null;
		}
		
		List<Subscriber> subscribers = null;
		if(viewId.contains("workspace")){
			subscribers = ((WorkspacePageBean) EDSWebUtils.getBean("workspacePageBean")).getSubscribers();
		}else if(viewId.contains("datapoint")){
			subscribers = ((DatapointPageBean) EDSWebUtils.getBean("datapointPageBean")).getSubscribers();
		}else if(viewId.contains("template")){
			subscribers = ((TemplatePageBean) EDSWebUtils.getBean("templatePageBean")).getSubscribers();
		}
		
		for(Subscriber subscriber: subscribers){
			if(subscriber.getSubscriberId().longValue() == id) {
                return subscriber;
            }
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if(arg2==null)return null;
		if(arg2=="")return null;
		return ((Subscriber) arg2).getSubscriberId().toString(); 
	}
	
}
