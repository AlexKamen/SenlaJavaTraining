package com.senla.javatraining.ui.actions;

import com.senla.javatraining.ui.Scan;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;

import java.util.ArrayList;

public class ListBooks implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public ListBooks() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		System.out.println("Select order type");
		System.out.println(BookShop.ORDERBY_ALPHABET +" - order by alphabet");
		System.out.println(BookShop.ORDERBY_DATE_RELEASE +" - order by date release");
		System.out.println(BookShop.ORDERBY_COST +" - order by cost");
		System.out.println(BookShop.ORDERBY_COUNT_EXEMPLARS +" - order by stock availability");
		
		int orderValue = this.scanner.getIntValue();
		
		ArrayList<Book> books = this.bookShop.getBooks(orderValue);
		
		for (Book book : books) {
			System.out.println(book.getId() + ". " + book.getAuthor() + ". " + book.getTitle());
		}
		
	}

}
