/**
 * 
 */
package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

/**
 * @author author
 *
 */
@ManagedBean(name="workspaceBean")
@ViewScoped
public class WorkspaceBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EDSClient client;
	
	private String workspaceName;
	
	private String templateName;
	
	private Part templateFile;
	
	public EDSClient getClient() {
		return client;
	}

	public void setClient(EDSClient client) {
		this.client = client;
	}

	public String getWorkspaceName() {
		return workspaceName;
	}

	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Part getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(Part templateFile) {
		this.templateFile = templateFile;
	}
	
}
