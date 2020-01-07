/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.repository;

import com.milford.churchcms.dao.Banner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author clarke
 */
public interface BannerRepository extends JpaRepository<Banner,Integer>{
    Optional<Banner> findTopByOrderByIdDesc();
   // Banner findById(int address_id);
}
