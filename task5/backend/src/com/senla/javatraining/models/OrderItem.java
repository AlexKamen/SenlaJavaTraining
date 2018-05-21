/**
 * One book item in order
 */
package com.senla.javatraining.models;

/**
 * @author Alexander
 *
 */
public class OrderItem {
	private Book book;
	private int countExemplars;
	
	public OrderItem(Book book, int countExemplars) {
		this.book = book;
		this.countExemplars = countExemplars;
	}

	public Book getBook() {
		return this.book;
	}

	public int getCount() {
		return this.countExemplars;
	}
}
