/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.ChurchInfo;
import com.milford.churchcms.dao.Staff;
import com.milford.churchcms.dao.User;
import com.milford.churchcms.repository.CalendarEventRepository;
import com.milford.churchcms.repository.ChurchRepository;
import com.milford.churchcms.repository.StaffRepository;
import com.milford.churchcms.repository.UserRepository;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author clarke
 */
@Service
public class StaffService {
    public Logger logger = LoggerFactory.getLogger(StaffService.class);
    private int staffId;
    private String role;

    @Autowired
    StaffRepository repository;
    
    @Autowired
    ChurchRepository churchRepo;
    
    @Autowired
    CalendarEventRepository calendarRepo;
 
    @Autowired
    UserRepository userRepo;
    
    public List<Staff> showStaff(){
        return repository.findAll();
    }
    
    public void addStaffPost(Staff staff){
        setPrefContact(staff);
        repository.save(staff); 
    }
    
    public void deleteStaff(@RequestParam int id){
        Staff staff = repository.getOne(id);
        int userId = staff.getUser().getId();
        userRepo.deleteById(userId);
        repository.deleteById(id);          
    }
    
    public void updateStaffPost(Staff staff){
        User user = staff.getUser();
        setPrefContact(staff);
        
        if(user != null)
            userRepo.delete(staff.getUser());
        repository.delete(staff);
        repository.save(staff);  
    }
    
    public Staff updateStaffGet(int id){
        return repository.findById(id).get();
    }
    
    public User findByUsername(String userName){
        return userRepo.findByUsername(userName);
    }
    
    public void updateUserPost(User user){
        List<Staff> savedStaff = null;
        ChurchInfo church = null;
        user.setRole(role);

        userRepo.delete(user);
        List<Staff> staff = repository.findAll();
        Optional<ChurchInfo> optChurch = churchRepo.findTopByOrderByIdDesc();
        
        if(optChurch.isPresent()){
            church = optChurch.get();
            churchRepo.deleteAll();
        }
        
        ListIterator<Staff> staffIterator = staff.listIterator();
        while(staffIterator.hasNext()){
            Staff nextStaff = staffIterator.next();
            if(nextStaff.getId().equals(staffId)){
                repository.deleteById(staffId);
                nextStaff.setUser(user);
                repository.save(nextStaff);            
            }
        }

        if(optChurch.isPresent()){
            church.setStaffers(savedStaff);
            churchRepo.save(church);
        }        
    }
    
    public User findUserById(int userId){
        Optional<User> optUser = userRepo.findById(userId);
        User user = optUser.get();
        user.setBlankPassword();      
        return user;
    }
    
    public boolean updateUserGet(int userId, int staffId, User currentUser){
        
        this.staffId = staffId;
        role = currentUser.getRole(); 
        return false;
    }

    private void setPrefContact(Staff staff) {
        String prefContact = staff.getPrefferedContact().replace(" ", ""); 
        Class staffObj = staff.getClass();
        Method[] methods = staffObj.getMethods();
      
        for(Method method : methods){
             if(method.getName().equals("get"+prefContact)){
                try {
                    staff.setPrefferedContact((String)method.invoke(staff));
                }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    logger.error("Failed to set contact ",prefContact, ex);
                }     
             }
         }
    }
}
