package com.boot.spring.entity;

public class PostEntity {

	private String number;
	private int quantity;
	private String date;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PostEntity [number=" + number + ", quantity=" + quantity + ", date=" + date + "]";
	}

}
