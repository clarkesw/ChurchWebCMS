/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class LogoutController extends BaseController{
    
    @Autowired 
    HttpSession session;
    
    Logger logger = LoggerFactory.getLogger(LogoutController.class);
     
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String user = (String) session.getAttribute(AppConstants.Session.CurrentUser);
        logger.debug("GET /logout user: {}", user);
        
        session.removeAttribute(AppConstants.Security.JWT);
        session.removeAttribute(user);
        return "redirect:/";
    }
}
