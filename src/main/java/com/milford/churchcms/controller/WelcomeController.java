/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import com.milford.churchcms.dao.User;
import com.milford.churchcms.repository.UserRepository;
import com.milford.churchcms.util.PasswordUtil;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
    UserRepository repository;
    
    @Autowired 
    private HttpSession session;
    
    @Value("${spring.datasource.url}")
    private String dataSourceInfo;
    
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    
    @PostMapping("/login")
    public String checkLoginCredentials(ModelMap model,@Valid @ModelAttribute("user") User user){
        String[] dataSource =  dataSourceInfo.split(":");
        String rootPW = PasswordUtil.generateSecurePassword("root");
        String whichDB = dataSource[1];
        String userName = user.getUsername();

        logger.debug("POST /login  User : {}", userName);
        User dbUser = repository.findByUsername(userName);
        
        if(dbUser == null){
            model.addAttribute("error", "Incorrect Username/Password.");
            return "cms/login-page";
        }
            
        if("mysql".equals(whichDB)){
            
            if(!user.getPassword().equalsIgnoreCase(dbUser.getPassword())){
                model.addAttribute("error", "Incorrect Username/Password.");
                return "cms/login-page";
            }
        }else{
            if(!user.getPassword().equalsIgnoreCase(rootPW)){
                model.addAttribute("error", "Incorrect Username/Password.");
                return "cms/login-page";
            }
        }
        
        session.setAttribute("loggedInUser", user);
        model.put("user", userName);   
        return "cms/welcome";
    }
    
    @GetMapping("/login")
    public String showWelcomePage(ModelMap model){
        logger.debug("GET /login User : {}",session.getAttribute("user"));
        if(session.getAttribute("user") != null)
            return "cms/welcome";
        return "cms/login-page";
    }
    
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
