package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "b_forum")
@Component
public class Forum extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/**
	 *  declare the database column names for User... 
	 */
	
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FORUM_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	private String description;
	
	@Column(name = "post_date")
	private Date postDate;
	
	private String userId;
	
	/**
	 *  getters/setters for all the fields taken... 
	 */
	
	public Date getPostDate() {
		return postDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
