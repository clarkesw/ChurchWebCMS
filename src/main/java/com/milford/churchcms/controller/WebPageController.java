/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.service.WebPageService;
import java.util.Date;
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
public class WebPageController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(WebPageController.class);
    
    @Autowired
    WebPageService service;
    
    @GetMapping("/list-pages")
    public String showPages(ModelMap model){
        model.put("pages", service.retrievePages());
        return "cms/list-pages";
    }
    
    @PostMapping("/update-page")
    public String updatePagePost(ModelMap model,@Valid @ModelAttribute("page") WebPage page, @ModelAttribute("constants") AppConstants constants, BindingResult result){
        if(result.hasErrors())
            return "cms/add-page";
        page.setLastModified(new Date());
        service.updatePage(page);
        return "redirect:/cms/list-pages";
    }
    
    @GetMapping("/update-page")
    public String updateShowEvent(ModelMap model, @RequestParam int id){
        WebPage page = service.retrieveOnePage(id);
        model.put("page", page);
        return "cms/add-page";
    }   
    
}
