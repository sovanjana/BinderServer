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
@Table(name = "B_JOB")
@Component
public class Job extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491280002140917397L;

	/**
	 *  declare the database column names for Job... 
	 */

	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_JOB_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	@Column(name = "company_name")
	private String companyName;
	
	private String location;
	
	private String description;
	
	@Column(name = "post_date")
	private Date date;
	
	private String status;
	
	private int noOfApplicants;
	
	/**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNoOfApplicants() {
		return noOfApplicants;
	}
	public void setNoOfApplicants(int noOfApplicants) {
		this.noOfApplicants = noOfApplicants;
	}
	
}
