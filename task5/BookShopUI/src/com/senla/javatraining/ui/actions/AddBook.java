package com.senla.javatraining.ui.actions;

import java.util.Date;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class AddBook implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public AddBook() {
		this.scanner = Scan.getInstance();
		this.bookShop = BookShop.getInstance();
	}

	@Override
	public void execute() {
		System.out.println("Book author");
		String author = this.scanner.getStringValue();
		
		System.out.println("Book title");
		String title = this.scanner.getStringValue();
		
		System.out.println("Book date release");
		Date dateRelease = this.scanner.getDateValue();
		
		System.out.println("Book cost");
		double cost = this.scanner.getDoubleValue();
		
		Book book = new Book(title, author, dateRelease, cost);
		this.bookShop.addBook(book);
	}

}
