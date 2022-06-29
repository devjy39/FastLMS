package com.minicampus.fastlms.member.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponent {
    private final JavaMailSender javaMailSender;

    public boolean sendMail(String mail, String subject, String text) {
        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            messageHelper.setTo(mail);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        };

        try {
            javaMailSender.send(msg);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
