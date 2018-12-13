/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {

    public Logger logger = LoggerFactory.getLogger(WebPageService.class);
    private final List<WebPage> webPages = new ArrayList<>();
    private final WebPage homePage = new WebPage(AppConstants.WebPage.HOME, "../images/backgrounds.png","www.google.com" ,AppConstants.WebPage.HOME, new Date(), "The church is pregnant.",true);
    
    {
        webPages.add(homePage);
    }

    public List<WebPage> retrievePages() {
        return webPages;
    }
    
    public WebPage retrieveOnePage(int id) {
        logger.debug("WebPageService.retrieveOneEvent id: {}" + id);
        for (WebPage page : webPages) {
            if (page.getId() == id) {
                return page;
            }
        }
        return null;
    }
    
   public WebPage retrieveOnePage(String name) {
        logger.debug("WebPageService.retrieveOneEvent name: " + name);
        for (WebPage page : webPages) {
   //         System.out.println("WebPageService.retrieveOneEvent page: " + page.getBgImage()+"   "+page.getMessage());
            if (page.getPageName().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return null;
    }
    
    public void deletePage(int id) {
        logger.debug("EventService.deletePage id: {}" + id);
        Iterator<WebPage> iterator = webPages.iterator();
        while (iterator.hasNext()) {
            WebPage page = iterator.next();
            if (page.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void updatePage(WebPage page){
        logger.debug("EventService.updatePage id: {}" + page.getId());
        deletePage(page.getId());    
    	webPages.add(page);
    }
}
