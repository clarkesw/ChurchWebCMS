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
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */
@Service
public class TextMessageService {
    public Logger logger = LoggerFactory.getLogger(TextMessageService.class);
    
    public boolean sendMessage(Staff staff, String messageText, String requester){
      logger.debug("   Staff : {}  Subject : {}  Sender: {} ",staff.getFullName() ,messageText, requester);
      String host = "localhost";

      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);

      Session session = Session.getDefaultInstance(properties);

      try {
         MimeMessage message = new MimeMessage(session);

         String to = staff.getMobilePhone() + staff.getMobileCarrier();
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject(requester);
         message.setText(messageText);
         message.setFrom("people@milford.com");

         Transport.send(message);

      } catch (MessagingException mex) {
          logger.debug(mex.toString());
         return false;
      }
      return true;
    }
}
