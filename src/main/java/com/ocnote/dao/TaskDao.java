package com.ocnote.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ocnote.domain.Task;

public class TaskDao {
	
	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	public void saveOrUpdateTask(Task task){
		Session session = sf.getCurrentSession();		
		session.saveOrUpdate(task);
	}
	
	public void updateTaskComplation(String taskId, boolean isComplation){
		
		String queryString = "update Task t set t.isComplation=:isComplation where t.id=:taskId";
		
		Session session = sf.getCurrentSession();
		session.createQuery(queryString)
				.setParameter("isComplation", isComplation)
				.setParameter("taskId", taskId)
				.executeUpdate();
	}
	
	public void deleteTask(String taskId){
		String queryString = "delete Task t where t.id =:taskId";
		Session session = sf.getCurrentSession();
		session.createQuery(queryString)
				.setParameter("taskId", taskId)
				.executeUpdate();
	}
	
	//删除指定sId的指定createDate的Task.
	public void deleteTaskByCreateTime(LocalDate createDate, String sId){
		
		Session session = sf.getCurrentSession();
		session.createQuery("delete Task t where t.secondCategory.id=:sId and t.createDate=:createTime")
				.setParameter("createTime", createDate)
				.setParameter("sId", sId)
				.executeUpdate();
		
	}
	
	public void updateTaskText(String taskId,String taskText){
		
		String queryString = "update Task t set t.taskText =:taskText where t.id=:id";
		
		Session session = sf.getCurrentSession();
		session.createQuery(queryString)
				.setParameter("taskText",taskText)
				.setParameter("id", taskId)
				.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getTasksBySecondCategory(String sId){
		
		String queryString = "from Task t where t.secondCategory.id=:sId";
		
		Session session = sf.getCurrentSession();
		
		return session.createQuery(queryString)
				.setParameter("sId", sId)
				.list();
		
	}
	
	public String getTaskText(String taskId){
		
		String queryString = "select taskText from Task t where t.id=:taskId";
		
		Session session = sf.getCurrentSession();
		
		return (String)session.createQuery(queryString)
								.setParameter("taskId", taskId)
								.uniqueResult();
	
	}
	
	public void deleteTask(Task task){
		Session session = sf.getCurrentSession();
		session.delete(task);
	}
	
	
	
	
	
}
