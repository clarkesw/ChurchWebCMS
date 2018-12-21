/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

/**
 *
 * @author clarke
 */
public class Church {
    private String name;
    private String missionStatement;
    private String email;
    private Address address;
    private String telephone;
    private String leadPastor;
    private String associatePastor;
    private String youthMinister;

    public Church(String name, String missionStatement, String email, Address address, String telephone, String leadPastor) {
        this.name = name;
        this.missionStatement = missionStatement;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.leadPastor = leadPastor;
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
