/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.util.JWTUtil;
import com.milford.churchcms.service.WelcomeService;
import com.milford.churchcms.util.PasswordUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController{
   
    @Autowired 
    HttpSession session;
    
    @Autowired
    WelcomeService service;
    
    @Value("${spring.datasource.url}")
    private String dataSourceInfo;
    
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    
    @PostMapping("/login")
    public String checkLoginCredentials(HttpServletResponse response, ModelMap model,@Valid @ModelAttribute("user") User user){
        String[] dataSource =  dataSourceInfo.split(":");
        String rootPW = PasswordUtil.generateSecurePassword("root");
        String whichDB = dataSource[1];
        String userName = user.getUsername();

        logger.debug("POST /login  User : {}", userName);
        User dbUser = service.checkLoginCredentials(userName);
        
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
    //    dbUser.setPassword("empty");
        session.setAttribute(AppConstants.Session.CurrentUser, dbUser.getUsername());
        model.put("user", userName);   
        
        String createdToken = JWTUtil.createToken(userName, 800000);
        session.setAttribute(AppConstants.Security.JWT, createdToken);
        logger.debug("   createToken : {}", createdToken);
        
        List<String> staffNames = getFullNames();
        model.put("staffers", staffNames);
        
        
        return "cms/welcome";
    }
    
    @GetMapping("/login")
    public String showWelcomePage(ModelMap model){
        String user = (String)session.getAttribute(AppConstants.Session.CurrentUser);
        logger.debug("GET /login User : {}",user);
        
        if(user != null){
            List<String> staffNames = getFullNames();
            
            model.put("user", user);   
            model.put("staffers", staffNames);
            
         //   JWTUtil.checkJWT((String)session.getAttribute(AppConstants.Security.JWT));
            logger.debug("   Recieve Prayer Requests : {}", staffNames);
            return "cms/welcome";
        }            
        return "cms/login-page";
    }
    
    private List<String> getFullNames(){
        List<Staff> prayerStaff = service.findByRecievePrayerRequestsTrue(); 
        
        List<String> staffNames = prayerStaff
                .stream()
                .map(staff -> staff.getFullName())
                .collect(Collectors.toList());
        return staffNames;
    }
}
