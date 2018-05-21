package com.senla.javatraining.ui.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.ui.Scan;

public class BookDescription implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public BookDescription() {
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Book book = this.bookShop.getBook(bookId);
		System.out.println("Book #" + bookId + " details");
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Title: " + book.getTitle());
		System.out.println("Date release: " + formatter.format(book.getDateRelease()));
		System.out.println("Cost: " + book.getCost());
	}

}
