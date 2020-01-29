/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.repository;

import com.milford.churchcms.dao.Sermon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author clarke
 */
public interface SermonRepository extends JpaRepository<Sermon,Integer>{
    Optional<Sermon> findTopByOrderByIdDesc();
    Optional<Sermon> findTopByOrderBySermonDateDesc();
}
