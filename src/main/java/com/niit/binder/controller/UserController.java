package com.niit.binder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.Users;

@RestController
public class UserController {

	Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	//	http://localhost:8081/Binder/users
	@GetMapping(value = "/users")
	public ResponseEntity<List<Users>> listUsers() {
		List<Users> users = userDAO.list();
		if(users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	//	http://localhost:8081/Binder/user/
	@PostMapping(value = "/user/")
	public ResponseEntity<Users> createUser(@RequestBody Users users) {
		if(userDAO.get(users.getId()) == null) {
			userDAO.save(users);
			return new ResponseEntity<Users>(HttpStatus.OK);
		}
		users.setErrorMessage("User already exist with id : " +users.getId());
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	//	http://localhost:8081/Binder/user/sovan001
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") String id, @RequestBody Users users) {
		if(userDAO.get(id) == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " +users.getId());
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		userDAO.update(users);
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	//	http://localhost:8081/Binder/user/sovan001
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("id") String id) {
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		userDAO.delete(users);
		return new ResponseEntity<Users>(HttpStatus.OK);		
	}
	
	//	http://localhost:8081/Binder/user/sovan001
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<Users> getUser(@PathVariable("id") String id) {
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	//	http://localhost:8081/Binder/user/authenticate/
	@PostMapping(value = "/user/authenticate/")
	public ResponseEntity<Users> authenticateUser(@RequestBody Users users, HttpSession session) {
		users = userDAO.authenticate(users.getId(), users.getPassword());
		if(users == null) {
			users = new Users();
			users.setErrorMessage("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			session.setAttribute("loggedInUserID", users.getId());
		}
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
}


