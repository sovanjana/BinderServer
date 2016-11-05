package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Users;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface UserDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Users users);						//implemented
	public boolean update(Users users);						//implemented
	public Users get(String id);							//implemented
	public List<Users> list();								//implemented
	
	public Users authenticate(String id, String password);	//implemented
	
	public void setOnline(String id);						//implemented
	public void setOffline(String id);						//implemented	
	
}
