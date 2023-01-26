package com.learning.utility.email;


import com.learning.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSender {

    private final JavaMailSender mailSender;
    private final StudentRepository studentRepository;

    public void mailSenderThread(String from, String subject, String message) {

        Runnable runnable = () -> {
            List<String> emailList = studentRepository.findEmails();
        try {
            log.info("Sending Emails...");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            for (String email : emailList) {
                simpleMailMessage.setFrom(from);
                simpleMailMessage.setTo(email);
                System.out.println(String.format("Mail sent to %s", email));
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(message);

                mailSender.send(simpleMailMessage);
            }
            log.info(" All Mails Sent Successfully...");

        }catch (Exception e){
                log.error("Error while sending mails");
        }
        };
        CompletableFuture.runAsync(runnable);
    }
}
