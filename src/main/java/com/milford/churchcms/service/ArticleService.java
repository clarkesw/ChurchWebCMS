/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Article;
import com.milford.churchcms.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class ArticleService {
    public Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    ArticleRepository repository;
        
    public List<Article> showArticlePost(){
        return repository.findAll();
    }
    
    public List<Article> showArticleGet(String page){
        return repository.findAllByPageName(page);
    }
    
    public void addArticlePost(Article article){
        saveArticle(article);
    }
    
    public void updateArticlePost(Article article){
        repository.delete(article);
        saveArticle(article);
    }
    public void deleteArticle(int id){
        repository.deleteById(id); 
    }
    
    public Optional<Article> updateArticleGet(int id){
        return repository.findById(id);
    }
    
    private void saveArticle(Article article){
        Article lastArt = repository.findTopByOrderByLastModified();
        int articleId = (lastArt != null) ? lastArt.getId() + 1 : 1;
        repository.save(new Article(articleId, article.getTitle(),article.getPageName(),article.getSubTitle(),
                                    article.getContent(),article.getImageURL()));     
    }
}
