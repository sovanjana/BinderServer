/*package com.niit.binder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.JobDAO;
import com.niit.binder.model.Job;

@RestController
public class JobController {
	
	@Autowired
	JobDAO jobDAO;
	
	//	http://localhost:8081/BinderServer/jobs
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> listJobs() {
		List<Job> job = jobDAO.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/job/
	@RequestMapping(value = "/job/", method = RequestMethod.POST)
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		if(jobDAO.get(job.getId()) == null) {
			jobDAO.save(job);
			return new ResponseEntity<Job>(HttpStatus.OK);
		}
		job.setErrorMessage("Job already exist with id : " +job.getId());
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/job/{id}
	@RequestMapping(value = "/job/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Job> updateJob(@PathVariable("id") String id, @RequestBody Job job) {
		if(jobDAO.get(id) == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " +job.getId());
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.update(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/job/{id}
	@RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Job> deleteJob(@PathVariable("id") String id) {
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.delete(job);
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	
	//	http://localhost:8081/BinderServer/job/{id}
	@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
	public ResponseEntity<Job> getJob(@PathVariable("id") String id) {
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
}
*/