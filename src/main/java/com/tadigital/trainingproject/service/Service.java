package com.tadigital.trainingproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Service {
	
	private String username,password;
	private Properties prop = new Properties();
	
	Logger logger = LogManager.getLogger(Service.class);

	public String getHtmlFormat(String dob) {
		
		logger.info("getHtmlFormat started");
		
		String dobArray[] = dob.split("/");
		String day = dobArray[0];
		String month = dobArray[1];
		String year = dobArray[2];
		
		logger.info("getHtmlFormat ended");
		
		return ""+year+"-"+month+"-"+day;
	}
	
	public String[] getStates() {
		
		logger.info("getStates started");
		
		String [] states = {"karnataka","Andhra Pradesh","jammu","goa","TamilNadu","telangana"};
		
		logger.info("getStates ended");
		
		return states;
		
	}
	
	public String[] getCountry() {
		
		logger.info("getCountry started");
		
		String [] country = {"India","Usa","China","Canada"};
		
		logger.info("getCountry ended");
		return country;
		
	}
	
	protected Properties getProperties() {
		
		logger.info("getProperties started");
		
		Properties prop1 = new Properties();
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/tadigital/properties/mail.properties");
		try {
			prop1.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String smtpHost = prop1.getProperty("mail.smtp.host");
        String socketPort = prop1.getProperty("mail.smtp.socketFactory.port");
        String socketClass = prop1.getProperty("mail.smtp.socketFactory.class");
        String smtpAuth = prop1.getProperty("mail.smtp.auth");
        String smtpPort = prop1.getProperty("mail.smtp.port");
        this.username = prop1.getProperty("mail.username");
        this.password = prop1.getProperty("mail.password");
        String smtpenable = prop1.getProperty("mail.smtp.starttls.enable"); 
		
		prop.put("mail.smtp.host",smtpHost);
        prop.put("mail.smtp.socketFactory.port",socketPort);
        prop.put("mail.smtp.socketFactory.class",socketClass);
        prop.put("mail.smtp.auth",smtpAuth);
        prop.put("mail.smtp.port",smtpPort);
        prop.put("mail.smtp.starttls.enable",smtpenable);
        
        logger.info("getProperties ended");
        
        return prop;
	}
	
protected Session connectToMailServer(Properties properties) {
	
		logger.info("connectToMailServer started");
		
		Session s = Session.getInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
		
		logger.info("connectToMailServer ended");
		
		return s;
		
	}
		
}
