/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import com.milford.churchcms.AppConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "BANNER")
public class Banner {
    
    @Id
    private int id = 1;
    private String message;
    
    @Column(name="last_modified")
    private String lastModified;

    public Banner() {}

    public Banner(String message) {
        this.message = message;
        this.lastModified =  new SimpleDateFormat(AppConstants.dateFormat).format(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Banner{" + "id=" + id + ", message=" + message + ", lastModified=" + lastModified + '}';
    }

}
