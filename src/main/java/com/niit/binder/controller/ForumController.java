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

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;
import com.niit.binder.model.ForumComment;
import com.niit.binder.model.Users;

@RestController
public class ForumController {
	
	Logger log = Logger.getLogger(ForumController.class);
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	Forum forum;
	
	/**
	 * 	http://localhost:8081/Binder/forums								[working]
	 * @return
	 */
	@GetMapping(value = "/forums")
	public ResponseEntity<List<Forum>> listForums() {
		log.debug("**********Starting of listForums() method.");
		List<Forum> forum = forumDAO.list();
		if(forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listForums() method.");
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forum/									[working]
	 * @param forum
	 * @return
	 */
	@PostMapping(value = "/forum/")
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum, HttpSession session) {
		log.debug("**********Starting of createForum() method.");
		if(forumDAO.get(forum.getId()) == null) {
			
			Users loggedInUser = (Users) session.getAttribute("loggedInUser");
			
			forum.setUserId(loggedInUser.getId());
			forumDAO.save(forum);
			
			log.debug("**********End of createForum() method.");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		forum.setErrorMessage("Forum already exist with id : " +forum.getId());
		log.error("Forum already exist with id : " +forum.getId());
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forum/{id}								[working]
	 * @param id
	 * @param forum
	 * @return
	 */
	@PutMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> updateForum(@PathVariable("id") int id, @RequestBody Forum forum) {
		log.debug("**********Starting of updateForum() method.");
		if(forumDAO.get(id) == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " +forum.getId());
			log.error("No forum exist with id : " +forum.getId());
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.update(forum);
		log.debug("**********End of updateForum() method.");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forum/{id}								[working]
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteForum() method.");
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.delete(forum);
		log.debug("**********End of deleteForum() method.");
		return new ResponseEntity<Forum>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:8081/Binder/forum/{id}							[working]
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") int id) {
		log.debug("**********Starting of getForum() method.");
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getForum() method.");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|Forum Comment Area|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	/**
	 * http://localhost:8081/Binder/forumComments						[working]
	 * @return
	 */
	@GetMapping(value = "/forumComments/{forumId}")
	public ResponseEntity<List<ForumComment>> listForumComments(@PathVariable("forumId") String forumId) {
		log.debug("**********Starting of listForumComments() method.");
		List<ForumComment> forumComment = forumDAO.listComment(forumId);
		if(forumComment.isEmpty()) {
			return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listForumComments() method.");
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/forumComment/					[working]
	 * @param forumComment
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/forumComment/")
	public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment, HttpSession session) {
		log.debug("**********Starting of createForumComment() method.");
		Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		forumComment.setUserId(loggedInUser.getId());
		
		forumDAO.saveComment(forumComment);
		log.debug("**********End of createForumComment() method.");
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8081/Binder/forumComment/{id}							[working]
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/forumComment/{id}")
	public ResponseEntity<ForumComment> getForumComment(@PathVariable("id") int id) {
		log.debug("**********Starting of getForumComment() method.");
		ForumComment forumComment = forumDAO.getComment(id);
		if(forumComment == null) {
			forumComment = new ForumComment();
			forumComment.setErrorMessage("No getForumComment exist with id : " + id);
			log.error("No getForumComment exist with id : " + id);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getForumComment() method.");
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
}
