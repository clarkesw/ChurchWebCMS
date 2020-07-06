/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Alert;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.service.AlertService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlertController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(AlertController.class);
    
    @Autowired
    AlertService service;
        
    @GetMapping("/sendAlert")
    public String sendAlertGet(ModelMap model){
        logger.debug("GET /sendAlert");
        model.addAttribute("positions", AppConstants.positions);
        model.addAttribute("alert", new Alert());
        
        return "cms/add-alert";
    }    
    
    @PostMapping("/sendAlert")
    public String sendAlertPost(ModelMap model,@Valid @ModelAttribute("alert") Alert alert){
        logger.debug("POST /sendAlert  Alert : {}", alert);
        service.sendAlerts(alert.getGroup(), alert.getMessage(),super.getLoggedInUser().getUsername() );
        
        return "redirect:login"; 
    }    
}
