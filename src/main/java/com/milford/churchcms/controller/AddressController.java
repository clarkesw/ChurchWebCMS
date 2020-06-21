/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.service.AddressService;
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
public class AddressController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(AddressController.class);
    
    @Autowired
    AddressService service;
        
    @GetMapping("/addAddressForStaff")
    public String addAddressForStaffGet(ModelMap model, @RequestParam String fisrtName, @RequestParam String lastName){
        logger.debug("GET addAddressForStaff  Name : {}",fisrtName);
        
        Address address = service.addAddressForStaffGet(fisrtName, lastName);
        
        model.put("name", fisrtName);
        if(address != null){
            model.put("address",address);
        }else{
            model.put("address", new Address());
        }
        
        logger.debug(" Address: {}",address);
        return "cms/add-address";
    }    
    
    @PostMapping("/addAddressForStaff")
    public String addAddressForStaffPost(ModelMap model,@Valid @ModelAttribute("address") Address address, BindingResult result, 
            @RequestParam String fisrtName, @RequestParam String lastName){
        logger.debug("POST addAddressForStaff  Name : {}",fisrtName);
        logger.debug("   Address: {}",address);
        
        service.addAddressForStaffPost(fisrtName, lastName, address);
        return "redirect:list-staffers"; 
    }    
 
    @GetMapping("/addAddressForChurch")
    public String addAddressForChurchGet(ModelMap model, @RequestParam int address_id){
        logger.debug("GET addAddressForChurch  Address : {}",address_id);
        
        model.put("name", "Church");
        session.setAttribute("AddressID", address_id);
        if(address_id != -1){
            model.put("address",service.addAddressForChurchGet(address_id));
        }else{
            model.put("address", new Address());
        }
        return "cms/add-address";
    }    
    
    @PostMapping("/addAddressForChurch")
    public String addAddressForChurchPost(ModelMap model,@Valid @ModelAttribute("address") Address address){
        logger.debug("Post addAddressForChurch  Name : {}",address);
        Integer id = (Integer)session.getAttribute("AddressID");
        
        service.addAddressForChurchPost(id,address);
        return "redirect:list-info"; 
    }    
}
