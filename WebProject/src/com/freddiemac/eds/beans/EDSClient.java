/**
 * 
 */
package com.freddiemac.eds.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author c38847
 *
 */
@ManagedBean
@ViewScoped
public class EDSClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientName;
	
	private String clientID;
	
	private List<WorkspaceBean> workspaceBeans;

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the clientID
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	/**
	 * @return the workspaceBeans
	 */
	public List<WorkspaceBean> getWorkspaceBeans() {
		return workspaceBeans;
	}

	/**
	 * @param workspaceBeans the workspaceBeans to set
	 */
	public void setWorkspaceBeans(List<WorkspaceBean> workspaceBeans) {
		this.workspaceBeans = workspaceBeans;
	}
	
}
