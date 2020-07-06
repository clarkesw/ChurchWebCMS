/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class AlertService {
    public Logger logger = LoggerFactory.getLogger(AlertService.class);
    
    @Autowired
    StaffRepository StaffRepo;
    
    @Autowired
    TextMessageService textService;

    public void sendAlerts(String group, String message, String sender){
        List<Staff> staffers = StaffRepo.findByRecievePrayerRequestsTrue();

        List<Staff> staffersToContact = staffers
            .stream()
            .filter(stc -> group.equals(stc.getPosition()))
            .collect(Collectors.toList());

        String senderInfo = "To: " + group + " From: " + sender;
        staffersToContact.forEach( staff -> 
                textService.sendMessage(staff, message, senderInfo));       
        
    }
}
