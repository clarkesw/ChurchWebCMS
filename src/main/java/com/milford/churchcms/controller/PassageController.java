/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.repository.PassageRepository;
import com.milford.churchcms.repository.SermonRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class PassageController{
    
    public Logger logger = LoggerFactory.getLogger(PassageController.class);
    
    @Autowired
    PassageRepository repository;
    
    @Autowired
    SermonRepository sermonRepo;
        
    @Autowired 
    private HttpSession session;
        
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-passages")
    public String showSermon(ModelMap model){
        String username = getLoggedInName(model);
        List<Passage> passages = repository.findAll();
        model.put("passages", passages);
        if(!passages.isEmpty())
            logger.debug("showEvent Passage 1 : {}" + passages.get(0));
        return "cms/list-passages";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @GetMapping("/add-passages")
    public String showAddSermon(ModelMap model, @ModelAttribute("passage") Passage passage){     
        logger.debug("GET /add-passages Passage : {}",passage);
        return "cms/add-passage";
    }
    
    @PostMapping("/add-passages")
    public String addSermon(ModelMap model,@Valid @ModelAttribute("passage") Passage passage, BindingResult result){
        logger.debug("POST /add-passages Passage : {}",passage);
        if(result.hasErrors())
            return "cms/add-passage";

        Passage lastSermon = repository.findTopByOrderByIdDesc();
        
        repository.save(passage);
        return "redirect:list-passages";
    }
    
    @GetMapping("/delete-passage")
    public String deleteSermon(@RequestParam int id){
        logger.debug("GET /delete-passage Passage : {}",id);
        repository.deleteById(id);
        return "redirect:list-passages";
    }
    
    @PostMapping("/update-passage")
    public String updateSermonPost(ModelMap model,@Valid @ModelAttribute("passage") Passage passage, BindingResult result){
        logger.debug("POST /update-passage eventId : {}",passage.getId());
        if(result.hasErrors())
            return "cms/add-passage";
        
        repository.delete(passage);
        repository.save(passage);
        return "redirect:list-passages";
    }
    
    @GetMapping("/update-passage")
    public String updateShowSermon(ModelMap model, @RequestParam int id){
        logger.debug("POST /update-passage ID: {}", id);
        Optional<Passage> passage = repository.findById(id);

        if(passage.isPresent())
            model.put("passage", passage.get());
        return "cms/add-passage";
    }
    
    @PostMapping("/addPassagesToSermon") 
    public String addPassagesToSermon(ModelMap model,@Valid @ModelAttribute("passage") Passage passage){
        logger.debug("POST /addPassagesToSermon  Name : {}",passage);
        int sermonId = (Integer)session.getAttribute("SermonID");
       
        Optional<Sermon> mySermon = sermonRepo.findById(sermonId);
        Sermon sermon = mySermon.get();     
        sermonRepo.delete(sermon);
        
        sermon.getPassages().add(passage); 
        List<Passage> passes = sermon.getPassages();
        
        Sermon lastSermon = sermonRepo.findTopByOrderByIdDesc();
        int lastSermonId = (lastSermon != null) ? lastSermon.getId() + 1 : 1;
        sermonRepo.save(new Sermon(lastSermonId, sermon.getTitle(),sermon.getSubTitle(),sermon.getDescription(),sermon.getSermonDate(),
                new ArrayList<>(passes)));
        
        return "redirect:list-sermons"; 
    }    
 
    @GetMapping("/addPassagesToSermon")
    public String addPassagesToSermon(ModelMap model, @RequestParam int id){ 
        logger.debug("GET /addPassagesToSermon  ");
        session.setAttribute("SermonID", id);
        
        List<Passage> passages = repository.findAll();
        model.put("passageList", passages);
        model.put("books", AppConstants.books);
        model.put("passage", new Passage());
        
        return "cms/add-passage";
    } 
    
//    @GetMapping("/getBooksOfBible")
//    public String getBooksOfBible(ModelMap model){ 
//        logger.debug("GET addPassagesToSermon  ");
//
//        model.put("books", AppConstants.books);
//
//        return "cms/add-passage";
//    }     

    private String createURL(int id){
        return "/passage/"+id;
    }
}
