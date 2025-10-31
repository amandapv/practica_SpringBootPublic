package com.example.palmares_act_form.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    public boolean sendEmail(String destination, String subject, String textMessage, String adjunto) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); //se añade true para indicarle que el correo será "Multipart", ya qu eno es un correo simple (texto plano), sino que adjuntaremos archivos (multipart - contenido complejo)
            helper.setTo(destination);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            File archivoAdjunto = new File(adjunto);
            helper.addAttachment(archivoAdjunto.getName(), archivoAdjunto); 
            sender.send(message);
            return true;
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return false; 
        }
    } 

    
}
