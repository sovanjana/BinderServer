package com.niit.binder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.BlogDAO;
import com.niit.binder.model.Blog;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	//	http://localhost:8081/BinderServer/blogs
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listBlogs() {
		List<Blog> blog = blogDAO.list();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/blog/
	//@RequestMapping(value = "/blog", method = RequestMethod.POST)
	@PostMapping(value = "/blog")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		if(blogDAO.get(blog.getId()) == null) {
			blogDAO.save(blog);
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
		blog.setErrorMessage("Blog already exist with id : " +blog.getId());
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	//	http://localhost:8081/BinderServer/blog/{id}
	//@RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
	@PutMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") String id, @RequestBody Blog blog) {
		if(blogDAO.get(id) == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " +blog.getId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.update(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/blog/{id}
	//@RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") String id) {
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(blog);
		return new ResponseEntity<Blog>(HttpStatus.OK);		
	}
	
	//	http://localhost:8081/BinderServer/blog/{id}
	//@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	@GetMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") String id) {
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
}
