package org.shoban.springbeanlifecycle.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.shoban.springbeanlifecycle.service.MyAwareService;

public class SpringAwareMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-aware.xml");

		ctx.getBean("myAwareService", MyAwareService.class);
		
		ctx.close();
	}

}
