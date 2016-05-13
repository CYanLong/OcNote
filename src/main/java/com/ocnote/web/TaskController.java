 package com.ocnote.web;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.domain.PrimaryCategory;
import com.ocnote.domain.SecondCategory;
import com.ocnote.domain.Task;
import com.ocnote.domain.User;
import com.ocnote.service.PrimaryCategoryService;
import com.ocnote.service.SecondCategoryService;
import com.ocnote.service.ServiceBeanFactory;
import com.ocnote.service.TaskService;
@Controller
@RequestMapping(value="tasks")
public class TaskController {

	private TaskService taskService = ServiceBeanFactory.getTaskService();
	
	private PrimaryCategoryService primaryCategoryService = ServiceBeanFactory.getPrimaryCategoryService();
	
	private SecondCategoryService secondCategoryService = ServiceBeanFactory.getSecondCategoryService();
	
//	-----------------面向tasks资源的操作----------------------
	
//	根据secondCategoryId得到Tasks
	@RequestMapping(value="/{sId}", method=RequestMethod.GET)
	@ResponseBody
	public List<Task> getTasksBySecondCategory(@PathVariable String sId){
		return taskService.getTasksBySecondCategory(sId);	
	}
	

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String addTask(@RequestParam(value="taskName") String taskName,
			@RequestParam(value="createDate") String createDateStr,
			@RequestParam(value="sId") String sId,
			HttpServletRequest request){
		
		Task task = new Task();
		task.setTaskName(taskName);
		task.setCreateDate(LocalDate.parse(createDateStr));
		SecondCategory sc = secondCategoryService.getSecondCategory(sId);
		task.setSecondCategory(sc);
		task.setUser(sc.getUser());
		task.setPrimaryCategory(sc.getPrimaryCategory());
		task.setIsComplation(false);
		taskService.saveOrUpdateTask(task);
		return task.getId();
	}
	
//	--------------------面向createTime资源集合的操作-----------------------
	@RequestMapping(value="{createTime}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTaskByCreateTime(@PathVariable(value="createTime") String createTime,String sId){							
		taskService.deleteTaskByCreateDate(LocalDate.parse(createTime),sId);
	}
	
	@RequestMapping(value="/{createTime}", method=RequestMethod.POST)
	@ResponseBody
	public String addTaskByCreateTime(@PathVariable(value="createTime") String createTime, 
			@RequestParam(value="sId") String sId, 
			@RequestParam(value="taskName") String taskName,
			HttpServletRequest request ){
		Task task = new Task();
		task.setTaskName(taskName);
		task.setCreateDate(LocalDate.parse(createTime));
		User u = (User)request.getSession().getAttribute("user");
		SecondCategory sc = secondCategoryService.getSecondCategory(sId);
		PrimaryCategory	pc = sc.getPrimaryCategory();
		task.setUser(u);
		task.setPrimaryCategory(pc);
		task.setSecondCategory(sc);
		taskService.saveOrUpdateTask(task);
		return task.getId();
	}
	
	
//	---------------面向单个task资源的操作.-----------------------------------------
	@RequestMapping(value="{createTime}/{taskId}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask(@PathVariable("taskId") String taskId){
		taskService.deleteTask(taskId);
	}
	
	@RequestMapping(value="{createTime}/{taskId}",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTask(@PathVariable("taskId") String taskId, String isComplation){	//isComplation
		taskService.updateTaskComplation(taskId, Boolean.parseBoolean(isComplation));
	}
	
	
	

//	------------------面向单个task的text资源的操作.------------------------------------------
	@RequestMapping(value="{createTime}/{taskId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTaskText(@PathVariable(value="taskId") String taskId, 
			@RequestBody String taskText){
		taskText = taskText.split("=")[1];
		taskService.updateTask(taskId, taskText);
	}
	
	@RequestMapping(value="{createTime}/{taskId}", method=RequestMethod.GET,
			produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getTaskText(HttpServletResponse response ,@PathVariable String taskId){
		System.out.println("invoke TaskText");
		return taskService.getTaskText(taskId);
		
	}
}
