package com.javaFilm;

import java.util.ArrayList;

public class Product {
	private String desc,finish;
	private int price,onHand,qnty,id;
	

	private ArrayList<Product> cart;
	
	public Product(int id, String desc, String finish, int price, int onHand, int qnty) {
		super();
		this.desc = desc;
		this.finish = finish;
		this.price = price;
		this.onHand = onHand;
		this.qnty = qnty;
		this.id = id;
	}
	
	protected int getId() {
		return id;
	}

	protected int getTotal() {
		return price * qnty;
	}
	protected void setId(int id) {
		this.id = id;
	}

	public Product() {
		cart = new ArrayList<Product>();
	}

	public void addToCart(Product prod) {
		cart.add(prod);
	}
	
	public void deleteFromCart(int toDel) {
		for (Product prod : cart) {
			if(prod.getId() == toDel) {
				cart.remove(prod);
			}
		}
	}
	
	public void updateCart(int toUpdate, Product prod) {
		deleteFromCart(toUpdate);
		addToCart(prod);
	}
	protected String getDesc() {
		return desc;
	}

	protected void setDesc(String desc) {
		this.desc = desc;
	}

	protected String getFinish() {
		return finish;
	}

	protected void setFinish(String finish) {
		this.finish = finish;
	}

	protected int getPrice() {
		return price;
	}

	protected void setPrice(int price) {
		this.price = price;
	}

	protected int getOnHand() {
		return onHand;
	}

	protected void setOnHand(int onHand) {
		this.onHand = onHand;
	}
	
	protected int getQnty() {
		return qnty;
	}

	protected void setQnty(int qnty) {
		this.qnty = qnty;
	}

	protected ArrayList<Product> getCart() {
		return cart;
	}

	protected void setCart(ArrayList<Product> cart) {
		this.cart = cart;
	}
	
}
