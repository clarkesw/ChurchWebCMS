/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Article;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.repository.ArticleRepository;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.SermonRepository;
import com.milford.churchcms.repository.WebPageRepository;
import com.milford.churchcms.service.EventService;
import com.milford.churchcms.service.WebPageService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UiController {
    
    public Logger logger = LoggerFactory.getLogger(UiController.class);
    
    @Autowired
    EventService eventService;
    
    @Autowired
    CalendarEventRepository churchEventRepository;
        
    @Autowired
    ChurchRepository churchRepository;
    
    @Autowired
    SermonRepository sermonRepository;
        
    @Autowired
    ArticleRepository articleRepository;
    
    @Autowired
    WebPageRepository pageRepository;
        
    @Autowired
    WebPageService pageService;
        
    @GetMapping("/calEventArray")
    @ResponseBody
    public List<CalendarEvent> getCalendarEvent(){
        logger.debug("UiController /calEventArray");
        return churchEventRepository.findAll();
    }
    
    @GetMapping("/page/{name}")
    public String showPage(@PathVariable String name, ModelMap model){
        logger.debug("UiController /page/" + name);
        
        List<ChurchInfo> churchInfo = churchRepository.findAll();
        Article article = articleRepository.findTopByOrderByLastModified();
        model.addAttribute("article", article);
        model.addAttribute("church", churchInfo.get(0));
        model.addAttribute("sermon", sermonRepository.findTopByOrderBySermonDateDesc());
        model.addAttribute("page", pageRepository.findByPageName(name));
        
        logger.debug(" *** " + churchInfo.get(0));
        if("event".equals(name))
            return "event";
                
        return "home";

    }
    
    @GetMapping("/event/{id}")
    public String showEventPage(@PathVariable String title, ModelMap model){
        logger.debug("UiController /event/" + title);

        model.addAttribute("event", pageService.retrieveOnePage(title));
      //  model.addAttribute("page", pageService.retrieveOnePage("event"));
        return "event";
    }
    
    @GetMapping("/home")
    public String getHome(){
        logger.debug("UiController /home");
        return "index";
    }
}
