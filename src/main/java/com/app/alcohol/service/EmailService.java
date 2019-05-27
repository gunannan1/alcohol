package com.app.alcohol.service;

import com.app.alcohol.enums.ResultEnum;
import com.app.alcohol.exception.GlobalException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
@Async("asyncExecutor")
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private String username;



    public void sendEmail(String to, String subject,String template, Map<String, Object> params) throws Exception {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        String emailContent = templateEngine.process(template , context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(emailContent, true);

        javaMailSender.send(message);
    }


}



