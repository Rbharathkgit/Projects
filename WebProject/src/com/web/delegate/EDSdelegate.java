/**
 * 
 */
package com.web.delegate;

import java.util.List;

import com.web.dao.EDSDAO;
import com.web.dao.impl.EDSDAOImpl;
import com.web.domain.Community;
import com.web.domain.DataPoint;
import com.web.domain.Subscriber;
import com.web.domain.Template;
import com.web.domain.Workspace;

/**
 * @author author
 *
 */
public class EDSdelegate {
	
	EDSDAO dao = new EDSDAOImpl();
	
	public List<Community> getCommunityList() {
		
		return null;
	}
	
	public List<Subscriber> getSubscriberByCommunity(Community community) {
		
		return null;
	}
	
	public Long saveWorkspace(Workspace workspace) {
		return dao.saveWorkspace(workspace);
	}
	
	public Long saveDatapoint(DataPoint dataPoint) {
		return dao.saveDatapoint(dataPoint);
	}
	
	public Long saveTemplate(Template template) {
		return dao.saveTemplate(template);
	}
	
	public Workspace getWorkspaceById(Long workspaceId) {
		return dao.getWorkspaceById(workspaceId);
	}
	
	public boolean isWorkspaceNameUnique(String workspaceName){
		return dao.isWorkspaceNameUnique(workspaceName);
	}
	
}
