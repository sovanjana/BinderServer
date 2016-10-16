package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Blog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/* declare the database column names for User... */
	
	@Id
	private String id;
	
	@NotBlank
	@Length(min = 5, max = 100, message = "title should be understandable.")
	private String title;
	
	@NotBlank
	@Length(min = 50, max = 2000, message = "post should contain 50-2000 characters. ")
	private String content;
	
	@NotBlank
	private Date postDate;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	User user;
		
	@NotBlank
	private String status;
	
	
	
	public Blog() {
		this.id = "blog" + UUID.randomUUID().toString().substring(24).toUpperCase();
	}
	
	/* getters/setters for all the fields taken... */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
