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

import com.niit.binder.dao.EventDAO;
import com.niit.binder.model.Event;

@EnableTransactionManagement
@Repository(value="eventDAO")
public class EventDAOImpl implements EventDAO {
	
	Logger log = Logger.getLogger(EventDAOImpl.class);
	
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
	 * 	Constructor of EventDAOImpl...
	 */
	public EventDAOImpl() { 		
		
	}	
	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(Event event){
		try {
			log.debug("***********Starting of save() method.");
			event.setDate(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().save(event);
			log.debug("***********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Event event){
		try {
			log.debug("***********Starting of update() method.");
			sessionFactory.getCurrentSession().update(event);
			log.debug("***********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Event event) {
		try {
			log.debug("***********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(event);
			log.debug("***********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Event get(int id) {
		log.debug("***********Starting of get() method.");
		String hql = "from Event where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Event> list = query.list();
		
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
	public List<Event> list() {
		log.debug("***********Starting of list() method.");
		String hql = "from Event";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("***********End of list() method.");
		return query.list();
	}	
}
