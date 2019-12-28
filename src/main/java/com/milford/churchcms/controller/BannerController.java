/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.SermonRepository;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class BannerController{
    
    public Logger logger = LoggerFactory.getLogger(BannerController.class);
    
    @Autowired
    SermonRepository repository;
        
    @GetMapping("/list-banner")
    public String showSermon(ModelMap model){
        List<Sermon> banner = repository.findAll();
        model.put("banner", banner);

        return "cms/list-banner";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @GetMapping("/add-banner")
    public String showAddSermon(ModelMap model, @ModelAttribute("banner") Sermon banner){     
        logger.debug("GET /add-banner Sermon : {}",banner);
        return "cms/add-banner";
    }
    
    @PostMapping("/add-banner")
    public String addSermon(ModelMap model,@Valid @ModelAttribute("banner") Sermon banner, BindingResult result){
        logger.debug("POST /add-banner Sermon : {}",banner);
        if(result.hasErrors())
            return "cms/add-banner";

        Sermon lastSermon = repository.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon != null) ? lastSermon.getId() + 1 : 1;
        
       // repository.save(result);
        return "redirect:list-banner";
    }
    
    @GetMapping("/delete-banner")
    public String deleteSermon(@RequestParam int id){
        logger.debug("/delete-banner Sermon : {}",id);
        repository.deleteById(id);
        return "redirect:list-banner";
    }
    
    @PostMapping("/update-banner")
    public String updateSermonPost(ModelMap model,@Valid @ModelAttribute("banner") Sermon banner, BindingResult result){
        logger.debug("POST /update-banner eventId : {}",banner.getId());
        if(result.hasErrors())
            return "cms/add-banner";
        
        repository.delete(banner);
        Sermon lastSermon = repository.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon != null) ? lastSermon.getId() + 1 : 1;
        
        repository.save(new Sermon(lastSermonId, banner.getTitle(),banner.getSubTitle(),banner.getDescription(),banner.getSermonDate()));
        return "redirect:list-banner";
    }
    
    @GetMapping("/update-banner")
    public String updateShowSermon(ModelMap model, @RequestParam int id){
        logger.debug("POST /update-banner ID: {}", id);
        Optional<Sermon> banner = repository.findById(id);

        if(banner.isPresent())
            model.put("banner", banner.get());
        return "cms/add-banner";
    }
}