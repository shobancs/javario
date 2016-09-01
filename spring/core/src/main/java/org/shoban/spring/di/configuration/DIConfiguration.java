package org.shoban.spring.di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.shoban.spring.di.services.EmailService;
import org.shoban.spring.di.services.MessageService;

@Configuration
@ComponentScan(value={"org.shoban.spring.di.consumer"})
public class DIConfiguration {

	@Bean
	public MessageService getMessageService(){
		return new EmailService();
	}
}
