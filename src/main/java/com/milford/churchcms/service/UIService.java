/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Article;
import com.milford.churchcms.dao.Banner;
import com.milford.churchcms.dao.CalendarEvent;
import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.repository.ArticleRepository;
import com.milford.churchcms.repository.BannerRepository;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.SermonRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.repository.WebPageRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clarke
 */
public class UIService {
    public Logger logger = LoggerFactory.getLogger(UIService.class);
    
    @Autowired
    CalendarEventRepository eventRepository;
        
    @Autowired
    ChurchRepository churchRepository;
    
    @Autowired
    SermonRepository sermonRepository;
        
    @Autowired
    ArticleRepository articleRepository;
    
    @Autowired
    WebPageRepository pageRepository;
        
    @Autowired
    WebPageService pageService;
    
    @Autowired
    StaffRepository staffRepository;
    
    @Autowired
    BannerRepository bannerRepository;
    
    public List<CalendarEvent> getCalendarEvent(){
        return eventRepository.findAll();
    }
    
    public Optional<CalendarEvent> findEventById(int id){
        return eventRepository.findById(id);
    }
        
    public Optional<Banner> getBanner(){
        return bannerRepository.findTopByOrderByIdDesc();
    }
    
    public Optional<Sermon> findSermonDateDesc(){
        return sermonRepository.findTopByOrderBySermonDateDesc();
    }
    
    public Optional<Sermon> findSermonById(int id){
        return sermonRepository.findById(id);
    }
    
    
    public Optional<ChurchInfo> findChurchInfoByIdDesc(){
        return churchRepository.findTopByOrderByIdDesc();
    }
    
    public Article findArticleByLastModified(){
        return articleRepository.findTopByOrderByLastModified();
    }
    
    public Optional<Article> findArticleById(int id){
        return articleRepository.findById(id);
    }
    public WebPage findByPageName(String name){
        return pageRepository.findByPageName(name);
    }
    
    public ChurchInfo getChurchInfo(){
        List<ChurchInfo> churchInfo = churchRepository.findAll();
        return churchInfo.get(0);
    }
    
    public Optional<Staff> findStaffById(int id){
        return staffRepository.findById(id);
    }
    
    public Optional<Staff> findStaffByFullName(CalendarEvent oneEvent){
        return staffRepository.findByFullName(oneEvent.getContactName());
    }
}
