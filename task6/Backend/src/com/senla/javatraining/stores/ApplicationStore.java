package com.senla.javatraining.stores;

import com.senla.javatraining.models.Application;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class ApplicationStore implements IApplicationStore {
	private static ApplicationStore instance;
	
	private ArrayList<Application> applications;

	/* Last generated id */
	private int lastInsertedId;
	
	public ApplicationStore() {
		
	}

	public static ApplicationStore getInstance() {
    	if (instance == null) {
            instance = new ApplicationStore();
        }
     
        return instance;
    }
	
	public void setApplications(ArrayList<Application> applications) {
		this.applications = applications;
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
}
