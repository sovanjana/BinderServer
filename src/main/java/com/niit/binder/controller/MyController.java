package com.niit.binder.controller;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	
	Logger log = Logger.getLogger(MyController.class); // when using log4j
	//private static final Logger log = LoggerFactory.getLogger(MyController.class);  // when using slf4j
	
    @RequestMapping(value="/home")
    public String onLoad() {
    	log.debug("Entered in onLoad method...");
    	return "home"; 
    }
    
}
