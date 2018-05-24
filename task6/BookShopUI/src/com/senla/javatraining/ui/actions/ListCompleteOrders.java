package com.senla.javatraining.ui.actions;

import java.util.ArrayList;
import java.util.Date;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.ui.Scan;

public class ListCompleteOrders implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public ListCompleteOrders() {
		this.scanner = Scan.getInstance();
		this.bookShop = BookShop.getInstance();
	}
	
	@Override
	public void execute() {
		Date dateStart = null;
		Date dateEnd = null;
		
		System.out.println("Enter period start date");
		dateStart = this.scanner.getDateValue();
		
		if (dateStart != null) {
			System.out.println("Enter period end date");
			dateEnd = this.scanner.getDateValue();
		}

		System.out.println("Select order type");
		System.out.println(BookShop.ORDERBY_DATE_COMPLETION +" - order by date");
		System.out.println(BookShop.ORDERBY_TOTAL_PRICE +" - order by price");
		
		int orderby = this.scanner.getIntValue();

		ArrayList<Order> orders = this.bookShop.getOrders(dateStart, dateEnd, orderby, true);
		
		for (Order order : orders) {
			System.out.print(order.getId() + " - ");
			for (OrderItem item : order.getItems()) {
				System.out.print(item.getBook().getTitle() + ", ");
			}
			System.out.println();
		}		

	}

}
