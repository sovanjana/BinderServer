package com.niit.binder.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OutputMessage extends Message {

	public OutputMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutputMessage(Message message) {
		super(message.getMessage(),message.getId());
		// TODO Auto-generated constructor stub
	}

	
	
	//private Date dateTime;
/*
	public OutputMessage(Message original, Date dateTime) {
		super(original.getMessage(), original.getId());
		this.dateTime = dateTime;
	}

	*//**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 *//*
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}	*/
}
