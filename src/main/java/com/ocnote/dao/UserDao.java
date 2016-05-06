package com.ocnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ocnote.domain.User;

public class UserDao {
	
	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	public void save(User user) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		session.save(user);
	}
	
	public User loginByUsername(String username, String password) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String queryString = "from User u where u.username=:username and u.password=:password";
		User u = (User)session.createQuery(queryString)
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
		return u;
	}
	
	public User loginByEmail(String email, String password){
		
		Session session = sf.getCurrentSession();
		String queryString = "from User u where u.email=:email and u.password=:password";
		return (User)session.createQuery(queryString)
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
	}
	
	public boolean verifyUserName(String username){
		
		Session session = sf.getCurrentSession();
		String queryString = "from User u where u.username=:username";
		Object result = session.createQuery(queryString)
				.setParameter("username", username)
				.uniqueResult();
		return result == null ? true : false;
	}
	
	public boolean verifyEmail(String email){
		
		Session session = sf.getCurrentSession();
		String queryString = "from User u where u.email=:email";
		Object result = session.createQuery(queryString)
				.setParameter("email", email)
				.uniqueResult();
		return result == null ? true : false;
	}
	
	
	
	

	public List<?> queryUserName(String username) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String queryString = "select u.username from User u where u.username=:username";
		List<?> lists = session.createQuery(queryString).setParameter("username", username).list();
		return lists;
	}

	
	
}
