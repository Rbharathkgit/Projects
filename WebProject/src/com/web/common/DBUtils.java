/**
 * 
 */
package com.web.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author author
 *
 */
public class DBUtils {

	private static Configuration configuration;
	
	private static Map<String,Configuration> cacheMap = new HashMap<String, Configuration>();
	
	private static final String CACHE_KEY =  "cfg.hibernate";
	
	private static SessionFactory factory = null;
	
	private static Session session = null;
	
	private static Transaction transaction = null;
	
	public DBUtils() {
		// TODO Auto-generated constructor stub
	}
	
	private static Configuration getConfigurationInstance(){
		
		if(null == cacheMap.get(CACHE_KEY)){
			configuration = new Configuration();
			
			System.out.println("Loading config file from resources ---");
			
			configuration.configure("/resources/hibernate.cfg.xml");
			
			System.out.println(configuration.getProperty("connection.username"));
			
			
			cacheMap.put(CACHE_KEY, configuration);
			
			cacheMap = Collections.synchronizedMap(cacheMap);
		}
		
		return cacheMap.get(CACHE_KEY);
	}
	
	public static Session getDBConnection(boolean isReadOnly){
		
		if(factory == null){
			factory = getConfigurationInstance().buildSessionFactory();
		}
		
		session = factory.openSession();
		
		if(!isReadOnly){
			transaction = session.beginTransaction();
		}else{
			transaction = null;
		}
		
		return session;
	}
	
	public static void closeDBConnection(){
		if(null != transaction){
			transaction.commit();
		}
		if(null!= session && session.isOpen()){
			session.close();
		}
	}
}
