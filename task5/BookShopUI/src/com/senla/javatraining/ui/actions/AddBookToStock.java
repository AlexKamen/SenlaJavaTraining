package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class AddBookToStock implements IAction {
	public static final Logger logger = Logger.getLogger(AddBookToStock.class.getName());
	
	private Scan scanner;
	private IBookShop bookShop;
	
	public AddBookToStock() {
		this.scanner = Scan.getInstance();
		this.bookShop = BookShop.getInstance();
	}
	
	@Override
	public void execute() {
		System.out.println("Select book (enter the ID)");
		ArrayList<Book> books = this.bookShop.getBooks(BookShop.ORDERBY_ALPHABET);
		
		for (Book book : books) {
			System.out.println(book.getId() + " - " + book.getAuthor() + ". " + book.getTitle());
		}
		
		int bookId = this.scanner.getIntValue();
		Book book = this.bookShop.getBook(bookId);

		System.out.println("Enter count exemplars");
		int countExemplars = this.scanner.getIntValue();
		try {
			this.bookShop.addBookToStock(book, countExemplars);
		} catch(NullPointerException e) {
			System.out.println("Book not found");
			logger.error("User entered incorrect book number", e);
		}
	}

}
