package com.ocnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ocnote.domain.PrimaryCategory;

public class PrimaryCategoryDao {
	
	SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	
	//根据userId得到用户的PrimaryCategory(包括SecondCategory)
	@SuppressWarnings("unchecked")
	public List<PrimaryCategory> getPrimaryCategoryByUser(String userId){
		Session session  = sessionFactory.getCurrentSession();
		String queryString = "from PrimaryCategory p where p.user.id=:uId";
		return session.createQuery(queryString).setParameter("uId", userId).list();
	}
	
	//save PrimaryCategory
	public void save(PrimaryCategory pc){
		 //获取SessionFactory
        Session session = sessionFactory.getCurrentSession();
		session.save(pc);	
	}
	
	public void deletePrimaryCategory(String id){
		Session session = sessionFactory.getCurrentSession();
		String querySession = "delete PrimaryCategory p where p.id =:id";
		session.createQuery(querySession)
				.setParameter("id", id)
				.executeUpdate();
	}
	
	//
	public void saveOrUpdatePrimaryCategory(PrimaryCategory pc){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(pc);
	}

	
	public void deletePrimaryCategory(PrimaryCategory pc){
		Session session = sessionFactory.getCurrentSession();
		session.delete(pc);
	}
	
	public PrimaryCategory getPrimaryCategory(String primaryCategoryId){
		Session session = sessionFactory.getCurrentSession();
		return (PrimaryCategory)session.get(PrimaryCategory.class, primaryCategoryId);
	}
}
