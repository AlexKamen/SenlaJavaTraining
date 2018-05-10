package com.senla.javatraining.comparators;

import com.senla.javatraining.Order;
import java.util.Comparator;

public class OrderSortByTotalPriceComparator implements Comparator<Order> {
	
	@Override
	public int compare(Order order1, Order order2) {
		return Double.compare(order1.getTotalPrice(), order2.getTotalPrice());
	}
}
