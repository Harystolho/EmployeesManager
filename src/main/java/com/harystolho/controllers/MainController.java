package com.harystolho.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.harystolho.IBUtils;
import com.harystolho.OpenWindow;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
			OpenWindow ow = new OpenWindow("Criar Arquivo");
			ow.load("newArchive.fxml", (c) -> {
				((NewArchiveController) c).setStage(ow.getStage());
			});

			ow.openWindow();
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

	public void addArchive(Archive arch) {
		archives.add(arch);
	}

	public List<Archive> getArchives() {
		return archives;
	}

	public void displayArchives() {
		employeeFlow.getChildren().clear();

		for (Archive arc : archives) {
			Button archiveButton = createArchiveButton(arc);
			employeeFlow.getChildren().add(archiveButton);
		}

	}

	private Button createArchiveButton(Archive arc) {
		Button b = new Button(arc.toString());

		b.setTextFill(getButtonColor(arc.getExpiryDate()));

		return b;
	}

	private Paint getButtonColor(LocalDate expiryDate) {
		if (expiryDate.isBefore(LocalDate.now())) {
			return Color.RED;
		} else if (expiryDate.isBefore(LocalDate.now().plus(30, ChronoUnit.DAYS))) {
			return Color.YELLOW;
		} else {
			return Color.GREEN;
		}
	}

}
