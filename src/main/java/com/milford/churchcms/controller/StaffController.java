/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffController{
    
    public Logger logger = LoggerFactory.getLogger(StaffController.class);
    
    @Autowired
    StaffRepository repository;
    
    @Autowired
    CalendarEventRepository calendarRepo;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-staffers")
    public String showArticle(ModelMap model){
        String username = getLoggedInName(model);
        model.put("staffers", repository.findAll()); //service.retrieveArticles());
        return "cms/list-staffers";
    }
        
//    @GetMapping("/listArticlesForPage")
//    public String showArticle(ModelMap model,@RequestParam String page){
//     //   String username = getLoggedInName(model);
//        session.setAttribute("ArticalWebPage", page);
//        model.put("articles", repository.findAllByPageName(page));
//        return "cms/list-articles";
//    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @GetMapping("/add-staff")
    public String showAddStaff(ModelMap model, @ModelAttribute("staff") Staff staff){   
        logger.debug("showAddStaff"); 
        return "cms/add-staff";
    }
    
    @PostMapping("/add-staff")
    public String addStaff(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("addStaff Staff : {}",staff); 
        if(result.hasErrors())
            return "cms/add-staff";
        
        repository.save(staff); 
        return "redirect:list-staffers";
    }
    
    @GetMapping("/delete-staff")
    public String deleteStaff(@RequestParam int id){
        logger.debug("deleteStaff ID : {}",id); 
        repository.deleteById(id);  //service.deleteArticle(id);
        return "redirect:list-staffers";
    }
    
    @PostMapping("/update-staff")
    public String updateStaffPost(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("updateStaffPost Staff : {}",staff);
        if(result.hasErrors())
            return "cms/add-staff";
        repository.delete(staff);
        repository.save(staff);  
        return "redirect:list-staffers";
    }
    
    @GetMapping("/update-staff")
    public String updateShowStaff(ModelMap model, @RequestParam int id){
        logger.debug("updateShowStaff  ID : {}",id);
        Optional<Staff> staff = repository.findById(id);
        
        model.put("staff", staff.get());
        return "cms/add-staff";
    }     
    
    @PostMapping("/addContactToEvent")
    public String addAddressForStaff(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result, 
            @RequestParam String fisrtName, @RequestParam String lastName, @RequestParam int id){
        logger.debug("POST addContactToEvent  Name : {}",fisrtName);

        Optional<CalendarEvent> myEvent = calendarRepo.findById(id);
        CalendarEvent event = myEvent.get();
        logger.debug("   ChurchInfo myEvent : {}", event);
         
        calendarRepo.delete(event);
        event.setContact(staff);
        
        calendarRepo.save(event);
        
        return "redirect:login"; 
    }    
 
    @GetMapping("/addContactToEvent")
    public String addContactToEvent(ModelMap model, @RequestParam int contact_id){
        logger.debug("GET addContactToEvent  Contact ID : {}",contact_id);
       
        if(contact_id != -1){
            Staff staff = repository.findById(contact_id).get();
            model.put("contact",staff);
        }else{
            model.put("contact", new Staff());
        }
        return "cms/add-contact";
    }    

    private CalendarEvent returnInfo(){
        List<CalendarEvent> infoList = calendarRepo.findAll();
        CalendarEvent myInfo = null;        
        for(CalendarEvent info : infoList){
            myInfo = info;
 
        }
        return myInfo;
    }
}
