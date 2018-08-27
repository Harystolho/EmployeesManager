package com.harystolho;

public class Main {

	private static IBApplication app;

	public static void main(String[] args) {

		new IBApplication().init(args);

	}

	public static IBApplication getApp() {
		return app;
	}

	public static void setApp(IBApplication app) {
		Main.app = app;
	}

}
