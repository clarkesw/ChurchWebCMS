/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.controller.BaseController;
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

//    static {
//        events.add(new Calendar("Learn Spring MVC",DateUtil.returnStringDate("2018-11-26", "10:00:00"), DateUtil.returnStringDate("2018-11-26", "11:00:00")));
//        events.add(new Calendar("Learn Spring MVC",DateUtil.returnStringDate("2018-11-24", "12:00:00"), DateUtil.returnStringDate("2018-11-24", "14:00:00")));
//
//    }

    public List<Calendar> retrieveEvents() {
        return events;
    }

    public Calendar retrieveOneEvent(int id) {
        logger.debug("EventService.retrieveOneEvent id: {}" + id);
        Calendar filteredEvents = new Calendar();
        for (Calendar event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    public void addEvent(Date start, Date end,String title, String url, boolean isRepeated) {
        logger.debug("EventService.addEvent title: {}" + title);
     //   events.add(new Calendar(++eventCount,start, end, isRepeated, title,  url));
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