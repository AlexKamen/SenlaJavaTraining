package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Order;

public class OrderSortByTotalPriceComparator implements Comparator<Order> {
	
	@Override
	public int compare(Order order1, Order order2) {
		return Double.compare(order1.getTotalPrice(), order2.getTotalPrice());
	}
}
