package com.niit.binder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	/* declare the database column names for User... */

	@Id
	private String chatId;
	
	@NotBlank
	private String senderId;
	
	@NotBlank
	private String receiverId;
	
	private String content;
	
	private Date dateTime;
	
	public Chat() {
		this.chatId = "chat" + UUID.randomUUID().toString().substring(24).toUpperCase();
	}
	
	/* getters/setters for all the fields taken... */

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
