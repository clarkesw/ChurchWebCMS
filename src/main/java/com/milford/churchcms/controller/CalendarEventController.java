/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.service.EventService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
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
    
    @Autowired
    CalendarEventRepository repository;
    
    @Autowired
    StaffRepository staffRepository;
        
    @Autowired 
    private HttpSession session;
        
    @GetMapping("/list-events")
    public String showEvent(ModelMap model){
        List<CalendarEvent> retrieveEvents = service.showEvent();
        logger.debug("GET /list-events  Event size : {} ", retrieveEvents.size());

        model.addAttribute("events", retrieveEvents);
        return "cms/list-events";
    }

    @GetMapping("/add-events")
    public String showAddEvent(ModelMap model, @ModelAttribute("event") CalendarEvent calEvent){     
        logger.debug("GET /add-events Event : {}",calEvent);
        List<String> findAll = staffRepository.getFullNames();
        model.addAttribute("staffList",findAll); 
        logger.debug("   StaffList : {}",findAll);
        return "cms/add-event";
    }
    
    @PostMapping("/add-events")
    public String addEvent(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent calEvent, BindingResult result){
        logger.debug("POST /add-events Event : {}",calEvent);
        if(result.hasErrors())
            return "cms/add-event";

        Date startDate = addTimeToDate(calEvent.getStartDateCont(),calEvent.getStartTime());
        Date endDate = addTimeToDate(calEvent.getEndDateCont(),calEvent.getEndTime());
        CalendarEvent lastEvent = repository.findTopByOrderByIdDesc();
        
        int eventId = (lastEvent != null) ? lastEvent.getId() + 1 : 1;
        
        repository.save(new CalendarEvent(eventId,calEvent.getTitle(),calEvent.getDetails(), startDate, 
                                                endDate,calEvent.getStartTime(),calEvent.getEndTime(),calEvent.getContactName()));
        return "redirect:list-events";
    }
    
    @GetMapping("/delete-event")
    public String deleteEvent(@RequestParam int id){
        logger.debug("/delete-event Event : {}",id);
        repository.deleteById(id);
        return "redirect:list-events";
    }
    
    @PostMapping("/update-event")
    public String updateEventPost(ModelMap model,@Valid @ModelAttribute("event") CalendarEvent event, BindingResult result){
        logger.debug("POST /update-event eventId : {}",event.getId());
        if(result.hasErrors())
            return "cms/add-event";
        
        repository.delete(event);
        Date startDate = addTimeToDate(event.getStartDateCont(),event.getStartTime());
        Date endDate = addTimeToDate(event.getEndDateCont(),event.getEndTime());
        CalendarEvent lastEvent = repository.findTopByOrderByIdDesc();
        
        int eventId = (lastEvent != null) ? lastEvent.getId() + 1 : 1;
        logger.debug("    eventId after update : {}",eventId);
        repository.save(new CalendarEvent(eventId, event.getTitle(),event.getDetails(), startDate, 
                                                endDate,event.getStartTime(),event.getEndTime(),event.getContactName()));
        return "redirect:list-events";
    }
    
    @GetMapping("/update-event")
    public String updateShowEvent(ModelMap model, @RequestParam int id){
        logger.debug("POST /update-event ID: {}", id);
        Optional<CalendarEvent> event = repository.findById(id);
        List<String> findAll = staffRepository.getFullNames();
        model.addAttribute("staffList",findAll); 
        logger.debug("   StaffList : {}",findAll);
        if(event.isPresent())
            model.put("event", event.get());
        return "cms/add-event";
    }
    
//    @PostMapping("/addContactToEvent") 
//    public String addContactToEvent(ModelMap model,@Valid @ModelAttribute("staffer") Staff staffer){
//        logger.debug("POST /addContactToEvent  Name : {}",staffer.getFullName());
//        int eventId = (Integer)session.getAttribute("EventID");
//        
//        Optional<CalendarEvent> myEvent = repository.findById(eventId);
//        CalendarEvent event = myEvent.get();
//        logger.debug("   staffer : {}", staffer);
//        repository.delete(event);
//        event.setContact(staffer);
//        Date startDate = addTimeToDate(event.getStartDateCont(),event.getStartTime());
//        Date endDate = addTimeToDate(event.getEndDateCont(),event.getEndTime());
//        
//        repository.save(new CalendarEvent(computeId(eventId), event.getTitle(),event.getDetails(), startDate, 
//                            endDate,event.getStartTime(),event.getEndTime(),event.getContact()));
//        
//        return "redirect:list-events"; 
//    }    
// 
    @GetMapping("/addContactToEvent")
    public String addContactToEvent(ModelMap model, @RequestParam int contactId, @RequestParam int id){
        logger.debug("GET /addContactToEvent  Contact ID : {}",contactId);
        session.setAttribute("EventID", id);
        
        List<Staff> staff = staffRepository.findAll();
        model.put("staffList", staff);
        model.put("staffer", new Staff());

        logger.debug("  staffList : {}", staff.size());
        return "cms/add-contact";
    }    

    private Date addTimeToDate(Date myDate, String myTime){
        
        StringTokenizer timeToken = new StringTokenizer(myTime,":");
        myDate.setHours(Integer.parseInt(timeToken.nextToken()));
        
        String time = timeToken.nextToken();
        String AmPm = time.substring(2);
        String minutes = time.substring(0,2);
        
        myDate.setMinutes(Integer.parseInt(minutes));
        logger.debug("New Date : {}", myDate);
        
        return myDate;
    }  
    
    private int computeId(int id){
        CalendarEvent lastEvent = repository.findTopByOrderByIdDesc();   
        return (lastEvent != null) ? lastEvent.getId() + 1 : 1;
    }
}
