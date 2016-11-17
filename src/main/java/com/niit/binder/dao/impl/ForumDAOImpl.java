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

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;
import com.niit.binder.model.ForumComment;

@EnableTransactionManagement
@Repository(value="forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	Logger log = Logger.getLogger(ForumDAOImpl.class);
	
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
	 *  Constructor of ForumDAOImpl...
	 */
	public ForumDAOImpl() { 		
		
	}	
	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(Forum forum){				//working
		try {
			log.debug("**********Starting of save() method.");
			forum.setPostDate(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().save(forum);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Forum forum){				//working
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(forum);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Forum forum) {				//working
		try {
			log.debug("**********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(forum);
			log.debug("**********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Forum get(int id) {								//working
		log.debug("**********Starting of get() method.");
		String hql = "from Forum where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Forum> list = query.list();
		
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
	public List<Forum> list() {									//working
		log.debug("**********Starting of list() method.");
		String hql = " from Forum ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}
	
	@Transactional
	public boolean saveComment(ForumComment forumComment) {
		try {
			log.debug("**********Starting of saveComment() method.");
			forumComment.setCommentDate(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().save(forumComment);
			log.debug("**********End of saveComment() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")				//working
	@Transactional
	public List<ForumComment> listComment(String  id) {
		log.debug("**********Starting of listComment() method.");
		int fid=Integer.parseInt(id);
		String hql = " from ForumComment where forumId = "+ id ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listComment() method.");
		return query.list();
	}
	
	@Transactional
	public ForumComment getComment(int id) {						//working
		log.debug("**********Starting of getComment() method.");
		String hql = "from ForumComment where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<ForumComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			log.debug("**********End of getComment() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}	
}
