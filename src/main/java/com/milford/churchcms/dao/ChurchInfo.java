/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.List;
import javax.persistence.CascadeType;
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
    private int id;
    private String name;
    private String missionStatement;
    private String email;
    private String url;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    private String telephone;
    private String leadPastor;
    private String associatePastor;
    private String youthMinister;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ServiceTimes.class)   
    @JoinColumn(name = "id",referencedColumnName="id")
    private List<ServiceTimes> serviceTimes;

    public ChurchInfo() {}

    public ChurchInfo(String name, String missionStatement, String email, Address address, String telephone, String leadPastor) {
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

    public String getLeadPastor() {
        return leadPastor;
    }

    public void setLeadPastor(String leadPastor) {
        this.leadPastor = leadPastor;
    }

    public String getAssociatePastor() {
        return associatePastor;
    }

    public void setAssociatePastor(String associatePastor) {
        this.associatePastor = associatePastor;
    }

    public String getYouthMinister() {
        return youthMinister;
    }

    public void setYouthMinister(String youthMinister) {
        this.youthMinister = youthMinister;
    }

    @Override
    public String toString() {
        return "ChurchInfo{" + "id=" + id + ", name=" + name + ", missionStatement=" + missionStatement + ", email=" + email + ", address=" + address + ", telephone=" + telephone + ", leadPastor=" + leadPastor + ", associatePastor=" + associatePastor + ", youthMinister=" + youthMinister + '}';
    }
}
