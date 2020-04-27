/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clarke
 */
public class BaseService<T> {
    
    private final List<T> items = new ArrayList<>();
    
    public List<T> retrieveEvents() {
        return items;
    }

//    public T retrieveOneItem(int id) {
//        for (T item : items) {
//            if (item.getId() == id) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public void deleteEvent(int id) {
//        logger.debug("EventService.deleteEvent id: {}" + id);
//        Iterator<Calendar> iterator = events.iterator();
//        while (iterator.hasNext()) {
//            Calendar event = iterator.next();
//            if (event.getId() == id) {
//                iterator.remove();
//            }
//        }
//    }
//    
//    public void updateEvent(Calendar event){
//        logger.debug("EventService.updateEvent id: {}" + event.getId());
//        deleteEvent(event.getId());    
//    	items.add(event);
//    }
}
