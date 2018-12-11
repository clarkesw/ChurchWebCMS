/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.service.EventService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("WebResponse")
public class UiController {
    
    public Logger logger = LoggerFactory.getLogger(UiController.class);
    
    @Autowired
    EventService service;
        
    @GetMapping("/calEventArray")
    public List<CalendarEvent> getTest(){
        return service.retrieveEvents();
    }
    
}

/*
{
  "defaultDate": "11-15-18",
  "editable": false,
  "eventLimit": true,
  "events": [{"title": "First Event","start": "2018-11-26T10:00:00","end": "2018-11-26T10:30:00"}]
}
*/