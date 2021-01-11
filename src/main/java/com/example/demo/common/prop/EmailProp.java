package com.example.demo.common.prop;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
@Component
@Data
@Validated
@ConfigurationProperties(prefix = "burger.global.email")
public class EmailProp {

	String username;
	String password;
	String host;
	String mailbox;
	Integer pop3Port;
	Integer pollRate;

	public String getPop3Url() {
	return String.format("pop3://%s:%s@%s:%s/%s", username,password,host,pop3Port, mailbox);	
	}
	
	
}
