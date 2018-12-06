/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Calendar;
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
    private static List<Calendar> events = new ArrayList<Calendar>();
    private static int eventCount = 2;

    private static Date endDate = new Date(2018,12,30,20,30);
    
    static {
        events.add(new Calendar(0, "Learn Spring MVC",new Date() ,endDate ));
        events.add(new Calendar(1, "Go Fishing",new Date() ,endDate ));
    }

    public List<Calendar> retrieveEvents() {
        return events;
    }

    public Calendar retrieveOneEvent(int id) {
        logger.debug("EventService.retrieveOneEvent id: {}" + id);
        for (Calendar event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    
    public void addEvent(Date start, Date end,String title, String url, boolean isRepeated) {
        logger.debug("EventService.addEvent title: {}" + title);
        events.add(new Calendar(++eventCount, title, start, end));
    }
    
    public void addLiteEvent(String title, Date start, Date end) {
        logger.debug("EventService.addEvent title: {}" + title);
        events.add(new Calendar(++eventCount, title, start, end));
    }
 
    public void deleteEvent(int id) {
        logger.debug("EventService.deleteEvent id: {}" + id);
        Iterator<Calendar> iterator = events.iterator();
        while (iterator.hasNext()) {
            Calendar event = iterator.next();
            if (event.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void updateEvent(Calendar event){
        logger.debug("EventService.updateEvent id: {}" + event.getId());
        deleteEvent(event.getId());    
    	events.add(event);
    }
}