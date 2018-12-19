/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.service.ChurchService;
import com.milford.churchcms.service.EventService;
import com.milford.churchcms.service.WebPageService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UiController {
    
    public Logger logger = LoggerFactory.getLogger(UiController.class);
    
    @Autowired
    EventService eventService;
    
    @Autowired
    ChurchService churchService;
        
    @Autowired
    WebPageService pageService;
        
    @GetMapping("/calEventArray")
    @ResponseBody
    public List<CalendarEvent> getTest(){
        logger.debug("UiController /calEventArray");
        return eventService.retrieveEvents();
    }
    
    @GetMapping("/page/{name}")
    public String showPage(@PathVariable String name, ModelMap model){
        
        model.addAttribute("church", churchService.getChurchInfo());
        model.addAttribute("page", pageService.retrieveOnePage(name));
        return "home";

    }
    
    @GetMapping("/home")
    public String getHome(){
        logger.debug("UiController /home");
        return "index";
    }
}
