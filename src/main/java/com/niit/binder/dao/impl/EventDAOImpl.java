package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.EventDAO;
import com.niit.binder.model.Event;

@EnableTransactionManagement
@Repository(value="EventDAO")
public class EventDAOImpl implements EventDAO {
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	// getter/setter method for sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public EventDAOImpl() { 		//defaullt constructor of EventDAOImpl...
		
	}
	
	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Declare all CRUD Operations...
	
	@Transactional
	public boolean save(Event event){
		try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Event event){
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Event event) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Event event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Event get(String id) {
		String hql = "from Event where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Event> list = query.list();
		
		if(list == null) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Event> list() {
		String hql = " from Event ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
