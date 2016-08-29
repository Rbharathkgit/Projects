/**
 * 
 */
package com.freddiemac.eds.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author c38847
 *
 */
@Entity
@Table
public class DataPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="datapoint_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long datapointId;
	
	@ManyToOne
	@JoinColumn(name="community_id")
	private Community community;
	
	@ManyToOne
	@JoinColumn(name="subscriber_Id")
	private Subscriber subscriber;
	
	@Column(name="datapoint_name")
	private String datapointName;

	@Column(name="xpath")
	private String datapointXpath;
	
	@Column(name="data_type")
	private String dataType;

	public String getDatapointName() {
		return datapointName;
	}

	public void setDatapointName(String datapointName) {
		this.datapointName = datapointName;
	}

	public String getDatapointXpath() {
		return datapointXpath;
	}

	public void setDatapointXpath(String datapointXpath) {
		this.datapointXpath = datapointXpath;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @param datapointId the datapointId to set
	 */
	public void setDatapointId(Long datapointId) {
		this.datapointId = datapointId;
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
	 * @return the datapointId
	 */
	public Long getDatapointId() {
		return datapointId;
	}

}
