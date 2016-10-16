package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.ForumComment;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface ForumCommentDAO {

	// Declare all CRUD Operations...
	
		public boolean save(ForumComment forumComment);
		
		public boolean update(ForumComment forumComment);
		
		public boolean saveOrUpdate(ForumComment forumComment);
		
		public boolean delete(ForumComment forumComment);
		
		public ForumComment get(String id);
		
		public List<ForumComment> list();
}