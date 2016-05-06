package com.ocnote.service;

public class ServiceBeanFactory {
	
	private static UserService userService = new UserService();
	
	private static PrimaryCategoryService primaryCategoryService = new PrimaryCategoryService();
	
	private static SecondCategoryService secondCategoryService = new SecondCategoryService();
	
	private static TaskService taskService = new TaskService();
	
	public static UserService getUserService(){
		return userService;
	}
	
	public static PrimaryCategoryService getPrimaryCategoryService(){
		return primaryCategoryService;
	}
	
	public static SecondCategoryService getSecondCategoryService(){
		return secondCategoryService;
	}
	
	public static TaskService getTaskService(){
		return taskService;
	}
}

