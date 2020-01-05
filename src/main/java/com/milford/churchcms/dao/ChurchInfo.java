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
    
    @OneToOne(cascade=CascadeType.DETACH, targetEntity = Staff.class, orphanRemoval = true)
    @JoinColumn(name = "LEAD_ID")
    private Staff leadPastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "ASSOC_ID")
    private Staff associatePastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "YOUTH_ID")
    private Staff youthPastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "MUSIC_ID")
    private Staff musicPastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "ADULT_ID")
    private Staff adultPastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "OUT_R_ID")
    private Staff outReachPastor = null;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "COLLAGE_ID")
    private Staff collagePastor = null;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ServiceTimes.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<ServiceTimes> serviceTimes;

    public ChurchInfo() {}

    public ChurchInfo(String name, String missionStatement, String email, Address address, String telephone, Staff leadPastor) {
        this.id = id;
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.leadPastor = leadPastor;
    }
        
    public ChurchInfo(int id,String name, String missionStatement, String email, Address address, String telephone, Staff leadPastor) {
        this.id = id;
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.leadPastor = leadPastor;
    }

    public ChurchInfo(ChurchInfo info) {
        this.name = info.getName();
        this.missionStatement = info.getMissionStatement();
        this.email = info.getEmail();
        this.address = info.getAddress();
        this.telephone = info.getTelephone();
        this.leadPastor = info.getLeadPastor();
        this.url = info.getUrl();
        this.serviceTimes = info.getServiceTimes();
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

    public Staff getLeadPastor() {
        return leadPastor;
    }

    public void setLeadPastor(Staff leadPastor) {
        this.leadPastor = leadPastor;
    }

    public Staff getAssociatePastor() {
        return associatePastor;
    }

    public void setAssociatePastor(Staff associatePastor) {
        this.associatePastor = associatePastor;
    }

    public Staff getYouthPastor() {
        return youthPastor;
    }

    public void setYouthPastor(Staff youthPastor) {
        this.youthPastor = youthPastor;
    }

    public Staff getMusicPastor() {
        return musicPastor;
    }

    public void setMusicPastor(Staff musicPastor) {
        this.musicPastor = musicPastor;
    }

    public Staff getAdultPastor() {
        return adultPastor;
    }

    public void setAdultPastor(Staff adultPastor) {
        this.adultPastor = adultPastor;
    }

    public Staff getOutReachPastor() {
        return outReachPastor;
    }

    public void setOutReachPastor(Staff outReachPastor) {
        this.outReachPastor = outReachPastor;
    }

    public Staff getCollagePastor() {
        return collagePastor;
    }

    public void setCollagePastor(Staff collagePastor) {
        this.collagePastor = collagePastor;
    }

    @Override
    public String toString() {
        return "ChurchInfo{" + "id=" + id + ", name=" + name + ", missionStatement=" + missionStatement + ", email=" + email + ", url=" + url + ", telephone=" + telephone + ", address=" + address + ", serviceTimes=" + serviceTimes + 
                ", leadPastor=" + leadPastor; //+ 
            //    ", associatePastor=" + ((associatePastor == null) ? associatePastor : "null") + 
            //    ", youthPastor=" + ((youthPastor == null) ? youthPastor : "null") + 
           //     ", musicPastor=" + ((musicPastor == null) ? musicPastor : "null") + 
           //     ", adultPastor=" + ((adultPastor == null) ? adultPastor : "null") + 
          //      ", outReachPastor=" + ((outReachPastor == null) ? outReachPastor : "null") + 
          //      ", collagePastor=" +  ((collagePastor == null) ? collagePastor : "null")+ '}';
    }
}
