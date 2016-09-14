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
import javax.persistence.Table;

/**
 * @author author
 *
 */
@Entity
@Table
public class Community implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="community_name")
	private String communityName;
	
	@Column(name="community_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long communityId;

	public Community() {
		// TODO Auto-generated constructor stub
	}
	
	public Community(Long communityId,String communityName) {
		// TODO Auto-generated constructor stub
		this.communityId = communityId;
		this.communityName = communityName;
	}
	
	/**
	 * @return the communityName
	 */
	public String getCommunityName() {
		return communityName;
	}

	/**
	 * @param communityName the communityName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	/**
	 * @return the communityId
	 */
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * @param communityId the communityId to set
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Community [communityName=" + communityName + ", communityId="
				+ communityId + "]";
	}

}
