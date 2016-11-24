package com.niit.binder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.JobDAO;
import com.niit.binder.model.Job;
import com.niit.binder.model.JobApplication;
import com.niit.binder.model.Users;

@RestController
public class JobController {

	Logger log = Logger.getLogger(JobController.class);

	@Autowired
	Job job;

	@Autowired
	JobApplication jobApplication;

	@Autowired
	JobDAO jobDAO;

	/**
	 * http://localhost:8081/Binder/jobs 					//working
	 * 
	 * @return
	 */
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		log.debug("**********Starting of listJobs() method.");
		List<Job> jobs = jobDAO.list();
		if (jobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listJobs() method.");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/jobApplications")
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		log.debug("**********Starting of listJobApplications() method.");
		
		List<JobApplication> jobApplications = jobDAO.listJobApplications();
		
		log.debug("**********End of listJobApplications() method.");
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/job/ 					//working
	 * 
	 * @param job
	 * @return
	 */
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		log.debug("**********Starting of createJob() method.");
		if (jobDAO.get(job.getId()) == null) {
			jobDAO.save(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		log.error("No job exist with id : " + job.getId());
		job.setErrorMessage("Job already exist with id : " + job.getId());
		log.debug("**********End of createJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/job/{id} 					//working
	 * 
	 * @param id
	 * @param job
	 * @return
	 */
	@PutMapping(value = "/job/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		log.debug("**********Starting of updateJob() method.");
		if (jobDAO.get(id) == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + job.getId());
			log.error("No job exist with id : " + job.getId());
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.update(job);
		log.debug("**********End of updateJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/job/{id} 					//working
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/job/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		log.debug("**********Starting of getJob() method.");
		Job job = jobDAO.get(id);
		if (job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/getMyAppliedJobs
	 * 
	 * @param httpSession
	 * @return
	 */
	@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		Users loggedInUser = (Users) httpSession.getAttribute("loggedInUser");
		String loggedInUserId = loggedInUser.getId();
		
		@SuppressWarnings("unchecked")
		List<Job> jobs = (List<Job>) jobDAO.getMyAppliedJobs(loggedInUserId);
		log.debug("**********End of getMyAppliedJobs() method.");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/callForInterview/{userId}/{jobId}
	 * @param userId
	 * @param jobId
	 * @param jobApplication
	 * @return
	 */
	@PutMapping(value = "/callForInterview/{userId}/{jobId}")
	public ResponseEntity<Job> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of callForInterview() method.");
		jobApplication = jobDAO.get(userId, jobId);
		jobApplication.setStatus("C");
		if (jobDAO.updateJobApplication(jobApplication) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change job application status 'call for interview'...");
			log.error("Not able to change job application status 'call for interview'...");
		}
		log.debug("**********End of callForInterview() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/rejectJobApplication/{userId}/{jobId}
	 * @param userId
	 * @param jobId
	 * @param jobApplication
	 * @return
	 */
	@PutMapping(value = "/rejectJobApplication/{userId}/{jobId}")
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of rejectJobApplication() method.");
		jobApplication = jobDAO.get(userId, jobId);
		jobApplication.setStatus("R");
		if (jobDAO.updateJobApplication(jobApplication) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to reject the application...");
			log.error("Not able to reject the application...");
		}
		log.debug("**********End of rejectJobApplication() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/listVacantJobs //working
	 * 
	 * @return
	 */
	@GetMapping(value = "/listVacantJobs")
	public ResponseEntity<List<Job>> listVacantJobs() {
		log.debug("**********Starting of listVacantJobs() method.");
		List<Job> vacantJobs = jobDAO.listVacantJobs();
		if (vacantJobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listVacantJobs() method.");
		return new ResponseEntity<List<Job>>(vacantJobs, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/jobApplied
	 * 
	 * @param jobApplication
	 * @param httpSession
	 * @return
	 */
	@PostMapping(value = "/jobApplied")
	public ResponseEntity<Job> applyForJob(@RequestBody Job job, HttpSession httpSession) {		
		log.debug("**********Starting of applyForJob() method.");

		Users loggedInUser = (Users) httpSession.getAttribute("loggedInUser");
		jobApplication.setUserId(loggedInUser.getId());
		jobApplication.setJobId(job.getId());
		jobApplication.setStatus("A"); // A = Applied ||R = Rejected ||C = Call for Interview 

		jobDAO.applyForJob(jobApplication);

		log.debug("**********End of applyForJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
}
