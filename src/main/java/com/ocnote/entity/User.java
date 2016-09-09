package com.ocnote.entity;

import java.util.ArrayList;
import java.util.List;


public class User {
	
	private int id;
	private String username;
	private String password;
	private String token;
	private String email;
	
	private List<PrimaryCategory> primaryCategorys = new ArrayList<>();
	private List<SecondCategory> secondCategorys = new ArrayList<>();
	private List<Task> tasks = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public User setId(int id) {
		this.id = id;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getToken() {
		return token;
	}
	public User setToken(String token) {
		this.token = token;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	public List<PrimaryCategory> getPrimaryCategorys() {
		return primaryCategorys;
	}
	public void setPrimaryCategorys(List<PrimaryCategory> primaryCategorys) {
		this.primaryCategorys = primaryCategorys;
	}
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}
	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}
	 
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public String toString(){
		return this.id + "";
	}
	
}
