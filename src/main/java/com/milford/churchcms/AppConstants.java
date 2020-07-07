/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author clarke
 */
public class AppConstants {
    
    public static final String dateFormat = "M-d-yyyy  h:mm:ss a";
    public static final String salt = "feiH34rvfdk!!fe((";
    
    public static List<String> roles = new ArrayList<>(
        Arrays.asList("ADMIN","USER"));
    
    public static List<String> prefferedContactList = new ArrayList<>(
         Arrays.asList("Home Email", "Work Email", "Home Phone", "Work Phone", "Mobile Phone"));
    
    public static List<String> days = new ArrayList<>(
        Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));

    public static Map<String, String> textMessageAddress = new HashMap<String, String>();
    
    public final static List<String> positions = new ArrayList<>(
         Arrays.asList("Lead Pastor", "Associate Pastor", "Youth Pastor", "College Pastor", "Music Pastor",
                 "IT","Sound", "Video","Guitarist", "Drumer", "Choir", "Pianist",
                 "Administrative Assistant","Food Bank","Deacon","Elder","Security","Custodian","Usher","Preschool","Greeter"));
    
    public final static List<String> corePositions = new ArrayList<>(
         Arrays.asList("Lead Pastor", "Associate Pastor", "Youth Pastor", "College Pastor", "Music Pastor", "Deacon","Elder"));
                
    public final static List<String> books = new ArrayList<>(
         Arrays.asList("Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges","Ruth","1 Samuel",
                 "2 Samuel","1 Kings","2 Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job",
                 "Psalm","Proverbs","Ecclesiastes","Song of Songs","Isaiah","Jeremiah","Lamentations","Ezekiel",
                 "Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai",
                 "Zechariah","Malachi","Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians",
                 "Galatians","Ephesians","Philippians","Colossians","1 Thessalonians","2 Thessalonians","1 Timothy",
                 "2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter","1 John","2 John","3 John",
                 "Jude","Revelation")); 
    
    static{
        textMessageAddress.put("AT&T", "@mms.att.net");
        textMessageAddress.put("Verizon", "@vtext.com");
        textMessageAddress.put("Sprint", "messaging.sprintpcs.com");
        textMessageAddress.put("T-Mobile", "@tmomail.net");
        textMessageAddress.put("Virgin", "@vmobl.com");
    }
    
    public static class Contact{
        public final static List<String> contactMethods = new ArrayList<>(
           Arrays.asList("None", "Email", "Phone"));
        public final static List<String> contactTimes = new ArrayList<>(
           Arrays.asList("Morning", "Afternoon","Evening"));          
    }
    
    public static class Name{
            public final static List<String> suffix = new ArrayList<>(
                Arrays.asList("Sr.", "Jr.", "II", "III", "IIII"));
            public final static List<String> preffix = new ArrayList<>(
                Arrays.asList("Dr.", "Rev."));            
    }
    
    public static class WebPage{
        public static final int num_of_items = 2;
        public static final String HOME = "Home";
        public static final String CALENDAR = "Calendar";
    }
    
    public static class Session{
       // public static final String 
    }
    
}
