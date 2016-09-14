/**
 * 
 */
package com.web.domain;

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
 * @author author
 *
 */
@Entity
@Table
public class Subscriber implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="subsriber_name")
	private String subscriberName;
	
	@Column(name="subscriber_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long subscriberId;
	
	@ManyToOne
	@JoinColumn(name="community_id")
	private Community community;
	
	public Subscriber() {
		// TODO Auto-generated constructor stub
	}
	
	public Subscriber(String subscriberName, Long subscriberId,
			Community community) {
		super();
		this.subscriberName = subscriberName;
		this.subscriberId = subscriberId;
		this.community = community;
	}

	/**
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
	 * @param subscriberName the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
	 * @return the subscriberId
	 */
	public Long getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subscriber [subscriberName=" + subscriberName
				+ ", subscriberId=" + subscriberId + ", community=" + community
				+ "]";
	}

}
