package com.cyl.ocnote.test.TaskTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ocnote.domain.Task;
import com.ocnote.service.ServiceBeanFactory;
import com.ocnote.service.TaskService;

public class TaskTest {
	
	TaskService service = ServiceBeanFactory.getTaskService();
	
	@Test
	public void testGetTaskTest(){
		String taskId = "123456";
		String result = service.getTaskText(taskId);
		System.out.println(result);
	}
	
	@Test
	public void testGetTaskBySecondCategory(){
		String sId = "123456a";
		List<Task> task = service.getTasksBySecondCategory(sId);
		System.out.println(task);
	}
	
	@Test
	public void testDate(){
		
		Date date = new Date("2014-06-24");
		System.out.println(date);
	}
	
	@Test
	public void testDeletTaskByDate(){
		LocalDate date = LocalDate.parse("2016-01-02");
		
		service.deleteTaskByCreateDate(date,"402881ea547aa36a01547aa4062a0001");
		
	}
	@Test
	public void testUpdateComplation(){
		
		service.updateTaskComplation("123456", Boolean.parseBoolean("true"));
	}
	
}
