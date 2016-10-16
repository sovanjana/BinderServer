package com.niit.binder.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Friend implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/* declare the database column names for User... */
	
	@Id
	@Length(min = 6, max = 11, message = "Id should contain 6-11 characters")
	private String id;
	
	@NotBlank
	@Length(min = 3 , message = "Name contains atleast 3 character")
	private String name;
	
	private String group;
	
	/* getters/setters for all the fields taken... */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
}
