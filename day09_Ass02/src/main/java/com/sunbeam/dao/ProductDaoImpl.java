package com.sunbeam.dao;

import org.hibernate.*;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

import com.sunbeam.entities.Category;
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

	@Override
	public List<Product> getSpecifiedCategoryAndRange(Category category, double sPrice, double ePrice) {
		
		String jpql = "select p from Product p where p.price between :start and :end and p.category=:cat";
		List<Product> products = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			products = session.createQuery(jpql, Product.class)
					.setParameter("start", sPrice)
					.setParameter("end", ePrice)
					.setParameter("cat", category)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return products;
	}

	@Override
	public String applyDiscount(Category category, double discount) {
		String msg = "Applying discount failed...!";
		String jpql = "update Product p set p.price=p.price-:disc where p.category=:cat";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			int rows = session.createQuery(jpql).setParameter("disc", discount).setParameter("cat", category)
					.executeUpdate();
			tx.commit();
			msg = "Total Product discounted is - " + rows;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return msg;
	}

	@Override
	public String deleteProductDetails(Long productId) {
		String msg = "deletion failed";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				msg = "product details deleted...";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			
			throw e;
		}

		return msg;
	}

	@Override
	public String purchaseProduct(String pname, int qty) {
		String msg = "Failed to Purchase Product ..!!";
		String jpql = "select p from Product p where pName=:pname";
		Product product = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			product = session.createQuery(jpql, Product.class).setParameter("pname", pname).getSingleResult();
			if(product != null && product.getAvlQty()>= qty) {
			product.setAvlQty((product.getAvlQty())-(qty));
			msg = "Successfully Purchased!!";
			}else 
			{
				msg = "Product is not Available...";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return msg;
	}
	
	
}
