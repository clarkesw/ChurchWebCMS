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
import com.milford.churchcms.repository.ArticleRepository;
import com.milford.churchcms.repository.BannerRepository;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.SermonRepository;
import com.milford.churchcms.repository.StaffRepository;
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
public class UiController extends BaseController{
    
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
    StaffRepository staffRepository;
    
    @Autowired
    BannerRepository bannerRepository;
    
    @Autowired 
    private HttpSession session;
        
    @GetMapping("/calEventArray")
    @ResponseBody
    public List<CalendarEvent> getCalendarEvent(){
        logger.debug("GET /calEventArray");
        return eventRepository.findAll();
    }
    
    @GetMapping("/bannerMessage")
    @ResponseBody
    public String getBanner(){
        Optional<Banner> message = bannerRepository.findTopByOrderByIdDesc();
        logger.debug("GET /bannerMessage");
        
        if(message.isPresent())
            return message.get().getMessage();
        
        return ""; 
    }
    
    @GetMapping("/page/{name}")
    public String showPage(@PathVariable String name, ModelMap model){
        logger.debug("GET /page/" + name);
        
        Optional<Sermon> sermon = sermonRepository.findTopByOrderBySermonDateDesc();
        Optional<ChurchInfo> myChurch = churchRepository.findTopByOrderByIdDesc();
        Article article = articleRepository.findTopByOrderByLastModified();
        
        model.addAttribute("article", article);
        if(myChurch.isPresent()){
            model.addAttribute("church", myChurch.get());
            model.addAttribute("services", myChurch.get().getServiceTimes());          
        }
        if(sermon.isPresent())
            model.addAttribute("sermon", sermon.get());
        model.addAttribute("page", pageRepository.findByPageName(name));
        
        logger.debug("  sermon : {}", sermon);
        logger.debug("  church : {}", myChurch);
        logger.debug("  article : {}", article);
        
        if("calendar".equals(name)){
            logger.debug(" Calendar " + pageRepository.findByPageName(name));
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
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isPresent())
            model.addAttribute("contact", staff.get());
        return "contact";
    }
    
    @GetMapping("/event/{id}")
    public String showEventPage(@PathVariable int id, ModelMap model){
        logger.debug("GET /event/" + id);
        Optional<CalendarEvent> calEvent = eventRepository.findById(id);
        CalendarEvent oneEvent = calEvent.get();
       
        Optional<Staff> staffer = staffRepository.findByFullName(oneEvent.getContactName());
        
        model.addAttribute("event",oneEvent);
        model.addAttribute("church", getChurchInfo());
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
        Optional<Article> artOption = articleRepository.findById(id);
        Article article = artOption.get();
        logger.debug("GET /article/  : {}" + article);     

        model.addAttribute("article", article);
        model.addAttribute("church", getChurchInfo());
        model.addAttribute("page", pageService.retrieveOnePage("event"));
        return "article";
    }
  
    @GetMapping("/sermon/{id}")
    public String showSermonPage(@PathVariable int id, ModelMap model){
        Optional<Sermon> sermonOpt = sermonRepository.findById(id);
        Sermon sermon = sermonOpt.get();
        logger.debug("GET /sermon/  : {}" + sermon);     

        model.addAttribute("sermon", sermon);
        model.addAttribute("church", getChurchInfo());
        model.addAttribute("page", pageService.retrieveOnePage("event"));
        return "sermon";
    }
    
//    @GetMapping("/prayer")
//    public String showPrayerPage(ModelMap model){
//        logger.debug("GET /prayer/  " );     
//
//        model.addAttribute("church", getChurchInfo());
//        model.addAttribute("page", pageService.retrieveOnePage("event"));
//        return "prayer";
//    }
    
    @GetMapping("/toolbar")
    public String toolBar(){
        return "toolbar";
    }
    
    private ChurchInfo getChurchInfo(){
         List<ChurchInfo> churchInfo = churchRepository.findAll();
         return churchInfo.get(0);
    }
}
