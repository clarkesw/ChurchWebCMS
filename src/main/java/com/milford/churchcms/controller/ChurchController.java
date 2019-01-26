/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.Church;
import com.milford.churchcms.service.ChurchService;
import com.milford.churchcms.service.EventService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class ChurchController{
    
    public Logger logger = LoggerFactory.getLogger(ChurchController.class);
    
    @Autowired
    ChurchService service;
        
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
    public String updateInfoPost(ModelMap model,@Valid @ModelAttribute("info") Church info, BindingResult result){
        if(result.hasErrors())
            return "cms/add-event";
        
        service.updateInfo(info);
        return "redirect:list-info";
    }
    
    @GetMapping("/update-info")
    public String updateShowEvent(ModelMap model){
        Church info = service.getChurchInfo();
        
        logger.debug("Updated Info for {}" + info.getName());
        model.put("info", info);
        return "cms/add-info";
    }    
}
