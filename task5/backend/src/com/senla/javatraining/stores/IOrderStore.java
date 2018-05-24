package com.senla.javatraining.stores;

import java.util.ArrayList;

import com.senla.javatraining.models.Order;

public interface IOrderStore {
	public void addOrder(Order order);

	public void updateOrder(Order order);

	public void deleteOrder(Order order);

	public ArrayList<Order> getAllOrders();

	public Order getOrder(int id);

	public Order[] readFromFile();
	
	public void writeToFile(Order[] orders);
}
