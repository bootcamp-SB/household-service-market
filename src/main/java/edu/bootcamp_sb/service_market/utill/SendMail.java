package edu.bootcamp_sb.service_market.utill;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMail {

    private final JavaMailSender mailSender;

}
