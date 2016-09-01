package org.shoban.java.dependencyinjection.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.shoban.java.dependencyinjection.consumer.Consumer;
import org.shoban.java.dependencyinjection.consumer.MyDIApplication;
import org.shoban.java.dependencyinjection.injectors.MessageServiceInjector;
import org.shoban.java.dependencyinjection.service.MessageService;

public class MyDIApplicationJUnitTest {

	private MessageServiceInjector injector;
	@Before
	public void setUp(){
		//mock the injector with anonymous class
		injector = new MessageServiceInjector() {
			
			@Override
			public Consumer getConsumer() {
				//mock the message service
				return new MyDIApplication(new MessageService() {
					
					@Override
					public void sendMessage(String msg, String rec) {
						System.out.println("Mock Message Service implementation");
						
					}
				});
			}
		};
	}
	
	@Test
	public void test() {
		Consumer consumer = injector.getConsumer();
		consumer.processMessages("Hi Shoban", "Shoban@javario.com");
	}
	
	@After
	public void tear(){
		injector = null;
	}

}
