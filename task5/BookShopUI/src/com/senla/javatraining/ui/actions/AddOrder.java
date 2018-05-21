package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.ui.Scan;

public class AddOrder implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public AddOrder() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		Order order = new Order();
		
		/* Default random value */
		int bookId;
		int countExemplars;
		while (true) {
			System.out.println("Add order items");
			System.out.println("0 - finish of items add");
			
			ArrayList<Book> books = this.bookShop.getBooks(BookShop.ORDERBY_ALPHABET);
			
			for (Book book : books) {
				System.out.println(book.getId() + " - " + book.getAuthor() + ". " + book.getTitle());
			}
			bookId = this.scanner.getIntValue();
			
			if (bookId == 0) {
				break;
			}
			
			System.out.println("Enter count exemplars");
			countExemplars = this.scanner.getIntValue();
			
			OrderItem orderItem = new OrderItem(this.bookShop.getBook(bookId), countExemplars);
			order.addItem(orderItem);
		}

		this.bookShop.addOrderWithCheckBookExistance(order);
	}

}
