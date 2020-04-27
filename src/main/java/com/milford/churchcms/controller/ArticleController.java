/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Article;
import com.milford.churchcms.repository.ArticleRepository;
import com.milford.churchcms.service.ArticleService;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController extends BaseController{
    
    public Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Autowired
    ArticleService service;
    
    @Autowired 
    private HttpSession session;
    
    @GetMapping("/list-articles")
    public String showArticlePost(ModelMap model){
        logger.debug("GET /list-articles");
        String username = getLoggedInName(model);
        model.put("articles", service.showArticlePost());
        return "cms/list-articles";
    }
        
    @GetMapping("/listArticlesForPage")
    public String showArticleGet(ModelMap model,@RequestParam String page){
        logger.debug("GET /listArticlesForPage page : {}",page); 
        session.setAttribute("ArticalWebPage", page);
        model.put("articles", service.showArticleGet(page));
        return "cms/list-articles";
    }
 
    @GetMapping("/add-articles")
    public String addArticleGet(ModelMap model, @ModelAttribute("article") Article article){   
        logger.debug("GET /add-articles Article : {}",article); 
        String pageName = (String)session.getAttribute("ArticalWebPage");
        if(pageName != null)
            article.setPageName(pageName);
        return "cms/add-article";
    }
    
    @PostMapping("/add-articles")
    public String addArticlePost(ModelMap model,@Valid @ModelAttribute("article") Article article, BindingResult result){
        logger.debug("POST /add-articles Article : {}",article); 
        if(result.hasErrors())
            return "cms/add-article";
        
        service.addArticlePost(article);
        return "redirect:list-articles";
    }
    
    @GetMapping("/delete-article")
    public String deleteArticle(@RequestParam int id){
        logger.debug("deleteArticle ID : {}",id); 
        service.deleteArticle(id);
        return "redirect:list-articles";
    }
    
    @PostMapping("/update-article")
    public String updateArticlePost(ModelMap model,@Valid @ModelAttribute("article") Article article, BindingResult result){
        logger.debug("POST /update-article article : {}",article);
        if(result.hasErrors())
            return "cms/add-article";
        
        service.updateArticlePost(article);
        return "redirect:list-articles";
    }
    
    @GetMapping("/update-article")
    public String updateArticleGet(ModelMap model, @RequestParam int id){
        logger.debug("GET /update-article ID : {}",id);
        Optional<Article> article = service.updateArticleGet(id);
        
        model.put("article", article.get());
        return "cms/add-article";
    }    
    
    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
        return (String)model.get("user");
    }
}
