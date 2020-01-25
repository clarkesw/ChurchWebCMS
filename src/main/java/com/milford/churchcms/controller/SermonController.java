/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.SermonRepository;
import java.util.Collection;
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
public class SermonController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(SermonController.class);
    
    @Autowired
    SermonRepository repository;
        
    @GetMapping("/list-sermons")
    public String showSermon(ModelMap model){
        String username = getLoggedInName(model);
        List<Sermon> sermons = repository.findAll();
        logger.debug("GET /list-sermons Sermon #: {}",sermons.size());
        model.put("sermons", sermons);

        return "cms/list-sermons";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @GetMapping("/add-sermons")
    public String showAddSermon(ModelMap model, @ModelAttribute("sermon") Sermon sermon){     
        logger.debug("GET /add-sermons Sermon : {}",sermon);
        return "cms/add-sermon";
    }
    
    @PostMapping("/add-sermons")
    public String addSermon(ModelMap model,@Valid @ModelAttribute("sermon") Sermon sermon, BindingResult result){
        logger.debug("POST /add-sermons Sermon : {}",sermon);
        if(result.hasErrors())
            return "cms/add-sermon";

        Sermon lastSermon = repository.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon != null) ? lastSermon.getId() + 1 : 1;
        
        repository.save(new Sermon(lastSermonId, sermon.getTitle(),sermon.getSubTitle(),sermon.getDescription(),sermon.getSermonDate()));
        return "redirect:list-sermons";
    }
    
    @GetMapping("/delete-sermon")
    public String deleteSermon(@RequestParam int id){
        logger.debug("/delete-sermon Sermon : {}",id);
        repository.deleteById(id);
        return "redirect:list-sermons";
    }
    
    @PostMapping("/update-sermon")
    public String updateSermonPost(ModelMap model,@Valid @ModelAttribute("sermon") Sermon sermon, BindingResult result){
        logger.debug("POST /update-sermon eventId : {}",sermon.getId());
        if(result.hasErrors())
            return "cms/add-sermon";
        
        repository.delete(sermon);
        Sermon lastSermon = repository.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon != null) ? lastSermon.getId() + 1 : 1;
        
        repository.save(new Sermon(lastSermonId, sermon.getTitle(),sermon.getSubTitle(),sermon.getDescription(),sermon.getSermonDate()));
        return "redirect:list-sermons";
    }
    
    @GetMapping("/update-sermon")
    public String updateShowSermon(ModelMap model, @RequestParam int id){
        logger.debug("POST /update-sermon ID: {}", id);
        Optional<Sermon> sermon = repository.findById(id);

        if(sermon.isPresent())
            model.put("sermon", sermon.get());
        return "cms/add-sermon";
    }
    
    private List<String> firstAndLastName(List<Staff> staffers){
        List<String> names = null;
        for(Staff staff : staffers){
            names.add(staff.getFirstName() + " " +staff.getLastName());
        }
        return names;
    }

    private String createURL(int id){
        return "/sermon/"+id;
    }
}
