package com.azure.partnercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("azure.properties")
public class AzurePartnercenterApiApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AzurePartnercenterApiApplication.class, args);
		
	}

}
