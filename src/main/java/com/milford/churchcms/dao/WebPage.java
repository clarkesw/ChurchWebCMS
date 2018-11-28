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
    private String mainPic;
    private String pageHeader;
    private String message;
    private String announcements;

    public WebPage() {}

    public WebPage(String mainPic, String pageHeader, String message, String announcements) {
        this.mainPic = mainPic;
        this.pageHeader = pageHeader;
        this.message = message;
        this.announcements = announcements;
    }

    public WebPage(int id, String mainPic, String pageHeader, String message, String announcements) {
        this.id = id;
        this.mainPic = mainPic;
        this.pageHeader = pageHeader;
        this.message = message;
        this.announcements = announcements;
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

    @Override
    public String toString() {
        return "HomePage{" + "id=" + id + ", mainPic=" + mainPic + ", pageHeader=" + pageHeader + ", message=" + message + ", announcements=" + announcements + '}';
    }
}
