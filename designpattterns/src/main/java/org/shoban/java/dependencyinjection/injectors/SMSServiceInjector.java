package org.shoban.java.dependencyinjection.injectors;

import org.shoban.java.dependencyinjection.consumer.Consumer;
import org.shoban.java.dependencyinjection.consumer.MyDIApplication;
import org.shoban.java.dependencyinjection.service.SMSServiceImpl;

public class SMSServiceInjector implements MessageServiceInjector {

	@Override
	public Consumer getConsumer() {
		return new MyDIApplication(new SMSServiceImpl());
	}

}
