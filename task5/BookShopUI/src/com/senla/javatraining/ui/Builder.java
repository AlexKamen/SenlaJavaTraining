package com.senla.javatraining.ui;

import com.senla.javatraining.ui.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Builder {
	private Menu rootMenu;

	Builder() {
		
	}
	
	public void buildMenu() {
		
		/* Create menu items with actions */
		MenuItem listBooks = new MenuItem("List books",new ListBooks());
		MenuItem listBooksRequests = new MenuItem("List books requests", new ListBooksRequests());
		MenuItem listOldBooks = new MenuItem("List old books", new ListOldBooks());
		MenuItem bookDescription = new MenuItem("Show book description", new BookDescription());
		MenuItem addBookToStock = new MenuItem("Add book to stock", new AddBookToStock());
		MenuItem deleteBookFromStock = new MenuItem("Delete book from stock", new DeleteBookFromStock());
		MenuItem addBook = new MenuItem("Add book", new AddBook());
		MenuItem listOrders = new MenuItem("List orders", new ListOrders());
		MenuItem listCompleteOrders = new MenuItem("List complete orders", new ListCompleteOrders());
		MenuItem orderDetails = new MenuItem("Show order details", new OrderDetails());
		MenuItem earnedMoney = new MenuItem("Show earned money", new EarnedMoney());
		MenuItem countOfCompleteOrders = new MenuItem("Show count of complete orders", new CountOfCompleteOrders());
		MenuItem addOrder = new MenuItem("Add order", new AddOrder());
		MenuItem cancelOrder = new MenuItem("Cancel order", new CancelOrder());
		MenuItem checkOrdersCompletion = new MenuItem("Check orders completion", new CheckOrdersCompletion());
		MenuItem checkApplicationsCompletion = new MenuItem("Check applications completion", new CheckApplicationsCompletion());
		
		
		/* UniteMenuItems */
		ArrayList<MenuItem> bookMenuItems = new ArrayList<MenuItem>();
		bookMenuItems.addAll(Arrays.asList(listBooks, listBooksRequests, listOldBooks,
				bookDescription, addBookToStock, deleteBookFromStock, addBook));
		ArrayList<MenuItem> orderMenuItems = new ArrayList<MenuItem>();
		orderMenuItems.addAll(Arrays.asList(listOrders, listCompleteOrders, orderDetails,
				earnedMoney, countOfCompleteOrders, addOrder, cancelOrder,
				checkOrdersCompletion));
		ArrayList<MenuItem> applicationMenuItems = new ArrayList<MenuItem>();
		applicationMenuItems.addAll(Arrays.asList(checkApplicationsCompletion));
		
		/* Create Book, Order and Application menu */
		MenuItem book = new MenuItem("Book", new Menu("Book",
				bookMenuItems.toArray(new MenuItem[bookMenuItems.size()])));
		MenuItem order = new MenuItem("Order", new Menu("Order",
				orderMenuItems.toArray(new MenuItem[orderMenuItems.size()])));
		MenuItem application = new MenuItem("Application", new Menu("Application",
				applicationMenuItems.toArray(new MenuItem[applicationMenuItems.size()])));
		
		/* Create root menu */
		ArrayList<MenuItem> rootMenuItems = new ArrayList<MenuItem>();
		rootMenuItems.addAll(Arrays.asList(book, order, application));
		
		Menu rootMenu = new Menu("Root",
				rootMenuItems.toArray(new MenuItem[rootMenuItems.size()]));
		
		this.rootMenu = rootMenu;
	}

	public Menu getRootMenu() {
		return this.rootMenu;
	}

}
