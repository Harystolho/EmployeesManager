package com.harystolho.controllers;

import java.util.Iterator;

import com.harystolho.Main;
import com.harystolho.ib.Archive;
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
	private Button remove;

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

		remove.setOnAction((e) -> {
			removeEmployee();
		});

	}

	private void addEmployee() {
		if (!name.getText().isEmpty()) {
			Employee empl = new Employee(name.getText());
			Main.getApp().getMainController().addEmployee(empl);
		}

		stage.close();
	}

	private void removeEmployee() {
		Employee empl = list.getSelectionModel().getSelectedItem();

		if (empl != null) {
			list.getItems().remove(empl);
			Main.getApp().getMainController().removeEmployee(empl);

			// Removes archives that belong to this employee
			Iterator<Archive> it = Main.getApp().getMainController().getArchives().iterator();
			while (it.hasNext()) {
				if (it.next().getEmployee() == empl) {
					it.remove();
				}
			}

			empl.setName("REMOVE EMPL");
			Main.getApp().getMainController().displayArchives();
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
