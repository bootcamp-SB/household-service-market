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


import java.util.Map;


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

        String process = templateEngine.process("test-mail", context);
         helper.setTo(to);
         helper.setSubject(subject);
         helper.setText(process,true);
         helper.setFrom("Nestify");

         javaMailSender.send(mimeMessage);

    }

    @Override
    public void sendBookingConfirmationMail(String to, Map<String, Object> variables)
            throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariables(variables);

        String process = templateEngine.process("booking-confirmation-mail", context);

        helper.setFrom("Nestify");
        helper.setTo(to);
        helper.setSubject("Booking Confirmed");
        helper.setText(process,true);

        javaMailSender.send(mimeMessage);

    }

    @Override
    public void sendBookingCanceledEmailToUser(String to, Map<String, Object> variables) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariables(variables);

        String process = templateEngine.process("booking-cancled-mail", context);

        helper.setFrom("Nestify");
        helper.setSubject("You have canceled the booking!");
        helper.setTo(to);
        helper.setText(process,true);

        javaMailSender.send(mimeMessage);


    }
}
