package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Job;
import com.niit.binder.model.JobApplication;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface JobDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Job job);										//implemented..	
	
	public boolean update(Job job);										//implemented..	
	
	public Job get(int id);												//implemented..	
	
	public List<Job> list();											//implemented..	
	public List<Job> listVacantJobs();									//implemented..	
	
	public List<JobApplication> listJobApplications();
	
	public boolean applyForJob(JobApplication jobApplication);			//implemented..	
	public boolean updateJobApplication(JobApplication jobApplication);	//implemented..	
	
	public JobApplication get(String userId, String jobId);				//implemented..	
	public List<Job> getMyAppliedJobs(String userId);				//implemented..	
}
