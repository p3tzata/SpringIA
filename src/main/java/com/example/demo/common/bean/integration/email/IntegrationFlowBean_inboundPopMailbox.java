package com.example.demo.common.bean.integration.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.MailReceivingMessageSource;
import org.springframework.integration.mail.MailSendingMessageHandler;
import org.springframework.integration.mail.Pop3MailReceiver;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessageHandler;

import com.example.demo.common.prop.EmailProp;
import com.rabbitmq.client.AMQP.Basic.Return;

/*
 * 
 *  In order to work send mail - you have to configure/disable your antivirus 
 * 
 * 
 */






@Configuration
public class IntegrationFlowBean_inboundPopMailbox {
	
	@Autowired
	private EmailProp emailProp;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	JavaMailSender mailSender;
	
	/*
	@Bean
	@InboundChannelAdapter(channel = "receivingPop3MailSendMailChannel", poller = @Poller(fixedRate = "30000" ))
	public MailReceivingMessageSource  recievingPop3mailbox() {
		
		Pop3MailReceiver pop3MailReceiver = 
				new Pop3MailReceiver(emailProp.getHost(), emailProp.getPop3Port(), emailProp.getUsername(),  emailProp.getPassword());
		pop3MailReceiver.setShouldDeleteMessages(true);
	    pop3MailReceiver.setMaxFetchSize(5);
	    
	    pop3MailReceiver.setJavaMailProperties(javaMailProp());
	    MailReceivingMessageSource mailReceivingMessageSource = new MailReceivingMessageSource(pop3MailReceiver);
	    return mailReceivingMessageSource;
		
	}
	*/
	
	private Properties javaMailProp() {
		Properties javaMailProperties = new Properties();
		
		//javaMailProperties.setProperty("mail.debug","true");		
		//javaMailProperties.setProperty("mail.pop3.starttls.enable","true");
		//javaMailProperties.setProperty("mail.pop3.starttls.required","true");
		javaMailProperties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.setProperty("mail.pop3.socketFactory.fallback","true");
		javaMailProperties.setProperty("mail.store.protocol","pop3s");
		return javaMailProperties;
	}

	


	@Bean
	@ServiceActivator(inputChannel = "receivingPop3MailConsoleChannel")
	public MessageHandler receivingPop3MailHendler() {
		
		return msg -> {
			MimeMessage  payload = (MimeMessage  ) msg.getPayload();
							try {
								System.out.println("Inbound adapter pop3 subject: " + payload.getSubject());
								
								
								
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} };
		
	}
	
	
	@Bean
	@ServiceActivator(inputChannel = "receivingPop3MailSendMailChannel")
	public MessageHandler receivingSendingPop3MailHendler() throws MessagingException {
		
		return msg -> {
			String payload = (String) msg.getPayload();
							try {
								
								MimeMessage mimeMessage = mailSender.createMimeMessage();
								MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
								    
								helper.setFrom("pet...@abv.bg");
							    helper.setTo("pe...@abv.bg");
								helper.setSubject("test subject 6663");
								helper.setText(payload);
							    mailSender.send(mimeMessage);  
								
								
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} };
		
		
	}
	
	@Bean
    public JavaMailSender getJavaMailSender() 
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.abv.bg69");
        mailSender.setPort(465);
          
        mailSender.setUsername("pet...@abv.bg");
        mailSender.setPassword("");
          
        Properties props = mailSender.getJavaMailProperties();
       
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
       
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
       
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");
        props.put("mail.debug","true");	
        return mailSender;
    }
	
	
	
}
