package com.javaFilm;

public class Product {
	private String desc,finish;
	private int price,onHand;
	
	public Product(String desc, String finish, int price, int onHand) {
		super();
		this.desc = desc;
		this.finish = finish;
		this.price = price;
		this.onHand = onHand;
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
	
}
