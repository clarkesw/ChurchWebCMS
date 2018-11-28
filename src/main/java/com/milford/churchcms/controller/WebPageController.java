/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.service.EventService;
import com.milford.churchcms.service.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class WebPageController extends BaseController{
    
    @Autowired
    WebPageService service;
    
    @GetMapping("/show-home")
    public String showPages(ModelMap model){
        model.put("events", service.retrievePages());
        return "list-pages";
    }
}
