package com.supriya.Booking_Service.notifications;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandler {

    private static final MailHandler mailer = new MailHandler();

    private MailHandler(){

    }

    public void sendMail(long id,String userName, String userEmail,double amount){
        String host="smtp.gmail.com";

        Properties props=System.getProperties();

        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.ssl.enable",true);
        props.put("mail.smtp.auth",true);

        Session session = Session.getInstance(props,new MailAuthenticator());

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(MailConstant.SENDER);
            message.setRecipients(Message.RecipientType.TO,userEmail);

            message.setSubject(MailConstant.SUBJECT);
            String content="Dear " + userName+
                    ",\n" +
                    "Thank you for choosing our booking service! Your reservation has been confirmed.\n" +
                    "\n" +
                    "Booking Details:\n" +
                    "    Booking ID: " +id+"\n"+
                    "    Total Amount: " +amount+"\n"+
                    "\n"+
                    "We look forward to providing you with a great experience. If you have any questions or concerns, please feel free to contact us.\n" +
                    "\n" +"\n"+
                    "Best regards,\n" +
                    "The Booking Service Team";
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Notification mail Send Successfully");

    }

    public static MailHandler getInstance(){
        return mailer;
    }
}
