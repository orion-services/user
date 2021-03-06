package orion.users.util;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
    public static void sendMail(String recepient, String hash) throws Exception {
        System.out.println("Preparing to sent");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        String myAccountEmail = "guilhermemoreiramex@gmail.com";
        String password = "taturanaLOL171";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient, hash);

        Transport.send(message);
        System.out.println("Message sent");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String hashcode) {
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("oi, eu sou Goku");
           
           
            String htmlCode ="Your code, don't sent to nobody: ";
            htmlCode = htmlCode + hashcode;
            message.setContent(htmlCode, "text/html");
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
    return null;
    } 
}