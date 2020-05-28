/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Banner;
import com.milford.churchcms.dao.Prayer;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.BannerRepository;
import com.milford.churchcms.repository.PrayerRepository;
import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class PrayerService {
    public Logger logger = LoggerFactory.getLogger(PrayerService.class);

    @Autowired
    PrayerRepository repository;
    
    @Autowired
    StaffRepository StaffRepo;
    
    @Autowired
    TextMessageService textService;
    
    public void addAddressForStaff(Prayer prayer){
        String fullName = prayer.getFirstName() + " " + prayer.getLastName();
        List<Staff> staffers = StaffRepo.findByRecievePrayerRequestsTrue();
        
        logger.debug("   name : {}", fullName);
        staffers.forEach( staff -> 
                textService.sendMessage(staff, prayer.getPrayerRquest(), fullName)
        );
 
        repository.save(prayer);
    }
    
    public List<Prayer> showPrayer(){
        return repository.findAll();
    }
}
