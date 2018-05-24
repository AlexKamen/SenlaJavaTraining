package com.senla.javatraining.stores;

import com.senla.javatraining.models.Book;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class BookStore implements IBookStore {
	private static BookStore instance;
	
	private ArrayList<Book> books;

	/* Last generated id */
	private int lastInsertedId;

	public BookStore() {
		
	}

	public static BookStore getInstance() {
    	if (instance == null) {
            instance = new BookStore();
        }
     
        return instance;
    }

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		
		/* Fill Book object fields */
		book.setId(this.generateId());
		book.setCountExemplars(0);
		book.setDateLastReceipt(new Date());

		ArrayList<Book> books = this.getAllBooks();

		books.add(book);
	}

	public void updateBook(Book book) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == book.getId()) {
				books.set(i, book);
			}
		}
	}

	public void deleteBook(Book book) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == book.getId()) {
				books.remove(i);
			}
		}
	}

	public ArrayList<Book> getAllBooks() {
		return this.books;
	}

	public Book getBook(int id) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == id) {
				return books.get(i);
			}
		}

		return null;
	}

	private int generateId() {
		this.lastInsertedId = this.getLastId() + 1;
		return this.lastInsertedId;
	}

	private int getLastId() {
		if (this.lastInsertedId != 0) {
			return this.lastInsertedId;
		}
		else {
			ArrayList<Book> books = this.getAllBooks();
			if (books == null || books.size() == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < books.size(); i++) {
					if (books.get(i).getId() > maxId) {
						maxId = books.get(i).getId();
					}
				}
				return maxId;
			}
		}
		
	}
}
