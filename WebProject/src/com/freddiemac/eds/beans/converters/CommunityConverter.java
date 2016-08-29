/**
 * 
 */
package com.freddiemac.eds.beans.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

import com.freddiemac.eds.beans.DatapointPageBean;
import com.freddiemac.eds.beans.TemplatePageBean;
import com.freddiemac.eds.beans.WorkspacePageBean;
import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.utils.EDSWebUtils;

/**
 * @author c38847
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
