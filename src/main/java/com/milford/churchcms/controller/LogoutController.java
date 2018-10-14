/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class LogoutController {
    
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                    HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response,
                                authentication);
        }

        return "redirect:/";
    }
}
