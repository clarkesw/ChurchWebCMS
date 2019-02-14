/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Article;
import com.milford.churchcms.repository.ArticleRepository;
import com.milford.churchcms.service.ArticleService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController{
    
    public Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Autowired
    ArticleService service;
    
    @Autowired
    ArticleRepository repository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-articles")
    public String showArticle(ModelMap model){
        String username = getLoggedInName(model);
        model.put("articles", repository.findAll()); //service.retrieveArticles());
        return "cms/list-articles";
    }
        
    @GetMapping("/listArticlesForPage")
    public String showArticle(ModelMap model,@RequestParam String page){
        String username = getLoggedInName(model);
        model.put("articles", repository.findAllByPageName(page));
        return "cms/list-articles";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
 
    @GetMapping("/add-articles")
    public String showAddArticle(ModelMap model, @ModelAttribute("article") Article article){     
        return "cms/add-article";
    }
    
    @PostMapping("/add-articles")
    public String addArticle(ModelMap model,@Valid @ModelAttribute("article") Article article, BindingResult result){
        logger.debug("addArticle Article : {}",article); 
        if(result.hasErrors())
            return "cms/add-article";
        
        repository.save(new Article(article.getTitle(),article.getPageName(),article.getSubTitle(),article.getUrl(),
                                    article.getContent(),article.getImageURL())); 
        return "redirect:list-articles";
    }
    
    @GetMapping("/delete-article")
    public String deleteArticle(@RequestParam int id){
        logger.debug("deleteArticle ID : {}",id); 
        repository.deleteById(id);  //service.deleteArticle(id);
        return "redirect:list-articles";
    }
    
    @PostMapping("/update-article")
    public String updateArticlePost(ModelMap model,@Valid @ModelAttribute("article") Article article, BindingResult result){
        if(result.hasErrors())
            return "cms/add-article";
        repository.delete(article);
        repository.save(new Article(article.getTitle(),article.getPageName(),article.getSubTitle(),article.getUrl(),
                                    article.getContent(),article.getImageURL()));  //service.updateArticle(article);
        return "redirect:list-articles";
    }
    
    @GetMapping("/update-article")
    public String updateShowArticle(ModelMap model, @RequestParam int id){
        Optional<Article> article = repository.findById(id);//service.retrieveOneArticle(id);
        
        model.put("article", article.get());
        return "cms/add-article";
    }    
}
