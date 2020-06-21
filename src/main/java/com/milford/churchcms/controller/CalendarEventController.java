/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.service.EventService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class CalendarEventController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(CalendarEventController.class);
    
    @Autowired
    EventService service;
        
    @GetMapping("/list-events")
    public String showEvent(ModelMap model){
        List<CalendarEvent> retrieveEvents = service.showEvent();
        logger.debug("GET /list-events  Event size : {} ", retrieveEvents.size());

        model.addAttribute("events", retrieveEvents);
        return "cms/list-events";
    }

    @GetMapping("/add-events")
    public String addEventGet(ModelMap model, @ModelAttribute("event") CalendarEvent calEvent){     
        logger.debug("GET /add-events Event : {}",calEvent);
        List<String> findAll = service.addEventGet();
        model.addAttribute("staffList",findAll); 
        logger.debug("   StaffList : {}",findAll);
        return "cms/add-event";
    }
    
    @PostMapping("/add-events")
    public String addEventPost(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent calEvent, BindingResult result){
        logger.debug("POST /add-events Event : {}",calEvent);
        if(result.hasErrors())
            return "cms/add-events";

        service.updateEventPost(calEvent);
        return "redirect:list-events";
    }
    
    @GetMapping("/delete-event")
    public String deleteEvent(@RequestParam int id){
        logger.debug("/delete-event Event : {}",id);
        service.deleteEvent(id);
        return "redirect:list-events";
    }
    
    @PostMapping("/update-event")
    public String updateEventPost(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent event, BindingResult result){
        logger.debug("POST /update-event eventId : {}",event.getId());
        if(result.hasErrors())
            return "cms/update-event";
        
        service.updateEventPost(event);
        return "redirect:list-events";
    }
    
    @GetMapping("/update-event")
    public String updateEventGet(ModelMap model, @RequestParam int id){
        logger.debug("POST /update-event ID: {}", id);
        Optional<CalendarEvent> event = service.updateEventGet(id);
        List<String> findAll = service.updateEventGet();
        
        model.addAttribute("staffList",findAll); 
        logger.debug("   StaffList : {}",findAll);
        
        if(event.isPresent())
            model.put("event", event.get());
        return "cms/add-event";
    }
    
    @GetMapping("/addContactToEvent")
    public String addContactToEvent(ModelMap model, @RequestParam int contactId, @RequestParam int id){
        logger.debug("GET /addContactToEvent  Contact ID : {}",contactId);
        session.setAttribute("EventID", id);
        
        List<Staff> staff = service.addContactToEvent();
        model.addAttribute("staffList", staff);
        model.addAttribute("staffer", new Staff());

        logger.debug("  staffList : {}", staff.size());
        return "cms/add-contact";
    }    

}
