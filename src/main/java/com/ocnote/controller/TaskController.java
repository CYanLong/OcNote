 package com.ocnote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.entity.Task;
import com.ocnote.entity.User;
import com.ocnote.mapper.SecondCategoryMapper;
import com.ocnote.mapper.TaskMapper;
import com.ocnote.service.TaskService;
@Controller
@RequestMapping(value="tasks")
public class TaskController {

	@Autowired
	private TaskService taskService ;
	
	@Autowired
	private SecondCategoryMapper scMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	
	
//	-----------------����tasks��Դ�Ĳ���----------------------
	
//	����secondCategoryId�õ�Tasks
	@RequestMapping(value="/{sId}", method=RequestMethod.GET)
	@ResponseBody
	public List<Task> getTasksBySecondCategory(@PathVariable String sId){
		return taskMapper.getTasksBySid(Integer.valueOf(sId));	
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String addTask(@RequestParam(value="taskName") String taskName,
			@RequestParam(value="createDate") String createDateStr,
			@RequestParam(value="sId") String sId,
			HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		
		Task task = new Task();
		task.setTaskName(taskName);
		task.setCreateDate(createDateStr);
		task.setIsComplation(false);
		task.setSid(Integer.valueOf(sId));
		task.setUser(user);
		//Ҫ����sid�õ�pid.
		int sid = scMapper.getPid(Integer.valueOf(sId));
		System.out.println("\n\n" + sid + "\n\n");
		
		task.setPid(scMapper.getPid(Integer.valueOf(sId)));
		
		taskService.addTask(task);
		return task.getId();
	}
	
//	--------------------����createTime��Դ���ϵĲ���-----------------------
	@RequestMapping(value="{createTime}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTaskByCreateTime(@PathVariable(value="createTime") String createTime,String sId){							
		taskService.deleteTaskByCreateDate(createTime, Integer.valueOf(sId));
	}
	
	@RequestMapping(value="/{createTime}", method=RequestMethod.POST)
	@ResponseBody
	public String addTaskByCreateTime(@PathVariable(value="createTime") String createTime, 
			@RequestParam(value="sId") String sId, 
			@RequestParam(value="taskName") String taskName,
			HttpServletRequest request ){
		
		Task task = new Task();
		task.setTaskName(taskName);
		task.setCreateDate(createTime);
		User u = (User)request.getSession().getAttribute("user");
		task.setUser(u);
		task.setSid(Integer.valueOf(sId));
		//Ҫ����sid�õ�pid
		task.setPid(scMapper.getPid(Integer.valueOf(sId)));
		taskService.addTask(task);
		return task.getId();
	}
	
	
//	---------------���򵥸�task��Դ�Ĳ���.-----------------------------------------
	
	@RequestMapping(value="{createTime}/{taskId}",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void setComp(@PathVariable("taskId") String taskId, String isComplation){	//isComplation
		taskService.updateTaskComplation(Integer.valueOf(taskId), Boolean.parseBoolean(isComplation));
	}
	
	@RequestMapping(value="{createTime}/{taskId}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask(@PathVariable("taskId") String taskId){
		taskService.deleteTask(Integer.valueOf(taskId));
	}
	
	
	

//	------------------���򵥸�task��text��Դ�Ĳ���.------------------------------------------
	@RequestMapping(value="{createTime}/{taskId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTaskText(@PathVariable(value="taskId") String taskId, 
			@RequestBody String taskText){
		taskText = taskText.split("=")[1];
		taskService.updateTask(Integer.valueOf(taskId), taskText);
	}
	
	@RequestMapping(value="{createTime}/{taskId}", method=RequestMethod.GET,
			produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getTaskText(@PathVariable String taskId){
		
		return taskMapper.getText(Integer.valueOf(taskId));
		
	}
}
