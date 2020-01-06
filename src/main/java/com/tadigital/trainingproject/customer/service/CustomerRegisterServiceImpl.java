package com.tadigital.trainingproject.customer.service;

import java.sql.Connection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.dao.CustomerDao;
import com.tadigital.trainingproject.service.Service;

@Component
public class CustomerRegisterServiceImpl extends Service implements CustomerRegisterService {

	Logger logger = LogManager.getLogger(CustomerRegisterServiceImpl.class);	
	
	CustomerDao custDao;
	
	@Autowired
	public CustomerRegisterServiceImpl(CustomerDao custDao) {
		logger.info("CustomerDao dependency injected");
		this.custDao = custDao;
		
	}

	@Override
	public boolean Register(String name, String email, String password) {
		
		logger.info("Register execution started");
		
		String[] stringArray = name.split(" ");
		int rows = 0;
		
		if(stringArray.length > 1) {
			rows = this.custDao.RegisterUser(stringArray[0],stringArray[1],email,password);
		}
		else {
			rows = this.custDao.RegisterUser(stringArray[0],"",email,password);
		}
		
		logger.info("Register execution ended");
		
		if(rows > 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void sendRegistrationMail(String name, String email) {
		
		logger.info("sendRegistrationMail execution started");
		
		Properties property = getProperties();
		Session s = connectToMailServer(property);
		
		//COMPOSE MESSAGE
		try {
		        MimeMessage mimeMessage = new MimeMessage(s);
		        mimeMessage.setFrom(new InternetAddress("darshanhosahalli@gmail.com"));
		        mimeMessage.setRecipients(Message.RecipientType.TO,    InternetAddress.parse(email));
		        mimeMessage.setSubject("Welcome to TA Digital E-Commerce Portal.");
		        String msg = "<body><b>Dear " + name + ",</b><br>" +
		                     "Thanks for Registering on our portal." +
		                     "<img src='https://image.shutterstock.com/image-vector/welcome-poster-spectrum-brush-strokes-260nw-1146069941.jpg'></img>"+
		                     "\n\nWelcome to TA Digital Family." +
		                     "\n\nThanks & Regards," +
		                     "\nTA Digital</body>";
		        mimeMessage.setContent(msg,"text/html");
		        
		        //SEND MAIL
		        Transport.send(mimeMessage);
		} catch (MessagingException mex) {
           logger.error(mex);
        }
		
		logger.info("sendRegistrationMail execution ended");

	}
}
