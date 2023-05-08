/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.util;

/**
 *
 * @author MSI
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ApiMailer {
    private final String username;
    private final String password;
    private final Properties properties;

    public ApiMailer(String username, String password) {
        this.username = username;
        this.password = password;

        // Configure SMTP properties
        properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com"); // Change to your SMTP server
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        Session session;
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);
       String content = new String();
        message.setContent(content, "text/html");
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }
    
    
    
}
