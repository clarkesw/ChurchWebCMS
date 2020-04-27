/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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
}