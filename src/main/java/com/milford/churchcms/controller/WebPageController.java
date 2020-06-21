/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.service.WebPageService;
import java.util.Optional;
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
        logger.debug("/list-pages ");
        model.put("pages", service.showPages());
        return "cms/list-pages";
    }
    
    @PostMapping("/update-page")
    public String updatePagePost(ModelMap model,@Valid @ModelAttribute("page") WebPage page, 
                                    @ModelAttribute("constants") AppConstants constants, BindingResult result){
        logger.debug("POST /update-page WebPage: {}", page);
        if(result.hasErrors())
            return "/cms/add-page";

        service.updatePagePost(page);
        return "redirect:list-pages";
    }
    
    @GetMapping("/update-page")
    public String updatePageGet(ModelMap model, @RequestParam int id){
        Optional<WebPage> page = service.findById(id);
        logger.debug("GET /update-page WebPage: {}", page.get());
        
        model.put("page", page.get());
        return "/cms/add-page";
    }   
    
    @GetMapping("/add-pages")
    public String addWebPageGet(ModelMap model,@Valid @ModelAttribute("page") WebPage page){    
        logger.debug("GET /add-pages WebPage: {}",page);
        return "cms/add-page";
    }
    
    @PostMapping("/add-pages")
    public String addWebPagePost(ModelMap model,@Valid @ModelAttribute("page") WebPage page, BindingResult result){
        logger.debug("POST /add-pages WebPage : {}",page);
        if(result.hasErrors())
            return "cms/add-page";
        Optional<WebPage> webPage = service.findById(page.getId());
       
        if(webPage.isPresent())
            service.deleteById(page.getId() +1);
        
        try{
            service.addWebPagePost(page);
        }catch(Exception e){
            e.printStackTrace();       
        }
    
        return "redirect:list-pages";
    }
  
}
