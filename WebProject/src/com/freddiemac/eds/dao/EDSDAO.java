package com.freddiemac.eds.dao;

import java.util.List;

import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.domain.DataPoint;
import com.freddiemac.eds.domain.Subscriber;
import com.freddiemac.eds.domain.Template;
import com.freddiemac.eds.domain.Workspace;

public interface EDSDAO {

	public Long saveWorkspace(Workspace workspace);
	
	public Long saveDatapoint(DataPoint dataPoint);
	
	public Long saveTemplate(Template template);
	
	public Workspace getWorkspaceById(Long workspaceId);
	
	public List<Workspace> getWorkspacesBySubscriber(Subscriber subscriber);
	
	public boolean isWorkspaceNameUnique(String wsName);
	
	public List<Workspace> getAllWorkspaces();
	
	public List<Community> getAllCommunities();
	
	public List<Subscriber> getAllSubscribers();
	
}
