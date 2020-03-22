/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author clarke
 */
@Entity
public class Prayer {
    @Id
    @GeneratedValue
    private int id;
    private String prayerRquest;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String perferedContactMethod;
    private String perferedContactTime;
    private Date date;
    
    public Prayer() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrayerRquest() {
        return prayerRquest;
    }

    public void setPrayerRquest(String prayerRquest) {
        this.prayerRquest = prayerRquest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerferedContactMethod() {
        return perferedContactMethod;
    }

    public void setPerferedContactMethod(String perferedContactMethod) {
        this.perferedContactMethod = perferedContactMethod;
    }

    public String getPerferedContactTime() {
        return perferedContactTime;
    }

    public void setPerferedContactTime(String perferedContactTime) {
        this.perferedContactTime = perferedContactTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date datee) {
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Prayer{" + "id=" + id + ", prayerRquest=" + prayerRquest + ", firstName=" + firstName + ", lastName=" + 
                lastName + ", email=" + email + ", phone=" + phone + ", perferedContactMethod=" + 
                perferedContactMethod + ", perferedContactTime=" + perferedContactTime + '}';
    }
  
}
