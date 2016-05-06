package com.ocnote.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User {
	
	private String id;
	private String username;
	private String password;
	private String token;
	private String email;
	
	private List<PrimaryCategory> primaryCategorys = new ArrayList<>();
	private List<SecondCategory> secondCategorys = new ArrayList<>();
	private List<Task> tasks = new ArrayList<>();
	
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="username",nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name="email",nullable=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//此处cascade=all,当save user对象时会自动save primary_category.
	//但primary_category的外键是否可以关联到对应的user上与这没有关系,只与在保存主控放时是否知道关联的信息.
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	public List<PrimaryCategory> getPrimaryCategorys() {
		return primaryCategorys;
	}
	public void setPrimaryCategorys(List<PrimaryCategory> primaryCategorys) {
		this.primaryCategorys = primaryCategorys;
	}
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}
	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}
	 
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	/*
	public User addPrimaryCategory(PrimaryCategory primaryCategory){
		this.primaryCategorys.add(primaryCategory);
		return this;
	}
	public User addSecondCategory(SecondCategory secondCategory){
		this.secondCategorys.add(secondCategory);
		return this;
	}*/
	
}
