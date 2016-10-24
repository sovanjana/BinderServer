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
	
	/**
	 * ----- url's related to users -----
	 * 
	 *	a. fetch all users : http://localhost:8081/Binder/users					//-----Y-----
	 *	b. save user : http://localhost:8081/Binder/user/						//-----Y-----
	 *	c. update existing user : http://localhost:8081/Binder/user/{id}		//-----Y-----
	 * 	d. delete user : http://localhost:8081/Binder/user/{id}					//-----Y-----
	 * 	e. fetch user by id : http://localhost:8081/Binder/user/{id}			//-----Y-----
	 * 	f. authenticate user : http://localhost:8081/Binder/user/authenticate/	//-----Y-----
	 * 
	 */
	
	/**
	 * 	http://localhost:8081/Binder/users
	 * @return
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<List<Users>> listUsers() {
		log.debug("**********Starting of listUsers() method.");
		List<Users> users = userDAO.list();
		if(users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listUsers() method.");
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/
	 * @param users
	 * @return
	 */
	@PostMapping(value = "/user/")
	public ResponseEntity<Users> createUser(@RequestBody Users users) {
		log.debug("**********Starting of createUser() method.");
		if(userDAO.get(users.getId()) == null) {
			userDAO.save(users);
			log.debug("**********End of createUser() method.");
			return new ResponseEntity<Users>(users, HttpStatus.OK);
		}
		users.setErrorMessage("User already exist with id : " +users.getId());
		log.error("User already exist with id : " +users.getId());
		return new ResponseEntity<Users>(HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/{id}
	 * @param id
	 * @param users
	 * @return
	 */
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") String id, @RequestBody Users users) {
		log.debug("**********Starting of updateUser() method.");
		if(userDAO.get(id) == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " +users.getId());
			log.error("User does not exist with id : " +users.getId());
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		userDAO.update(users);
		log.debug("**********End of updateUser() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/{id}
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteUser() method.");
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " + id);
			log.error("User does not exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		userDAO.delete(users);
		log.debug("**********End of deleteUser() method.");
		return new ResponseEntity<Users>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/{id}
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<Users> getUser(@PathVariable("id") String id) {
		log.debug("**********Starting of getUser() method.");
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("User does not exist with id : " + id);
			log.error("User does not exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getUser() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/authenticate/
	 * @param users
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/user/authenticate/")
	public ResponseEntity<Users> authenticateUser(@RequestBody Users users, HttpSession session) {
		log.debug("**********Starting of authenticateUser() method.");
		users = userDAO.authenticate(users.getId(), users.getPassword());
		if(users == null) {
			users = new Users();
			users.setErrorMessage("Invalid userId or password...");
			log.error("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			session.setAttribute("loggedInUserID", users.getId());
		}
		log.debug("**********End of authenticateUser() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
}


