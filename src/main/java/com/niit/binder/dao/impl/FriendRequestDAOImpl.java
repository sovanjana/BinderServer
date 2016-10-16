package com.niit.binder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.FriendRequestDAO;
import com.niit.binder.model.FriendRequest;

@EnableTransactionManagement
@Repository(value="FriendRequestDAO")
public class FriendRequestDAOImpl implements FriendRequestDAO {
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	// getter/setter method for sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public FriendRequestDAOImpl() { 		//defaullt constructor of FriendRequestDAOImpl...
		
	}
	
	public FriendRequestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Declare all CRUD Operations...
	
	@Transactional
	public boolean save(FriendRequest friendRequest){
		try {
			sessionFactory.getCurrentSession().save(friendRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(FriendRequest friendRequest){
		try {
			sessionFactory.getCurrentSession().update(friendRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(FriendRequest friendRequest) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friendRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(FriendRequest friendRequest) {
		try {
			sessionFactory.getCurrentSession().delete(friendRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public FriendRequest get(String id) {
		String hql = "from FriendRequest where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<FriendRequest> list = query.list();
		
		if(list == null) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<FriendRequest> list() {
		String hql = " from FriendRequest ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
