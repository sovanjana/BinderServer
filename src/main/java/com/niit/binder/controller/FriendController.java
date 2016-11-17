package com.niit.binder.controller;

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
import com.niit.binder.model.Friend;

@RestController
public class FriendController {

	Logger log = Logger.getLogger(FriendController.class);
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	/**
	 * http://localhost:8081/Binder/user/myFriends			//working
	 * @param session
	 * @return
	 */
	@GetMapping(value = "/user/myFriends")
	public ResponseEntity<List<Friend>> myFriends(HttpSession session) {
		log.debug("**********Starting of myFriends() method");
		String userId = (String) session.getAttribute("loggedInUserID");
		List<Friend> myFriends = friendDAO.getMyFriends(userId);
		log.debug("**********End of myFriends() method");
		return new ResponseEntity<List<Friend>> (myFriends, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/friend/{id}				//working
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/user/friend/{id}")
	public ResponseEntity<Friend> getFriend(@PathVariable("id") int id) {
		log.debug("**********Starting of getFriend() method.");
		Friend friend = friendDAO.get(id);
		log.debug("**********End of getFriend() method.");
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/newFriendRequests			//working
	 * @param session
	 * @return
	 */
	@GetMapping(value = "/user/newFriendRequests")			
	public ResponseEntity<List<Friend>> newFriendRequests(HttpSession session) {
		log.debug("**********Starting of newFriendRequests() method");
		
		String userId = (String) session.getAttribute("loggedInUserID");
		log.debug("********** Calling newFriendRequests() method for Userid : " + userId);
		
		List<Friend> newRequests = friendDAO.getNewFriendRequests(userId);
		log.debug("**********End of newFriendRequests() method");
		return new ResponseEntity<List<Friend>> (newRequests, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/addFriend/{friendId}			//working
	 * @param friendId
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/user/addFriend/{friendId}")			
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		log.debug("**********Starting of sendFriendRequest() method");
		String userId = (String) session.getAttribute("loggedInUserID");
		String status = (String) session.getAttribute("LoggedInStatus");
		
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setIsOnline(status);
		friend.setStatus("N");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		
		friendDAO.save(friend);
		
		log.debug("**********End of sendFriendRequest() method");		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/rejectFriend/{id}					//working
	 * @param id
	 * @param friend
	 * @param session
	 * @return
	 */
	@PutMapping(value = "/user/rejectFriend/{id}")				
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("id") int id, @RequestBody Friend friend, HttpSession session) {
		log.debug("**********Starting of rejectFriendRequest() method with id : "+id);
		
		friend.setStatus("R");	// N = New, A = Accepted, R = Rejected, U = Unfriend  						
		friendDAO.update(friend);
		
		log.debug("**********End of rejectFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/user/acceptFriend/{id}						//working
	 * @param id
	 * @param friend
	 * @param session
	 * @return
	 */
	@PutMapping(value = "/user/acceptFriend/{id}")			
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("id") int id, @RequestBody Friend friend, HttpSession session) {
		log.debug("**********Starting of acceptFriendRequest() method with id : "+id);
		
		friend.setStatus("A");	// N = New, A = Accepted, R = Rejected, U = Unfriend  						
		friendDAO.update(friend);
		
		log.debug("**********End of acceptFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}	
	
	/**
	 * http://localhost:8081/Binder/user/unFriend/{id}						//working
	 * @param id
	 * @param friend
	 * @param session
	 * @return
	 */
	@PutMapping(value = "/user/unFriend/{id}")			
	public ResponseEntity<Friend> unFriend(@PathVariable("id") int id, @RequestBody Friend friend, HttpSession session) {
		log.debug("**********Starting of unFriend() method with id : "+id);
		
		friend.setStatus("U");	// N = New, A = Accepted, R = Rejected, U = Unfriend  		
		friendDAO.update(friend);
		
		log.debug("**********End of unFriend() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
}
