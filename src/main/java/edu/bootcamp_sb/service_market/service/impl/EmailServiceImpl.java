package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;


    @Override
    public void sendTestMail(
            String to, String subject, String username)
            throws MessagingException {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();

            context.setVariable("username",username);

        String process = templateEngine.process("bookingMail-template", context);
         helper.setTo(to);
         helper.setSubject(subject);
         helper.setText(process,true);
         helper.setFrom("Nestify");

         javaMailSender.send(mimeMessage);

    }
}
