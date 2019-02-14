/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.service.WebPageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class WebPageController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(WebPageController.class);
    
    @Autowired
    WebPageService service;
    
    @GetMapping("/list-pages")
    public String showPages(ModelMap model){
        logger.debug("WebPageController.showPages ");
        model.put("pages", service.retrievePages());
        return "cms/list-pages";
    }
    
    @PostMapping("/update-page")
    public String updatePagePost(ModelMap model,@Valid @ModelAttribute("page") WebPage page, @ModelAttribute("constants") AppConstants constants, BindingResult result){
        logger.debug("WebPageController.updatePagePost : {}", page);
        if(result.hasErrors())
            return "/cms/add-page";
      //  page.setLastModified(new Date());
        service.updatePage(page);
        logger.debug("WebPageController redirect:/cms/list-pages ");
        return "redirect:list-pages";
    }
    
    @GetMapping("/update-page")
    public String updateShowPage(ModelMap model, @RequestParam int id){
        WebPage page = service.retrieveOnePage(id);
        logger.debug("WebPageController.updateShowEvent : {}",page);
        model.put("page", page);
        return "/cms/add-page";
    }   
    
 /* Uncomment when ready to add webpages.   
    @GetMapping("/add-events")
    public String showAddEvent(ModelMap model, @ModelAttribute("event") CalendarEvent calEvent){     
        return "cms/add-event";
    }
    
    @PostMapping("/add-events")
    public String addEvent(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent calEvent, BindingResult result){
        if(result.hasErrors())
            return "cms/add-event";

        Date startDate = addTimeToDate(calEvent.getStartDateCont(),calEvent.getStartTime());
        Date endDate = addTimeToDate(calEvent.getEndDateCont(),calEvent.getEndTime());
  //      logger.debug("CalendardController Event : {}",calEvent);
        service.addLiteEvent(calEvent.getTitle(), startDate, endDate);        
        return "redirect:/cms/list-events";
    }
   */ 
}
