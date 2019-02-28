/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.AddressRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
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
public class AddressController{
    
    public Logger logger = LoggerFactory.getLogger(AddressController.class);
    
    @Autowired
    AddressRepository repository;
    
    @Autowired
    StaffRepository staffRepository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
    
    @GetMapping("/addAddressForStaff")
    public String addAddressForStaff(ModelMap model,@Valid @ModelAttribute("address") Address address, @RequestParam String fisrtName, @RequestParam String lastName, 
            @RequestParam int id){
        logger.debug("Get addAddressForStaff  Name : {}",fisrtName);
        logger.debug(" Address: {}",address);

        model.put("lastName", fisrtName);
        model.put("lastName", fisrtName);
        Staff tempStaff = staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0);
        
        if(address != null){
            model.put("address",tempStaff.getHomeAddress());
        }else{
            model.put("address", new Address());
        }
        return "cms/add-address";
    }    
    
    @PostMapping("/addAddressForStaff")
    public String addAddressForStaff(ModelMap model,@Valid @ModelAttribute("address") Address address, BindingResult result, 
            @RequestParam String fisrtName, @RequestParam String lastName, HttpServletRequest request){
        logger.debug("Post addAddressForStaff  Name : {}",fisrtName);

        Staff tempStaff = staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0);
        logger.debug("Address: {}",address);
        
        staffRepository.delete(tempStaff);
        tempStaff.setHomeAddress(address);
        
        staffRepository.save(tempStaff);
        
        return "login"; 
    }    
 
}
