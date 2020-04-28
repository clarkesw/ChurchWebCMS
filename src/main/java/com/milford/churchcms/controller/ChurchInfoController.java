/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.ServiceTimes;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.ServiceTimeRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.service.ChurchInfoService;
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
public class ChurchInfoController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(ChurchInfoController.class);
    
    @Autowired
    ChurchInfoService service;
        
    @GetMapping("/list-info")
    public String showInfo(ModelMap model){
        Optional<ChurchInfo> info = service.showInfo();
        
        if(info.isPresent()){
            logger.debug("GET /list-info Info: {}",info.get());
            model.put("info", info.get());       
        }else{
            model.addAttribute("info",new ChurchInfo());
        }

        return "cms/list-info";
    }

    @PostMapping("/update-info")
    public String updateInfoPost(ModelMap model,@Valid @ModelAttribute("info") ChurchInfo info, BindingResult result, 
            @RequestParam int id){
        logger.debug("POST /update-info Info :{}",info);
         
        if(result.hasErrors())
            return "cms/add-event";

        service.updateInfoPost(info, id);
        return "redirect:list-info";
    }
    
    @GetMapping("/update-info")
    public String updateShowInfo(ModelMap model){
        Optional<ChurchInfo> myInfo = service.showInfo();
        
        List<Staff> findAll = service.updateShowInfo();
        model.addAttribute("staffList",findAll); 
        
        if(myInfo.isPresent()){ 
           logger.debug("GET /update-info Church Info : {}", myInfo.get());
           model.put("info", myInfo.get());         
        }else{
           model.addAttribute("info", new ChurchInfo());  
        }   
        
        return "cms/add-info";
    }    
    
    @PostMapping("/editServiceTimes")
    public String updateServicePost(ModelMap model,@Valid @ModelAttribute("serviceTime") ServiceTimes time){
        logger.debug("POST /editServiceTimes Time :{}",time);
        
        service.updateServicePost(time);
        return "redirect:list-info";
    }
    
    @GetMapping("/editServiceTimes")
    public String updateShowService(ModelMap model){
        List<ServiceTimes> serviceTimes = service.updateShowService();
        logger.debug("GET /editServiceTimes Times : {}",serviceTimes.size());
        
        model.addAttribute("days", AppConstants.days);
        model.addAttribute("serviceTimes", serviceTimes);
        model.addAttribute("serviceTime", new ServiceTimes());
        
        return "cms/add-serviceTime";
    }    
    
}
