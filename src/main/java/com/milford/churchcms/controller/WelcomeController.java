/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import com.milford.churchcms.dao.User;
import com.milford.churchcms.service.WelcomeService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sUser")
public class WelcomeController {
    
    @Autowired
    WelcomeService service;
    
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    
    @PostMapping("/login")
    public String checkLoginCredentials(ModelMap model,@Valid @ModelAttribute("user") User user){
        logger.debug("WelcomeController checkLoginCredentials db :" + user.getUsername() + "   input: "+model.get("username"));
        User dbUser = service.retrieveOneUser(user.getUsername());
        
        if(dbUser == null || !dbUser.getPassword().equals(user.getPassword() )){
            model.addAttribute("error", "Incorrect Username/Password.");
            return "/login";
        }
        model.put("user", user);   
        return "cms/welcome";
    }
    
    @GetMapping("/login")
    public String showWelcomePage(ModelMap model,@ModelAttribute("user") User user){
        model.put("user", user);   
        logger.debug("WelcomeController User  ");
        return "cms/login-page";
    }
    
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
