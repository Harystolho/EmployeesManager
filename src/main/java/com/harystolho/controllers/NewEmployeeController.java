package com.harystolho.controllers;

import com.harystolho.Main;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NewEmployeeController {

	@FXML
	private TextField name;

	@FXML
	private Button add;

	@FXML
	private ListView<Employee> list;

	private Stage stage;

	@FXML
	void initialize() {
		list.getItems().addAll(Main.getApp().getMainController().getEmployees());

		eventHandlers();
	}

	private void eventHandlers() {
		add.setOnAction((e) -> {
			addEmployee();
		});
	}

	private void addEmployee() {
		if (!name.getText().isEmpty()) {
			Employee empl = new Employee(name.getText());
			Main.getApp().getMainController().addEmployee(empl);
		}

		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				addEmployee();
			}
		});

	}

}
