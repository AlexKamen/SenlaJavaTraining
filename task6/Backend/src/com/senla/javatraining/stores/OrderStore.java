package com.senla.javatraining.stores;

import com.senla.javatraining.models.Order;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class OrderStore implements IOrderStore {
	private static OrderStore instance;
	
	private ArrayList<Order> orders;

	/* Last generated id */
	private int lastInsertedId;

	public OrderStore() {

	}

	public static OrderStore getInstance() {
    	if (instance == null) {
            instance = new OrderStore();
        }
     
        return instance;
    }

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		
		/* Fill Order object fields */
		order.setId(this.generateId());
		order.setDateCreation(new Date());
		order.setStatus(Order.STATUS_CREATE);
		
		ArrayList<Order> orders = this.getAllOrders();
		
		orders.add(order);
	}

	public void updateOrder(Order order) {
		ArrayList<Order> orders = this.getAllOrders();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == order.getId()) {
				orders.set(i, order);
			}
		}
	}

	public void deleteOrder(Order order) {
		ArrayList<Order> orders = this.getAllOrders();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == order.getId()) {
				orders.remove(i);
			}
		}
	}

	public ArrayList<Order> getAllOrders() {
		return this.orders;
	}

	public Order getOrder(int id) {
		ArrayList<Order> orders = this.getAllOrders();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == id) {
				return orders.get(i);
			}
		}

		return null;
	}

	private int generateId() {
		this.lastInsertedId = this.getLastId() + 1;
		return this.lastInsertedId;
	}

	private int getLastId() {
		if (this.lastInsertedId != 0) {
			return this.lastInsertedId;
		}
		else {
			ArrayList<Order> orders = this.getAllOrders();
			if (orders == null || orders.size() == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getId() > maxId) {
						maxId = orders.get(i).getId();
					}
				}
				return maxId;
			}
		}
	}
}
