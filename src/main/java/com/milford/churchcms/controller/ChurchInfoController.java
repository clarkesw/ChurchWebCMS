/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.service.ChurchInfoService;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class ChurchInfoController{
    
    public Logger logger = LoggerFactory.getLogger(ChurchInfoController.class);
    
    @Autowired
    ChurchInfoService service;
        
    @GetMapping("/churchInfo")
    public String showEvent(ModelMap model){
        String username = getLoggedInName(model);
        model.put("info", service.getChurchInfo());
        return "cms/list-info";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @PostMapping("/update-info")
    public String updateInfoPost(ModelMap model,@Valid @ModelAttribute("info") ChurchInfo info, BindingResult result){
        if(result.hasErrors())
            return "cms/add-event";
        
        service.updateInfo(info);
        return "redirect:list-info";
    }
    
    @GetMapping("/update-info")
    public String updateShowEvent(ModelMap model){
        ChurchInfo info = service.getChurchInfo();
        
        logger.debug("Updated Info for {}" + info.getName());
        model.put("info", info);
        return "cms/add-info";
    }    
}
