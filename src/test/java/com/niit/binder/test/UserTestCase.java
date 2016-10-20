package com.niit.binder.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.Users;

public class UserTestCase {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		//Here i'm going to perform basic database operations using hibernate provided functions....
		
		Users users = (Users) context.getBean("user");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
		users.setId("prantik009");
		users.setName("Prantik Hore");
		users.setPassword("prantik001");
		users.setGender("MALE");
		users.setEmail("prantik@gmail.com");
		users.setPhone("1010101010");
		users.setRole("EMPLOYEE");
		users.setImage(null);
		users.setPhotos(null);
				
		System.out.println("reached just before saving the details...");
		
		if(userDAO.save(users) == true) {
			System.out.println("User created successfully...");
		}
		else {
			System.out.println("Not able to create user...");
			
		}
		
	}
}
