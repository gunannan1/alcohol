package com.app.alcohol.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * Email service
 */
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


    /**
     * send resetting password email
     * @param to
     * @param subject
     * @param template
     * @param params
     * @throws Exception
     */
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



