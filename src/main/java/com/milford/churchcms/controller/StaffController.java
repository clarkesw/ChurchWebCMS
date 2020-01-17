/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
    private int staffId;
    private String role;
    
    @Autowired
    StaffRepository repository;
    
    @Autowired
    ChurchRepository churchRepo;
    
    @Autowired
    CalendarEventRepository calendarRepo;
 
    @Autowired
    UserRepository userRepo;
        
    @Autowired 
    private HttpSession session;
        
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-staffers")
    public String showArticle(ModelMap model){
        logger.debug("GET /list-staffers"); 
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
        User currentUser = (User)session.getAttribute("loggedInUser");
        logger.debug("GET /add-staff User: {}", currentUser);  
        
        if("admin".equalsIgnoreCase(currentUser.getRole()))
            model.addAttribute("unlockRole","good");
       
        model.addAttribute("roles", AppConstants.roles);
        return "cms/add-staff";
    }
    
    @PostMapping("/add-staff")
    public String addStaff(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("POST /add-staff Staff : {}",staff); 
        if(result.hasErrors())
            return "cms/add-staff";
        staff.setConFullName();
        repository.save(staff); 
        return "redirect:list-staffers";
    }
    
    @GetMapping("/delete-staff")
    public String deleteStaff(@RequestParam int id){
        logger.debug("GET /delete-staff ID : {}",id); 
        
        Staff staff = repository.getOne(id);
        int userId = staff.getUser().getId();
        userRepo.deleteById(userId);
        repository.deleteById(id);  
        return "redirect:list-staffers";
    }
    
    @PostMapping("/update-staff")
    public String updateStaffPost(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("POST /update-staff Staff : {}",staff);
        if(result.hasErrors())
            return "cms/add-staff";
        userRepo.delete(staff.getUser());
        repository.delete(staff);
        repository.save(staff);  
        return "redirect:list-staffers";
    }
    
    @GetMapping("/update-staff")
    public String updateShowStaff(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-staff  ID : {}",id);
        User currentUser = (User)session.getAttribute("loggedInUser");
        Optional<Staff> staff = repository.findById(id);
        logger.debug("User : {}",currentUser);
        
        if("admin".equals(currentUser.getRole()))
            model.addAttribute("unlockRole","good");
        model.put("staff", staff.get());
        
        return "cms/add-staff";
    }     
    
    @PostMapping("/update-user")
    public String updateUserPost(ModelMap model,@Valid @ModelAttribute("user") User user, BindingResult result){
        List<Staff> savedStaff = null;
        ChurchInfo church = null;
        
        logger.debug("POST /update-user User : {}",user);
       
        user.setRole(role);
        if(result.hasErrors())
            return "cms/update-user";
        userRepo.delete(user);
        List<Staff> staff = repository.findAll();
        Optional<ChurchInfo> optChurch = churchRepo.findTopByOrderByIdDesc();
        
        if(optChurch.isPresent()){
            church = optChurch.get();
            churchRepo.deleteAll();
        }
        
        ListIterator<Staff> staffIterator = staff.listIterator();
        while(staffIterator.hasNext()){
            Staff nextStaff = staffIterator.next();
            if(nextStaff.getId().equals(staffId)){
                repository.deleteById(staffId);
                nextStaff.setUser(user);
                repository.save(nextStaff);            
            }
        }

        if(optChurch.isPresent()){
            church.setStaffers(savedStaff);
            churchRepo.save(church);
        }
        
        return "redirect:list-staffers";
    }
    
    @GetMapping("/update-user")
    public String updateShowUser(ModelMap model, @RequestParam int userId, @RequestParam int staffId){
        logger.debug("GET /update-user  ID : {}",userId);
        User currentUser = (User)session.getAttribute("loggedInUser");
       
        Optional<User> optUser = userRepo.findById(userId);
        User user = optUser.get();

        user.setBlankPassword();
        logger.debug("User : {}",user);
                    
       if(!currentUser.getUsername().equals(optUser.get().getUsername()))
           return "redirect:list-staffers";
  
        model.put("user", user);
        this.staffId = staffId;
        role = currentUser.getRole();
        
        return "cms/add-user";
    }     
    
    @PostMapping("/addContactToEventPost") // Need both the event.id and staff.id
    public String addContactToEvent(ModelMap model,@Valid @ModelAttribute("staffer") Staff staffer){
        logger.debug("POST /addContactToEvent  Name : {}",staffer.getFullName());
        int eventId = (Integer)session.getAttribute("EventID");
        
        Optional<CalendarEvent> myEvent = calendarRepo.findById(eventId);
        CalendarEvent event = myEvent.get();
        logger.debug("   ChurchInfo myEvent : {}", event);
        calendarRepo.delete(event);
        event.setContact(staffer);
        calendarRepo.save(event);
        
        return "redirect:login"; 
    }    
 
    @GetMapping("/addContactToEvent")
    public String addContactToEvent(ModelMap model, @RequestParam int contactId, @RequestParam int id){ //,@Valid @ModelAttribute("staffList") StaffList staffList
        logger.debug("GET /addContactToEvent  Contact ID : {}",contactId);
        session.setAttribute("EventID", id);
        
        List<Staff> staff = repository.findAll();
        model.put("staffList", staff);
        model.put("staffer", new Staff());

        logger.debug("  ******* staffList : {}", staff.size());
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
