package com.niit.binder.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.JobDAO;
import com.niit.binder.model.Job;
import com.niit.binder.model.JobApplication;

@EnableTransactionManagement
@Repository(value="jobDAO")
public class JobDAOImpl implements JobDAO {
	
	Logger log = Logger.getLogger(JobDAOImpl.class);
	
	@Autowired
	Job job;
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	/**
	 *  getter/setter method for sessionFactory
	 */	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *	Constructor of JobDAOImpl... 
	 */
	public JobDAOImpl() {
		
	}	
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	@Transactional
	public boolean save(Job job){
		try {
			log.debug("**********Starting of save() method.");
			job.setStatus("V");	//V-Vacant	F-Filled	P-Pending
			job.setDate(new Date(System.currentTimeMillis()));
			job.setNoOfApplicants(0);
			
			sessionFactory.getCurrentSession().save(job);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Job job){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(job);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Job get(int id) {
		log.debug("**********Starting of get() method.");
		String hql = "from Job where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Job> list = query.list();
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public List<Job> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}
	
	@Transactional
	public List<Job> listVacantJobs() {
		log.debug("**********Starting of listVacantJobs() method.");
		String hql = "from Job where status = 'V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listVacantJobs() method.");
		return query.list();
	}
	
	@Transactional
	public List<JobApplication> listJobApplications() {
		log.debug("**********Starting of listJobApplications() method.");
		String hql = "from JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listJobApplications() method.");
		return query.list();
	}
	
	@Transactional
	public boolean applyForJob(JobApplication jobApplication) {
		try {
			log.debug("**********Starting of applyForJob() method.");
			sessionFactory.getCurrentSession().save(jobApplication);
			log.debug("**********End of applyForJob() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			log.debug("**********Starting of updateJobApplication() method.");
			sessionFactory.getCurrentSession().update(jobApplication);
			log.debug("**********End of updateJobApplication() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public JobApplication get(String userId, String jobId) {
		log.debug("**********Starting of get() method.");
		String hql = "from JobApplication where userId = '" + userId + "' and jobId = '" + jobId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of get() method.");
		return (JobApplication) query.list();
	}
	
	@Transactional
	public List<Job> getMyAppliedJobs(String userId) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		String hql = "from Job where id in (select jobId from JobApplication where userId = '" + userId + "')";
		log.debug("******hql query : "+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of getMyAppliedJobs() method.");
		return query.list();
	}
	
		
}
