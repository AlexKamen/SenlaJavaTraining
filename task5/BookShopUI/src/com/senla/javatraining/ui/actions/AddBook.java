package com.senla.javatraining.ui.actions;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.ui.Scan;

public class AddBook implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public AddBook() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}

	@Override
	public void execute() {
		/*System.out.println("Book author");
		String author = this.scanner.getStringValue();
		
		System.out.println("Book title");
		String title = this.scanner.getStringValue();
		
		System.out.println("Book date release");
		
		System.out.println("Book cost");*/

	}

}
