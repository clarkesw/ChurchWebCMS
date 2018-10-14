/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.service.EventService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.validation.Valid;
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
public class CalendarController {
    
    @Autowired
    EventService service;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-events")
    public String showEvent(ModelMap model){
        String username = getLoggedInName(model);
        model.put("events", service.retrieveEvents());
        return "list-events";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
//        for(Object val: values){
//             System.out.println("++++++++ getLoggedInName" + val);
//        }
        return (String)model.get("user");
    }
 
    @GetMapping("/add-events")
    public String showAddEvent(ModelMap model, @ModelAttribute("event") CalendarEvent event){     
        return "add-event";
    }
    
    @PostMapping("/add-events")
    public String addEvent(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent event, BindingResult result){
        if(result.hasErrors())
            return "add-event";
        service.addEvent( event.getStartDate(), event.getEndDate(), event.getTitle(), event.getDetails(), 
                event.getPicURL(), event.isIsRepeated());
        return "redirect:/list-events";
    }
    
    @GetMapping("/delete-event")
    public String deleteEvent(@RequestParam int id){
        service.deleteEvent(id);
        return "redirect:/list-events";
    }
    
    @GetMapping("/update-event")
    public String updateShowEvent(ModelMap model, @RequestParam int id){
        CalendarEvent event = service.retrieveOneEvent(id);
        model.put("event", event);
        return "add-event";
    }    
    
    @PostMapping("/update-event")
    public String updateEventPost(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent event, BindingResult result){
        if(result.hasErrors())
            return "add-event";
        
        service.updateEvent(event);
        return "redirect:/list-events";
    }
}
