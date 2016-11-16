package com.niit.binder.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.binder.model.Message;
import com.niit.binder.model.OutputMessage;

@Controller
public class ChatController {

	Logger log = Logger.getLogger(ChatController.class);
	
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message, Date date) {
		log.debug("Calling the method sendMessage().");
		
		log.debug("Message : "+message.getMessage());
		
		log.debug("Message ID : "+message.getId());
				
		return OutputMessage(message, date);
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String viewApplication() {
		return "chat";
	}

}
