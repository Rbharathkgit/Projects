/**
 * 
 */
package com.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import com.web.common.EDSCache;
import com.web.common.EDSCacheConstants;
import com.web.delegate.EDSdelegate;
import com.web.domain.Community;
import com.web.domain.Subscriber;
import com.web.domain.Template;
import com.web.utils.EDSWebUtils;

/**
 * @author author
 *
 */
@ManagedBean(name="templatePageBean")
@ViewScoped
public class TemplatePageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Community> communities;
	
	private List<Subscriber> subscribers;
	
	private boolean savedMode = false;
	
	private Part templatefile;
	
	private Template template = new Template();

	{
		communities = (List<Community>) EDSCache.getCached(EDSCacheConstants.COMMUNITY).get("community");
	}
	
	public void communityChangeEvent(AjaxBehaviorEvent event) {
	    System.out.println(template.getCommunity()); // Look, (new) value is already set.
	    subscribers = (List<Subscriber>) EDSCache
	    		.getCached(EDSCacheConstants.SUBSCRIBER).get(template.getCommunity().getCommunityId());
	}
	
	public String saveDatapoint(){

		if(isValidTemplate()){
			EDSdelegate edSdelegate = new EDSdelegate();
			Long wsId = edSdelegate.saveTemplate(template);
			setSavedMode(true);
			EDSWebUtils.addSuccessMessage("Datapoint "+template.getTemplateName()+" is saved successfully.");
		}
		return null;
	}
	
	private boolean isValidTemplate(){
		if(StringUtils.isBlank(template.getTemplateName())){
			EDSWebUtils.addErrorMessage("Template Name is Mandatory");
		}
		if(templatefile == null){
			EDSWebUtils.addErrorMessage("Enter a valid template file");
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
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(Template template) {
		this.template = template;
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
