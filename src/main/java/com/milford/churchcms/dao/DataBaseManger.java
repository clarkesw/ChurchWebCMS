/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.sql.Date;

public class DataBaseManger {
    private int id;
    private Date lastBackup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastBackup() {
        return lastBackup;
    }

    public void setLastBackup(Date lastBackup) {
        this.lastBackup = lastBackup;
    }

    @Override
    public String toString() {
        return "DataBaseManger{" + "lastBackup=" + lastBackup + '}';
    }
}
