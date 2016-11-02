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

import com.niit.binder.dao.BlogDAO;
import com.niit.binder.model.Blog;

@EnableTransactionManagement
@Repository(value="blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	Logger log = Logger.getLogger(BlogDAOImpl.class);
	
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
	 * Constructor of BlogDAOImpl...
	 */
	public BlogDAOImpl() { 		
		
	}	
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(Blog blog){
		try {
			log.debug("**********Starting of save() method.");
			
			blog.setPostDate(new Date(System.currentTimeMillis()));	//set current time as postDate
			blog.setStatus("N");	// N = New, R = Rejected, A = Approved 
			
			sessionFactory.getCurrentSession().save(blog);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Blog blog){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(blog);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Blog blog) {
		try {
			log.debug("**********Starting of saveOrUpdate() method.");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			log.debug("**********End of saveOrUpdate() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Blog blog) {
		try {
			log.debug("**********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(blog);
			log.debug("**********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Blog get(int id) {
		log.debug("**********Starting of get() method.");
		String hql = "from Blog where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Blog> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			log.debug("**********End of get() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Blog> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}	
}
