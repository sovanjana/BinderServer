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

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;

@RestController
public class ForumController {
	
	Logger log = Logger.getLogger(ForumController.class);
	
	@Autowired
	ForumDAO forumDAO;
	
	/**
	 * ----- url's related to forum -----
	 * 
	 *	a. fetch all forums : http://localhost:8081/Binder/forums				//
	 *	b. save forum : http://localhost:8081/Binder/forum/						//
	 *	c. update existing forum : http://localhost:8081/Binder/forum/{id}		//
	 * 	d. delete forum : http://localhost:8081/Binder/forum/{id}				//
	 * 	e. fetch forum by id : http://localhost:8081/Binder/forum/{id}			//
	 * 
	 */
	
	/**
	 * 	http://localhost:8081/Binder/forums
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
	 * 	http://localhost:8081/Binder/forum/
	 * @param forum
	 * @return
	 */
	@PostMapping(value = "/forum/")
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
		log.debug("**********Starting of createForum() method.");
		if(forumDAO.get(forum.getId()) == null) {
			forumDAO.save(forum);
			log.debug("**********End of createForum() method.");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		forum.setErrorMessage("Forum already exist with id : " +forum.getId());
		log.error("Forum already exist with id : " +forum.getId());
		return new ResponseEntity<Forum>(HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/forum/{id}
	 * @param id
	 * @param forum
	 * @return
	 */
	@PutMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> updateForum(@PathVariable("id") String id, @RequestBody Forum forum) {
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
	 * 	http://localhost:8081/Binder/forum/{id}
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") String id) {
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
	 * 	http://localhost:8081/Binder/forum/{id}
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/forum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") String id) {
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
	
}
