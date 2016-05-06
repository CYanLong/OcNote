package com.ocnote.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ocnote.domain.SecondCategory;

public class SecondCategoryDao {
	
	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	public void saveOrUpdateSecondCategory(SecondCategory sc){
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(sc);
	}
	
	public void deleteSecondCategory(String id){
		
		Session session = sf.getCurrentSession();
		String queryString = "delete SecondCategory s where s.id=:id";
		session.createQuery(queryString )
				.setParameter("id", id)
				.executeUpdate();
	}
	
	
	
	
	public void deleteSecondCategory(SecondCategory sc){
		Session session = sf.getCurrentSession();
		session.delete(sc);
	}

	public SecondCategory getSecondCategory(String secondCategoryId) {
		Session session = sf.getCurrentSession();
		return (SecondCategory)session.get(SecondCategory.class, secondCategoryId);
	}
	
}
