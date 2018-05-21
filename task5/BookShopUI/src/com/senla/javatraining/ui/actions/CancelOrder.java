package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.ui.Scan;

public class CancelOrder implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public CancelOrder() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
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

		this.bookShop.cancelTheOrder(this.bookShop.getOrder(orderId));
	}

}
