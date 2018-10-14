/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class WelcomeController {
    
//    @Autowired
//    UserRepository repository;
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    
    @GetMapping("/")
    public String showWelcomePage(ModelMap model){
        model.put("user", getLoggedInUser());
        System.out.println("++++++++++++ showWelcomePage "+ getLoggedInUser());
        return "welcome";
    }
    
    @GetMapping("/welcome")
    public String showWelcomePageAfterLogin(ModelMap model){
        return "welcome";
    }
    
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
