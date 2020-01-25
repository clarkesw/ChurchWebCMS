/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.ServiceTimes;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.ServiceTimeRepository;
import com.milford.churchcms.repository.StaffRepository;
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
public class ChurchInfoController{
    
    public Logger logger = LoggerFactory.getLogger(ChurchInfoController.class);
    
    @Autowired
    ChurchRepository churchRepository;
    
    @Autowired
    ServiceTimeRepository timeRepository;    
    
    @Autowired
    StaffRepository staffRepository;
        
    @GetMapping("/list-info")
    public String showInfo(ModelMap model){
        ChurchInfo info = returnInfo();
        logger.debug("GET /list-info Info: {}",info);

        model.put("info", info);
        return "cms/list-info";
    }

    @PostMapping("/update-info")
    public String updateInfoPost(ModelMap model,@Valid @ModelAttribute("info") ChurchInfo info, BindingResult result, 
            @RequestParam int id){
        logger.debug("POST /update-info Info :{}",info);
        List<Staff> staffers = staffRepository.findAll();
        
        if(result.hasErrors())
            return "cms/add-event";

        if(id != -1)
            churchRepository.deleteAll();
        
        churchRepository.save(new ChurchInfo(info.getName(),info.getMissionStatement(),info.getEmail(),
                info.getAddress(),info.getTelephone(),staffers)); 
        return "redirect:list-info";
    }
    
    @GetMapping("/update-info")
    public String updateShowInfo(ModelMap model){
        ChurchInfo myInfo = returnInfo();
        logger.debug("GET /update-info Church Info : {}",myInfo);
        List<Staff> findAll = staffRepository.findAll();
        model.addAttribute("staffList",findAll); 
        
        if(myInfo != null){      
           model.put("info", myInfo);         
        }else{
           model.put("info", new ChurchInfo());  
        }   
        
        return "cms/add-info";
    }    
    
    @PostMapping("/editServiceTimes")
    public String updateServicePost(ModelMap model,@Valid @ModelAttribute("serviceTime") ServiceTimes time){
        logger.debug("POST /editServiceTimes Time :{}",time);
        
        ChurchInfo myInfo = returnInfo();
        churchRepository.delete(myInfo);
        
        myInfo.getServiceTimes().add(time);
        churchRepository.save(myInfo);
        return "redirect:list-info";
    }
    
    @GetMapping("/editServiceTimes")
    public String updateShowService(ModelMap model){
        List<ServiceTimes> serviceTimes = timeRepository.findAll();
        logger.debug("GET /editServiceTimes Times : {}",serviceTimes.size());
        
        model.put("serviceTimes", serviceTimes);
        model.put("serviceTime", new ServiceTimes());
        
        return "cms/add-serviceTime";
    }    
    
    private ChurchInfo returnInfo(){
        List<ChurchInfo> infoList = churchRepository.findAll();
        ChurchInfo myInfo = null;        
        for(ChurchInfo info : infoList){
            myInfo = info;
        }
        
        return myInfo;
    }
}
