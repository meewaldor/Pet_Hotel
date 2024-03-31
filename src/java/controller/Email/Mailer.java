/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Email;


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    public static void send(String to, String subject, String msg) {

        final String user = "anh713988@gmail.com";//thay bang email cua minh
        final String pass = "ycwk bbec ocea uvvf";//thay dong nay bang code tren gg https://myaccount.google.com/apppasswords

        //1st step) Get the session object	
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly; để như này là smtp của email
        props.put("mail.smtp.auth", "true");

        // Enable TLS encryption
        props.put("mail.smtp.starttls.enable", "true");//có thêm dòng này để xác thực với mail

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        //2nd step)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);

            //3rd step)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
