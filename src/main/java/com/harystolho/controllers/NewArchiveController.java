package com.harystolho.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.harystolho.Main;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;
import com.harystolho.utils.IBDataConverter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class NewArchiveController {

	@FXML
	private ListView<Employee> empl;

	@FXML
	private ListView<Document> docs;

	@FXML
	private DatePicker date;

	@FXML
	private Button create;

	private Stage stage;

	@FXML
	void initialize() {
		empl.getItems().addAll(Main.getApp().getMainController().getEmployees());
		docs.getItems().addAll(Main.getApp().getMainController().getDocuments());

		date.setConverter(new IBDataConverter());

		eventHandlers();
	}

	private void eventHandlers() {
		create.setOnAction((e) -> {
			createEmployee();
		});
	}

	private void createEmployee() {
		Employee employee = empl.getSelectionModel().getSelectedItem();
		Document doc = docs.getSelectionModel().getSelectedItem();
		LocalDate expDate = date.getValue();

		if (employee != null && doc != null && expDate != null) {
			Archive arch = new Archive(employee, doc, expDate);

			stage.close();

			Main.getApp().getMainController().addArchive(arch);
			Main.getApp().getMainController().displayArchives();
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
