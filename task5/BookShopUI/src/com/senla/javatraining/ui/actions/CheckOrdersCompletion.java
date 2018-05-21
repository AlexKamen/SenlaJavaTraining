package com.senla.javatraining.ui.actions;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;

public class CheckOrdersCompletion implements IAction {
	private IBookShop bookShop;
	
	public CheckOrdersCompletion() {
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		this.bookShop.checkOrdersCompletion();
		System.out.println("Orders completion checked");
	}

}
