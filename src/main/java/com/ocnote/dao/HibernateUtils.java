package com.ocnote.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	public static final ThreadLocal<Session> session = new ThreadLocal<>();
	

	public static final SessionFactory sessionFactory;
	
	
	static{
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
	public static Session currentSession(){
		Session s = session.get();
		 
		if(s == null){
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession(){
		Session s = session.get();
		if( s!=null){
			s.close();
		}
		session.set(null);
	}
}
