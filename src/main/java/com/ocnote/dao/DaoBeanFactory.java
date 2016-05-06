package com.ocnote.dao;

public class DaoBeanFactory {
	
	private static UserDao userDao =new UserDao();
	
	private static PrimaryCategoryDao primaryCategoryDao = new PrimaryCategoryDao();
	
	private static SecondCategoryDao secondCategoryDao = new SecondCategoryDao();
	
	private static TaskDao taskDao = new TaskDao();
	
	public static UserDao getUserDao(){
		return userDao;
	}
	
	public static PrimaryCategoryDao getPrimaryCategoryDao(){
		return primaryCategoryDao;
	}
	
	public static SecondCategoryDao getSecondCategoryDao(){
		return secondCategoryDao;
	}
	
	public static TaskDao getTaskDao(){
		return taskDao;
	}
}
