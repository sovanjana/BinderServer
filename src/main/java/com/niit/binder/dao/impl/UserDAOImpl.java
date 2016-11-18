package com.niit.binder.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
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
	
	Logger log = Logger.getLogger(UserDAOImpl.class);
	
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
	 *  Constructors of UserDAOImpl... 
	 */
	public UserDAOImpl() { 
		
	}	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */	
	
	@Transactional
	public boolean save(Users users){
		try {
			log.debug("**********Starting of save() method.");
			users.setIsOnline("N");
			sessionFactory.getCurrentSession().save(users);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Users users){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(users);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Users get(String id) {
		log.debug("**********Starting of get() method.");
		String hql = "from Users where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list = (List<Users>) query.list();
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public List<Users> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from Users";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}	
		
	@Transactional
	public Users authenticate(String id, String password) {
		log.debug("**********Starting of authenticate() method.");
		String hql = "from Users where id = '" + id + "' and " + "password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Users> list = (List<Users>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}	

	@Transactional
	public void setOnline(String id) {
		log.debug("**********Starting of setOnline() method.");
		String hql = "update Users set isOnline = 'Y' where Id = '" + id + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOnline() method.");		
	}
	
	@Transactional
	public void setOffline(String id) {
		log.debug("**********Starting of setOffline() method.");
		String hql = "update Users set isOnline = 'N' where id = '" +id + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOffline() method.");
	}
	
}
