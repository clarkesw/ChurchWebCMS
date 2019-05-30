/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "SERMON")
public class Sermon {
    @Id
    @Column(name = "sid")
    @GeneratedValue
    private int id;
    private String title;
    private String subTitle;
    private String description;
    private Date sermonDate;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Passage.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<Passage> passages;// = new ArrayList<>(); 

    public Sermon() {}

    public Sermon(String title, String description, Date sermonDate) {
        this.title = title;
        this.description = description;
        this.sermonDate = sermonDate;
    }

    public Sermon(String title, String subTitle, String description, Date date, List<Passage> passages) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.sermonDate = date;
        this.passages = passages;
    }

    public int getId() {
        return id;
    }

    public void setId(int sermon_id) {
        this.id = sermon_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Date getSermonDate() {
        return sermonDate;
    }

    public void setSermonDate(Date sermonDate) {
        this.sermonDate = sermonDate;
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    @Override
    public String toString() {
        return "Sermon{" + "title=" + title + ", subTitle=" + subTitle + ", description=" + description + ", sermonDate=" + sermonDate + ", passages=" + passages + '}';
    }
}
