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
@ConfigurationProperties(prefix = "burger.global")
public class GlobalProp {

	@Min(value = 2)
	@Max(value = 10)
	private int ingrediantPageSize;
	
	private String readWriteDir;
	
	public  String getBaseIntegrationDir() {
		return readWriteDir + "/integrationBaseDir";
	}
	
	
}
