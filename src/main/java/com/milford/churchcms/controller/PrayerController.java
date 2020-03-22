/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Prayer;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.PrayerRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.service.TextMessageService;
import java.util.List;
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
    PrayerRepository repository;
    
    @Autowired
    StaffRepository StaffRepo;
    
    @Autowired
    TextMessageService textService;
     
    @GetMapping("/savePrayer")
    public String addPrayer(ModelMap model){
        logger.debug("GET /prayer ");
        model.addAttribute("prayer", new Prayer());
        return "prayer";
    }    
    
    @PostMapping("/savePrayer")
    public String addAddressForStaff(ModelMap model,@Valid @ModelAttribute("prayer") Prayer prayer){
        logger.debug("POST /prayer Prayer : {}",prayer);

        String fullName = prayer.getFirstName() + " " + prayer.getLastName();
        List<Staff> staffers = StaffRepo.findByRecievePrayerRequestsTrue();
        
        logger.debug("   name : {}", fullName);
        staffers.forEach( staff -> 
                textService.sendMessage(staff, prayer.getPrayerRquest(), fullName)
        );
 
        repository.save(prayer);
        return "redirect:index.html"; 
    }    
    
    @GetMapping("/showPrayerRequests")
    public String showPrayer(ModelMap model){
        logger.debug("GET /showPrayerRequests ");
        model.addAttribute("prayers", repository.findAll());
        return "cms/list-prayers";
    }    
}
