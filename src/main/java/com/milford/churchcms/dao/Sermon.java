/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private Integer id = 1;
    private String title;
    private String url;
    private String imageURL;
    private String subTitle;
    private String description;
    // TODO check if sermonDate needs to be a string
    private Date sermonDate;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Passage.class)   
    @JoinColumn(name = "sid",referencedColumnName="sid")
    private List<Passage> passages;// = new ArrayList<>(); 

    public Sermon() {}

    public Sermon(int id, String title, String subTitle, String description, Date sermonDate) {
        this.title = title;
        this.description = description;
        this.sermonDate = sermonDate;
        this.url = createUrl(id);
    }

    public Sermon(int id, String title, String subTitle, String description, Date date, List<Passage> passages) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.sermonDate = date;
        this.passages = passages;
        this.url = createUrl(id);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    private String createUrl(int id){
        return "/sermon/" + id;
    }

    @Override
    public String toString() {
        return "Sermon{" + "id=" + id + ", title=" + title + ", url=" + url + ", imageURL=" + imageURL + ", subTitle=" + subTitle + ", description=" + description + ", sermonDate=" + sermonDate + ", passages=" + passages + '}';
    }
}
