package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

public interface ProductDao {
	String addProduct(Product product);
	Product getProductDetailsById(Long productId);
	List<Product> getSpecifiedCategoryAndRange(Category category,double sPrice,double ePrice);
	String applyDiscount(Category category,double discount);
	String deleteProductDetails(Long productId);
	String purchaseProduct(String pname,int qty);
}
