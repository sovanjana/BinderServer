package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "b_event")
@Component
public class Event extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/**
	 * declare the database column names for User... 
	 */
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String name;
	
	private String venue;
	
	private String description;
	
	@Column(name = "event_date")
	private Date date;
	
	/**
	 *  getters/setters for all the fields taken... 
	 */

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenue() {
		return venue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}	
}
