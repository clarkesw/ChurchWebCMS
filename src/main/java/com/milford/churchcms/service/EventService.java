/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class EventService {
    public Logger logger = LoggerFactory.getLogger(EventService.class);
    private static final List<CalendarEvent> events = new ArrayList<CalendarEvent>();
    private static int eventCount = 2;

    static Date endDate = new Date();
    
    static {
        endDate.setDate(23);
        endDate.setHours(20);
        events.add(new CalendarEvent(0, "Learn Spring MVC",new Date() ,endDate ));
        events.add(new CalendarEvent(1, "Go Fishing",new Date() ,endDate ));
    }

    public List<CalendarEvent> retrieveEvents() {
        return events;
    }

    public CalendarEvent retrieveOneEvent(int id) {
        logger.debug("EventService.retrieveOneEvent id: {}" + id);
        for (CalendarEvent event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    
    public void addEvent(Date start, Date end,String title, String url, boolean isRepeated) {
        logger.debug("EventService.addEvent title: {}" + title);
        events.add(new CalendarEvent(++eventCount, title, start, end));
    }
    
    public void addLiteEvent(String title, Date start, Date end) {
        logger.debug("EventService.addEvent title: {}" + title);
        events.add(new CalendarEvent(++eventCount, title, start, end));
    }
 
    public void deleteEvent(int id) {
        logger.debug("EventService.deleteEvent id: {}" + id);
        Iterator<CalendarEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            CalendarEvent event = iterator.next();
            if (event.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void updateEvent(CalendarEvent event){
        logger.debug("EventService.updateEvent id: {}" + event.getId());
        deleteEvent(event.getId());  
        
        events.add(DateUtil.updateTimeDate(event));
    }
}