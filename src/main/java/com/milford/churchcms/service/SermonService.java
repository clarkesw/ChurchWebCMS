/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.Description;
import com.milford.churchcms.dao.Passage;
import com.milford.churchcms.dao.Sermon;
import com.milford.churchcms.repository.PassageRepository;
import com.milford.churchcms.repository.SermonRepository;
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
public class SermonService {
    public Logger logger = LoggerFactory.getLogger(SermonService.class);

    @Autowired
    SermonRepository repository;
    
    @Autowired
    PassageRepository passRepo;
    
    public List<Sermon> showSermon(){
        return repository.findAll();
    }
    
    public void addSermonPost(Sermon sermon){
        Optional<Sermon> lastSermon = repository.findTopByOrderBySermonDateDesc();
        
        int lastSermonId = (lastSermon.isPresent()) ? lastSermon.get().getId() + 1 : 1;
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), sermon.getDescription(),
                sermon.getSermonDate(),sermon.getPassages()));
    }
    
    public void deleteSermon(int id){
        repository.deleteById(id);
    }
    
    public void updateSermonPost(Sermon sermon, List<Passage> passages){
        repository.delete(sermon);
        Optional<Sermon> lastSermon = repository.findTopByOrderBySermonDateDesc();
        
        int lastSermonId = (lastSermon.isPresent()) ? lastSermon.get().getId() + 1 : 1;
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), sermon.getDescription(),
                sermon.getSermonDate(),passages));
    }
    
    public Optional<Sermon> updateShowSermonGet(int id){
        return repository.findById(id);
    }
    
    public void addDescriptionSermonPost(List<Passage> passages, String description, int sermonId){
        Integer lastSermonIdRep = repository.getGreatestSid().get(0);
        Sermon sermon = repository.findById(sermonId).get();
       
        repository.deleteById(sermonId);
        passRepo.saveAll(passages);
             
        int lastSermonId = (lastSermonIdRep != null) ? lastSermonIdRep + 1 : 1;
        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), description,
                sermon.getSermonDate(),passages));
    }
    
    private void findLastIdSave(){
//        int lastSermonId = (lastSermonIdRep != null) ? lastSermonIdRep + 1 : 1;
//        repository.save(new Sermon(lastSermonId, sermon.getTitle(), sermon.getSubTitle(), description.getDescription(),
//                sermon.getSermonDate(),passages));
    }
}
