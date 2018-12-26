/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

/**
 *
 * @author clarke
 */
public class Article {
    private String title;
    private String subTitle;
    private String url;
    private String content;
    private String imageURL;

    public Article(String title,String subTitle, String url,String content, String imageURL) {
        this.subTitle = subTitle;
        this.url = url;
        this.title = title;
        this.content = content;
        this.imageURL = imageURL;
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
    
    
}
