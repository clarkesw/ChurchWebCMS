/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.repository;

import com.milford.churchcms.dao.Passage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author clarke
 */
public interface PassageRepository extends JpaRepository<Passage,Integer>{
    Passage findTopByOrderByIdDesc();
    List<Passage> findPassagesById(int id);
}
