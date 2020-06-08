/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.util;

import com.milford.churchcms.dao.CalendarEvent;
import java.text.SimpleDateFormat;
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
        event.setStart(geTime(event.getStartDateCont()));
        event.setEnd(geTime(event.getEndDateCont()));
        event.setStart(getUIDate(event.getStartDateCont()));
        event.setEnd(getUIDate(event.getEndDateCont()));
        return event;
    }
    public static String geTime(Date dateCont){
       logger.debug("geTime()  date : {}",dateCont.toString());
       return dateCont.getHours() + ":" + dateCont.getMinutes();
    }
    
   public static String getUIDate( Date dateCont){
       logger.debug("getUIDate()  date : {}",dateCont.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return sdf.format(dateCont);  
    }
    
    public static String dateFormat(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(date);
    }
}
