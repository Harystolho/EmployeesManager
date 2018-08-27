package com.harystolho;

import javafx.application.Application;
import javafx.stage.Stage;

public class IBApplication extends Application {

	@Override
	public void start(Stage window) throws Exception {
		Main.setApp(this);

		window.setTitle("Employees");
		window.setWidth(1200);
		window.setHeight(650);

		
		
		window.show();
	}

	public void init(String[] args) {
		launch(args);
	}

}
