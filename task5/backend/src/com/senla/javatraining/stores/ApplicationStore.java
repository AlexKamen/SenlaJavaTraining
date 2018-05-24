package com.senla.javatraining.stores;

import com.senla.javatraining.models.Application;
import com.senla.training.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class ApplicationStore implements IApplicationStore {
	private static final String APPLICATION_FILE = "applications.txt";
	private static ApplicationStore instance;
	
	private ArrayList<Application> applications;
	
	/* Last generated id */
	private int lastInsertedId;
	private final FileWorker fileWorker;
	
	public ApplicationStore() {
		this.fileWorker = new TextFileWorker(APPLICATION_FILE);
		this.applications = new ArrayList<Application>(Arrays.asList(this.readFromFile()));
	}

	public static ApplicationStore getInstance() {
    	if (instance == null) {
            instance = new ApplicationStore();
        }
     
        return instance;
    }
	
	@Override
	public void addApplication(Application application) {

		/* Fill Application object fields */
		application.setId(this.generateId());
		application.setDateCreation(new Date());
		
		ArrayList<Application> applications = this.getAllApplications();

		applications.add(application);

	}

	@Override
	public void updateApplication(Application application) {
		ArrayList<Application> applications = this.getAllApplications();
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == application.getId()) {
				applications.set(i, application);
			}
		}
	}

	@Override
	public void deleteApplication(Application application) {
		ArrayList<Application> applications = this.getAllApplications();
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == application.getId()) {
				applications.remove(i);
			}
		}
	}

	@Override
	public ArrayList<Application> getAllApplications() {
		return this.applications;
	}

	@Override
	public Application getApplication(int id) {
		ArrayList<Application> applications = this.getAllApplications();
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				return applications.get(i);
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
			ArrayList<Application> applications = this.getAllApplications();
			if (applications == null || applications.size() == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < applications.size(); i++) {
					if (applications.get(i).getId() > maxId) {
						maxId = applications.get(i).getId();
					}
				}
				return maxId;
			}
		}
		
	}

	public Application[] readFromFile() {
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

	public void writeToFile(final Application[] values) {
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
