package com.senla.javatraining.stores;

import com.senla.javatraining.models.Order;

public interface IOrderStore {
	public void addOrder(Order order);

	public void updateOrder(Order order);

	public void deleteOrder(Order order);

	public Order[] getAllOrders();

	public Order getOrder(int id);
}
