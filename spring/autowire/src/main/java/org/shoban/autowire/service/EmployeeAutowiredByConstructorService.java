package org.shoban.autowire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.shoban.autowire.model.Employee;

public class EmployeeAutowiredByConstructorService {

	private Employee employee;

	//Autowired annotation on Constructor is equivalent to autowire="constructor"
	@Autowired(required=false)
	public EmployeeAutowiredByConstructorService(@Qualifier("employee") Employee emp){
		this.employee=emp;
	}
	
	
	public Employee getEmployee() {
		return this.employee;
	}
}
