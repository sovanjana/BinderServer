package com.niit.binder.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.User;

public class UserTestCase {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		//Here i'm going to perform basic database operations using hibernate provided functions....
		
		User user = (User) context.getBean("user");
		System.out.println("reached....2");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		System.out.println("reached....3");
		
		user.setId("arpan001");
		user.setName("Arpan Ghosh");
		user.setPassword("arpan@001");
		user.setGender("MALE");
		user.setEmail("arpanriders@gmail.com");
		user.setPhone("9007826433");
		user.setRole("EMPLOYEE");
		user.setImage(null);
		user.setPhotos(null);
				
		if(userDAO.save(user) == true) {
			System.out.println("User created successfully...");
		}
		else {
			System.out.println("Not able to create user...");
			
		}
	}
}
