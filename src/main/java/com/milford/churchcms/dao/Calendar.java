package com.milford.churchcms.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Calendar {
    
    @Id
    @GeneratedValue 
    private Integer id;
    private boolean isRepeated;
    private String  title;
    private String  url;
    private String details;
    
    private String  start;
    private String  end;

    public Calendar() {}

    public Calendar(int id, boolean isRepeated, String title, String start, String end, String url) {
        this.id = id;
        this.isRepeated = isRepeated;
        this.title = title;
        this.start = start;
        this.end = end;
        this.url = url;
    }

    public Calendar(int id, String title, String start, String end, String url) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.url = url;
    }

    public Calendar(int id, String title, String start, String end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Calendar(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

        public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
      
      
}
