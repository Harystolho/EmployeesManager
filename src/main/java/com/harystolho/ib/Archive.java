package com.harystolho.ib;

import java.io.Serializable;
import java.time.LocalDate;

public class Archive implements Serializable {

	private Employee employee;
	private Document doc;
	private LocalDate expiryDate;
	private long id = System.currentTimeMillis();

	public Archive(Employee empl, Document doc, LocalDate localDate) {
		this.setEmployee(empl);
		this.setDoc(doc);
		this.setExpiryDate(localDate);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public long getId() {
		return id;
	}


	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return employee.getName() + " " + doc.getName();
	}
}
