package com.ocnote.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocnote.dao.HibernateUtils;
import com.ocnote.dao.UserDao;
import com.ocnote.domain.PrimaryCategory;
import com.ocnote.domain.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	public void register(User user){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//初始化一个默认分类.
		PrimaryCategory pc = new PrimaryCategory();
		pc.setCategoryName("默认分类");
		pc.setCreateTime(new Date());
		
		pc.setUser(user);
		user.getPrimaryCategorys().add(pc);
		
		userDao.save(user);//会级联保存.

		tx.commit();
		
	}
	
	//验证用户名是否重复,若未重复,返回true.
	//适用于注册.
	public boolean verifyUserName(String username){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		boolean result = userDao.verifyUserName(username);
		tx.commit();
		return result;
	}
	
	public boolean verifyEmail(String email){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		boolean result = userDao.verifyEmail(email);
		tx.commit();
		return result;
	}

	public User loginByUsername(String username, String password) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		User u = userDao.loginByUsername(username,password);
		tx.commit();
		return u;
	}
	
	public User loginByEmail(String email, String password) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		User u = userDao.loginByEmail(email,password);
		tx.commit();
		return u;
	}
}
