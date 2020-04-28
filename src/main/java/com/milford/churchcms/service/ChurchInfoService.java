/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.ServiceTimes;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.ServiceTimeRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clarke
 */
public class ChurchInfoService {
    public Logger logger = LoggerFactory.getLogger(ChurchInfoService.class);

    @Autowired
    ChurchRepository churchRepository;
    
    @Autowired
    ServiceTimeRepository timeRepository;    
    
    @Autowired
    StaffRepository staffRepository;
        
    public Optional<ChurchInfo> showInfo(){
        return churchRepository.findTopByOrderByIdDesc();
    }
    
    public void updateInfoPost(ChurchInfo info, int id){
        if(id != -1)
            churchRepository.deleteAll();
        
        List<Staff> staffers = staffRepository.findAll();
        churchRepository.save(new ChurchInfo(info.getName(),info.getMissionStatement(),info.getEmail(),
                info.getAddress(),info.getTelephone(),staffers)); 
    }
    
    public List<Staff> updateShowInfo(){
        return staffRepository.findAll();
    }
    
    public void updateServicePost(ServiceTimes time){
        ChurchInfo myInfo = returnInfo();
        churchRepository.delete(myInfo);
        
        myInfo.getServiceTimes().add(time);
        churchRepository.save(myInfo);
    }
    
    public List<ServiceTimes> updateShowService(){
        return timeRepository.findAll();
    }
    
    public ChurchInfo returnInfo(){
        List<ChurchInfo> infoList = churchRepository.findAll();
        ChurchInfo myInfo = null;        
        for(ChurchInfo info : infoList){
            myInfo = info;
        }
        
        return myInfo;
    }
}
