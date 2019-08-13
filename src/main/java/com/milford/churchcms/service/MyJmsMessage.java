/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.WebPage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */

@Service
public class MyJmsMessage {
    
    private final List<WebPage> webPages = new ArrayList<>();
    
    public WebPage getWeb(){
        return webPages.get(0);
    }
}
