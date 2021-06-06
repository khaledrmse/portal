package it.intervalle.portal.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import it.intervalle.portal.entity.Client;
import net.bytebuddy.utility.RandomString;

@Service
public class MailService {
	private static Logger log = LoggerFactory.getLogger(MailService.class); 
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000; 
	
	 @Autowired
	 private MuserDetailsServices muserDetailsServices;
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @Autowired
	 JavaMailSender mailSender;
	
 
	
	
	
	public void generateOneTimePassword(Client client)
	        throws UnsupportedEncodingException, MessagingException {
	    String OTP = RandomString.make(8);
	    String encodedOTP = bCryptPasswordEncoder.encode(OTP);
	     
	    client.setPassword(encodedOTP);
	    client.setTimepassword(new Date());
	     
	    muserDetailsServices.updateOrSaveclient(client);
	     
	    sendOTPEmail(client, OTP);
	}
	
	private void sendOTPEmail(Client client, String OTP)
	        throws UnsupportedEncodingException, MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	 
	
	    log.info("sending mail to ....."+  client.getFirtsname()+" "+client.getLastname());
	  
	

	     String subject = "one time password (OTP)";
	     
	    String content = "<p>Bonjour  " + client.getFirtsname()+" "+client.getLastname()  + "</p>"
 	            + "Mot de passe :</p>"
	            + "<p><b>" + OTP + "</b></p>"
	            + "<br>"
	            + " Veillez charger les document demandés dans l'URL :</p>"
	            + "<p><b>http://10.10.0.116:9998/load</b></p>"
	            + "<br>"
	            + "<p>Note: ce  OTP va expirer dans  5 minutes.</p>"
	            +"<img src=\"https://ebanking.algerie.natixis.com/ebanking/image.ebk?ressource=headerBanner.jpg&id=25926\" alt=\"htts://javabydeveloper.com\\\" height=\"110\\\" width=\"500\\\"/>";
	 
	    helper.setSubject(subject);
	    helper.setTo(client.getMail());
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);      
	}
	
	
	
	public void clearOTP(Client client) {
	    client.setPassword(null);
	    client.setTimepassword(null);
	    muserDetailsServices.updateOrSaveclient(client); 
	}
	
 public boolean ExpiredOTP(Client client)
 {
	 
	 if(client.getPassword()==null)
	 {
		 return false;
	 }
	 
	 long currentTimeMils= System.currentTimeMillis();
	 long timerequested = client.getTimepassword().getTime();
	 
	 if(timerequested+OTP_VALID_DURATION <currentTimeMils)
	 {
		 return false;
	 }
	 
	 
	 
	 
	 return true;
 }
}
