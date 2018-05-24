package com.senla.javatraining.ui.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.ui.Scan;

public class OrderDetails implements IAction {
	public static final Logger logger = Logger.getLogger(OrderDetails.class.getName());
	
	private Scan scanner;
	private IBookShop bookShop;
	
	public OrderDetails() {
		this.scanner = Scan.getInstance();
		this.bookShop = BookShop.getInstance();
	}
	
	@Override
	public void execute() {
		System.out.println("Select order (enter the ID)");
		ArrayList<Order> orders = this.bookShop.getOrders(null, null, BookShop.ORDERBY_STATUS, false);
		
		for (Order order : orders) {
			System.out.print(order.getId() + " - ");
			for (OrderItem item : order.getItems()) {
				System.out.print(item.getBook().getTitle() + ", ");
			}
			System.out.println();
		}
		
		int orderId = this.scanner.getIntValue();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			Order order = this.bookShop.getOrder(orderId);
			System.out.println("Order #" + orderId + " details");
			System.out.println("Creation date: " + formatter.format(order.getDateCreation()));
			System.out.println("Completion date: " + formatter.format(order.getDateCompletion()));
			System.out.println("Total price: " + order.getTotalPrice());
			System.out.println("Items:");
			for (OrderItem item : order.getItems()) {
				System.out.println("   - " + item.getBook().getAuthor() + ". "
								 + item.getBook().getTitle() + ". Count: " + item.getCount());
			}
		} catch(NullPointerException e) {
			System.out.println("Order not found");
			logger.error("User entered incorrect order number", e);
		}
	}

}
