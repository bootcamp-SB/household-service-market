package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail-service")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/test-email")
    public void testMail(@RequestParam String to,
                         @RequestParam String username ,
                         @RequestParam String subject) throws MessagingException {
        emailService.sendBookingConfirmationMail(to,subject,username);
    }

}
