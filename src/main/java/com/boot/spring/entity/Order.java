package com.boot.spring.entity;

import java.util.Date;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

	private String id;
	@JsonProperty("order_no")
	private String no;
//	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date date;
	private int quantity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
