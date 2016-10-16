package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Forum;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface ForumDAO {

	// Declare all CRUD Operations...
	
		public boolean save(Forum forum);
		
		public boolean update(Forum forum);
		
		public boolean saveOrUpdate(Forum forum);
		
		public boolean delete(Forum forum);
		
		public Forum get(String id);
		
		public List<Forum> list();
}