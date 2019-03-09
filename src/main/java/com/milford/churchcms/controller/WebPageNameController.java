/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.WebPageNames;
import com.milford.churchcms.repository.WebPageNameRepository;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
public class WebPageNameController{
    
    public Logger logger = LoggerFactory.getLogger(WebPageNameController.class);
    
    @Autowired
    WebPageNameRepository repository;
    
    @Autowired 
    private HttpSession session;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-names")
    public String showWebPageNames(ModelMap model){
        
        List<WebPageNames> findAll = repository.findAll();
        logger.debug("GET /list-names : {}",findAll.size()); 
        model.put("names", findAll); 
        return "cms/list-names";
    }
        
    @GetMapping("/listWebPageNames")
    public String showWebPageNames(ModelMap model,@RequestParam String page){

        session.setAttribute("ArticalWebPage", page);
        model.put("names", repository.findAll());
        return "cms/list-names";
    }
 
    @GetMapping("/add-names")
    public String showAddWebPageNames(ModelMap model, @ModelAttribute("inputname") WebPageNames name){   
        return "cms/add-name";
    }
    
    @PostMapping("/add-names")
    public String addWebPageNames(ModelMap model,@Valid @ModelAttribute("inputname") WebPageNames name, BindingResult result){
        logger.debug("addWebPageNames WebPageNames : "); 
        if(result.hasErrors())
            return "cms/add-name";
        
        repository.save(new WebPageNames(name.getName())); 
        return "redirect:list-names";
    }
    
    @GetMapping("/delete-name")
    public String deleteWebPageNames(@RequestParam int id){
        logger.debug("deleteWebPageNames ID : {}",id); 
        repository.deleteById(id);  
        return "redirect:list-names";
    }
    
    @PostMapping("/update-name")
    public String updateWebPageNamesPost(ModelMap model,@Valid @ModelAttribute("name") WebPageNames name, BindingResult result){
        logger.debug("POST updateWebPageNamesPost Name : {}",name); 
        if(result.hasErrors())
            return "cms/add-name";
        repository.delete(name);
        repository.save(new WebPageNames(name.getName()));  
        return "redirect:list-names";
    }
    
    @GetMapping("/update-name")
    public String updateShowWebPageNames(ModelMap model, @RequestParam int id){
        logger.debug("GET updateWebPageNamesPost ID : {}",id); 
        Optional<WebPageNames> name = repository.findById(id);
        
        model.put("name", name.get());
        return "cms/add-name";
    }    
}
