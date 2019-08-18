/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author clarke
 */
public class AppConstants {
    
    public static final String dateFormat = "M-d-yyyy  h:mm:ss a";
    public static List<String> roles = new ArrayList<>(
        Arrays.asList("ADMIN","USER"));
    
    public final static List<String> books = new ArrayList<>(
         Arrays.asList("Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges","Ruth","1 Samuel",
                 "2 Samuel","1 Kings","2 Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job",
                 "Psalm","Proverbs","Ecclesiastes","Song of Songs","Isaiah","Jeremiah","Lamentations","Ezekiel",
                 "Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai",
                 "Zechariah","Malachi","Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians",
                 "Galatians","Ephesians","Philippians","Colossians","1 Thessalonians","2 Thessalonians","1 Timothy",
                 "2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter","1 John","2 John","3 John",
                 "Jude","Revelation")); 
    
    public class WebPage{
        public static final int num_of_items = 2;
        public static final String HOME = "Home";
        public static final String CALENDAR = "Calendar";
    }
    
    public class Session{
       // public static final String 
    }
    
}
