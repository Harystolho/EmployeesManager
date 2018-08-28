package com.harystolho.controllers;

import java.util.Iterator;

import com.harystolho.Main;
import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;

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
	private Button remove;

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

		remove.setOnAction((e) -> {
			removeDocument();
		});
	}

	private void addDocument() {
		if (!name.getText().isEmpty()) {
			Document doc = new Document(name.getText());
			Main.getApp().getMainController().addDocument(doc);
		}

		stage.close();
	}

	private void removeDocument() {
		Document doc = list.getSelectionModel().getSelectedItem();

		if (doc != null) {
			list.getItems().remove(doc);
			Main.getApp().getMainController().removeDocument(doc);

			// Removes archives that belong to this employee
			Iterator<Archive> it = Main.getApp().getMainController().getArchives().iterator();
			while (it.hasNext()) {
				if (it.next().getDoc() == doc) {
					it.remove();
				}
			}

			doc.setName("REMOVE DOC");
			Main.getApp().getMainController().displayArchives();
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
