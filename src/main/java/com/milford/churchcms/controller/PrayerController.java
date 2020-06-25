/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Prayer;
import com.milford.churchcms.service.PrayerService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrayerController{
    
    public Logger logger = LoggerFactory.getLogger(PrayerController.class);
    
    @Autowired
    PrayerService service;
     
    @GetMapping("/savePrayer")
    public String addPrayer(ModelMap model){
        logger.debug("GET /savePrayer ");
        model.addAttribute("prayer", new Prayer());
        return "prayer";
    }    
    
    @PostMapping("/savePrayer")
    public String addPrayerPost(ModelMap model,@Valid @ModelAttribute("prayer") Prayer prayer){
        logger.debug("POST /savePrayer Prayer : {}",prayer);

        service.addPrayerPost(prayer);
        return "redirect:index.html"; 
    }    
    
    @GetMapping("/showPrayerRequests")
    public String showPrayer(ModelMap model){
        logger.debug("GET /showPrayerRequests ");
        model.addAttribute("prayers", service.showPrayer());
        return "cms/list-prayers";
    }    
}
