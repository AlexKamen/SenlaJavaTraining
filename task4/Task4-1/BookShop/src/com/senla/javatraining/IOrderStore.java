package com.senla.javatraining;

public interface IOrderStore {
	public void addOrder(Order order);

	public void updateOrder(Order order);

	public void deleteOrder(Order order);

	public Order[] getAllOrders();

	public Order getOrder(int id);
}
