package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Friend;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface FriendDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public boolean saveOrUpdate(Friend friend);
			
	public boolean delete(Friend friend);
			
	public Friend get(String id);
			
	public List<Friend> list();
}
