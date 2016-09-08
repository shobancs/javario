package org.shoban.hibernate.spring.dao;

import java.util.List;

import org.shoban.hibernate.spring.model.Person;

public interface PersonDAO {

	public void save(Person p);
	
	public List<Person> list();
	
}
