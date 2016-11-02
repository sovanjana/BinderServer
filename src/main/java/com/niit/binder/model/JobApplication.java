package com.niit.binder.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="B_JOBAPPLICATION")
@Component
public class JobApplication extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 867599936475521L;

	/**
	 *  declare the database column names for JobApplication... 
	 */
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_JOB_APP_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	private String userId;
	
	private int jobId;
	
	private String status;	// R = Reject, C = Call for Interview, S = Selected
	
	private String remarks;

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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
