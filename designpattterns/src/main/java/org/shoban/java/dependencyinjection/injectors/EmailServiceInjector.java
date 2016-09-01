package org.shoban.java.dependencyinjection.injectors;

import org.shoban.java.dependencyinjection.consumer.Consumer;
import org.shoban.java.dependencyinjection.consumer.MyDIApplication;
import org.shoban.java.dependencyinjection.service.EmailServiceImpl;

public class EmailServiceInjector implements MessageServiceInjector {

	@Override
	public MyDIApplication getConsumer() {
		MyDIApplication app = new MyDIApplication();
		app.setService(new EmailServiceImpl());
		return app;
	}

}
