package com.senla.javatraining.stores;

import java.io.Serializable;
import java.util.ArrayList;

import com.senla.javatraining.models.Application;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.models.Order;

public class DtoStores implements Serializable{
	private static final long serialVersionUID = -2930204883284707031L;

	public static transient String STORE_FILE = "store.ser";
	
	private ArrayList<Book> books;
	private ArrayList<Order> orders;
	private ArrayList<Application> applications;
	
	public DtoStores(ArrayList<Book> books,
			ArrayList<Order> orders,
			ArrayList<Application> applications) {
		 this.books = books;
		 this.orders = orders;
		 this.applications = applications;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Application> getApplications() {
		return applications;
	}

	public void setApplications(ArrayList<Application> applications) {
		this.applications = applications;
	}
}
