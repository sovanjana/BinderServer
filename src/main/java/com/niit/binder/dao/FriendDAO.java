package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Friend;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface FriendDAO {

	/**
	 *  Declare all CRUD Operations...
	 * 
	 */
	
	public boolean save(Friend friend);				//implemented...
	
	public boolean update(Friend friend);							//implemented...
			
	public Friend getSelectedFriend(String userId, String friendId);		//implemented
	public Friend get(int id);
	public List<Friend> getMyFriends(String userId);				//implemented...
	
	public List<Friend> getNewFriendRequests(String userId);		//implemented...
	public void rejectFriend(String userId);
	
	public void setOnline(String userId);					//implemented...
	public void setOffline(String userId);					//implemented...
	
	public boolean isFriend(String userId, String friendId);
}
