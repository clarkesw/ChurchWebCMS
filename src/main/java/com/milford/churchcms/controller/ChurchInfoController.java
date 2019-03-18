/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.service.ChurchInfoService;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class ChurchInfoController{
    
    public Logger logger = LoggerFactory.getLogger(ChurchInfoController.class);
    
    @Autowired
    ChurchInfoService service;
    
    @Autowired
    ChurchRepository churchRepository;
        
    @GetMapping("/list-info")
    public String showEvent(ModelMap model){
        logger.debug("GET showEvent ");
        model.put("info", returnInfo());
        return "cms/list-info";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @PostMapping("/update-info")
    public String updateInfoPost(ModelMap model,@Valid @ModelAttribute("info") ChurchInfo info, BindingResult result, 
            @RequestParam int id){
        logger.debug("POST updateInfoPost Info :{}",info);
        if(result.hasErrors())
            return "cms/add-event";
        
        churchRepository.deleteById(id);//.delete(info);
        churchRepository.save(info);  
        return "redirect:list-info";
    }
    
    @GetMapping("/update-info")
    public String updateShowEvent(ModelMap model){
        ChurchInfo myInfo = returnInfo();
        logger.debug("GET updateInfoPost Church Info : {}",myInfo);
        
        if(myInfo != null){      
           model.put("info", myInfo);         
        }else{
           model.put("info", new ChurchInfo());  
        }   
        
        return "cms/add-info";
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
