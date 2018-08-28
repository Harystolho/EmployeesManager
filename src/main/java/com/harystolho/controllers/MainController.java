package com.harystolho.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harystolho.IBUtils;
import com.harystolho.OpenWindow;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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
		archives = IBUtils.loadArchivesFromFile();
		displayArchives();
	}

	public void save() {
		IBUtils.saveEmployeesToFile(employees);
		IBUtils.saveDocumentsToFile(documents);
		IBUtils.saveArchivesToFile(archives);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee empl) {
		employees.add(empl);
	}

	public void removeEmployee(Employee empl) {
		employees.remove(empl);
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void addDocument(Document doc) {
		documents.add(doc);
	}

	public void removeDocument(Document doc) {
		documents.remove(doc);
	}

	public void addArchive(Archive arch) {
		archives.add(arch);
	}

	public List<Archive> getArchives() {
		return archives;
	}

	public void removeArchive(Archive arch) {
		archives.remove(arch);
	}

	public void displayArchives() {
		employeeFlow.getChildren().clear();

		archives.sort((o1, o2) -> {
			return o1.toString().compareTo(o2.toString());
		});

		for (Archive arc : archives) {
			Button archiveButton = createArchiveButton(arc);

			archiveButton.setEffect(new DropShadow(2, 0, 1, Color.BLACK));
			archiveButton.setStyle("-fx-background-color: #fff;");
			FlowPane.setMargin(archiveButton, new Insets(12, 7, 10, 7));

			employeeFlow.getChildren().add(archiveButton);
		}

	}

	private Button createArchiveButton(Archive arc) {
		Button b = new Button(arc.toString());

		b.setOnAction((e) -> {
			OpenWindow ow = new OpenWindow(arc.toString());
			ow.load("showArchive.fxml", (c) -> {
				ShowArchiveController sac = ((ShowArchiveController) c);
				sac.setStage(ow.getStage());
				sac.setArchive(arc);
				sac.show();
			});
			ow.openWindow();
		});

		b.setTextFill(getButtonColor(arc.getExpiryDate()));

		return b;
	}

	private Paint getButtonColor(LocalDate expiryDate) {
		if (expiryDate.isBefore(LocalDate.now())) {
			return Color.RED;
		} else if (expiryDate.isBefore(LocalDate.now().plus(30, ChronoUnit.DAYS))) {
			return Color.web("#998100");
		} else {
			return Color.GREEN;
		}
	}

}
