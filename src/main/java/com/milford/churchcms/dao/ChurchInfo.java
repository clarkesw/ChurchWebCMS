/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "CHURCH")
public class ChurchInfo {
    @Id
    @GeneratedValue
    @Column(name = "sid")
    private int id;
    private String name;
    private String missionStatement;
    private String email;
    private String url;
    private String telephone;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Staff.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<Staff> staffers;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ServiceTimes.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<ServiceTimes> serviceTimes;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Staff.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<Staff> staffers;    

    public ChurchInfo() {}

    public ChurchInfo(String name, String missionStatement, String email, Address address, String telephone, List<Staff> staffers) {
        this.id = id;
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.staffers = staffers;
    }
        
    public ChurchInfo(int id,String name, String missionStatement, String email, Address address, String telephone, List<Staff> staffers) {
        this.id = id;
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.staffers = staffers;
    }

    public ChurchInfo(ChurchInfo info) {
        this.name = info.getName();
        this.missionStatement = info.getMissionStatement();
        this.email = info.getEmail();
        this.address = info.getAddress();
        this.telephone = info.getTelephone();
        this.staffers = info.getStaffers();
        this.url = info.getUrl();
        this.serviceTimes = info.getServiceTimes();
    }

    public List<Staff> getStaffers() {
        return staffers;
    }

    public void setStaffers(List<Staff> staffers) {
        this.staffers = staffers;
    }

    public List<ServiceTimes> getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(List<ServiceTimes> serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Staff> getStaffers() {
        return staffers;
    }

    public void setStaffers(List<Staff> staffers) {
        this.staffers = staffers;
    }

    @Override
    public String toString() {
        return "ChurchInfo{" + "id=" + id + ", name=" + name + ", missionStatement=" + missionStatement + ", email=" + email + ", url=" + url + ", telephone=" + telephone + ", address=" + address + ", staffers=" + staffers + ", serviceTimes=" + serviceTimes + '}';
    }

}
