/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Address;
import com.milford.churchcms.dao.Church;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class ChurchService {
    public Logger logger = LoggerFactory.getLogger(ChurchService.class);
    private Address address = new Address("45 Millford Church Rd", "Smyrna", "GA", 30060);
    private Church myC = new Church("Milford Baptist","C3","church@milford.com",address, "919-368-6332","Clarence");
    
    public Church getChurchInfo(){
        return myC;
    }
    
    public void updateInfo(Church info){
        logger.debug("pdateInfo info: {}" +info);
        myC = new Church(info);
    }
    
}
