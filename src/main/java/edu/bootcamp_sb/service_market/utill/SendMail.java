package edu.bootcamp_sb.service_market.utill;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;




@Component
@RequiredArgsConstructor
public class SendMail {

    private final JavaMailSender mailSender;



    public void sendBookingConfirmation() throws MessagingException {

    }

}
