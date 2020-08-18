/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.service.StaffService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author clarke
 */
@Controller
@RequestMapping("/api")
public class BaseController {
    
    @Autowired 
    HttpSession session;
    
    @Autowired
    StaffService staffService;
        
    @InitBinder
    void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
       
    User getLoggedInUser(){
       String currentUserName = (String)session.getAttribute(AppConstants.Session.CurrentUser);
       User user = staffService.findByUsername(currentUserName);
       user.setBlankPassword();
       return user;
   }
}
