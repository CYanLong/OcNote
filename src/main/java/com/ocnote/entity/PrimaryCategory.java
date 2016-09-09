package com.ocnote.entity;

import java.util.ArrayList;
import java.util.List;


public class PrimaryCategory {
	
	private int id;
	private String categoryName;
	private String createTime;
	
	private User user;
	private List<SecondCategory> secondCategorys = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}
	
	public String toString(){
		return "id=" + id + 
				";categoryName=" + categoryName + 
				";createTime" + createTime +
				";user=[" + user +"]"+
				";secondCategorys=[" + secondCategorys + "]";
	}
	
}
