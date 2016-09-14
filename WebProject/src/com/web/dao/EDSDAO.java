package com.web.dao;

import java.util.List;

import com.web.domain.Community;
import com.web.domain.DataPoint;
import com.web.domain.Subscriber;
import com.web.domain.Template;
import com.web.domain.Workspace;

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
