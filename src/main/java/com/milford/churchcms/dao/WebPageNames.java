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
@Table(name = "WEBPAGENAMES")
public class WebPageNames {
    
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private boolean deletable = true;

    public WebPageNames() {}

    public WebPageNames(String name) {
        if((name.equals("Home") || name.equals("Calendar")))
            deletable = false;     
        this.name = name;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WebPageNames{" + "id=" + id + ", name=" + name + ", deletable=" + deletable + '}';
    }
}
