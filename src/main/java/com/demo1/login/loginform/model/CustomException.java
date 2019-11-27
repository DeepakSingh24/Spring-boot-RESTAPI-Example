package com.demo1.login.loginform.model;

public class CustomException {
	
	private String name;
	private Message message;
	private  String details;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	

}
