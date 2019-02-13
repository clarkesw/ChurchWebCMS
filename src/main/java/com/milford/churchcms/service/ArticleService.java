/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Article;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class ArticleService {
    public Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private Article art = new Article("Helping Others",AppConstants.WebPage.HOME,"Sub Title","www.google.com", "To help others you must...", "../../images/your_image.jpg");
    private List<Article> artList = new ArrayList<>(); 
    {
        artList.add(art);
    }
    
    public List<Article> retrieveArticles() {
        return artList;
    }
    
    public Article getArticleInfo(){
        return art;
    }

    public Article retrieveOneArticle(int id) {
        logger.debug("ArticleService.retrieveOnePage id: {}" + id);
        for (Article art : artList) {
            if (art.getId() == id) {
                return art;
            }
        }
        return null;
    }

    public void addArticle(Article art) {
        logger.debug("addEvent title: {}" + art);
        artList.add(new Article(art));
    }
    
    public void deleteArticle(int id) {
        logger.debug("ArticleService.deletePage id: {}" + id);
        Iterator<Article> iterator = artList.iterator();
        while (iterator.hasNext()) {
            Article it = iterator.next();
            if (it.getId() == id) {
                iterator.remove();
            }
        }
    }
        
    public void updateArticle(Article art){
        logger.debug("ArticleService.updatePage id: {}" + art.getId());
        deleteArticle(art.getId());    
    	artList.add(art);
    }
}
