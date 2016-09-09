package com.ocnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocnote.entity.Task;
import com.ocnote.mapper.TaskMapper;

@Service
public class TaskService {

	@Autowired
	private TaskMapper taskMapper;
	
	
	@Transactional
	public void addTask(Task task) {
		taskMapper.addTask(task);
	}

	@Transactional
	public void deleteTaskByCreateDate(String date, int sid) {
		taskMapper.deleteByDate(date, sid);
	}

	@Transactional
	public void updateTaskComplation(int id, boolean isComp) {
		taskMapper.updateComp(id, isComp);
	}

	@Transactional
	public void deleteTask(int taskId) {
		taskMapper.deleteById(taskId);
	}

	@Transactional
	public void updateTask(int taskId, String taskText) {
		taskMapper.updateTask(taskId, taskText);
	}

}
