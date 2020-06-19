/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.service.StaffService;
import java.util.List;
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
 
    @Autowired
    StaffService service;
        
    @GetMapping("/list-staffers")
    public String showStaff(ModelMap model){
        List<Staff> allstaff = service.showStaff();
        logger.debug("GET /list-staffers Staffers: {}",allstaff.size());
        User currentUser = getLoggedInUser();
        
        if("ADMIN".equalsIgnoreCase(currentUser.getRole()))
            model.addAttribute("userEdit", false);
        
        model.addAttribute("staffers", allstaff); 
        return "cms/list-staffers";
    }
 
    @GetMapping("/add-staff")
    public String addStaffGet(ModelMap model, @RequestParam boolean isAdmin){   
        String currentUserName = (String)session.getAttribute("loggedInUser");
        logger.debug("GET /add-staff currentUser: {}", currentUserName);  
        
        User currentUser = service.findByUsername(currentUserName);
        Object[] keyCarriers = AppConstants.textMessageAddress.keySet().toArray();
        
        Staff staff = new Staff();
        
        if("admin".equalsIgnoreCase(currentUser.getRole())){
            staff.setIsAdmin(true);
            model.addAttribute("unlockRole","good");
        }
       
        model.addAttribute("staff", staff);
        model.addAttribute("roles", AppConstants.roles);
        model.addAttribute("carriers", keyCarriers);
        model.addAttribute("positions", AppConstants.positions);
        model.addAttribute("prefferedContactList", AppConstants.prefferedContactList);

        return "cms/add-staff";
    }
    
    @PostMapping("/add-staff")
    public String addStaffPost(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("POST /add-staff Staff : {}",staff); 
        if(result.hasErrors())
            return "cms/add-staff";
        staff.setConFullName();
        
        service.addStaffPost(staff);
        return "redirect:list-staffers";
    }
    
    @GetMapping("/delete-staff")
    public String deleteStaff(@RequestParam int id){
        logger.debug("GET /delete-staff ID : {}",id); 
        
        service.deleteStaff(id);
        return "redirect:list-staffers";
    }
    
    @PostMapping("/update-staff")
    public String updateStaffPost(ModelMap model,@Valid @ModelAttribute("staff") Staff staff, BindingResult result){
        logger.debug("POST /update-staff Staff : {}",staff);
        if(result.hasErrors())
            return "cms/update-staff";

        service.updateStaffPost(staff);
        return "redirect:list-staffers";
    }
    
    @GetMapping("/update-staff")
    public String updateStaffGet(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-staff  ID : {}",id);
        String loggedInUser = (String)session.getAttribute("loggedInUser");
        User currentUser = service.findByUsername(loggedInUser);
        
        logger.debug("   currentUser : {}",currentUser);
        
        Staff staff = service.updateStaffGet(id);
        logger.debug("   staff : {}",staff);
        
        Object[] keyCarriers = AppConstants.textMessageAddress.keySet().toArray();
          
        if("admin".equals(currentUser.getRole()))
            model.addAttribute("unlockRole","good");
        
        if(staff.isIsAdmin()){
            staff.getUser().setBlankPassword();
            logger.debug("   isAdmin : {}",staff.isIsAdmin());    
        }
         
        model.addAttribute("carriers", keyCarriers);
        model.addAttribute("roles", AppConstants.roles);
        model.addAttribute("positions", AppConstants.positions);
        model.addAttribute("staff", staff);
        model.addAttribute("prefferedContactList", AppConstants.prefferedContactList);
        
        return "cms/update-staff";
    }     
    
    @PostMapping("/update-user")
    public String updateUserPost(ModelMap model,@Valid @ModelAttribute("user") User user, BindingResult result){
        logger.debug("POST /update-user User : {}",user);
       
        service.updateUserPost(user);
        if(result.hasErrors())
            return "cms/update-user";
        return "redirect:list-staffers";
    }
    
    @GetMapping("/update-user")
    public String updateUserGet(ModelMap model, @RequestParam int userId, @RequestParam int staffId){
        logger.debug("GET /update-user  ID : {}",userId);
        String loggedInUser = (String)session.getAttribute("loggedInUser");
        User currentUser = service.findByUsername(loggedInUser);
        
        User user = service.findUserById(userId);
        model.put("user", user);
        logger.debug("User : {}",user);
        
        if(currentUser == null)
            return "redirect:login";
        
        if(!currentUser.getUsername().equals(user.getUsername()))
           return "redirect:list-staffers";
  
        service.updateUserGet(userId, staffId, currentUser);
        
        return "cms/add-user";
    }     
      
   private User getLoggedInUser(){
       String currentUserName = (String)session.getAttribute("loggedInUser");
        User user = service.findByUsername(currentUserName);
        user.setBlankPassword();
       return user;
   }
}
