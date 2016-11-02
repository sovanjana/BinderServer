package com.niit.binder.junit.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.BlogDAO;
import com.niit.binder.model.Blog;

public class BlogJUnitTestCase {
	
	Logger log =  Logger.getLogger(BlogJUnitTestCase.class);
	
	@Autowired
	BlogDAO blogDAO;	//instance of BlogDAO created...
	
	@Autowired
	Blog blog;		//instance of Blog created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		
	}
	
	//@Test
	public void listBlog() {
		log.debug("Entered in listBlog method.........");
		assertEquals(blogDAO.list().size(), 1);
	}
		
	//@Test
	public void addBlog() {
		//blog.setId(3);
		blog.setTitle("#7 Blog");
		blog.setReason("Testing purpose");
		blog.setContent("This is my seventh blog...");
		blog.setUserId("sudip001");
		
		assertEquals(blogDAO.save(blog), true);
	}
	
	//@Test
	public void updateBlog() {
		//blog.setId("arpan001");
		blog.setTitle("#1 Blog");
		blog.setContent("This is my first blog...");
		blog.setUserId("sovan001");
		blog.setStatus("approved");
		
		assertEquals(blogDAO.update(blog), true);
	}
	
	//@Test
	public void deleteBlog() {
		blog.setId(1);
		
		assertEquals(blogDAO.delete(blog), true);
	}
	
	//@Test
	public void getBlog() {
		
		//assertEquals(blogDAO.get("blog001").getTitle(), "#1 Blog");
	}
}
