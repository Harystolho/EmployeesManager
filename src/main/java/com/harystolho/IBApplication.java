package com.harystolho;

import java.util.function.Consumer;

import com.harystolho.controllers.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IBApplication extends Application {

	private MainController mainController;

	@Override
	public void start(Stage window) throws Exception {
		Main.setApp(this);

		window.setTitle("Employees");

		Scene scene = new Scene(loadFXML("main.fxml", (c) -> {
			mainController = (MainController) c;
		}));

		window.setScene(scene);
		window.show();

		window.setOnCloseRequest((e) -> {
			mainController.save();
		});
	}

	public static Parent loadFXML(String name, Consumer<Object> controller) {
		Parent p = null;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource(name));
			p = loader.load();
			controller.accept(loader.getController());
		} catch (Exception e) {
			System.out.println("Error reading fxml file.");
		}

		return p;
	}

	public MainController getMainController() {
		return mainController;
	}

	public void init(String[] args) {
		launch(args);
	}

}
