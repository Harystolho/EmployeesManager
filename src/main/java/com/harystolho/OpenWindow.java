package com.harystolho;

import java.util.function.Consumer;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenWindow {

	private Stage stage;
	private Parent parent;
	private Scene scene;

	public OpenWindow(String title) {
		stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);

		stage.setTitle(title);
	}

	public void load(String fxmlName, Consumer<Object> controller) {
		parent = IBApplication.loadFXML(fxmlName, controller);

		scene = new Scene(parent);

		scene.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				stage.close();
			}
		});

		stage.setScene(scene);
	}

	public void openWindow() {
		stage.show();

	}

	public Stage getStage() {
		return stage;
	}

}
