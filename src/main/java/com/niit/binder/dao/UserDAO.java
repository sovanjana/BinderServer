package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.User;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface UserDAO {

	// Declare all CRUD Operations...
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean saveOrUpdate(User user);
	
	public boolean delete(User user);
	
	public User get(String id);
	
	public List<User> list();
}
