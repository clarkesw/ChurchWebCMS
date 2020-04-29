/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public Logger logger = LoggerFactory.getLogger(WelcomeService.class);
    
    @Autowired
    UserRepository repository;
    
    @Autowired
    StaffRepository staffRepo;
    
    public User checkLoginCredentials(String userName){
        return repository.findByUsername(userName);
    }
   
    public List<Staff> findByRecievePrayerRequestsTrue(){
        return staffRepo.findByRecievePrayerRequestsTrue();
    }

}
