package org.shoban.spring.di.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.shoban.spring.di.consumer.MyXMLApplication;

public class ClientXMLApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"C:\\shoban\\myproject\\javario\\spring\\core\\src\\main\\resource\\applicationContext.xml");
		MyXMLApplication app = context.getBean(MyXMLApplication.class);

		app.processMessage("Hi shoban", "shoban@abc.com");

		// close the context
		context.close();
	}

}
