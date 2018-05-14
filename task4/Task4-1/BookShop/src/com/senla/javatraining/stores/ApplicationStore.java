package com.senla.javatraining.stores;

import com.senla.javatraining.models.Application;
import com.senla.training.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class ApplicationStore implements IApplicationStore {
	private static final String APPLICATION_FILE = "applications.txt";
	
	private Application[] applications;
	
	/* Last generated id */
	private int lastInsertedId;
	private final FileWorker fileWorker;
	
	public ApplicationStore() {
		this.fileWorker = new TextFileWorker(APPLICATION_FILE);
	}
	
	@Override
	public void addApplication(Application application) {

		/* Fill Application object fields */
		application.setId(this.generateId());
		application.setDateCreation(new Date());
		
		/* Some manipulations for add new book to existance */
		Application[] applications = this.getAllApplications();
		ArrayList<Application> result = new ArrayList<Application>();
		if (!(applications == null || applications.length == 0)) {
			result = new ArrayList(Arrays.asList(applications));
		}

		result.add(application);
		this.writeToFile(result.toArray(new Application[result.size()]));

	}

	@Override
	public void updateApplication(Application application) {
		Application[] applications = this.getAllApplications();
		for (int i = 0; i < applications.length; i++) {
			if (applications[i].getId() == application.getId()) {
				applications[i] = application;
			}
		}

		this.writeToFile(applications);
	}

	@Override
	public void deleteApplication(Application application) {
		Application[] applications = this.getAllApplications();
		ArrayList<Application> result = new ArrayList<Application>(Arrays.asList(applications));
		for (int i = 0; i < applications.length; i++) {
			if (applications[i].getId() == application.getId()) {
				result.remove(i);
			}
		}

		this.writeToFile(result.toArray(new Application[result.size()]));
	}

	@Override
	public Application[] getAllApplications() {
		Application[] applications = this.readFromFile();
		return applications;
	}

	@Override
	public Application getApplication(int id) {
		Application[] applications = this.getAllApplications();
		for (int i = 0; i < applications.length; i++) {
			if (applications[i].getId() == id) {
				return applications[i];
			}
		}

		return null;
	}
	
	private int generateId() {
		this.lastInsertedId = this.getLastId() + 1;
		return this.lastInsertedId;
	}

	private int getLastId() {
		if (this.lastInsertedId != 0) {
			return this.lastInsertedId;
		}
		else {
			Application[] applications = this.getAllApplications();
			if (applications == null || applications.length == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < applications.length; i++) {
					if (applications[i].getId() > maxId) {
						maxId = applications[i].getId();
					}
				}
				return maxId;
			}
		}
		
	}

	private Application[] readFromFile() {
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			return null;
		}
		
		final Application[] result = new Application[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			result[i] = this.parseFileLine(lines[i]);
		}
		
		return result;
	}

	private void writeToFile(final Application[] values) {
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException();
		}
		final String[] lines = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			lines[i] = this.collectFileLine(values[i]);
		}
		fileWorker.writeToFile(lines);
	}

	private String collectFileLine(final Application application) {
		if (application == null) {
			throw new IllegalArgumentException();
		}

		final String[] objectFields = new String[] {
				String.valueOf(application.getId()),
				String.valueOf(application.getBook().getId()),
				String.valueOf(application.getCountExemplar()),
				String.valueOf(application.getDateCreation().getTime()),
				String.valueOf(application.getDateCompletion().getTime()),
				String.valueOf(application.getStatus()),
				
			};
		return String.join(";", objectFields);
	}
	
	private Application parseFileLine(final String line) {
		if (line == null || line.isEmpty()) {
			throw new IllegalArgumentException();
		}

		final String[] parts = line.split(";");

		BookStore bookStore = new BookStore();
		
		final Application application = new Application(
				bookStore.getBook(Integer.valueOf(parts[1])),
				Integer.valueOf(parts[2]),
				new Date(Long.valueOf(parts[4])),
				Integer.valueOf(parts[5]));
		application.setId(Integer.valueOf(parts[0]));
		application.setDateCreation(new Date(Long.valueOf(parts[3])));
		
		return application;
	}

}
