/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author clarke
 */
public class DateUtil {
    
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
