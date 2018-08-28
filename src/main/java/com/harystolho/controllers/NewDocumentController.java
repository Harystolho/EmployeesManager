package com.harystolho.controllers;

import com.harystolho.Main;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NewDocumentController {

	@FXML
	private TextField name;

	@FXML
	private Button add;

	@FXML
	private ListView<Document> list;

	private Stage stage;

	@FXML
	void initialize() {
		list.getItems().addAll(Main.getApp().getMainController().getDocuments());

		eventHandlers();
	}

	private void eventHandlers() {
		add.setOnAction((e) -> {
			addDocument();
		});
	}

	private void addDocument() {
		if (!name.getText().isEmpty()) {
			Document doc = new Document(name.getText());
			Main.getApp().getMainController().addDocument(doc);
		}

		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				addDocument();
			}
		});

	}

}
