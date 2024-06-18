package com.sunbeam.entities;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Category category;
	@Column(name = "p_name" ,length = 25,unique = true)
	private String pName;
	private double price;
	private int avlQty;
	
	public Product() {
		
	}
	
	

	public Product(Category category, String pName, double price, int avlQty) {
		this.category = category;
		this.pName = pName;
		this.price = price;
		this.avlQty = avlQty;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvlQty() {
		return avlQty;
	}

	public void setAvlQty(int avlQty) {
		this.avlQty = avlQty;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", pName=" + pName + ", price=" + price + ", avlQty="
				+ avlQty + "]";
	}
	
	
}
