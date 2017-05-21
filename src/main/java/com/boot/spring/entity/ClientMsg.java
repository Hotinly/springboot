package com.boot.spring.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * 信息实体封装类
 */
public class ClientMsg {

	private String msg_from;
	private String msg_to;
	private String msg_content;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss:SSS")
	private Date msg_date;

	public String getMsg_from() {
		return msg_from;
	}
	public void setMsg_from(String msg_from) {
		this.msg_from = msg_from;
	}
	public String getMsg_to() {
		return msg_to;
	}
	public void setMsg_to(String msg_to) {
		this.msg_to = msg_to;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Date getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
}
