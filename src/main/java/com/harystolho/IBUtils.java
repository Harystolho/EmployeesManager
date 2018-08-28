package com.harystolho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import com.harystolho.ib.Archive;
import com.harystolho.ib.Document;
import com.harystolho.ib.Employee;

import javafx.scene.shape.Arc;

public class IBUtils {

	private static final File folder = new File("storage");
	private static File employeesFile;
	private static File documentsFile;
	private static File archivesFile;

	public static void init() {
		if (!folder.exists()) {
			folder.mkdir();
		}

		employeesFile = new File(folder, "employees.txt");
		documentsFile = new File(folder, "docs.txt");
		archivesFile = new File(folder, "archives.txt");

	}

	@SuppressWarnings("unchecked")
	public static List<Employee> loadEmployeesFromFile() {
		if (employeesFile.exists()) {
			List<Employee> empl;

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(employeesFile))) {
				System.out.println("-- Loading Employees --");
				empl = (List<Employee>) ois.readObject();
				return empl;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return new ArrayList<>();
	}

	public static void saveEmployeesToFile(List<Employee> list) {
		if (!employeesFile.exists()) {
			try {
				employeesFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(employeesFile))) {
			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static List<Document> loadDocumentsFromFile() {
		if (documentsFile.exists()) {
			List<Document> docs;

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(documentsFile))) {
				System.out.println("-- Loading Documents --");
				docs = (List<Document>) ois.readObject();
				return docs;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return new ArrayList<>();
	}

	public static void saveDocumentsToFile(List<Document> documents) {
		if (!documentsFile.exists()) {
			try {
				documentsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(documentsFile))) {
			oos.writeObject(documents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Archive> loadArchivesFromFile() {
		if (archivesFile.exists()) {
			List<Archive> archs;

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivesFile))) {
				System.out.println("-- Loading Documents --");
				archs = (List<Archive>) ois.readObject();
				return archs;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return new ArrayList<>();
	}

	public static void saveArchivesToFile(List<Archive> archives) {
		if (!archivesFile.exists()) {
			try {
				archivesFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivesFile))) {
			oos.writeObject(archives);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
