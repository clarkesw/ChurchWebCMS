/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.service;

import com.milford.churchcms.controller.PrayerController;
import com.milford.churchcms.dao.Staff;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 * 
 * https://www.quickprogrammingtips.com/spring-boot/how-to-send-email-from-spring-boot-applications.html
 */
@Service
public class TextMessageService {
    
    @Autowired
    private JavaMailSender sender;
    public Logger logger = LoggerFactory.getLogger(TextMessageService.class);
    
    public boolean sendMessage(Staff staff, String messageText, String requester){
        
      logger.debug("   Staff : {}  Message : {}  Requester: {} ",staff.getFullName() ,messageText, requester);
      String sendTo = staff.getMobilePhone() + staff.getMobileCarrier();

      try {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(sendTo);
        helper.setText(messageText);
        helper.setSubject(requester);
         
        sender.send(message);
      } catch (MessagingException mex) {
          logger.debug(mex.toString());
         return false;
      }
      return true;
    }
    
    public void someMethod(){}
}
