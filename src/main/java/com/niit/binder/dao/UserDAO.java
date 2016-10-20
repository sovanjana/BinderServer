package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Users;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface UserDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Users users);
	
	public boolean update(Users users);
	
	public boolean saveOrUpdate(Users users);
	
	public boolean delete(Users users);
	
	public Users get(String id);
	
	public List<Users> list();
	
	public Users authenticate(String id, String password);
}
