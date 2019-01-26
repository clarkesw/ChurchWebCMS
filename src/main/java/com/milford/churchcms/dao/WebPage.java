/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "WEBPAGE")
public class WebPage {
    
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String bgImage;
    private String link;
    
    @OneToMany(cascade=CascadeType.ALL, targetEntity = Article.class)
    @JoinColumn(name = "page_name")
    private List<Article> mainPageArticle;
    private String pageName;
    private Date   lastModified;
    private String mainPic;
    private String pageHeader;
    private String message;
    private boolean isVisible;

    public WebPage() {}

    public WebPage(String title, String bgImage, String link, String pageName, Date lastModified, String message, boolean isVisible) {
        this.title = title;
        this.bgImage = bgImage;
        this.link = link;
        this.pageName = pageName;
        this.lastModified = lastModified;
        this.message = message;
        this.isVisible = isVisible;
    }
    
    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getPageHeader() {
        return pageHeader;
    }

    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public String toString() {
        return "WebPage{" + "id=" + id + ", title=" + title + ", bgImage=" + bgImage + ", link=" + link + ", mainPageArticle=" + mainPageArticle + ", pageName=" + pageName + ", lastModified=" + lastModified + ", mainPic=" + mainPic + ", pageHeader=" + pageHeader + ", message=" + message + ", isVisible=" + isVisible + '}';
    }

}
