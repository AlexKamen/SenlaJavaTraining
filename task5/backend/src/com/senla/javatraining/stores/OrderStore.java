package com.senla.javatraining.stores;

import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.training.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class OrderStore implements IOrderStore{
	private static final String ORDER_FILE = "orders.txt";
	
	private Order[] orders;
	
	/* Last generated id */
	private int lastInsertedId;
	private final FileWorker fileWorker;

	public OrderStore() {
		this.fileWorker = new TextFileWorker(ORDER_FILE);
	}

	public void addOrder(Order order) {
		
		/* Fill Order object fields */
		order.setId(this.generateId());
		order.setDateCreation(new Date());
		order.setStatus(order.STATUS_CREATE);
		
		/* Some manipulations for add new order to existance */
		Order[] orders = this.getAllOrders();
		ArrayList<Order> result = new ArrayList<Order>();
		if (!(orders == null || orders.length == 0)) {
			result = new ArrayList(Arrays.asList(orders));
		}
		
		result.add(order);
		this.writeToFile(result.toArray(new Order[result.size()]));
	}

	public void updateOrder(Order order) {
		Order[] orders = this.getAllOrders();
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == order.getId()) {
				orders[i] = order;
			}
		}

		this.writeToFile(orders);
	}

	public void deleteOrder(Order order) {
		Order[] orders = this.getAllOrders();
		ArrayList<Order> result = new ArrayList<Order>(Arrays.asList(orders));
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == order.getId()) {
				result.remove(i);
			}
		}

		this.writeToFile(result.toArray(new Order[result.size()]));
	}

	public Order[] getAllOrders() {
		Order[] orders = this.readFromFile();
		return orders;
	}

	public Order getOrder(int id) {
		Order[] orders = this.getAllOrders();
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == id) {
				return orders[i];
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
			Order[] orders = this.getAllOrders();
			if (orders == null || orders.length == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < orders.length; i++) {
					if (orders[i].getId() > maxId) {
						maxId = orders[i].getId();
					}
				}
				return maxId;
			}
		}
	}

	private Order[] readFromFile() {
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			return null;
		}
		
		final Order[] result = new Order[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			result[i] = parseFileLine(lines[i]);
		}
		
		return result;
	}

	private void writeToFile(final Order[] values) {
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException();
		}
		final String[] lines = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			lines[i] = collectFileLine(values[i]);
		}
		fileWorker.writeToFile(lines);
	}

	private String collectFileLine(final Order order) {
		if (order == null) {
			throw new IllegalArgumentException();
		}

		/* Convert order items to string format as idBook1-countExemplars1,idBook2-countExemplars2 */
		String orderItemsStr = "";
		ArrayList<OrderItem> orderItems = order.getItems();
		for (int i = 0; i < orderItems.size(); i++) {
			orderItemsStr += orderItems.get(i).getBook().getId()
							 + "-" + orderItems.get(i).getCount()
							 + ",";
		}
		orderItemsStr = orderItemsStr.substring(0, orderItemsStr.length() - 1);
		
		final String[] objectFields = new String[] { 
				String.valueOf(order.getId()),
				String.valueOf(order.getDateCreation().getTime()),
				String.valueOf(order.getDateCompletion().getTime()),
				orderItemsStr,
				String.valueOf(order.getStatus()),
				String.valueOf(order.getCanceled())				
			};
		return String.join(";", objectFields);
	}
	
	private Order parseFileLine(final String line) {
		if (line == null || line.isEmpty()) {
			throw new IllegalArgumentException();
		}

		final String[] parts = line.split(";");

		Order order = new Order(new Date(Long.valueOf(parts[2])));

		/* Get order items */
		final String[] itemsParts = parts[3].split(",");
		for (int i = 0; i < itemsParts.length; i++) {
			String[] bookIdAndCount = itemsParts[i].split("-");
			order.addItem(new OrderItem(new BookStore().getBook(Integer.valueOf(bookIdAndCount[0])),
						  				Integer.valueOf(bookIdAndCount[1])));
		}
		order.setId(Integer.valueOf(parts[0]));
		order.setDateCreation(new Date(Long.valueOf(parts[1])));
		order.setStatus(Integer.valueOf(parts[4]));
		order.setCanceled(Boolean.valueOf(parts[5]));
		
		return order;
	}
}
