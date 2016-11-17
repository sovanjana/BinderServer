/*package com.niit.binder.junit.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;

public class ForumJUnitTestCase {
	
	@Autowired
	ForumDAO forumDAO;	//instance of ForumDAO created...
	
	@Autowired
	Forum forum;		//instance of Forum created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		
	}
	
	@Test
	public void listForum() {
		
		assertEquals(forumDAO.list().size(), 1);
	}
	
	//@Test
	public void addForum() {
		//forum.setId("forum002");
		
		assertEquals(forumDAO.save(forum), true);
	}
	
	//@Test
	public void updateForum() {
		//forum.setId("forum002");
		
		assertEquals(forumDAO.update(forum), true);
	}
	
	//@Test
	public void deleteForum() {
		forum.setId("forum001");
		
		assertEquals(forumDAO.delete(forum), true);
	}
	
	//@Test
	public void getForum() {
		
		assertEquals(forumDAO.get("forum001").getId(), "forum001");
	}
}
*/