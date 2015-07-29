// File Name SendFileEmail.java

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



public class emailoutlook
{
	static String d_email = "pparthenhs@gmail.com";//to email mas 
	static String d_password = "jgfaksjbfkas8934j@njms$";// o kwdikos mas 
	static String d_host = "smtp.gmail.com";// o host
	static String d_port = "465";// to port
	static String m_to = "cparthen@hol.gr";//to email pou phgenei
	static String m_subject = "to eftia3a";
	static String m_text = "Hey, this is a test email.kai gia sas kai xwris outlouk!!!!!1";
	
	static private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
    
	
   public static void main(String [] args)
   {
      
      // Recipient's email ID needs to be mentioned.
      String to = "abcd@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "web@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties props = System.getProperties();
      
      props.put("mail.smtp.user", d_email);
      props.put("mail.smtp.host", d_host);
      props.put("mail.smtp.port", d_port);
      
      
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.socketFactory.port", d_port);
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.socketFactory.fallback", "false");
      
      props.setProperty("mail.user", d_email);
      props.setProperty("mail.password", d_password);
      
      
      Authenticator auth = new SMTPAuthenticator();
      Session session = Session.getInstance(props,auth); 
       
      
      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setText("This is message body");
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "file.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart );

         // Send message
         Transport.send(message);
         
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}