package com.milford.churchcms.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milford.churchcms.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarEvent {
   
    private Integer id;
    private String  title;
    private String  start;
    private String  end;
    
    @JsonIgnore
    private boolean isRepeated;    
    @JsonIgnore
    private String  url;
    @JsonIgnore
    private String details;
    
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
      
      
}
