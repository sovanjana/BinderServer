package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Job implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	/* declare the database column names for User... */

	@Id
	private String id;
	
	@NotBlank
	@Length(min = 3)
	private String companyName;
	
	@NotBlank
	private String location;
	
	@NotBlank
	@Length(min = 50)
	private String description;
	
	@Column(name = "post_date")
	private Date date;
	
	/* getters/setters for all the fields taken... */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

		
}
