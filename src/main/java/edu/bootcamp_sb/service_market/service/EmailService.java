package edu.bootcamp_sb.service_market.service;

import jakarta.mail.MessagingException;


public interface EmailService {

    void sendTestMail(String to,
                                     String subject,
                                     String username) throws MessagingException;

}
