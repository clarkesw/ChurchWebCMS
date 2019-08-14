/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.dao.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public Logger logger = LoggerFactory.getLogger(WelcomeService.class);
    
    private final List<User> users = new ArrayList<>();
    private final User rootUser = new User("clarke","t",null);
    
    {
        users.add(rootUser);
    }

    public List<User> retrieveUsers() {
        return users;
    }
    
    public User retrieveOneUser(String username) {
        logger.debug("EventService.retrieveOneEvent name: {}" + username);
        for (User user : users) {
            if (user.getUsername().equals(username) ) {
                return user;
            }
        }
        return null;
    }
    
    public void deleteUser(int id) {
        logger.debug("EventService.deletePage id: {}" + id);
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User page = iterator.next();
            if (page.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void updateUser(User page){
        logger.debug("EventService.updatePage id: {}" + page.getId());
        deleteUser(page.getId());    
    	users.add(page);
    }
}
