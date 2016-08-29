/**
 * 
 */
package com.freddiemac.eds.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author c38847
 *
 */
@Entity
@Table
public class Scenario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="scenario_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String scenarioId;
	
	@Column(name="scenario_name")
	private String scenarioName;
	
	@Column(name="req_desc")
	private String reqDesc;
	
	@Column
	private boolean purchasable;
	
	@Column(name="template_Id")
	private String templateName;
	
	@Column(name="scenario_xml")
	private Blob scenarioXml;
	
	@Column
	private String message;

	/**
	 * @return the scenarioId
	 */
	public String getScenarioId() {
		return scenarioId;
	}

	/**
	 * @param scenarioId the scenarioId to set
	 */
	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	/**
	 * @return the scenarioName
	 */
	public String getScenarioName() {
		return scenarioName;
	}

	/**
	 * @param scenarioName the scenarioName to set
	 */
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	/**
	 * @return the purchasable
	 */
	public boolean isPurchasable() {
		return purchasable;
	}

	/**
	 * @param purchasable the purchasable to set
	 */
	public void setPurchasable(boolean purchasable) {
		this.purchasable = purchasable;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the scenarioXml
	 */
	public Blob getScenarioXml() {
		return scenarioXml;
	}

	/**
	 * @param scenarioXml the scenarioXml to set
	 */
	public void setScenarioXml(Blob scenarioXml) {
		this.scenarioXml = scenarioXml;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the reqDesc
	 */
	public String getReqDesc() {
		return reqDesc;
	}

	/**
	 * @param reqDesc the reqDesc to set
	 */
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	
	
}
