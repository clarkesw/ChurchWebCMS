/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.repository.PassageRepository;
import com.milford.churchcms.repository.SermonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clarke
 */
public class PassageService {
    public Logger logger = LoggerFactory.getLogger(PassageService.class);

    @Autowired
    PassageRepository repository;
    
    @Autowired
    SermonRepository sermonRepo;
        
    public List<Passage> showSermon(){
        return repository.findAll();
    }
    
    public void deleteSermon(int id){
        repository.deleteById(id); 
    }
    
    public void updateSermonPost(Passage passage){
        repository.deleteById(passage.getId());
        repository.save(passage);
    }
    
    public Optional<Passage> updateShowSermonGet(int id){
        return repository.findById(id);
    }
    
    public void addPassagesToSermonPost(Passage passage, int sermonId){
        Optional<Sermon> mySermon = sermonRepo.findById(sermonId);
        Sermon sermon = mySermon.get();     
        sermonRepo.delete(sermon);
        
        sermon.getPassages().add(new Passage(passage)); 
        List<Passage> passes = sermon.getPassages();
        
        Optional<Sermon> lastSermon = sermonRepo.findTopByOrderBySermonDateDesc();
        int lastSermonId = (lastSermon.isPresent()) ? lastSermon.get().getId() + 1 : 1;

        sermonRepo.save(new Sermon(lastSermonId, sermon.getTitle(),sermon.getSubTitle(),sermon.getDescription(),sermon.getSermonDate(),
                new ArrayList<>(passes)));
    }
}
