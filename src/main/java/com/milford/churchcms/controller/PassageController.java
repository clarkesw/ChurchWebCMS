/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.service.PassageService;
import java.util.List;
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
public class PassageController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(PassageController.class);
    
    @Autowired
    PassageService service;
        
    @GetMapping("/delete-passage")
    public String deletePassage(@RequestParam int id){
        logger.debug("GET /delete-passage Passage ID: {}",id);
        service.deletePassage(id);
        return "redirect:list-sermons";
    }
    
    @PostMapping("/update-passage")
    public String updateSermonPost(ModelMap model,@Valid @ModelAttribute("passage") Passage passage, BindingResult result){
        logger.debug("POST /update-passage ID : {}",passage.getId());
        if(result.hasErrors())
            return "cms/add-passage";
        
        service.updateSermonPost(passage);
        return "redirect:list-sermons";
    }
    
    @GetMapping("/update-passage")
    public String updateShowSermonGet(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-passage ID: {}", id);
        Optional<Passage> passage = service.updateShowSermonGet(id);

        if(passage.isPresent())
            model.put("passage", passage.get());
        model.addAttribute("books", AppConstants.books);
        return "cms/update-passage";
    }
    
    @PostMapping("/addPassagesToSermon") 
    public String addPassagesToSermonPost(ModelMap model,@Valid @ModelAttribute("passage") Passage passage){
        logger.debug("POST /addPassagesToSermon  Name : {}",passage);
        int sermonId = (Integer)session.getAttribute("SermonID");
       
        service.addPassagesToSermonPost(passage, sermonId);
        return "redirect:list-sermons"; 
    }    
 
    @GetMapping("/addPassagesToSermon")
    public String addPassagesToSermonGet(ModelMap model, @RequestParam int id){ 
        logger.debug("GET /addPassagesToSermon  id: {}", id);
        session.setAttribute("SermonID", id);
        
        List<Passage> passages = service.getPassages(id);
        model.addAttribute("passageList", passages);
        model.addAttribute("books", AppConstants.books);
        model.addAttribute("passage", new Passage());
        
        return "cms/add-passage";
    } 
}
