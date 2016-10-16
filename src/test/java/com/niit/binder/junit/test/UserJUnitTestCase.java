package com.niit.binder.junit.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.User;

public class UserJUnitTestCase {
	
	@Autowired
	UserDAO userDAO;	//instance of UserDAO created...
	
	@Autowired
	User user;		//instance of User created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
		
	}
	
	@Test
	public void addUser() {
		user.setId("arpan001");
		user.setName("Arpan Ghosh");
		user.setPassword("arpan@001");
		user.setGender("MALE");
		user.setEmail("arpanriders@gmail.com");
		user.setPhone("9007826433");
		user.setRole("EMPLOYEE");
		
		assertEquals(userDAO.save(user), true);
	}
}
