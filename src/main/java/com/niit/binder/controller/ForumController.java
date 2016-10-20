/*package com.niit.binder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.ForumDAO;
import com.niit.binder.model.Forum;

@RestController
public class ForumController {
	
	@Autowired
	ForumDAO forumDAO;
	
	//	http://localhost:8081/BinderServer/forums
	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listForums() {
		List<Forum> forum = forumDAO.list();
		if(forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/forum/
	@RequestMapping(value = "/forum/", method = RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
		if(forumDAO.get(forum.getId()) == null) {
			forumDAO.save(forum);
			return new ResponseEntity<Forum>(HttpStatus.OK);
		}
		forum.setErrorMessage("Forum already exist with id : " +forum.getId());
		return new ResponseEntity<Forum>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/forum/{id}
	@RequestMapping(value = "/forum/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable("id") String id, @RequestBody Forum forum) {
		if(forumDAO.get(id) == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " +forum.getId());
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.update(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/forum/{id}
	@RequestMapping(value = "/forum/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") String id) {
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.delete(forum);
		return new ResponseEntity<Forum>(HttpStatus.OK);		
	}
	
	//	http://localhost:8081/BinderServer/forum/{id}
	@RequestMapping(value = "/forum/{id}", method = RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("id") String id) {
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
}
*/