package com.javaFilm;

public class Customer {
	private String name, address,city,province, username, passaword;
	private int zip;
	public Customer(String name, String address, String city, String province, String username, String passaword,
			int zip) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.province = province;
		this.username = username;
		this.passaword = passaword;
		this.zip = zip;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getAddress() {
		return address;
	}
	protected void setAddress(String address) {
		this.address = address;
	}
	protected String getCity() {
		return city;
	}
	protected void setCity(String city) {
		this.city = city;
	}
	protected String getProvince() {
		return province;
	}
	protected void setProvince(String province) {
		this.province = province;
	}
	protected String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	protected String getPassaword() {
		return passaword;
	}
	protected void setPassaword(String passaword) {
		this.passaword = passaword;
	}
	protected int getZip() {
		return zip;
	}
	protected void setZip(int zip) {
		this.zip = zip;
	}
	
	
}
