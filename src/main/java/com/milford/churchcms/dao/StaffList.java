/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import com.milford.churchcms.repository.StaffRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clarke
 */
public class StaffList {
    
    @Autowired
    StaffRepository repository;
    private List<Staff> staffers;

    public StaffList() {
        staffers = repository.findAll();
    }

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

    @Override
    public String toString() {
        return "StaffList{" + "repository=" + repository + ", staffers=" + staffers + '}';
    }
}
