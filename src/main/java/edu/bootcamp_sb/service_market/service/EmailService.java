package edu.bootcamp_sb.service_market.service;

import jakarta.mail.MessagingException;

import java.util.Map;


public interface EmailService {

    void sendTestMail(String to, String subject,String username) throws MessagingException;

    void sendBookingConfirmationMail(String to, Map<String, Object> variables) throws MessagingException;

}
