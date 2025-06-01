package com.reminder.demo.Config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class emailConfig {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private String port;

    @Bean
    public JavaMailSender javaMailSender(){
        /*
         * Definimos e instanciamos un objeto de tipo JavaMailSenderImpl
         * Se realiza la configuración
         */
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setPort(Integer.parseInt(port));
        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Añadir esta línea para confiar en el certificado del servidor de correo
        properties.put("mail.smtp.ssl.trust", host);
        
        // Opcionalmente puedes añadir esta línea para ver más información de debugging
        // properties.put("mail.debug", "true");
        
        return mailSender;
    }
}