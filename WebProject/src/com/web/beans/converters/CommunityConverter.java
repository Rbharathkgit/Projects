/**
 * 
 */
package com.web.beans.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

import com.web.beans.DatapointPageBean;
import com.web.beans.TemplatePageBean;
import com.web.beans.WorkspacePageBean;
import com.web.domain.Community;
import com.web.utils.EDSWebUtils;

/**
 * @author author
 *
 */
@FacesConverter(value = "communityConverter")
public class CommunityConverter implements Converter {

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
		
		List<Community> communities = null;
		if(viewId.contains("workspace")){
			communities = ((WorkspacePageBean) EDSWebUtils.getBean("workspacePageBean")).getCommunities();
		}else if(viewId.contains("datapoint")){
			communities = ((DatapointPageBean) EDSWebUtils.getBean("datapointPageBean")).getCommunities();
		}else if(viewId.contains("template")){
			communities = ((TemplatePageBean) EDSWebUtils.getBean("templatePageBean")).getCommunities();
		}
		
		for(Community community: communities){
			if(community.getCommunityId().longValue() == id) {
                return community;
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
		else if(arg2=="")return null;
		return ((Community) arg2).getCommunityId().toString(); 
	}
	
}
