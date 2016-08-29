/**
 * 
 */
package com.freddiemac.eds.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.apache.commons.lang3.StringUtils;

import com.freddiemac.eds.common.EDSCache;
import com.freddiemac.eds.common.EDSCacheConstants;
import com.freddiemac.eds.constants.SessionConstants;
import com.freddiemac.eds.delegate.EDSdelegate;
import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.domain.Subscriber;
import com.freddiemac.eds.domain.Workspace;
import com.freddiemac.eds.utils.EDSWebUtils;

/**
 * @author c38847
 *
 */
@ManagedBean(name="workspacePageBean")
@SessionScoped
public class WorkspacePageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Community> communities;
	
	private List<Subscriber> subscribers;
	
	private Workspace workspace;
	
	private boolean newws;
	private boolean createScenario;
	private boolean saved;
	private boolean wsUnique;
	
	{
		communities = (List<Community>) EDSCache.getCached(EDSCacheConstants.COMMUNITY).get("community");
	}
	
	public void updateSubscribers(AjaxBehaviorListener listener){
		System.out.println("AjaxBehaviorListener");
		subscribers.clear();
	}
	
	public void communityChangeEvent(AjaxBehaviorEvent event) {
	    System.out.println(workspace.getCommunity()); // Look, (new) value is already set.
	    subscribers = (List<Subscriber>) EDSCache
	    		.getCached(EDSCacheConstants.SUBSCRIBER).get(workspace.getCommunity().getCommunityId());
	}
	
	public String openWorkspace(){
		System.out.println(workspace.getWorkspaceId());
		System.out.println(workspace);
		if(null==workspace.getCommunity() || null == workspace.getSubscriber()){
			setNewws(false);
			setSaved(false);
			setWsUnique(true);
			return "workspace";
		}else{
			
			EDSWebUtils.setObjectinSession(workspace.getCommunity().getCommunityName(), SessionConstants.COMMUNITY_NAME);
			EDSWebUtils.setObjectinSession(workspace.getSubscriber().getSubscriberName(), SessionConstants.SUBSCRIBER_NAME);
			EDSWebUtils.setObjectinSession(workspace.getWorkspaceName(), SessionConstants.WORK_SPACE_NAME);
			EDSWebUtils.setObjectinSession(workspace.getCommunity(), SessionConstants.COMMUNITY);
			EDSWebUtils.setObjectinSession(workspace.getSubscriber(), SessionConstants.SUBSCRIBER);
			EDSWebUtils.setObjectinSession(workspace, SessionConstants.WORK_SPACE);
			
			return "scenario";
		}
	}
	
	public String createNewWorkspace(){
		workspace = new Workspace();
		setNewws(true);
		setSaved(false);
		setWsUnique(true);
		return "workspace";
	}
	
	public String editWorkspace(){
		setSaved(false);
		setNewws(false);
		return null;
	}
	
	public String saveWorkspace(){
		
		if(isValidWorkspace()){
			EDSdelegate edSdelegate = new EDSdelegate();
			Long wsId = edSdelegate.saveWorkspace(workspace);
			EDSCache.updateCache(EDSCacheConstants.WORKSPACE);
			if(isNewws()){
				workspace.setWorkspaceId(wsId);
			}
			setSaved(true);
			
			EDSWebUtils.addSuccessMessage("Workspace "+workspace.getWorkspaceName()+" is saved successfully.");
			if(workspace.getCommunity()==null || workspace.getSubscriber()==null){
				EDSWebUtils.addMessage("Associate community and subscriber to start creating scenarios.", FacesMessage.SEVERITY_WARN);
			}else if(workspace.getCommunity() !=null && workspace.getSubscriber() != null){
				EDSWebUtils.setObjectinSession(workspace.getCommunity().getCommunityName(), SessionConstants.COMMUNITY_NAME);
				EDSWebUtils.setObjectinSession(workspace.getSubscriber().getSubscriberName(), SessionConstants.SUBSCRIBER_NAME);
				EDSWebUtils.setObjectinSession(workspace.getWorkspaceName(), SessionConstants.WORK_SPACE_NAME);
				EDSWebUtils.setObjectinSession(workspace.getCommunity(), SessionConstants.COMMUNITY);
				EDSWebUtils.setObjectinSession(workspace.getSubscriber(), SessionConstants.SUBSCRIBER);
				EDSWebUtils.setObjectinSession(workspace, SessionConstants.WORK_SPACE);
			}
			
			return null;
		}
		
		
		return null;
	}
	
	public String saveWorkspaceForScenario(){
		
		if(isValidWorkspace()){
			EDSdelegate edSdelegate = new EDSdelegate();
			Long wsId = edSdelegate.saveWorkspace(workspace);
			EDSCache.updateCache(EDSCacheConstants.WORKSPACE);
			if(isNewws()){
				workspace.setWorkspaceId(wsId);
			}
			
			EDSWebUtils.setObjectinSession(workspace.getCommunity().getCommunityName(), SessionConstants.COMMUNITY_NAME);
			EDSWebUtils.setObjectinSession(workspace.getSubscriber().getSubscriberName(), SessionConstants.SUBSCRIBER_NAME);
			EDSWebUtils.setObjectinSession(workspace.getWorkspaceName(), SessionConstants.WORK_SPACE_NAME);
			EDSWebUtils.setObjectinSession(workspace.getCommunity(), SessionConstants.COMMUNITY);
			EDSWebUtils.setObjectinSession(workspace.getSubscriber(), SessionConstants.SUBSCRIBER);
			EDSWebUtils.setObjectinSession(workspace, SessionConstants.WORK_SPACE);
			
			setSaved(true);
			return "scenario";
		}
		
		
		return null;
	}
	
	public void workspaceNameCheck(AjaxBehaviorEvent event) {
		setWsUnique(new EDSdelegate().isWorkspaceNameUnique(workspace.getWorkspaceName()));
	}
	
	private boolean isValidWorkspace(){
		if(StringUtils.isBlank(workspace.getWorkspaceName())){
			EDSWebUtils.addErrorMessage("Workspace Name is Mandatory");
		}else if(!isWsUnique()){
			EDSWebUtils.addErrorMessage("Workspace Name already available. Select different one.");
		}
		if(StringUtils.isBlank(workspace.getProjectName())){
			EDSWebUtils.addErrorMessage("Project Name is Mandatory");
		}
		if(StringUtils.isBlank(workspace.getProjectDesc())){
			EDSWebUtils.addErrorMessage("Project Desc is Mandatory");
		}
		if(!isNewws()){
			if(null == workspace.getCommunity()){
				EDSWebUtils.addErrorMessage("Select a Community");
			}
			if(null == workspace.getSubscriber()){
				EDSWebUtils.addErrorMessage("Select a Subscrier");
			}
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
	 * @return the newws
	 */
	public boolean isNewws() {
		return newws;
	}

	/**
	 * @param newws the newws to set
	 */
	public void setNewws(boolean newws) {
		this.newws = newws;
	}

	/**
	 * @return the createScenario
	 */
	public boolean isCreateScenario() {
		return createScenario;
	}

	/**
	 * @param createScenario the createScenario to set
	 */
	public void setCreateScenario(boolean createScenario) {
		this.createScenario = createScenario;
	}

	/**
	 * @return the wsUnique
	 */
	public boolean isWsUnique() {
		return wsUnique;
	}

	/**
	 * @param wsUnique the wsUnique to set
	 */
	public void setWsUnique(boolean wsUnique) {
		this.wsUnique = wsUnique;
	}

	/**
	 * @return the saved
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

}
