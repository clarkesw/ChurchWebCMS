/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import com.milford.churchcms.AppConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author clarke
 */

public class Alert {

    private String group;
    private String message;
    private String date;

    public Alert() {}

    public Alert(String group, String message) {
        this.message = message;
        this.group = group;
        this.date = new SimpleDateFormat(AppConstants.DATE_FORMAT).format(new Date());
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Alert{" + "group=" + group + ", message=" + message + ", date=" + date + '}';
    }
}