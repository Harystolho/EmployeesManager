package com.harystolho.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

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

public class ShowArchiveController {

	@FXML
	private TextField emplName;

	@FXML
	private Button remove;

	@FXML
	private Button save;

	@FXML
	private TextField docName;

	@FXML
	private DatePicker date;

	@FXML
	private Button plusOneYear;

	@FXML
	private Button plusOneMonth;

	private Stage stage;

	private Archive archive;

	@FXML
	void initialize() {
		eventHandlers();
	}

	private void eventHandlers() {

		remove.setOnAction((e) -> {
			removeArchive();
		});

		save.setOnAction((e) -> {
			saveArchive();
		});

		plusOneMonth.setOnAction((e) -> {
			increaseExpiryDate(30);
		});

		plusOneYear.setOnAction((e) -> {
			increaseExpiryDate(365);
		});

	}

	private void increaseExpiryDate(int days) {
		LocalDate newDate = date.getValue().plus(days, ChronoUnit.DAYS);
		date.setValue(newDate);
	}

	private void removeArchive() {
		stage.close();
		Main.getApp().getMainController().removeArchive(archive);
		Main.getApp().getMainController().displayArchives();
	}

	private void saveArchive() {
		stage.close();
		archive.setExpiryDate(date.getValue());
		Main.getApp().getMainController().displayArchives();
	}

	public void show() {
		emplName.setText(archive.getEmployee().getName());
		docName.setText(archive.getDoc().getName());
		date.setValue(archive.getExpiryDate());
	}

	public void setStage(Stage stage) {
		this.stage = stage;

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				saveArchive();
			}
		});

	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

}
