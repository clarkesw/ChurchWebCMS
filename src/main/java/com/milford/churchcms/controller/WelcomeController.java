/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import com.milford.churchcms.dao.User;
import com.milford.churchcms.service.WelcomeService;
import javax.servlet.http.HttpSession;
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

@Controller
//@SessionAttributes("user")
public class WelcomeController {
    
    @Autowired
    WelcomeService service;
    
    @Autowired 
    private HttpSession session;
    
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    
    @PostMapping("/login")
    public String checkLoginCredentials(ModelMap model,@Valid @ModelAttribute("user") User user){
        String userName = user.getUsername();
        logger.debug("checkLoginCredentials  User : {}", userName);
        User dbUser = service.retrieveOneUser(userName);
        
        if(dbUser == null || !dbUser.getPassword().equals(user.getPassword() )){
            model.addAttribute("error", "Incorrect Username/Password.");
            return "/login";
        }
        session.setAttribute("user", userName);
        model.put("user", userName);   
        return "cms/welcome";
    }
    
    @GetMapping("/login")
    public String showWelcomePage(ModelMap model){
        logger.debug("WelcomeController User : {}",session.getAttribute("user"));
        if(session.getAttribute("user") != null)
            return "cms/welcome";
        return "cms/login-page";
    }
    
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
