package com.tadigital.trainingproject.service;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.dao.Dao;

@Component
public class SendContactEmail extends Service {
	
	Logger logger = LogManager.getLogger(SendContactEmail.class);
	
	public SendContactEmail() {
		
	}
	
	public boolean sendEmailToCust(String fname, String lname, String email, String subject, String msg) {
		
		logger.info("sendEmailToCust execution started");
		
		Properties prop = getProperties();
		
		Session s = connectToMailServer(prop);
		
		//COMPOSE MESSAGE
		try {
		        MimeMessage mimeMessage = new MimeMessage(s);
		        mimeMessage.setFrom(new InternetAddress("darshanhosahalli@gmail.com"));
		        mimeMessage.setRecipients(Message.RecipientType.TO,    InternetAddress.parse(email));
		        mimeMessage.setSubject(subject);
		        BodyPart messageBodyPart1 = new MimeBodyPart(); 
		        String message = "<body><b>Dear " + fname +" "+lname+",</b><br>" +
		                     "Thanks for reaching out to us regarding your concern." +
		                     "<br>"+
		                     "\n\n We will definitely look into your issue that is.. <br>." +msg+
		                     "\n\n<br>Thanks & Regards,<br>" +
		                     "\nTA Digital</body>";
		        
		        messageBodyPart1.setContent(message,"text/html");
		        
		        MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		        
		        String filename = "D:/Trainee Engineers July 2019/workspace/intermediate/src/main/webapp/images/bag1.jpg";//change accordingly
		        File file = new File(filename);
		        DataSource source = new FileDataSource(file);  
		        messageBodyPart2.setDataHandler(new DataHandler(source));  
		        messageBodyPart2.setFileName(filename);
		        
		        Multipart multipart = new MimeMultipart();  
		        multipart.addBodyPart(messageBodyPart1);  
		        multipart.addBodyPart(messageBodyPart2);  
		        
		        mimeMessage.setContent(multipart);
		        
		        //SEND MAIL
		        Transport.send(mimeMessage);
		} catch (MessagingException mex) {
			logger.error(mex);
			return false;
                        
        }
		
		logger.info("sendEmailToCust execution ended");
		
		return true;
	}

}
