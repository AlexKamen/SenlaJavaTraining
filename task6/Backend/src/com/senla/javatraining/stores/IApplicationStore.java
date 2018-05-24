package com.senla.javatraining.stores;

import java.util.ArrayList;

import com.senla.javatraining.models.Application;

public interface IApplicationStore {
	public void setApplications(ArrayList<Application> applications);
	
	public void addApplication(Application application);
	
	public void updateApplication(Application application);

	public void deleteApplication(Application application);
	
	public ArrayList<Application> getAllApplications();

	public Application getApplication(int id);
}