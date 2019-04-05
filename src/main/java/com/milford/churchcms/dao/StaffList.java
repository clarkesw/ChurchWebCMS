/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clarke
 */
public class StaffList {
    
    @Autowired
    StaffRepository repository;
    
    public Logger logger = LoggerFactory.getLogger(StaffList.class);
    private List<Staff> staffers;

    public StaffList() {}

    public StaffList(List<Staff> staffers) {
        this.staffers = staffers;
    }

    public List<Staff> getStaffers() {
        return staffers;
    }

    public void setStaffers(List<Staff> staffers) {
        this.staffers = staffers;
    }
    
    public int length(){
        return staffers.size();
    }
}
