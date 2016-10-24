package com.niit.binder.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	Logger log = Logger.getLogger(MyController.class); // i'm using log4j
	
	@GetMapping(value="/")
    public String onLoad() {
    	log.debug("******************* Entered in onLoad method.");
    	return "index"; 
    }
	
	@GetMapping(value="/home")
    public String goHome() {
    	log.debug("******************* Entered in goHome method.");
    	return "home"; 
    }    
}
