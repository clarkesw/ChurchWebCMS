/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Banner;
import com.milford.churchcms.repository.BannerRepository;
import com.milford.churchcms.service.BannerService;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class BannerController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(BannerController.class);
        
    @Autowired
    BannerService service;
    
    @GetMapping("/list-banner")
    public String showBanner(ModelMap model){
        Optional<Banner>  optBanner = service.showBanner();
        
        if(optBanner.isPresent()){
            Banner banner = optBanner.get();
            logger.debug("GET /list-banner Banner : {}",banner);
            model.put("banner", banner); 
        }else{
            model.put("banner", new Banner());
        }

        return "cms/list-banner";
    }

    @GetMapping("/add-banner")
    public String addBanner(ModelMap model, @ModelAttribute("banner") Banner banner){     
        logger.debug("GET /add-banner Banner : {}",banner);
        return "cms/add-banner";
    }
    
    @PostMapping("/add-banner")
    public String addBanner(ModelMap model,@Valid @ModelAttribute("banner") Banner banner, BindingResult result){
        logger.debug("POST /add-banner Banner : {}",banner);
        if(result.hasErrors())
            return "cms/add-banner";

        service.addBanner(banner);
        return "redirect:list-banner";
    }
    
    @GetMapping("/delete-banner")
    public String deleteBanner(){
        logger.debug("GET /delete-banner");
        service.deleteBanner();
        return "redirect:list-banner";
    }
}