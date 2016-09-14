/**
 * 
 */
package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.web.constants.SessionConstants;
import com.web.domain.Community;
import com.web.domain.Scenario;
import com.web.domain.Subscriber;
import com.web.domain.Workspace;
import com.web.utils.EDSWebUtils;

/**
 * @author author
 *
 */
@ManagedBean(name="scenarioPageBean")
@ViewScoped
public class ScenarioPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Community community = (Community) EDSWebUtils.getObjectFromSession(SessionConstants.COMMUNITY);
	private Subscriber subscriber = (Subscriber) EDSWebUtils.getObjectFromSession(SessionConstants.SUBSCRIBER);
	private Workspace workspace = (Workspace) EDSWebUtils.getObjectFromSession(SessionConstants.WORK_SPACE);
	
	private Scenario scenario = new Scenario();
	
	
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
	 * @return the workspace
	 */
	public Workspace getWorkspace() {
		return workspace;
	}
	/**
	 * @param workspace the workspace to set
	 */
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	/**
	 * @return the scenario
	 */
	public Scenario getScenario() {
		return scenario;
	}
	/**
	 * @param scenario the scenario to set
	 */
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	
}
