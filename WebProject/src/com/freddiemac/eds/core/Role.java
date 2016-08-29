/**
 * 
 */
package com.freddiemac.eds.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author c38847
 *
 */
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rollName;
	
	private List<Persmission> permissions;

	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public List<Persmission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Persmission> permissions) {
		this.permissions = permissions;
	}
}
