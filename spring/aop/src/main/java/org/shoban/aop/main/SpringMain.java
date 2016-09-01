package org.shoban.aop.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.shoban.aop.service.EmployeeService;

public class SpringMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("C:\\shoban\\myproject\\javario\\spring\\aop\\src\\main\\resources\\spring.xml");
		EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
		
		System.out.println(employeeService.getEmployee().getName());
		
		employeeService.getEmployee().setName("Shoban");
		
		employeeService.getEmployee().throwException();
		
		ctx.close();
	}

}
