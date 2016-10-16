package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.User;

@EnableTransactionManagement
@Repository(value="UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	// getter/setter method for sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public UserDAOImpl() { 		//defaullt constructor of UserDAOImpl...
		
	}
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Declare all CRUD Operations...
	
	@Transactional
	public boolean save(User user){
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(User user){
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(User user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public User get(String id) {
		String hql = "from User where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		
		if(list == null) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		String hql = " from User ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
