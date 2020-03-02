package com.azure.partnercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("azure.properties")
public class AzurePartnercenterApiApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AzurePartnercenterApiApplication.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(AzurePartnercenterApiApplication.class, args);
		
	}

}
