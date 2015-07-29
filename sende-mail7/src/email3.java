
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class email3 {    
	
	static String d_email = "pparthenhs@yahoo.gr";//to email mas 
	static String d_password = "eempcqbybiacumgk";// o kwdikos mas 
	static String d_host = "smtp.mail.yahoo.com";// o host
	static String d_port = "465";// to port
	static String m_to = "pparthenhs@gmail.com";//to email pou phgenei
	static String m_subject = "to eftia3a";
	static String m_text = "Hey, this is a test email.kai gia sas kai xwris outlouk!!!!!1";
    
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
            
              
            MimeMessage msg = new MimeMessage(session);
            
            msg.setText(m_text);
            
            msg.setSubject(m_subject);
            
            msg.setFrom(new InternetAddress(d_email));
            
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            
            Transport.send(msg);
            
            
            
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
   

}