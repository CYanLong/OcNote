package com.ocnote.domain;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ocnote.dao.LocalDateConverter;

@Entity
@Table(name="task")
@JsonIgnoreProperties({"user","primaryCategory","secondCategory"})
public class Task {
	
	private String id;
	private String taskName;
	private String taskText;
	@JsonSerialize(using=JsonDateSerializer.class)
	private LocalDate createDate;
	private boolean isComplation;
	private User user;
	private PrimaryCategory primaryCategory;
	private SecondCategory secondCategory;
	
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="task_name",nullable=false)
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name="task_text",nullable=true)
	public String getTaskText() {
		return taskText;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	@Column(name="create_date")
	@Convert(converter=LocalDateConverter.class)
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="is_complation")
	public boolean getIsComplation() {
		return isComplation;
	}
	
	public void setIsComplation(boolean isComplation) {
		this.isComplation = isComplation;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="primary_category_id")
	public PrimaryCategory getPrimaryCategory() {
		return primaryCategory;
	}
	public void setPrimaryCategory(PrimaryCategory primaryCategory) {
		this.primaryCategory = primaryCategory;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="second_category_id")
	public SecondCategory getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}
	
	public String toString(){
		return "id=" + id 
				+ ";taskName=" + taskName
				+ ";taskText=" + taskText
				+ ";createTime=" + createDate
				+ ";isComplation=" + isComplation;
	}
	
}
