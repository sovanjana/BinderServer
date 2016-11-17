package com.niit.binder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.FriendDAO;
import com.niit.binder.dao.UserDAO;
import com.niit.binder.model.Friend;
import com.niit.binder.model.Users;

@RestController
public class UserController {

	Logger log = Logger.getLogger(UserController.class);
	
	//@Autowired
	//Users users;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	/**
	 * 	http://localhost:8081/Binder/users			//working
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
	 * 	http://localhost:8081/Binder/searchForFriends			//working
	 * @return
	 */
	@GetMapping(value = "/searchForFriends")
	public ResponseEntity<List<Users>> listSearchForFriends(HttpSession session) {
		ArrayList<Users> list = new ArrayList<Users>();
		log.debug("**********Starting of listSearchForFriends() method.");
		List<Users> users = userDAO.list();
		if(users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		for(Users  u:users)
		{
			String userId = u.getId();
			boolean status = friendDAO.isFriend(userId, loggedInUser.getId());
			if (!status  && userId.compareTo(loggedInUser.getId())!=0) {
				list.add(u);
			}
		}
		log.debug("**********End of listSearchForFriends() method.");
		return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/user/			//working
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
	 * 	http://localhost:8081/Binder/user/{id}			//working
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
	 * 	http://localhost:8081/Binder/user/{id}			//working
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
	 * 	http://localhost:8081/Binder/user/login			//working
	 * @param users
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/user/login")
	public ResponseEntity<Users> login(@RequestBody Users users, HttpSession session) {
		log.debug("**********Starting of login() method.");
		users = userDAO.authenticate(users.getId(), users.getPassword());
		if(users == null) {
			users = new Users();	//we need to create new users object to set errorMsg and errorCode...
			users.setErrorCode("404");
			users.setErrorMessage("Invalid userId or password...");
			log.error("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			session.setAttribute("loggedInUserID", users.getId());
			session.setAttribute("LoggedInStatus", users.getIsOnline());
			
			friendDAO.setOnline(users.getId());
			userDAO.setOnline(users.getId());
		}
		log.debug("**********End of login() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/logout			//not working
	 * @param users
	 * @param session
	 * @return
	 */
	@GetMapping(value = "/user/logout")
	public ResponseEntity<Users> logout(HttpSession session) {
		log.debug("**********Starting of logout() method.");
		
	String userId = (String) session.getAttribute("loggedInUserID");
		
		log.debug("**********"+userId+"**********");
		
		friendDAO.setOffline(userId);
		userDAO.setOffline(userId);
		
		session.invalidate();
		log.debug("**********End of logout() method.");
		return new ResponseEntity<Users> (HttpStatus.OK);
	}
	
}


