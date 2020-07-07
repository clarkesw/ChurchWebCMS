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
    @Column(name = "sid")
    private int id = 1;
    private String name;
    private String missionStatement;
    
    @Column(columnDefinition="TEXT")
    private String about;
    private String email;
    private String url;
    private String telephone;
    private String fax;
    
    private String facebook;
    private String twitter;
    private String youtube;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Staff.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<Staff> staffers;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ServiceTimes.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<ServiceTimes> serviceTimes;

    public ChurchInfo() {}

    public ChurchInfo(ChurchInfo info) {
        this.name = info.getName();
        this.missionStatement = info.getMissionStatement();
        this.about = info.getAbout();
        this.email = info.getEmail();
        this.address = info.getAddress();
        this.telephone = info.getTelephone();
        this.staffers = info.getStaffers();
        this.facebook = info.getFacebook();
        this.twitter = info.getTwitter();
        this.youtube = info.getYoutube();
        this.url = info.getUrl();
        this.serviceTimes = info.getServiceTimes();
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
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

    @Override
    public String toString() {
        return "ChurchInfo{" + "id=" + id + ", name=" + name + ", missionStatement=" + missionStatement + ", email=" + email + ", url=" + url + ", telephone=" + telephone + ", fax=" + fax + ", facebook=" + facebook + ", twitter=" + twitter + ", youtube=" + youtube + ", address=" + address + ", staffers=" + staffers + ", serviceTimes=" + serviceTimes + '}';
    }
}
