package com.milford.churchcms.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milford.churchcms.util.DateUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CALENDAREVENT")
public class CalendarEvent {
    @Id
    private Integer id = 1;
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
    @JsonIgnore
    private Staff contact;
    @JsonIgnore
    private Date  startDateCont;
    @JsonIgnore
    private Date  endDateCont;
    
    @JsonIgnore
    private String startTime;
    @JsonIgnore
    private String endTime;
    
    
    public CalendarEvent() {}

    public CalendarEvent(String title, Date startDateCont, Date endDateCont) {
        this.title = title;
        this.startDateCont = startDateCont;
        this.endDateCont = endDateCont;   
        this.startTime = DateUtil.setStartTime(endDateCont); 
        this.endTime = DateUtil.setEndTime(endDateCont);
       this.start =  DateUtil.setStartUIDate(startDateCont);
        this.end = DateUtil.setEndUIDate(endDateCont);
    }

    public CalendarEvent(Integer id, String title, Date startDateCont, Date endDateCont) {
        this.id = id;
        this.title = title;
        this.startDateCont = startDateCont;
        this.endDateCont = endDateCont;
        this.startTime = DateUtil.setStartTime(endDateCont); 
        this.endTime = DateUtil.setEndTime(endDateCont);
       this.start =  DateUtil.setStartUIDate(startDateCont);
        this.end = DateUtil.setEndUIDate(endDateCont);
    }

    public CalendarEvent(Integer id, String title, String url, String details, Date startDateCont, Date endDateCont, 
                        String startTime, String endTime, Staff contact) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.details = details;
        this.startDateCont = startDateCont;
        this.endDateCont = endDateCont;
        this.startTime = startTime;
        this.endTime = endTime;
        this.start =  DateUtil.setStartUIDate(startDateCont);
        this.end = DateUtil.setEndUIDate(endDateCont);
    }

    public Staff getContact() {
        return contact;
    }

    public void setContact(Staff contact) {
        this.contact = contact;
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
        return "CalendarEvent{" + "id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", isRepeated=" + isRepeated + ", url=" + url + ", details=" + details + ", startDateCont=" + startDateCont + ", endDateCont=" + endDateCont + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
      
      
}
