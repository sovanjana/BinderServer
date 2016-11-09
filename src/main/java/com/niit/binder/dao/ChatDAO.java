package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Chat;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface ChatDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Chat chat);
	
	public boolean update(Chat chat);
	
	public boolean delete(Chat chat);
	
	public Chat get(String id);
	
	public List<Chat> list();
	
}
