package com.sunbeam.dao;

import org.hibernate.*;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;

import com.sunbeam.entities.Product;

public class ProductDaoImpl implements ProductDao{

	@Override
	public String addProduct(Product product) {
		String msg="Producted Not Added ...";

		Session session=getFactory().getCurrentSession();

		Transaction tx=session.beginTransaction();
		try {
			Serializable productId = session.save(product);
			tx.commit();
			msg = "Product Added Successfully , with Id"+productId;
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		
		return msg;
	}

	@Override
	public Product getProductDetailsById(Long productId) {
		
		Product product = null;
		
		Session session = getFactory().getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		try {
			product = session.get(Product.class, productId);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		
		return product;
	}
	
}
