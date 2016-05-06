package com.ocnote.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ocnote.dao.DaoBeanFactory;
import com.ocnote.dao.HibernateUtils;
import com.ocnote.dao.SecondCategoryDao;
import com.ocnote.domain.SecondCategory;

public class SecondCategoryService {

	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	private SecondCategoryDao secondCategoryDao = DaoBeanFactory.getSecondCategoryDao();
	
	public SecondCategory getSecondCategory(String secondCategoryId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		SecondCategory sc = secondCategoryDao.getSecondCategory(secondCategoryId);
		tx.commit();
		return sc;
	}
	
	public void deleteSecondCategory(String id){
		
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		secondCategoryDao.deleteSecondCategory(id);
		tx.commit();
	}
	
	
	public void saveOrUpdateSecondCategory(SecondCategory sc){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		secondCategoryDao.saveOrUpdateSecondCategory(sc);
		tx.commit();
	}
	
	
	
	public void deleteSecondCategory(SecondCategory sc){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		secondCategoryDao.deleteSecondCategory(sc);
		tx.commit();
	}
	
	
}
