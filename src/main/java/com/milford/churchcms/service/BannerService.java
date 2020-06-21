/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Banner;
import com.milford.churchcms.repository.BannerRepository;
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
public class BannerService {
    public Logger logger = LoggerFactory.getLogger(BannerService.class);

    @Autowired
    BannerRepository repository;
        
    public Optional<Banner> showBanner(){
        return repository.findTopByOrderByIdDesc();
    }
    public void addBanner(Banner banner){
        repository.save(new Banner(banner.getMessage()));
    }
    
    public void deleteBanner(){
        repository.deleteAll();
    }
}
