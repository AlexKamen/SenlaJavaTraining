/**
 * One book item in order
 */
package com.senla.javatraining.models;

import java.io.Serializable;

/**
 * @author Alexander
 *
 */
public class OrderItem implements Serializable, Cloneable {
	private static final long serialVersionUID = 2645364649827050868L;

	private Book book;
	private int countExemplars;
	
	public OrderItem(Book book, int countExemplars) {
		this.book = book;
		this.countExemplars = countExemplars;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return this.book;
	}

	public int getCount() {
		return this.countExemplars;
	}

	public void setCountExemplars(int countExemplars) {
		this.countExemplars = countExemplars;
	}

	@Override
	public OrderItem clone() {
		try {
			return (OrderItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
