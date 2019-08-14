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
import com.milford.churchcms.service.MyJmsMessage;
import com.milford.churchcms.service.WebPageService;
import com.milford.churchcms.util.DateUtil;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
    CalendarEventRepository eventRepository;
        
    @Autowired
    ChurchRepository churchRepository;
    
    @Autowired
    SermonRepository sermonRepository;
        
    @Autowired
    ArticleRepository articleRepository;
    
    @Autowired
    WebPageRepository pageRepository;
    
    @Autowired
    MyJmsMessage myJmsMessage;
        
    @Autowired
    WebPageService pageService;
    
    @Autowired 
    private HttpSession session;
        
    @GetMapping("/calEventArray")
    @ResponseBody
    public List<CalendarEvent> getCalendarEvent(){
        logger.debug("GET /calEventArray");
        return eventRepository.findAll();
    }
    
    @GetMapping("/page/{name}")
    public String showPage(@PathVariable String name, ModelMap model){
        logger.debug("GET /page/" + name);
        
        ChurchInfo myChurch = getChurchInfo();
        Article article = articleRepository.findTopByOrderByLastModified();
        model.addAttribute("article", article);
        model.addAttribute("church", myChurch);
        model.addAttribute("services", myChurch.getServiceTimes());
        model.addAttribute("sermon", sermonRepository.findTopByOrderBySermonDateDesc());
        model.addAttribute("page", pageRepository.findByPageName(name));
        
        logger.debug(" *** " + pageRepository.findByPageName(name));
        if("calendar".equals(name)){
            return "calendar";
        }  
                
        return "home";
    }
    
    @GetMapping("/email/{name}")
    public void sendEmail(@PathVariable String name){
        // Figure out Later.
    }
    
    @GetMapping("/event/{id}")
    public String showEventPage(@PathVariable int id, ModelMap model){
        logger.debug("GET /event/" + id);
        Optional<CalendarEvent> calEvent = eventRepository.findById(id);
        CalendarEvent oneEvent = calEvent.get();
        model.addAttribute("event",oneEvent);
        model.addAttribute("church", getChurchInfo());
        model.addAttribute("startdate", DateUtil.dateFormat(oneEvent.getStartDateCont()));
        model.addAttribute("enddate", DateUtil.dateFormat(oneEvent.getEndDateCont()));
        model.addAttribute("contact",oneEvent.getContact());
        logger.debug("event" + oneEvent);
        return "event";
    }
    
    @GetMapping("/article/{id}")
    public String showArticlePage(@PathVariable String title, ModelMap model){
        logger.debug("GET /article/" + title);

        model.addAttribute("event", pageService.retrieveOnePage(title));
        model.addAttribute("page", pageService.retrieveOnePage("event"));
        return "event";
    }
    
    @GetMapping("/home")
    public String getHome(){
        logger.debug("GET /home");
        return "index";
    }
    
    private ChurchInfo getChurchInfo(){
         List<ChurchInfo> churchInfo = churchRepository.findAll();
         return churchInfo.get(0);
    }
}
