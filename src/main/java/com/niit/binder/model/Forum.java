package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Forum implements Serializable{

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
	
	private Date postDate;
	
	private long replies;
	
	private String lastPost;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	User user;
	
	@OneToMany(mappedBy="forum", fetch=FetchType.EAGER)
	Set<ForumComment> forumComment;
	
	public Forum() {
		this.id = "forum" + UUID.randomUUID().toString().substring(24).toUpperCase();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ForumComment> getForumComment() {
		return forumComment;
	}

	public void setForumComment(Set<ForumComment> forumComment) {
		this.forumComment = forumComment;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public long getReplies() {
		return replies;
	}

	public void setReplies(long replies) {
		this.replies = replies;
	}

	public String getLastPost() {
		return lastPost;
	}

	public void setLastPost(String lastPost) {
		this.lastPost = lastPost;
	}
}
