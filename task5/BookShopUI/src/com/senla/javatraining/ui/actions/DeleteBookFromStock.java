package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class DeleteBookFromStock implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public DeleteBookFromStock() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		ArrayList<Book> books = this.bookShop.getBooks(BookShop.ORDERBY_ALPHABET);
		
		for (Book book : books) {
			System.out.println(book.getId() + " - " + book.getAuthor() + ". " + book.getTitle());
		}
		
		int bookId = this.scanner.getIntValue();

		this.bookShop.deleteBookFromStock(this.bookShop.getBook(bookId));
	}

}
