package com.ocnote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"user","primaryCategory","secondCategory"})
public class Task {
	
	private String id;
	private String taskName;
	private String taskText;
	private String createDate;
	private boolean isComplation;
	private User user;
	private int pid;
	private int sid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskText() {
		return taskText;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public boolean getIsComplation() {
		return isComplation;
	}
	
	public void setIsComplation(boolean isComplation) {
		this.isComplation = isComplation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setComplation(boolean isComplation) {
		this.isComplation = isComplation;
	}
	public String toString(){
		return "id=" + id 
				+ ";taskName=" + taskName
				+ ";taskText=" + taskText
				+ ";createTime=" + createDate
				+ ";isComplation=" + isComplation;
	}
	
}
