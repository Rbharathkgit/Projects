/**
 * 
 */
package com.freddiemac.eds.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.freddiemac.eds.common.DBUtils;
import com.freddiemac.eds.dao.EDSDAO;
import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.domain.DataPoint;
import com.freddiemac.eds.domain.Subscriber;
import com.freddiemac.eds.domain.Template;
import com.freddiemac.eds.domain.Workspace;

/**
 * @author c38847
 *
 */
public class EDSDAOImpl implements EDSDAO {

	/* (non-Javadoc)
	 * @see com.freddiemac.eds.dao.WorkspaceDAO#saveWorkspace(com.freddiemac.eds.domain.Workspace)
	 */
	@Override
	public Long saveWorkspace(Workspace workspace) {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(false);
		session.saveOrUpdate(workspace);
		System.out.println(" workspaces == "+workspace);
		DBUtils.closeDBConnection();
		
		return workspace.getWorkspaceId();
	}
	
	@Override
	public Long saveDatapoint(DataPoint dataPoint) {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(false);
		session.saveOrUpdate(dataPoint);
		System.out.println(" dataPoint == "+dataPoint);
		DBUtils.closeDBConnection();
		
		return dataPoint.getDatapointId();
	}

	@Override
	public Long saveTemplate(Template template) {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(false);
		session.saveOrUpdate(template);
		System.out.println(" template == "+template);
		DBUtils.closeDBConnection();
		
		return template.getTemplateId();
	}

	/* (non-Javadoc)
	 * @see com.freddiemac.eds.dao.WorkspaceDAO#getWorkspaceById(java.lang.Long)
	 */
	@Override
	public Workspace getWorkspaceById(Long workspaceId) {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(true);
		
		Workspace workspace = session.get(Workspace.class, workspaceId);
		
		System.out.println(" workspaces == "+workspace);
		
		DBUtils.closeDBConnection();
		
		return workspace;
	}

	/* (non-Javadoc)
	 * @see com.freddiemac.eds.dao.WorkspaceDAO#getWorkspacesBySubscriber(com.freddiemac.eds.domain.Subscriber)
	 */
	@Override
	public List<Workspace> getWorkspacesBySubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		
		Session session = DBUtils.getDBConnection(true);
		Criteria criteria = session.createCriteria(Workspace.class);
		
		criteria.createAlias("subsciber", "subsciber");
		
		Criterion subscriberCriterion = Restrictions.eq("subsciber.subscriberId", subscriber.getSubscriberId());
		criteria.add(subscriberCriterion);
		
		List<Workspace> workspaces = criteria.list();
		
		System.out.println(" workspaces == "+workspaces.size());
		
		DBUtils.closeDBConnection();
		
		return workspaces;
	}

	@Override
	public List<Workspace> getAllWorkspaces() {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(true);
		Criteria criteria = session.createCriteria(Workspace.class);
		
		List<Workspace> workspaces = criteria.list();
		System.out.println(" workspaces == "+workspaces.size());
		
		DBUtils.closeDBConnection();
		
		return workspaces;
	}

	@Override
	public List<Community> getAllCommunities() {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(true);
		Criteria criteria = session.createCriteria(Community.class);

		List<Community> communities = criteria.list();
		System.out.println(" communities == " + communities.size());

		DBUtils.closeDBConnection();

		return communities;
	}

	@Override
	public List<Subscriber> getAllSubscribers() {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(true);
		Criteria criteria = session.createCriteria(Subscriber.class);

		List<Subscriber> subscribers = criteria.list();
		System.out.println(" subscribers == " + subscribers.size());

		DBUtils.closeDBConnection();

		return subscribers;
	}

	@Override
	public boolean isWorkspaceNameUnique(String wsName) {
		// TODO Auto-generated method stub
		Session session = DBUtils.getDBConnection(true);
		
		List<Workspace> workspaces =  session.createCriteria(Workspace.class)
			    .add( Restrictions.eq("workspaceName", wsName).ignoreCase())
			    .list();
		
		System.out.println(" workspaces == "+workspaces);
		
		DBUtils.closeDBConnection();
		
		return (workspaces==null||workspaces.isEmpty());
	}

}
