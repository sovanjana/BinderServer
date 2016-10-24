package com.niit.binder.controller;

import java.util.List;

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

import com.niit.binder.dao.ForumCommentDAO;
import com.niit.binder.model.ForumComment;

@RestController
public class ForumCommentController {
	
	Logger log = Logger.getLogger(ForumCommentController.class);
	
	@Autowired
	ForumCommentDAO forumCommentDAO;
	
	/**
	 * ----- url's related to forumComment -----
	 * 
	 *	a. fetch all forumComments : http://localhost:8081/Binder/forumComments				//
	 *	b. save forumComment : http://localhost:8081/Binder/forumComment/					//
	 *	c. update existing forumComment : http://localhost:8081/Binder/forumComment/{id}	//
	 * 	d. delete forumComment : http://localhost:8081/Binder/forumComment/{id}				//
	 * 	e. fetch forumComment by id : http://localhost:8081/Binder/forumComment/{id}		//
	 * 
	 */
	
	/**
	 * 	http://localhost:8081/Binder/forumComments
	 * @return
	 */
	@GetMapping(value = "/forumComments")
	public ResponseEntity<List<ForumComment>> listForumComments() {
		List<ForumComment> forumComment = forumCommentDAO.list();
		if(forumComment.isEmpty()) {
			return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forumComment/
	 * @param forumComment
	 * @return
	 */
	@PostMapping(value = "/forumComment/")
	public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment) {
		if(forumCommentDAO.get(forumComment.getId()) == null) {
			forumCommentDAO.save(forumComment);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
		}
		forumComment.setErrorMessage("ForumComment already exist with id : " +forumComment.getId());
		return new ResponseEntity<ForumComment>(HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forumComment/{id}
	 * @param id
	 * @param forumComment
	 * @return
	 */
	@PutMapping(value = "/forumComment/{id}")
	public ResponseEntity<ForumComment> updateForumComment(@PathVariable("id") String id, @RequestBody ForumComment forumComment) {
		if(forumCommentDAO.get(id) == null) {
			forumComment = new ForumComment();
			forumComment.setErrorMessage("No forumComment exist with id : " +forumComment.getId());
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
		}
		forumCommentDAO.update(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forumComment/{id}
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/forumComment/{id}")
	public ResponseEntity<ForumComment> deleteForumComment(@PathVariable("id") String id) {
		ForumComment forumComment = forumCommentDAO.get(id);
		if(forumComment == null) {
			forumComment = new ForumComment();
			forumComment.setErrorMessage("No forumComment exist with id : " + id);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
		}
		forumCommentDAO.delete(forumComment);
		return new ResponseEntity<ForumComment>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:8081/Binder/forumComment/{id}
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/forumComment/{id}")
	public ResponseEntity<ForumComment> getForumComment(@PathVariable("id") String id) {
		ForumComment forumComment = forumCommentDAO.get(id);
		if(forumComment == null) {
			forumComment = new ForumComment();
			forumComment.setErrorMessage("No forumComment exist with id : " + id);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
}
