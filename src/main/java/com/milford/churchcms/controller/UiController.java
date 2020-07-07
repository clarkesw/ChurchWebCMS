/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Article;
import com.milford.churchcms.dao.Banner;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Prayer;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.service.UIService;
import com.milford.churchcms.util.DateUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UiController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(UiController.class);
    
    @Autowired
    UIService service;
        
    @GetMapping("/calEventArray")
    @ResponseBody
    public List<CalendarEvent> getCalendarEvent(){
        logger.debug("GET /calEventArray");
        return service.getCalendarEvent();
    }
    
    @GetMapping("/bannerMessage")
    @ResponseBody
    public String getBanner(){
        Optional<Banner> banner = service.getBanner();
        
        if(banner.isPresent()){
            logger.debug("GET /bannerMessage Message" + banner.get().getMessage());
            return banner.get().getMessage();
        }
            
        return ""; 
    }
    
    @GetMapping("/page/{name}")
    public String showPage(@PathVariable String name, ModelMap model){
        logger.debug("GET /page/" + name);
        
        Optional<Sermon> sermon = service.findSermonDateDesc();
        Optional<ChurchInfo> myChurch = service.findChurchInfoByIdDesc();
        Article article = service.findArticleByLastModified();
        
        model.addAttribute("article", article);
        if(myChurch.isPresent()){
            model.addAttribute("church", myChurch.get());
            model.addAttribute("services", myChurch.get().getServiceTimes());          
        }
        if(sermon.isPresent())
            model.addAttribute("sermon", sermon.get());
        
        logger.debug("  sermon : {}", sermon);
        logger.debug("  church : {}", myChurch);
        logger.debug("  article : {}", article);
        List<String> positionList = AppConstants.prefferedContactList;
        List<Staff> contactList = null;
        
        if("about".equals(name)){
            return "about";
        }else if("churchStaff".equals(name)){
            for(String position : positionList){
               contactList.addAll(service.findAllByPosition(position));
            }
            model.addAttribute("mainStaff", contactList);
            return "churchStaff";
        }else if("calendar".equals(name)){
            return "calendar";
        }else if("prayer".equals(name)){
            model.addAttribute("contactMethods", AppConstants.Contact.contactMethods);
            model.addAttribute("contactTimes", AppConstants.Contact.contactTimes);
            model.addAttribute("prayer", new Prayer());
            return "prayer";
        }  
                
        return "home";
    }
    
    @GetMapping("/email/{name}")
    public void sendEmail(@PathVariable String name){
        // Figure out Later.
    }
    
    @GetMapping("/staff/{id}")
    public String getStaff(@PathVariable int id, ModelMap model){
        Optional<Staff> staff = service.findStaffById(id);
        if(staff.isPresent())
            model.addAttribute("contact", staff.get());
        return "contact";
    }
    
    @GetMapping("/event/{id}")
    public String showEventPage(@PathVariable int id, ModelMap model){
        logger.debug("GET /event/" + id);
        Optional<CalendarEvent> calEvent = service.findEventById(id);
        CalendarEvent oneEvent = calEvent.get();
       logger.debug("   oneEvent : {}", oneEvent);
        Optional<Staff> staffer = service.findStaffByFullName(oneEvent);
        
        model.addAttribute("event",oneEvent);
        model.addAttribute("church", service.getChurchInfo());
        model.addAttribute("startdate", DateUtil.dateFormat(oneEvent.getStartDateCont()));
        model.addAttribute("enddate", DateUtil.dateFormat(oneEvent.getEndDateCont()));
        
        if(staffer.isPresent()){
            model.addAttribute("contact",staffer.get());
        }else{
            model.addAttribute("contact",new Staff());
        }
        
        logger.debug("    event" + oneEvent);
        return "event";
    }
    
    @GetMapping("/article/{id}")
    public String showArticlePage(@PathVariable int id, ModelMap model){
        Optional<Article> artOption = service.findArticleById(id);
        Article article = artOption.get();
        logger.debug("GET /article/  : {}" + article);     

        model.addAttribute("article", article);
        model.addAttribute("church", service.getChurchInfo());
        model.addAttribute("page", service.findByPageName("article"));
        return "article";
    }
  
    @GetMapping("/sermon/{id}")
    public String showSermonPage(@PathVariable int id, ModelMap model){
        Optional<Sermon> sermonOpt = service.findSermonById(id);
        Sermon sermon = sermonOpt.get();
        logger.debug("GET /sermon/  : {}" + sermon);     

        model.addAttribute("sermon", sermon);
        model.addAttribute("church", service.getChurchInfo());
        model.addAttribute("page", service.findByPageName("event"));
        return "sermon";
    }
       
    @GetMapping("/aboutOurChurch")
    public String aboutPage(ModelMap model){
        logger.debug("GET /aboutOurChurch  ");     
        model.addAttribute("church", service.getChurchInfo());
        return "about";
    }
    
    @GetMapping("/churchStaff")
    public String churchStaff(ModelMap model){
        logger.debug("GET /aboutOurChruch  ");     
        model.addAttribute("church", service.getChurchInfo());
        return "about";
    }
    
    @GetMapping("/testMe")
    @ResponseBody
    public String testPage(ModelMap model){
        logger.debug("GET /testMe/  " );     
        String uri = "http://labs.bible.org/api/?passage=Exodus%204:4";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
    

    
    @GetMapping("/toolbar")
    public String toolBar(){
        return "toolbar";
    }
   
}
