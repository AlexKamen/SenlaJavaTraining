package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class ListOldBooks implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public ListOldBooks() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		System.out.println("Select order type");
		System.out.println(BookShop.ORDERBY_DATE_LAST_RECEIPT +" - order by date receipt");
		System.out.println(BookShop.ORDERBY_COST +" - order by cost");

		int orderValue = this.scanner.getIntValue();
		
		ArrayList<Book> books = this.bookShop.getOldBooks(orderValue);
		if (books.size() == 0) {
			System.out.println("Nothing to show");
		} else {
			for (Book book : books) {
				System.out.println(book.getId() + ". " + book.getAuthor() + ". " + book.getTitle());
			}
		}
		
	}

}
