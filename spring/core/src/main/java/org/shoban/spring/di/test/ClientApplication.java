package org.shoban.spring.di.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.shoban.spring.di.configuration.DIConfiguration;
import org.shoban.spring.di.consumer.MyApplication;

public class ClientApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		MyApplication app = context.getBean(MyApplication.class);
		
		app.processMessage("Hi shoban", "shoban@abc.com");
		
		//close the context
		context.close();
	}

}
