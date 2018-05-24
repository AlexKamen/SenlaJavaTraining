/**
 * Add count requests for book. Based on order
 */
package com.senla.javatraining.models;

import java.util.ArrayList;

import com.senla.javatraining.stores.OrderStore;

/**
 * @author Alexander
 *
 */
public class Request {
	private Book book;
	private int countRequests;

	public Request(Book book) {
		this.book = book;
		this.countRequests = this.calculateCountRequests();
	}

	public int calculateCountRequests() {
		OrderStore orderStore = OrderStore.getInstance();
		ArrayList<Order> orders = orderStore.getAllOrders();
		int countRequests = 0;
		for (int i = 0; i < orders.size(); i++) {
			for (OrderItem orderItem : orders.get(i).getItems()) {
				if (orderItem.getBook().getId() == this.book.getId()) {
					countRequests++;
				}
			}
		}

		return countRequests;
	}

	public Book getBook() {
		return this.book;
	}
	
	public int getCountRequests() {
		return this.countRequests;
	}
}
