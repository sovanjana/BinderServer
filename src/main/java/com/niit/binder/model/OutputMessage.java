package com.niit.binder.model;

import java.util.Date;

public class OutputMessage extends Message {

	private Date dateTime;

	public OutputMessage(Message original, Date dateTime) {
		super(original.getMessage(), original.getId());
		this.dateTime = dateTime;
	}

	/**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 */
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}	
}
