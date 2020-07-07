/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.repository;

import com.milford.churchcms.dao.Staff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author clarke
 */
public interface StaffRepository extends JpaRepository<Staff,Integer>{
    Staff findByPosition(String position);
    List<Staff> findAllByPosition(String position);
    Optional<Staff> findByFullName(String fullName);
    List<Staff> findByFirstNameInAndLastNameIn(String firstName, String lastName);
    
    @Query("select fullName from Staff")
    List<String> getFullNames();
    
    List<Staff> findByRecievePrayerRequestsTrue();
    List<Staff> findByRecieveChurchUpdatesTrue();
    Staff findTopByOrderByIdDesc();
}
