package com.niit.binder.model;

public class Message {

	private String message;
	private int id;
	private String name;
	
	public Message() {
		
	}
	public Message(String message, int id) {
		this.message = message;
		this.id = id;
	}
	
	/**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 */
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
