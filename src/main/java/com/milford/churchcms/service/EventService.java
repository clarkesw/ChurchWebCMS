/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.CalendarEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
//https://www.youtube.com/watch?v=CCVvq4g4Qj0
// Integer id, Date startDate, Date endDate, String title, String details, String picURL
@Service
public class EventService {
    private static List<CalendarEvent> events = new ArrayList<CalendarEvent>();
    private static int eventCount = 3;

    static {
        events.add(new CalendarEvent(1,new Date(),new Date(), "ev1", "Learn Spring MVC", 
                "www.yahoo.com", false));
        events.add(new CalendarEvent(2,new Date(),new Date(), "ev2", "Learn Spring MVC", 
                "www.yahoo.com", false));
        events.add(new CalendarEvent(3, new Date(),new Date(), "ev3", "Learn Spring MVC", 
                "www.yahoo.com", true));
    }

    public List<CalendarEvent> retrieveEvents() {
        return events;
    }

    public CalendarEvent retrieveOneEvent(int id) {
        CalendarEvent filteredEvents = new CalendarEvent();
        for (CalendarEvent event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    public void addEvent(Date startDate, Date endDate,String name, String desc, String url, boolean isRepeated) {
        events.add(new CalendarEvent(++eventCount, startDate, endDate, name, desc, url, isRepeated));
    }
 
    public void deleteEvent(int id) {
        Iterator<CalendarEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            CalendarEvent event = iterator.next();
            if (event.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void updateEvent(CalendarEvent event){
        deleteEvent(event.getId());    
    	events.add(event);
    }
}