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

import com.niit.binder.dao.BlogDAO;
import com.niit.binder.model.Blog;

@RestController
public class BlogController {
	
	Logger log = Logger.getLogger(BlogController.class);
	
	@Autowired
	BlogDAO blogDAO;
	
	/**
	 * ----- url's related to blog -----
	 * 
	 *	a. fetch all blogs : http://localhost:8081/Binder/blogs				//-----Y-----
	 *	b. save blog : http://localhost:8081/Binder/blog/					//-----Y-----
	 *	c. update existing blog : http://localhost:8081/Binder/blog/{id}	//-----Y-----
	 * 	d. delete blog : http://localhost:8081/Binder/blog/{id}				//-----Y-----
	 * 	e. fetch blog by id : http://localhost:8081/Binder/blog/{id}		//-----Y-----
	 * 
	 */
	
	/**
	 * 	http://localhost:8081/Binder/blogs
	 * @return
	 */
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {
		log.debug("**********Starting of listBlogs() method.");
		List<Blog> blog = blogDAO.list();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlogs() method.");
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/blog/
	 * @param blog
	 * @return
	 */
	@PostMapping(value = "/blog/")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		log.debug("**********Starting of createBlog() method.");
		if(blogDAO.get(blog.getId()) == null) {
			blogDAO.save(blog);
			log.debug("**********End of createBlog() method.");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		blog.setErrorMessage("Blog already exist with id : " +blog.getId());
		log.error("Blog already exist with id : " +blog.getId());
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	/**
	 * 	http://localhost:8081/Binder/blog/{id}
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") String id, @RequestBody Blog blog) {
		log.debug("**********Starting of updateBlog() method.");
		if(blogDAO.get(id) == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " +blog.getId());
			log.error("No blog exist with id : " +blog.getId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.update(blog);
		log.debug("**********End of updateBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:8081/Binder/blog/{id}
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteBlog() method.");
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(blog);
		log.debug("**********End of deleteBlog() method.");
		return new ResponseEntity<Blog>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:8081/Binder/blog/{id}
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") String id) {
		log.debug("**********Starting of getBlog() method.");
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}	
}
