package com.ocnote.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ocnote.dao.DaoBeanFactory;
import com.ocnote.dao.HibernateUtils;
import com.ocnote.dao.TaskDao;
import com.ocnote.domain.Task;

public class TaskService {

	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	private TaskDao taskDao = DaoBeanFactory.getTaskDao();
		
	public void deleteTask(String taskId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		taskDao.deleteTask(taskId);
		
		tx.commit();
	}
	
	public void updateTask(String taskId,String taskText){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		taskDao.updateTaskText(taskId, taskText);
		
		tx.commit();
	}
	
	public void updateTaskComplation(String taskId, boolean isComplation){
		
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		taskDao.updateTaskComplation(taskId, isComplation);
		tx.commit();
	}
	
	public List<Task> getTasksBySecondCategory(String sId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Task> result = taskDao.getTasksBySecondCategory(sId);
		tx.commit();
		return result;
	}
	
	public String getTaskText(String taskId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		String result = taskDao.getTaskText(taskId);
		tx.commit();
		return result;
	}
	
	public void deleteTaskByCreateDate(LocalDate createDate,String sId){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		taskDao.deleteTaskByCreateTime(createDate, sId);
		tx.commit();
	}
	
	
	
	public void saveOrUpdateTask(Task task){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		taskDao.saveOrUpdateTask(task);
		
		tx.commit();
	}
	
	public void deleteTask(Task task){
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		taskDao.deleteTask(task);
		
		tx.commit();
	}
	
	
}
