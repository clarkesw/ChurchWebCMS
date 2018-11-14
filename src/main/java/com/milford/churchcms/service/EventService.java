/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class EventService {
    public static Logger logger;
    private static List<CalendarEvent> events = new ArrayList<CalendarEvent>();
    private static int eventCount = 2;

//    static {
//        events.add(new CalendarEvent("Learn Spring MVC",DateUtil.returnStringDate("2018-11-26", "10:00:00"), DateUtil.returnStringDate("2018-11-26", "11:00:00")));
//        events.add(new CalendarEvent("Learn Spring MVC",DateUtil.returnStringDate("2018-11-24", "12:00:00"), DateUtil.returnStringDate("2018-11-24", "14:00:00")));
//
//    }

    public List<CalendarEvent> retrieveEvents() {
        return events;
    }

    public CalendarEvent retrieveOneEvent(int id) {
        logger.debug("EventService.retrieveOneEvent id: {}" + id);
        CalendarEvent filteredEvents = new CalendarEvent();
        for (CalendarEvent event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    public void addEvent(String start, String end,String title, String url, boolean isRepeated) {
        logger.debug("EventService.addEvent title: {}" + title);
     //   events.add(new CalendarEvent(++eventCount,start, end, isRepeated, title,  url));
    }
    
    public void addLiteEvent(Date start, Date end,String title) {
        logger.debug("EventService.addEvent title: {}" + title);
        events.add(new CalendarEvent(++eventCount, start, end, title));
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
    	events.add(event);
    }
}