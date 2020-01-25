/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.AddressRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
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
public class AddressController{
    
    public Logger logger = LoggerFactory.getLogger(AddressController.class);
    
    @Autowired 
    private HttpSession session;
    
    @Autowired
    AddressRepository repository;
    
    @Autowired
    StaffRepository staffRepository;
    
    @Autowired
    ChurchRepository churchRepository;
        
    @GetMapping("/addAddressForStaff")
    public String addAddressForStaff(ModelMap model, @RequestParam String fisrtName, @RequestParam String lastName){
        
        logger.debug("GET addAddressForStaff  Name : {}",fisrtName);
        
        model.put("name", fisrtName);
        Address address = staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0).getHomeAddress();
        
        if(address != null){
            model.put("address",address);
        }else{
            model.put("address", new Address());
        }
        logger.debug(" Address: {}",address);
        return "cms/add-address";
    }    
    
    @PostMapping("/addAddressForStaff")
    public String addAddressForStaff(ModelMap model,@Valid @ModelAttribute("address") Address address, BindingResult result, 
            @RequestParam String fisrtName, @RequestParam String lastName){
        logger.debug("POST addAddressForStaff  Name : {}",fisrtName);

        Staff tempStaff = staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0);
        logger.debug("Address: {}",address);
        
        staffRepository.delete(tempStaff);
        tempStaff.setHomeAddress(address);
        
        staffRepository.save(tempStaff);
        
        return "redirect:list-staffers"; 
    }    
 
    @GetMapping("/addAddressForChurch")
    public String addAddressForChurch(ModelMap model, @RequestParam int address_id){
        logger.debug("GET addAddressForChurch  Address : {}",address_id);
        
        model.put("name", "Church");
        session.setAttribute("AddressID", address_id);
        if(address_id != -1){
            Address churchAddress = repository.findById(address_id).get();
            model.put("address",churchAddress);
        }else{
            model.put("address", new Address());
        }
        return "cms/add-address";
    }    
    
    @PostMapping("/addAddressForChurch")
    public String addAddressForChurch(ModelMap model,@Valid @ModelAttribute("address") Address address){
        logger.debug("Post addAddressForChurch  Name : {}",address);
        Integer id = (Integer)session.getAttribute("AddressID");
        ChurchInfo myInfo = returnInfo();
        logger.debug("   ChurchInfo myInfo : {}",myInfo);
         
        churchRepository.delete(myInfo);
        myInfo.setAddress(address);
        
        churchRepository.save(myInfo);
        
        return "redirect:list-info"; 
    }    
    
    private ChurchInfo returnInfo(){
        List<ChurchInfo> infoList = churchRepository.findAll();
        ChurchInfo myInfo = null;        
        for(ChurchInfo info : infoList){
            myInfo = info;
 
        }
        return myInfo;
    }
}
