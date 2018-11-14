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
import javax.validation.constraints.Future;

@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class CalendarEvent {
 
    @Id
    @GeneratedValue 
    private Integer id;
    
    @Future
    private Date startDate;
    
    @Future
    private Date endDate;
    private String title;
    private String details;
    private String url;
    private boolean isRepeated;

    public CalendarEvent() {
    }

    public CalendarEvent(Integer id, Date startDate, Date endDate, String title) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
 
    public CalendarEvent(Integer id, Date startDate, Date endDate, String title, 
            String details, String url, boolean isRepeated) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.details = details;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", title=" + 
                title + ", details=" + details + ", url=" + url + ", isRepeated=" + isRepeated + '}';
    }
    
    
}
