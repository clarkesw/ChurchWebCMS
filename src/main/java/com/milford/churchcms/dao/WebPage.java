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
public class WebPage {
    
    private Integer id;
    private String pageName;
    private String mainPic;
    private String pageHeader;
    private String message;
    private String announcements;
    private boolean isVisible;

    public WebPage() {}

    public WebPage(String pageName, String mainPic, String pageHeader, String message, String announcements, boolean isVisible) {
        this.pageName = pageName;
        this.mainPic = mainPic;
        this.pageHeader = pageHeader;
        this.message = message;
        this.announcements = announcements;
        this.isVisible = isVisible;
    }

    public WebPage(int id, String pageName, String mainPic, String pageHeader, String message, String announcements, boolean isVisible) {
        this.id = id;
        this.pageName = pageName;
        this.mainPic = mainPic;
        this.pageHeader = pageHeader;
        this.message = message;
        this.announcements = announcements;
        this.isVisible = isVisible;
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
