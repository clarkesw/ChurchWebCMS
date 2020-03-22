/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.repository.UserRepository;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffController extends BaseController{
    
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
        
    @GetMapping("/list-staffers")
    public String showArticle(ModelMap model){
        List<Staff> allstaff = repository.findAll();
        logger.debug("GET /list-staffers Staffers: {}",allstaff.size()); 
        
        model.put("staffers", allstaff); 
        return "cms/list-staffers";
    }
 
    @GetMapping("/add-staff")
    public String showAddStaff(ModelMap model, @RequestParam boolean isUser){   
        User currentUser = (User)session.getAttribute("loggedInUser");
        logger.debug("GET /add-staff User: {}", currentUser);  
        Object[] keyCarriers = AppConstants.textMessageAddress.keySet().toArray();
        
        Staff staff = new Staff();
        
        if("admin".equalsIgnoreCase(currentUser.getRole()) && isUser){
            staff.setIsUser(true);
            model.addAttribute("unlockRole","good");
        }
            
       logger.debug("   isUser: {}", staff);  
       
        model.addAttribute("staff", staff);
        model.addAttribute("roles", AppConstants.roles);
        model.addAttribute("carriers", keyCarriers);
        model.addAttribute("positions", AppConstants.positions);

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
        Staff staff = repository.findById(id).get();
        Object[] keyCarriers = AppConstants.textMessageAddress.keySet().toArray();
        
        
        if("admin".equals(currentUser.getRole()) && staff.isIsUser())
            model.addAttribute("unlockRole","good");
        
        if(staff.isIsUser()){
            staff.getUser().setBlankPassword();
            logger.debug("   isUser : {}",staff.isIsUser());
            
        }
        
        model.addAttribute("carriers", keyCarriers);
        model.addAttribute("roles", AppConstants.roles);
        model.addAttribute("positions", AppConstants.positions);
        model.addAttribute("staff", staff);
        
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
        
}
