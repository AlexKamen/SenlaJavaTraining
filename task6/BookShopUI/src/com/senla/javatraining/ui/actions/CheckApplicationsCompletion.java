package com.senla.javatraining.ui.actions;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;

public class CheckApplicationsCompletion implements IAction {
	private IBookShop bookShop;
	
	public CheckApplicationsCompletion() {
		this.bookShop = BookShop.getInstance();
	}
	
	@Override
	public void execute() {
		this.bookShop.checkApplicationsCompletion();
		System.out.println("Applications completion checked");
	}

}
