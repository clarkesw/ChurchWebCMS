/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.repository.SermonRepository;
import java.util.Collection;
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
    SermonRepository repository;
    
    @Autowired 
    private HttpSession session;
        
    @GetMapping("/list-sermons")
    public String showSermon(ModelMap model){
        List<Sermon> sermons = repository.findAll();
        logger.debug("GET /list-sermons Sermon size: {}",sermons.size());
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

        Optional<Sermon> lastSermon = repository.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon.isPresent()) ? lastSermon.get().getId() + 1 : 1;
        logger.debug("   Sermon ID : {}",lastSermonId);
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), sermon.getDescription(),
                sermon.getSermonDate(),sermon.getPassages()));
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
        logger.debug("POST /update-sermon : {}",sermon);
        if(result.hasErrors())
            return "cms/add-sermon";
        
        repository.delete(sermon);
        Optional<Sermon> lastSermon = repository.findTopByOrderBySermonDateDesc();
        List<Passage> passages = (List<Passage>)session.getAttribute("passages");
        int lastSermonId = (lastSermon.isPresent()) ? lastSermon.get().getId() + 1 : 1;
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), sermon.getDescription(),
                sermon.getSermonDate(),passages));
        return "redirect:list-sermons";
    }
    
    @GetMapping("/update-sermon")
    public String updateShowSermon(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-sermon ID: {}", id);
        Optional<Sermon> sermon = repository.findById(id);
        model.addAttribute("passageList", sermon.get().getPassages());
        logger.debug("   passageList: {}", sermon.get().getPassages());
        session.setAttribute("passages", sermon.get().getPassages());
        if(sermon.isPresent())
            model.put("sermon", sermon.get());
        return "cms/add-sermon";
    }
    
    @PostMapping("/addDescriptionToSermon")
    public String addDescriptionSermonPost(ModelMap model,@Valid @ModelAttribute("description") String description, BindingResult result){
        logger.debug("POST /addDescriptionToSermon : {}",description);
        if(result.hasErrors())
            return "cms/add-description";
        
        Integer sermonId = (Integer)session.getAttribute("sermonId");
        Integer lastSermonIdRep = repository.getGreatestSid().get(0);
        Sermon sermon = repository.findById(sermonId).get();
       
        repository.deleteById(sermonId);
        
        List<Passage> passages = (List<Passage>)session.getAttribute("passages");
        int lastSermonId = (lastSermonIdRep != null) ? lastSermonIdRep + 1 : 1;
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), description,
                sermon.getSermonDate(),passages));
        return "redirect:list-sermons";
    }
    
    @GetMapping("/addDescriptionToSermon")
    public String addDescriptionSermon(ModelMap model, @RequestParam int id){
        logger.debug("GET /addDescriptionToSermon ID: {}", id);
        session.setAttribute("sermonId", id);

        return "cms/add-sermon";
    }
}
