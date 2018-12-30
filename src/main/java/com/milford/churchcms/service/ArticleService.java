/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.Article;
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
    
    public Article getArticleInfo(){
        return art;
    }

}
