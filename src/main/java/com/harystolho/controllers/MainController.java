package com.harystolho.controllers;

import java.util.ArrayList;
import java.util.List;

import com.harystolho.IBUtils;
import com.harystolho.OpenWindow;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class MainController {

	@FXML
	private Button newEmployee;

	@FXML
	private Button newDocument;

	@FXML
	private Button createArchive;

	@FXML
	private FlowPane employeeFlow;

	private List<Employee> employees;
	private List<Document> documents;
	private List<Archive> archives;

	@FXML
	void initialize() {
		eventHandlers();

		employees = new ArrayList<>();
		documents = new ArrayList<>();
		archives = new ArrayList<>();

		loadEmployees();
		loadDocuments();
		loadArchives();
	}

	private void eventHandlers() {
		newEmployee.setOnAction((e) -> {
			OpenWindow ow = new OpenWindow("Novo Funcionario");
			ow.load("newEmployee.fxml", (c) -> {
				((NewEmployeeController) c).setStage(ow.getStage());
			});

			ow.openWindow();
		});

		newDocument.setOnAction((e) -> {
			OpenWindow ow = new OpenWindow("Novo Documento");
			ow.load("newDocument.fxml", (c) -> {
				((NewDocumentController) c).setStage(ow.getStage());
			});

			ow.openWindow();
		});

		createArchive.setOnAction((e) -> {

		});
	}

	private void loadEmployees() {
		employees = IBUtils.loadEmployeesFromFile();
	}

	private void loadDocuments() {
		documents = IBUtils.loadDocumentsFromFile();
	}

	private void loadArchives() {

	}

	public void save() {
		IBUtils.saveEmployeesToFile(employees);
		IBUtils.saveDocumentsToFile(documents);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee empl) {
		employees.add(empl);
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void addDocument(Document doc) {
		documents.add(doc);
	}
}
