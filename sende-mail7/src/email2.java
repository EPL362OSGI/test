
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class email2 {    
	
	static String d_email = "pparthenhs@gmail.com";//to email mas 
	static String d_password = "jgfaksjbfkas8934j@njms$";// o kwdikos mas 
	static String d_host = "smtp.gmail.com";// o host
	static String d_port = "465";// to port
	static String m_to = "cparthen@hol.gr";//to email pou phgenei
	static String m_subject = "To email einai etoimo ";
	static String m_text = "Hey, this is a test email.";
    
	static private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
    
    public static void main(String[] args){
    
        Properties props = new Properties();//ena hashtable
        
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
        
        try {
        	
           Authenticator auth = new SMTPAuthenticator();
           Session session = Session.getInstance(props,auth); 
              
            Message msg = new MimeMessage(session);
             
            	msg.setSubject(m_subject);
            	msg.setFrom(new InternetAddress(d_email));
            	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            
            BodyPart messageBodyPart = new MimeBodyPart();
            
            	messageBodyPart.setText(m_text);
            
            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);
            
            messageBodyPart = new MimeBodyPart();
            
            String file = "C:/Users/Panos/Desktop/EPL222.1.AB325898.docx";
            
            String fileName = "EPL222.1.AB325898.docx";
            
            DataSource source = new FileDataSource(file);
            
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            msg.setText(m_text);
            
            msg.setContent(multipart);
          
          
            Transport.send(msg);
   
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
   

}