package com.milford.churchcms.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milford.churchcms.util.DateUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

@Entity
@Table(name = "CALENDAREVENT")
public class CalendarEvent {
    @Id
    private int id = 1;
    private String  title;
    @Column(name="start_date")
    private String  start;
    @Column(name="end_date")
    private String  end;
    private String  url = "/event/";
    
    @JsonIgnore
    private boolean isRepeated;    
    @JsonIgnore
    private String details;
    
    // Turn this into an String of the full name.
    @JsonIgnore
    private String contactName;
    
    @JsonIgnore
    @FutureOrPresent
    private Date  startDateCont;
    
    @JsonIgnore
    @FutureOrPresent
    private Date  endDateCont;
    
    @JsonIgnore
    private String startTime;
    @JsonIgnore
    private String endTime;
    
    
    public CalendarEvent() {}

    public CalendarEvent(Integer id, String title, String details, Date startDateCont, Date endDateCont, 
                        String startTime, String endTime, String contact) {
        this.id = id;
        this.title = title;
        this.url = url + Integer.valueOf(id);
        this.details = details;
        this.startDateCont = startDateCont;
        this.endDateCont = endDateCont;
        this.startTime = startTime;
        this.endTime = endTime;
        this.start =  DateUtil.getUIDate(startDateCont);
        this.end = DateUtil.getUIDate(endDateCont);
        this.contactName = contact;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public Date getStartDateCont() {
        return startDateCont;
    }

    public void setStartDateCont(Date startDateCont) {
        this.startDateCont = startDateCont;
    }

    public Date getEndDateCont() {
        return endDateCont;
    }

    public void setEndDateCont(Date endDateCont) {
        this.endDateCont = endDateCont;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "CalendarEvent{" + "id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", url=" + url + ", isRepeated=" + isRepeated + ", details=" + details + ", contactName=" + contactName + ", startDateCont=" + startDateCont + ", endDateCont=" + endDateCont + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

}
