/**
 * 
 */
package com.freddiemac.eds.delegate;

import java.util.List;

import com.freddiemac.eds.dao.EDSDAO;
import com.freddiemac.eds.dao.impl.EDSDAOImpl;
import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.domain.DataPoint;
import com.freddiemac.eds.domain.Subscriber;
import com.freddiemac.eds.domain.Template;
import com.freddiemac.eds.domain.Workspace;

/**
 * @author c38847
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
