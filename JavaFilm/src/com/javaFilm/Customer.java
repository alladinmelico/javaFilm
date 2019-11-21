package com.javaFilm;

public class Customer {
	private String name, address,city,province, username, password;
	private int zip,idCust;
	public Customer(int idCust,String name, String address, String city, String province, String username, String password,
			int zip) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.province = province;
		this.username = username;
		this.password = password;
		this.zip = zip;
		this.idCust = idCust;
	}
	protected int getIdCust() {
		return idCust;
	}
	protected void setIdCust(int idCust) {
		this.idCust = idCust;
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
		return password;
	}
	protected void setPassaword(String password) {
		this.password = password;
	}
	protected int getZip() {
		return zip;
	}
	protected void setZip(int zip) {
		this.zip = zip;
	}
}
