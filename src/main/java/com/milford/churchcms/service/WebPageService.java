/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.dao.WebPage;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {

    public Logger logger = LoggerFactory.getLogger(WebPageService.class);
    private final List<WebPage> webPages = new ArrayList<>();
    private final WebPage homePage = new WebPage(0,AppConstants.WebPage.HOME, "High.bmp","Home to C3" ,"WE will rock u", "The church is pregnant.",true);
    
    {
        webPages.add(homePage);
    }

    public List<WebPage> retrievePages() {
        return webPages;
    }
}
