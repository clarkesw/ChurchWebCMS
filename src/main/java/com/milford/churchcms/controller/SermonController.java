/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Description;
import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.service.SermonService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class SermonController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(SermonController.class);
    
    @Autowired
    SermonService service;
    
    @Autowired 
    private HttpSession session;
        
    @GetMapping("/list-sermons")
    public String showSermon(ModelMap model){
        List<Sermon> sermons = service.showSermon();
        logger.debug("GET /list-sermons Sermon size: {}",sermons.size());
        model.put("sermons", sermons);

        return "cms/list-sermons";
    }
 
    @GetMapping("/add-sermons")
    public String showAddSermonGet(ModelMap model, @ModelAttribute("sermon") Sermon sermon){     
        logger.debug("GET /add-sermons Sermon : {}",sermon);
        return "cms/add-sermon";
    }
    
    @PostMapping("/add-sermons")
    public String addSermonPost(ModelMap model,@Valid @ModelAttribute("sermon") Sermon sermon, BindingResult result){
        logger.debug("POST /add-sermons Sermon : {}",sermon);
        if(result.hasErrors())
            return "cms/add-sermon";

        service.addSermonPost(sermon);
        return "redirect:list-sermons";
    }
    
    @GetMapping("/delete-sermon")
    public String deleteSermon(@RequestParam int id){
        logger.debug("/delete-sermon Sermon : {}",id);
        service.deleteSermon(id);
        return "redirect:list-sermons";
    }
        
    @PostMapping("/update-sermon")
    public String updateSermonPost(ModelMap model,@Valid @ModelAttribute("sermon") Sermon sermon, BindingResult result){
        logger.debug("POST /update-sermon : {}",sermon);
        if(result.hasErrors())
            return "cms/add-sermon";
        
        List<Passage> passages = (List<Passage>)session.getAttribute("passages");
        service.updateSermonPost(sermon, passages);
        return "redirect:list-sermons";
    }
    
    @GetMapping("/update-sermon")
    public String updateShowSermonGet(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-sermon ID: {}", id);
        Optional<Sermon> sermon = service.updateShowSermonGet(id);
        logger.debug("   passageList: {}", sermon.get().getPassages());
        
        model.addAttribute("passageList", sermon.get().getPassages());
        session.setAttribute("passages", sermon.get().getPassages());
        
        if(sermon.isPresent())
            model.put("sermon", sermon.get());
        return "cms/add-sermon";
    }
    
    @PostMapping("/addDescriptionToSermon")
    public String addDescriptionSermonPost(ModelMap model,@Valid @ModelAttribute("description") Description description, BindingResult result){
        logger.debug("POST /addDescriptionToSermon : {}",description);
        if(result.hasErrors())
            return "cms/add-description";
        
        Integer sermonId = (Integer)session.getAttribute("sermonId");
        List<Passage> passages = (List<Passage>)session.getAttribute("passages");
        service.addDescriptionSermonPost(passages, description, sermonId);
        return "redirect:list-sermons";
    }
    
    @GetMapping("/addDescriptionToSermon")
    public String addDescriptionSermonGet(ModelMap model, @RequestParam int id){
        logger.debug("GET /addDescriptionToSermon ID: {}", id);
        session.setAttribute("sermonId", id);
        Optional<Sermon> sermon = service.updateShowSermonGet(id);
        
        model.addAttribute("passages", sermon.get().getPassages());
        if(sermon.isPresent() && sermon.get().getDescription() != null){       
            model.addAttribute("description", sermon.get().getDescription());
        }else{
            model.addAttribute("description", new Description());
        }
        
        return "cms/add-description";
    }
}
