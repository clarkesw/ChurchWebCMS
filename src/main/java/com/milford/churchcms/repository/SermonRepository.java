/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.repository;

import com.milford.churchcms.dao.Sermon;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author clarke
 */
public interface SermonRepository extends JpaRepository<Sermon,Integer>{
    Optional<Sermon> findTopByOrderByIdDesc();
    Optional<Sermon> findTopByOrderBySermonDateDesc();
    
    @Query("SELECT id FROM Sermon ORDER BY id DESC")
    List<Integer> getGreatestSid();
}
