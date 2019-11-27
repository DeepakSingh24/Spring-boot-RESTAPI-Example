package com.demo1.login.loginform.model;

import java.util.List;

public class Employee {
	private List<Accounting> accounting;
	private List<Sales> sales;
	public List<Accounting> getAccounting() {
		return accounting;
	}
	public void setAccounting(List<Accounting> accounting) {
		this.accounting = accounting;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	

}
