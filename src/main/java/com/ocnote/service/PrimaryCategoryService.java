package com.ocnote.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ocnote.dao.DaoBeanFactory;
import com.ocnote.dao.HibernateUtils;
import com.ocnote.dao.PrimaryCategoryDao;
import com.ocnote.domain.PrimaryCategory;

public class PrimaryCategoryService {
	
	private PrimaryCategoryDao primaryCategoryDao = DaoBeanFactory.getPrimaryCategoryDao();
	
	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	public List<PrimaryCategory> getPrimaryCategoryByUser(String userId){
		Session session = sf.getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		
		List<PrimaryCategory> result = primaryCategoryDao.getPrimaryCategoryByUser(userId);
		
		tx.commit();
		return result;
	}
	
	public void deletePrimaryCategory(String id){
		
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		primaryCategoryDao.deletePrimaryCategory(id);

		tx.commit();
	}

	public void saveOrUpdatePrimaryCategory(PrimaryCategory pc){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		primaryCategoryDao.saveOrUpdatePrimaryCategory(pc);
		
		tx.commit();
	}
	
	
	
	public void deletePrimaryCategory(PrimaryCategory pc){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		primaryCategoryDao.deletePrimaryCategory(pc);
		
		tx.commit();
	}
	
	public PrimaryCategory getPrimaryCategory(String primaryCategoryId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		PrimaryCategory pc = primaryCategoryDao.getPrimaryCategory(primaryCategoryId);
		tx.commit();
		return pc;
	}
}
