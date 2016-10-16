package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
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
public class ForumComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/* declare the database column names for User... */
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="forumId")
	Forum forum;
	
	@NotBlank
	@Length(min = 5, max = 100)
	@Column(name = "forum_comment")
	private String comment;
	
	@NotBlank
	private String commentBy;
	
	@NotBlank
	private Date commentDate;
	
	public ForumComment() {
		this.id = "comment" + UUID.randomUUID().toString().substring(24).toUpperCase();
	}

	/* getters/setters for all the fields taken... */
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
	}
	
	
}
