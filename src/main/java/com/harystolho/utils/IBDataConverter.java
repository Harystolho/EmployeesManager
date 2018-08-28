package com.harystolho.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class IBDataConverter extends StringConverter<LocalDate> {
	String pattern = "dd/MM/yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

	@Override
	public LocalDate fromString(String string) {
		if (string != null && !string.isEmpty()) {
			return LocalDate.parse(string, dateFormatter);
		} else {
			return null;
		}
	}

	@Override
	public String toString(LocalDate object) {
		if (object != null) {
			return dateFormatter.format(object);
		} else {
			return "";
		}

	}

}
