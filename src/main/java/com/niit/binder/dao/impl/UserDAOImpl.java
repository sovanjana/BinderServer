package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.Users;

@EnableTransactionManagement
@Repository(value="userDAO")
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
	public boolean save(Users users){
		try {
			sessionFactory.getCurrentSession().save(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean update(Users users){
		try {
			sessionFactory.getCurrentSession().update(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Users users) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Users users) {
		try {
			sessionFactory.getCurrentSession().delete(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Users get(String id) {
		String hql = "from Users where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Users> list = (List<Users>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Users> list() {
		String hql = "from Users";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
	
	@Transactional
	public Users authenticate(String id, String password) {
		String hql = "from User where id = '" + id + "' and " + "password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Users> list = (List<Users>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
