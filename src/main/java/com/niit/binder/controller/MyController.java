package com.niit.binder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping(value="/home")
    public String onLoad() {
    	System.out.println("I'm in index.html...");
        return "index"; 
    }
    
    @RequestMapping(value="/hello")
    public String hello() {
    	System.out.println("I'm in hello.html...");
        return "hello"; 
    }
}
