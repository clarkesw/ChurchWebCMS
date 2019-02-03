/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.service.EventService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
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
public class CalendarEventController{
    
    public Logger logger = LoggerFactory.getLogger(CalendarEventController.class);
    
    @Autowired
    EventService service;
    
    @Autowired
    CalendarEventRepository repository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-events")
    public String showEvent(ModelMap model){
        String username = getLoggedInName(model);
        List<CalendarEvent> retrieveEvents = repository.findAll();
        model.put("events", retrieveEvents);
       logger.debug("showEvent endDate: {}" + retrieveEvents.get(0).getEndDateCont());
       logger.debug("showEvent endTime: {}" + retrieveEvents.get(0).getEndTime());
        return "cms/list-events";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
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
     //   service.addLiteEvent(calEvent.getTitle(), startDate, endDate);    
        repository.save(new CalendarEvent(calEvent.getTitle(), startDate, endDate));
        return "redirect:list-events";
    }
    
    private Date addTimeToDate(Date myDate, String myTime){
        System.out.println("Raw Date : {}"+ myDate);
        System.out.println("Raw Time : {}"+ myTime);
        
        StringTokenizer timeToken = new StringTokenizer(myTime,":");
        myDate.setHours(Integer.parseInt(timeToken.nextToken()));
        
        String time = timeToken.nextToken();
        String AmPm = time.substring(2);
        String minutes = time.substring(0,2);
        
        myDate.setMinutes(Integer.parseInt(minutes));
        System.out.println("New Date : {}"+ AmPm);
        
        return myDate;
    }
    
    @GetMapping("/delete-event")
    public String deleteEvent(@RequestParam int id){
        repository.deleteById(id);
        return "redirect:list-events";
    }
    
    @PostMapping("/update-event")
    public String updateEventPost(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent event, BindingResult result){
        if(result.hasErrors())
            return "cms/add-event";
        
    //    service.updateEvent(event);
        repository.delete(event);
        repository.save(event);
        return "redirect:list-events";
    }
    
    @GetMapping("/update-event")
    public String updateShowEvent(ModelMap model, @RequestParam int id){
        logger.debug("updateShowEvent ID: {}" + id);
        Optional<CalendarEvent> event = repository.findById(id);
        
        logger.debug("updateShowEvent ID: {}" + event.isPresent());
        model.put("event", event.get());
        return "cms/add-event";
    }    
    
//    @ResponseBody 
//    @GetMapping("/calEventArray")
//    public List<CalendarEvent> getTest(){
//        return service.retrieveEvents();
//    }
}
