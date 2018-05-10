package com.senla.javatraining;

import java.util.ArrayList;
import java.util.Date;

public interface IBookShop {
	public void addBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book);
	
	public ArrayList<Book> getBooks(int orderby);
	
	public Book getBook(int id);
	
	public ArrayList<Book> getOldBooks(int orderby);

	public void addBookToStock(Book book, int countExemplars);

	public void deleteBookFromStock(Book book);

	public ArrayList<Request> getBooksRequests(int orderby);

	public void addOrderWithCheckBookExistance(Order order);

	public void updateOrder(Order order);

	public void deleteOrder(Order order);

	public Order getOrder(int id);
	
	public void completeTheOrder(Order order);
	
	public void cancelTheOrder(Order order);
	
	public ArrayList<Order> getOrders(Date dateStart, Date dateEnd, int orderby, boolean completeOnly);
	
	public double getTotalOrdersPrice(Date dateStart, Date dateEnd);
	
	public int getCompleteOrdersCount(Date dateStart, Date dateEnd);

	public void addApplication(Application application);
	
	public void updateApplication(Application application);

	public void deleteApplication(Application application);
	
	public ArrayList<Application> getApplications(boolean actualOnly);

	public Application getApplication(int id);

	public void completeTheApplication(Application application);
}