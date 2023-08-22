package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws MessagingException {

        System.out.println( "Preparing to send message" );
        String message ="hello, dear it is a test email for learning purpose";
        String subject =" confirmation";
        String to= "maryamsaddiqa7@gmail.com";
        String from ="bejaz2800@gmail.com";

       // sendEmail(message, subject,to,from);
        sendAttachment(message,subject,to,from);
    }

    private static void sendAttachment(String message, String subject, String to, String from) {
        // variable for gmail host: smtp.gmail.com.
        String host= "smtp.gmail.com";
        // get the system properities System.getProperties();
        Properties properties=System.getProperties();
        // setting important information to properities object for host
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        // step 1: to get the session object to send the email. without session we cant send the email
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bejaz2800@gmail.com","djoztjkrlqrikuxw");
            }
        });
        session.setDebug(true); // to debug the errors

        // step 2: compose the message [text, photo, multimedia, pdf. ] mineMessage
        MimeMessage mimeMessage= new MimeMessage(session);
        // we have to set properities. from where we are sending the messange
        // from email id
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // adding subject to message
            mimeMessage.setSubject(subject);

            // adding text and file to message, attachment
            // if a file is to be attached, we need to add the file path.
            String path= "C:\\Users\\malhi\\OneDrive\\Desktop\\maryampic.jpg";
            MimeMultipart multipart= new MimeMultipart(); // this multipart object can have both text and file
            // to set text in multipart
            MimeBodyPart text= new MimeBodyPart();
            MimeBodyPart attachment= new MimeBodyPart();
            try {
                text.setText(message);
                File file= new File(path);
                attachment.attachFile(file);

                // adding the text and attachment to multipart
            multipart.addBodyPart(text);
            multipart.addBodyPart(attachment);
            }catch (Exception e){
                e.printStackTrace();
            }

            mimeMessage.setContent(multipart);
            // STEP 3: Send the message using transport class

            Transport.send(mimeMessage);
            System.out.println("ssent sucessfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


    // this method is responsbile to send email
    private static void sendEmail(String message, String subject, String to, String from) throws MessagingException {
        // variable for gmail host: smtp.gmail.com.
        String host= "smtp.gmail.com";
        // get the system properities System.getProperties();
        Properties properties=System.getProperties();
        // setting important information to properities object for host
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        // step 1: to get the session object to send the email. without session we cant send the email
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bejaz2800@gmail.com","djoztjkrlqrikuxw");
            }
        });
        session.setDebug(true); // to debug the errors

        // step 2: compose the message [text, photo, multimedia, pdf. ] mineMessage
        MimeMessage mimeMessage= new MimeMessage(session);
        // we have to set properities. from where we are sending the messange
        // from email id
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // adding subject to message
            mimeMessage.setSubject(subject);
            // adding text to message
            mimeMessage.setText(message);

            // STEP 3: Send the message using transport class

            Transport.send(mimeMessage);
            System.out.println("ssent sucessfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
