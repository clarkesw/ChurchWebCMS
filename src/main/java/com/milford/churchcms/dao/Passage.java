/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "PASSAGE")
public class Passage {
    @Id
    @GeneratedValue
    private int id;
    private String book;
    private int chapter;
    private int verse;
    private String link;

    public Passage(){}
    
     public Passage(Passage pass){
        this.book = pass.book;
        this.chapter = pass.chapter;
        this.verse = pass.verse;
        setLink(pass.book, pass.chapter, pass.verse);
     }
    public Passage(String book, int chapter, int verse) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        setLink(book, chapter, verse);
    }

    public String getLink() {
        return link;
    }
//http://labs.bible.org/api/?passage=John%203:16-17
    private void setLink(String book, int chapter, int verse) {
        this.link = "http://labs.bible.org/api/?passage=" + book + " " + chapter + ":" + verse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    @Override
    public String toString() {
        return "Passage{" + "book=" + book + ", chapter=" + chapter + ", verse=" + verse + '}';
    }
}
