package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Scanner;

public class AddProduct {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in)) {
			ProductDao dao = new ProductDaoImpl();
			
			System.out.println("Enter Product Details - Category , Product Name , Price , Available Qty ");
			
			Product product = new Product(Category.valueOf(sc.next().toUpperCase()), sc.next(),sc.nextDouble(),sc.nextInt());
			System.out.println(dao.addProduct(product));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
