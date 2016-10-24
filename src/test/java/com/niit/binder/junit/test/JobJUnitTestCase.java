package com.niit.binder.junit.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.JobDAO;
import com.niit.binder.model.Job;

public class JobJUnitTestCase {
	
	Logger log =  Logger.getLogger(JobJUnitTestCase.class);
			
	@Autowired
	JobDAO jobDAO;	//instance of JobDAO created...
	
	@Autowired
	Job job;		//instance of Job created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
		
	}
	
	//@Test
	public void listJob() {
		log.debug("Starting of listJob() method...........");
		
		assertEquals(jobDAO.list().size(), 2);
	}	
	
	//@Test
	public void addJob() {
		log.debug("Starting of addJob() method...........");
		
		assertEquals(jobDAO.save(job), true);
	}
	
	//@Test
	public void updateJob() {
		log.debug("Starting of updateJob() method...........");
		
		assertEquals(jobDAO.update(job), true);
	}
	
	//@Test
	public void deleteJob() {
		log.debug("Starting of deleteJob() method...........");
		
		job.setId("prantik001");
		assertEquals(jobDAO.delete(job), true);
	}
	
	//@Test
	public void getJob() {
		log.debug("Starting of getJob() method...........");
		
		//assertEquals(jobDAO.get("sovan001").getName(), "Sovan Jana");
	}
}
