/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.repository.AddressRepository;
import com.milford.churchcms.repository.ChurchRepository;
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
public class AddressService {
    @Autowired
    AddressRepository repository;
    
    @Autowired
    StaffRepository staffRepository;
    
    @Autowired
    ChurchRepository churchRepository;
    public Logger logger = LoggerFactory.getLogger(AddressService.class);
    
    public Address addAddressForStaffGet(String fisrtName, String lastName){
        return staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0).getHomeAddress();
    }
    
    public void addAddressForStaffPost(String fisrtName, String lastName, Address address){
        Staff tempStaff = staffRepository.findByFirstNameInAndLastNameIn(fisrtName, lastName).get(0);
        staffRepository.delete(tempStaff);
        tempStaff.setHomeAddress(address);
        
        staffRepository.save(tempStaff);
    }
    
    public Address addAddressForChurchGet(int address_id){
        return repository.findById(address_id).get();
    }
    
    public void addAddressForChurchPost(int id, Address address){
        ChurchInfo myInfo = returnInfo();
        logger.debug("   ChurchInfo myInfo : {}",myInfo);
         
        churchRepository.delete(myInfo);
        myInfo.setAddress(address);
        
        churchRepository.save(myInfo);
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
