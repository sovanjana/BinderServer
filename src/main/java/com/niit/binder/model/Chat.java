package com.niit.binder.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "b_chat")
@Component
public class Chat extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	/* declare the database column names for User... */

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String chatId;
	
	@Column(name = "sender")
	private String senderId;
	
	@Column(name = "receiver")
	private String receiverId;
	
	private String content;
	
	@Column(name = "date_time")
	private Timestamp dateTime;
	
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

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	
}
