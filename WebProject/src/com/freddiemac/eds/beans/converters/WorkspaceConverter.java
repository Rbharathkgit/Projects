/**
 * 
 */
package com.freddiemac.eds.beans.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.freddiemac.eds.beans.HomePageBean;
import com.freddiemac.eds.domain.Workspace;
import com.freddiemac.eds.utils.EDSWebUtils;

/**
 * @author c38847
 *
 */
@FacesConverter(value = "wsConverter")
public class WorkspaceConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		
		HomePageBean bean = (HomePageBean) EDSWebUtils.getBean("homePageBean");
		for(Workspace ws: bean.getWorkspaces()){
			if(ws.getWorkspaceId() == Long.parseLong(arg2)) {
                return ws;
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
		return ((Workspace) arg2).getWorkspaceId().toString(); 
	}
	
}
