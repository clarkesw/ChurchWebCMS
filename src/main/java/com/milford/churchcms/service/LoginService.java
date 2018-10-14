package com.milford.churchcms.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userid, String password) {
        System.out.println("++++ "+userid+"  "+password);
        return userid.equalsIgnoreCase("clarke")
                && password.equalsIgnoreCase("test");
    }

}