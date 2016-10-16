package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;

@EnableTransactionManagement
@Repository(value="ForumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	// getter/setter method for sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ForumDAOImpl() { 		//defaullt constructor of ForumDAOImpl...
		
	}
	
	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Declare all CRUD Operations...
	
	@Transactional
	public boolean save(Forum forum){
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Forum forum){
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Forum forum) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Forum get(String id) {
		String hql = "from Forum where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Forum> list = query.list();
		
		if(list == null) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Forum> list() {
		String hql = " from Forum ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
