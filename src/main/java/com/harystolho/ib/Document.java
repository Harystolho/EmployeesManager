package com.harystolho.ib;

import java.io.Serializable;

public class Document implements Serializable {

	private String name;
	private long id = System.currentTimeMillis();

	public Document(String text) {
		this.name = text;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Document) {
			return this.id == ((Document) obj).getId();
		}
		return false;
	}

	public long getId() {
		return id;
	}

}
