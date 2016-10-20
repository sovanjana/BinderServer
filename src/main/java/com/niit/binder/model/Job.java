package com.niit.binder.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "b_job")
@Component
public class Job extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	/* declare the database column names for User... */

	@Id
	private String id;
	
	@Column(name = "company_name")
	private String companyName;
	
	private String location;
	
	private String description;
	
	@Column(name = "post_date")
	private Timestamp date;
	
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

		
}
