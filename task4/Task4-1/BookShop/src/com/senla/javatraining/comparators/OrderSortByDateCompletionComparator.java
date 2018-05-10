package com.senla.javatraining.comparators;

import com.senla.javatraining.Order;
import java.util.Comparator;

public class OrderSortByDateCompletionComparator implements Comparator<Order> {
	
	@Override
	public int compare(Order order1, Order order2) {
		return Double.compare(order1.getDateCompletion().getTime(),
							  order2.getDateCompletion().getTime());
	}
}
