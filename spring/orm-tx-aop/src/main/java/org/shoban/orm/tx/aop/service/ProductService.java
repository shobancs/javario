package org.shoban.orm.tx.aop.service;

import org.shoban.orm.tx.aop.dao.ProductDao;
import org.shoban.orm.tx.aop.model.Product;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public void add(Product product) {
		productDao.persist(product);
	}
	
	public void addAll(Collection<Product> products) {
		for (Product product : products) {
			productDao.persist(product);
		}
	}
	
	public List<Product> listAll() {
		return productDao.findAll();
		
	}

}
