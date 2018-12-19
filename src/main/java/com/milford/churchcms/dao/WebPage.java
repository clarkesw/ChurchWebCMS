/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import java.util.Date;

/**
 *
 * @author clarke
 */
public class WebPage {
    
    private Integer id;
    private String title;
    private String bgImage;
    private String link;
    private String missionStatement;
    private Article mainPageArticle;
    private String pageName;
    private Date   lastModified;
    private String mainPic;
    private String pageHeader;
    private String message;
    private String announcements;
    private boolean isVisible;

    public WebPage() {}

    public WebPage(String title, String missionStatement, String bgImage, String link, String pageName, Date lastModified, String message, boolean isVisible) {
        this.title = title;
        this.bgImage = bgImage;
        this.link = link;
        this.missionStatement = missionStatement;
        this.pageName = pageName;
        this.lastModified = lastModified;
        this.message = message;
        this.isVisible = isVisible;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
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

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public String toString() {
        return "HomePage{" + "id=" + id + ", mainPic=" + mainPic + ", pageHeader=" + pageHeader + ", message=" + message + ", announcements=" + announcements + '}';
    }
}
