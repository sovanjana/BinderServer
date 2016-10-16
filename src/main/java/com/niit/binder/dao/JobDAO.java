package com.niit.binder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.binder.model.Job;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface JobDAO {

	// Declare all CRUD Operations...
	
	public boolean save(Job job);
	
	public boolean update(Job job);
	
	public boolean saveOrUpdate(Job job);
	
	public boolean delete(Job job);
	
	public Job get(String id);
	
	public List<Job> list();
}
