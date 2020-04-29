/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.WebPage;
import com.milford.churchcms.repository.WebPageRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {

    public Logger logger = LoggerFactory.getLogger(WebPageService.class);

    @Autowired
    WebPageRepository repository;
    
    public List<WebPage> showPages(){
        return repository.findAll();
    }
    
    public void  updatePagePost(WebPage page){
        repository.delete(page); 
        repository.save(new WebPage(page.getTitle(),page.getBgImage(),page.getLink(),page.getPageName(),page.getMessage(),page.isIsVisible()));        
    }
    
    public Optional<WebPage> findById(int id){
        return repository.findById(id);
    }
    
    public void deleteById(int id){
        repository.deleteById(id);
    }
    
    public void addWebPagePost(WebPage page){
        repository.save(new WebPage(page.getTitle(),page.getBgImage(), page.getLink(), page.getPageName(), 
                page.getMessage(), page.isIsVisible()));   
    }
}
