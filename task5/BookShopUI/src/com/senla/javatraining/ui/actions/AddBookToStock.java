package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class AddBookToStock implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public AddBookToStock() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
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
		
		this.bookShop.addBookToStock(book, countExemplars);
	}

}
