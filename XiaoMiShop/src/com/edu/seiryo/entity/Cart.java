package com.edu.seiryo.entity;

public class Cart {
	private int id;
	//商品id
	private int pId;
	//商品数量
	private int num;
	//小计（数量*单价）
	private double money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
}
