package com.edu.seiryo.entity;

public class GoodType {
	private int id;
	private String name;
	public GoodType() {
		super();
	}
	public GoodType(String name) {
		super();
		this.name = name;
	}
	public GoodType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "GoodType [id=" + id + ", name=" + name + "]";
	}
}
