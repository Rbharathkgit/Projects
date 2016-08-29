/**
 * 
 */
package com.freddiemac.eds.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.freddiemac.eds.common.EDSCache;
import com.freddiemac.eds.common.EDSCacheConstants;
import com.freddiemac.eds.domain.Workspace;

/**
 * @author c38847
 *
 */
@ManagedBean(name="homePageBean")
@ViewScoped
public class HomePageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Workspace> workspaces = new ArrayList<Workspace>();
	{
		workspaces = (List<Workspace>) EDSCache.getCached(EDSCacheConstants.WORKSPACE).get("workspace");
		System.out.println("Home page Bean initiated == "+ workspaces.size());
	}
	
	/**
	 * @return the workspaces
	 */
	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	/**
	 * @param workspaces the workspaces to set
	 */
	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}

}
