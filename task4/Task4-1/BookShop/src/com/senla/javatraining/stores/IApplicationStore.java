package com.senla.javatraining.stores;

import com.senla.javatraining.models.Application;

public interface IApplicationStore {
	public void addApplication(Application application);
	
	public void updateApplication(Application application);

	public void deleteApplication(Application application);
	
	public Application[] getAllApplications();

	public Application getApplication(int id);
}