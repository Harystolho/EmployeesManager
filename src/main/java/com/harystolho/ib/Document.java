package com.harystolho.ib;

import java.io.Serializable;

public class Document implements Serializable {

	private String name;
	private long id = System.currentTimeMillis();

	public Document(String text) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
