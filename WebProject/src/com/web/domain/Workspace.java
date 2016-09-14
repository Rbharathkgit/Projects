/**
 * 
 */
package com.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author author
 *
 */
@Entity
@Table
@ManagedBean
public class Workspace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="community_id")
	private Community community;
	
	@ManyToOne
	@JoinColumn(name="subscriber_id")
	private Subscriber subscriber;
	
	@Column(name="workspace_name")
	private String workspaceName;

	@Column(name="workspace_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long workspaceId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="project_desc")
	private String projectDesc;
	
	@Column(name="impl_date")
	private Date implementationDate;
	
	public Workspace() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the community
	 */
	public Community getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(Community community) {
		this.community = community;
	}



	/**
	 * @return the subscriber
	 */
	public Subscriber getSubscriber() {
		return subscriber;
	}



	/**
	 * @param subscriber the subscriber to set
	 */
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}



	/**
	 * @return the workspaceName
	 */
	public String getWorkspaceName() {
		return workspaceName;
	}



	/**
	 * @param workspaceName the workspaceName to set
	 */
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}

	/**
	 * @return the workspaceId
	 */
	public Long getWorkspaceId() {
		return workspaceId;
	}

	/**
	 * @param workspaceId the workspaceId to set
	 */
	public void setWorkspaceId(Long workspaceId) {
		this.workspaceId = workspaceId;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectDesc
	 */
	public String getProjectDesc() {
		return projectDesc;
	}

	/**
	 * @param projectDesc the projectDesc to set
	 */
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	/**
	 * @return the implementationDate
	 */
	public Date getImplementationDate() {
		return implementationDate;
	}

	/**
	 * @param implementationDate the implementationDate to set
	 */
	public void setImplementationDate(Date implementationDate) {
		this.implementationDate = implementationDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Workspace [community=" + community + ", subscriber="
				+ subscriber + ", workspaceName=" + workspaceName
				+ ", workspaceId=" + workspaceId + ", projectName="
				+ projectName + ", projectDesc=" + projectDesc
				+ ", implementationDate=" + implementationDate + "]";
	}

}
