/**
 * 
 */
package com.freddiemac.eds.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.model.relational.Database;

import com.freddiemac.eds.dao.EDSDAO;
import com.freddiemac.eds.dao.impl.EDSDAOImpl;
import com.freddiemac.eds.domain.Community;
import com.freddiemac.eds.domain.Subscriber;
import com.freddiemac.eds.domain.Workspace;

/**
 * @author c38847
 *
 */
@WebListener
public class EDSCache implements ServletContextListener {

	
	private static Map<EDSCacheConstants,Map<?,?>> edsCache = new HashMap<EDSCacheConstants, Map<?,?>>(); 
	private static EDSDAO workspaceDAO = new EDSDAOImpl();
	
	public static Map<?,?> getCached(EDSCacheConstants key){
		return edsCache.get(key);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		edsCache.clear();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			resetDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session session = DBUtils.getDBConnection(true);
		updateWorkspaces();
		edsCache.put(EDSCacheConstants.COMMUNITY, new HashMap<String, List<Community>>(){
			{
				put("community",workspaceDAO.getAllCommunities());
			}
		});
		edsCache.put(EDSCacheConstants.SUBSCRIBER, new HashMap<Long, List<Subscriber>>(){
			{
				List<Subscriber> subscribers = workspaceDAO.getAllSubscribers();
				Long key = null;
				for(Subscriber subscriber:subscribers){
					key = subscriber.getCommunity().getCommunityId();
					if(!containsKey(key)){
						put(key,new ArrayList<Subscriber>());
					}
					get(key).add(subscriber);
				}
				
			}
		});
		DBUtils.closeDBConnection();
		
		
	}
	
	public static void updateCache(EDSCacheConstants key){
		switch (key) {
		case WORKSPACE:
			updateWorkspaces();
			break;

		default:
			break;
		}
	}
	
	private static void updateWorkspaces(){
		edsCache.put(EDSCacheConstants.WORKSPACE, new HashMap<String, List<Workspace>>(){
			{
				put("workspace",workspaceDAO.getAllWorkspaces());
			}
		});
	}
	
	private static void resetDatabase() throws SQLException
    {
        String s            = new String();
        StringBuffer sb = new StringBuffer();
        try
        {											 
            FileReader fr = new FileReader(new File("C:/Users/5307/git/Projects/WebProject/src/resources/Create_tables.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
            Session session = DBUtils.getDBConnection(false);
            Query q = null;
            
//            q = session.createSQLQuery("BEGIN " + sb.toString() + " END;"); 
//            q.executeUpdate();
//            System.out.println(">>"+sb.toString());
            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    q = session.createSQLQuery(inst[i]); 
                    q.executeUpdate();
                    System.out.println(">>"+inst[i]);
                }
            }
   
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
 
    }


}
