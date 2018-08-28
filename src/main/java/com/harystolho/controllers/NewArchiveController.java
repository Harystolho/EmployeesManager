package com.harystolho.controllers;

import com.harystolho.Main;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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

		eventHandlers();
	}

	private void eventHandlers() {
		create.setOnAction((e) -> {
			createEmployee();
		});
	}

	private void createEmployee() {
		if (empl.getSelectionModel().getSelectedItem() != null && docs.getSelectionModel().getSelectedItem() != null) {

			Archive arch = new Archive(empl.getSelectionModel().getSelectedItem(),
					docs.getSelectionModel().getSelectedItem(), date.getValue());

			Main.getApp().getMainController().addArchive(arch);
			Main.getApp().getMainController().displayArchives();
		}

		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				createEmployee();
			}
		});

	}

}
