/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "ARTICLE")
public class Article {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(name="page_name")
    private String pageName;
    private String subTitle;
    private String url;
    private String content;
    private String imageURL;
    private String publishedDate;

    public Article() {}

    public Article(String title, String pageName, String subTitle, String url, String content, String imageURL) {
        this.title = title;
        this.pageName = pageName;
        this.subTitle = subTitle;
        this.url = url;
        this.content = content;
        this.imageURL = imageURL;
        this.publishedDate =  new SimpleDateFormat("M-d-yyyy  h:mm:ss a").format(new Date());

    }

    public Article(Article art) {
        new Article (art.getTitle(), art.getPageName(), art.getSubTitle(),art.getUrl(), art.getContent(), art.getImageURL());
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", pageName=" + pageName + ", subTitle=" + subTitle + ", url=" + url + ", content=" + content + ", imageURL=" + imageURL + ", publishedDate=" + publishedDate + '}';
    }  
}
