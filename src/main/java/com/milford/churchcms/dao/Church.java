/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "CHURCH")
public class Church {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String missionStatement;
    private String email;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    private String telephone;
    private String leadPastor;
    private String associatePastor;
    private String youthMinister;

    public Church() {}

    public Church(String name, String missionStatement, String email, Address address, String telephone, String leadPastor) {
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.leadPastor = leadPastor;
    }

    public Church(Church info) {
        this.name = info.getName();
        this.missionStatement = info.getMissionStatement();
        this.email = info.getEmail();
        this.address = info.getAddress();
        this.telephone = info.getTelephone();
        this.leadPastor = info.getLeadPastor();
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
    
    
}
