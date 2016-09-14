/**
 * 
 */
package com.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;

import com.web.common.EDSCache;
import com.web.common.EDSCacheConstants;
import com.web.delegate.EDSdelegate;
import com.web.domain.Community;
import com.web.domain.DataPoint;
import com.web.domain.Subscriber;
import com.web.utils.EDSWebUtils;

/**
 * @author author
 *
 */
@ManagedBean(name="datapointPageBean")
@ViewScoped
public class DatapointPageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Community> communities;
	
	private List<Subscriber> subscribers;
	
	private boolean savedMode = false;
	
	private DataPoint datapoint = new DataPoint();

	{
		communities = (List<Community>) EDSCache.getCached(EDSCacheConstants.COMMUNITY).get("community");
	}
	
	public void communityChangeEvent(AjaxBehaviorEvent event) {
	    System.out.println(datapoint.getCommunity()); // Look, (new) value is already set.
	    subscribers = (List<Subscriber>) EDSCache
	    		.getCached(EDSCacheConstants.SUBSCRIBER).get(datapoint.getCommunity().getCommunityId());
	}
	
	public String saveDatapoint(){

		if(isValidDataPoint()){
			EDSdelegate edSdelegate = new EDSdelegate();
			Long wsId = edSdelegate.saveDatapoint(datapoint);
			setSavedMode(true);
			EDSWebUtils.addSuccessMessage("Datapoint "+datapoint.getDatapointName()+" is saved successfully.");
		}
		return null;
	}
	
	private boolean isValidDataPoint(){
		if(StringUtils.isBlank(datapoint.getDatapointName())){
			EDSWebUtils.addErrorMessage("Datapoint Name is Mandatory");
		}
		if(StringUtils.isBlank(datapoint.getDatapointXpath())){
			EDSWebUtils.addErrorMessage("Enter a valid Xpath");
		}
		if(StringUtils.isBlank(datapoint.getDataType())){
			EDSWebUtils.addErrorMessage("Select a valid datatype");
		}
		
		return !EDSWebUtils.hasErrors();
	}
	
	/**
	 * @return the communities
	 */
	public List<Community> getCommunities() {
		return communities;
	}

	/**
	 * @param communities the communities to set
	 */
	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	/**
	 * @return the subscribers
	 */
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	/**
	 * @param subscribers the subscribers to set
	 */
	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	/**
	 * @return the datapoint
	 */
	public DataPoint getDatapoint() {
		return datapoint;
	}

	/**
	 * @param datapoint the datapoint to set
	 */
	public void setDatapoint(DataPoint datapoint) {
		this.datapoint = datapoint;
	}

	/**
	 * @return the savedMode
	 */
	public boolean isSavedMode() {
		return savedMode;
	}

	/**
	 * @param savedMode the savedMode to set
	 */
	public void setSavedMode(boolean savedMode) {
		this.savedMode = savedMode;
	}
	
	
	
}
