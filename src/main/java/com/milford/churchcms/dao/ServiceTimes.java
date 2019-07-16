/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "ServiceTimes")
public class ServiceTimes {
    
    @Id
    @GeneratedValue
    private int id;
    private String day;
    private String time;

    public ServiceTimes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String getDayAndTime() {
        return day + " @ " + time;
    }

    @Override
    public String toString() {
        return "ServiceTimes{" + "id=" + id + ", day=" + day + ", time=" + time + '}';
    }
}
