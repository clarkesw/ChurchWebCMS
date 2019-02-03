/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.util;

import com.milford.churchcms.dao.CalendarEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author clarke
 */
public class DateUtil {
     public static Logger logger = LoggerFactory.getLogger(DateUtil.class);
     
    public static CalendarEvent updateTimeDate(CalendarEvent event){
        event.setStart(setStartTime(event.getStartDateCont()));
        event.setEnd(setEndTime(event.getEndDateCont()));
        event.setStart(setStartUIDate(event.getStartDateCont()));
        event.setEnd(setEndUIDate(event.getEndDateCont()));
        return event;
    }
    public static String setStartTime(Date startDateCont){
       logger.debug(startDateCont.toString());
       return startDateCont.getHours() + ":" + startDateCont.getMinutes();
    }

    public static String setEndTime( Date endDateCont){
        logger.debug(endDateCont.toString());
       return endDateCont.getHours() + ":" + endDateCont.getMinutes();
    }
    
   public static String setStartUIDate( Date endDateCont){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return sdf.format(endDateCont);  
    }

   public static String setEndUIDate(Date endDateCont){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return sdf.format(endDateCont);
    }
    public static LocalDateTime stringToDate(String date, String time){
        //   LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)
      
        LocalDateTime ldt = LocalDateTime.of(2018, 11, 20, 10, 30);
        return ldt;
    }
    
    public static Date localToDate(LocalDateTime ldt){
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static String returnStringDate(String date, String time){
        String concatDate = date + "T" + time;
        return concatDate;
    }
    
}
