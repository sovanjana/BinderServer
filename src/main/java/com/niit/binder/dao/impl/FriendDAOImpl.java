package com.niit.binder.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.binder.dao.FriendDAO;
import com.niit.binder.model.Friend;

@EnableTransactionManagement		/**when we are using @Configuration i.e XML free configuration and need to 
									 * connect to database with hibernate. We need to use @EnableTransactionManagement.
									 */
@Repository(value="friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	Logger log = Logger.getLogger(FriendDAOImpl.class);
	
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
	 *   Constructor of FriendDAOImpl...
	 */
	public FriendDAOImpl() { 		
		
	}	
	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	/**
	 * used for sendFriendRequest
	 */
	@Transactional
	public boolean save(Friend friend){
		try {
			log.debug("**********Starting of save() method.");
			sessionFactory.getCurrentSession().save(friend);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Friend friend){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(friend);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * used for getting myFriends
	 */
	@Transactional
	public List<Friend> getMyFriends(String userId) {
		log.debug("**********Starting of getMyFriends() method.");
		String hql = "from Friend where (userId = '" + userId + "' and status = 'A') or (friendId = '" + userId + "' and status = 'A')";
		log.debug("**********hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getMyFriends() method.");
		return list;
	}
	
	/**
	 * used for getting newFriendRequest
	 */
	@Transactional
	public List<Friend> getNewFriendRequests(String userId) {
		log.debug("**********Starting of getNewFriendRequests() method.");
		
		String hql = "from Friend where (friendId = '" + userId + "' and status = 'N') or (userId = '" + userId + "' and status = 'N')";
		log.debug("***********hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getNewFriendRequests() method.");
		return list;
	}
	
	@Transactional
	public void rejectFriend(String userId) {
		log.debug("**********Starting of rejectFriend() method.");
		String hql = "update Friend set status = 'R' where friendId = '" + userId + "'";
		log.debug("**********hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of rejectFriend() method.");
	}
	
	@Transactional
	public Friend getSelectedFriend(String userId, String friendId) {
		log.debug("**********Starting of get() method.");
		String hql = "from Friend where userId = '" + userId + "' and friendId = '" + friendId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		log.debug("**********End of get() method.");
		return null;
	}
	
	@Transactional
	public void setOnline(String userId) {
		log.debug("**********Starting of setOnline() method.");
		String hql = "update Friend set isOnline = 'Y' where userId = '" + userId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOnline() method.");
	}
	
	@Transactional
	public void setOffline(String userId) {
		log.debug("**********Starting of setOffline() method.");
		String hql = "update Friend set isOnline = 'N' where userId = '" + userId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOffline() method.");
	}
	
	@Transactional
	public Friend get(int id) {
		log.debug("**********Starting of get() method.");
		String hql = "from Friend where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = query.list();
		
		if (list != null && !list.isEmpty()) {
			log.debug("**********End of get() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}
	public boolean isFriend(String userId, String friendId) {
		log.debug("**********Starting of isFriend() method.");
		
		String hql = "from Friend where (userId = '" + userId +"' and friendId = '"+ friendId +"') or (friendId = '" + userId +"' and userId = '"+ friendId +"')";
		Session session=sessionFactory.openSession();
		Query query = session.createQuery(hql);
		
		List<Friend> list = query.list();
		
		if (list != null && !list.isEmpty()) {
			log.debug("**********End of isFriend() method.");
			//session.flush();
			//session.close();
			return true;
		}
		log.debug("**********End of isFriend() method.");
		return false;
	}
		
}
