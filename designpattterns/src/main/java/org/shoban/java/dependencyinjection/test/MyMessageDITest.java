package org.shoban.java.dependencyinjection.test;

import org.shoban.java.dependencyinjection.consumer.Consumer;
import org.shoban.java.dependencyinjection.injectors.EmailServiceInjector;
import org.shoban.java.dependencyinjection.injectors.MessageServiceInjector;
import org.shoban.java.dependencyinjection.injectors.SMSServiceInjector;

public class MyMessageDITest {

	public static void main(String[] args) {
		String msg = "Hi Shoban";
		String email = "Shoban@abc.com";
		String phone = "4088888888";
		MessageServiceInjector injector = null;
		Consumer app = null;
		
		//Send email
		injector = new EmailServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, email);
		
		//Send SMS
		injector = new SMSServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, phone);
	}

}
