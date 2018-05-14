package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Order;

public class OrderSortByStatusComparator implements Comparator<Order> {
	
	@Override
	public int compare(Order order1, Order order2) {
		return Integer.compare(order1.getStatus(), order2.getStatus());
	}
}
