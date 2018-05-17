package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Request;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class ListBooksRequests implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public ListBooksRequests() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		System.out.println("Select order type");
		System.out.println(BookShop.ORDERBY_ALPHABET +" - order by alphabet");
		System.out.println(BookShop.ORDERBY_COUNT_REQUESTS +" - order by requests count");
		
		int orderValue = this.scanner.getIntValue();
		
		ArrayList<Request> requests = this.bookShop.getBooksRequests(orderValue);
		
		for (Request request : requests) {
			System.out.println(request.getBook().getId() + ". "
							   + request.getBook().getAuthor() + " "
							   + request.getBook().getTitle() + " "
							   + "Requests: " + request.getCountRequests());
		}
	}

}