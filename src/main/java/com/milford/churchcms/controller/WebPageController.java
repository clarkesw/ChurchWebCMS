/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.exception.DBException;
import com.milford.churchcms.exception.DbExceptionDescription;
import com.milford.churchcms.repository.WebPageRepository;
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
public class WebPageController{
    
    public Logger logger = LoggerFactory.getLogger(WebPageController.class);
    
    @Autowired
    WebPageService service;
    
    @Autowired
    WebPageRepository repository;
    
    @GetMapping("/list-pages")
    public String showPages(ModelMap model){
        logger.debug("showPages ");
        model.put("pages", repository.findAll());
        return "cms/list-pages";
    }
    
    @PostMapping("/update-page")
    public String updatePagePost(ModelMap model,@Valid @ModelAttribute("page") WebPage page, 
                                    @ModelAttribute("constants") AppConstants constants, BindingResult result){
        logger.debug("updatePagePost WebPage: {}", page);
        if(result.hasErrors())
            return "/cms/add-page";

        repository.delete(page); 
        repository.save(new WebPage(page.getTitle(),page.getBgImage(),page.getLink(),page.getPageName(),page.getMessage(),page.isIsVisible()));
        return "redirect:list-pages";
    }
    
    @GetMapping("/update-page")
    public String updateShowPage(ModelMap model, @RequestParam int id){
        logger.debug("updateShowPage ID: {}",id);
        Optional<WebPage> page = repository.findById(id);
        
        model.put("page", page.get());
        return "/cms/add-page";
    }   
    
    @GetMapping("/add-pages")
    public String showAddWebPage(ModelMap model,@Valid @ModelAttribute("page") WebPage page){    
        logger.debug("showAddWebPage WebPage: {}",page);
        return "cms/add-page";
    }
    
    @PostMapping("/add-pages")
    public String addWebPage(ModelMap model,@Valid @ModelAttribute("page") WebPage page, BindingResult result){
        logger.debug("addWebPage WebPage : {}",page);
        if(result.hasErrors())
            return "cms/add-page";

        try{
            repository.save(new WebPage(page.getTitle(),page.getBgImage(), page.getLink(), page.getPageName(), 
                page.getMessage(), page.isIsVisible()));   
        }catch(Exception e){
            throw new DBException("Add Web Page ",DbExceptionDescription.NOT_UNIQUE);
        }
    
        return "redirect:/cms/list-pages";
    }
  
}
