/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    public Logger logger = LoggerFactory.getLogger(EventService.class);
    
    @Autowired
    CalendarEventRepository repository;
    
    @Autowired
    StaffRepository staffRepository;
    
    public List<CalendarEvent> showEvent(){
        return repository.findAll();
    }
    
    public List<String> addEventGet(){
        return staffRepository.getFullNames();
    }
    
    public void addEventPost(CalendarEvent calEvent){
        Date startDate = addTimeToDate(calEvent.getStartDateCont(),calEvent.getStartTime());
        Date endDate = addTimeToDate(calEvent.getEndDateCont(),calEvent.getEndTime());
        CalendarEvent lastEvent = repository.findTopByOrderByIdDesc();
        
        int eventId = (lastEvent != null) ? lastEvent.getId() + 1 : 1;
        
        repository.save(new CalendarEvent(eventId,calEvent.getTitle(),calEvent.getDetails(), startDate, 
                                                endDate,calEvent.getStartTime(),calEvent.getEndTime(),calEvent.getContactName()));
    }
    
    public void deleteEvent(int id){
        repository.deleteById(id);
    }
    
    public void updateEventPost(CalendarEvent event){
        repository.delete(event);
        Date startDate = addTimeToDate(event.getStartDateCont(),event.getStartTime());
        Date endDate = addTimeToDate(event.getEndDateCont(),event.getEndTime());
        CalendarEvent lastEvent = repository.findTopByOrderByIdDesc();
        
        int eventId = (lastEvent != null) ? lastEvent.getId() + 1 : 1;
        logger.debug("    eventId after update : {}",eventId);
        repository.save(new CalendarEvent(eventId, event.getTitle(),event.getDetails(), startDate, 
                                                endDate,event.getStartTime(),event.getEndTime(),event.getContactName()));
    }
    
    public Optional<CalendarEvent>  updateEventGet( int id){
        return repository.findById(id);
    }
    
    public List<String> updateEventGet(){
        return staffRepository.getFullNames();
    }
    
    public List<Staff> addContactToEvent(){
        return staffRepository.findAll();
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
   
}