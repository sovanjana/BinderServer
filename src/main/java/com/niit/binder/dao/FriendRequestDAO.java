package com.niit.binder.dao;

import java.util.List;

import com.niit.binder.model.FriendRequest;

public interface FriendRequestDAO {

	// Declare all CRUD Operations...
	
			public boolean save(FriendRequest friendRequest);
			
			public boolean update(FriendRequest friendRequest);
			
			public boolean saveOrUpdate(FriendRequest friendRequest);
			
			public boolean delete(FriendRequest friendRequest);
			
			public FriendRequest get(String id);
			
			public List<FriendRequest> list();
}
