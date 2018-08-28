package com.harystolho.ib;

import java.io.Serializable;

public class Employee implements Serializable {

	private String name;
	private long id = System.currentTimeMillis();

	public Employee(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Employee) {
			return this.id == ((Employee) obj).getId();	
		}
		return false;
	}

}
