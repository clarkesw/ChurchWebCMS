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

@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Calender {
 
    @Id
    @GeneratedValue 
    private Integer id;
    
//    @Future
    private Date startDate;
    
//    @Future
    private Date endDate;
    private String title;
    private String details;
    private String picURL;

    public Calender() {
    }

    public Calender(Integer id, Date startDate, Date endDate, String title, String details, String picURL) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.details = details;
        this.picURL = picURL;
    }

    
    public Calender(Date startDate, Date endDate, String title, String details, String picURL) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.details = details;
        this.picURL = picURL;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
    
    
}
