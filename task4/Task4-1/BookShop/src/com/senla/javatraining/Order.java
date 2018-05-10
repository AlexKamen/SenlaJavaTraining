package com.senla.javatraining;

import java.util.ArrayList;
import java.util.Date;

/**
 * Order Model
 * @author Alexander
 *
 */
public class Order {
	public static final int STATUS_CREATE = 1;
	public static final int STATUS_NOT_STAFF = 2;
	public static final int STATUS_COMPLETE = 3;
	public static final int STATUS_CANCEL = 4;
	public static final int MIN_DAYS_TO_COMPLETE = 2;
	
	private int idOrder;
	private Date dateCreation;
	private Date dateCompletion;
	private ArrayList<OrderItem> items = new ArrayList<OrderItem>();
	
	/** Int value of order status would be receive from class constant */
	private int status;
	
	/** Is order canceled */
	private boolean canceled = false;
	
	public Order() {
		
	}
	
	public Order(ArrayList<OrderItem> items) {
		this.items = items;
		this.status = STATUS_CREATE;
	}

	public Order(Date dateCompletion) {
		this.dateCompletion = dateCompletion;
		this.status = STATUS_CREATE;
	}

	public Order(ArrayList<OrderItem> items, Date dateCompletion) {
		this.items = items;
		this.dateCompletion = dateCompletion;
		this.status = STATUS_CREATE;
	}

	public void setId(int newId) {
		this.idOrder = newId;
	}

	public int getId() {
		return this.idOrder;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCompletion(Date dateCompletion) {
		this.dateCompletion = dateCompletion;
	}
	
	public Date getDateCompletion() {
		return this.dateCompletion;
	}

	public void addItem(OrderItem item) {
		if (item != null) {
			this.items.add(item);
		}
	}

	public ArrayList<OrderItem> getItems() {
		return this.items;
	}

	public void setStatus(int newStatus) {
		this.status = newStatus;
	}

	public int getStatus() {
		return this.status;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean getCanceled() {
		return this.canceled;
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (OrderItem item : this.items) {
			totalPrice += item.getBook().getCost() * item.getCount();
		}

		return totalPrice;
	}
}