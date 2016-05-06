package com.ocnote.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="primary_category")
@JsonIgnoreProperties({"user","createTime"})
public class PrimaryCategory {
	
	private String id;
	private String categoryName;
	private Date createTime;
	
	private User user;
	private List<SecondCategory> secondCategorys = new ArrayList<>();
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name="category_name",nullable=false)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy="primaryCategory",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
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
