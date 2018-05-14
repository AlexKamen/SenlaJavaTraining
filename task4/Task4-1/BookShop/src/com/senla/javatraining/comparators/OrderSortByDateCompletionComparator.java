package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Order;

public class OrderSortByDateCompletionComparator implements Comparator<Order> {
	
	@Override
	public int compare(Order order1, Order order2) {
		return Double.compare(order1.getDateCompletion().getTime(),
							  order2.getDateCompletion().getTime());
	}
}
