package com.niit.binder.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class FriendRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/* declare the database column names for User... */
	
	@Id
	private String id;
	
	@NotBlank
	private String requestTo;
	
	@NotBlank
	private String requestFrom;
	
	/* getters/setters for all the fields taken... */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	
	
}
