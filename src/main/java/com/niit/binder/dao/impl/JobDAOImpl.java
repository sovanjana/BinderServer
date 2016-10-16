package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.JobDAO;
import com.niit.binder.model.Job;

@EnableTransactionManagement
@Repository(value="JobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	// getter/setter method for sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public JobDAOImpl() { 		//defaullt constructor of JobDAOImpl...
		
	}
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Declare all CRUD Operations...
	
	@Transactional
	public boolean save(Job job){
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Job job){
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Job job) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Job get(String id) {
		String hql = "from Job where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Job> list = query.list();
		
		if(list == null) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		String hql = " from Job ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
